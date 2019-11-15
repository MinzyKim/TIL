from django.db import models

# Create your models here.
class Board(models.Model):
    title = models.CharField(max_length=10)
    content = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f'{self.id} : {self.title}' # 함수정의는 테이블변경이 아니기 때문에 makemigrate안해도 됨

class Subway(models.Model):
    name = models.CharField(max_length=10)
    date = models.TextField()
    sandwich=models.TextField()
    bread=models.TextField()
    size=models.CharField(max_length=10)
    source=models.TextField()

    def __str__(self):
        return f'{self.id} : {self.name}, {self.date}, {self.sandwich}, {self.size}, {self.bread}, {self.source}'
    
        