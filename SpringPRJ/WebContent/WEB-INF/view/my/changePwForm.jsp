<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String id2 = CmmUtil.nvl(request.getParameter("id"));
%>
<!DOCTYPE html>
<html>
<head>
<style>
	.myContainer {
	margin: auto;
	width: 370px;
	height: 460px;
	box-shadow: 1px 1px 3px 1px #dadce0;
	padding: 10px;
</style>
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<meta charset="UTF-8">
<title>비밀번호 변경</title>
<!-- header 영역 첨부 -->
<%@ include file="../header.jsp"%>
</head>
<body>
	<!-- 메인메뉴 영역 첨부 -->
	<%@ include file="../mainMenu.jsp"%>
	
	<!--히어로 영역 시작 -->
	<div class="slider-area2">
		<div
			class="slider-height3  hero-overly hero-bg4 d-flex align-items-center">
			<div class="container">
				<div class="row">
					<div class="col-xl-12">
						<div class="hero-cap2 pt-20 text-center">
							<h2>마이페이지</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //히어로 영역 -->
	<br>
	<br>
	<div class="myContainer">
		<form action="DoChangePwForm.do" method="post">
			<p>변경하실 비밀번호를 입력해 주십시오.</p>
			<hr>
			<input type="text" name="id" value="<%=id2 %>" hidden="hidden">
			<p>비밀번호 입력</p>
			<input type="password" placeholder="비밀번호 입력" name="pw" class="single-input-primary">
			<p>비밀번호 확인</p>
			<input type="password" placeholder="비밀번호 확인" data-match="pw" data-match-error="비밀번호가 일치하지 않습니다." class="single-input-primary">
			<br>
			<button type="submit" style="width: 100%;" class="genric-btn primary radius">제출</button>
		</form>
		<br>
		<a href="/my/MyMain.do" class="genric-btn primary radius" style="width: 100%;">취소</a>
	</div>
	
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>