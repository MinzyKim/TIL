# 1일차 파이썬 기본

```python
#-*- coding: utf-8 -*-
from flask import Flask,render_template,request
import requests
from decouple import config
from pprint import pprint
import random

app=Flask(__name__)

# @app.route('/')
# def hello():
#     name="world!!"
#     return f'Hello {name}'

@app.route('/mulcam')
def mulcam():
    return 'Hello multicampus~'

@app.route('/greeting/<string:name>')
def greeting(name):
    return f'{name}님 안녕하세요.'

@app.route('/lunch/<int:num>')
def lunch(num):
    menu=["낙곱새", "차돌된장찌개", "생고기김치찌개", "한우불고기"]
    order= random.sample(menu, num)
    return render_template('menu.html', menu=order)

@app.route('/lotto')
def lotto():
    random_num=random.sample(range(1,46),6)
    return str(random_num)
    

@app.route('/send')
def send():
    return render_template('send.html')

@app.route('/receive')
def receive():
    name=request.args.get('name')
    message=request.args.get('message')
    return render_template('receive.html', name=name, msg=message)


@app.route('/indian')
def indian():
    return render_template('indian.html')

@app.route('/indian_result')
def result():
    list_1 = []
    list_2 = []
    list_3 = []

    name=request.args.get("name")
    l1 = random.choice(list_1)
    l2 = random.choice(list_2)
    l3 = random.choice(list_3)

    res = f'{name} 의 인디언 네임은 {l1+l2+l3}입니다.'
    return render_template('reslut.html', name="name")

@app.route('/lotto_get')
def lotto_get():
    return render_template('lotto_get.html')

@app.route('/lotto_num')
def lotto_num():
    num=request.args.get("num")
    url=f"https://www.dhlottery.co.kr/common.do?method=getLottoNumber&drwNo={num}"
    res=requests.get(url).json()
    # List comprehension
    # [ 받는 변수 for  받는변수 in 범위로된데이터]
    wnum = [res[f'drwtNo{i}'] for i in range(1, 7)]
    lotto = random.sample(range(1,47), 6)
    print(lotto)
    match = list(set(wnum) & set(lotto))   
    count = len(match)
    msg =""
    if count == 6:
        msg="1등 입니다~~~"
    elif count == 5:
       msg="2등 입니다~~~"
    elif count == 4:
        msg="3등 입니다~~~"
    elif count == 3:
        msg="4등 입니다~~~"
    else:
       msg="꽝 입니다~~~"
    

    print(match)

    return render_template("lotto_result.html", num=num, wnum=wnum, msg=msg, lotto=lotto)

```

