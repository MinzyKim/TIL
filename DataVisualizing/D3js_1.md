## D3js

### 1. 꺾은선 그래프

 #### 1-1 기본

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Sample</title>
<script src="https://d3js.org/d3.v5.min.js"></script>
 
<script src="./js/line1.js"></script>
		<style>
			svg { width: 380px; height: 240px; border: 1px solid black; }
		  .line { fill: none; stroke: black; }
		</style>
	</head>
	<body>
		<h1>꺾은선 그래프 표시</h1>
		<svg id="myGraph"></svg>
		 
	</body>
</html>
```

```javascript
window.addEventListener("load", function(){
var svgWidth = 320;
var svgHeight = 240;
var dataSet = [10,47,65,8,64,99,75,22,63,80];
var margin = svgWidth/(dataSet.length - 1);

var line = d3.line()
	.x(function(d, i){
		return i*margin;
	})
	.y(function(d, i){
		return svgHeight - d;
	})
	
var lineElements = d3.select("#myGraph")
	.append("path")
	.attr("class", "line")
	.attr("d", line(dataSet))
});
```



#### 1-2 꺾은선 그래프 표시 - 눈금 표시

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Sample</title>
<script src="https://d3js.org/d3.v5.min.js"></script>
 
<script src="./js/line2.js"></script>
		<style>
			svg { width: 380px; height: 240px; border: 1px solid black; }
		  .line { fill: none; stroke: black; }
		  .axis text {
				font-family: sans-serif;
				font-size: 11px;
			}
		  .axis path,
		  .axis line {
				fill: none;
				stroke: black;
			}
		  .axis_x line {
				fill: none;
				stroke: black;
			}
		</style>
	</head>
	<body>
		<h1>꺾은선 그래프 표시 - 눈금 표시</h1>
		<svg id="myGraph"></svg>
		 
	</body>
</html>

```

```javascript
window.addEventListener("load", function(){
var svgWidth = 320;	// SVG 요소의 넓이
var svgHeight = 240;	// SVG 요소의 높이
var offsetX = 30;	// 가로 오프셋
var offsetY = 20;	// 세로 오프셋
var scale = 2.0;	// 2배 크기로 그리기
var dataSet = [10, 47, 65, 8, 64, 99, 75, 22, 63, 80];	// 데이터셋
var margin = svgWidth/(dataSet.length - 1);	// 꺾은선 그래프의 간격 계산
// 꺾은선 그래프의 좌표를 계산하는 메서드
var line = d3.line()	// svg의 선
.x(function(d, i){
		return offsetX + i * margin;	// X 좌표는 표시 순서×간격
	})
.y(function(d, i){
		return svgHeight - (d * scale) - offsetY;	// 데이터로부터 Y 좌표 빼기
	})
// 꺾은선 그래프 그리기
var lineElements = d3.select("#myGraph")
	.append("path")	// 데이터 수만큼 path 요소가 추가됨
	.attr("class", "line")	// CSS 클래스 지정
	.attr("d", line(dataSet))	//연속선 지정
	
// 눈금 표시를 위한 스케일 설정
var yScale = d3.scaleLinear()  // 스케일 설정
  .domain([0, 100])   // 원래 크기
  .range([scale*100, 0]) // 실제 표시 크기
  
var axis = d3.axisLeft(yScale);	

// 눈금 표시
d3.select("#myGraph")	// SVG 요소를 지정
	  .append("g")	// g 요소 추가. 이것이 눈금을 표시하는 요소가 됨
	  .attr("class", "axis")	// CSS 클래스 지정
	  .attr("transform", "translate("+offsetX+", "+offsetY+")")
	  .call(axis)
	  
	// 가로 방향의 선을 표시
	d3.select("#myGraph")
	  .append("rect")	// rect 요소 추가
	  .attr("class", "axis_x")	// CSS 클래스 지정
	  .attr("width", svgWidth)	// 선의 넓이를 지정
	  .attr("height", 1)	// 선의 높이를 지정
	  .attr("transform", "translate("+offsetX+", "+(svgHeight-offsetY-0.5)+")")	
	
	
}); //addEventListener() end
```

