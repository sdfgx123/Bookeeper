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
								<input type="text" name="email" class="single-input" id="wm" maxlength="20" placeholder="이메일 입력" pattern="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$" data-remote="/user/DupCheck.do" data-remote-error="이미 사용중인 이메일입니다" required data-error="유효한 이메일 주소가 아닙니다">
								<br>
								<input type="text" name="user_tel" class="single-input" id="wm" placeholder="전화번호 입력" pattern="[^1-9][0-9]{1,2}-[0-9]{3,4}-[0-9]{4}" data-pattern-error="올바른 전화번호가 아닙니다." >
								<br>
								<input type="password" name="password" id="wm" placeholder="암호 입력" class="single-input" required data-error="필수 입력사항 입니다. 암호를 입력해 주십시오.">
								<br>
								<input type="password" name="password" id="wm" placeholder="암호 재입력" data-match="#password" data-match-error="암호가 일치하지 않습니다." class="single-input" data-error=" " required>
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
	
	<script>
	// 전화번호 형식 자동변환
	function phoneNumberFormat(obj) {
		obj.value = obj.value.replace(/[^0-9\-]/g, "");
			var number = obj.value.replace(/[^0-9]/g, "");
			var tel = "";
			
			// 서울 지역번호 02 들어오는 경우
			if(number.substring(0, 2).indexOf('02') == 0) {
				$("#user_tel").attr("maxlength", "12")
				if(number.length < 3) {
					return number;
				} else if(number.length < 6) {
					tel += number.substr(0,2);
					tel += "-";
		            tel += number.substr(2);
				} else if(number.length < 10) {
					tel += number.substr(0, 2);
		            tel += "-";
		            tel += number.substr(2, 3);
		            tel += "-";
		            tel += number.substr(5);
				} else {
					tel += number.substr(0, 2);
		            tel += "-";
		            tel += number.substr(2, 4);
		            tel += "-";
		            tel += number.substr(6);
				}
				
			// 서울 지역번호 02가 아닌 경우
			} else {
				$("#user_tel").attr("maxlength", "13")
				if(number.length < 4) {
					return number;
				} else if(number.length < 7) {
					tel += number.substr(0, 3);
		            tel += "-";
		            tel += number.substr(3);
				} else if(number.length < 11) {
					tel += number.substr(0, 3);
		            tel += "-";
		            tel += number.substr(3, 3);
		            tel += "-";
		            tel += number.substr(6);
				} else {
					tel += number.substr(0, 3);
		            tel += "-";
		            tel += number.substr(3, 4);
		            tel += "-";
		            tel += number.substr(7);
				}
			}
			
			obj.value = tel;
			$(obj).focusout();
	}
	
	</script>
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>