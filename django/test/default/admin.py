from datetime import datetime, timezone

from django.contrib import admin

# Register your models here.
from django.contrib import admin
from .models import Question, Choice


class ChoiceInline(admin.TabularInline):
    model = Choice
    extra = 3


class QuestionAdmin(admin.ModelAdmin):
    fieldsets = [
        (None, {'fields': ['question_text']}),
        ('Date information', {'fields': ['pub_date']}),
    ]
    list_display = ('question_text', 'pub_date')
    inlines = [ChoiceInline]

    # list_filter = ['pub-date']
    # search_fields = ['question_text']
admin.site.register(Question, QuestionAdmin)
