<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String code = (String) request.getAttribute("code");
%>
<!DOCTYPE html>
<html>
<!-- header 영역  -->
<%@ include file="../header.jsp"%>
<head>
<meta charset="UTF-8">
<title>비밀번호 초기화</title>
</head>
<body>
	<!-- 메인메뉴 영역 시작, 메인메뉴 부분은 없앰 -->
	<header>
		<div class="header-area header-transparent">
			<div class="main-header header-sticky">
				<div class="container-fluid">
					<div
						class="menu-wrapper d-flex align-items-center justify-content-between">

						<!-- 로고 영역 -->
						<div class="logo">
							<a href="/index.do"><img src="../assets/img/logo/logo.png"
								alt=""></a>
						</div>

						<div class="main-menu f-right d-none d-lg-block"></div>

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

	<!--히어로 영역 시작 -->
	<div class="slider-area2">
		<div
			class="slider-height3  hero-overly hero-bg4 d-flex align-items-center">
			<div class="container">
				<div class="row">
					<div class="col-xl-12">
						<div class="hero-cap2 pt-20 text-center">
							<h2>Bookeeper 비밀번호 초기화</h2>
							<hr>
							<p id="msg">새 비밀번호를 입력해 주세요.</p>
							<form name="recoverForm" action="/user/RecoverPwFormProc.do"
								method="post">
								<br> <input type="password" id="password" name="password"
									placeholder="새 비밀번호 입력" class="single-input"> <br>
								<input type="password" class="single-input" id="verify_password"
									placeholder="비밀번호 확인" data-match="#password" data-error=" "
									data-match-error="암호가 일치하지 않습니다" required> <br>
								<input name="code" value="<%=code %>" hidden="hidden">
								<button type="submit" class="genric-btn primary radius">비밀번호
									초기화</button>
								<br>
							</form>
							<a href="/user/userLogin.do" id="button"
									class="genric-btn primary radius">돌아기기</a> <br>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->
	<script type="text/javascript">
		$("#password").on('keyup', function() {
			$("#verify_password").focusout();
		})
	</script>
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>