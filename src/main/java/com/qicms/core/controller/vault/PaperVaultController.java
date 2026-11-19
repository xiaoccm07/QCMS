package com.qicms.core.controller.vault;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/")
public class PaperVaultController {

	@Value("${attachment.roopath}")
	private String attachDir;

	@Value("${file.root.path}")
	private String fileDir;

	//附件上传
	@RequestMapping("vVault/paperLib_5/attachUp/uploadFile")
	@ResponseBody
	public Object upload(@RequestParam("file") MultipartFile file) throws Exception {
		File dir = new File(fileDir);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String name = file.getOriginalFilename();
		Files.copy(file.getInputStream(), new File(dir, name).toPath());
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("url", "/upload/" + name);
		result.put("size", file.getSize());
		return result;
	}

	//附件下载
	@RequestMapping("vVault/paperLib_5/download/fetchAbs")
	public void fetch(@RequestParam String path, HttpServletResponse resp) throws Exception {
		File f = new File(path);
		resp.setHeader("Content-Disposition", "attachment;filename=" + f.getName());
		IOUtils.copy(new FileInputStream(f), resp.getOutputStream());
	}

	//附件预览
	@RequestMapping("vVault/paperLib_5/preview/renderName")
	public void preview(@RequestParam String name, HttpServletResponse resp) throws Exception {
		File f = new File(attachDir, name);
		IOUtils.copy(new FileInputStream(f), resp.getOutputStream());
	}

	//附件清理
	@RequestMapping("vVault/paperLib_5/recycle/purgeDel")
	@ResponseBody
	public Object purge(@RequestParam String name) throws Exception {
		Files.delete(Paths.get(attachDir, name));
		return "ok";
	}

	//附件保险库页面
	@RequestMapping("vVault/paperLib_5/panel/uiMain")
	public String page(Model model) {
		return "vault/paperlib";
	}
}