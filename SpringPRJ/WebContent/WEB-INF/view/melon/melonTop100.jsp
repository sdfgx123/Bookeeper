<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<script src="jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(window).on("load", function() {

		//������ �ε� �Ϸ� ��, ��� ���� �������� �Լ� ������
		getRank();
	});

	//��� ���� ��������
	function getRank() {

		//Ajax ȣ��
		$.ajax({
			url : "/melon/getRank.do",
			type : "post",
			dataType : "JSON",
			contentType : "appliation/json; charset=UTF-8",
			success : function(json) {

				var melon_rank = "";

				for (var i = 0; i < json.length; i++) {
					melon_rank += (json[i].rank + "�� | ");
					melon_rank += (json[i].song + " | ");
					melon_rank += (json[i].singer + " | ");
					melon_rank += (json[i].album + "<br>");
				}

				$('#melon_rank').html(melon_rank);
			}
		})

	}
</script>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h1>��� ����</h1>
	<hr />
	<div id="melon_rank"></div>
	<br />
	<hr />
</body>
</html>