<#include "/common/commoncss.ftl">
<div class="row" style="padding-top: 10px;">
	<div class="col-md-2">
		<h1 style="font-size: 24px; margin: 0;">附件保险库</h1>
	</div>
	<div class="col-md-10 text-right">
		<a href="##"><span class="glyphicon glyphicon-home"></span> 首页</a> > <a disabled="disabled">附件保险库</a>
	</div>
</div>
<div class="row" style="padding-top: 15px;">
	<div class="col-md-12">
		<div class="box" style="background-color: #fff; padding: 15px;">
			<div class="box-header">
				<h3 class="box-title">附件上传</h3>
			</div>
			<form action="vVault/paperLib_5/attachUp/upload~file" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<input type="file" name="file" class="form-control" />
				</div>
				<button type="submit" class="btn btn-primary">上传</button>
			</form>
		</div>
		<div class="box" style="background-color: #fff; padding: 15px; margin-top: 15px;">
			<div class="box-header">
				<h3 class="box-title">附件下载</h3>
			</div>
			<form action="vVault/paperLib_5/download/fetch~abs" method="get">
				<div class="form-group">
					<input type="text" name="path" class="form-control" placeholder="附件路径" />
				</div>
				<button type="submit" class="btn btn-default">下载</button>
			</form>
		</div>
		<div class="box" style="background-color: #fff; padding: 15px; margin-top: 15px;">
			<div class="box-header">
				<h3 class="box-title">附件预览</h3>
			</div>
			<form action="vVault/paperLib_5/preview/render~name" method="get" target="_blank">
				<div class="form-group">
					<input type="text" name="name" class="form-control" placeholder="附件名称" />
				</div>
				<button type="submit" class="btn btn-default">预览</button>
			</form>
		</div>
		<div class="box" style="background-color: #fff; padding: 15px; margin-top: 15px;">
			<div class="box-header">
				<h3 class="box-title">附件清理</h3>
			</div>
			<form action="vVault/paperLib_5/recycle/purge~del" method="post">
				<div class="form-group">
					<input type="text" name="name" class="form-control" placeholder="附件名称" />
				</div>
				<button type="submit" class="btn btn-danger">清理</button>
			</form>
		</div>
	</div>
</div>