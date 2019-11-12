# 파이썬 4일차

## 1. Django 사용하기

1. Django 폴더 만들기


![image-20191111142359083](/Users/minji/Library/Application Support/typora-user-images/image-20191111142359083.png)

2. Visual Studio.app에서 스위치 워크스페이스 -> 세팅 준비
3. 터미널(`Ctrl+~`) 창 열어서 가상환경 세팅

```bash
virtualenv env
source venv/bin/activate
pip list
```

4. Django설치

```bash
pip install django
Successfully installed django-2.2.7 pytz-2019.3 sqlparse-0.3.0
```

5. 프로젝트 수행할 폴더 생성 - Django가 자동으로 세팅해줌

```bash
cd djangoex/ -> 생성한 폴더
django-admin startproject config . -> 이 폴더에서 프로젝트를 시작하겠다.
```

6. Pages폴더 생성 후 앱을 시작

```bash
python manage.py startapp pages
```

![image-20191111142939543](/Users/minji/Library/Application Support/typora-user-images/image-20191111142939543.png)

7. 세팅된거 확인, 이제 어떤 동작할지 설정



## 3. 디버깅할 때 순서

1. Urls.py에 에러가 없는지 볼 것
2. Views.py
3. Settings.py
4. manage.py



## 4. Django Built-in langauge

```
https://docs.djangoproject.com/en/2.2/ref/templates/builtins/
```



### 5. MVT방식

![img](https://naditya.azurewebsites.net/wp-content/uploads/2017/03/Django-Template-214x300.png)

- `urls` 에 있는 이름과 `Views`의 함수명이 일치돼야 한다!



## 6. 오늘 실습

- `urls.py`

```python
"""config URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""
from django.contrib import admin
from django.urls import path
from pages import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('image/', views.images),
    path('dtl/', views.dtl),
    path('isyourbirth/', views.birth),
]

```



- `Views.py`

```python
from django.shortcuts import render
from django.http import HttpResponse #  Text를 보낼 때
from datetime import datetime
import random
# Create your views here.

def images(request):
    num=random.choice(range(1,100))
    url=f""

    context={
        'url':url
    }

def dtl(request):
    foods=["짜장면","탕수육", "짬뽕", "볶음밥", "양장피"]
    my_sentence='life is short, you need python'
    messages=['apple', 'banana', 'cucumber','mango']
    datetimenow = datetime.now()
    empty_list=[]

    context={
        "foods":foods,
        "my_sentence":my_sentence,
        "messages":messages,
        "timenow":datetimenow,
        "empty_list":empty_list,
    }
    return render(request, 'dtl.html', context)

def birth(request):
    today=datetime.now()

    if today.month == 7 and today.date == 28:
        res=True
    else:
        res=False
 
    birth = datetime(2020, 7, 28)

    d_day = (today-birth).days

    context={
        "result":res,
        'd_day':d_day
    }

    return render(request, 'isyourbirth.html', context)
```



- `template`/`dtl.html`

```python
<!-- templates/dtl.html -->
<h3>1. 반복문</h3>
{% for f in foods %}
    <p>{{ f }}</p>
{% endfor %}
<hr>
{% for f in foods %}
    <p>{{ forloop.counter }}. {{ f }}</p>
{% endfor %}
<hr>
{% for user in empty_list %}
    <p>{{ user }}</p>
    <!-- empty : for 태그안에 아무것도 없을 때 밑에 설정된 값이 출력-->
{% empty %}
    <p>현재 가입한 유저가 없습니다.</p>
{% endfor %}
<hr>
<h3>2. 조건문</h3>
{% if '짜장면' in foods %}
    <p>짜장면엔 단무지 최고!</p>
{% endif %}

<h3>7. 연산</h3>
<p>{{ 4|add:6 }}</p>
<hr>

<h3>8. 날짜 표현</h3>
{{ timenow }}<br>
{% now "DATETIME_FORMAT" %}<br>
{% now "SHORT_DATETIME_FORMAT %}<br>
{% now "DATE_FORMAT %}<br>
{% now "SHORT_DATE_FORMAT %}<br>
<hr>
{% now "Y년 m월 d일 (D) h:i" %}<br>

<h3>9. 하이퍼링크</h3>
{{ 'google.com'|urlize }}
```



![image-20191111171438351](/Users/minji/Library/Application Support/typora-user-images/image-20191111171438351.png)



