from django.db import models

class Offer(models.Model):
    name = models.CharField("nom de l'acheteur", max_length=100)
    mount = models.CharField("montant de l'offre",max_length=10)


