<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%
	String content = nvl(request.getParameter("memo"));
	String isbn = nvl(request.getParameter("isbn"));
%>
<!DOCTYPE html>
<html>
<head>
<style>
	#container {
		width: 1000px;
		height: 500px;
		box-shadow: 1px 1px 3px 1px #dadce0;
		padding: 30px;
		margin: auto;
	}
</style>
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<meta charset="UTF-8">
<title>메모 수정</title>
<!-- header 영역 첨부 -->
<%@ include file="../header.jsp"%>
</head>
<body>
	<!-- 메인메뉴 영역 첨부 -->
	<%@ include file="../mainMenu.jsp" %>
	
	<!--히어로 영역  -->
	<div class="slider-area2">
		<div
			class="slider-height3  hero-overly hero-bg4 d-flex align-items-center">
			<div class="container">
				<div class="row">
					<div class="col-xl-12">
						<div class="hero-cap2 pt-20 text-center">
							<h2>메모 수정</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //히어로 영역 -->
	<br>
	
	<!-- 메모 수정 영역 -->
	<form action="/lib/DoEditMemoForm.do" method="post">
		<div id="container">
			<p>메모를 입력해 주세요.</p>
			<textarea name="memo" class="single-textarea" placeholder="<%=content %>"></textarea>
			<input type="text" id="test" name="isbn" hidden="hidden" value="<%=isbn %>">
		</div>
		<br>
		<div style="text-align: center;">
			<button type="submit" class="genric-btn warning" style="margin: auto; color: black;">완료</button>
		</div>
	</form>
	<!-- //메모 수정 영역 -->
	
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>