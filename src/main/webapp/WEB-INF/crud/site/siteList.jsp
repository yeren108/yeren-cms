<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>站点列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.min.css"
	rel="stylesheet">

<script src="${pageContext.request.contextPath}/static/jQuery/jquery-3.0.0.min.js"></script>
<script src="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/jQuery/confirm.js"></script>
<style type="text/css">
	.important{
		background-color: red;
	}
</style>
</head>


<body>
	<jsp:include page="../../crud/navi.jsp" flush="true" />
	<div id="container" class="container">
			
			<table class="table table-bordered table-striped table-hover table-responsive">
				<caption >
					<div style="padding: 0px 0px 0px 0px;">
					   <form class="bs-example bs-example-form" role="form">
					      <div class="row" style="text-align: right;margin-left:20%;width: 80%;display: inline-block;">
					         <div style="display: inline-block;">
					            <div class="input-group">
					               <input type="text" id="attribute"  placeholder="站点名称" class="form-control">
					               <span class="input-group-btn">
					                  <button class="btn btn-primary" type="button" onclick="find();">
					                                                                 确定
					                  </button>
					                  <button class="btn btn-default" type="button" onclick="clean();">
					                                                                 清除
					                  </button>
					                  <button class="btn btn-primary" type="button" onclick="add();" style="display: inline-block;margin-left: 90px;">新增</button>
					               </span>
					            </div>
					         </div>
					      </div>
					   </form>
					</div>
				</caption>
				<thead>
					<tr>
						<th width="3%" style="text-align: center;">ID</th><!-- 不变 -->
						<th width="24%" style="text-align: center;">站点名称</th>
						<th width="5%" style="text-align: center;">排序</th><!-- 不变 -->
						<th width="5%" style="text-align: center;">line</th><!-- 不变 -->
						<th width="25%" style="text-align: center;">更新时间</th>
						<th width="38%" style="text-align: center;">操作</th>
					</tr>
				</thead>
				<tbody id="tbody"  style="font-size: xx-small;">
					
				</tbody>
			</table>
	</div>
	<div id="pagination" align="center">
		
	</div>
</body>
</html>

<script type="text/javascript">
	
	//全局变量
	var numbers=7;//每页多少条数据
	var pageNow=sessionStorage.getItem("sitePage");
	
////////////////////////////////////////////////////////////site特有的-start////////////////////////////////////////////////////////////
	//初始化页面
	$(function(){
		if(pageNow==""||pageNow=="null"||pageNow==null){
			loadData(1,numbers);
		}else{
			loadData(pageNow,numbers);
		}
    });

	//加载数据
	function loadData(page,rows) {
		$("#tbody").html("");//清除之前的数据
		$("#pagination").html("");//清除之前的数据
		$.ajax({
			url : "${pageContext.request.contextPath}/site/listByPage?page="+page+"&rows="+rows,
			type : "GET",
			data : {},
			dataType: "json",	
			success : function(result) {
				for (var i=0;i<result.jsonArray.length;i++){
					show(result.jsonArray[i].id,result.jsonArray[i].name,result.jsonArray[i].sort,result.jsonArray[i].status,result.jsonArray[i].createTime,result.jsonArray[i].updateTime);
				}
				pagination(result.sum,numbers);
			},
			error : function() {
				Confirm.show('提示', '超时,请重试或刷新页面!');
			}
		});
	}
	
	//展示的格式
	function show(id,name,sort,status,createTime,updateTime){
		var tbody = 
					"<tr  style='text-align: center;'>"+
						"<td>"+id+"</td>"+
						"<td><a href='site/update_jsp?id="+id+"&name="+name+"&sort="+sort+"&status="+status+"&createTime="+createTime+"'>"+name+"</a></td>"+
						"<td>"+sort+"</td>"+
						"<td>"+status+"</td>"+
						"<td>"+updateTime+"</td>"+
						"<td>"+
							"<div class='btn-group' role='group' aria-label='...'>"+
						  		"<button type='button' class='btn btn-default' onclick='up("+id+");'>上移</button>"+
						  		"<button type='button' class='btn btn-default' onclick='down("+id+");'>下移</button>"+
						  		"<button type='button' class='btn btn-default' onclick='statusChange("+id+");'>上线/下线</button>"+
						  		"<button type='button' class='btn btn-default' onclick='isDelete("+id+");'>删除</button>"+
							"</div>"+
						"</td>"+
						
					"</tr>";
					
		$("#tbody").append(tbody);
	}
	
	//分页-导航-跳转
	function stepIn(){
		var read=$("#sr").val();
		if(read==""){
			
		}else{
			whichPage(read);
		}
		
		
	}
	
	
	//删除
	function isDelete(id) {
		Confirm.show('提示消息', '您是否要删除这条数据？', {
	           'Delete': {
	               'primary': true,
	               'callback': function() {
	                  $.ajax({
						url : "${pageContext.request.contextPath}/site/delete/"+id+"",
						type : "GET",
						data : {},
						dataType: "json",	
						success : function(result) {
							if(result.success){
								Confirm.show('提示', '你删除了'+result.message+'条信息');
								loadData(1,numbers);
							}else{
								Confirm.show('提示', '你删除了'+result.message+'条信息,站点下尚有栏目存在，删除失败！');
								loadData(1,numbers);
							}
						},
						error : function() {
							Confirm.show('提示', '超时,请重试或刷新页面!');
						}
	   				  });
	                  Confirm.hide();
	               }
	           }
	   });
	}
	
	//清空搜索条件
	function clean(){
		$("#attribute")[0].value="";
	}
	
	//新增
	function add(){
		window.location.href = "site/add_jsp";
	}
	
	//模糊查找
	function find() {
		  $("#tbody").html("");//清除之前的数据
          $.ajax({
			url : "${pageContext.request.contextPath}/site/find/"+$("#attribute")[0].value,
			type : "GET",
			data : {},
			dataType: "json",	
			success : function(result) {
				for (var i=0;i<result.length;i++){
					show(result[i].id,result[i].name,result[i].sort,result[i].status,result[i].createTime,result[i].updateTime);
				}
			},
			error : function() {
				Confirm.show('提示', '超时,请重试或刷新页面!');
			}
		});
	}
	
	//上移
	function up(id){
		window.location.href = "${pageContext.request.contextPath}/site/up/"+id+"";
	}
	
	//下移
	function down(id){
		window.location.href = "${pageContext.request.contextPath}/site/down/"+id+"";
	}
	
	//上线下线
	function statusChange(id){
		window.location.href = "${pageContext.request.contextPath}/site/changeStatus/"+id+"";
	}
	
