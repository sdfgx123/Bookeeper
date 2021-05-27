<%@page import="poly.util.CmmUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String bookName = CmmUtil.nvl((String) request.getAttribute("bookName"));
	bookName = bookName.replaceAll("& #40;","(").replaceAll("& #41;",")");
%>
<!DOCTYPE html>
<html>
<head>
<style>
	#detail {
		width: 80%;
		margin: auto;
		padding: 30px;
	}
	.button-container {
		width: 200px;
		margin: auto;
	}
</style>
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<meta charset="UTF-8">
<title>책 검색 결과</title>
<!-- header 영역 첨부 -->
<%@ include file="../header.jsp"%>
	<!-- 책 검색 AJAX 세트 시작 -->
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>

	<script>
		let query = "<%=bookName%>";
		console.log("query : "+query);
		$.ajax({
			method : "GET",
			url : "https://dapi.kakao.com/v3/search/book?target=title",
			data : {
				"query" : query
			},
			headers : {
				Authorization : "KakaoAK d6ed1d1cbb3e2caa8769e2c3e233acca"
			}
		}).done(function(msg) {
			console.log(msg.documents[0].title);
			console.log(msg.documents[0].thumbnail);
			console.log(msg.documents[0].contents);
			$("#thumb").append("<img src='" + msg.documents[0].thumbnail + "'/>");
			$("#title").append("<strong>" + msg.documents[0].title + "</strong>");
			$("#contents").append(msg.documents[0].contents);
			$("#datetime").append(msg.documents[0].datetime);
			$("#authors").append(msg.documents[0].authors);
			$("#publisher").append(msg.documents[0].publisher);
		});
	</script>
	<!-- 책 검색 AJAX 세트 끝 -->
	
	<!-- 내 서재에 책 저장 함수 시작 -->
	<script type="text/javascript">
		$.ajax({
			url : '/search/InsertBookInfo.do',
			type : 'post',
			data : {
				"title" : $('#title'),
				"contents" : contents,
				"thumbnail" : thumbnail,
				"datatime" : datetime,
				"authors" : authors,
				"publisher" : publisher
			},
			contentType: "application/json; charset=UTF-8",
			success: function(data) {
			}
		});
		
	</script>
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
							<h2>
								<%=bookName%>
							</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->
	
	<br>
	<!-- 책 디테일 표시 영역 -->
	<div class="section-top-border" id="detail">
					<h3 class="mb-30" id="title"></h3>
					<p id="datetime" style="float: right;"></p>
					<hr>
					<div class="row">
						<div class="col-md-3">
							<div id="thumb" alt="" class="img-fluid"></div>
						</div>
						<div class="col-md-9 mt-sm-20">
							<p id="contents"></p>
						</div>
					</div>
					<hr>
					<div style="float: right; text-align: right;">
					<p id="authors"></p>
					<p id="publisher"></p>
					</div>
					<br>
					<br>
	</div>
	<!-- //책 디테일 표시 영역 -->
	<br>
	<br>
	<!-- 버튼 영역 -->
	<div class="button-container">
	<a href="/lib/InsertBookInfo.do" class="genric-btn primary radius">내 서재에 추가</a>
	</div>
</body>
<!-- footer 영역 첨부 -->
<%@ include file="../footer.jsp"%>
</html>