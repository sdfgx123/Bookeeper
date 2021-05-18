<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="poly.dto.NoticeDTO"%>
<%
	NoticeDTO rDTO = (NoticeDTO)request.getAttribute("rDTO");
%>
<!DOCTYPE html>
<html>
<head>
<!-- Confirm 함수로 회원 탈퇴시 예, 아니오 구현하려 했으나 어느 것을 클릭해도 예 실행 오류 -->
<script type="text/javascript">
function ask() {
	if (!confirm("정말 삭제 하시겠습니까?")) {
		return false;
	}
}
</script>
<style>
	#date {
		float: right;
	}
	
	#write-button {
		float: right;
		padding: 30px;
		margin-right: 330px;
	}
</style>
<!-- session 첨부 -->
<%@ include file="../session.jsp"%>
<meta charset="UTF-8">
<title>Bookeeper 공지사항</title>
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
							<h2>공지사항</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 히어로 영역 끝 -->
	
	<!-- 공지사항 상세 시작 -->
	<section class="sample-text-area">
			<div class="container box_1170">
				<h2 class="text-heading"><%=rDTO.getPost_title() %></h2>
				<p id="date"><%=rDTO.getReg_dt() %></p>
				<hr>
				<p class="sample-text">
					<%=rDTO.getPost_content() %>
				</p>
			</div>
	<!-- 관리자 공지사항 글 수정, 삭제 버튼 영역 시작-->
	<%if (user_type.equals("1")) { %>
	<div id="write-button">
	<a href="/notice/NoticeForm.do" class="genric-btn primary radius">수정</a>
	<a href="/notice/DeleteNoticeInfo.do?seq=<%=rDTO.getSeq() %>" class="genric-btn primary radius" onclick="ask();">삭제</a>
	</div>
	<%} %>
	<!-- 관리자 공지사항 글 수정, 삭제 버튼 영역 끝-->
	</section>
	<!-- 공지사항 상세 끝 -->
	
	

</body>
<!-- footer 영역 첨부 -->
<%@ include file="../footer.jsp"%>
</html>