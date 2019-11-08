# 3일차 파이썬 - 텔레그램 챗봇 만들기

## 1. 기본세팅

- `app.py` 

```python
#-*- coding: utf-8 -*-
from flask import Flask,render_template,request
import requests
from decouple import config
from pprint import pprint
import random

app=Flask(__name__)

token = config("TOKEN")
base_url=f"https://api.telegram.org/bot{token}"

@app.route("/telegram")
def telegram():
    res = requests.get(f"{base_url}/getUpdates").json()
    id = res["result"][0]["message"]["chat"]["id"]

    number = range(1,45)
    lotto = random.sample(number,6)

    requests.get(f"{base_url}/sendMessage?chat_id={id}&text={lotto}")

    return ""

@app.route("/chat")
def chat():
    return render_template("chat.html")

@app.route("/send_msg")
def send_message():
    req = request.args.get("chat")

    res = requests.get(f"{base_url}/getUpdates").json()
    id = res["result"][0]["message"]["chat"]["id"]

    requests.get(f"{base_url}/sendMessage?chat_id={id}&text={req}")

    return "성공"

  @app.route('/', methods=['POST']) # 포스트 방식으로 받기!
def tel_web():
    req = request.get_json().get('message') # reqeusts는 주고 받기용, 받는 용은 request
 
    if req is not None:
        chat_id=req.get('chat').get('id')
        lotto = random.sample(range(1,47), 6)
            if req == '로또':
            ext=req.get('lotto')
            else:
            text=req.get('text')
    print(chat_id, text)
   
    return '', 200
  
if __name__=="__main__":
    app.run(debug=True, port=8000) #자동으로 디버그,포트번호 설정
```



- `chat.html`

```python
<form action="/send_msg" method="GET">
    채팅내용 : <input type="text" name="chat">
    <input type="submit" value="보내기">
</form>
```



- .`env`

```python
TOKEN="myToken"
```



- `Set_webhook.py`

```python
from decouple import config
import requests
from pprint import pprint

token = config("TOKEN")
base_url=f"https://api.telegram.org/bot{token}"
url = "5ebe3716.ngrok.io"
setweb_url = f'/setWebhook?url={url}'

req = requests.get(base_url+setweb_url).json()

pprint(req)
```

```bash
(venv) ip-70-12-227-176:test minji$ python set_webhook.py
{'description': 'Webhook was set', 'ok': True, 'result': True}

# Webhook was set을 확인하면 완료
```

``` bash
ngrok 설치 후
터미널에 ngrok http {port번호}
```



## 2. 로또번호 보내기('로또'라고 보내면 로또번호가, 아니면 메세지)

```python
@app.route('/', methods=['POST']) # 포스트 방식으로 받기!
def tel_web():
    req = request.get_json().get('message') # reqeusts는 주고 받기용, 받는 용은 request
    text=req.get('text')
    if req is not None:
        chat_id=req.get('chat').get('id')
        lotto = random.sample(range(1,47), 6)
        if req.get('text') == '로또':
          
           requests.get(f"{base_url}/sendMessage?chat_id={chat_id}&text={lotto}")
        else:
            requests.get(f"{base_url}/sendMessage?chat_id={chat_id}&text={text}")
        print(chat_id, text)
   
    return '', 200

```



### 3. 파파고 API 이용해서 텔레그램에 적용하기

```python
@app.route('/', methods=['POST']) # 포스트 방식으로 받기!
def tel_web():

    C_ID=config('C_ID')
    C_SC=config('C_SC')

    url = "https://openapi.naver.com/v1/papago/n2mt"

    headers = {
       "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
       "X-Naver-Client-Id": C_ID,
       "X-Naver-Client-Secret":  C_SC
    }

    req = request.get_json().get('message') # reqeusts는 주고 받기용, 받는 용은 request
    text=req.get('text')

    if "/번역" in text:
        re_txt=text.replace("/번역","")
        data={
            "source":"ko",
            "target":"en",
            "text":re_txt
            }
        res=requests.post(url, headers=headers, data=data).json() 
        msg=res.get('message').get('result').get('translatedText')

    if req is not None:
        chat_id=req.get('chat').get('id')
        lotto = random.sample(range(1,47), 6)
        if req.get('text') == '로또':  
           requests.get(f"{base_url}/sendMessage?chat_id={chat_id}&text={lotto}")
      
        else:
            requests.get(f"{base_url}/sendMessage?chat_id={chat_id}&text={msg}")
        print(chat_id, text)
   
    return '', 200

@app.route('/papago')
def papago():
    C_ID=config('C_ID')
    C_SC=config('C_SC')

    url = "https://openapi.naver.com/v1/papago/n2mt"

    headers = {
       "Content-Type": "application/x-www-form-urlencoded; charset=UTF-8",
       "X-Naver-Client-Id": C_ID,
       "X-Naver-Client-Secret":  C_SC
    }
    
    data={
        "source":"ko",
        "target":"en",
        "text":"안녕하세요"
    }

    req=requests.post(url, headers=headers, data=data).json()

    print(req)

    return "Finish!"
```

