<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#admin-button {
	width: 50%;
	height: 50px;
}
</style>
<!-- session 첨부 -->
<%@ include file="../session.jsp" %>
<meta charset="UTF-8">
<title>Bookeeper 관리자 메인페이지</title>
<!-- header 영역 첨부 -->
<%@ include file="../header.jsp"%>
</head>
<body>
	<!-- 메인메뉴 영역 첨부 -->
	<%@ include file="../mainMenu.jsp"%>
	
	<!-- 히어로 영역 : 대형 시작 -->
	<div class="slider-area hero-bg1 hero-overly">
		<div
			class="single-slider hero-overly  slider-height1 d-flex align-items-center">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-xl-10 col-lg-10">
						<div class="hero__caption pt-100">
							<h1>Bookeeper 관리자 메인페이지</h1>
							<br>
							<a href="/user/userRegister.do" id="admin-button" class="genric-btn primary radius">회원 리스트</a>
							<br><br>
							<a href="/user/userRegister.do" id="admin-button" class="genric-btn primary radius">공지사항 관리</a>
							<br><br>
							<a href="/user/logout.do" id="admin-button" class="genric-btn primary radius">로그아웃</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 : 대형 끝 -->

</body>
	<!-- footer 영역 첨부 -->
	<%@ include file="../footer.jsp"%>
</html>