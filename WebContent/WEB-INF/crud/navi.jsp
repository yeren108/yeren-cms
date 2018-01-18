<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<nav class="navbar navbar-default navbar-top" role="navigation">
   <div class="navbar-header">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/crud/site/siteList.jsp" style="height: 70px;padding: 25px 23px;font-size: 24px;">yeren-cms-V1.0</a>
      <a class="navbar-brand" style="width: 0px;height:70px;background-color: #FFFFF;padding: 3px;"></a>
   </div>
   <div style="padding: 10px 23px;font-size: 24px;">
      <ul id="WWW" class="nav navbar-nav">
         <li id="btn1" ><a href="${pageContext.request.contextPath}/site/list_jsp">站点</a></li>
         <li id="btn2"><a href="${pageContext.request.contextPath}/category/list_jsp">栏目</a></li>
         <li id="btn3"><a href="${pageContext.request.contextPath}/article/list_jsp">标题</a></li>
         <li id="btn4"><a href="${pageContext.request.contextPath}/link/list_jsp">链接</a></li>
         <li id="btn5" class="dropdown">
            <a href="${pageContext.request.contextPath}/common/404" class="dropdown-toggle" data-toggle="dropdown">
               	备用<b class="caret"></b>
            </a>
            <ul class="dropdown-menu">
               <li><a href="${pageContext.request.contextPath}/common/404">子备用1</a></li>
               <li><a href="${pageContext.request.contextPath}/common/404">子备用2</a></li>
               <li><a href="${pageContext.request.contextPath}/common/404">子备用3</a></li>
               <li class="divider"></li>
               <li><a href="${pageContext.request.contextPath}/common/404">子备用4</a></li>
               <li class="divider"></li>
               <li><a href="${pageContext.request.contextPath}/common/404">子备用5</a></li>
            </ul>
         </li>
         <li id="btn6"><a href="${pageContext.request.contextPath}/common/404">其他</a></li>
      </ul>
   </div>
</nav>

<!--  -->
<script type="text/javascript">


	$(function(){
		if(sessionStorage.getItem("clickThis")=="btn1"){
			var d=$("#btn1").addClass("active");
		}
		if(sessionStorage.getItem("clickThis")=="btn2"){
			var d=$("#btn2").addClass("active");
		}
		if(sessionStorage.getItem("clickThis")=="btn3"){
			var d=$("#btn3").addClass("active");
		}
		if(sessionStorage.getItem("clickThis")=="btn4"){
			var d=$("#btn4").addClass("active");
		}
		if(sessionStorage.getItem("clickThis")=="btn5"){
			var d=$("#btn5").addClass("active");
		}
		if(sessionStorage.getItem("clickThis")=="btn6"){
			var d=$("#btn6").addClass("active");
		}
	});

	$("#btn1").click(function(){
		sessionStorage.removeItem("clickThis");
		sessionStorage.setItem("clickThis", "btn1");
		var c=$(".active").removeClass("active");
		
	});
	$("#btn2").click(function(){
		sessionStorage.removeItem("clickThis");
		sessionStorage.setItem("clickThis", "btn2");
		var c=$(".active").removeClass("active");
		
	});
	$("#btn3").click(function(){
		sessionStorage.removeItem("clickThis");
		sessionStorage.setItem("clickThis", "btn3");
		var c=$(".active").removeClass("active");
		
	});
	$("#btn4").click(function(){
		sessionStorage.removeItem("clickThis");
		sessionStorage.setItem("clickThis", "btn4");
		var c=$(".active").removeClass("active");
		
	});
	$("#btn5").click(function(){
		sessionStorage.removeItem("clickThis");
		sessionStorage.setItem("clickThis", "btn5");
		var c=$(".active").removeClass("active");
		
	});
	$("#btn6").click(function(){
		sessionStorage.removeItem("clickThis");
		sessionStorage.setItem("clickThis", "btn6");
		var c=$(".active").removeClass("active");
		
	});
</script>	
	