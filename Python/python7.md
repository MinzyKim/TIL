# 파이썬 7일차

## 1. CRUD

- Create
  - New/ 폼을 보여줌
  - create/submit/ 했을때 저장
- Read
  - index에서 모든 글 리스트를 보여주고
  - 해당 글 리스트에서 제목을 클릭하면 자세한 정보를 보여줌(detal)
- Update
  - detail페이지에서 수정하기 버튼으로 접근
  - form에서 해당 article정보를 채워둔채로 보여주기
  - 수정하면 detail 페이지로 돌아가 수정한 결과를 보여줌
- Delete
  - Detail 페이지에서 삭제하기 버튼으로 접근
  - 삭제하면 index페이지로 리다이렉트



## 2. 코드

- `views.py`

  ```python
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
  ```