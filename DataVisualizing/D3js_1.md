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

#### 1-3 여러 개의 꺾은선 그래프 표시

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Sample</title>
<script src="https://d3js.org/d3.v5.min.js"></script>
 
<script src="./js/line3.js"></script>
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
          .itemA { stroke: #000; }
          .itemB { stroke: #F00; }
          .itemC { stroke: #00F; }
		</style>
	</head>
	<body>
		<h1>여러 개의 꺾은선 그래프 표시</h1>
		<svg id="myGraph"></svg>
		 
	</body>
</html>

```

```javascript
window.addEventListener("load",function(){
var svgWidth =320;   //SVG요소의 넓이
var svgHeight =240;   //SVG요소의 높이
var offsetX = 30;
var offsetY = 20;
var scale = 2.0;
var dataSet1 = [10,47,65,8,64,99,75,22,63,80];   //데이터셋
var dataSet2 = [90,77,55,48,64,90,85,42,13,40];   
var dataSet3 = [50,27,45,58,84,70,45,22,30,90];   
var margin = svgWidth/(dataSet1.length - 1); // 꺾은 선 그래프의 간격 계산
drawGraph(dataSet1, "itemA");   //itemA의 꺽은선 그래프 표시
drawGraph(dataSet2, "itemB");   //itemB의 꺽은선 그래프 표시
drawGraph(dataSet3, "itemC");   //itemC의 꺽은선 그래프 표시
drawScale();   //눈금 표시


//꺾은 선 그래프의 좌표를 계산하는 메소드
function drawGraph(dataSet, cssClassName){
   //꺽은선 그래프의 좌표를 계산하는 메소드
   var line = d3.line()   //svg의 선
   .x(function(d,i){               //X 좌표는 표시 순서x 간격
      return  offsetX + i * margin;   //X는 꺽은선 따라 움직이기 때문에 index * margin 으로
   })                           //marign(전체길이/데이터셋갯수나누기) 을 더해준다.)
   .y(function(d,i){
      return svgHeight - (d * scale) - offsetY;   //데이터로부터 Y 좌표 빼기
   })                                    //y는고정이라 i(index)안씀.
   
   //꺾은 선 그래프 그리기
   var LineElements = d3.select("#myGraph")
   .append("path")         //데이터 수만큼 path 요소가 추가됨
   .attr("class", "line " + cssClassName)      // CSS 클래스 지정
   .attr("d", line(dataSet))      //연속선 지정
   
}


function drawScale(){

   //눈금을 표시하기 위한 스케일 설정
   var yScale = d3.scaleLinear() // 스케일 설정
   .domain([0,100])   // 원래 크기
   .range([scale*100, 0])   // 실제 출력 크기
   
   //눈금의 표시 위치를 왼쪽으로 지정
   var axis = d3.axisLeft(yScale)

   //눈금을 설정하여 표시
   d3.select("#myGraph").append("g")
   .attr("class", "axis")
   .attr("transform", "translate("+offsetX+", "+offsetY+")")   //눈금을 오프셋 X,Y만큼 움직여준다.
   .call(axis)
   
   // 가로 방향의 선을 표시
   d3.select("#myGraph")
     .append("rect")   
     .attr("class", "axis_x")   
     .attr("width", svgWidth)   
     .attr("height", 1)   // 
     .attr("transform", "translate("+offsetX+", "+(svgHeight-offsetY-0.5)+")")   
}   
   
   

});   // addEventListener() end
```

#### 1-4 영역 안을 칠한 꺾은선 그래프 표시

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Sample</title>
<script src="https://d3js.org/d3.v4.js"></script>
<script src="./js/line4.js"></script>
<sytle>
</sytle>
</head>
<body>
<h1> 영역 안을 칠한 꺾은선 그래프 표시</h1>

<div id="my_dataviz"></div>
<br>
</body>
</html>
```

```javascript
window.addEventListener("load",function(){
	
	   // set the dimensions and margins of the graph
	var margin = {top:10, right: 30, bottom: 30, left: 50},
		width = 460 - margin.left - margin.right,
		height = 400 - margin.top - margin.bottom;
	
	//append the svg object to the body of the page
	var svg = d3.select("#my_dataviz")
		.append("svg")
		.attr("width", width + margin.left + margin.right)
		.attr("hight", height + margin.top + margin.bottom)
		.append("g")
		.attr("transform",
				"translate(" + margin.left +", "+ margin.top+")");
	//Road the data
	d3.csv("./datas/orders.csv",
			// when reading the csv, I must format variables:
	function(d){
		return { date : d3.timeParse("%Y-%m-%d")(d.date), value : d.value }
	},
	
	// Now I can user this dataset :
	function(data){
		
		// Add X axis --> it is a date format
		var x = d3.scaleTime()
			.domain(d3.extent(data, function(d) { return d.date; }))
			.range([0, width ]);
		svg.append("g")
			.attr("transform", "translate(0, "+height+")")
			.call(d3.axisBottom(x));
		
		// Add y axis
		var y = d3.scaleLinear()
			.domain([0, d3.max(data, function(d) { return +d.value; })])
			.range([ height, 0 ]);
		
		svg.append("g")
			.call(d3.axisLeft(y));
		
		//Add the area
		svg.append("path")
			.datum("data")
			.attr("fill", "#cce5df")
			.attr("stroke", "#69b3a2")
			.attr("stroke-width", 1.5)
			.attr("d", d3.area()
						 .x(function(d) { return x(d.date ) })
						 .y0(y(0))
						 .y1(function(d) { return y(d.value) })
			)
			
	})
})
```

#### 1-5 여러 개의 꺾은선 그래프 표시

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Sample</title>
<script src="https://d3js.org/d3.v5.min.js"></script>

 
<script src="./js/line5.js"></script>
		<style>
			svg { width: 380px; height: 300px; border: 1px solid black; }
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
          .itemA { stroke: #000; }
          .itemB { stroke: #F00; }
          .itemC { stroke: #00F; }
		</style>
	</head>
	<body>
		<h1>여러 개의 꺾은선 그래프 표시</h1>
		<svg id="myGraph"></svg>
		 
	</body>
</html>

```

```javascript
window.addEventListener("load",function(){
var svgWidth =320;   //SVG요소의 넓이
var svgHeight =240;   //SVG요소의 높이
var offsetX = 30;
var offsetY = 20;
var scale = 2.0;
var dataSet = [
	[
		{ year : 2004, value : 10 },
		{ year : 2005, value : 47 },
		{ year : 2006, value : 65 },
		{ year : 2007, value : 8 },
		{ year : 2008, value : 64 },
		{ year : 2009, value : 99 },
		{ year : 2010, value : 75 },
		{ year : 2011, value : 22 },
		{ year : 2012, value : 63},
		{ year : 2013, value : 80 }
	],
	[
		{ year : 2004, value : 90 },
		{ year : 2005, value : 77 },
		{ year : 2006, value : 55 },
		{ year : 2007, value : 48},
		{ year : 2008, value : 64 },
		{ year : 2009, value : 90 },
		{ year : 2010, value : 85 },
		{ year : 2011, value : 42 },
		{ year : 2012, value : 13 },
		{ year : 2013, value : 40 }
	],
	[
		{ year : 2004, value : 50 },
		{ year : 2005, value : 27 },
		{ year : 2006, value : 45 },
		{ year : 2007, value : 58 },
		{ year : 2008, value : 84 },
		{ year : 2009, value : 70 },
		{ year : 2010, value : 45 },
		{ year : 2011, value : 22 },
		{ year : 2012, value : 30},
		{ year : 2013, value : 90 }
	]
]
var margin = svgWidth/(dataSet[0].length - 1); // 꺾은 선 그래프의 간격 계산
drawGraph(dataSet[0], "itemA");   //itemA의 꺽은선 그래프 표시
drawGraph(dataSet[1], "itemB");   //itemB의 꺽은선 그래프 표시
drawGraph(dataSet[2], "itemC");   //itemC의 꺽은선 그래프 표시
drawScale();   //눈금 표시


//꺾은 선 그래프의 좌표를 계산하는 메소드
function drawGraph(dataSet, cssClassName){
   //꺽은선 그래프의 좌표를 계산하는 메소드
   var line = d3.line()   //svg의 선
   .x(function(d,i){               //X 좌표는 표시 순서x 간격
      return  offsetX + i * margin;   //X는 꺽은선 따라 움직이기 때문에 index * margin 으로
   })                           //marign(전체길이/데이터셋갯수나누기) 을 더해준다.)
   .y(function(d,i){
      return svgHeight - (d.value * scale) - offsetY;   //데이터로부터 Y 좌표 빼기
   })                                    //y는고정이라 i(index)안씀.
   
   //꺾은 선 그래프 그리기
   var LineElements = d3.select("#myGraph")
   .append("path")         //데이터 수만큼 path 요소가 추가됨
   .attr("class", "line " + cssClassName)      // CSS 클래스 지정
   .attr("d", line(dataSet))      //연속선 지정
   
}


function drawScale(){

   //눈금을 표시하기 위한 스케일 설정
   var yScale = d3.scaleLinear() // 스케일 설정
   .domain([0,100])   // 원래 크기
   .range([scale*100, 0])   // 실제 출력 크기
   
   //눈금의 표시 위치를 왼쪽으로 지정
   var axis = d3.axisLeft(yScale)

   //눈금을 설정하여 표시
   d3.select("#myGraph").append("g")
   .attr("class", "axis")
   .attr("transform", "translate("+offsetX+", "+offsetY+")")   //눈금을 오프셋 X,Y만큼 움직여준다.
   .call(axis)
   
   // 가로 방향의 선을 표시
   d3.select("#myGraph")
     .append("rect")   
     .attr("class", "axis_x")   
     .attr("width", svgWidth)   
     .attr("height", 1)   // 
     .attr("transform", "translate("+offsetX+", "+(svgHeight-offsetY-0.5)+")")   
   

var xScale = d3.scaleLinear()

	.domain([new Date("2004/1/1"), new Date("2013/1/1")])
	.range([0, svgWidth])
	
//가로 눈금 표시	
var bottomAxis = d3.axisBottom(xScale)
				   .ticks(5)
				   .tickFormat(function(d,i){
					   var formatTime = d3.timeFormat("%Y년%m월");
					   return formatTime(d);
				   })
				   
d3.select("#myGraph")
  .append("g")
  .attr("class", "axis")
  .attr("transform", "translate("+offsetX+", "+(svgHeight - offsetY)+")")
  .call(
		  bottomAxis
  )
   seletAll("text")
   .attr("transform", "rotate(90)")
   .attr("dx", "0.7em")
   .attr("dy","-0.4em")
   .style("text-anchor", "start")
}   
});   // addEventListener() end
```



### 2. 산포도

#### 2-1 기본

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Sample</title>
<script src="https://d3js.org/d3.v5.min.js"></script>

 
<script src="./js/plot1.js"></script>
		<style>
 svg { width : 380px; height: 240px; border: 1px solid black; }
 		.mark { fill: red; stroke: none; }
</style>
	</head>
	<body>
		<h1> 산포도 표시</h1>
		<svg id="myGraph"></svg>
		 
	</body>
</html>

```

```javascript
window.addEventListener("load",function(){
var svgWidth =320;   //SVG요소의 넓이
var svgHeight =240;   //SVG요소의 높이

var dataSet = [
	[30,40], [120, 115], [125, 90], [150, 160], [300, 190],
	[60, 40], [140, 145], [165, 110], [200, 170],[250, 190]
];

//산포도 그리기
var circleElements = d3.select("#myGraph")
		.selectAll("circle")
		.data(dataSet)
		.enter()
		.append("circle") //데이터의 개수만큼 circle 요소가 추가됨
		.attr("class", "mark") // CSS 클래스 지정
		.attr("cx", function(d, i){
			return d[0]; // 최초 요소를 X 좌표로 함
		})
		.attr("cy", function(d, i){
			return svgHeight-d[1] // 2번째의 요소를 Y 좌표로 함
		})
		.attr("r", 5) //반지름을 지정
 
});   // addEventListener() end
```

#### 2-2 산포도 표시 - 일정 시간 간격으로 움직이는 애니메이션

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Sample</title>
<script src="https://d3js.org/d3.v5.min.js"></script>

 
<script src="./js/plot2.js"></script>
		<style>
 svg { width : 380px; height: 240px; border: 1px solid black; }
 		.mark { fill: red; stroke: none; }
</style>
	</head>
	<body>
		<h1> 산포도 표시 - 일정 시간 간격으로 움직이는 애니메이션</h1>
		<svg id="myGraph"></svg>
		 
	</body>
</html>

```

```javascript
window.addEventListener("load",function(){
var svgWidth =320;   //SVG요소의 넓이
var svgHeight =240;   //SVG요소의 높이

var dataSet = [
	[30,40], [120, 115], [125, 90], [150, 160], [300, 190],
	[60, 40], [140, 145], [165, 110], [200, 170],[250, 190]
];

//산포도 그리기
var circleElements = d3.select("#myGraph")
		.selectAll("circle")
		.data(dataSet)
		.enter()
		.append("circle") //데이터의 개수만큼 circle 요소가 추가됨
		.attr("class", "mark") // CSS 클래스 지정
		.attr("cx", function(d, i){
			return d[0]; // 최초 요소를 X 좌표로 함
		})
		.attr("cy", function(d, i){
			return svgHeight-d[1] // 2번째의 요소를 Y 좌표로 함
		})
		.attr("r", 5) //반지름을 지정

		
		function updateData(data){
	var result = data.map(function(d, i){
		var x = Math.random() * svgWidth;
		var y = Math.random() * svgHeight;
		return [x, y];
	})
	return result;
}

function updateGraph(dataSet){
	d3.select("#myGraph").selectAll("*").remove();
	circleElements = d3.select("#myGraph")
						.selectAll("circle")
						.data(dataSet)
	circleElements.enter()
	.append("circle") //데이터의 개수만큼 circle 요소가 추가됨
	.attr("class", "mark") // CSS클래스 지정
	.transition()// cx,cy 애니메이션
	.attr("cx", function(d, i){
		return d[0]; //x좌표를 설정
	})
	.attr("cy", function(d, i){
		return svgHeight-d[1]; //y좌표를 설정
		
	})
	.attr("r", 5) //반지름을 지정
}
// 타이머를 사용하여 2초마다 단위를 변화시킴
setInterval(function(){
	dataSet = updateData(dataSet); //데이터 갱신
	updateGraph(dataSet); //그래프 갱신
}, 2000);
 
});   // addEventListener() end
```

#### 2-3 산포도 표시 - 일정 시간 간격으로 움직이는 애니메이션(눈금 표시)

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Sample</title>
<script src="https://d3js.org/d3.v5.min.js"></script>

 
<script src="./js/plot3.js"></script>
		<style>
 svg { width : 380px; height: 240px; border: 1px solid black; }
 		.mark { fill: red; stroke: none; }
 		.axis text{
 		font-family: sans-serif;
 		font-size: 11px;
 		}
 		.axis path,
 		.axis line {
 		fill: none;
 		stroke: black;
 		}
</style>
	</head>
	<body>
		<h1> 산포도 표시 - 일정 시간 간격으로 움직이는 애니메이션</h1>
		<svg id="myGraph"></svg>
		 
	</body>
</html>

```

```javascript
window.addEventListener("load", function() {
	var svgWidth = 320; // SVG요소의 넓이
	var svgHeight = 240; // SVG요소의 높이
	var offsetX = 30;	// X 
	var offsetY = 20;	// Y 

	var dataSet = [
		[ 30, 40 ], [ 120, 115 ], [ 125, 90 ], [ 150, 160 ],
			[ 300, 190 ], [ 60, 40 ], [ 140, 145 ], [ 165, 110 ], [ 200, 170 ],
			[ 250, 190 ] 
		];

	// 산포도 그리기
	var circleElements = d3.select("#myGraph").selectAll("circle")
			.data(dataSet)

	circleElements.enter().append("circle").attr("class", "mark").attr("cx",
			function(d, i) {
				return d[0]+offsetX;
			}).attr("cy", function(d, i) {
		return svgHeight - d[1]-offsetY;
	}).attr("r", 5)

	function updateData(data) {
		var result = data.map(function(d, i) {
			var x = Math.random() * svgWidth;
			var y = Math.random() * svgHeight;
			return [ x, y ];
		})
		return result;
	}

	function updateGraph(dataSet) {
		d3.select("#myGraph").selectAll("*").remove();
		circleElements = d3.select("#myGraph").selectAll("circle")
				.data(dataSet)
		circleElements.enter().append("circle") // 데이터의 개수만큼 circle 요소가 추가됨
		.attr("class", "mark") // CSS클래스 지정
		.transition()// cx,cy 애니메이션
		.attr("cx", function(d, i) {
			return d[0]+offsetX; // x좌표를 설정
		}).attr("cy", function(d, i) {
			return svgHeight - d[1]-offsetY; // y좌표를 설정

		}).attr("r", 5) // 반지름을 지정
	}

	function drawScale() {
		d3.select("#myGraph").selectAll("g").remove();
		var maxX = d3.max(dataSet, function(d, i) {
			return d[0]; //X 좌표값
		});
		var maxY = d3.max(dataSet, function(d, i) {
			return d[1]; //Y 좌표값
		})
		
		//세로 눈금을 표시하고자 D3 스케일을 지정
		var yScale = d3.scaleLinear()
						.domain([0, maxY])
						.range([maxY, 0])
						
		var axis = d3.axisLeft(yScale);
		  //눈금 표시
		d3.select("#myGraph") // SVG 요소를 지정
		  .append("g") //g요소 추가, 이것이 눈금을 표시하는 요소가 됨
		  .attr("class", "axis") //CSS
		  .attr("transform", "translate("+offsetX+", "+(svgHeight-maxY-offsetY)+")")
		  .call(axis)
		  //가로 눈금을 표시하고나 D3스케일 설정
		 var xScale = d3.scaleLinear() //스케일 설정
		 				.domain([0, maxX]) //원래 데이터 범위
		 				.range([0, maxX]) // 실제 표시 크기
		 				
		var bottomAxis=d3.axisBottom(xScale)// 눈금 표시
		
		d3.select("#myGraph")
		  .append("g")
		   .attr("class", "axis")
		  .attr("transform", "translate("+offsetX+", "+(svgHeight-offsetY)+")")
		  .call(bottomAxis)
	}// drawScale()
	
	//눈금 표시
	drawScale();

	//타이머를 사용하여 2초마다 단위를 변화시킴
	setInterval(function() {
		dataSet = updateData(dataSet); // 데이터 갱신
		updateGraph(dataSet); // 그래프 갱신
		drawScale(dataSet); //눈금 그리기, 수정
	}, 2000);
}); // addEventListener() end

```

#### 2-4 산포도 표시 - 애니메이션+그리드 표현

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Sample</title>
<script src="https://d3js.org/d3.v5.min.js"></script>

 
<script src="./js/plot4.js"></script>
		<style>
 svg { width : 380px; height: 240px; border: 1px solid black; }
 		.mark { fill: red; stroke: none; }
 		.axis text{
 		font-family: sans-serif;
 		font-size: 11px;
 		}
 		.axis path,
 		.axis line {
 		fill: none;
 		stroke: black;
 		}
 		.grid {
 		stroke: gray;
 		stroke-dasharray : 4, 2;
 		shape-rendering : crispEdges;
 		}
</style>
	</head>
	<body>
		<h1> 산포도 표시 - 애니메이션+그리드 표현</h1>
		<svg id="myGraph"></svg>
		 
	</body>
</html>

```

```javascript
window.addEventListener("load", function() {
	var svgWidth = 320; // SVG요소의 넓이
	var svgHeight = 240; // SVG요소의 높이
	var offsetX = 30;	// X 좌표의 오프셋
	var offsetY = 20;	// Y 좌표의 오프셋
	var svg = d3.select("#myGraph");

	var dataSet = [
		[ 30, 40 ], [ 120, 115 ], [ 125, 90 ], [ 150, 160 ],
			[ 300, 190 ], [ 60, 40 ], [ 140, 145 ], [ 165, 110 ], [ 200, 170 ],
			[ 250, 190 ] 
		];

	// 산포도 그리기
	var circleElements = svg.selectAll("circle").data(dataSet)

	circleElements.enter()
	.append("circle")
	.attr("class", "mark")
	.attr("cx",
			function(d, i) {
				return d[0]+offsetX;
			}).attr("cy", function(d, i) {
		return svgHeight - d[1]-offsetY;
	}).attr("r", 5)

	function updateData(data) {
		var result = data.map(function(d, i) {
			var x = Math.random() * svgWidth;
			var y = Math.random() * svgHeight;
			return [ x, y ];
		})
		return result;
	}

	function updateGraph(dataSet) {
		d3.select("#myGraph").selectAll("*").remove(); //본래 요소 모두 삭제
		circleElements = d3.select("#myGraph").selectAll("circle")
				.data(dataSet)
		circleElements.enter().append("circle") // 데이터의 개수만큼 circle 요소가 추가됨
		.attr("class", "mark") // CSS클래스 지정
		.transition()// cx,cy 애니메이션
		.attr("cx", function(d, i) {
			return d[0]+offsetX; // x좌표를 설정
		}).attr("cy", function(d, i) {
			return svgHeight - d[1]-offsetY; // y좌표를 설정

		}).attr("r", 5) // 반지름을 지정
	}

	function drawScale() {
		d3.select("#myGraph").selectAll("g").remove();
		var maxX = d3.max(dataSet, function(d, i) {
			return d[0]; //X 좌표값
		});
		var maxY = d3.max(dataSet, function(d, i) {
			return d[1]; //Y 좌표값
		})
		
		//세로 눈금을 표시하고자 D3 스케일을 지정
		var yScale = d3.scaleLinear()
						.domain([0, maxY])
						.range([maxY, 0])
						
		var axis = d3.axisLeft(yScale);
		  //눈금 표시
		d3.select("#myGraph") // SVG 요소를 지정
		  .append("g") //g요소 추가, 이것이 눈금을 표시하는 요소가 됨
		  .attr("class", "axis") //CSS
		  .attr("transform", "translate("+offsetX+", "+(svgHeight-maxY-offsetY)+")")
		  .call(axis)
		  //가로 눈금을 표시하고나 D3스케일 설정
		 var xScale = d3.scaleLinear() //스케일 설정
		 				.domain([0, maxX]) //원래 데이터 범위
		 				.range([0, maxX]) // 실제 표시 크기
		 				
		var bottomAxis=d3.axisBottom(xScale)// 눈금 표시
		
		d3.select("#myGraph")
		  .append("g")
		   .attr("class", "axis")
		  .attr("transform", "translate("+offsetX+", "+(svgHeight-offsetY)+")")
		  .call(bottomAxis)
		  
		  
		 var grid = svg.append("g")
		 //가로 방향과 세로 방향의 그리드 간격 자동 생성
		 
		 var rangeX = d3.range(50, maxX, 50);
		var rangeY = d3.range(20, maxY, 20);
		//세로 방향 그리드 생성
		
		grid.selectAll("line.y") // line요소의 y 클래스를 선택
			.data(rangeY) //세로 방향의 범위를 데이터셋으로 설정
			.enter()
			.append("line") //line 요소 추가
			.attr("class", "grid") //css 클래스의 grid를 지정
			// (x1, y1)- (x2, y2) 의 좌표값을 설정
			.attr("x1", offsetX)
			.attr("y1", function(d, i){
				return svgHeight - d - offsetY;
			})
			.attr("x2", maxX + offsetX)
			.attr("y2", function(d, i){
				return svgHeight - d - offsetY;
			})
			// 가로 방향의 그리드 생성
			grid.selectAll("line.x") // line요소의 x 클래스를 선택
				.data(rangeX) // 가로 방향의 범위를 데이터셋으로 설정
				.enter()
				.append("line") // line 요소 추가
				.attr("class", "grid") // css 클래스의 grid를 지정
		
			.attr("x1", function(d, i){
				return d + offsetX;
			})
			.attr("y1", svgHeight + offsetY)
			.attr("x2", function(d, i){
				return d + offsetX;
			})
			.attr("y2", svgHeight - offsetY - maxY)
	}// drawScale()
	
	//눈금 표시
	drawScale();

	//타이머를 사용하여 2초마다 단위를 변화시킴
	setInterval(function() {
		dataSet = updateData(dataSet); // 데이터 갱신
		updateGraph(dataSet); // 그래프 갱신
		drawScale(dataSet); //눈금 그리기, 수정
	}, 2000);
}); // addEventListener() end

```

#### 2-5 산포도 표시 -풍선도움말

````html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Sample</title>
<script src="https://d3js.org/d3.v5.min.js"></script>

 
<script src="./js/plot5.js"></script>
		<style>
 svg { width : 380px; height: 240px; border: 1px solid black; }
 		.mark { fill: red; stroke: none; }
 		.axis text{
 		font-family: sans-serif;
 		font-size: 11px;
 		}
 		.axis path,
 		.axis line {
 		fill: none;
 		stroke: black;
 		}
 		.grid {
 		stroke: gray;
 		stroke-dasharray : 4, 2;
 		shape-rendering : crispEdges;
 		}
 		.tip{
 	 position: absoulute;
            top : 0px;
            left : 0px;
            z-index: 9999;
            visibility : hidden;
            border : 1px solid black;
            background-color: yellow;
            width: 80px;
            height: 16px;
            overflow : hidden;
            text-align : center;
            font-size: 9pt;
            font-family : Tahoma, Optima, Helvetica;
            color: black;   
 		
 		}
</style>
	</head>
	<body>
		<h1> 산포도 표시 -풍선도움말</h1>
		<svg id="myGraph"></svg>
		 
	</body>
</html>

````

```javascript
window.addEventListener("load", function() {
	var svgWidth = 320; // SVG요소의 넓이
	var svgHeight = 240; // SVG요소의 높이
	var offsetX = 30;	// X 좌표의 오프셋
	var offsetY = 20;	// Y 좌표의 오프셋
	var svg = d3.select("#myGraph");

	var dataSet = [
		[ 30, 40 ], [ 120, 115 ], [ 125, 90 ], [ 150, 160 ],
			[ 300, 190 ], [ 60, 40 ], [ 140, 145 ], [ 165, 110 ], [ 200, 170 ],
			[ 250, 190 ] 
		];

	// 산포도 그리기
	var circleElements = svg.selectAll("circle").data(dataSet)

	circleElements.enter()
	.append("circle")
	.attr("class", "mark")
	.attr("cx",
			function(d, i) {
				return d[0]+offsetX;
			}).attr("cy", function(d, i) {
		return svgHeight - d[1]-offsetY;
	}).attr("r", 5)

	function updateData(data) {
		var result = data.map(function(d, i) {
			var x = Math.random() * svgWidth;
			var y = Math.random() * svgHeight;
			return [ x, y ];
		})
		return result;
	}

	function updateGraph(dataSet) {
		d3.select("#myGraph").selectAll("*").remove(); //본래 요소 모두 삭제
		circleElements = d3.select("#myGraph").selectAll("circle")
				.data(dataSet)
		circleElements.enter().append("circle") // 데이터의 개수만큼 circle 요소가 추가됨
		.attr("class", "mark") // CSS클래스 지정
		.transition()// cx,cy 애니메이션
		.attr("cx", function(d, i) {
			return d[0]+offsetX; // x좌표를 설정
		}).attr("cy", function(d, i) {
			return svgHeight - d[1]-offsetY; // y좌표를 설정

		}).attr("r", 5) // 반지름을 지정
	}

	function drawScale() {
		d3.select("#myGraph").selectAll("g").remove();
		var maxX = d3.max(dataSet, function(d, i) {
			return d[0]; //X 좌표값
		});
		var maxY = d3.max(dataSet, function(d, i) {
			return d[1]; //Y 좌표값
		})
		
		//세로 눈금을 표시하고자 D3 스케일을 지정
		var yScale = d3.scaleLinear()
						.domain([0, maxY])
						.range([maxY, 0])
						
		var axis = d3.axisLeft(yScale);
		  //눈금 표시
		d3.select("#myGraph") // SVG 요소를 지정
		  .append("g") //g요소 추가, 이것이 눈금을 표시하는 요소가 됨
		  .attr("class", "axis") //CSS
		  .attr("transform", "translate("+offsetX+", "+(svgHeight-maxY-offsetY)+")")
		  .call(axis)
		  //가로 눈금을 표시하고나 D3스케일 설정
		 var xScale = d3.scaleLinear() //스케일 설정
		 				.domain([0, maxX]) //원래 데이터 범위
		 				.range([0, maxX]) // 실제 표시 크기
		 				
		var bottomAxis=d3.axisBottom(xScale)// 눈금 표시
		
		d3.select("#myGraph")
		  .append("g")
		   .attr("class", "axis")
		  .attr("transform", "translate("+offsetX+", "+(svgHeight-offsetY)+")")
		  .call(bottomAxis)
		  
		  
		 var grid = svg.append("g")
		 //가로 방향과 세로 방향의 그리드 간격 자동 생성
		 
		 var rangeX = d3.range(50, maxX, 50);
		var rangeY = d3.range(20, maxY, 20);
		//세로 방향 그리드 생성
		
		grid.selectAll("line.y") // line요소의 y 클래스를 선택
			.data(rangeY) //세로 방향의 범위를 데이터셋으로 설정
			.enter()
			.append("line") //line 요소 추가
			.attr("class", "grid") //css 클래스의 grid를 지정
			// (x1, y1)- (x2, y2) 의 좌표값을 설정
			.attr("x1", offsetX)
			.attr("y1", function(d, i){
				return svgHeight - d - offsetY;
			})
			.attr("x2", maxX + offsetX)
			.attr("y2", function(d, i){
				return svgHeight - d - offsetY;
			})
			// 가로 방향의 그리드 생성
			grid.selectAll("line.x") // line요소의 x 클래스를 선택
				.data(rangeX) // 가로 방향의 범위를 데이터셋으로 설정
				.enter()
				.append("line") // line 요소 추가
				.attr("class", "grid") // css 클래스의 grid를 지정
		
			.attr("x1", function(d, i){
				return d + offsetX;
			})
			.attr("y1", svgHeight + offsetY)
			.attr("x2", function(d, i){
				return d + offsetX;
			})
			.attr("y2", svgHeight - offsetY - maxY)
	}// drawScale()
	
	//눈금 표시
	drawScale();
	
	var tooltip = d3.select("body")
		.append("div")
		.attr("class", "tip")
		
	function showTooltip(){
		circleElements= d3.select("#myGraph")
							.selectAll("circle")
		circleElements.on("mouseover", function(d){
			var x = parseInt(d[0]); //원의 x 좌표값
			var y = parseInt(d[1]);// 원의 y 좌표값
			var data = d3.select(this).datum(); //요소의 데이터를 읽어옴
			var dx = parseInt(data[0]); //원의 x좌표. 변수 x와 같은 값
			var dy = parseInt(data[1]);//원의 y좌표. 변수 y와 같은 값
			tooltip
				.style("left", offsetX + x + "px")
				.style("top", svgHeight + offsetY - y +"px")
				.style("visibility", "visible") //풍선 도움말을 표시
				.text(dx+", "+dy)
		})
		circleElements.on("mouseout", function(){
			tooltip.style("visibility", "hidden") // 풍선 도움말을 숨김
		})
	}
	showTooltip()

	//타이머를 사용하여 2초마다 단위를 변화시킴
	setInterval(function() {
		dataSet = updateData(dataSet); // 데이터 갱신
		updateGraph(dataSet); // 그래프 갱신
		drawScale(); //눈금 그리기, 수정
		showTooltip();
	}, 2000);
}); // addEventListener() end

```

### 3. treemap

```html
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title> D3 v5 hierarchy treemap </title>
<script src="https://d3js.org/d3.v5.min.js"></script>

<script src="./js/treemap1.js"></script>
		<style>
		</style>
</head>
<body>
<svg width="800" height="600"></svg>
</body>
</html>
```

```javascript
window.addEventListener("load", function() {
var width = document.querySelector("svg").clientWidth;
var height = document.querySelector("svg").clientHeight;
var data = {
		"name": "A",
		"children":[
			{"name":"B", "value": 25},
			{
				"name":"C",
				"children":[
					{"name":"D", "value": 10},
					{"name":"E", "value": 15},
					{"name":"F", "value": 10},
				]
			},
			{"name":"G", "value": 15},
			{
				"name":"H",
				"children":[
					{"name":"I", "value": 20},
					{"name":"J", "value": 10},
				]
			},
			{"name":"K", "value": 10},
		]
};

root=d3.hierarchy(data);
root
	.sum(function(d) { return d.value; })
	.sort(function(a,b) { return b.height - a.height 
												|| b.value - a.value; });

var treemap = d3.treemap()
	.size([width, height])
	.padding(1)
	.round(true);

treemap(root);


var g = d3.select("svg")
	.selectAll(".node")
	.data(root.leaves())
	.enter()
	.append("g")
	.attr("class", "node")
	.attr("transform", function(d) { return "translate("+d.x0+","+(d.y0)+")";});
	
	g.append("rect")
	 .style("width", function(d) { return d.x1 - d.x0 })
	 .style("height", function(d) { return d.y1 - d.y0;})
	 .style("fill", function(d){
		 while(d.depth > 1) d=d.parent;
		 return d3.schemeCategory10[parseInt(d.value % 7)];
		 
	 }) 
	 .style("opacity", 0.6)
	 
	 g.append("text")
	 	.attr("text-anchor", "start")
	 	.attr("x", 5)
	 	.attr("dy", 30)
	 	.attr("font-size", "150%")
	 	.attr("class", "node-label")
	 	.text(function(d) { return d.data.name + " : "+d.value; });

}); // addEventListener() end

```

### 4. Map

```javascript
window.addEventListener("load", function() {
var width = 760;
var height = 500;

var svg = d3.select("#chart").append("svg")
	.attr("width", width)
	.attr("height", height);

var projection = d3.geo.mercator()
	.center([128, 36])
	.scale(4000)
	.translate([width/2, height/2]);

var path = d3.geo.path()
	.projection(projection);

var quantize = d3.scale.quantize() //양자화
	.domain([0, 1000])
	.range(d3.range(9).map(function(i) { return "p" + i; }));

var popByName = d3.map(); //지도 레이아웃

queue()
	.defer(d3.json, "./datas/municipalities-topo-simple.json")
	.defer(d3.csv, "./datas/population.csv", function(d){ //데이터 읽어들이기
		popByName.set(d.name, +d.population);
	})
	.await(ready);

function ready(error, data){
	var features = topojson.feature(data, data.objects["municipalities-geo"]).features;
	
	features.forEach(function(d) {
		d.properties.population = popByName.get(d.properties.name);
		d.properties.density = d.properties.population / path.area(d);
		d.properties.quantized = quantize(d.properties.density);
	});
	svg.selectAll("path")
 	.data(features)
 	.enter().append("path")
 		.attr("class", function(d){ return "municipality "+ d.properties.quantized;})
 		.attr("d",path)
 		.attr("id", function(d) { return d.properties.name; })
 			.append("title")
 			.text(function(d) { return d.properties.name + ":"+d.properties.population/10000 + "만 명"});
	
	svg.selectAll("text")
	.data(features.filter(function(d){
		return d.properties.name.endsWith("시");ㅣ
	}))
	.enter().append("text")
	.attr("transform", function(d) { return "translate(" + path.centroid(d)+ ")" })
	
	.attr("dy", ".35em")
	.attr("class", "region-label")
	.text(function(d) { return d.properties.name;})
 	
}

})
```

```html

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
 <style>
    svg { background-color: #eee; }
    svg .municipality { fill: red; }
    svg .municipality:hover { stroke: #333; }
    svg .municipality.p0 { fill: rgb(247,251,255); }
    svg .municipality.p1 { fill: rgb(222,235,247); }
    svg .municipality.p2 { fill: rgb(198,219,239); }
    svg .municipality.p3 { fill: rgb(158,202,225); }
    svg .municipality.p4 { fill: rgb(107,174,214); }
    svg .municipality.p5 { fill: rgb(66,146,198); }
    svg .municipality.p6 { fill: rgb(33,113,181); }
    svg .municipality.p7 { fill: rgb(8,81,156); }
    svg .municipality.p8 { fill: rgb(8,48,107); }
    svg text { font-size: 10px; }
    </style>
    <script src="http://d3js.org/d3.v3.min.js"></script>
    <script src="http://d3js.org/topojson.v1.min.js"></script>
    <script src="http://d3js.org/queue.v1.min.js"></script>
    <script src="./js/map1.js"></script>
</head>
<body>
  <div id="chart"></div>
</body>
</html>
```

