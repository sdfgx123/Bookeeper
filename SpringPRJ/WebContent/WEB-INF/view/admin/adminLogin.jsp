<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
<style>
#DoAdminLogin {
	width: 100%;
}

#pw-feedback {
	width: 50%;
	margin: auto;
}

#container {
	margin: auto;
	width: 350px;
	height: 450px;
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

	<!-- 관리자 로그인 영역 시작 -->
	<br>
	<div id="container">
	<form id="loginForm">
	<br>
	아이디 입력
	<input type="text" name="id" id="id" placeholder="아이디 입력"
			class="single-input-primary">
	<br>
	<div id="pw-feedback" hidden=hidden>아이디와 암호를 모두 입력해 주십시오</div>
	<br>
	비밀번호 입력
	<input type="password" name="password" id="password" placeholder="비밀번호 입력"
			class="single-input-primary">
	<br>
	<button type="submit" id="DoAdminLogin" class="genric-btn primary radius">로그인</button>
	</form>
	<br>
	<a href="/user/userLogin.do" id="done" class="genric-btn danger radius">돌아가기</a>
	</div>
	<!-- 관리자 로그인 영역 끝 -->

	<!-- AJAX 로그인 처리 시작-->
	<script>
	$("#loginForm").submit(function(e){
		e.preventDefault();
		var id = $("#id");
		var pw = $("#password");
		if(!Boolean(id.val().trim()) || !Boolean(pw.val().trim())){
			$("#pw-feedback").removeAttr('hidden')
		}else{
			$("#pw-feedback").attr('hidden', 'hidden')
			$.ajax({
				data : {id : id.val(), pw : pw.val()},
				type : "POST",
				url : "DoAdminLogin.do",
				success : function(data) {
					if(data=="0"){
						location.href = "/admin/AdminMain.do";
					}else if(data=="1"){
						$("#pw-feedback").html('아이디 또는 암호가 올바르지 않습니다.');
						$("#pw-feedback").removeAttr('hidden')
					}else if(data=="2"){
						$("#pw-feedback").html('관리자 등급이 아닙니다. 사용자 로그인을 통해 로그인을 시도 하십시오.');
						$("#pw-feedback").removeAttr('hidden')
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
					$("#pw-feedback").html('서버 오류입니다.');
					$("#pw-feedback").removeAttr('hidden')
			      }
		})
		}
	})
	</script>
	<!-- AJAX 로그인 처리 끝 -->
	
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>