<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String msg = CmmUtil.nvl((String)request.getAttribute("msg"));
String url = (String)request.getAttribute("url");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<script>
	window.onload = function() {
		var redirectUrl = '<%=url %>';
		var msg = '<%=msg%>' 
		if(msg !=""){
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