<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 로그인</title>
<style>
#login {
	width: 50%;
	margin: auto;
}

#login_small {
	width: 25%;
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
							<a href="/index.do"><img src="../assets/img/logo/logo.png"
								alt=""></a>
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
							<h1>Bookeeper 로그인</h1>
							<br>
							<form name="loginForm" method="post" action="/user/LoginTest" onsubmit="return validate();">
								<input type="text" name="id" id="login" placeholder="아이디 입력"
									class="single-input"> <br> <input type="text"
									name="password" id="login" placeholder="비밀번호 입력"
									class="single-input">
									<br>
									<button type="submit" id="login" class="genric-btn primary radius">로그인</button>
							</form>
							<br> <a href="#" id="login_small"
								class="genric-btn primary radius">아이디 찾기</a> <a
								href="/bootstrap.do" id="login_small"
								class="genric-btn primary radius">돌아가기</a> <br>
							<br> <a href="/user/userRegister.do" id="login"
								class="genric-btn primary radius">회원이 아니신가요? 회원가입 하기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 : 대형 끝 -->

	<!-- 로그인 형식 유효성 검사 시작 -->
	<script>
		function validate() {

			var valid = false;
			if (document.loginForm.id.value == "") {
				alert("아이디를 입력해주세요");
				return false;
			} else if (document.loginForm.password.value == "") {
				alert("암호를 입력해주세요");
				return false;
			}

			var query = {
				id : $("#id").val(),
				password : $("#password").val()
			};
			$.ajax({
				url : "/user/LoginTest.do",
				type : "post",
				data : query,
				success : function(data) {
					if (data == "0") {
						location.href = "/main.do";
					} else if (data == "1") {
						$("#errorMsg").html("아이디 또는 암호가 일치하지 않습니다.")
					} else if (data == "2") {
						$("#errorMsg").html("이메일 인증이 필요합니다.")
					} else {
						alert("해당 계정은 관리자에 의해 정지되었습니다.\n정지 사유 : " + data)
						$("#errorMsg").html("관리자에 의해 정지된 계정입니다.")
					}
				}
			});
			return valid;

		}
	</script>
	<!-- 로그인 형식 유효성 검사 끝 -->
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>