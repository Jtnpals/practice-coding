from django.contrib.auth.views import LoginView, logout_then_login
from django.contrib.auth import login as auth_login
from django.shortcuts import render, redirect
from django.contrib import messages
# Create your views here.
from accounts.forms import SignupForm

login = LoginView.as_view(template_name='accounts/login_form.html')


def logout(reqeust):
    messages.success(reqeust, "로그아웃되었습니다.")
    return logout_then_login(reqeust)


def signup(request):
    if request.method == 'POST':
        form = SignupForm(request.POST)
        if form.is_valid():
            signed_user = form.save()
            auth_login(request, signed_user)
            # signed_user.send_welcome_email()  # FIXME: celery로 비동기 처리해야함
            messages.success(request, '회원가입 환영합니다.')
            next_url = request.GET.get('next', '/')
            return redirect(next_url)
    else:
        form = SignupForm()
    return render(request, 'accounts/signup_form.html', {
        'form': form
    })
