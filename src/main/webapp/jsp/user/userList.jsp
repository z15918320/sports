<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/scripts/jquery/jquery-1.7.1.js"></script>
<link href="../../..${pageContext.request.contextPath}/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="../../..${pageContext.request.contextPath}/style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="../../..${pageContext.request.contextPath}/style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/scripts/artDialog/artDialog.js?skin=default"></script>
<title>活动列表</title>

<script type="text/javascript">
	$(document).ready(function(){
		/** 新增   **/
	    $("#addBtn").fancybox({
	    	'href'  : '${pageContext.request.contextPath}/UserActivities/toUserEdit.do',
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = '${pageContext.request.contextPath}/UserActivities/toUserList.do';
	        }
	    });
		
	    /**编辑   **/
	    $("a.edit").fancybox({
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = '${pageContext.request.contextPath}/UserActivities/toUserList.do';
	        }
	    });
	});
	/** 用户角色   **/
	var userRole = '';

	/** 条件查询用户  **/
	function search(){
		$("#submitForm").attr("action", "${pageContext.request.contextPath}/UserActivities/toUserList.do").submit();
	}
	
	/**导出*/
	function exp(){
		$("#submitForm").attr("action", "${pageContext.request.contextPath}/UserActivities/toExpExcel.do").submit();
	}
	 
	/** 修改状态 **/
	function del(userId){
		// 非空判断
		if(userId == '') return;
		if(confirm("您确定要修改状态吗？")){
			$("#allIDCheck").val(userId);
			$("#submitForm").attr("action", "${pageContext.request.contextPath}/UserActivities/userUnactive.do").submit();			
		}
	}
	
	/** 批量操作**/
	function batchDel(){
		if($("input[name='IDCheck']:checked").size()<=0){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'至少选择一条', ok:true,});
			return;
		}
		// 1）取出用户选中的checkbox放入字符串传给后台,form提交
		var allIDCheck = "";
		$("input[name='IDCheck']:checked").each(function(index, domEle){
			allIDCheck += $(domEle).val() + ",";
		});
		// 截掉最后一个","
		if(allIDCheck.length>0) {
			allIDCheck = allIDCheck.substring(0, allIDCheck.length-1);
			// 赋给隐藏域
			$("#allIDCheck").val(allIDCheck);
			if(confirm("您确定要批量修改这些记录状态吗？")){
				// 提交form
				$("#submitForm").attr("action", "${pageContext.request.contextPath}/UserActivities/userUnactive.do").submit();
			}
		}
	}

	/** 首页跳转 **/
	function jumpTopPage(){
		$("#pageNow").val(1);
		$("#submitForm").attr("action", "${pageContext.request.contextPath}/UserActivities/selectLastPage.do?isFirst="+1).submit();
	}
	
	/** 尾页跳转 **/
	function jumpEndPage(){
		 $("#pageNow").val($("#pageCount").val());
		$("#submitForm").attr("action", "${pageContext.request.contextPath}/UserActivities/selectNextPage.do?isEnd="+1).submit();
	}
	
	/** 下一页跳转 **/
	function jumpNextPage(){
		var NowPaget = $("#pageNow").val();
		var pageCountt = $("#pageCount").val();
		if(NowPaget-pageCountt==0){
			alert("当前已是最后一页");
		}else{
			$("#submitForm").attr("action", "${pageContext.request.contextPath}/UserActivities/selectNextPage.do").submit();
		}
		
	}
	
	/** 上一页跳转 **/
	function jumpLastPage(){
		var NowPage = $("#pageNow").val();
		if(NowPage==1){
			alert("当前已是首页");
		}else{
			$("#submitForm").attr("action", "${pageContext.request.contextPath}/UserActivities/selectLastPage.do").submit();
		}
		
	}
	
	/** 输入页跳转 **/
	function jumpInputPage(totalPage){
		// 如果“跳转页数”不为空
		if($("#jumpNumTxt").val() != ''){
			var pageNum = parseInt($("#jumpNumTxt").val());
			// 如果跳转页数在不合理范围内，则置为1
			if(pageNum<1 | pageNum>totalPage){
				art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请输入合适的页数，\n自动为您跳到首页', ok:true,});
				pageNum = 1;
			}
			$("#submitForm").attr("action", "${pageContext.request.contextPath}/UserActivities/jumpInputPage.do?pageCountt=" + pageNum).submit();
		}else{
			// “跳转页数”为空
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请输入合适的页数，\n自动为您跳到首页', ok:true,});
			jumpTopPage();
		}
	}
