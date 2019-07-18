[TOC]

# Day 1

---

## 자바의 8가지 자료형

| byte | short |  int  | long  | float | double |
| :--: | :---: | :---: | :---: | :---: | :----: |
| 8bit | 16bit | 32bit | 64bit | 32bit | 64bit  |

> 자료형의 크기 : **byte < short < int < long < float < double**

이중 `byte, short, int, long`은 정수의 값을 가져다 저장할 수 있는 자료형

작은 타입에서 큰타입은 자동변환이 가능

큰 타입에서 작은탑입은 강제변환이 없으면 에러남

기본적으로 같은 타입형 변수만이 연산가능하다.



## 변수는 값을 저장하는 기억공간

​	자료형 : 변수선언을 할 수 있다.

​	값을 저장할 때는 대입

```java
int i;	//변수선언   int == 자료형   i == 이름
i = 100 ;	//대입
```



## 문제

### 	연 6.6퍼 이자율에 초기금 100만원을 100년간  묵힐때

```java
public class TestCode1 {
    public static void main(String[] args) {
        double money = 1000000;
        double interestRate = 0.066;
        for (int i=0; i<100; i++){
            money += money * interestRate;
        }
        System.out.println((int)money);
    }
}
```



### 	연6.6% 이자율의 복리 (매 100만원 적금)

```java
public class TestCode2 {
    public static void main(String[] args) {
        int addMoney = 1000000;
        double money = 0;
        double interestRate = 0.066;
        for (int i=0; i<100; i++){
            money += addMoney;
            money += money * interestRate;
        }
        System.out.println(money);
        long endTime = System.currentTimeMillis();
    }
}
```

# Day 2

---

​	**코드 잘 짜는 방법**

+ 잘 쪼개서 짠다.

+ 자신의 보폭을 잘 파악해야한다.

+ 반복문이 정리가되려면 일단 늘어놓고 정리한다.



##  Java와 C의 차이점

```java
public class Test001 {
    public static void main(String[] args) {
        int i;
        i = 100;
        System.out.println(i);
    }
}
```

​	**java**는 `int i = 100;` 이 허용됨.

```c
#include <stdio.h>
i1nt main(){
    int i;
    i = 100;
    printf("%d\n", i);
    return 0;
}
```

​	**C**의 경우에는 변수의 선언은 위에 몰아서 하는게 원칙임.

​	`int i; i=100;` 처럼 선언과 대입은 철저히 구분.

## C언어의 개념

### 기본 포인터

```c
#include <stdio.h>
int main(){
    int* t; //포인터도 기억공간 할당 int형 기억공간을 가르키기 위해 쓰임
    int i;
    // 아래 코드에서 *t와 i는 동일하다. 이유는?
    i = 100;
    t = &i;  // t포인터가 i주소의 기억공간을 가리킴
    printf("%d\n", *t); // t변수가 가르키는 공간
    return 0;
}
```

```
 결과값 : 100
```

​	**C**언어의 경우에는 자료형의 포인터형 변수가 존재한다.

```c
#include <stdio.h>
int main(){
    int* t;
    int* l;
    int i;
    i = 100;
    t = &i;
    l = t; 
    printf("%d\n", *l); // l값은 100이 나올까?  -> 100이나옴
    return 0;
}
```

```
결과값 : 100
```

​	자료형 변수의 대입과 포인터의 대입은 다르다. 

​	포인터의 대입은 오른쪽이 가리키는 대상을 왼쪽이 가리키게 된다.

​	**포인터의 역할 : 변수타입의 기억공간을 가리키기 위하여**

​	**자료형 변수의 역할 : 값을 저장하기 위하여**

```c
#include <stdio.h>
int main(){
    int i;
    void* t;
    i= 100;
    t = &i;
    return 0;
}
```

```c
#include <stdio.h>
int main(){
    void* t;
    float l
    t = 3.14;
    t = &i;
    return 0;
}
```

> 위의 두 코드 모두 컴파일에러가 나지않음

​	void* 변수는 어떤 기억공간이든 다 가리킨다.

```c
#include <stdio.h>
int main(){
    void* t;
    float l
    t = 3.14;
    t = &i;
    printf("%d\n", *t);
    return 0;
}
```

> 여기서는 컴파일 에러가남

​	void*는 어떤 기억공간이든 다 가리킬 수 있지만 실체에 접근할수는 없다.

```c
#include <stdio.h>
int main(){
    void* t;
    int i;
    i = 100
    t = &i //void*는 기억공간의 꼭지점을 가리킨다고 보자.
    int* h;
    h = (int*)t;
    printf("%d\n", *h);
    return 0;
}
```

```
결과값 : 100
```

​	void*는 꼭짓점만 가르킬 수있음.

​	t가 가리키는 지점을 기준으로 int형 기억공간 만큼을 확장한 기억공간 을 h가 가리킨다.

### 지역변수

```c
#include <stdio.h>
int add(int i, int j){ return 100;}

int main(){
    int r; //지역변수
    r = add(10, 20);
    printf("%\n", r);
    return 0;
}
```

```
결과값 : 100
```

**지역변수** r은 ==함수가 호출되는 시점== 에서 메모리 할당됨

[유일한 리턴타입] [이름]] ( 0.. * 변수선언 - 매개변수 ) { ... }

i , j -> 매개변수 (함수가 호출될 때 메모리 할당됨)

특정 입력값에 대하여 결과값은 하나만 존재

여러 입력값이 존재할 수 있다.

### 전역변수

```c
#include <stdio.h>
int t; //전역변수
int main(){
    int r;
    r = add(10, 20);
    printf("%\n", r);
    return 0;
}
```

**전역 변수** t는 ==컴파일되는 시점==에서 메모리 확보

main도 함수다. 함수안에 변수 선언 가능

함수가 호출되는 시점에메모리 할당. 호출 끝나면 소거

= 오른편이 먼저 동작. 선언된 함수를 호출한다.

함수에는 매개변수가 선언된다.



t 로컬변수가 호출시에 생성되고 i + j 값을 대입당한다. ( 30 )

함수의 종료에는 return ( 변수 / 값 ) 인데 ( 변수 / 값 )은 함수의 리턴타입을 만족해야한다. ( int )

r = add( 10, 20 ); 의 결과로 return t 가 동작하면 r = t 의 현상이 벌어짐

### 함수 포인터

