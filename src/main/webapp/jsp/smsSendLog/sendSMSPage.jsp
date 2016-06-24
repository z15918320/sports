<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page language="java" import="java.util.*"%>
<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>短信发送</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="../../..${pageContext.request.contextPath}/style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="../../..${pageContext.request.contextPath}/style/authority/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/scripts/authority/commonAll.js"></script>
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="../../..${pageContext.request.contextPath}/scripts/artDialog/artDialog.js?skin=default"></script>
<script type="text/javascript">

	$(document).ready(function() {
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
		reqParam.phoneNum = $('#phoneNum').val();
		reqParam.sendContext = $('#sendContext').val(); 
		$.ajax({
			type:"POST",
			url:"${pageContext.request.contextPath}/toSMSSendSubmit.do",
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
					alert("发送失败");
			}
		});

		return true;
	}
	
	/** 表单验证  **/
	function validateForm(){
		if($("#phoneNum").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'手机号码不能为空', ok:true,});
			return false;
		}else if($("#sendContext").val()==""){
			art.dialog({icon:'error', title:'友情提示', drag:false, resize:false, content:'要发送的内容不能为空', ok:true,});
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<form id="submitForm" name="submitForm" action="" method="post">
	
	<div id="container">
		<div id="nav_links">
			当前位置：短信日志&nbsp;>&nbsp;<span style="color: #1A5CC6;">短信发送</span>
			<div id="page_close">
				<a href="javascript:parent.$.fancybox.close();">
					<img src="../../..${pageContext.request.contextPath}/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table  cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt">接收手机号码</td>
					<td class="ui_text_lt">
						<input type="text" id="phoneNum" name="phoneNum" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt">发送内容</td>
					<td class="ui_text_lt">
						&nbsp;&nbsp;<textarea name="sendContext" id="sendContext" rows="10"></textarea>
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