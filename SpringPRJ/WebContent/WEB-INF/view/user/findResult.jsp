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
					
					
					<!-- 메인메뉴 영역 -->
					<div class="main-menu f-right d-none d-lg-block">
						<nav>
							<ul id="navigation">
								<li><a href="index.html">내 서재</a></li>
								<%if (user_type.equals("0")) { %>
								<li><a href="/user/logout.do">로그아웃</a></li>
								<%} else {%>
								<li><a href="/user/userLogin.do">로그인</a></li>
								<%} %>
								<li><a href="/notice/noticeList.do">공지사항</a></li>
								<li><a href="/notice/noticeList.do">마이페이지</a></li>
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
<%=msg %>
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>