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
.editContainer {
	margin: auto;
	width: 350px;
	height: 700px;
	box-shadow: 1px 1px 3px 1px #dadce0;
	padding: 20px;
}

#done {
	width: 100%;
}

#undo {
	width: 100%;
}
</style>
<meta charset="UTF-8">
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<title>회원정보 수정</title>
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
							<h2>회원정보 수정</h2>
							<p style="color: white;">변경하실 정보를 입력해 주세요.</p>
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
	<div class="editContainer">
		<form name="editForm" action="/my/DoUserEdit.do" method="post">
		<p>이름</p>
		<input type="text" value=<%=CmmUtil.nvl(uDTO.getUser_name()) %> name="user_name" placeholder="이름 입력" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Primary color'" required="" class="single-input-primary">
		<br>
		<p>이메일</p>
		<input type="text" value=<%=CmmUtil.nvl(uDTO.getEmail()) %> name="email" placeholder="이메일 입력" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Primary color'" required="" class="single-input-primary" readonly>
		<br>
		<p>아이디</p>
		<input type="text" value=<%=CmmUtil.nvl(uDTO.getId()) %> name="id" placeholder="이메일 입력" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Primary color'" required="" class="single-input-primary" readonly>
		<br>
		<p>전화번호</p>
		<input type="text" value=<%=CmmUtil.nvl(uDTO.getUser_tel()) %> name="user_tel" placeholder="이메일 입력" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Primary color'" required="" class="single-input-primary">
		<br>
		<p>가입일</p>
		<input type="text" value=<%=CmmUtil.nvl(uDTO.getRegdate()) %> name="regdate" placeholder="이메일 입력" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Primary color'" required="" class="single-input-primary" readonly>
		<br>
		<button type="submit" id="done" class="genric-btn primary radius">수정하기</button>
		</form>
		<br>
		<a href="/my/MyMain.do" id="undo" class="genric-btn danger radius">취소</a>
	</div>
	
	
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>