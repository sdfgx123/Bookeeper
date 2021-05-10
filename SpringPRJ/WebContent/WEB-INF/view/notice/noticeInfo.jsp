<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="poly.dto.NoticeDTO"%>
<%
	NoticeDTO rDTO = (NoticeDTO)request.getAttribute("rDTO");
%>
<!DOCTYPE html>
<html>
<head>
<style>
	#date {
		float: right;
	}
</style>
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<meta charset="UTF-8">
<title>Bookeeper 공지사항</title>
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
							<h2>공지사항</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->
	
	<section class="sample-text-area">
			<div class="container box_1170">
				<h2 class="text-heading"><%=rDTO.getPost_title() %></h2>
				<p id="date"><%=rDTO.getReg_dt() %></p>
				<hr>
				<p class="sample-text">
					<%=rDTO.getPost_content() %>
				</p>
			</div>
		</section>

</body>
<!-- footer 영역 첨부 -->
<%@ include file="../footer.jsp"%>
</html>