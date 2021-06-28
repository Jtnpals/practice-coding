from django.contrib.auth.forms import (UserCreationForm, PasswordChangeForm as AuthPasswordChangeForm)

from .models import User
from django import forms


class SignupForm(UserCreationForm):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        self.fields['email'].required = True

    class Meta(UserCreationForm.Meta):
        model = User
        fields = ['username', 'email', 'first_name', 'last_name']

    def clean_email(self):
        email = self.cleaned_data.get('email')
        if email:
            qs = User.objects.filter(email=email)
            if qs.exists():
                raise forms.ValidationError('이미 등록된 이메일 입니다.')
            return email


class ProfileForm(forms.ModelForm):
    class Meta:
        model = User
        fields = ['profile', 'first_name', 'last_name', 'bio', 'website_url', 'phone_number', 'gender']


class PasswordChangeForm(AuthPasswordChangeForm):
    def clean_password2(self):
        old_password = self.cleaned_data.get('old_password')
        new_password2 = super().clean_new_password2()
        if old_password == new_password2:
            raise forms.ValidationError("새로운 암호는 기존과 다르게 입력해주세요.")
        return new_password2
