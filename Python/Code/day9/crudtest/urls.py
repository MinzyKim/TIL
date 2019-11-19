from django.urls import path
from . import views

app_name='crudtest'

urlpatterns=[
    path('', views.index, name='index'),
    path('new/', views.new, name='new'),
    path('<int:id>/detail/', views.detail, name='detail'),
    path('<int:id>/update/', views.update, name='update'),
    path('<int:id>/delete/', views.delete, name='delete'),
    path('<int:id>/survey/', views.survey, name='survey'),
    path('<int:c_id>/survey_edit/', views.survey_edit, name='survey_edit'),
]