# Generated by Django 3.2.4 on 2021-06-24 06:14

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('accounts', '0003_auto_20210624_0610'),
    ]

    operations = [
        migrations.AddField(
            model_name='user',
            name='profile',
            field=models.ImageField(blank=True, upload_to='accounts/profile/%Y/%m/%d'),
        ),
    ]
