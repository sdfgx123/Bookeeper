<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 로그인</title>
<style>
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

<script>
	$(document)
			.ready(
					function() {
						
						$("#doLogin").on("click", function() {
							var id = $("#login_id").val();
							var password = $("#login_password").val();
							if (!id) {
								alert("아이디를 입력하세요");
								$("#login_id").focus();
								return false;
							}
							if (!password) {
								alert("비밀번호를 입력하세요");
								$("#login_password").focus();
								return false;
							}
							if (id && password) {
								loginSubmit();
							}
						});

						$("#index").on("click", function() {
							var id = $("#login_id").val();
							var password = $("#login_password").val();
							if (!email) {
								alert("이메일을 입력하세요");
								$("#login_email").focus();
								return false;
							}
							if (!password) {
								alert("비밀번호를 입력하세요");
								$("#login_password").focus();
								return false;
							}
							if (email && password) {
								loginSubmit();
							}
						});

					});
	
	function loginSubmit() {
		var myJSON = JSON.stringify({
			id : $('#login_id').val(),
			password : $('#login_password').val()
		});
		$.ajax({
			type : "POST",
			url : "/user/getUserLoginCheck.do",
			data : myJSON,
			contentType : "application/json",
			dataType : "json",
			success : function(data) {
				console.log(data);
				if (data.loginResult == 1) {
					window.location.href = "/index.do";
				} else if (data.loginResult == 0) {
					alert("아이디, 비밀번호 불일치");
				} else {
					alert("시스템오류");
				}
			},
			error : function(request, status, error) {
				alert("오류");
			}
		});
	}
	
</script>
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
							<br> <input type="text" name="id" id="id"
								placeholder="아이디 입력" class="single-input"> <br> <input
								type="password" name="password" id="password"
								placeholder="비밀번호 입력" class="single-input"> <br> <a
								href="#" id="doLogin" class="genric-btn primary radius">로그인</a>
							<br> <br> <a href="#" id="login_small"
								class="genric-btn primary radius">아이디 찾기</a> <a
								href="/bootstrap.do" id="login_small"
								class="genric-btn primary radius">돌아가기</a> <br> <br> <a
								href="/user/userRegister.do" id="login"
								class="genric-btn primary radius">회원이 아니신가요? 회원가입 하기</a>
						</div>
						<!-- 로그인 영역 끝 -->

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
						location.href = "/index.do";
					} else if (data == "1") {
						$("#errorMsg").html("아이디 또는 암호가 일치하지 않습니다.")
					} else if (data == "2") {
						$("#errorMsg").html("이메일 인증이 필요합니다.")
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