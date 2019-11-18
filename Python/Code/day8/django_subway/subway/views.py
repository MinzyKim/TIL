from django.shortcuts import render, redirect
from .models import Subway

# Create your views here.
def index(request):
    subways=Subway.objects.all()

    context={
        'subways':subways
    }
    return render(request, 'subway/index.html', context)

def new(request):
    return render(request, 'subway/new.html')

def create(request):
    name=request.POST.get('name')
    address=request.POST.get('address')
    phone=request.POST.get('phone')
    menu=request.POST.get('menu')
    bread=request.POST.get('bread')
    sauce=request.POST.get('sauce')
    veg=request.POST.get('vegetable')
    drink=request.POST.get('drink')

    subway=Subway()
    subway.name= name
    subway.address=address
    subway.phone =phone
    subway.menu = menu
    subway.bread=bread
    subway.sauce=sauce
    subway.vegetable=veg
    subway.drink=drink

    subway.save()

    return redirect('/')

def detail(request,id):
    sub = Subway.objects.get(id=id)

    context={
        'sub':sub
    }
    return render(request, 'subway/detail.html', context)

def edit(request, id):
    subway = Subway.objects.get(id=id)
    
    context={
        'subway':subway
    }
    return render(request, 'subway/update.html',context)

def update(request,id):
    name=request.POST.get('name')
    address=request.POST.get('address')
    phone=request.POST.get('phone')
    menu=request.POST.get('menu')
    bread=request.POST.get('bread')
    sauce=request.POST.get('sauce')
    veg=request.POST.get('veg')
    drink=request.POST.get('drink')

    subway=Subway()
    subway.name= name
    subway.address=address
    subway.phone =phone
    subway.menu = menu
    subway.bread=bread
    subway.sauce-sauce
    subway.vegetable=veg
    subway.drink=drink

    subway.save()

    return redirect(f'{subway.id}/detail/')

def delete(request,id):
    sub=Subway.objects.get(id=id)

    sub.delete()

    return redirect('/')