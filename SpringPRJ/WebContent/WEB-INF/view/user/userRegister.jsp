<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookeeper 회원가입</title>
<style>
#wm {
	width: 50%;
	margin: auto;
}
</style>
<!-- header 영역  -->
<%@ include file="../header.jsp"%>
</head>
<body>
	<!-- 메인메뉴 영역 시작 -->
	<header>
		<div class="header-area header-transparent">
			<div class="main-header header-sticky">
				<div class="container-fluid">
					<div
						class="menu-wrapper d-flex align-items-center justify-content-between">

						<!-- Logo -->
						<div class="logo">
							<a href="/bootstrap.do"><img
								src="../assets/img/logo/logo.png" alt=""></a>
						</div>

						<!-- Main-menu -->
						<div class="main-menu f-right d-none d-lg-block">
							<nav>
								<ul id="navigation">
									<li><a href="index.html">내 서재</a></li>
									<li><a href="/user/userLogin.do">로그인</a></li>
									<li><a href="/notice/noticeList.do">공지사항</a></li>
								</ul>
							</nav>
						</div>

						<!-- Mobile Menu -->
						<div class="col-12">
							<div class="mobile_menu d-block d-lg-none"></div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- 메인메뉴 영역 끝 -->
	
	<!-- 히어로 영역 : 대형 시작 -->
	<div class="slider-area hero-bg1 hero-overly">
		<div
			class="single-slider hero-overly  slider-height1 d-flex align-items-center">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-xl-10 col-lg-10">
						<!-- Hero Caption -->
						<div class="hero__caption pt-100">
							<h1>Bookeeper 회원가입</h1>
							<br>
							<!-- 유저 회원가입 폼 시작 -->
							<form name="userLogin" action="/user/UserRegProc.do" method="post">
								<input type="text" pattern="^[가-힣]{1,}$" required data-pattern-error="한글만 입력" name="user_name" class="single-input" id="wm" maxlength="7" placeholder="이름 입력 : 한글만 입력 하십시오"  >
								<br>
								<input type="text" pattern="^[_A-z0-9]{4,}$" name="id" class="single-input" maxlength="20" data-pattern-error="영문 4글자 이상으로 입력 하십시오." id="wm" placeholder="아이디 입력" data-remote="/user/DupCheck.do" data-remote-error="이미 사용중인 아이디입니다" required>
								<br>
								<input type="text"
									name="password" id="wm" placeholder="이메일 입력"
									class="single-input">
								<br>
								<input type="text"
									name="password" id="wm" placeholder="전화번호 입력"
									class="single-input">
								<br>
								<input type="text"
									name="password" id="wm" placeholder="암호 입력"
									class="single-input">
								<br>
								<input type="text"
									name="password" id="wm" placeholder="암호 확인"
									class="single-input">
								<br>
								<button type="submit" id="wm" class="genric-btn primary radius">가입 신청</button>
							</form>
							<!-- 유저 회원가입 폼 끝 -->
							<br>
							<a href="/user/userLogin.do" id="login"
								class="genric-btn primary radius">돌아가기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 : 대형 끝 -->
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>