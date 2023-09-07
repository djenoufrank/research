from django.db import models

class User(models.Model):
    name = models.CharField("name", max_length=100)
    password = models.CharField("password",max_length=20)