```c
#include <stdio.h>
int add(int i, int j){ return i+j;}

int main(){
	int r;
    // 아래와 같이선언된 변수 fp는 함수를 가리 킬 수 있다.
    // 리턴타입이 int, 매개변수가 int, int형태로 선언된 함수를.
    int (*fp)( int, int);
    fp = add;
    //fp 라는 함수가 아닌 fp 변수가 가리키는 함수를 호출한다.
    r = fp(10, 20);
    printf("%d\n", r);
    return 0;
    
}
```

> JavaScript 대부분이 이렇게 함수포인터로 구현되어져있음

## 자바

### 선언, 대입, 연산

```java
class Apple{
    int i;
    i = 100;  // 에러남
    int add(int i, int j){ return 100;}
    add(); // 에러남
}
```

- 클래스에서 변수, 함수 선언 가능
- 클래스에서 변수, 함수 호출 연산, 초기화 등등은 불가능

### 참조형 변수

```java
class Apple{
    int i;
    int add(int i, int j){ return 100;};
}
public class Test023 {
    public static void main(String[] args) {
        Apple t = new Apple();
    }
}
```

`Apple t;` 기본자료형이 아님에도 에러가 나지않음

 참조형 변수 -> 포인터와 같이 동작

클래스를 선언하고, new 를 이용해서 instance를 만들 수 있다.

==참조형 변수 t는 Apple이라는 instance를 가르킴==

```java
class Apple{
    int i;
    int add(int i, int j){ return 100;};
}

public class Test023 {
    public static void main(String[] args) {
        Apple t = new Apple();
        t.i = 100;
        System.out.println(t.add(100, 20));
    }
}
```

클래스는 설계도이다. ( 변수 함수 선언 ) - 실제로 활용은 안됨

new 를 이용해서 instance를 만든다. 그 안에는 변수와 함수가 들어있다.

인스턴스는 이름이 없다. 단 포인터로 실체에 접근이 가능

`t.i` : t가 가리키는 인스턴스가 가지고있는 변수 i

`t.add(10,20)` : t 가 가리키는 인스턴스가 가지고 있는 함수

## C언어의 구조체

```c
#include <stdio.h>
struct apple{
    int i;
    int add;
}; // 두개의 변수를 묶어서 apple이라는 이름으로 '구조체'를 정의
int main(){
    struct apple* t;
    t = (struct apple*)malloc( sizeof(struct apple)); // malloc 은 java의 new와 비슷
    free(t);
    return 0;
}
```

> `malloc`은 `struct` 크기만큼 메모리를 할당받음 `t`포인터가 해당 공간을 가리킴

`t`는 `apple`구조체의 기억공간을 가리킬 수있는 포인터 변수

구조체의 기억공간은 이름이없고 포인터로 접근이 가능 

-> ==지역변수라 말할 수 없다==

-> 메소드가 끝이나도 강제로 메모리가 삭제되지 않음

-> free(t)는 t가 가리키는 대상을 삭제 (t 변수가 삭제되는게 아니라 가리키는 지점만 삭제됨)

```c
#include <stdio.h>
struct apple{
    int i;
    int add;
};
int main(){
    struct apple* t;
    t = (struct apple*)malloc( sizeof(struct apple));
    t->i =100;  // t가 가리키고있는 i공간에 100을 넣어라   -->t안의 i라는 표현 x
    t->add = 20; // t가 가리키고있는 add공간에 20을 넣어라  -->t안의 add라는 표현 x
    printf("%d\n", (t->i + t->add) );
    free(t);
    return 0;
}
```

```c
#include <stdio.h>
int apple_add(int i, int j){
    return 100;
}
typedef struct apple{
    int i;
    int (*add)(int, int); // 함수포인터도 변수이므로 구조체가 가질 수 있다
}Apple; // 이름 지정 가능
int main(){
    Apple* t;
    t = (Apple*)malloc( sizeof(struct apple));
    t->i =100;
    t->add = apple_add; // t->apple 구조체의 add포인터를 가리킴 -> add포인터는 apple_add 함수를 가리킴
    printf("%d\n", (t->i + t->add(100,20)) );
    free(t);
    return 0;
}
```

```
결과값 : 200     // 100 + 100
```

```c
#include <stdio.h>
int apple_add(int i, int j){
    return 100;
}
typedef struct apple{
    int i;
    int (*add)(int, int);
}Apple;
// instance를 생성하는것과 유사한 동작을 하게 된다. 이렇게해서 생성되고
//이것을 가리키는 포인터를 통해서 함수와 변수가 접근되어질 수 있다.

//가비지콜렉션은 메모리가 충분하면 일어날 일이 없다. 대게 CPU가 놀때 일어남
Apple* new_Apple(int j){
    Apple* n;
    n = (Apple*)malloc( sizeof(struct apple));
    n->i = j; //자바의 생성자역할
    n->add = apple_add;
    return n;
}

int main(){
    Apple* t;
    t = new_Apple(100);
    printf("%d\n", (t->i + t->add(10,20)) );
    free(t);
    return 0;
}
```

>  C로 Java의 new 역할하게 만들기

```java
class Apple{
    int i;
    public Apple(int i) {
        this.i = i;
    } //constructor  - 포인터로 호출되지않아 함수라고 보기는 좀 어렵다. 인스턴스가 생성되는 시점에 호출
    int add(int i, int j){return 100;}
}

public class Test025 {
    public static void main(String[] args) {
        Apple t = new Apple(100);
        System.out.println(t.i + t.add(30, 20));
    }
}

```

> 위의 C코드를 자바로

## 요약

==클래스==를 선언해서 ==인스턴스==를 만듬 이떄 사용하는것이 ==new==

class 내부 ( ==멤버변수==, ==멤버함수== )       |||||     멤버함수 내부 ( 멤버변수 )

클래스 - instance - ( new ) - (이때 멤버함수/멤버변수가 존재하게됨)

instance가 만들어지는 시점에 멤버변수의 메모리가 할당되게됨

함수가 호출되는 시점에 ==로컬변수==에 메모리가 할당됨

Apple apple = new Apple(somthing)

==참조형 변수==는 인스턴스를 가리키기위한 용도 : class 이름으로 선언 (apple)

==자료형 변수==는 자바의 기본8개 자료형

자료형 변수에서의 대입 ( i = t )  -> t의 값을 복사해서 i에 대입

