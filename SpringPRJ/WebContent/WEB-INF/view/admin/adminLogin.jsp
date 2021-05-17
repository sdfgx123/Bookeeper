<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#container {
	margin: auto;
	width: 300px;
	height: 650px;
	box-shadow: 1px 1px 3px 1px #dadce0;
	padding: 20px;
}

#done {
	width: 100%;
}
</style>
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<meta charset="UTF-8">
<title>Bookeeper 관리자 로그인</title>
<!-- header 영역  -->
<%@ include file="../header.jsp"%>
</head>
<body>
	<!-- 메인메뉴 영역  첨부 -->
	<%@ include file="../mainMenu.jsp"%>

	<!--히어로 영역 시작 -->
	<div class="slider-area2">
		<div
			class="slider-height3  hero-overly hero-bg4 d-flex align-items-center">
			<div class="container">
				<div class="row">
					<div class="col-xl-12">
						<div class="hero-cap2 pt-20 text-center">
							<h2>Bookeeper 관리자 로그인</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->

	<br>
	<div id="container">
	<form name="adminLogin" action="/admin/DoAdminLogin" method="post">
	<br>
	아이디 입력
	<input type="text" name="id" placeholder="아이디 입력"
			class="single-input-primary">
	<br>
	비밀번호 입력
	<input type="password" name="pw" placeholder="비밀번호 입력"
			class="single-input-primary">
	<br>
	<button type="submit" id="done" class="genric-btn primary radius">로그인</button>
	</form>
	<br>
	<a href="/my/UserLogin.do" id="done" class="genric-btn danger radius">돌아가기</a>
	</div>

</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>