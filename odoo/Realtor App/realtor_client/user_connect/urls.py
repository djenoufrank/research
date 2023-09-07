from django.urls import path
from . import views

app_name = 'user_connect'

urlpatterns = [
    path('', views.index, name='index'),
    path('result_connect', views.result_connect, name='result_connect'),
    path('flatApp/', views.realtor, name='realtor'),
]