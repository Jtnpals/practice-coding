from django.shortcuts import render

# Create your views here.
from rest_framework import generics

from booking.models import Booking
from booking.serializers import BookingSerializer


class BookingList(generics.ListCreateAPIView):
    queryset = Booking.objects.all()
    serializer_class = BookingSerializer


class BookingDetail(generics.RetrieveUpdateDestroyAPIView):
    queryset = Booking.objects.all()
    serializer_class = BookingSerializer
