from django.shortcuts import render
from .models import Article

# Create your views here.
def index(request):

    articles = Article.objects.order_by('-id')

    context={
        "articles":articles
    }
    return render(request, 'crud/index.html', context)

def new(request):
    return render(request, 'crud/new.html')

def create(request):
    title = request.POST.get('title')
    content=request.POST.get('content')

    article=Article()
    article.title = title
    article.content=content
    article.save()

    return render(request, 'crud/create.html')

def detile(request,pk):
    article = Article.objects.get(pk=pk)

    context={
        "articles":articles
        }

    return render(request, 'crud/index.html', context)
  
def update(request):
    article = Article.objects.get(pk=pk)

    context={
        "articles":articles
        }

    return render(request, 'crud/update.html', context)

def revise(request, pk):
    article=Article.objects.get(pk=pk)

    title = request.POST.get('title')
    content = request.POST.get('content')

    article.title = title
    article.content = content
    artilce.save()

    return redirect(f'/crud/{article.id}/article/')

def delete(request, pk):
    article = Article.objects.get(pk=pk)
    article.delete()
    return redircet('/crud/')