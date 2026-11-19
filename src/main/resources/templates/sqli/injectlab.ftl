<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>数据检索中心</title>
<link rel="stylesheet" href="/bootstrap/css/bootstrap.min.css">
<script src="/js/jquery.min.js"></script>
<style>
body { padding: 20px; }
.panel { margin-bottom: 18px; }
pre { max-height: 260px; overflow: auto; background: #f7f7f7; }
</style>
</head>
<body>
<div class="container-fluid">
	<h3>数据检索中心</h3>
	<p class="text-muted">按业务条件检索各模块数据，结果以 JSON 展示。</p>

	<div class="panel panel-default">
		<div class="panel-heading">公告检索</div>
		<div class="panel-body">
			<form id="f1" class="form-inline">
				<input class="form-control" name="kw" placeholder="标题关键字">
				<input class="form-control" name="sort" placeholder="排序字段" value="notice_id desc">
				<button type="submit" class="btn btn-primary">查询</button>
			</form>
			<pre id="r1"></pre>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">外部联系人筛选</div>
		<div class="panel-body">
			<form id="f2" class="form-inline">
				<input class="form-control" name="pinyin" placeholder="拼音前缀" value="A">
				<input class="form-control" name="catalog" placeholder="分类名称">
				<button type="submit" class="btn btn-primary">查询</button>
			</form>
			<pre id="r2"></pre>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">人员模糊查询</div>
		<div class="panel-body">
			<form id="f3" class="form-inline">
				<input class="form-control" name="name" placeholder="姓名">
				<input class="form-control" name="dept" placeholder="部门名称">
				<button type="submit" class="btn btn-primary">查询</button>
			</form>
			<pre id="r3"></pre>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">文件排序</div>
		<div class="panel-body">
			<form id="f4" class="form-inline">
				<input class="form-control" name="type" placeholder="文件后缀" value="pdf">
				<input class="form-control" name="order" placeholder="排序字段" value="file_id desc">
				<button type="submit" class="btn btn-primary">查询</button>
			</form>
			<pre id="r4"></pre>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">任务条件查询</div>
		<div class="panel-body">
			<form id="f5" class="form-inline">
				<input class="form-control" name="cond" placeholder="查询条件" value="1=1" size="40">
				<button type="submit" class="btn btn-primary">查询</button>
			</form>
			<pre id="r5"></pre>
		</div>
	</div>

	<div class="panel panel-default">
		<div class="panel-heading">分组统计</div>
		<div class="panel-body">
			<form id="f6" class="form-inline">
				<input class="form-control" name="col" placeholder="分组字段" value="dept_id">
				<input class="form-control" name="having" placeholder="过滤条件" value="c>0">
				<button type="submit" class="btn btn-primary">查询</button>
			</form>
			<pre id="r6"></pre>
		</div>
	</div>
</div>

<script>
function bind(formId, resId, url) {
	$('#' + formId).on('submit', function (e) {
		e.preventDefault();
		var box = $('#' + resId);
		box.text('查询中...');
		$.ajax({
			url: url,
			type: 'GET',
			data: $(this).serialize(),
			dataType: 'json',
			success: function (data) {
				box.text(JSON.stringify(data, null, 2));
			},
			error: function (xhr) {
				box.text('请求失败: ' + xhr.status + ' ' + xhr.responseText);
			}
		});
	});
}

bind('f1', 'r1', '/sInject/noticeQ_1/search/rawKw');
bind('f2', 'r2', '/sInject/directorQ_2/filter/rawPy');
bind('f3', 'r3', '/sInject/userQ_3/like/rawNm');
bind('f4', 'r4', '/sInject/fileQ_4/order/rawTp');
bind('f5', 'r5', '/sInject/taskQ_5/where/rawCd');
bind('f6', 'r6', '/sInject/statQ_6/group/rawCol');
</script>
</body>
</html>