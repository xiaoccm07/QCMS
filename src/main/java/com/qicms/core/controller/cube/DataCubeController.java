package com.qicms.core.controller.cube;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qicms.core.mappers.CubeQueryMapper;

@Controller
@RequestMapping("/")
public class DataCubeController {

	@Autowired
	CubeQueryMapper cubeQueryMapper;

	/**
	 * 数据自助查询，按报表条件返回结果
	 */
	@RequestMapping("x7Stat/cuBe_9/flexQ/rawExec")
	public @ResponseBody Object flex(@RequestParam(defaultValue = "aoa_user") String table,
			@RequestParam(defaultValue = "*") String cols,
			@RequestParam(required = false) String where,
			@RequestParam(required = false) String order) {
		return cubeQueryMapper.flexQuery(table, cols, where, order);
	}

	/**
	 * 将查询结果导出为CSV文件
	 */
	@RequestMapping("x7Stat/cuBe_9/flexQ/dumpCsv")
	public @ResponseBody Object export(@RequestParam(defaultValue = "aoa_user") String table,
			@RequestParam(defaultValue = "*") String cols,
			@RequestParam(required = false) String where,
			@RequestParam(required = false) String order) throws Exception {
		List<Map<String, Object>> rows = cubeQueryMapper.flexQuery(table, cols, where, order);
		StringBuilder sb = new StringBuilder();
		if (rows != null && !rows.isEmpty()) {
			List<String> headers = new ArrayList<String>(rows.get(0).keySet());
			sb.append(join(headers)).append("\r\n");
			for (Map<String, Object> row : rows) {
				List<String> cells = new ArrayList<String>();
				for (String key : headers) {
					Object val = row.get(key);
					cells.add(val == null ? "" : String.valueOf(val));
				}
				sb.append(join(cells)).append("\r\n");
			}
		}
		File dir = new File("./export");
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File("./export/" + table + "_" + System.currentTimeMillis() + ".csv");
		Files.write(file.toPath(), sb.toString().getBytes(Charset.forName("UTF-8")));
		return file.getPath();
	}

	/*拼接CSV一行，字段用逗号分隔*/
	private String join(List<String> cells) {
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < cells.size(); i++) {
			if (i > 0) {
				line.append(",");
			}
			String cell = cells.get(i);
			if (cell.contains(",") || cell.contains("\"") || cell.contains("\n")) {
				line.append("\"").append(cell.replace("\"", "\"\"")).append("\"");
			} else {
				line.append(cell);
			}
		}
		return line.toString();
	}

	/**
	 * 数据自助查询页面
	 */
	@RequestMapping("x7Stat/cuBe_9/flexQ/panelUi")
	public String page(Model model) {
		model.addAttribute("title", "数据自助查询");
		return "cube/flexquery";
	}
}