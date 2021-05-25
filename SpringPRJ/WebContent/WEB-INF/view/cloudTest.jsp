<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#chartdiv {
  width: 100%;
  height: 350px;
}
body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="//cdn.amcharts.com/lib/4/core.js"></script>
<script src="//cdn.amcharts.com/lib/4/charts.js"></script>
<script src="//cdn.amcharts.com/lib/4/plugins/wordCloud.js"></script>
<script src="//cdn.amcharts.com/lib/4/themes/animated.js"></script>
<script type="text/javascript">
/**
 * ---------------------------------------
 * This demo was created using amCharts 4.
 *
 * For more information visit:
 * https://www.amcharts.com/
 *
 * Documentation is available at:
 * https://www.amcharts.com/docs/v4/
 * ---------------------------------------
 */

am4core.useTheme(am4themes_animated);
var chart = am4core.create("chartdiv", am4plugins_wordCloud.WordCloud);
var series = chart.series.push(new am4plugins_wordCloud.WordCloudSeries());

series.data = [{
  "tag": "Breaking News",
  "weight": 60
}, {
  "tag": "Environment",
  "weight": 80
}, {
  "tag": "Politics",
  "weight": 90
}, {
  "tag": "Business",
  "weight": 25
}, {
  "tag": "Lifestyle",
  "weight": 30
}, {
  "tag": "World",
  "weight": 45
}, {
  "tag": "Sports",
  "weight": 160
}, {
  "tag": "Fashion",
  "weight": 20
}, {
  "tag": "Education",
  "weight": 78
}];

series.dataFields.word = "tag";
series.dataFields.value = "weight";
</script>
<div id="chartdiv"></div>
</body>
</html>