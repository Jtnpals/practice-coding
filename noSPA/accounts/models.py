from django.contrib.auth.models import AbstractUser
from django.db import models

# Create your models here.
from django.template.loader import render_to_string

from config import settings
from django.core.mail import send_mail


class User(AbstractUser):
    website_url = models.URLField(blank=True)
    bio = models.TextField(blank=True)

    def send_welcome_email(self):
        title = render_to_string("accounts/welcome_email_title.txt", {'user': self})
        content = render_to_string("accounts/welcome_email_content.txt", {'user': self})
        sender_email = settings.WELCOME_EMAIL_SENDER

        send_mail(title, content, sender_email, [self.email], fail_silently=False)

# class Profile(models.Model):
#     pass
