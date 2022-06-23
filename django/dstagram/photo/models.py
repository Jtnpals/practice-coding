from django.contrib.auth.models import User
from django.db import models


# Create your models here.
from django.urls import reverse


class Photo(models.Model):
    author = models.ForeignKey(User, on_delete=models.CASCADE, related_name='user_photos')
    photo = models.ImageField(upload_to='photo/%Y/%m/%d', default='photos/no_img.png')
    text = models.TextField()

    created = models.DateTimeField(auto_now_add=True)
    updated = models.DateTimeField(auto_now=True)

    class Meta:
        ordering = ['-updated']

    def __str__(self):
        return self.author.username + " " + self.created.strftime("%Y-%m-%d-%H-%M-%S")

    def get_absolute_url(self):
        return reverse('photo:photo_detail', args=[str(self.id)])

