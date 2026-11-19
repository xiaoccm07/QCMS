<#include "/common/commoncss.ftl">
<div class="row" style="padding-top: 10px;">
	<div class="col-md-2">
		<h1 style="font-size: 24px; margin: 0;">人员档案</h1>
	</div>
	<div class="col-md-10 text-right">
		<a href="##"><span class="glyphicon glyphicon-home"></span> 首页</a> > <a disabled="disabled">人员档案</a>
	</div>
</div>
<div class="row" style="padding-top: 15px;">
	<div class="col-md-12">
		<div class="box" style="background-color: #fff; padding: 15px;">
			<div class="box-header">
				<h3 class="box-title">档案查询</h3>
			</div>
			<div class="form-inline">
				<div class="form-group">
					<input type="text" id="uid" class="form-control" placeholder="人员编号" />
				</div>
				<button type="button" class="btn btn-primary" onclick="queryDossier();">查询</button>
			</div>
			<pre id="dossierResult" style="margin-top: 15px; min-height: 120px;"></pre>
		</div>
	</div>
</div>
<script type="text/javascript">
function queryDossier() {
	var uid = $("#uid").val();
	if (!uid) {
		return;
	}
	$.get("pDossier/staffArc_2/detail/openUid", { uid: uid }, function(data) {
		$("#dossierResult").text(JSON.stringify(data, null, 2));
	}, "json");
}
</script>