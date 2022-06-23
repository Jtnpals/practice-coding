from django.db import models


# Create your models here.
from django.urls import reverse


class Bookmark(models.Model):
    site_name = models.CharField(max_length=100)
    url = models.URLField('Site URL')

    def __str__(self):
        return "name : " + self.site_name + ", url : " + self.url

    def get_absolute_url(self):
        return reverse('detail', args=[str(self.id)])
