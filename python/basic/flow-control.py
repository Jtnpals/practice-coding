
print(type(True))
print(type(False))

# 기본 형식

# 예1
if True:
    print("Yes")  # 들여쓰기 중요

if False:
    # 출력되지 않음.
    print("No")

# 예2
if False:
    # 여기는 실행되지 않음.
    print("You can't reach here")
else:
    # 여기가 실행된다.
    print("Oh, you are here")

# 관계연산자
# >, >=, <, <=, ==, !=


a = 10
b = 0

# == 양 변이 같을 때 참.
print(a == b)

# != 양 변이 다를 때 참.
print(a != b)

# > 왼쪽이 클때 참.
print(a > b)

# >= 왼쪽이 크거나 같을 때 참.
print(a >= b)

# < 오른쪽이 클 때 참.
print(a < b)

# <= 오른쪽이 크거나 같을 때 참.
print(a <= b)

# 참 거짓 종류
# 참 : "내용", [내용], (내용), {내용}, 1
# 거짓 : "", [], (), {}, 0, None

city = ""
if city:
    print("You are in:", city)
else:
    # 이쪽이 출력된다.
    print("Please enter your city")

city = "Seoul"
if city:
    print("You are in:", city)
else:
    # 이쪽이 출력된다.
    print("Please enter your city")

# 논리연산자
# and, or, not

a = 100
b = 60
c = 15

print('and : ', a > b and b > c)  # a > b > c
print('or : ', a > b or b > c)
print('not : ', not a > b)
print('not : ', not b > c)
print(not True)
print(not False)

# 산술, 관계, 논리 우선순위
# 산술 > 관계 > 논리 순서로 적용

print('ex1 : ', 3 + 12 > 7 + 3)
print('ex2 : ', 5 + 10 * 3 > 7 + 3 * 20)
print('ex3 : ', 5 + 10 > 3 and 7 + 3 == 10)
print('ex4 : ', 5 + 10 > 0 and not 7 + 3 == 10)

score1 = 90
score2 = 'A'

# 복수의 조건이 모두 참일 경우에 실행.
if score1 >= 90 and score2 == 'A':
    print("합격하셨습니다.")
else:
    print("불합격입니다.")

id1 = "gold"
id2 = "admin"
grade = 'super'

if id1 == "gold" or id2 == "admin":
    print("관리자 로그인 성공")

if id2 == "admin" and grade == "super":
    print("최고 관리자 로그인 성공")

is_work = False

if not is_work:
    print("is work!")

# 다중 조건문
num = 90

if num >= 70:
    print("num ? ", num)
elif num >= 60:
    print("num ? ", num)
else:
    print("default num")

# 중첩 조건문

age = 27
height = 175

if age >= 20:
    if height >= 170:
        print("A지망 지원 가능")
    elif height >= 160:
        print("B지망 지원 가능")
    else:
        print("지원 불가")
else:
    print("20세 이상 지원가능")

# in, not in

q = [1, 2, 3]
w = {7, 8, 9, 9}
e = {"name": 'Kim', "city": "seoul", "grade": "B"}
r = (10, 12, 14)

print(1 in q)
print(6 in w)
print(12 not in r)
print("name" in e)  # key 검색
print("seoul" in e.values())  # value 검색

# 기본 반복문 사용(while, for)
v1 = 1

while v1 < 11:
    print("v1 is :", v1)
    v1 += 1

for v2 in range(10):
    print("v2 is :", v2)

for v3 in range(1, 11):
    print("v3 is :", v3)

for v4 in range(1, 11, 2):
    print("v4 is :", v4)

# 1 ~ 100합

sum1 = 0
cnt1 = 1

while cnt1 <= 100:
    sum1 += cnt1
    cnt1 += 1

print('1 ~ 100 합 : ', sum1)
print('1 ~ 100 합 : ', sum(range(1, 101)))  # sum(리스트)
print('1 ~ 100 안에 3의 배수의 합 : ', sum(range(1, 101, 3)))

# 시퀀스(순서가 있는) 자료형 반복
# 문자열, 리스트, 튜플, 집합, 사전
# iterable 리턴 함수 : range, reversed, enumerate, filter, map, zip

# 예제1
names = ["Kim", "Park", "Cho", "Lee", "Choi", "Yoo"]

for name in names:
    print("You are", name)

# 예제2
lotto_numbers = [11, 19, 21, 28, 36, 37]

for number in lotto_numbers:
    print("Your number", number)

# 예제3
word = 'dreams'

for s in word:
    print('word : ', s)

# 예제4
my_info = {
    "name": "Kim",
    "age": 33,
    "city": "Seoul"
}

for key in my_info:
    print(key, ":", my_info[key])

for val in my_info.values():
    print(val)

# 예제5
name = 'KennRY'

for n in name:
    if n.isupper():
        print(n)
    else:
        print(n.upper())

numbers = [14, 3, 4, 7, 10, 24, 17, 2, 33, 15, 34, 36, 38]

# break
for num in numbers:
    if num == 33:
        print("found : 33!")
        break
    else:
        print("not found : ", num)

# continue
lt = ["1", 2, 5, True, 4.3, complex(4)]

for v in lt:
    if type(v) is float:
        continue

    print("type:", type(v))
    print("multiply by 2:", v * 3)

# for-else 실습
numbers = [14, 3, 4, 7, 10, 24, 17, 2, 33, 15, 34, 36, 38]

for num in numbers:
    if num == 33:  # 45
        print("found : 33!")
        break
    else:
        print("not found : ", num)
else:
    print("Not Found 39...")

# flag 사용

f = True
numbers = [14, 3, 4, 7, 10, 24, 17, 2, 33, 15, 34, 36, 38]

while f:
    for v in numbers:
        if v == 33:
            print("found : 33!")
            f = False
        print("not found : ", v)

# else 구문 정리(반복문이 정상적으로 수행 된 경우 else 블럭 수행)
# 예제1

i = 1
while i <= 10:
    print('i : ', i)
    if i == 6:
        break
    i += 1
else:
    print('else block run!')

# 예제2
j = 1
while j <= 10:
    print('j : ', j)
    if j == 11:
        break
    j += 1
else:
    print('else block run!')

# 중첩 for 문 구구단 출력

for i in range(1, 11):
    for j in range(1, 11):
        print('{:4d}'.format(i * j), end='')
    print()

# 자료 구조 변환 예제
name = 'Niceman'
print('reversed : ', reversed(name))
print('list : ', list(reversed(name)))
print('list : ', tuple(reversed(name)))
print('list : ', set(reversed(name)))  # 순서X