참조형 변수에서의 대입 (i = t ) -> t가 가리키는 대상을 i가 가리킴

==생성자함수==는 클래스의 멤버함수 -> 주로 멤버변수를 초기화하는데 사용 (constructor)

인스턴스가 생성될때 생성자함수가 생성됨

![State Flow](../images/stateflow.png)



## 문제

###  함수를 이용한 C 프로그래밍

```c
#include <stdio.h>
float bokri(float money, int year, float rate){
    float moneyStart;
    int i;
    moneyStart = 0;
    for (i=0; i<year; i++){
        moneyStart += money;
        moneyStart += moneyStart * rate;
    }
    return moneyStart;
}

int main(){
    float r;
    r = bokri(100.0, 100, 0.066);
    printf("%\n", r);
    return 0;
}

```

​	 함수가 들어간 코드는 무조건 틀부터 짠다.

### 함수를 이용한 C프로그래밍2

```c
#include <stdio.h>
int apple(int i, int j){ return 100;}
int banana(int i, int j){ return 200;}

int main(){
	int r;
    r = apple( 10, 20);
    printf("%d\n", r); //100
    r = banana( 10, 20);
    printf("%d\n", r); //200
    return 0;
}
```

### 함수포인터를 이용한 C프로그래밍

```c
#include <stdio.h>
int apple(int i, int j){ return 100;}
int banana(int i, int j){ return 200;}

int main(){
    int (*fp)( int, int);
	int r;
    fp = apple;
    r = fp( 10, 20);
    printf("%d\n", r); //100
    fp = banana;
    r = fp( 10, 20);
    printf("%d\n", r); //200
    return 0;
}
```

```
결과값 : 100, 200
```

### 단리/복리 가능한 C프로그래밍 (매 100만원 적금)

```c
#include <stdio.h>
float danri(float money, int year, float rate){
    int i;
    float totalMoney, mainMoney;
    totalMoney = 0;
    mainMoney = 0;
    for (i=0; i<year; i++){
        mainMoney += money;
        totalMoney += mainMoney * rate + money;
    }
    return totalMoney;
}

float bokri(float money, int year, float rate){
    float moneyStart;
    int i;
    moneyStart = 0;
    for (i=0; i<year; i++){
        moneyStart += money;
        moneyStart += moneyStart * rate;
    }
    return moneyStart;
}

int main(){
    float (*fp)( float, int, float);
    float r;
    fp = danri;
    r = fp( 100.0, 100, 0.066);
    printf("%f\n", r);
    fp = bokri;
    r = fp( 100.0, 100, 0.066);
    printf("%f\n", r);
    return 0;
}
```

### 단계별 단리 작성

#### 1단계

```c
float danri(float money, int year, float rate){
    return 100;
}

int main(){
    float (*fp)( float, int, float);
    float r;
    fp = danri;
    r = fp( 100.0, 100, 0.066);
    printf("%f\n", r);
    return 0;
}
```

```
결과값 : 100
```

#### 2단계

```c
float danri(float money, int year, float rate){
	int i;
    for (i=0; i<year; i++){
        prinf(i);
    }
    return 100;
}

int main(){
    float (*fp)( float, int, float);
    float r;
    fp = danri;
    r = fp( 100.0, 100, 0.066);
    printf("%f\n", r);
    return 0;
}
```

```
결과값 : 0 1 2 ... 99
```

#### 3단계

```c
float danri(float money, int year, float rate){
	int i;
    for (i=0; i<year; i++){
        total += money * rate;
    }
    return total;
}

int main(){
    float (*fp)( float, int, float);
    float r;
    fp = danri;
    r = fp( 100.0, 100, 0.066);
    printf("%f\n", r);
    return 0;
}
```

```
결과값 : 75999...
```

# Day3

---

## JAVA

```java
class Apple3{
    int data = 0;
    int add(int i, int j){
        return 100;
    }
}
// 멤버변수 ( property ), 멤버함수 ( method )
// 클래스로 할수있는것 -> 참조형 변수 선언, 인스턴스 생성
// 인스턴스와 클래스 관계, 참조형 변수와 인스턴스 관계.
public class Test26 {
    public static void main(String[] args) {
        Apple3 t = new Apple3();
        int i = t.add(10,20);
        System.out.println(i);
    }
}
```

​	참조형 변수는 인스턴스를 가리키기 위한 것

 **객체지향 3대 속성**

1. 상속성: 클래스를 상속해서 클래스를 만든다.
2. 은닉성: 감추고 싶은건 감출 수 있다.
3. 다형성: 하나의 심볼(이름)이 여러 실체에 매핑될 수 있다.

```java
class A {
    int apple = 10;
}
//클래스 B는 클래스 A를 상속하여 만들었음을 명시.
// A에서 선언한 멤버변수는 멤버함수를 내려받겠다.
class B extends A{
    int add(int i, int j){return  100;}
}
public class Test27 {
    public static void main(String[] args) {
		B t = new B();
        System.out.println(t.apple);
    }
}
```

```
결과값 : 100
```

> B는 A클래스를 상속받고 있음

**클래스를 가지고 할 수 있는 3가지**

+ 참조형 변수의 선언
+ 인스턴스 생성
+ 상속받아 클래스 선언

*위 코드가 에러가 안나는 이유?*

new를 통해 B클래스의 인스턴스를 생성하는데 이 때, A를 상속받은 B클랙스의  참조형 변수t가 이렇게 생성된 인스턴스를 가리킴으로써 인스턴스 내부에서 부모클래스의 멤버변수에 접근할 수 있게되기 때문.

```java
class Apple4{
    int data = 0;
    // return이 존재하지 않는 함수를 서브루틴이라 한다.
    //리턴타입을 void로 한다.
    void print(){
        System.out.println(this.data);
    }
}
public class Test28 {
    public static void main(String[] args) {
	    Apple t = new Apple();
        t.data = 10;
        t.print();
        Apple l = new Apple();
        l.data = 20;
        l.print();
    }
}
```

```
결과값 : 10 20
```

> 멤버함수안에서 자신이 소속된 인스턴스에 대한 포인터 : this

함수하나의 길이가 크다고해서 인스턴스를 많이 생성하면 메모라에 부담가나? -> No

##  Java로  리스트 만들기

### 연결 리스트

