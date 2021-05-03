from django.urls import path

from todo.views import todo_fetch, todo_save, index

urlpatterns = [
    path('', index, name='index'),
    path('', index, name='index'),
    path('fetch/', todo_fetch, name='fetch'),
    path('save/', todo_save, name='save'),
]
