### 1. `$ pip install django`
### 2. `$ django-admin startproject <ProjectName> .`
### 3. `$ python manage.py migrate`
### 4. `$ python startapp <AppName>`
### 5. `$ python manage.py creatsuperuser`
### 6. `$ python manage.py runserver`
### 7. `$ python manage.py 8080`  (초기값 8000)
### 8. `$ python managy.py makemigrations <AppName>`
### 9. `$ python managy.py sqlmigrate <AppName> 0001`(아직 migration된 상태가 아님)
### 10. `$ python manage.py migrate <AppName> 0001` (테이블 생성 및 초기화)  