```java
class Node{ //외워라!
    int data = 0; //  대입은 원래 불가능하나 선언과 동시에 대입은 가능함
    Node next = null; //가리키고 싶지않다.

    Node( int i , Node n){ //생성자 일반적 예시
        this.data = i;
        this.next = n;
    }
}
public class Test29 {
    public static void main(String[] args) {
        Node head = new Node(0,null);		//Step 1
        Node tail = head;

        tail.next = new Node(10, null);			//Step 2
        tail = tail.next;

        tail.next = new Node(20, null);			//Step 3
        tail = tail.next;

        for( Node t =head.next ; t !=null ; t=t.next){		//Step 4
            System.out.println(t.data);
        }
    }
}
/*
    모든 참조형 변수에는 null 이라는 값이 대입 가능 : 가리키는 인스턴스 없다.
    -어떻게 돌아가는지 상황파악을 그림으로 설명 가능할 것.
 */
```

```
출력값 : 10 20
```

위 코드의 객체 상태변화는 아래 그림과 같다.

![Class State](..\images\nodeflow.png)

### 연결리스트 클래스화

```java
class Node{
    int data = 0;
    Node next = null;
    Node( int i , Node n){
        this.data = i;
        this.next = n;
    }
}
class LinkedList {
    Node head = null;
    Node tail = null;
    public LinkedList() {
        this.head = new Node(0, null);
        this.tail = this.head;
    }
    void add(int i){
        tail.next = new Node(i, null);
        tail = tail.next;
    }
    public void print() {
        for (Node t = head.next; t !=null; t=t.next) {
            System.out.println(t.data);
        }
    }
}
public class Test29 {
    public static void main(String[] args) {
       LinkedList l = new LinkedList();  //Step 1
       l.add(10);  //Step 2
       l.add(20);  //Step 3
       l.print();

    }
}
```

```
출력값 : 10 20
```

위 코드의 객체 상태변화는 아래 그림과 같다.

![linkedlistflow](..\images\linkedlistflow.png)

## Java 상속

```java
class A{
    A(){
        System.out.println("A constructor");
    }
}
class BB extends A{
    B() {
        System.out.println("B constructor");
    }
}
public class Test31 {
    public static void main(String[] args) {
        new B();
    }
}
```

```
결과값: A constructor   B constructor
```



상속관계가 존재할때 ( 조상클래스 , 자손클래스 ) 자손의 인스턴스를 생성하면 조상의 생성자가 먼저 호출되고, 자손의 생성자가 호출된다.

==이떄 인스턴스는 두번 생성되는것 처럼보이지만 하나가 생성되고 조상과 자손을 호출하는 것이다==

생성자는 상속되지 않는다. 다만 호출될 뿐이다 : 멤버함수가 아니라서

```java
        B t = new B();
        t.B();   // 이부분에서 에러가남
```

따라서 위와 같은 코드에서는 `t.B();` 생성자를 찾을수 없다고 뜸

```java
class A{
    void print(){
        System.out.println("A print");
    }
}
class B extends A{
    void print(){
        System.out.println("B print"); // method overriding
        super.print();// 부모의 함수를 호출하고 싶을때
    void print2(){
        System.out.println("B print2");
    }
}
public class Test32 {
    public static void main(String[] args) {
        B t = new B();      //자손 B호출
        t.print();
        A t2 = new A();         //조상 A 호출
        t2.print();
        A t3 = new B();        //자손 B 호출
        t3.print();
        //조상에서 선언된 멤버함수, 멤버변수만 호출 가능
        //t3.print()2;     -------> 에러남
    }
}
```

```
결과값 : B print A print A print B print A print
```

> 조상에서 선언한 멤버함수를 다시 자손에서 선언 할 수있다.
>
> 자손의 인스턴스를 호출하면 자손의것으로 나옴
>
> 상속은 클래스와 클래스에서 일어남

```java
       A t3 = new B();
        t3.print();
//        B t4 = (B) new A();//에러남 캐스팅되지않음
//        t4.print();   //다운캐스팅은 일반적으로 불가
        B t4 = (B) t3;   //이 경우 다운캐스팅이됨
        t4.print();         //JVM이 무슨형인지 추리할수 있기때문
```

>다운캐스팅은 일반적으로 불가하나 JVM이 그 부모를 알 수 있는경우, 자료형의 경우 다운캐스팅이 가능(업캐스팅이 선행된 경우)

```java
class A{
    int i = 100;   //  <------
    void print(){
        System.out.println("A print");
    }
}
class B extends A{
    int i = 200;  //  <-----
    void print2(){
        System.out.println("B print2");
    }
}
public class Test {
    public static void main(String[] args) {
        A t = new B();
        t.print();
        System.out.println(t.i);
    }
}
```

```
결과값 : B print2   100
```

> ==멤버변수에는 오버라이딩이라는 개념이 없기떄문==에 이 경우 부모의 i값인 100이 출력된다

![overridingflow](..\images\overridingflow.png)

### 은닉성

+ 상속 x, 외부노출x, 내가 쓰는것 : **private**

+ 상속 o, 외부노출x, 내가 쓰는것 : **protected**

+ 상속 o, 외부노출o, 내가 쓰는것 : **public

1.  **protected** :같은 패키지에서는 접근가능 / 다른패키지에서는 접근 불가

2. **friendly(defualt)** : 같은 패키지에서는 public / 다른패키지에서는 private

   ``public > protected > friendly > protect``  순으로 자유로움

>  참조 : https://hunit.tistory.com/162

|                   클래스내부                    |        동일패키지 접근가능         |               다른패키지 접근불가               |
| :---------------------------------------------: | :--------------------------------: | :---------------------------------------------: |
| public<br />protected<br />default<br />private | public<br />protected<br />default | protected(상속시가능)<br />default<br />private |

멤버변수에는 오버라이딩이라는 개념이 없기떄문에 이 경우 부모의 i값인 100이 출력된다

-> 때문에 Getter를 이용하여 자신의 값을 호출하게 만듬

```java
class A{
    private int data =100;
	public int getData(){ return data;}
}
class B extends A{
    private int data =200;
    public int getData(){return data;}
}
public class Test{
    public void static main(String[] args){
        A t = new B();
        System.out.println(t.getData());
    }
}
```

```
결과값 : 200
```

B 클래스의 getData가 호출이되서 혼란이 방지됨

