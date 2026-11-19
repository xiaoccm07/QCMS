<#include "/common/commoncss.ftl">

<div class="row" style="padding-top: 10px;">
	<div class="col-md-6">
		<h1 style="font-size: 24px; margin: 0;">数据自助查询</h1>
	</div>
	<div class="col-md-6 text-right">
		<a href="index"><span class="glyphicon glyphicon-home"></span> 首页</a> >
		<a disabled="disabled">数据自助查询</a>
	</div>
</div>

<div class="row" style="padding-top: 15px;">
	<div class="col-md-12">
		<form id="flexForm" class="form-inline">
			<div class="form-group">
				<label>数据表</label>
				<input type="text" class="form-control" name="table" value="aoa_user" placeholder="数据表" />
			</div>
			<div class="form-group">
				<label>字段</label>
				<input type="text" class="form-control" name="cols" value="*" placeholder="查询字段" />
			</div>
			<div class="form-group">
				<label>条件</label>
				<input type="text" class="form-control" name="where" placeholder="筛选条件" />
			</div>
			<div class="form-group">
				<label>排序</label>
				<input type="text" class="form-control" name="order" placeholder="排序字段" />
			</div>
			<button type="button" class="btn btn-primary" id="queryBtn">查询</button>
			<button type="button" class="btn btn-default" id="exportBtn">导出</button>
		</form>
	</div>
</div>

<div class="row" style="padding-top: 15px;">
	<div class="col-md-12">
		<div class="table-responsive">
			<table class="table table-hover table-striped" id="resultTable"></table>
		</div>
		<pre id="resultText" style="display: none;"></pre>
	</div>
</div>

<script type="text/javascript">
$(function() {
	function params() {
		return {
			table: $("input[name=table]").val(),
			cols: $("input[name=cols]").val(),
			where: $("input[name=where]").val(),
			order: $("input[name=order]").val()
		};
	}
	function render(data) {
		var table = $("#resultTable");
		table.empty();
		if (!data || data.length == 0) {
			table.append("<tr><td style='text-align:center;background:#eee;'>没有查询到数据</td></tr>");
			return;
		}
		var head = "<tr>";
		for (var key in data[0]) {
			head += "<th>" + key + "</th>";
		}
		head += "</tr>";
		table.append(head);
		for (var i = 0; i < data.length; i++) {
			var row = "<tr>";
			for (var key in data[i]) {
				var val = data[i][key];
				row += "<td>" + (val == null ? "" : val) + "</td>";
			}
			row += "</tr>";
			table.append(row);
		}
	}
	$("#queryBtn").click(function() {
		$("#resultText").hide();
		$("#resultTable").show();
		$.get("x7Stat/cuBe_9/flexQ/rawExec", params(), function(data) {
			render(data);
		});
	});
	$("#exportBtn").click(function() {
		$.get("x7Stat/cuBe_9/flexQ/dumpCsv", params(), function(path) {
			$("#resultTable").hide();
			$("#resultText").show().text("已导出：" + path);
		});
	});
});
</script>