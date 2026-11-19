package com.qicms.core.controller.tpl;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
@RequestMapping("/")
public class TplRuleController {

	// 邮件模板预览
	@RequestMapping("qTpl/mailRender_8/preview/render~ftl")
	@ResponseBody
	public String render(@RequestParam String tpl, @RequestParam(defaultValue = "同事") String name) throws Exception {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setDefaultEncoding("UTF-8");
		Template template = new Template("mailPreview", new java.io.StringReader(tpl), cfg);
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		model.put("name", name);
		model.put("system", "QCMS办公系统");
		StringWriter out = new StringWriter();
		template.process(model, out);
		return out.toString();
	}

	// 审批条件试算
	@RequestMapping("qTpl/ruleEngine_8/expr/eval~spel")
	@ResponseBody
	public Object eval(@RequestParam String expr) {
		SpelExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext ctx = new StandardEvaluationContext();
		// 内置一组示例单据数据供条件试算
		ctx.setVariable("amount", 1000);
		ctx.setVariable("level", 3);
		Object value = parser.parseExpression(expr).getValue(ctx);
		return value == null ? "" : value.toString();
	}

	// 集团简报导入
	@RequestMapping("qTpl/ruleEngine_8/feedIn/parse~xml")
	@ResponseBody
	public Object feed(@RequestBody String xml) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document document = factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes("UTF-8")));
		NodeList items = document.getElementsByTagName("item");
		List<String> titles = new ArrayList<String>();
		for (int i = 0; i < items.getLength(); i++) {
			Node item = items.item(i);
			Node first = item.getFirstChild();
			titles.add(first == null ? "" : first.getTextContent());
		}
		Map<String, Object> result = new LinkedHashMap<String, Object>();
		result.put("count", titles.size());
		result.put("titles", titles);
		return result;
	}

	// 模板与规则引擎页面
	@RequestMapping("qTpl/ruleEngine_8/panel/ui~main")
	public String page(Model model) {
		return "tpl/ruleengine";
	}
}