</script>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<form id="submitForm" name="submitForm" action="" method="post">
		<input type="hidden" name="allIDCheck" value="" id="allIDCheck"/>
		<input type="hidden" name="pageNow" value="${pageNow}" id="pageNow"/>
		<input type="hidden" name="pageCount" value="${pageCount}" id="pageCount"/>
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center" align="center">
							登录名
							<input type="text" value="${selectLoginName }" id="login_name" name="login_name" class="ui_input_txt02" />
							真实姓名
							<input type="text" value="${selectUserName }" id="user_name" name="user_name" class="ui_input_txt02" />
							用户昵称
							<input type="text" value="${selectNickName }" id="nickname" name="nickname" class="ui_input_txt02" />
							公司名称
							<input type="text" value="${selectCompanyName }" id="company_name" name="company_name" class="ui_input_txt02" />
                                                                            状态
							<select name="entityStatus" id="entityStatus" value="${entityStatus }" class="ui_select01">
                                <option value=" ">--请选择--</option>
                               <c:choose>
                                	<c:when test="${entityStatus==1 }">
		                                <option selected="selected" value="1">生效</option>
		                                <option value="0">失效</option>
                                    </c:when>
                                    <c:when test="${entityStatus==0 }">
		                                <option value="1">生效</option>
                                		<option selected="selected" value="0">失效</option>
                                    </c:when>
                                    <c:otherwise>  
										<option value="1">生效</option>
                                		<option value="0">失效</option>
   									</c:otherwise> 
                                </c:choose>
                            </select>
						</div>
						<div id="box_center" align="center">
						创建时间(开始)
							<input type="date" value="${starTime }" id="starTime" name="starTime" class="ui_input_txt02" />
							创建时间(结束)
							<input type="date" value="${endTime }" id="endTime" name="endTime" class="ui_input_txt02" />
						用户类型
						<select name="identity_key" id="identity_key" value="${identity_key }" class="ui_select01">
                        <option value=" ">--请选择--</option>
                               <c:choose>
                                	<c:when test="${identity_key==1 }">
		                                <option selected="selected" value="1">普通用户</option>
		                                <option value="2">管理员</option>
                                    </c:when>
                                    <c:when test="${identity_key==2 }">
		                                <option value="1">普通用户</option>
                                		<option selected="selected" value="2">管理员</option>
                                    </c:when>
                                    <c:otherwise>  
										<option value="1">普通用户</option>
                                		<option value="2">管理员</option>
   									</c:otherwise> 
                                </c:choose>
                            </select>
						</div>
						<div id="box_bottom">
							<input type="button" value="导出" class="ui_input_btn01" onclick="exp();" /> 
							<input type="button" value="查询" class="ui_input_btn01" onclick="search();" /> 
							<input type="button" value="新增" class="ui_input_btn01" id="addBtn" /> 
							<input type="button" value="改状态" class="ui_input_btn01" onclick="batchDel();" /> 
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" onclick="selectOrClearAllCheckbox(this);" />
							</th>
							<th>登录名</th>
							<th>用户昵称</th>
							<th>真实姓名</th>
							<th>用户类型</th>
							<th>生日</th>
							<th>性别</th>
							<th>公司名称</th>
							<th>公司地址</th>
							<th>部门名称</th>
							<th>最后登录时间</th>
							<th>上次登录时间</th>
							<th>创建时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
						<c:forEach var="entityInfo" items="${infoList}">
							<tr>
								<td><input type="checkbox" name="IDCheck" value="${entityInfo.USER_INFO_ID}" class="acb" /></td>
								<td><c:out value="${entityInfo.LOGIN_CODE}" /></td>
								<td><c:out value="${entityInfo.NICK_NAME}" /></td>
								<td><c:out value="${entityInfo.USER_NAME}" /></td>
								<td>
								<c:choose>
                                	<c:when test="${entityInfo.USER_TYPE==1 }">
		                              	 普通用户
                                    </c:when>
                                    <c:when test="${entityInfo.USER_TYPE==2 }">
		                                                                                               管理员
                                    </c:when> 
                                </c:choose>
								</td>
								<td><c:out value="${entityInfo.birthdayStr}" /></td>
								<td>
								<c:choose>
                                	<c:when test="${entityInfo.GENDER==0 }">
		                              	 男
                                    </c:when>
                                    <c:when test="${entityInfo.GENDER==1 }">
		                                                                                               女
                                    </c:when> 
                                </c:choose>
								</td>
								<td><c:out value="${entityInfo.COMPANY_NAME}" /></td>
								<td><c:out value="${entityInfo.COMPANY_ADDR}" /></td>
								<td><c:out value="${entityInfo.DEPT_NAME}" /></td>
								<td><c:out value="${entityInfo.lastLoginTimeStr}" /></td>
								<td><c:out value="${entityInfo.preLoginTimeStr}" /></td>
								<td><c:out value="${entityInfo.createTimeStr}" /></td>
								<td>
								<c:choose>
                                	<c:when test="${entityInfo.STATUS==1 }">
		                              	 生效
                                    </c:when>
                                    <c:when test="${entityInfo.STATUS==0 }">
		                                                                                                  失效
                                    </c:when> 
                                </c:choose>
								</td>
								<td>
									<a href="${pageContext.request.contextPath}/UserActivities/toUserEdit.do?userid=${entityInfo.USER_INFO_ID}" class="edit">编辑</a> | 
									<a href="javascript:del('${entityInfo.USER_INFO_ID}');">改状态</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div class="ui_tb_h30">
					<div class="ui_flt" style="height: 30px; line-height: 30px;">
						共有
						<span class="ui_txt_bold04">${custCount}</span>
						条记录，当前第
						<span class="ui_txt_bold04">${pageNow}
						/${pageCount}</span>
						页
					</div>
					<div class="ui_frt">
						<!--    如果是第一页，则只显示下一页、尾页 -->
						
							<input type="button" value="首页" class="ui_input_btn01 " onclick="jumpTopPage();"/>
							<input type="button" value="上一页" class="ui_input_btn01" onclick="jumpLastPage();" />
							<input type="button" value="下一页" class="ui_input_btn01" onclick="jumpNextPage();" />
							<input type="button" value="尾页" class="ui_input_btn01" onclick="jumpEndPage();" />
						
						
						<!--     如果是最后一页，则只显示首页、上一页 -->
						转到第<input type="text" id="jumpNumTxt" class="ui_input_txt01" value="${pageNow}" />页
							 <input type="button" class="ui_input_btn01" value="跳转" onclick="jumpInputPage(${pageCount});" />
					</div>
				</div>
			</div>
		</div>
	</form>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
