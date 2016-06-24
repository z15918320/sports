<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
<link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
<link href="style/authority/zTreeStyle.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/jquery/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="scripts/zTree/jquery.ztree.core-3.2.js"></script>
<script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
<script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"/></link>
<script type="text/javascript" src="scripts/artDialog/artDialog.js?skin=default"></script>
<title>ä¿¡æ¯ç®¡çç³»ç»</title> 
<script type="text/javascript">
	
		/** ----------------leftMenu zTreeé¨åå¼å§----------------  **/
		var zTree;
		var setting = {
			view : {
				dblClickExpand : false,
				showLine : true,
				selectedMulti : false,
				expandSpeed : ($.browser.msie && parseInt($.browser.version) <= 6) ? ""
						: "fast"
			},
			data : {
				key : {
					name : "weiduName"
				},
				simpleData : {
					enable : true,
					idKey : "weiduID",
					pIdKey : "parentID",
					rootPId : ""
				}
			},
			callback : {
				beforeClick : beforeClick,
				onClick : onClick
			}
		};

		var zNodes = [{"checked":false,"crospID":0,"delFlag":1,"parentID":0,"weiduCode":"FANGYUANGUANLI","weiduGrade":0,"weiduID":5,"weiduName":"æ¿æºç®¡ç","weiduOrder":2,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":5,"weiduCode":"KANGJUXINCHENG","weiduGrade":1,"weiduID":6,"weiduName":"çæ¯æ²³ç","weiduOrder":1,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":5,"weiduCode":"YULANXIAOQU","weiduGrade":1,"weiduID":77,"weiduName":"èèå°åº","weiduOrder":2,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":5,"weiduCode":"HESHENGYUAN","weiduGrade":1,"weiduID":83,"weiduName":"åçå­å°åº","weiduOrder":3,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":83,"weiduCode":" HESHENGYUAN1HAOLOU","weiduGrade":2,"weiduID":84,"weiduName":" åçå­å°åº112å·æ¥¼","weiduOrder":1,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":6,"weiduCode":"KANGJUXINCHENG500HAO1DONG","weiduGrade":2,"weiduID":16,"weiduName":"çæ¯æ²³ç16å·æ¥¼","weiduOrder":1,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":77,"weiduCode":"YULANXIAOQU2HAOLOU","weiduGrade":2,"weiduID":78,"weiduName":"èèå°åº4å·æ¥¼","weiduOrder":1,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":77,"weiduCode":"YULANXIAOQU5HAOLOU","weiduGrade":2,"weiduID":79,"weiduName":"èèå°åº5å·æ¥¼","weiduOrder":2,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":6,"weiduCode":"KANGJUXINCHENG500HAO2DONG","weiduGrade":2,"weiduID":17,"weiduName":"çæ¯æ²³ç17å·æ¥¼","weiduOrder":2,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":83,"weiduCode":" HESHENGYUAN2HAOLOU","weiduGrade":2,"weiduID":85,"weiduName":" åçå­å°åº113å·æ¥¼","weiduOrder":2,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":6,"weiduCode":"KANGJUXINCHENG500HAO3DONG","weiduGrade":2,"weiduID":69,"weiduName":"çæ¯æ²³ç18å·æ¥¼","weiduOrder":3,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":77,"weiduCode":"YULANXIAOQU7HAOLOU","weiduGrade":2,"weiduID":80,"weiduName":"èèå°åº7å·æ¥¼","weiduOrder":3,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":77,"weiduCode":"YULANXIAOQU8HAOLOU","weiduGrade":2,"weiduID":81,"weiduName":"èèå°åº8å·æ¥¼","weiduOrder":4,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":6,"weiduCode":"RUIJINGHEPAN20HAOLOU","weiduGrade":2,"weiduID":72,"weiduName":"çæ¯æ²³ç20å·æ¥¼","weiduOrder":4,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":6,"weiduCode":"RUIJINGHEPAN22HAOLOU","weiduGrade":2,"weiduID":73,"weiduName":"çæ¯æ²³ç22å·æ¥¼","weiduOrder":5,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":6,"weiduCode":"RUIJINGHEPAN23HAOLOU","weiduGrade":2,"weiduID":74,"weiduName":"çæ¯æ²³ç23å·æ¥¼","weiduOrder":6,"weiduType":"FANGYUANGUANLI"},{"checked":false,"crospID":0,"delFlag":1,"parentID":6,"weiduCode":"RUIJINGHEPAN24HAOLOU","weiduGrade":2,"weiduID":75,"weiduName":"çæ¯æ²³ç24å·æ¥¼","weiduOrder":7,"weiduType":"FANGYUANGUANLI"}];
		$(document).ready(function() {
			$.fn.zTree.init($("#tree"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("tree");
			// é»è®¤å±å¼ææèç¹
			zTree.expandAll(true);
		});

		function beforeClick(treeId, treeNode) {
			var check = (treeNode && treeNode.isParent && treeNode.weiduGrade != 2);
			if (check) {
				art.dialog({icon:'error', title:'åææç¤º', drag:false, resize:false, content:'åªè½éæ©å°åºæ å·', ok:true,});
				return false;
			}
			return true;
		}

		/** å·¦é®åå» **/
		function onClick(e, treeId, treeNode) {
			var fyXqCode = treeNode.getParentNode().weiduID;
			var fyDhCode = treeNode.weiduID;
			$("#submitForm").attr("action","/xngzf/archives/showLoupanChart.action?fyXqCode="+ fyXqCode + "&fyDhCode=" + fyDhCode).submit();
		}
		/** ----------------leftMenu  zTreeé¨åå¼å§----------------  **/
		
		
</script>
	
	
<script type="text/javascript">
	$(document).ready(function(){
        $(".fy_table_td").fancybox({
            'width' : 900,
            'height' : 650,
            'type' : 'iframe',
            'hideOnOverlayClick' : false,
        });
        
        var lists = $('.unit-the-table tr').eq(0);
        $.each(lists, function(){
			var tds = $(this).find('td[unitname]');
			var names = {};
			$.each(tds, function(){
				var unitname = $(this).attr('unitname');
				names[unitname] = names[unitname] ? names[unitname] + 1 : 1;
			});
			var html = '<tr><th width="40" class="fang_th">åå</th>';
			$.each(names, function(i, n){
				html += '<th width="40" colspan="'+n+'" class="fang_th">'+i+'åå</th>';
			});
			$('#unit-thead').html(html);
        });
	});
</script>
<style type="text/css">
	#sider{
		position: absolute;
		top: 0;
		left: 25px;
		bottom: 0px;
		width: 260px;
		border: 1px solid #DEDFDF;
	}
	
	#main{
		position: absolute;
		top: 0;
		left: 285px;
		right: 0px;
		bottom: 0px;
		border: 1px solid #DEDFDF;
		overflow: auto;
	}
	#box_border {
		border: 1px solid #EDEDED;
		height: 50px;
		line-height: 50px;
		text-align: center;
	}
	#fang_type {
		list-style-type: none;
	}
	#fang_type li{
		width: 80px;
		height: 22px;
		line-height: 22px;
		color: #FFF;
		display: inline-block;
	}
	
	.fy_table_td{
		color: #fff;
	}
	
	.fang_th{
		background: #044599 no-repeat;
		text-align: center;
		border-left: 1px solid #02397F;
		border-right: 1px solid #02397F;
		border-bottom: 1px solid #02397F;
		border-top: 1px solid #02397F;
		letter-spacing: 2px;
		text-transform: uppercase;
		font-size: 14px;
		color: #fff;
		height: 37px;
	}
