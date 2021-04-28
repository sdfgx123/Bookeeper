<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
				<div class="serial">#</div>
				<div class="country">Countries</div>
				<div class="visit">Visits</div>
				<div class="percentage">Percentages</div>
			</div>
			<div class="table-row">
				<div class="serial">01</div>
				<div class="country">
					<img src="../assets/img/elements/f1.jpg" alt="flag">Canada
				</div>
				<div class="visit">645032</div>
				<div class="percentage">
					<div class="progress">
						<div class="progress-bar color-1" role="progressbar"
							style="width: 80%" aria-valuenow="80" aria-valuemin="0"
							aria-valuemax="100"></div>
					</div>
				</div>
			</div>
			<div class="table-row">
				<div class="serial">02</div>
				<div class="country">
					<img src="../assets/img/elements/f2.jpg" alt="flag">Canada
				</div>
				<div class="visit">645032</div>
				<div class="percentage">
					<div class="progress">
						<div class="progress-bar color-2" role="progressbar"
							style="width: 30%" aria-valuenow="30" aria-valuemin="0"
							aria-valuemax="100"></div>
					</div>
				</div>
			</div>
			<div class="table-row">
				<div class="serial">03</div>
				<div class="country">
					<img src="../assets/img/elements/f3.jpg" alt="flag">Canada
				</div>
				<div class="visit">645032</div>
				<div class="percentage">
					<div class="progress">
						<div class="progress-bar color-3" role="progressbar"
							style="width: 55%" aria-valuenow="55" aria-valuemin="0"
							aria-valuemax="100"></div>
					</div>
				</div>
			</div>
			<div class="table-row">
				<div class="serial">04</div>
				<div class="country">
					<img src="../assets/img/elements/f4.jpg" alt="flag">Canada
				</div>
				<div class="visit">645032</div>
				<div class="percentage">
					<div class="progress">
						<div class="progress-bar color-4" role="progressbar"
							style="width: 60%" aria-valuenow="60" aria-valuemin="0"
							aria-valuemax="100"></div>
					</div>
				</div>
			</div>
			<div class="table-row">
				<div class="serial">05</div>
				<div class="country">
					<img src="../assets/img/elements/f5.jpg" alt="flag">Canada
				</div>
				<div class="visit">645032</div>
				<div class="percentage">
					<div class="progress">
						<div class="progress-bar color-5" role="progressbar"
							style="width: 40%" aria-valuenow="40" aria-valuemin="0"
							aria-valuemax="100"></div>
					</div>
				</div>
			</div>
			<div class="table-row">
				<div class="serial">06</div>
				<div class="country">
					<img src="../assets/img/elements/f6.jpg" alt="flag">Canada
				</div>
				<div class="visit">645032</div>
				<div class="percentage">
					<div class="progress">
						<div class="progress-bar color-6" role="progressbar"
							style="width: 70%" aria-valuenow="70" aria-valuemin="0"
							aria-valuemax="100"></div>
					</div>
				</div>
			</div>
			<div class="table-row">
				<div class="serial">07</div>
				<div class="country">
					<img src="../assets/img/elements/f7.jpg" alt="flag">Canada
				</div>
				<div class="visit">645032</div>
				<div class="percentage">
					<div class="progress">
						<div class="progress-bar color-7" role="progressbar"
							style="width: 30%" aria-valuenow="30" aria-valuemin="0"
							aria-valuemax="100"></div>
					</div>
				</div>
			</div>
			<div class="table-row">
				<div class="serial">08</div>
				<div class="country">
					<img src="../assets/img/elements/f8.jpg" alt="flag">Canada
				</div>
				<div class="visit">645032</div>
				<div class="percentage">
					<div class="progress">
						<div class="progress-bar color-8" role="progressbar"
							style="width: 60%" aria-valuenow="60" aria-valuemin="0"
							aria-valuemax="100"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Table 영역 끝 -->

	<!-- footer 영역 첨부 -->
	<%@ include file="../footer.jsp"%>

</body>
</html>