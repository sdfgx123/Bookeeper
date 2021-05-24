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
	width: 500px;
	height: 500px;
	box-shadow: 1px 1px 3px 1px #dadce0;
	padding: 20px;
}
#delete-button {
		color: blue;
		text-decoration: underline;
	}
</style>
<!-- session 첨부 -->
<%@ include file="../session.jsp" %>
<meta charset="UTF-8">
<title>회원 정보 상세</title>
<!-- header 영역 첨부 -->
<%@ include file="../header.jsp"%>
<!-- Confirm 함수로 회원 탈퇴시 예, 아니오 구현하려 했으나 어느 것을 클릭해도 예 실행 오류 -->
<script type="text/javascript">
function ask() {
	if (!confirm("정말 탈퇴 하시겠습니까?")) {
		return false;
	}
}
</script>
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
							<h2>회원 정보 상세</h2>
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
		<ul class="unordered-list" id="margin-setting">
			<li>이름 : <%=uDTO.getUser_name() %></li>
			<li>아이디 : <%=uDTO.getId() %></li>
			<li>이메일 : <%=uDTO.getEmail() %></li>
			<li>전화번호 : <%=uDTO.getUser_tel() %></li>
			<li>가입일 : <%=uDTO.getRegdate() %></li>
		</ul>
		<br>
		<a id="delete-button" href="/admin/DeleteUser.do?seq=<%=uDTO.getUser_seq() %>" class="visit" onclick="ask();">회원 삭제</a>
	</div>
	
	
</body>
<!-- footer 영역 첨부 -->
<%@ include file="../footer.jsp"%>
</html>