from django.urls import path

from todo.views import todo_fetch, todo_save

urlpatterns = [
    path('fetch/', todo_fetch, name='fetch'),
    path('save/', todo_save, name='save'),
]