""조상에서 getXXX 함수가 보이면 그런 변수가 있는줄 알고 xXX 변수는 피해간다.""

<u>이경우 `getData()`로 부모클래스의 data를 부르려고 하면 `getData()`가 오버라이딩 되어있기 때문에 접근이 힘들어진다.</u>

## 추상클래스

```java
abstract class A{
    public abstract void print();
}
abstract class B{}
class C extends B{}
abstract class D extends C{}
public class Test1 {
    public static void main(String[] args) {
//      A t = new A();
        A t = null;
    }
}
```

추상메소드 : 선언되었지만 구현되어있지않은  메소드

만일 abstract 메소드가 하나라도 선언되어있으면 그 메소드를 가진 클래스는 반드시 abstract클래스로 정의하여야 한다.

다만 B클래스와 같이 아무것도 없는 abstract클래스는 만들 수 있다.

+ abstract class는 instance를 못만든다.
+ 변수선언은 가능
+ C 클래스처럼 상속또한 가능하다.

```java
abstract class A{
    public abstract void print();
}
abstract class B extends A{}  //  <-------
class C extends A{			// <------
    @Override
    public void print() {
         System.out.println("hi");
    }
}
public class Test1 {
    public static void main(String[] args) {
        A t = new C();   // <------
        t.print();
    }
}
```

```
결과값 : hi
```

B클래스처럼 abstract class로부터 상속은 이상없지만 C class와 같이 abstract class가 아닌 class가 abstract클래스를 상속받은경우 구현해줘야함.

`A t = new C();` 의 경우 조상의 참조형 변수로 자손을 만드는것은 가능하고, 조상의 함수를 호출한 경우 자손의 함수가 호출되므로 오버라이딩 된 구현부가 호출이됨

![abstarctflow](..\images\abstarctflow.png)

> 아래 코드의 동작과정

```java
A t = new C();
t.print();
```

### 추상클래스 사용 예(Template Method Pattern)

````java
abstract class Bank{
    public abstract double calc(double money, int years, double rate);
    public void print(){
        double r = calc(100, 100, 0.066);
        System.out.println(r);
    }
}
class BokriBank extends Bank{
    @Override
    public double calc(double money, int years, double rate) {
        return 59665.1234 * subCalc();
    }
    private int subCalc(){return 3;}
}
class DanriBank extends Bank{
    @Override
    public double calc(double money, int years, double rate) {
        return 760.0 + money();
    }
    private int money(){return 100;}
}
public class Test2 {
    public static void main(String[] args) {
        Bank bk = new BokriBank();
        bk.print();
        Bank bk2 = new DanriBank();
        bk2.print();
    }
}
````

```
결과값 : 178995.3702   860.0
```

> 자주 변하는 부분과 자주 안변하는부분을 나누어 자주변하는 부분을 자유롭게 바꿔서 사용할 수있음
>
> 복리와 단리 마음대로 바꿀 수 있음 (템플릿 메소드 패턴)
>
> 여기서 `subClac()`, `money()`와 같은 독자적인 구동도 구현가능

## 인터페이스

```java
interface ICalc{
    public void print(); //인터페이스 안에는 모두 추상자료형이여야함
}
interface IUnknown{}
class Apple{}
class Calc extends Apple implements ICalc, IUnknown{
    @Override
    public void print() {
        System.out.println("A");
    }
}

public class Test3 {
    public static void main(String[] args) {
        ICalc ic = new Calc();
        ic.print();
    }
}
```

+ 인터페이스는 일종의 abstract 클래스이다. - 변수선언, 상속당할 수 있음 --> 다만 인스턴스 생성 불가.

+ 인터페이스 상속해서 클래스를 선언할 때는 implements를 이용해야한다.

+ 다중상속가능, 클래스 상속과 인터페이스 동시상속 가능

  

### 인터페이스를 이용한 Decorator Pattern

```java
interface IGreet {
    public String greet();
}
class MerciGreet implements IGreet{
    @Override
    public String greet() {
        return "Merci";
    }
}
class HelloGreet implements IGreet{
    @Override
    public String greet() {
        return "hello";
    }
}
abstract class GreetDeco implements IGreet{
    protected IGreet ig = null;

    public GreetDeoo(IGreet ig) {
        this.ig = ig;
    }
}
// 자손의 생성자에서 조상의 생성자 중 매개변수 있는 생성자를 호출 원하면 supoer로 지정.
class StarDeco extends GreetDeoo{
    public StarDeco(IGreet ig) {
        super(ig);
    }
    @Override
    public String greet() {
        return "" + ig.greet() + "*";
    }
}
class SharpDeco extends GreetDeoo{
    public SharpDeco(IGreet ig) {
        super(ig);
    }
    @Override
    public String greet() {
        return "" + ig.greet() + "#";
    }
}
public class Test4 {
    public static void main(String[] args) {
        IGreet ig= new SharpDeco(new StarDeco(new HelloGreet()));
        System.out.println(ig.greet());
    }
}
```

```
결과값 : hello*#
```

추가상식 : interface는 public을 명시안해도 컴파일시 자동으로 추가해줌

![uml](..\images\uml.PNG)

> 데코레이터 패턴 UML

# Day4

---



## Method Overloading

```java
class Temp{
    public void print(){};
    public void print(int i){
        System.out.println(1);
    };
    public void print(double i){
        System.out.println(2);
    };
    public void print(int i, int j){
        System.out.println(3);
    };
}
public class Test40 {
    public static void main(String[] args) {
        Temp t= new Temp();
        t.print();
//        float j = 3.14;     큰타입 Double(3.14) -> 작은타입 float(j)로 바로 변환 x
        float i = (float) 3.14; //방법 1  강제형변환
        float j = 3.14f; //방법2  float = float
        t.print(j);// 매개변수가 딱 맞지 않으면 가장 가깝게 자동형변환 되는것을 찾아서 호출
    }
}

```

```
결과값 : 2
```

하나의클래스 안에 이름은 같은데 매개변수 형태가 다른 함수가 여러개 공존가능

```java
class A{
    int i = 100;
}
class B extends A{
    int i = 200;
    void print(){};
}
public class Test40 {
    public static void main(String[] args) {
        A t1 = new B();
        System.out.println(t.i);
        //t 포인터가 가리키는 인스턴스를 감싸는 B클래스 인스턴스를 t2가 가리킨다.
        B t2 = (B)t;
        t2.print();
        System.out.println(t2.i); //갑자기 i값이 변하므로 private해주는게 좋음
    }
}
```