////////////////////////////////////////////////////////////site特有的-end////////////////////////////////////////////////////////////
	
	//分页导航
	function pagination(sum,num){
		var paginationFirst=
		"<nav>"+
		  "<ul class='pagination'>"+
		    "<li>"+
		      "<a id='XXX' href='javascript:whichPage("+((sessionStorage.getItem("sitePage")-1)>0?(sessionStorage.getItem("sitePage")-1):1)+");' aria-label='Previous'>"+
		        "<span aria-hidden='true'>&laquo;上一页</span>"+
		      "</a>"+
		    "</li>";
		
	    var choose=
			"<li id='UUU'><a >>></a></li>"+
		    "<li><a style='padding: 3px'><input id='sr' type='number' min='1' max='1000' value='' style='height: 26px;width: 50px;padding: 0px;'><input id='tz' type='button' value='跳转' onclick='stepIn();'></a></li>"+
		    "<li><a >>></a></li>";
		    
		var pages=
			"<li><a href='javascript:whichPage("+(Math.ceil(sum/num))+");' >"+(Math.ceil(sum/num))+"</a></li>";
			
		var paginationLast=
			"<li>"+
		      "<a href='javascript:whichPage("+((sessionStorage.getItem("sitePage")-(-1))<(Math.ceil(sum/num))?(sessionStorage.getItem("sitePage")-(-1)):(Math.ceil(sum/num)))+");' aria-label='Next'>"+
		        "<span aria-hidden='true'>下一页&raquo;</span>"+
		      "</a>"+
		    "</li>"+
		  "</ul>"+
		"</nav>";
		
		var min=   
			"<li><a href='javascript:whichPage(1);'>1</a></li>";
		
		var lister="";
		
	    if(sum/num<=3){
	    	lister=min;
	    }else if(sum/num<=8){
	    	for(var i=1;i<=sum/num;i++){
	    		if(i==sessionStorage.getItem("sitePage")){
	    			lister+="<li><a style='background-color: #DFF4FA' href='javascript:whichPage("+i+");'>"+i+"</a></li>";
	    		}else{
	    			lister+="<li><a href='javascript:whichPage("+i+");'>"+i+"</a></li>";
	    		}
	    	}
	    }else{
	    	for(var i=1;i<=8;i++){
	    		if(i==sessionStorage.getItem("sitePage")){
	    			lister+="<li><a style='background-color: #DFF4FA' href='javascript:whichPage("+i+");'>"+i+"</a></li>";
	    		}else{
	    			lister+="<li><a href='javascript:whichPage("+i+");'>"+i+"</a></li>";
	    		}
	    	}
	    }
		
		var pagination=paginationFirst+lister+choose+pages+paginationLast;
		
		$("#pagination").append(pagination);
	}
	
	
	//分页导航
	function whichPage(i){
		sessionStorage.removeItem("sitePage");
		loadData(i,numbers);
		sessionStorage.setItem("sitePage", i);
		
	}
	
</script>



