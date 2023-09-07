from django.urls import path
from . import views

app_name = 'flatApp'

urlpatterns = [
    path('create', views.create_offer, name='create'),
]