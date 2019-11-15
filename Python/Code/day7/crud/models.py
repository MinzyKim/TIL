from django.db import models

# Create your models here.

class Article(models.Model):
    title = models.CharField(max_length=30) # 최대 글자수 =10
    content = models.TextField() # 텍스트 부분은 글자제한이 없도록
    created_at = models.DateTimeField(auto_now_add=True) # 오늘 날짜로 수정된 사항이 저장되게만들기
    updated_at = models.DateTimeField(auto_now=True)# 수정될 때 시간이 저장