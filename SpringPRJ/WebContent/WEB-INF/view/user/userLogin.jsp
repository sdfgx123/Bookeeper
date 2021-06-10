<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- session 첨부 -->
<%@ include file="../session.jsp" %>
<meta charset="UTF-8">
<title>유저 로그인</title>
<style>
#admin-login {
	color: white;
}
#pw-feedback {
	width: 50%;
	margin: auto;
	color: white;
}

#doLogin {
	width: 50%;
	margin: auto;
}

#id {
	width: 50%;
	margin: auto;
}

#password {
	width: 50%;
	margin: auto;
}

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
	<!-- 메인메뉴 영역  첨부 -->
	<%@ include file="../mainMenu.jsp"%>

	<!-- 히어로 영역 : 대형 시작 -->
	<div class="slider-area hero-bg1 hero-overly">
		<div
			class="single-slider hero-overly  slider-height1 d-flex align-items-center">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-xl-10 col-lg-10">

						<!-- 로그인 영역 시작 -->
						<div class="hero__caption pt-100">
							<h1>Bookeeper 로그인</h1>
							<form id="loginForm">
								<br>
								<input type="text" name="id" id="id" placeholder="아이디 입력" class="single-input">
								<div id="pw-feedback" hidden=hidden style="color: white; text-align: center;">아이디 또는 비밀번호가 일치하지 않습니다.</div>
								<br>
								<input type="password" name="password" id="password" placeholder="비밀번호 입력" class="single-input">
								<br>
								<button type="submit" id="doLogin" class="genric-btn primary radius">로그인</button>
							</form>
								<br>
								<a href="/user/FindID.do" id="login_small" class="genric-btn primary radius">아이디 찾기</a>
								<a href="/index.do" id="login_small" class="genric-btn primary radius">돌아가기</a>
								<br><br>
								<a href="/user/userRegister.do" id="login" class="genric-btn primary radius">회원이 아니신가요? 회원가입 하기</a>
								<br><br>
								<a href="/admin/AdminLogin.do" id="admin-login">관리자 로그인</a>
						</div>
						<!-- //로그인 영역 -->

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 : 대형 끝 -->
	
	<!-- 로그인 처리 시작-->
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
				url : "doLogin.do",
				success : function(data) {
					if(data=="0"){
						location.href = "/index.do";
					}else if(data=="1"){
						$("#pw-feedback").html('아이디 또는 암호가 올바르지 않습니다.');
						$("#pw-feedback").removeAttr('hidden')
					}else if(data=="2"){
						$("#pw-feedback").html('이메일 인증이 필요합니다. 인증 메일을 확인해 주십시오.');
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
	<!-- 로그인 처리 끝 -->

</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>