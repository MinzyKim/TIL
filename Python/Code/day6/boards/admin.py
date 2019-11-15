from django.contrib import admin
from .models import Board, Subway

# Register your models here.
class BoardAdmin(admin.ModelAdmin):
    fields = ['content', 'title'] # Content랑 title 반대로 넣기
    list_display=["title", "updated_at"] 
    list_filter=["updated_at"]
    search_fields = ["title", "content"]

admin.site.register(Board, BoardAdmin)
admin.site.register(Subway)