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