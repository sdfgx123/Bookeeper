<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js" lang="zxx">
<head>
<!-- session 첨부 -->
<%@ include file="./session.jsp" %>
<title>Bookeeper 메인페이지</title>

<!-- header 영역 첨부 -->
<%@ include file="./header.jsp"%>

<style>
#search_bar {
	width: 78%;
}

#mainButton {
	background-color: #B367FF;
}
</style>
</head>
<body>
	<!-- Preloader Start -->
	<div id="preloader-active">
		<div
			class="preloader d-flex align-items-center justify-content-center">
			<div class="preloader-inner position-relative">
				<div class="preloader-circle"></div>
				<div class="preloader-img pere-text">
					<img src="assets/img/logo/loder.png" alt="">
				</div>
			</div>
		</div>
	</div>
	<!-- Preloader End -->

	<!-- 메인메뉴 영역 첨부 -->
	<%@ include file="./mainMenu.jsp"%>

	<main>
	
	<!--히어로 영역 시작-->
	<div class="slider-area hero-bg1 hero-overly">
		<div
			class="single-slider hero-overly  slider-height1 d-flex align-items-center">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-xl-10 col-lg-10">
						<!-- Hero Caption -->
						<div class="hero__caption pt-100">
							<h1>Bookeeper</h1>
							<p>책 검색을 통해 나만의 서재를 만들어 보세요!</p>
						</div>
						<!--Hero form -->
						<form action="/search/doForm.do" class="search-box mb-100">
							<div id="search_bar" class="input-form">
								<input type="text" id="bookName" name="bookName"
									placeholder="검색할 책 제목 입력">
							</div>
							<br>
							<button type="submit" id="mainButton" class="search-form">
								<i class="ti-search"></i> 검색
							</button>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!--히어로 영역 끝-->
	
	<!--워드클라우드 영역-->
	<div class="popular-location border-bottom section-padding40">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<!-- Section Tittle -->
					<div class="section-tittle text-center mb-80">
						<h2>워드클라우드</h2>
						<p>교보문고에서 추출한 워드클라우드를 확인해 보세요!</p>
					</div>
					<%@ include file="./cloudTest2.jsp" %>
				</div>
			</div>
		</div>
	</div>
	<!-- //워드클라우드 영역 --></main>
</body>
<!-- footer 영역 -->
<%@ include file="./footer.jsp"%>
</html>