<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="poly.dto.LibDTO"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	UserDTO uDTO = (UserDTO) request.getAttribute("uDTO");
	List<LibDTO> rList = ((List<LibDTO>) request.getAttribute("rList"));
%>
<!DOCTYPE html>
<html>
<head>
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<meta charset="UTF-8">
<title>내 서재</title>
<style>
	#container {
		margin: auto;
		text-align: center;
		padding: 30px;
	}
</style>
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
							<h2><%=uDTO.getId() %>님의 서재</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->
	<br>
	<!-- 저장한 책 불러오기 -->
	<div class="row">
	<%for (LibDTO e : rList) {%>
	<div id="container" style="width: 300px; height: 430px; padding: 20px; box-shadow: 1px 1px 3px 1px #dadce0;">
	<p><img src=<%=nvl(e.getThumbnail()) %>></p>
	<hr>
	<p style="height: 70px;"><%=nvl(e.getTitle()) %></p>
	<a href="/lib/LibDetail.do?isbn=<%=nvl(e.getIsbn()) %>" class="genric-btn primary radius">자세히 보기</a>
	</div>
	</div>
	<br>
	<%} %>
	<!-- //저장한 책 불러오기 -->
	
</body>
<!-- footer 영역 첨부 -->
<%@ include file="../footer.jsp"%>
</html>