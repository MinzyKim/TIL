# 파이썬 5일차

## 1. Form

- form : action - 데이터가 전송될 URL, method - GET/POST
- input : name(데이터 넘길 때, view에서 이 설정값을 가져올 수 있음)
  - name : key / value : value
- label : for  옵션 input의 id값과 매치(이름표)



## 2. GET

- data가 body를 통한게 아니라 쿼리스트링으로 넘어감

- 데이터를 조회할 때 사용

  ## *실습

  - 설정순

  `urls.py`

  ```python
  from django.contrib import admin
  from django.urls import path
  from pages import views
  
  urlpatterns = [
      path('admin/', admin.site.urls),
      path('throw/', views.throw),
      path('catch/', views.catch),
      path('lotto/', views.lotto),
      path('lotto_result/', views.lotto_result),
  ]
  ```

  `views.py`

  ```python
  from django.shortcuts import render
  import random
  
  # Create your views here.
  def throw(request):
      return render(request, 'throw.html')
  
  def catch(request):
      # print(request)
      # print(request.path)
      # print(request.method)
     
      # print(request.META)
      message = request.GET.get('message')
      message2 = request.GET.get('message2')
  
      context={
          'msg':message,
          'msg2':message2
      }
  
      return render(request, 'catch.html', context)
  
  def lotto(request):
      return render(request, 'lotto.html')
  
  def lotto_result(request):
      count = int(request.GET.get('count'))
      print(type(count))
      lotto_num=[]
      for i in range(count):
          lotto_num.append(random.sample(range(1,47), 6))
  
          context = {
              'count':count,
              'lotto_num':lotto_num
          }
      return render(request, 'lotto_result.html', context)
  
  ```

  

  `throw.html`

  ```html
  <form action="/catch/" method="GET">
      <label for="msg"></label>
      <input type="text" name="message" id="msg">
      <label for="msg2"></label>
      <input type="text" name="message2" id="msg2">
      <input type="submit">
  </form>
  ```

  `catch.html`

   ```html
  <h1> 받을 내용 : {{ msg }} // {{ msg2 }}</h1>
   ```

  

  `lotto.html`

  ```html
  <form action="/lotto_result/" method="GET">
      <label for="count">몇개 살거?</label>
      <input type="text" name="count" id="count">
      <input type="submit">
  </form>
  ```

  

  `lotto_result.html`

  ```html
  <h1>{{ count }}개 구매함.</h1>
  
  <ol>
  {% for lotto in lotto_num %}
      <li>{{ lotto }}</li>
  {% endfor %}
  </ol>
  ```

  

## 3. 과제

```
ASKII ART API를 이용한 실습

1. 입력한 Text를 Artii api를 이용하여 화면에 출력
 - 텍스트 받기 위해 form필요
 - form date를 받아서 artii에 요청
 		- requests 모듈 이용
 		- import requests
 		- request.get('요청할 곳 URL')
 		- 요청할 URL 분석
 		- requests로 받은 값을 그대로 html에 뿌려준다.
```

- `artii`, `artii_result`

```python
def artii(request):
    return render(request, 'artii.html')

def artii_result(request):
    message = request.GET.get('msg')
    url=f'http://artii.herokuapp.com/make?text={message}&font=trek'

    url_result=requests.get(url)
    context={
        'url_result':url_result.text # text로 바꿔주는 것
    }

    return render(request, 'artii_result.html', context)
```

- `artii.html`

```html
<form action="/artii_result/" method="GET">
<label for="msg">메세지 입력</label>
<input type="text" name="msg" id="msg">
<input type="submit">
</form>
```

- `artti_result.html`

```html
<h1>아스키 코드</h1>
<pre>{{ url_result }}</pre>
<!-- pre 태그는 있는 그대로 가져올 때 사용하는 태그 -->
<!-- 그래서 context로 받아올 때 text로 바꿔줘야 한다. -->
```

