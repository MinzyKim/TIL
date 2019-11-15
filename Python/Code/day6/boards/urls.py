from django.urls import path
from . import views

urlpatterns = [
    path('', views.index),
    path('subway_form/', views.subway_form),
    path('subway_result/', views.subway_result),
]