<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>navi</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0 user-scalable=no">
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap-theme.min.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/jQuery/jquery-3.0.0.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<style type="text/css">
	a:link {
		text-decoration: none;
	}
	a:visited {
		text-decoration: none;
	}
	a:hover {
		text-decoration: none;
	}
	a:active {
		text-decoration: none;
	}
</style>

</head>

<body>
	
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	   <div class="navbar-header">
	      <a class="navbar-brand" href="#" style="height: 70px;padding: 25px 23px;font-size: 24px;">yeren-cms内容管理系统</a>
	   </div>
	   <div style="padding: 10px 23px;font-size: 24px;">
	      <ul class="nav navbar-nav">
	         <li class="active"><a href="#">iOS</a></li>
	         <li><a href="#">SVN</a></li>
	         <li class="dropdown">
	            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	               Java <b class="caret"></b>
	            </a>
	            <ul class="dropdown-menu">
	               <li><a href="#">jmeter</a></li>
	               <li><a href="#">EJB</a></li>
	               <li><a href="#">Jasper Report</a></li>
	               <li class="divider"></li>
	               <li><a href="#">分离的链接</a></li>
	               <li class="divider"></li>
	               <li><a href="#">另一个分离的链接</a></li>
	            </ul>
	         </li>
	      </ul>
	   </div>
	</nav>
	
	
	<div style="width: 200px;margin-top: 70px;margin-left: 20px;display: inline-block;">
            <div class="panel-group" id="accordion2">
            <div class="panel-heading" style="text-align: center;">
                <strong style="font-size: 25px;">菜单分类</strong>
            </div>
                <div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse"
                        data-parent="#accordion2" href="#collapseOne" style="text-align: center;font-size: 20px;cursor:pointer;">
                        <a class="accordion-toggle" >Java</a>
                    </div>
                    <div id="collapseOne" class="panel-collapse in"
                        style="height: auto;">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#">Java基础</a></li>
                                <li><a href="#">Java面向对象</a></li>
                                <li><a href="#">Java核心API</a></li>
                                <li><a href="#">JavaEE</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default" >
                    <div class="panel-heading" data-toggle="collapse"
                        data-parent="#accordion2" href="#collapseTwo" style="text-align: center;font-size: 20px;cursor:pointer;">
                        <a class="accordion-toggle">数据库</a>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse"
                        style="height: 0px;">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#">SQL基础</a></li>
                                <li><a href="#">Oracle</a></li>
                                <li><a href="#">MySQL</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" data-toggle="collapse"
                        data-parent="#accordion2" href="#collapseThree" style="text-align: center;font-size: 20px;cursor:pointer;">
                        <a class="accordion-toggle">技术前沿</a>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse"
                        style="height: 0px;">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#">NoSQL</a></li>
                                <li><a href="#">Hadoop</a></li>
                                <li><a href="#">WebService</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div style="width: 200px;height:100px;background-color: red;display: inline-block;">22</div>
</body>
</html>
