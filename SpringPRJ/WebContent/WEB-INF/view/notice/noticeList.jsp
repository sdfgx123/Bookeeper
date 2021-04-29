<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="poly.dto.NoticeDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

	List<NoticeDTO> rList = ((List<NoticeDTO>) request.getAttribute("rList"));

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>

<!-- header 영역 첨부 -->
<%@ include file="../header.jsp"%>

</head>
<body>
	<!-- 메인메뉴 시작-->
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
									<li><a href="listing.html">공지사항</a></li>
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
	<!-- 메인메뉴 끝 -->

	<!--히어로 영역 시작 -->
	<div class="slider-area2">
		<div
			class="slider-height3  hero-overly hero-bg4 d-flex align-items-center">
			<div class="container">
				<div class="row">
					<div class="col-xl-12">
						<div class="hero-cap2 pt-20 text-center">
							<h2>공지사항</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->

	<!-- Table 영역 시작 -->
	<div class="progress-table-wrap">
		<div class="progress-table">
			<div class="table-head">
				<div class="serial">순번</div>
				<div class="country">제목</div>
				<div class="visit">등록자</div>
				<div class="visit">등록일</div>
			</div>
			<%for(NoticeDTO e : rList) { %>
			<div class="table-row">
				<div class="serial"><%=nvl(e.getSeq()) %></div>
				<div class="country"><%=nvl(e.getPost_title()) %></div>
				<div class="visit"><%=nvl(e.getReg_id()) %></div>
				<div class="visit"><%=nvl(e.getReg_dt()) %></div>
			</div>
			<%} %>
		</div>
	</div>
	<!-- Table 영역 끝 -->

	<!-- footer 영역 첨부 -->
	<%@ include file="../footer.jsp"%>

</body>
</html>