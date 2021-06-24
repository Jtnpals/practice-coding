from django.contrib.auth.models import AbstractUser
from django.core.validators import RegexValidator
from django.db import models

# Create your models here.
from django.template.loader import render_to_string
from django.utils.translation import gettext_lazy as _

from config import settings
from django.core.mail import send_mail


class User(AbstractUser):
    class GenderChoices(models.TextChoices):
        MALE = 'M', _('Male')
        FEMALE = 'F', _('Female')

    website_url = models.URLField(blank=True)
    bio = models.TextField(blank=True)
    phone_number = models.CharField(max_length=13, blank=True, validators=[RegexValidator(r"^010-?[0-9]\d{3}-?\d{4}$")])
    gender = models.CharField(max_length=1, blank=True, choices=GenderChoices.choices)
    profile = models.ImageField(blank=True, upload_to='accounts/profile/%Y/%m/%d', help_text='45px * 45px png / jpeg')


    def send_welcome_email(self):
        title = render_to_string("accounts/welcome_email_title.txt", {'user': self})
        content = render_to_string("accounts/welcome_email_content.txt", {'user': self})
        sender_email = settings.WELCOME_EMAIL_SENDER

        send_mail(title, content, sender_email, [self.email], fail_silently=False)

# class Profile(models.Model):
#     pass
