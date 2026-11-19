package com.qicms.core.controller.sqli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qicms.core.mappers.InjectLabMapper;

@Controller
@RequestMapping("/")
public class InjectLabController {

	@Autowired
	InjectLabMapper injectLabMapper;

	/**
	 * 公告检索，按标题关键字与排序字段查询
	 */
	@RequestMapping("sInject/noticeQ_1/search/raw~kw")
	public @ResponseBody Object noticeSearch(@RequestParam(defaultValue = "") String kw,
			@RequestParam(defaultValue = "notice_id desc") String sort) {
		return injectLabMapper.noticeSearch(kw, sort);
	}

	/**
	 * 外部联系人筛选，按拼音前缀与分类过滤
	 */
	@RequestMapping("sInject/directorQ_2/filter/raw~py")
	public @ResponseBody Object directorFilter(@RequestParam(defaultValue = "A") String pinyin,
			@RequestParam(defaultValue = "") String catalog) {
		return injectLabMapper.directorFilter(pinyin, catalog);
	}

	/**
	 * 人员模糊查询，按姓名与部门匹配
	 */
	@RequestMapping("sInject/userQ_3/like/raw~nm")
	public @ResponseBody Object userLike(@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") String dept) {
		return injectLabMapper.userLike(name, dept);
	}

	/**
	 * 文件排序，按类型筛选并指定排序字段
	 */
	@RequestMapping("sInject/fileQ_4/order/raw~tp")
	public @ResponseBody Object fileOrder(@RequestParam(defaultValue = "pdf") String type,
			@RequestParam(defaultValue = "file_id desc") String order) {
		return injectLabMapper.fileOrder(type, order);
	}

	/**
	 * 任务条件查询，查询条件由页面组装
	 */
	@RequestMapping("sInject/taskQ_5/where/raw~cd")
	public @ResponseBody Object taskWhere(@RequestParam(defaultValue = "1=1") String cond) {
		return injectLabMapper.taskWhere(cond);
	}

	/**
	 * 分组统计，按指定字段聚合并过滤分组结果
	 */
	@RequestMapping("sInject/statQ_6/group/raw~col")
	public @ResponseBody Object statGroup(@RequestParam(defaultValue = "dept_id") String col,
			@RequestParam(defaultValue = "c>0") String having) {
		return injectLabMapper.statGroup(col, having);
	}

	/**
	 * 数据检索中心页面
	 */
	@RequestMapping("sInject/labQ_0/panel/ui~main")
	public String page(Model model) {
		model.addAttribute("title", "数据检索中心");
		return "sqli/injectlab";
	}
}