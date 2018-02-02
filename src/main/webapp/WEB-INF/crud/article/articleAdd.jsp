<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>新增文章</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0 user-scalable=no">
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/jQuery/jquery-3.0.0.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
</head>

<body>
	<jsp:include page="../../crud/navi.jsp" flush="true" />
	<form class="form-horizontal" action="article/add" method="post">
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;">栏目：</label>
			<label style="width: 196px;">
				<select id="select" class="form-control" name="categoryId">
      			</select>
			</label>
		</div>
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;display: inline-block;">文章：</label>
			<div style="display: inline-block;">
				<input type="text" class="form-control" name="name" placeholder="文章" value="${param.name}">
			</div>
		</div>
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;display: inline-block;">内容：</label>
			<div style="display: inline-block;">
				<input type="text" class="form-control" name="data" placeholder="内容" value="${param.data}">
			</div>
		</div>
		
		<%-- 
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;display: inline-block;">内容2：</label>
			<textarea rows="4" cols="30" name="data2" value="${param.data2}"></textarea>
		</div>
		 --%>
		
		
		<div class="form-group" style="margin:4px;width:100%;">
			<label style="width: 100px;text-align: right;display: inline-block;">链接地址：</label>
			<div style="display: inline-block;">
				<input type="text" class="form-control" name="url" placeholder="链接地址" value="${param.url}">
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


