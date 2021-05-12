<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String bookName = CmmUtil.nvl((String) request.getAttribute("bookName"));
%>
<!DOCTYPE html>
<html>
<head>
<!-- session 첨부 -->
<%@ include file="../session.jsp" %>
<meta charset="UTF-8">
<title>책 검색 결과</title>
<!-- header 영역 첨부 -->
<%@ include file="../header.jsp"%>
<style>
	#container {
		width: 300px;
		height: 500px;
		margin: auto;
		text-align: center;
		box-shadow: 1px 1px 3px 1px #dadce0;
	}
	
	
</style>
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
							<h2>"<%=bookName %>" 검색결과</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->
	
	<!-- 검색한 책 정보 컨테이너 영역 시작-->
	<br><br>
	<%for (int i=0; i<10; i++) { %>
	<div id="container">
	<br>
	<%for (int j=0; j<10; j++) { %>
	<p id="thumb"></p>
	<%} %>
	<hr>
	<p id="title"></p>
	<a href="/search/SearchDetail.do?bookName=<%=bookName %>" id="button" class="genric-btn primary radius">자세히 보기</a>
	</div>
	<%} %>
	<!-- 검색한 책 정보 컨테이너 영역 끝 -->

	<!-- 책 검색 AJAX 세트 시작 -->
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>

	<script>
		let query = "<%=bookName%>";
		console.log("query : " + query);
		$.ajax({
			method : "GET",
			url : "https://dapi.kakao.com/v3/search/book?target=title&page=1&size=10",
			data : {
				"query" : query
			},
			headers : {
				Authorization : "KakaoAK d6ed1d1cbb3e2caa8769e2c3e233acca"
			}
		}).done(function(msg) {
			console.log(msg.documents[0].title);
			console.log(msg.documents[0].thumbnail);
			<%for (int i=0; i<10; i++) { %>
			$("#thumb").append("<img src='" + msg.documents[<%=i%>].thumbnail + "'/>");
			$("#title").append("<strong>" + msg.documents[<%=i%>].title + "</strong>");
			<%} %>
		});
	</script>
	<!-- 책 검색 AJAX 세트 끝 -->

</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>