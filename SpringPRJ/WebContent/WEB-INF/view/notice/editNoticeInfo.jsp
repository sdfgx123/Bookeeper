<%@page import="poly.dto.NoticeDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
 
 #write-button {
		float: right;
		padding: 30px;
		margin-right: 350px;
}
</style>
<meta charset="UTF-8">
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<title>공지사항 수정</title>
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
							<h2>공지사항 수정</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->
	
	<!-- 공지사항 상세 시작 -->
	<section class="sample-text-area">
	<form action="DoEditNoticeInfo.do" method="get" name="editNoticeForm">
			<div class="container box_1170">
				<input type="text" name="title" placeholder="제목 입력" class="form-control" value=<%=rDTO.getPost_title() %>>
				<p id="date"><%=rDTO.getReg_dt() %></p>
				<hr>
				<textarea class="form-control w-100" name="content" cols="200" rows="9" placeholder="내용 입력"><%=rDTO.getPost_content() %></textarea>
				<input type="text" name="seq" value=<%=rDTO.getSeq() %> hidden="hidden">
			</div>
	<!-- 관리자 공지사항 글 수정, 삭제 버튼 영역 시작-->
	<%if (user_type.equals("1")) { %>
	<div id="write-button">
	<button type="submit" class="genric-btn primary radius">완료</button>
	<a href="/notice/NoticeInfo.do?seq=<%=rDTO.getSeq() %>" class="genric-btn primary radius" >취소</a>
	</div>
	<%} %>
	<!-- 관리자 공지사항 글 수정, 삭제 버튼 영역 끝-->
	</form>
	</section>
	<!-- 공지사항 상세 끝 -->
	
</body>
<!-- footer 영역 첨부 -->
<%@ include file="../footer.jsp"%>
</html>