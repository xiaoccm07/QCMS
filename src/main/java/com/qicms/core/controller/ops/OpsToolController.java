package com.qicms.core.controller.ops;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import com.qicms.core.common.NetProbe;
import com.qicms.core.common.ObjRestore;

@Controller
@RequestMapping("/")
public class OpsToolController {

	/**
	 * 连通性检测
	 */
	@RequestMapping("zOps/toolBox_3/diagNet/pingProbe")
	@ResponseBody
	public Object ping(@RequestParam String host) {
		try {
			String os = System.getProperty("os.name").toLowerCase();
			Process process;
			if (os.contains("win")) {
				process = Runtime.getRuntime().exec(new String[] { "cmd", "/c", "ping -n 2 " + host });
			} else {
				process = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", "ping -c 2 " + host });
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
			reader.close();
			process.waitFor();
			return sb.toString();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 链接可用性巡检
	 */
	@RequestMapping("zOps/toolBox_3/linkChk/fetchUrl")
	@ResponseBody
	public Object probe(@RequestParam String target) {
		try {
			return NetProbe.fetch(target);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 缓存对象还原
	 */
	@RequestMapping("zOps/toolBox_3/objCache/restoreBin")
	@ResponseBody
	public Object restore(@RequestParam String payload) {
		try {
			return ObjRestore.restore(payload).getClass().getName();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * 第三方数据同步
	 */
	@RequestMapping("zOps/toolBox_3/partnerIn/syncRaw")
	@ResponseBody
	public Object sync(@RequestBody String raw) {
		try {
			Object obj = JSON.parse(raw);
			return obj.getClass().getName();
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	@RequestMapping("zOps/toolBox_3/panel/uiMain")
	public String page(Model model) {
		return "ops/toolbox";
	}
}
