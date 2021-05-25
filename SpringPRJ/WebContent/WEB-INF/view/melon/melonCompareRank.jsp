<%--
  Created by IntelliJ IDEA.
  User: data03
  Date: 2021-04-30
  Time: 오후 6:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.js"
		integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
		crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(window).on("load", function () {
            //페이지 로딩 완료 후, 멜론 순위가져오기 함수 실행함
            getRank();
        });
        function getRank() {
            //Ajax 호출
            $.ajax({
                url: "/melon/getCompareRank.do",
                type: "post",
                dataType: "JSON",
                contentType: "application/json; charset=UTF-8",
                success: function (json) {
                    var melon_rank = "";
                    for (var i = 0; i < json.length; i++) {
                        melon_rank += (json[i].song + " | ");
                        melon_rank += (json[i].singer + " | ");
                        melon_rank += ("현재 : " + json[i].current_rank + "위 | ");
                        melon_rank += ("이전 : " + json[i].pre_rank + "위 | ");
                        var change_rank = json[i].change_rank;
                        if (change_rank.indexOf("하강") > 0) { //순위 하락은 파란색
                            melon_rank += ("<font color='blue'>" + json[i].change_rank + "</font><br>");
                        } else if (change_rank.indexOf("상승") > 0) { //순위 상승은 빨간색
                            melon_rank += ("<font color='red'>" + json[i].change_rank + "</font><br>");
                        } else {
                            melon_rank += ("<font color='black'>" + json[i].change_rank + "</font><br>");
                        }
                    }
                    $("#melon_rank").html(melon_rank);
                }
            })
        }
    </script>
</head>
<body>
<h1>멜론 노래별 이전 랭킹과 비교</h1>
<hr/>
<div id="melon_rank"></div>
<br/>
<hr/>
</body>
</html>