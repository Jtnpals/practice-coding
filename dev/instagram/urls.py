from django.urls import path, re_path, register_converter

from instagram import views
from instagram.converter import YearConverter, MonthConverter, DayConverter

register_converter(YearConverter, 'year')
# register_converter(MonthConverter, 'month')
# register_converter(DayConverter, 'day')

app_name = 'instagram'

urlpatterns = [
    path('new/', views.post_new, name='post_new'),
    path('', views.post_list, name='post_list'),
    path('<int:pk>/delete', views.post_delete, name='post_delete'),
    path('<int:pk>/', views.post_detail, name='post_detail'),
    path('<int:pk>/edit/', views.post_edit, name='post_edit'),
    # path('archives/<year:year>/', views.archives_year),
    # re_path(r'archives/(?P<year>20\d{2})/', views.archives_year),
    # re_path(r'(?P<pk>\d+)/$', views.post_detail),
    path('archives/', views.post_archive, name='post_archive'),
    path('archives/<year:year>/', views.post_archive_year, name='post_archive_year'),
    # path('archives/<year:year>/<month:month>', views.post_archive_month, name='post_archive_month'),
    # path('archives/<year:year>/<month:month>/<day:day>', views.post_archive_day, name='post_archive_day'),
]


