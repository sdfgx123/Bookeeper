<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String user_seq = CmmUtil.nvl(String.valueOf(session.getAttribute("user_seq")));
	String user_type = CmmUtil.nvl((String) session.getAttribute("user_type"));
	String id = CmmUtil.nvl((String) session.getAttribute("id"));
%>

<!--

<script>
alert("세션이 만료 되었습니다.");
location.href = "/index.do";
</script>

-->