멤버함수에는 Overriding 이라는 개념이있지만 멤버변수에는 Overriding 개념이 없음

![extendsflow](..\images\extendsflow.png)

> 상단 코드의 개략

형변환을 했을때 예기치못한 변수의 변화가 생길 수 있으므로 같은이름으로 하는것은 금지!

클래스 안에 올 수 있는것

(변수 선언, 함수 선언)을 할 수 있고 이렇게 선언된 걸 (멤버 변수, 멤버 함수)이다.



1. 실행시키면 main이 먼저 올라오고
2. static으로 선언되면 클래스가 메모리에 올라올때 인스턴스가 생성되기전에 메모리를 할당받음

자바 실행환경은 클래스가 필요해지면 . class 파일을 메모리에 올린다. 그 후에 인스턴스 생성이 가능하다.

+ static 멤버는 클래스 로딩시에 메모리를 할당받는다. 무조건 유일하다.

+ non-static 멤버는 인스턴스가 생성 될때 메모리를 할당받는다. 인스턴스마다 따로따로 존재

instance가 100번 생성되도 class는 설계도이니 최초에 한번만 올라옴 이때 스태틱은 전역변수처럼 1개만 올라옴

```java
class Temp{
  static int i = 100;
  static void print(){
      System.out.println(i);
  }
  void func(){
      System.out.println("hi");
  }
  static class Temp2{
      private int i = 3;
      public void getI(){
      }
      static void print(){
          System.out.println("hi");
      };
  }
}
public class Test40 {
    public static void main(String[] args) {
        int j = Temp.i;
        Temp t = new Temp();
        t.func();
        Temp.print();
        Temp.Temp2.print();
    }
}// static 멤버는 클래스명. 심볼 형태로 접근

```

```
결과값 : hi  100 hi
```

> Static 멤버함수 안에서는 non-static한 멤버함수 멤버변수에 접근 할 수 없다.
>
> 메모리에 아직 안올라와있기 때문

(  abstract 클래스는 인스턴스를 생성 할 수 없다.  )

---

## 복습

+ class 
+ instance
+ 로컬변수 함수가 호출될 때 메모리할당
+ 멤버변수 클래스내에 선언된 변수 인스턴스가 생성될떄 메모리 할당(static이 아닌경우)
+ 매개변수 일종의 로컬변수로 함수호출시 생성
+ 참조형변수 인스턴스를 가리키기위한 용도 : class 이름으로 선언
+ 자료형변수 byte short int long float double boolean char
+ 생성자함수 인스턴스가 생성될떄 호출 되는 함수, 주로 멤버변수를 초기화하는데 사용

...

- extends 인터페이스 외 클래스 상속 1개만가능
- implements 인터페이스 상속
- interface(abstract만 가지고 있는 클래스) 
- abstract class(하나의 abstract method가 있는경우 무조건)  
- abstract method(선언은 되었지만 구현은 없는경우)
- overriding( 부모로부터 상속)
- overloading( 같은 이름의 메소드 다른 매개변수)
- 자료형변수_캐스팅 : 큰 형태에서 작은형태 x 작은 형태에서 큰형태 o
- 참조형 변수_캐스팅 (큰박스가 작은박스를 감싸는 모양)
- 자료형 변수의 대입 : 오른쪽의 값을 복사해서 왼쪽에 대입
- 참조형 변수의 대입 : 오른쪽이 가리키는 대상이왼쪽을 가리킴
- private : 나만쓸수있음
- public : 나쓸수있고 참조형변수도되고 자손도가능
- protected : 나쓸수있고 자손도가능
- friendly(default) : 같은패키지에서는 public 다른패키지에서는 private
- `A t = new B() ` 조상의 참조형 변수로 자손을 만드는것은 가능하고, 조상의 함수를 호출한 경우 자손의 함수가 호출되므로 오버라이딩 된 구현부가 호출이됨

## JAVA의 배열

```java
public class Test045 {
    public static void main(String[] args) {
        int[] i = new int[]{1,2,3,4};  // new를 썼으므로 자바의 배열은 instance
        for (int j: i) {               // i는 고로 참조형 변수
            System.out.println(j);
        }
        System.out.println(i.length); //그래서 멤버변수를 가지고 있음
    }
}// 배열 : 동일한 형태의 기억잦ㅇ소가 연속으로 할당된 기억공간
```

```
결과값 : 1 2 3 4 4
```

`int[] i = new int[]{1,2,3,4};` 는 `int[] i = {1,2,3,4};` 로 축약해서 사용할 수있다.

```java
        for (int j = 0; j < i.length; j++) {
            System.out.println(i);
        }
```

>  for문으로 구현할시는 요로케

```java
        for (int j = 0; j < i.length; j++) {
            if( j == 2){
                break;    // continue;
            }
            System.out.println(i);
        }
```

+ `break;` 는 반복문을 탈출
+ `continue;` 이하의 코드는 수행을 건너뛰고 반복을 계속

## Object Class

```java
class Temp{
}
// java에서는 기본적으로 제공되는 클래스들이 많은데
// java,lang 패키지에 소속된 클래스는 import 없이 사용 가능하다.
//(가장 기본적인 클래스모음, 막 가져다 써도 된단 이야기)
public class Test048 {
    public static void main(String[] args) {
        Object t = new Temp();
        System.out.println(t.toString());// toString()은 Object에 선언되었고, 상속되었다.
        Object t2 = new Temp();
        System.out.println(t2.toString());
    }
}
```

```
결과값 : Temp@460141958  Temp@1163157884
```

>Temp@1b6d3586  : 클래스명@해시코드값(인스턴스가 다르면 숫자값 다름)

조상 클래스를 지정하지 않으면 Object로부터 상속받는다. Object는 모든 클래스의 조상

 Object는 어떤타입의 인스턴스도 가르킬수있다. (조상이니까)

toString()은 Object.class에 선언되었다.

## Getter Setter

```java
class Temp{
    private int data = 100;
    public int getData(){
        return data;
    }
    public void SetData(int data) {
        this.data = data;
    }
}
```

멤버함수는 private을 권장. 값을 읽을 때는 getter 함수를 제작해 쓴다.

인스턴스 내의 변수값을 읽기 전용으로 하려면? getter만 만들어준다.

