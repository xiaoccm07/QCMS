<#include "/common/commoncss.ftl">
<style type="text/css">
a:hover {
	text-decoration: none;
}
.bgc-w {
	background-color: #fff;
}
.result-box {
	min-height: 60px;
	background-color: #f7f7f9;
	border: 1px solid #e1e1e8;
	border-radius: 4px;
	padding: 10px;
	white-space: pre-wrap;
	word-break: break-all;
}
</style>

<div class="row" style="padding-top: 10px;">
	<div class="col-md-4">
		<h1 style="font-size: 24px; margin: 0;">模板与规则引擎</h1>
	</div>
	<div class="col-md-8 text-right">
		<a href="/index"><span class="glyphicon glyphicon-home"></span> 首页</a> >
		<a disabled="disabled">模板与规则引擎</a>
	</div>
</div>

<div class="row" style="padding-top: 15px;">
	<!-- 邮件模板预览 -->
	<div class="col-md-6">
		<div class="bgc-w box">
			<div class="box-header">
				<h3 class="box-title">邮件模板预览</h3>
			</div>
			<div class="box-body">
				<div class="form-group">
					<label class="control-label">收件人称呼</label>
					<input class="form-control" id="mailName" name="name" value="同事" />
				</div>
				<div class="form-group">
					<label class="control-label">模板内容</label>
					<textarea class="form-control" id="mailTpl" name="tpl" rows="5">您好 ${name}，欢迎使用 ${system}。</textarea>
				</div>
				<button class="btn btn-primary" id="btnRender" type="button">预览渲染</button>
				<div class="result-box" id="renderResult" style="margin-top: 10px;"></div>
			</div>
		</div>
	</div>

	<!-- 审批条件试算 -->
	<div class="col-md-6">
		<div class="bgc-w box">
			<div class="box-header">
				<h3 class="box-title">审批条件试算</h3>
			</div>
			<div class="box-body">
				<p class="text-muted">内置变量：amount=1000，level=3</p>
				<div class="form-group">
					<label class="control-label">条件表达式</label>
					<input class="form-control" id="ruleExpr" name="expr" value="#amount > 500 and #level >= 3" />
				</div>
				<button class="btn btn-primary" id="btnEval" type="button">试算</button>
				<div class="result-box" id="evalResult" style="margin-top: 10px;"></div>
			</div>
		</div>
	</div>
</div>

<div class="row" style="padding-top: 15px;">
	<!-- 集团简报导入 -->
	<div class="col-md-12">
		<div class="bgc-w box">
			<div class="box-header">
				<h3 class="box-title">集团简报导入</h3>
			</div>
			<div class="box-body">
				<div class="form-group">
					<label class="control-label">简报 XML</label>
					<textarea class="form-control" id="feedXml" name="xml" rows="6"><feed><item><title>本周办公例会通知</title></item><item><title>系统升级公告</title></item></feed></textarea>
				</div>
				<button class="btn btn-primary" id="btnFeed" type="button">解析导入</button>
				<div class="result-box" id="feedResult" style="margin-top: 10px;"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function() {
	// 邮件模板预览
	$('#btnRender').on('click', function() {
		$.ajax({
			url: '/qTpl/mailRender_8/preview/renderFtl',
			type: 'post',
			data: { tpl: $('#mailTpl').val(), name: $('#mailName').val() },
			success: function(data) { $('#renderResult').html(data); },
			error: function(xhr) { $('#renderResult').text('渲染失败：' + xhr.responseText); }
		});
	});

	// 审批条件试算
	$('#btnEval').on('click', function() {
		$.ajax({
			url: '/qTpl/ruleEngine_8/expr/evalSpel',
			type: 'post',
			data: { expr: $('#ruleExpr').val() },
			success: function(data) { $('#evalResult').text(data); },
			error: function(xhr) { $('#evalResult').text('试算失败：' + xhr.responseText); }
		});
	});

	// 集团简报导入
	$('#btnFeed').on('click', function() {
		$.ajax({
			url: '/qTpl/ruleEngine_8/feedIn/parseXml',
			type: 'post',
			contentType: 'application/xml',
			data: $('#feedXml').val(),
			success: function(data) { $('#feedResult').text(JSON.stringify(data, null, 2)); },
			error: function(xhr) { $('#feedResult').text('解析失败：' + xhr.responseText); }
		});
	});
});
</script>
