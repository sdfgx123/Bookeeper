<%@page import="poly.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	UserDTO uDTO = (UserDTO) request.getAttribute("uDTO");
%>
<!DOCTYPE html>
<html>
<head>
<style>
.myContainer {
	margin: auto;
	width: 300px;
	height: 650px;
	box-shadow: 1px 1px 3px 1px #dadce0;
}

#profile {
	width: 150px;
	height: 250px;
	margin-left: 65px;
	object-fit: contain;
}

#margin-setting {
	margin-left: 10px;
}

#button {
	margin-left: 30px;
}
</style>
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<meta charset="UTF-8">
<title>마이페이지</title>
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
	<!-- 히어로 영역 끝 -->
	<br>
	<br>
	<br>
	<div class="myContainer">
		<img id="profile" src="/assets/img/profile.PNG">
		<ul class="unordered-list" id="margin-setting">
			<li>이름 : <%=uDTO.getUser_name() %></li>
			<li>아이디 : <%=uDTO.getId() %></li>
			<li>이메일 : <%=uDTO.getEmail() %></li>
			<li>전화번호 : <%=uDTO.getUser_tel() %></li>
			<li>가입일 : <%=uDTO.getRegdate() %></li>
		</ul>
		<br>
		<a href="/my/UserEdit.do?user_seq=<%=uDTO.getUser_seq() %>" id="button" class="genric-btn primary radius">수정</a>
		<a href="#" id="button" class="genric-btn danger radius">탈퇴</a>
		
	</div>

</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>