인스턴스내의 변수값을 바꾸고 싶을때는 setter 를 쓰는것이 예의.

```java
class Temp{
    private Object data = null;
    public Object getData(){
        return data;
    }
    public void setData(Object obj){
        this.data = obj;
    }
}
```

> Object 클래스의 getter setter

### 주의점

```java
class Temp{
    private int data = 100;
    public int getData(){
        return data;
    }
}
class Temp2 extends Temp{
    private int data = 200;
}
public class Test048 {
    public static void main(String[] args) {
        Temp t = new Temp();
        System.out.println(t.getData());
        Temp t2 = new Temp2();
        System.out.println(t2.getData());
        Temp2 t3 = new Temp2();
        System.out.println(t3.getData());
    }
}
```

```
결과값 : 100 100 100
```

```java
class Temp{
    private int data = 100;
    public int getData(){
        return data;
    }
}
class Temp2 extends Temp{
    private int data = 200;
    public int getData(){
        return data;
    }
}
public class Test048 {
    public static void main(String[] args) {
        Temp t = new Temp();
        System.out.println(t.getData());
        Temp t2 = new Temp2();
        System.out.println(t2.getData());
        Temp2 t3 = new Temp2();
        System.out.println(t3.getData());
    }
}
```

```
결과값 : 100 200 200
```

## String 클래스

```java
public class Test048 {
    public static void main(String[] args) {
        String t = "HelloWorld";
        String t2 = "HelloWorld";
        System.out.println(t == t2);
        System.out.println(t.equals(t2));
        System.out.println(t.hashCode());
        System.out.println(t2.hashCode());
    }
```

```
결과값 : true true 439329280 439329280
```

“”(쌍따옴표) 를 만나면 VM은 StringPool을 뒤져서 없으면 만들고, 있으면 재활용

StringPool을 먼저뒤지고 없으면 만듬

웹 프로그래밍에 매우 유용하다. HTML 내용을 String으로 만들고 재활용 하는 쪽이 메모리 관리에 유용하다.

![stringpool](..\images\stringpool.png)

```java
public class Test048 {
    public static void main(String[] args) {
        String t = "HelloWorld";
        Object o = "HelloWorld";
        String  r = (String) o; // <-------
    }
}
```

String으로 캐스팅  

```java
class Temp{
    private Object data = null;
    public Object getData(){
        return data;
    }
    public void setData(Object obj){
        this.data = obj;
    }
}
class Temp2{
    private String data = null;
    public String getData(){
        return data;
    }
    public void setData(String obj){
        this.data = obj;
    }
}
public class Test048 {
    public static void main(String[] args) {
        Temp2 t2 = new Temp2();
        t2.setData("HelloWorld"); //스트링만 인스턴스로 저장 할 수 있음 (스트링은 상속불가)
        String l2 = t2.getData();  //casting 불필요

        Temp t = new Temp();
        t.setData("HelloWorld"); //모든 인스턴스로 저장할 수 있음
        String l = (String) t.getData();   //캐스팅 필요 
    }
}
```

## Generic

```java
class Temp< T extends Object>{
    private T data = null;
    public T getData(){return data;}
    public void setData(T data){this.data = data;}
}
public class Test048 {
    public static void main(String[] args) {
        Temp<String> t = new Temp<String>();
        t.setData("HelloWorld");
        System.out.println(t.getData());

        Temp<Object> t2 = new Temp<Object>();
        t2.setData("HelloWorld");
        String l2 = (String) t2.getData();
        System.out.println(t2.getData());
    }//인스턴스 내부의 자료형을 동적으로 지정할 수있다. : 제네릭
    //1.5버전부터 지원되었다. < > 안에 지정 가능한 타입은 참조형 변수 타입이어야한다.(자료형 안됨)
}//c++의 템플레이트가 이거와 같음. 차이점은 C++은 자료형도 <>안에 허용
//System.out.print로 호출하면 자동으로 toString이 호출되도록 약속되어 있다.
```

```
결과값 : HelloWorld HelloWorld
```

```java
// Wrapper Class : 자료형 값을 감싸주는 가벼운 클래스
// int -> Integer 라는 클래스가 있음
// double -> Double 이라는 클래스 있음
public class Test054 {
    public static void main(String[] args) {
        Object ob = new Integer(100);
        Object ob2 = new Double(3.14);

        int i = ((Integer) ob).intValue();
        System.out.println(i);

        double j = ((Double)ob2).doubleValue();
        System.out.println(j);
    }
}
```

### AutoBoxing

```java
        Integer i = 100;
        Object t = 200;
        System.out.println(i.getClass().getName());
        System.out.println(t.getClass().getName());
```

```
결과값 : java.lang.Integer java.lang.Integer
```

컴파일러가 `Integer i = new Integer(100);`로 자동바꿈.

값을 Wrapper클래스에 대입하는 코드는 자동으로 인스턴스를 생성해서 값을 감싸줌 이걸 **Auto Boxing**이라고함

`Object t = 200;` Object형 변수에서도 AutoBoxing은 동작함   JDK 1.5에서 추가됨

### AutoUnboxing

```java
        Integer i = 100;
        int j = i;  //i.intValue();
        System.out.println(j);
```

```
결과값 : 100
```

컴파일러가 `Integer i = new Integer(100);` 로 자동바꿈 (Unboxing)

```java
        Object t = 200;
        int k = t; <--- 에러남
```

Object타입으로 오토박싱된 인스턴스는 언박싱 안된다. (t.intValue() 호출할 수 없다.)   JDK 1.5에서 추가됨

### Command Pattern

