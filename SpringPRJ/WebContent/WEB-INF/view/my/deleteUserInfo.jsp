<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.deleteContainer {
	margin: auto;
	width: 350px;
	height: 500px;
	box-shadow: 1px 1px 3px 1px #dadce0;
	padding: 20px;
}

#done {
	width: 100%;
}
</style>
<meta charset="UTF-8">
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<title>회원탈퇴</title>
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
							<h2>회원탈퇴</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->
	<br>
	<br>
	<div class="deleteContainer">
	<p>회원 탈퇴 처리를 위해 아이디와 비밀번호를 입력해 주십시오.</p>
	<hr>
	<form name="deleteForm" action="/my/DoDeleteUserInfo.do" method="post">
	<p>아이디</p>
	<input type="text" name="id" placeholder="아이디 입력" class="single-input-primary">
	<br>
	<p>비밀번호</p>
	<input type="password" name="password" placeholder="비밀번호 입력" class="single-input-primary">
	<br>
	<button type="submit" id="done" class="genric-btn primary radius" onclick="ask();">탈퇴하기</button>
	</form>
	<br>
	<a href="/my/MyMain.do" id="done" class="genric-btn danger radius">취소</a>
	</div>
	
<!-- Confirm 함수로 회원 탈퇴시 예, 아니오 구현하려 했으나 어느 것을 클릭해도 예 실행 오류 -->
<script type="text/javascript">
function ask() {
	if (!confirm("정말 탈퇴 하시겠습니까?")) {
		return false;
	}
}
</script>
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>