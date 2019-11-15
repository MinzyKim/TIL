from django.shortcuts import render
from boards.models import Subway

# Create your views here.
def index(request):
    return render(request, "boards/index.html")

def subway_form(request):
    return render(request, 'boards/subway_form.html')

def subway_result(request):
    username=request.POST.get('name')
    date=request.POST.get('date')
    sandwich=request.POST.get('sandwich')
    size=request.POST.get('size')
    bread=request.POST.get('bread')
    source=request.POST.getlist('source')

    subway=Subway.objects.create(name=username, date=date, sandwich=sandwich, 
                                size=size, bread=bread, source=source)
    subway.save()
    subway=Subway.objects.all()
    print(subway)

    context={
       'subway':subway
    }
    return render(request, 'boards/subway_result.html',context)