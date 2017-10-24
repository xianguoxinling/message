<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>烛照小程序管理系统 | 预定查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon" />
<link href="/css/store.css" rel="stylesheet" type="text/css" />
<link href="/css/layout.css" rel="stylesheet" type="text/css" />
<link href="/css/jquery.validation.css" rel="stylesheet" type="text/css" />
<link href="/css/ie_style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/bootstrap.css" media="screen"
	type="text/css" />
<link rel="stylesheet" href="/css/normalize.css" type="text/css" />
</head>

<body>
	<div id="main">
		<div id="header"></div>
		<div id="content" style="padding-bottom: 0px;">
			<div id="header2"></div>
		</div>

		<div class="clear"></div>

		<table class="cart_product">
			<tr class="bg">
				
				<th class="name">预定人</th>
				<th class="name">手机号</th>
				<th class="name">到店人数</th>
				<th class="name">到店项目</th>
				<th class="name" style="text-align: center">预定到店时间</th>
				<th class="name" style="text-align: center">留言</th>
				<th class="name">状态</th>
				<th class="edit2" style="text-align: center">操作</th>
			</tr>

			<c:forEach items="${bespeakList}" var="bespeak">
				<tr>
					<td class="name"><c:out value="${bespeak.name}" /></td>
					<td class="name"><c:out value="${bespeak.phone}" /></td>
					<td class="name"><c:out value="${bespeak.number}" /></td>
					<td class="name"><c:out value="${bespeak.content}" /></td>
					<td class="name"><c:out value="${bespeak.bespeak_time}" /></td>
					<td class="name" style="width:260px"><c:out value="${bespeak.message}" /></td>

					<td class="name">
						<div class="status">
							<span> <c:choose>
									<c:when test="${bespeak.state == 'undeal'}">新预约</c:when>
									<c:when test="${bespeak.state == 'tostore'}">已如期到店</c:when>
									<c:when test="${bespeak.state == 'noshop'}">未如期到店</c:when>
									<c:otherwise>状态异常</c:otherwise>
								</c:choose>
							</span>
						</div>
					</td>

					<td class="edit2" style="padding: 0 8px;"><c:choose>
							<c:when test="${bespeak.state == 'undeal'}">
								<button class="btn btn-primary btn-sm"
									onclick="location.href='/message/bespeak/tostore.action?id=${bespeak.id}';">已如期到店</button>
								<button class="btn btn-primary btn-sm" style="margin-top: 8px"
									onclick="location.href='/message/bespeak/noshop.action?id=${bespeak.id}';">未如期到店</button>
							</c:when>
						</c:choose></td>
				</tr>
			</c:forEach>

		</table>

		<div class="paging">
			<ul class="pager">
				<li id="preLi"><a id="pre" href="#">上一页</a></li>
				<li><a id="next"
					href="/manager/order/queryall.action?begin=2&num=20">下一页</a></li>
			</ul>
		</div>

		<div id="foot"></div>
	</div>

	<script type="text/javascript" src="/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript" src="/js/layui/layui.js"></script>
	<script type="text/javascript" src="/js/bootstrap.js"></script>
	<script type="text/javascript" src="/js/cookies.js"></script>
	<script type="text/javascript" src="/js/move-top.js"></script>
	<script type="text/javascript" src="/js/easing.js"></script>
	<script type="text/javascript" src="/js/sellerOrders.js"></script>

	<script type="text/javascript">
    $(function () {
    	$('#header').load('/load/header.html');
    	$('#header2').load('/load/header2.html');
    	$('#foot').load('/load/foot.html');
    });

    $(function () {
    	var begin = parseInt(GetQueryString("begin"));
    	var num = '20';
    	var url = '';
    	<%String s2 = (String) request.getAttribute("magiczz");%>
    	var s = '<%=s2%>';
			if (s == 'all') {
				url = '/message/bespeak/queryall.action';
			}
			if (s == 'new') {
				url = '/message/bespeak/querynew.action';
			}
			if (s == 'tostore') {
				url = '/message/bespeak/querytostore.action';
			}
			if (s == 'noshop') {
				url = '/message/bespeak/querynoshop.action';
			}

			if (begin && begin > 1) {
				$("#pre").attr('href',
						url + '?begin=' + (begin - 1) + '&num=20');
				$("#next").attr('href',
						url + '?begin=' + (begin + 1) + '&num=20');
			} else {
				$("#preLi").addClass("disabled");
				$("#pre").attr('href', 'javascript:void(0);');
				$("#next").attr('href', url + '?begin=2&num=20');
			}
		});

		function GetQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return (r[2]);
			return null;
		}
	</script>

	<script type="text/javascript"
		src="http://kefu.puckart.com/mibew/js/compiled/chat_popup.js"></script>
	<script type="text/javascript" src="/js/kf.js"></script>

</body>
</html>
