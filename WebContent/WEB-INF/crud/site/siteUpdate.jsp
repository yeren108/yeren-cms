<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>update</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0 user-scalable=no">
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/jQuery/jquery-3.0.0.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>

<body>
		<jsp:include page="../../crud/navi.jsp" flush="true" />
		<form class="form-horizontal" action="site/update" method="post">
			<div class="form-group" style="margin:4px;width:100%;">
				<label style="width: 100px;text-align: right;display: inline-block;">ID：</label>
				<div style="display: inline-block;">
					<input type="text" class="form-control"
						name="id" value="${param.id}" readonly="readonly">
				</div>
			</div>
			<div class="form-group" style="margin:4px;width:100%;">
				<label style="width: 100px;text-align: right;display: inline-block;">站点名称：</label>
				<div style="display: inline-block;">
					<input type="text" class="form-control" name="name"
						placeholder="站点名称" value="${param.name}">
				</div>
			</div>
			<div class="form-group" style="margin:4px;width:100%;">
				<label style="width: 100px;text-align: right;display: inline-block;">排序：</label>
				<div style="display: inline-block;">
					<input type="number" class="form-control" name="sort"
						placeholder="排序" value="${param.sort}">
				</div>
			</div>
			<div class="form-group" style="margin:4px;width:100%;">
				<label style="width: 100px;text-align: right;display: inline-block;">状态：</label>
				<div style="display: inline-block;">
					<input type="text" class="form-control" name="status"
						placeholder="状态" value="${param.status}" readonly="readonly">
				</div>
			</div>
			<div class="form-group" style="margin:4px;width:100%;">
				<label style="width: 100px;text-align: right;display: inline-block;">创建时间：</label>
				<div style="display: inline-block;">
					<input type="text" class="form-control" name="createTime"
						placeholder="创建时间" value="${param.createTime}" readonly="readonly">
				</div>
			</div>
			<div class="form-group" style="margin:4px;margin-left:48px;width:100%;">
				<div>
					<button type="submit" class="btn btn-default">提交</button>
					<button onclick="history.go(-1)" class="btn btn-default">取消</button>
				</div>
			</div>
		</form>


</body>
</html>


