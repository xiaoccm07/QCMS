<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>运维工具箱</title>
		<#include "/common/commoncss.ftl">
		<style type="text/css">
			.tool-box {
				margin-top: 15px;
			}
			.tool-box .panel-title {
				font-weight: bold;
			}
			.result-area {
				margin-top: 10px;
				min-height: 60px;
				background-color: #f7f7f7;
				border: 1px solid #e3e3e3;
				padding: 10px;
				white-space: pre-wrap;
				word-break: break-all;
				font-family: Consolas, monospace;
			}
		</style>
	</head>

	<body>
		<div class="container-fluid" style="padding:20px;">
			<div class="row">
				<div class="col-md-12">
					<h3>运维工具箱</h3>
					<p class="text-muted">日常巡检与数据维护常用小工具。</p>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-default tool-box">
						<div class="panel-heading"><span class="panel-title">连通性检测</span></div>
						<div class="panel-body">
							<div class="form-group">
								<label>主机地址</label>
								<input type="text" class="form-control" id="pingHost" placeholder="如 127.0.0.1">
							</div>
							<button class="btn btn-primary" id="pingBtn">开始检测</button>
							<div class="result-area" id="pingResult"></div>
						</div>
					</div>
				</div>

				<div class="col-md-6">
					<div class="panel panel-default tool-box">
						<div class="panel-heading"><span class="panel-title">链接可用性巡检</span></div>
						<div class="panel-body">
							<div class="form-group">
								<label>目标链接</label>
								<input type="text" class="form-control" id="probeTarget" placeholder="如 http://example.com">
							</div>
							<button class="btn btn-primary" id="probeBtn">抓取内容</button>
							<div class="result-area" id="probeResult"></div>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-6">
					<div class="panel panel-default tool-box">
						<div class="panel-heading"><span class="panel-title">缓存对象还原</span></div>
						<div class="panel-body">
							<div class="form-group">
								<label>序列化串</label>
								<textarea class="form-control" id="restorePayload" rows="3" placeholder="Base64 编码内容"></textarea>
							</div>
							<button class="btn btn-primary" id="restoreBtn">还原对象</button>
							<div class="result-area" id="restoreResult"></div>
						</div>
					</div>
				</div>

				<div class="col-md-6">
					<div class="panel panel-default tool-box">
						<div class="panel-heading"><span class="panel-title">第三方数据同步</span></div>
						<div class="panel-body">
							<div class="form-group">
								<label>同步报文</label>
								<textarea class="form-control" id="syncRaw" rows="3" placeholder="JSON 报文"></textarea>
							</div>
							<button class="btn btn-primary" id="syncBtn">解析同步</button>
							<div class="result-area" id="syncResult"></div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			$(function() {
				$('#pingBtn').click(function() {
					$.ajax({
						url: '../diag/ping',
						type: 'get',
						data: { host: $('#pingHost').val() },
						success: function(data) { $('#pingResult').text(data); },
						error: function(xhr) { $('#pingResult').text(xhr.responseText); }
					});
				});

				$('#probeBtn').click(function() {
					$.ajax({
						url: '../link/probe',
						type: 'get',
						data: { target: $('#probeTarget').val() },
						success: function(data) { $('#probeResult').text(data); },
						error: function(xhr) { $('#probeResult').text(xhr.responseText); }
					});
				});

				$('#restoreBtn').click(function() {
					$.ajax({
						url: '../cache/restore',
						type: 'post',
						data: { payload: $('#restorePayload').val() },
						success: function(data) { $('#restoreResult').text(data); },
						error: function(xhr) { $('#restoreResult').text(xhr.responseText); }
					});
				});

				$('#syncBtn').click(function() {
					$.ajax({
						url: '../partner/sync',
						type: 'post',
						contentType: 'application/json',
						data: $('#syncRaw').val(),
						success: function(data) { $('#syncResult').text(data); },
						error: function(xhr) { $('#syncResult').text(xhr.responseText); }
					});
				});
			});
		</script>
	</body>

</html>
