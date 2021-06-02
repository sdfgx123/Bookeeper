<%@page import="poly.dto.LibDTO"%>
<%@page import="java.util.List"%>
<%@page import="static poly.util.CmmUtil.nvl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<LibDTO> rList = ((List<LibDTO>) request.getAttribute("rList"));
	String memo = nvl((String) request.getAttribute("memo"));
%>
<!DOCTYPE html>
<html>
<head>
<style>
	#container {
		width: 300px;
		height: 430px;
		padding: 20px;
		box-shadow: 1px 1px 3px 1px #dadce0;
		margin: auto;
		text-align: center;
	}
</style>
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<meta charset="UTF-8">
<title>내 서재</title>
<!-- header 영역 첨부 -->
<%@ include file="../header.jsp"%>
</head>
<body>
	<!-- 메인메뉴 영역 첨부 -->
	<%@ include file="../mainMenu.jsp" %>
	
	<!--히어로 영역 시작 -->
	<div class="slider-area2">
		<div
			class="slider-height3  hero-overly hero-bg4 d-flex align-items-center">
			<div class="container">
				<div class="row">
					<div class="col-xl-12">
						<div class="hero-cap2 pt-20 text-center">
							<h2></h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- //히어로 영역 -->
	<br>
	<!-- 디테일 표시 영역 -->
	<%for (LibDTO e : rList) { %>
	<div class="section-top-border" style="padding: 50px;">
					<h3 class="mb-30"><%=nvl(e.getTitle()) %></h3>
					<p style="float: right;"><%=nvl(e.getDatetime()) %></p>
					<hr>
					<div class="row">
						<div class="col-md-3">
							<div class="img-fluid">
							<img src=<%=nvl(e.getThumbnail()) %>>
							</div>
						</div>
						<div class="col-md-9 mt-sm-20">
							<p><%=nvl(e.getContents()) %></p>
						</div>
					</div>
					<div style="float: right; text-align: right;">
					<p><%=nvl(e.getAuthors()) %></p>
					<p><%=nvl(e.getPublisher()) %></p>
					</div>
					<br><br>
					<hr>
					<div style="text-align: center; width: 100%; background-color: yellow; padding: 10px; box-shadow: 1px 1px 3px 1px #dadce0; margin: auto;">
					<%if (memo.equals("")) { %>
					<p>메모를 추가해 보세요!</p>
					<%} else {%>
					<p><%=memo %></p>
					<%} %>
					</div>
					<br>
					<div style="text-align: center;">
					<%if (memo.equals("")) { %>
					<a href="/lib/MemoForm.do?isbn=<%=nvl(e.getIsbn()) %>" class="genric-btn warning" style="margin: auto; color: black;">메모 추가</a>
					<%} else { %>
					<a href="/lib/EditMemoForm.do?isbn=<%=nvl(e.getIsbn()) %>" class="genric-btn warning" style="margin: auto; color: black;">메모 수정</a>
					<%} %>
					</div>
					<br><br>
	</div>
	<%} %>
	<!-- //디테일 표시 영역 -->
	
</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>