</style>	
</head>
<body>
	<form id="submitForm" name="submitForm" action="/xngzf/archives/showLoupanChart.action" method="post" enctype="multipart/form-data">
		<div id="container">
			<div id="sider">
				<ul id="tree" class="ztree"></ul>
			</div>
			<div id="main">
				<div id="box_border">
					<ul id="fang_type">
						<li style="width: 160px;"><span class="ui_txt_bold05">çæ¯æ²³ç16å·æ¥¼</span></li>
						<li style="background-color: #b51d1a;">å·²ç§èµ</li>
						<li style="background-color: #000EFF;">å·²éç§</li>
						<li style="background-color: #a5c438;">æ¬ è´¹</li>
						<li style="background-color: #7065D5;">è¾éå¾ç§</li>
						<li style="background-color: #917430;">å»ºæå¾ç§</li>
						<li style="background-color: #23b628;">å¨å»º</li>
						<li style="background-color: #7b8587;">å¶ä»</li>
					</ul>
				</div>
				<table class="unit-the-table table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
					<thead id="unit-thead"></thead>
					<tbody>
						 
						 	<tr>
								<td style="color:#1853A1;">1å±</td>
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458579642011" class="fy_table_td" style="color:#fff;">1-111</a>
										</td>
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458625716623" class="fy_table_td" style="color:#fff;">1-112</a>
										</td>
									
									
									
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458667159302" class="fy_table_td" style="color:#fff;">1-113</a>
										</td>
									
									
									
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458709870134" class="fy_table_td" style="color:#fff;">1-211</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458762850630" class="fy_table_td" style="color:#fff;">1-212</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458815023215" class="fy_table_td" style="color:#fff;">1-213</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458856106360" class="fy_table_td" style="color:#fff;">1-311</a>
										</td>
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458895470090" class="fy_table_td" style="color:#fff;">1-312</a>
										</td>
									
									
									
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458937394501" class="fy_table_td" style="color:#fff;">1-313</a>
										</td>
									
									
									
								
							</tr>
						 
						 	<tr>
								<td style="color:#1853A1;">2å±</td>
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458592537463" class="fy_table_td" style="color:#fff;">2-121</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458632171244" class="fy_table_td" style="color:#fff;">2-122</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458674204381" class="fy_table_td" style="color:#fff;">2-123</a>
										</td>
									
									
									
								
									
										<td width="40"  style="background-color: #b51d1a;" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458716035781" class="fy_table_td" style="color:#fff;" style="color:#fff;">2-221</a>
										</td>
									
									
									
									
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458769094855" class="fy_table_td" style="color:#fff;">2-222</a>
										</td>
									
									
									
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458821080777" class="fy_table_td" style="color:#fff;">2-223</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458862660086" class="fy_table_td" style="color:#fff;">2-321</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458901283258" class="fy_table_td" style="color:#fff;">2-322</a>
										</td>
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458943404274" class="fy_table_td" style="color:#fff;">2-323</a>
										</td>
									
									
									
									
									
									
								
							</tr>
						 
						 	<tr>
								<td style="color:#1853A1;">3å±</td>
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458599318263" class="fy_table_td" style="color:#fff;">3-131</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458638753194" class="fy_table_td" style="color:#fff;">3-132</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458682581758" class="fy_table_td" style="color:#fff;">3-133</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458722561604" class="fy_table_td" style="color:#fff;">3-231</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458775824339" class="fy_table_td" style="color:#fff;">3-232</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458827335266" class="fy_table_td" style="color:#fff;">3-233</a>
										</td>
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458868792698" class="fy_table_td" style="color:#fff;">3-331</a>
										</td>
									
									
									
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458908609294" class="fy_table_td" style="color:#fff;">3-332</a>
										</td>
									
									
									
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458949563827" class="fy_table_td" style="color:#fff;">3-333</a>
										</td>
									
									
									
								
							</tr>
						 
						 	<tr>
								<td style="color:#1853A1;">4å±</td>
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458606278350" class="fy_table_td" style="color:#fff;">4-141</a>
										</td>
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458644830000" class="fy_table_td" style="color:#fff;">4-142</a>
										</td>
									
									
									
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458689701887" class="fy_table_td" style="color:#fff;">4-143</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458729044449" class="fy_table_td" style="color:#fff;">4-241</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458787335535" class="fy_table_td" style="color:#fff;">4-242</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458833493856" class="fy_table_td" style="color:#fff;">4-243</a>
										</td>
									
									
									
								
									
										<td width="40"  style="background-color: #b51d1a;" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458875681904" class="fy_table_td" style="color:#fff;" style="color:#fff;">4-341</a>
										</td>
									
									
									
									
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458914747679" class="fy_table_td" style="color:#fff;">4-342</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458955609523" class="fy_table_td" style="color:#fff;">4-343</a>
										</td>
									
									
									
								
							</tr>
						 
						 	<tr>
								<td style="color:#1853A1;">5å±</td>
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458612828548" class="fy_table_td" style="color:#fff;">5-151</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458651021626" class="fy_table_td" style="color:#fff;">5-152</a>
										</td>
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458696987832" class="fy_table_td" style="color:#fff;">5-153</a>
										</td>
									
									
									
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458744575953" class="fy_table_td" style="color:#fff;">5-251</a>
										</td>
									
									
									
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458801011635" class="fy_table_td" style="color:#fff;">5-252</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458839607546" class="fy_table_td" style="color:#fff;">5-253</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458883493520" class="fy_table_td" style="color:#fff;">5-351</a>
										</td>
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458924838698" class="fy_table_td" style="color:#fff;">5-352</a>
										</td>
									
									
									
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458961673499" class="fy_table_td" style="color:#fff;">5-353</a>
										</td>
									
									
									
								
							</tr>
						 
						 	<tr>
								<td style="color:#1853A1;">6å±</td>
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458619251417" class="fy_table_td" style="color:#fff;">6-161</a>
										</td>
									
									
									
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458657101639" class="fy_table_td" style="color:#fff;">6-162</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='1'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458703663113" class="fy_table_td" style="color:#fff;">6-163</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458751752209" class="fy_table_td" style="color:#fff;">6-261</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458808590082" class="fy_table_td" style="color:#fff;">6-262</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='2'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458846045169" class="fy_table_td" style="color:#fff;">6-263</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458889588928" class="fy_table_td" style="color:#fff;">6-361</a>
										</td>
									
									
									
								
									
									
									
									
									
										<td width="40"  style="background-color: #917430" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458930982536" class="fy_table_td" style="color:#fff;">6-362</a>
										</td>
									
									
									
								
									
									
										<td width="40" style="background-color: #000EFF" unitname='3'>
										<a href="/xngzf/archives/fangyuanRentHistory.action?fyID=14458971566951" class="fy_table_td" style="color:#fff;">6-363</a>
										</td>
									
									
									
									
									
									
								
							</tr>
						 
					</tbody>
				</table>
			</div>
		</div>
	</form>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>