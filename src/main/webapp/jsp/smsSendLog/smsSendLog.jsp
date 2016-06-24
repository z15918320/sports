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

	/** 条件查询用户  **/
	function search(){
		$("#submitForm").attr("action", "${pageContext.request.contextPath}/SMSSendMsgLogActivities/toSmsSendLogList.do").submit();
	}
	
	/** 删除 **/
	function del(userId){
		// 非空判断
		if(userId == '') return;
		$("#allIDCheck").val(userId);
		$("#submitForm").attr("action", "${pageContext.request.contextPath}/SMSSendMsgLogActivities/reSendSMS.do").submit();			
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
			// 提交form
			$("#submitForm").attr("action", "${pageContext.request.contextPath}/SMSSendMsgLogActivities/reSendSMS.do").submit();
		}
	}

	/** 首页跳转 **/
	function jumpTopPage(){
		$("#pageNow").val(1);
		$("#submitForm").attr("action", "${pageContext.request.contextPath}/SMSSendMsgLogActivities/selectLastPage.do?isFirst="+1).submit();
	}
	
	/** 尾页跳转 **/
	function jumpEndPage(){
		 $("#pageNow").val($("#pageCount").val());
		$("#submitForm").attr("action", "${pageContext.request.contextPath}/SMSSendMsgLogActivities/selectNextPage.do?isEnd="+1).submit();
	}
	
	/** 下一页跳转 **/
	function jumpNextPage(){
		var NowPaget = $("#pageNow").val();
		var pageCountt = $("#pageCount").val();
		if(NowPaget-pageCountt==0){
			alert("当前已是最后一页");
		}else{
			$("#submitForm").attr("action", "${pageContext.request.contextPath}/SMSSendMsgLogActivities/selectNextPage.do").submit();
		}
		
	}
	
	/** 上一页跳转 **/
	function jumpLastPage(){
		var NowPage = $("#pageNow").val();
		if(NowPage==1){
			alert("当前已是首页");
		}else{
			$("#submitForm").attr("action", "${pageContext.request.contextPath}/SMSSendMsgLogActivities/selectLastPage.do").submit();
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
			$("#submitForm").attr("action", "${pageContext.request.contextPath}/SMSSendMsgLogActivities/jumpInputPage.do?pageCountt=" + pageNum).submit();
		}else{
			// “跳转页数”为空
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请输入合适的页数，\n自动为您跳到首页', ok:true,});
			jumpTopPage();
		}
	}
	$(document).ready(function(){
		/** 新增   **/
	    $("#addBtn").fancybox({
	    	'href'  : '${pageContext.request.contextPath}/jsp/smsSendLog/sendSMSPage.jsp',
	    	'width' : 733,
	        'height' : 530,
	        'type' : 'iframe',
	        'hideOnOverlayClick' : false,
	        'showCloseButton' : false,
	        'onClosed' : function() { 
	        	window.location.href = '${pageContext.request.contextPath}/SMSSendMsgLogActivities/toSmsSendLogList.do';
	        }
	    });
	});
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
							接收手机号码
							<input type="text" value="${selectSendNum }" id="sendNum" name="sendNum" class="ui_input_txt02" />
							发送内容
							<input type="text" value="${selectSendText }" id="sendText" name="sendText" class="ui_input_txt02" />
                                                                                   发送状态
							<select name="entityStatus" id="entityStatus" value="${selectSendStatus }" class="ui_select01">
                                <option value=" ">--请选择--</option>
                               <c:choose>
                                	<c:when test="${entityStatus==1 }">
                                		<option value="0">准备发送</option>
		                                <option selected="selected" value="1">发送成功</option>
		                                <option value="2">发送失败</option>
                                    </c:when>
                                    <c:when test="${entityStatus==0 }">
                                    	<option selected="selected" value="0">准备发送</option>
		                                <option value="1">发送成功</option>
                                		<option value="2">发送失败</option>
                                    </c:when>
                                    <c:when test="${entityStatus==2 }">
                                    	<option value="0">准备发送</option>
		                                <option value="1">发送成功</option>
                                		<option selected="selected" value="2">发送失败</option>
                                    </c:when>
                                    <c:otherwise>  
                                    	<option value="0">准备发送</option>
										<option value="1">发送成功</option>
		                                <option value="2">发送失败</option>
   									</c:otherwise> 
                                </c:choose>
                            </select>
                           	 发送时间(开始)
							<input type="date" value="${starTime }" id="starTime" name="starTime" class="ui_input_txt02" />
							发送创建时间(结束)
							<input type="date" value="${endTime }" id="endTime" name="endTime" class="ui_input_txt02" />
						</div>
						<div id="box_bottom">
							<input type="button" value="发送短信" class="ui_input_btn01" id="addBtn" />
							<input type="button" value="查询" class="ui_input_btn01" onclick="search();" /> 
							<input type="button" value="重发" class="ui_input_btn01" onclick="batchDel();" /> 
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
							<th>发送人</th>
							<th>接收手机号码</th>
							<th>发送内容</th>
							<th>发送时间</th>
							<th>状态</th>
							<th>发送失败原因</th>
							<th>操作</th>
						</tr>
						<c:forEach var="entityInfo" items="${infoList}">
							<tr>
								<td><input type="checkbox" name="IDCheck" value="${entityInfo.SMS_SEND_ID}" class="acb" /></td>
								<td><c:out value="${entityInfo.USER_NAME}" /></td>
								<td><c:out value="${entityInfo.SEND_MOBILE}" /></td>
								<td><c:out value="${entityInfo.SMS_SEND_CONTENT}" /></td>
								<td><c:out value="${entityInfo.sendDateStr}" /></td>
								<td>
								<c:choose>
                                	<c:when test="${entityInfo.SEND_STATUS==0 }">
		                              	 准备发送
                                    </c:when>
                                    <c:when test="${entityInfo.SEND_STATUS==1 }">
		                                                                                                  发送成功
                                    </c:when>
                                    <c:when test="${entityInfo.SEND_STATUS==2 }">
		                                                                                                  准备失败 
                                    </c:when>  
                                </c:choose>
								</td>
								<td><c:out value="${entityInfo.SEND_REMARK}" /></td>
								<td>
									<a href="javascript:del('${entityInfo.SMS_SEND_ID}');">重发</a>
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
