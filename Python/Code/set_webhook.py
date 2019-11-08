from decouple import config
import requests
from pprint import pprint

token = config("TOKEN")
base_url=f"https://api.telegram.org/bot{token}"
url = "mjkim.pythonanywhere.com/" # 메세지가 도착하면 이 주소로 보내기
setweb_url = f'/setWebhook?url={url}'

req = requests.get(base_url+setweb_url).json()

pprint(req)