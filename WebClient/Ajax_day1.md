## 1.Ajax - javascript

- 큰 특징 두 가지
  - 비동기 통신
  - (페이지)부분 갱신

- javascript로 txt파일 불러오기, json파일 불러오기

```javascript
	<script type="text/javascript">
	 	window.onload = function(){  //이벤트 발생
	 		var req = new XMLHttpRequest(); //1. XMLHttpRequest
	 		req.onloadstart = function(){
	 			console.log("loadstar : 요청을 보낼 때");
	 		}
	 		req.onload = function(){
	 				console.log("load : 요청 성공, 응답 가져올 수 있을 때");
	 		}
	 			req.onloadend = function(){
	 				console.log("loadend : 요청 완료");
	 		}
	 			req.onprogress = function(){
	 				console.log("progress : 데이터를 주고 받을 때");
	 		}

	 		req.onreadystatechange = function(){
	 			if(req.readyState == 4){
	 				if(req.status == 200){
	 			
	 			document.getElementById("image_container").innerHTML=req.responseText;
	 		}
	 		}
	 		} //응답이 왔을 때 처리할 함수 지정
	 		req.open("GET", "images.jsp"); //원래 주소를 써야되는데 같은 페이지 안에 있으면 이름만 써도 됨
	 		//2. 요청 보낼 준비
	 		req.send(null); //3. 요청 보냄

	 	};
	</script>
```

**핵심**은 이것

```javascript
window.onload = function(){
	req=new XMLHttpRequest();
    req.onreadystatechange = resultProcess;
	req.open("get", url, "true");
	//req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    //위에건 post방식에서만 사용
	req.send(null);
    
    function resultProcess(){
    f(req.readyState==4){
		if(req.status==200){
			confirmedProcess();
            //성공했을 때 수행해야 할 것 여기 적으면 됨
}
    
```



*XML - 이 기종간에 데이터 통신 위한 마크업랭귀지*

*json - {"속성명":"속성값"}*



## 

## 2. Ajax - jQuery

```javascript
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $.ajax({
                url: "data.txt",
                success : function(data){
                    $("view").html(data);
                }
            });
            });
    	
    </script>
```

```javascript
var $imageContainer 	= $("#image_container");
		$imageContainer.append(strDOM);
		
		$(document).ajaxComplete(function(){
			console.log("ajax event : complete");			
		});
		$(document).ajaxSend(function(){
			console.log("ajax event : send");			
		});
		$(document).ajaxStart(function(){
			console.log("ajax event : start");			
		});
		$(document).ajaxSuccess(function(){
			console.log("ajax event : success");			
		});
	}	
```





