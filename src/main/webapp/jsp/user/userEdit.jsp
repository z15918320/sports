<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户编辑</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="../../..${pageContext.request.contextPath}/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="../../..${pageContext.request.contextPath}/style/authority/common_style.css" rel="stylesheet" type="text/css">
<link href="../../..${pageContext.request.contextPath}/plugs/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/plugs/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/scripts/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript">
	function initGender(){
		  //初始化紧急程度
		  var cyvalue = ${userInfo.GENDER};  
		  var EmergencyObj = document.getElementById("gender").options;
		  for (var i=0; i<EmergencyObj.length; i++) {
		            if (EmergencyObj[i].value == cyvalue) {
		                  EmergencyObj[i].selected = true;
		                  break;
		               }
		   }
		  
		}
	
	function initUserType(){
		  //初始化紧急程度
		  var cyvalue = ${userInfo.USER_TYPE};  
		  var EmergencyObj = document.getElementById("user_type").options;
		  for (var i=0; i<EmergencyObj.length; i++) {
		            if (EmergencyObj[i].value == cyvalue) {
		                  EmergencyObj[i].selected = true;
		                  break;
		               }
		         }
		}
	
	function initStatus(){
		  //初始化紧急程度
		  var cyvalue = ${userInfo.STATUS};  
		  var EmergencyObj = document.getElementById("status").options;
		  for (var i=0; i<EmergencyObj.length; i++) {
		            if (EmergencyObj[i].value == cyvalue) {
		                  EmergencyObj[i].selected = true;
		                  break;
		               }
		         }
		}

	$(document).ready(function() {
		initGender();
		initUserType();
		initStatus();
		/*
		 * 提交
		 */
		$("#submitbutton").click(function() {
			if(validateForm()){
				checkUserSubmit();
			}
		});
		
		/*
		 * 取消
		 */
		$("#cancelbutton").click(function() {
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		});
		
		var result = 'null';
		if(result =='success'){
			/**  关闭弹出iframe  **/
			window.parent.$.fancybox.close();
		}
	});
	
	/** 提交form  **/
	function checkUserSubmit(){
		// 分别获取用户各个字段
		var reqParam = {};
		reqParam.user_info_id = $('#user_info_id').val();
		reqParam.login_code = $('#login_code').val();
		reqParam.user_name = $('#user_name').val();
		reqParam.password = $('#password').val();
		reqParam.birthday = $("#birthday").val();
		reqParam.nick_name = $("#nick_name").val();
		reqParam.company_name = $("#company_name").val();
		reqParam.company_addr = $("#company_addr").val();
		reqParam.dept_name = $("#dept_name").val();
		reqParam.user_type = $("#user_type").val();
		reqParam.status = $("#status").val(); 
		reqParam.gender = $("#gender").val(); 
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/UserActivities/toUserSubmit.do",
			dataType : 'json',
			data : JSON.stringify(reqParam),
			beforeSend : function(xhr) {
				  xhr.setRequestHeader("Content-Type","application/json;charset=UTF-8");
			},
			success : function(data) {
				alert(JSON.stringify(data));
				window.parent.$.fancybox.close();
			},
			error : function(data) {
					alert("重复用户");
			}
		});

		return true;
	}
	
	/** 表单验证  **/
	function validateForm(){
		if($("#user_name").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'填写用户名称', ok:true,});
			return false;
		}else if($("#password").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'请填写用户密码', ok:true,});
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="${pageContext.request.contextPath}/UserActivities/toUserSubmit.do" method="post">
	
	<div id="container">
		<div id="nav_links">
			当前位置：用户管理&nbsp;>&nbsp;<span style="color: #1A5CC6;">用户编辑</span>
			<div id="page_close">
				<a href="javascript:parent.$.fancybox.close();">
					<img src="../../..${pageContext.request.contextPath}/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
			<input type="hidden" name="user_info_id" value="${userInfo.USER_INFO_ID }" id="user_info_id"/>
				<tr>
					<td class="ui_text_rt">用户名</td>
					<td class="ui_text_lt">
						<input type="text" id="login_code" name="login_code" value="${userInfo.LOGIN_CODE }"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">真实姓名</td>
					<td class="ui_text_lt">
						<input type="text" id="user_name" name="user_name" value="${userInfo.USER_NAME }"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">用户昵称</td>
					<td class="ui_text_lt">
						<input type="text" id="nick_name" name="nick_name" value="${userInfo.NICK_NAME }"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">密码</td>
					<td class="ui_text_lt">
						<input type="text" id="password" name="password" value="${userInfo.PASSWORD }"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">生日</td>
					<td class="ui_text_lt">
						<input type="date" id="birthday" name="birthday" value="${userInfo.birthdayStr }"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">性别</td>
					<td class="ui_text_lt">
						<select name="gender" id="gender" value="${userInfo.GENDER }" class="ui_select01">
                                <option value="1">女</option>
                                <option value="0">男</option>
                            </select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">公司名称</td>
					<td class="ui_text_lt">
						<input type="text" id="company_name" name="company_name"  value="${userInfo.COMPANY_NAME }" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">公司地址</td>
					<td class="ui_text_lt">
						<input type="text" id="company_addr" name="company_addr" value="${userInfo.COMPANY_ADDR }" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">部门名称</td>
					<td class="ui_text_lt">
						<input type="text" id="dept_name" name="dept_name" value="${userInfo.DEPT_NAME }" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">用户类型</td>
					<td class="ui_text_lt">
						<select name="user_type" id="user_type" value="${userInfo.USER_TYPE }" class="ui_select01">
                                <option value="1">普通用户</option>
                                <option value="2">管理员</option>
                            </select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">状态</td>
					<td class="ui_text_lt">
						<select name="status" id="status" value="${userInfo.STATUS }" class="ui_select01">
                                <option value="1">生效</option>
                                <option value="0">失效</option>
                            </select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input id="submitbutton" type="button" value="提交" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="取消" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</form>

<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>