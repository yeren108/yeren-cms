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
	<jsp:include page="/crud/navi.jsp" flush="true" />
	<form class="form-horizontal" action="article/update" method="post">
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;display: inline-block;">ID：</label>
			<div style="display: inline-block;">
				<input type="text" class="form-control" name="id" value="${param.articleId}" readonly="readonly">
			</div>
		</div>
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;display: inline-block;">栏目：</label>
			<label style="width: 196px;">
      			<select id="select" class="form-control" name="categoryId">
					<option value="${param.categoryId}">${param.categoryName}</option>
	      		</select>
			</label>
		</div>
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;display: inline-block;">标题：</label>
			<div style="display: inline-block;">
				<input type="text" class="form-control" name="name" placeholder="标题" value="${param.articleName}">
			</div>
		</div>
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;display: inline-block;">内容：</label>
			<div style="display: inline-block;">
				<input type="text" class="form-control" name="data" placeholder="内容" value="${param.articleData}">
			</div>
		</div>
		
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;display: inline-block;">链接地址：</label>
			<div style="display: inline-block;">
				<input type="text" class="form-control" name="url" placeholder="链接地址" value="${param.linkUrl}">
			</div>
		</div>
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;display: inline-block;">排序：</label>
			<div style="display: inline-block;">
				<input type="number" class="form-control" name="sort" placeholder="排序" value="${param.articleSort}">
			</div>
		</div>
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;display: inline-block;">状态：</label>
			<div style="display: inline-block;">
				<input type="text" class="form-control" name="status" placeholder="状态" value="${param.articleStatus}" readonly="readonly">
			</div>
		</div>
		<div class="form-group" style="margin:4px;width:100%;">
				<label style="width: 100px;text-align: right;display: inline-block;">创建时间：</label>
				<div style="display: inline-block;">
					<input type="text" class="form-control" name="createTime"
						placeholder="创建时间" value="${param.articleCreateTime}" readonly="readonly">
				</div>
		</div>
		<div class="form-group" style="margin:4px;margin-left:48px;width:100%;">
			<div>
				<button type="submit" class="btn btn-default">修改</button>
				<button onclick="history.go(-1)" class="btn btn-default">取消</button>
			</div>
		</div>
	</form>
</body>
</html>
<script type="text/javascript">
	
	$("#select").one('click',function() {
		
		/* $("#select option").html(""), */
		$.ajax({
			type:"get",
			url:"category/list",
			success: function(data){
					
				for(var i=0;i<data.length;i++){
					$("#select").append(new Option(data[i].name,data[i].id));
				}
			},
			fail:function(data){
				alert("fail");
			} 
		})
	})
	
	
</script>


