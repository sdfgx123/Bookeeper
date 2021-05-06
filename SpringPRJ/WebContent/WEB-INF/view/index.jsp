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
						<form action="/doForm.do" class="search-box mb-100">
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
	
	<!--? Popular Locations Start 01-->
	<div class="popular-location border-bottom section-padding40">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<!-- Section Tittle -->
					<div class="section-tittle text-center mb-80">
						<h2>워드클라우드 영역</h2>
						<p>교보문고에서 검색한 최신 단행본 몇권의 워드클라우를 확인해 보세요!</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="single-location mb-30">
						<div class="location-img">
							<img src="assets/img/gallery/location1.png" alt="">
						</div>
						<div class="location-details">
							<p>New York</p>
							<a href="#" class="location-btn">65 <i class="ti-plus"></i>
								Listing
							</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="single-location mb-30">
						<div class="location-img">
							<img src="assets/img/gallery/location2.png" alt="">
						</div>
						<div class="location-details">
							<p>Paris</p>
							<a href="#" class="location-btn">60 <i class="ti-plus"></i>
								Listing
							</a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6">
					<div class="single-location mb-30">
						<div class="location-img">
							<img src="assets/img/gallery/location3.png" alt="">
						</div>
						<div class="location-details">
							<p>Rome</p>
							<a href="#" class="location-btn">50 <i class="ti-plus"></i>
								Listing
							</a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6">
					<div class="single-location mb-30">
						<div class="location-img">
							<img src="assets/img/gallery/location4.png" alt="">
						</div>
						<div class="location-details">
							<p>Italy</p>
							<a href="#" class="location-btn">28 <i class="ti-plus"></i>
								Location
							</a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6">
					<div class="single-location mb-30">
						<div class="location-img">
							<img src="assets/img/gallery/location5.png" alt="">
						</div>
						<div class="location-details">
							<p>Nepal</p>
							<a href="#" class="location-btn">99 <i class="ti-plus"></i>
								Listing
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Popular Locations End --> <!--? Popular Locations Start 01-->
	<div class="popular-location border-bottom section-padding40">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<!-- Section Tittle -->
					<div class="section-tittle text-center mb-80">
						<h2>News & Updates</h2>
						<p>Let's uncover the best places to eat, drink, and shop
							nearest to you.</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="single-location mb-30">
						<div class="location-img">
							<img src="assets/img/gallery/home-blog1.png" alt="">
						</div>
						<div class="location-details">
							<a href="#" class="location-btn">Tips</a>
							<ul>
								<li>12 March I by Alan</li>
							</ul>
							<p>
								<a href="blog_details.html">The Best SPA Salons For Your
									Relaxation</a>
							</p>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="single-location mb-30">
						<div class="location-img">
							<img src="assets/img/gallery/home-blog2.png" alt="">
						</div>
						<div class="location-details">
							<a href="#" class="location-btn">Tips</a>
							<ul>
								<li>12 March I by Alan</li>
							</ul>
							<p>
								<a href="blog_details.html">The Best SPA Salons For Your
									Relaxation</a>
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Popular Locations End --> <!--? Want To work 02-->
	<section class="wantToWork-area">
		<div class="container">
			<div class="wants-wrapper w-padding2">
				<div class="row justify-content-between">
					<div class="col-xl-8 col-lg-8 col-md-7">
						<div class="wantToWork-caption wantToWork-caption2">
							<img src="assets/img/logo/logo2_footer.png" alt="" class="mb-20">
							<p>Zookeeper를 통해 책을 검색하고 내 서재를 만들어 그 책들을 관리해 보세요!</p>
							<br>
							<p style="font-size: 12px">서울특별시 강서구 우장산로 10길 112 한국폴리텍 강서캠퍼스
								데이터분석과</p>
						</div>
					</div>

				</div>
			</div>
		</div>
	</section>
	<!-- Want To work End --> <!--? Want To work 01-->
	<section class="wantToWork-area">
		<div class="container">
			<div class="wants-wrapper">
				<div class="row align-items-center justify-content-between"></div>
			</div>
		</div>
	</section>
	<!-- Want To work End --> </main>

</body>
<!-- footer 영역 -->
<%@ include file="./footer.jsp"%>
</html>