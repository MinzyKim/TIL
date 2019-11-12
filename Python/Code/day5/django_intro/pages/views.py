from django.shortcuts import render
import random
import requests

# Create your views here.
def throw(request):
    return render(request, 'pages/throw.html')

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

    return render(request, 'pages/catch.html', context)

def lotto(request):
    return render(request, 'pages/lotto.html')

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
    return render(request, 'pages/lotto_result.html', context)

def artii(request):
    return render(request, 'pages/artii.html')

def artii_result(request):
    message = request.GET.get('msg')
    f_url='http://artii.herokuapp.com/fonts_list'
    fonts=requests.get(f_url).text
    fonts = fonts.split('\n')
    
    font=random.choice(fonts)
    url=f'http://artii.herokuapp.com/make?text={message}&font={font}'

    url_result=requests.get(url)
    context={
        'url_result':url_result.text
    }

    return render(request, 'pages/artii_result.html', context)

def user_new(request):
    return render(request, 'pages/user_new.html')

def user_create(request):
    username=request.POST.get('name')
    passward=request.POST.get('pw')

    context={
        'username':username,
        'passward':passward
    }
    return render(request, 'pages/user_create.html', context)
    
def subway_form(request):
    return render(request, 'pages/subway_form.html')

def subway_result(request):
    username=request.POST.get('name')
    date=request.POST.get('date')
    sandwich=request.POST.get('sandwich')
    size=request.POST.get('size')
    bread=request.POST.get('bread')
    source=request.POST.getlist('source')

    context={
        'username':username,
        'date':date,
        "sandwich":sandwich,
        'size':size,
        'bread':bread,
        'source': ", ".join(source)
    }
    return render(request, 'pages/subway_result.html',context)

def static_ex(request):
    return render(request, 'pages/static.html')

def index(request):
    return render(request,'pages/index.html')