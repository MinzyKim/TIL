from django.urls import path
from . import views

app_name="subway"

urlpatterns=[
    path('', views.index, name="index"),
    path('new/', views.new, name="new"),
    path('create/',views.create, name="create"),
    path('<int:id>/detail/', views.detail, name="detail"),
    path('<int:id>/edit/', views.edit, name="edit"),
    path('<int:id>/update/', views.update, name="update"),
    path('<int:id>/delete/', views.delete, name="delete"),
]