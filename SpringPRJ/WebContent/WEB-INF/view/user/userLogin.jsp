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
	<header>
	
		<div class="header-area header-transparent">
			<div class="main-header header-sticky">
				<div class="container-fluid">
					<div
						class="menu-wrapper d-flex align-items-center justify-content-between">

						<!-- Logo -->
						<div class="logo">
							<a href="/bootstrap.do"><img src="../assets/img/logo/logo.png"
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
	
	 <!--? Hero Area Start-->
        <div class="slider-area hero-bg1 hero-overly">
            <div class="single-slider hero-overly  slider-height1 d-flex align-items-center">
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-xl-10 col-lg-10">
                            <!-- Hero Caption -->
                            <div class="hero__caption pt-100">
                                <h1>Bookeeper 로그인</h1>
                                <br>
                                <form name="userLogin" action="#">
                                <input type="text" name="id" id="login" placeholder="아이디 입력" class="single-input">
                                <br>
                                <input type="text" name="password" id="login" placeholder="비밀번호 입력" class="single-input">
                                <br>
                                <a href="#" id="login" class="genric-btn primary radius" >로그인</a>
                                </form>
                                <br>
                                <a href="#" id="login_small" class="genric-btn primary radius">아이디 찾기</a>
                                <a href="/bootstrap.do" id="login_small" class="genric-btn primary radius">돌아가기</a>
                                <br><br>
                                <a href="#" id="login" class="genric-btn primary radius">회원이 아니신가요? 회원가입 하기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--Hero Area End-->
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>