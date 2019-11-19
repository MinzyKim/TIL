from django.db import models

# Create your models here.
class Question(models.Model):
    title = models.CharField(max_length=50)
    created_at=models.DateTimeField(auto_now_add=True)
    updated_at=models.DateTimeField(auto_now=True)

    def __str__(self):
        return f'{self.id} : {self.title}'

class Choice(models.Model):
    survey=models.CharField(max_length=100)
    question=models.ForeignKey(Question, on_delete=models.CASCADE)
    created_at=models.DateTimeField(auto_now_add=True)
    updated_at=models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.survey
