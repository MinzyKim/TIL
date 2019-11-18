from django.db import models

# Create your models here.
class Subway(models.Model):
    name= models.TextField() 
    address = models.TextField() 
    phone= models.TextField() 
    menu = models.TextField() 
    bread = models.TextField() 
    vegetable = models.TextField() 
    sauce = models.TextField() 
    drink = models.TextField() 
    created_at =models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def __str__(self):
        return f'{self.id}. {self.name} : {self.menu}'