<%@page import="static poly.util.CmmUtil.nvl"%>
<%@page import="java.util.List"%>
<%@page import="poly.dto.UserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	List<UserDTO> rList = ((List<UserDTO>) request.getAttribute("rList"));

%>
<!DOCTYPE html>
<html>
<head>
<style>
	#delete-button {
		color: blue;
		text-decoration: underline;
	}
</style>

<!-- session 첨부 -->
<%@ include file="../session.jsp" %>

<meta charset="UTF-8">
<title>Bookeeper 회원 리스트</title>

<!-- header 영역 첨부 -->
<%@ include file="../header.jsp"%>

<!-- Confirm 함수로 회원 탈퇴시 예, 아니오 구현하려 했으나 어느 것을 클릭해도 예 실행 오류 -->
<script type="text/javascript">
function ask() {
	if (!confirm("정말 탈퇴 하시겠습니까?")) {
		return false;
	}
}
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
							<h2>회원 리스트</h2>
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
				<div class="serial">이름</div>
				<div class="visit">아이디</div>
				<div class="visit">등급</div>
				<div class="visit">비고</div>
			</div>
			<%for(UserDTO e : rList) { %>
			<div class="table-row">
				<div class="serial"><%=nvl(e.getUser_name()) %></div>
				<div class="visit"><%=nvl(e.getId()) %></div>
				<%String type = nvl(e.getUser_type()); %>
				<%if (type.equals("0")) { %>
				<div class="visit">사용자</div>
				<%} else { %>
				<div class="visit">관리자</div>
				<%} %>
				<%if (type.equals("0")) { %>
				<a id="delete-button" href="/admin/DeleteUser.do?seq=<%=e.getUser_seq() %>" class="visit" onclick="ask();">회원 삭제</a>
				<%} %>
			</div>
			<%} %>
		</div>
	</div>
	<!-- Table 영역 끝 -->

</body>
<!-- footer 영역 첨부 -->
<%@ include file="../footer.jsp"%>
</html>