<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = CmmUtil.nvl((String) session.getAttribute("id"));
	String user_state = CmmUtil.nvl((String) session.getAttribute("user_state"));
	String user_type = CmmUtil.nvl((String) session.getAttribute("user_type"));

	String res = CmmUtil.nvl((String) request.getAttribute("res"));
	String url = CmmUtil.nvl((String) request.getAttribute("url"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
<%
	String msg = "";

	if (res.equals("1")) {
		msg = id + " 님이 로그인 하였습니다.";
	} else if (res.equals("0")) {
		msg = "아이디 또는 비밀번호가 일치하지 않습니다.";
	} else {
		msg = "시스템에 문제가 발생 하였습니다. 잠시 후 다시 시도해 주십시오.";
	}
%>
</head>
<body>
	<script>
	window.onload = function() {
		var redirectUrl = '<%=url%>';
		var msg = '<%=msg%>
		'
			if (msg != "") {
				alert(msg);
			}
			location.href = redirectUrl;
			/*
			var redirectPage = redirectUrl.split('?')[0];
			if(redirectUrl.split('?').length > 1){
				formData = new FormData();
				var queries = redirectUrl.split('?')[1].split('&');
				for(var i=0;i<queries.length;i++){
				    queries[i] = queries[i].split('=');
				    formData.append(queries[i][0], queries[i][1]);
				}
				var request = new XMLHttpRequest();
				request.open("POST", redirectPage);
				request.send(formData);
			
			}else{
				
			}
			
			 */
		};
	</script>
</body>
</html>