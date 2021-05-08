<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String title = CmmUtil.nvl((String)request.getAttribute("title"));
	String status = CmmUtil.nvl((String)request.getAttribute("status"));
	String msg = CmmUtil.nvl((String)request.getAttribute("msg"));
	String id = CmmUtil.nvl((String)request.getAttribute("id"));
	String findType = CmmUtil.nvl((String)request.getAttribute("findType"));
	String user_type = (String) session.getAttribute("user_type");
%>
<!DOCTYPE html>
<html>
<!-- header 영역  -->
<%@ include file="../header.jsp"%>
<head>
<style>
	#msg {
	color: white;
	}
	#button {
	width: 50%;
	margin: auto;
}
</style>
<meta charset="UTF-8">
<title><%=title %></title>
</head>
<body>
<!-- 메인메뉴 영역 시작 -->
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
					
					<div class="main-menu f-right d-none d-lg-block">
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

<!--히어로 영역 시작 -->
	<div class="slider-area2">
		<div
			class="slider-height3  hero-overly hero-bg4 d-flex align-items-center">
			<div class="container">
				<div class="row">
					<div class="col-xl-12">
						<div class="hero-cap2 pt-20 text-center">
							<h2>아이디 찾기 결과</h2>
							<hr>
							<p id="msg"><%=msg %></p>
							<br>
							<a href="/user/FindPw.do" id="button" class="genric-btn primary radius">비밀번호 찾기</a>
							<br><br>
							<a href="/user/userLogin.do" id="button" class="genric-btn primary radius">돌아기기</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>