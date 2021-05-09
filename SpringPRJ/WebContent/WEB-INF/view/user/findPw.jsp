<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bookeeper 비밀번호 찾기</title>
<style>
	#button {
	width: 50%;
	margin: auto;
}

	#id {
	width: 50%;
	margin: auto;
	}
</style>
<!-- header 영역  -->
<%@ include file="../header.jsp"%>
</head>
<body>
<!-- 히어로 영역 : 대형 시작 -->
	<div class="slider-area hero-bg1 hero-overly">
		<div
			class="single-slider hero-overly  slider-height1 d-flex align-items-center">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-xl-10 col-lg-10">

						<!-- 폼 영역 시작 -->
						<div class="hero__caption pt-100">
							<h1>Bookeeper 비밀번호 찾기</h1>
							<form id="findIdForm" action="/user/FindPwProc.do" method="post">
								<br>
								<p>아이디를 입력해 주세요</p>
								<input type="text" name="email" id="id" placeholder="아이디 입력" class="single-input">
								<br>
								<button type="submit" id="button" class="genric-btn primary radius">비밀번호 초기화</button>
							</form>
								<br>
								<a href="/user/userLogin.do" id="button" class="genric-btn primary radius">돌아가기</a>
								<br><br>
								<a href="/user/userRegister.do" id="button" class="genric-btn primary radius">회원이 아니신가요? 회원가입 하기</a>
						</div>
						<!-- 폼 영역 끝 -->

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