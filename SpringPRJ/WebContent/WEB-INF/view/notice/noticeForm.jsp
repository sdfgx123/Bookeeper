<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.form-container {
	margin: auto;
	width: 500px;
	height: 550px;
	box-shadow: 1px 1px 3px 1px #dadce0;
	padding: 20px;
}

#notice-button {
	width: 100%;
}
</style>
<meta charset="UTF-8">
<!-- session 첨부 -->
<%@ include file="../session.jsp" %>
<title>Bookeeper 공지사항 글쓰기</title>
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
							<h2>공지사항 글쓰기</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->
	<br>
	<!-- 공지사항 글쓰기 폼 영역 시작 -->
	<div class="form-container">
	<form name="noticeForm" action="/notice/DoNoticeForm.do" method="get">
	제목 입력
	<input type="text" name="title" placeholder="제목 입력" class="form-control">
	내용 입력
	<textarea class="form-control w-100" name="content" cols="200" rows="9" placeholder="내용 입력"></textarea>
	<br>
	<button type="submit" id="notice-button" class="button button-contactForm btn_1 boxed-btn">등록</button>
	</form>
	<br>
	<a href="/notice/noticeList.do" id="notice-button" class="button button-contactForm btn_1 boxed-btn">취소</a>
	</div>

</body>
	<!-- footer 영역 첨부 -->
	<%@ include file="../footer.jsp"%>
</html>