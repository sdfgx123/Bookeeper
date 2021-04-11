<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Form result</title>
</head>
<body>

	<p></p>

	<script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
	
	<script>
        $.ajax({
            method: "GET",
            url: "https://dapi.kakao.com/v3/search/book?target=title",
            data: { query: "이기적 유전자" },
            headers: { Authorization: "KakaoAK d6ed1d1cbb3e2caa8769e2c3e233acca" }
        })
            .done(function (msg) {
                console.log(msg.documents[0].title);
                console.log(msg.documents[0].thumbnail);
                $( "p" ).append( "<strong>" + msg.documents[0].title + "</strong>" );
                $( "p" ).append( "<img src='" + msg.documents[0].thumbnail + "'/>" );


            });
    </script>
    
</body>
</html>