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
<!-- header 첨부 -->
<%@ include file="../header.jsp"%>
<style>
	#container {
		margin: auto;
		text-align: center;
		padding: 30px;
	}
	
	#button {
	width:250px;
	}
	
	#search_bar {
	width: 78%;
	}

	#mainButton {
	background-color: #B367FF;
	}
	
	#bookCont {
	width: 250px;
	}
	
</style>
</head>
<body>
	<!-- 메인메뉴 영역 첨부 -->
	<%@ include file="../mainMenu.jsp" %>
	
	<!--히어로 영역 시작-->
	<div class="slider-area hero-bg1 hero-overly">
		<div
			class="single-slider hero-overly  slider-height1 d-flex align-items-center">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-xl-10 col-lg-10">
						<!-- Hero Caption -->
						<div class="hero__caption pt-100">
							<h1><%=bookName %> 검색결과</h1>
							<br>
						</div>
						<!--Hero form -->
						<form action="/search/doForm.do" class="search-box mb-100">
							<div id="search_bar" class="input-form">
								<input type="text" id="bookName" name="bookName"
									placeholder="검색할 책 제목 입력">
							</div>
							<br>
							<button type="submit" id="mainButton" class="search-form" style="cursor: pointer">
								<i class="ti-search"></i> 검색
							</button>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!--히어로 영역 끝-->
	
	<!-- 검색한 책 정보 컨테이너 영역-->
	<div id="container" class="row"></div>
	<!-- //검색한 책 정보 컨테이너 영역 -->

	<!-- 책 검색 AJAX 세트 시작 -->
	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>

	<script>
		let query = "<%=bookName%>";
		console.log("query : " + query);
		$.ajax({
			method : "GET",
			url : "https://dapi.kakao.com/v3/search/book?target=title&page=1&size=24",
			data : {
				"query" : query
			},
			headers : {
				Authorization : "KakaoAK d6ed1d1cbb3e2caa8769e2c3e233acca"
			}
		}).done(function(msg) {
			let list = msg.documents
			let resHTML = "";
			
			for(let i=0; i<list.length;i++){
				console.log(list[i]);
				let searchTitle = list[i].title.replaceAll(" ","+");
				
				resHTML+='<div id="bookCont" style="width: 300px; height: 430px; padding: 20px; box-shadow: 1px 1px 3px 1px #dadce0;">';
				resHTML+='<p id="thumb"><img src=\''+list[i].thumbnail+'\'/></p>';
				resHTML+='<hr>';
				resHTML+='<p id=""title"" style="height: 70px;">'+list[i].title+'</p>';
				resHTML+='<a href="/search/SearchDetail.do?bookName='+searchTitle+'" id="button" class="genric-btn primary radius">자세히 보기</a>' 
				resHTML+='</div>';
				resHTML+='<br>';
			}
			$("#container").html(resHTML);
			
			<%-- <%for (int i=0; i<10; i++) { %>
			$("#thumb").append("<img src='" + msg.documents[<%=i%>].thumbnail + "'/>");
			$("#title").append("<strong>" + msg.documents[<%=i%>].title + "</strong>");
			<%} %> --%>
		});
	</script>
	<!-- 책 검색 AJAX 세트 끝 -->

</body>
<!-- footer 영역 -->
<%@ include file="../footer.jsp"%>
</html>