```java
interface ICalc{
    public int execute(int i);
}

class AddCalc implements ICalc{
    private int data;
    public AddCalc(int data) {
        this.data = data;
    }

    @Override
    public int execute(int i) {
        return i+data;
    }
}
// 빼기를 할떄는 **를 붙여서 프린트 하라고 시키고싶다.
// 일시적으로 시키고 싶은경우가 있더라.
//엣날 프로그래머들은 빈 인터페이스를 이럴 때 이용했다.  이것이 @어노테이션
//쓰임 당하는 입장에서 쓰는 쪽에게 어떤 의견을 제시하는 통로가 필요할 때
// 아래의 빈 인터페이스를 이용하면 상당히 괜찮다.
// 이것을 체계화 한 것이 1,5 이후에 도입된 Annotation 개념이 된다.
interface PrintStarts{} //어노테이션 인터페이스의 조상뻘 개념
class MinusCalc implements ICalc, PrintStarts{
    private int data;
    public MinusCalc(int data) {
        this.data = data;
    }
    @Override
    public int execute(int i) {
        return i-data;
    }
}
public class Test054 {
    public static void main(String[] args) {
        ICalc i = new AddCalc(3);
        int r = 5;
        r = i.execute(r);
        System.out.println(r);

        ICalc[] ls = new ICalc[5];
        ls[0] = new AddCalc(2);
        ls[1] = new MinusCalc(3);
        ls[2] = new AddCalc(6);
        ls[3] = new MinusCalc(1);
        ls[4] = new AddCalc(7);

        int y = 10;
        for (int j = 0; j < ls.length; j++) {
            y = ls[j].execute(y);
            if(ls[j]instanceof PrintStarts){
                System.out.print("**");
            }
            System.out.println(y);
        }
    }
}
/*
일반적으로 자료값은 변수로, 동작은 함수로 만든다.
동작을 하나의 인스턴스로  수행하게 하는 경우가 있다.
- 이런 설계기법을 Command Pattern이라고 한다.
리스트같은데 인스턴스들을 저장하여 매크로처럼 사용 할 수 있다.
 */
```

> 어노테이션 기반이 된 빈 인터페이스

### Build

```java
class Temp1{
    int data = 0;
    // 아래 코드의 t와 this가 가리키는 대상은 같다.
    // 따라서 t.add(10)은 10을 더한 후에 t로 바꿔 쓸 수 있다.
    // StringBuffer의 append에서 볼 수 있다.
    Temp1 add (int i){
        data += i;
        return this;
    }
}
public class Test057 {
    public static void main(String[] args) {
        Temp1 t = new Temp1();
        t.add(10).add(10).add(100);
        System.out.println(t.data);
    }
}

```

```
결과값 : 120
```



## StringBuffer Class

```java
public class Test057 {                                   
    public static void main(String[] args) {         
        StringBuffer sb = new StringBuffer();             
        sb.append("apple");       
        sb.append("banana");        
        String l = sb.toString();       
        System.out.println(l);                                                                       
        System.out.println("apple" + "banana");         
    }                     
}                            
```

```
결과값 : applebanana applebanana
```

`System.out.println("apple" + "banana");  ` 코드는 컴파일러가 `new StringBuffer().append("apple").append("banana").tosString ` 방식으로 동작시키므로 메모리 효율이 매우 떨어짐 아래코드는 한 줄 마다 new StringBuffer가 동작하지만 위의코드는 한번만 동작.

#### String vs StringBuffer Test

```java
public class Test057 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 100000; i++) {
            sb.append("apple");
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        start = System.currentTimeMillis();
        String st = new String("");
        for (int i = 0; i < 100000; i++) {
            st += "apple";
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
```

```
결과값 : 8  11645
```

`java -verbosegc Test057` 통해 가비지 콜렉터 과정을 볼 수 있음

메모리가 부족할 때 메모리를 비우고 확보하는 일을 모니터링 하게 된다.

이런 방법으로도 확인 가능

## String

### substring();

```java
        String l = "HelloWorld";
        System.out.println(l.substring(2,5));
```

```
결과값 : llo
```

### indexOf();

```java
        String l = "HelloWorld";
        System.out.println(l.indexOf("or"));
```

```
결과값 : 6
```

> 만약 없는 문자열을 찾을때는 -1을 리턴한다.
>
> 문자열 안의 부분 문자열의 위치를 리턴 : 여기서는 6

### startsWith(); / endsWirh();

```java
	    String l = "HelloWorld";
        System.out.println(l.startsWith("Hell"));
        System.out.println(l.endsWith("ld"));
```

```
결과값 : true true
```

> 시작이나 끝 문자열이 매개변수값과 같으면 true 아니면 false

### toCharArray();

```java
        String l = "HelloWorld";
        char[] ch = l.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            System.out.println(ch[i]);
        }
```

```
결과값 : H e l l o W o r l d
```

> String을 char 배열로

## 문제

### 배열의 최대값 구하기

```java
public class Test045 {
    public static void main(String[] args) {
        int[] i = new int[]{4,9,6,5};
        int max = i[0];
        for (int j = 1; j < i.length; j++) {
            if(max < i[j]){
                max = i[j];
            }
        }
        System.out.println(max);
    }
}
```

```
결과값 : 9
```

### Generic Linked List

```java
package node;
class Node<T>{
    T data = null;
    Node<T> next = null;
    Node(T i, Node<T> n){
        data = i;
        next = n;
    }
}
class LinkedList<X>{
    private Node<X> head = null;
    private Node<X> tail = null;
    public LinkedList() {
        this.head = new Node<>(null, null);
        this.tail = head;
    }
    public LinkedList<X> add(X input){
        tail.next = new Node<>(input, null);
        tail = tail.next;
        return this;
    }
    public void print(){
        for(Node<X> n = head.next; n != null; n = n.next){
            System.out.println(n.data);
        }
    }
}
public class MyLinkedList {
    public static void main(String[] args) {
        LinkedList<String> ll= new LinkedList<>();
        ll.add("test").add("this").add("how");
        ll.print();
        LinkedList<Integer> l2 = new LinkedList<>();
        l2.add(3).add(2);
        l2.print();

    }
}
```

### indexOf(); 구현

```java
package index;

class Finder{
    public static int indexOf(char[] operand, char[] operator) {
        int odLenth = operand.length;
        int otLenth = operator.length;
        int idx = -1;
        for (int remainder = odLenth - otLenth;  remainder >= 0; remainder--) {
            if(operand[remainder] == operator[0]){
                for(int i=1; otLenth-1 >= i; i++){
                    if(operand[remainder+i] == operator[i]){
                        if(i == otLenth-1){
                            idx = remainder;
                            break;
                        }
                    }else {
                        break;
                    }
                }
            }
        }
        return idx;
    }
}
public class MyIndexOf {
    public static void main(String[] args) {
        int idx = Finder.indexOf("Hedoerrdwld".toCharArray(), "sdadsadsadorw".toCharArray());
        System.out.println(idx);  //String의 indexOf와 동일한 동작을 하도록 만들어 주세요
    }
}

```

# Day5

---

