from django.shortcuts import render

# Create your views here.
from boards.models import Board


def index(request):
    boards = Board.objects.all()[::-1]
    return render(request, 'boards/index.html', {'boards': boards})
