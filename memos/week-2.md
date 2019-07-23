[TOC]

# Day1

---

## java.io

```java
//package - import - class 선언식으로 온다

package javaclass;

import java.io.OutputStream;

public class Test076 {
    public static void main(String[] args) {
        OutputStream out = null;
    }
}

//패키지가 있는 폴더 javac -d . Test076.java
//java -classpath . javaclass.Test076
```

```java
package javaclass;

import java.io.FileNotFoundException;
import java.io.FileOutputStream; //import의 단위는 class, 다른패키지에 있는 class가져다 쓸때 필요
import java.io.OutputStream;

public class Test076 {
    public static void main(String[] args){
        try {//FileOutputStram의 생성자가 에러를 던지고 있기때문에 이걸 호출하는 코드는 try-catch문에 적어야함
            OutputStream out = new FileOutputStream("a.dat");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

throws XXXException 형태로 선언된 함수는 XXXException이 깐깐할 경우에 해당 예외를 처리할 수있는 try-catch 영역 안에서 사용해야한다.

깐깐하다 : 컴파일시 에러가 난다.

```java
 try {
            OutputStream out = new FileOutputStream("a.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
```

FileNotFoundException를 IOException로 바꿔도 IOException이 조상이기 떄문에 자손의 인스턴스를 가리킬 수 있고 때문에 에러가 나지않는다.

``` java
package javaclass;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Test076 {
    public static void main(String[] args) throws IOException {
            OutputStream out = new FileOutputStream("a.dat");
    }//발생되는 에러를 잡으면 에러가 일어난게 아님 프로그램 안죽음
}
```

main(String[] args) throws IOException 이 문법 에러가 아닌 이유

1. 에러는 발생시에 잡아주면 에러 발생 안된걸로 한다. (일사부재리)

2. 에러가 발생할 수 있으면 그 사실을 명시하면 된다.

   (생상자 호출한 쪽이 아니라 main을 호출한 쪽이 처리한다.)
   ex) 사원이 사고치는데 대리는 그책임을 부장에게 넘기고 부장이 처리한다.

``` java
public class Test077 {
    public static void main(String[] args)throws IOException {
            OutputStream out = new FileOutputStream("a.dat");
            out.write(65);  //A
            out.write(66);  //B  참고로 256 집어넣으면 0이 들어가고 -1을 넣으면 255가 들어감
            out.write(67);  //C
            out.close();
        }
}
```

+ FileOutputStream : 파일에 저장하는 방법을 제공한다.

+ OutputStream : 내보내는 방법을추상화 한 클래스(이걸 상속받은 클래스는 이걸로 가리킬 수있고, 이것에 선언 된 것만 쓴다)

+ write : 한번에 1바이트를 내보낸다. 오버라이딩 된 write가 호출된다(api보니까), 오버라이딩 된 write는 매개변수의 값을 a.dat 파일에 저장하게 된다.

+ out.close(); : 내보내는 통로를 닫고 뒤처리를 해 준다.

  `AppleOutputStream extends Outputstream ...` 형태로 선언되어졌다고 셈 치면, 인스턴스만 바꾸면 나머지는 바꿀 필요가 없다.

``` java
package javaclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test079 {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("a.dat");
        int a= in.read();
        int b= in.read();
        int c= in.read();
        int d= in.read();
        int e= in.read();
        in.close();

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
    }
}
```

```
결과값 : 65 66 67 -1 -1
```

+ `in.read()` :한 바이트씩 읽어드리는 역할

  더 읽을값이 없을때 -1이 나온다.

### Stream( Input/ Output ) 개념

+ byte단위로 전송
+ 순서대로읽고 순서대로 내보냄
+ 중간부분만 따로읽고 내보내는건 없다. 무조건 처음부터 끝까지!
+ 다 썻으면 반드시 close 호출

### 파일 복사 / 읽기

```java
        // 복사 : dir *.dat로 확인하고 type b.dat로 확인하고
        while (true) {
            int r = in.read();
            if (r == -1){
                break;
            }
            out.write(r);
        }
```

``` java
		//많이 쓰는방법1
        int r = 0;
        while ((r = in.read()) != -1){
            out.write(r);
        }
```

```java
        //많이 쓰는방법2
        int r = 0;
        byte[] buf = new byte[1024 * 8]; //64bit = 8 byte  //1024bit = 1kb
        while ((r = in.read(buf)) != -1){
            out.write(buf, 0, r);
        }
```

+ 버퍼링 : 한꺼번에 읽고 내보낸다
+ `int read(byte[] but)` : buf가 감당 가능한 만큼 읽고, 읽은 데이터 갯수 리턴;
+ `void write(byte[] buf, int s, int r)` : buf의 내용을 내보낸다. s부터 r만큼

## Socket 통신

```java
package javaclass;

import java.io.IOException;
import java.net.Socket;

public class Test083C {
    public static void main(String[] args) throws IOException {
        Socket skt = new Socket("192.168.2.70", 1123);
                // OutputStream으로 상속받은 무언가를 생성하고 리턴. out을 가리킴
        OutputStream out = skt.getOutputStream();

        out.write(65);
        out.flush();

        skt.close();
    }
}
```

네트워크 버퍼 언제 비워지냐 : 버퍼가 꽉찼을 때 , flush(); 쓸때, close();쓸때

```java
package javaclass;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Test083S {
    public static void main(String[] args) throws IOException {
        ServerSocket svr = new ServerSocket(1123);
        System.out.println("Before accept()");
        Socket skt = svr.accept();

        System.out.println("After accept()");

        skt.close();
        svr.close();
    }
}
```

+ 대기하는 쪽 : 서버, 찾아가는 쪽 : 클라이언트

  먼저 서버가 구동한다 - 포트를 물고 구동해야한다. (1123)

  accept : 대기하다가 클라이언트가 찾아오면 소켓을 생성해서 리턴

클라이언트가 찾아가기위해 IP, PORT번호 줌

잘 찾아가지면 서버와 클라이언트 모두 Socket이 생성됨 이 둘은 통신이 가능함

```java

import java.io.*;

public class Test084 {
    public static void main(String[] args) {
        try (// java.io 패키지가 데코레이터 패턴이라는 설계기법으로 구현되었다.
             OutputStream out = new BufferedOutputStream(new FileOutputStream("d.dat"));
             InputStream in = new BufferedInputStream(new FileInputStream("c.dat"));
        ) {
            int r = 0;
            while ((r = in.read()) != -1){
                out.write(r);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

out.write 호출했을때 대상은 FileOutputStream 에서 지정한다.

BufferedOutputStream에서 버퍼링을 제공한다.

두 클래스가 결합된 결과를 만드는데 사용자는 OutputStream에서 지정하는 함수만 사용하면 된다.

```java
package javaclass;

import java.io.*;

public class Test085 {
    public static void main(String[] args) {
        int a, b, c;
        // 이게 왜 깨지는지 ? 4바이트 다 보내는게 아니더라
        try (OutputStream out = new FileOutputStream("d.dat");
             InputStream in = new FileInputStream("d.dat");
        ){
            out.write(10101);
            out.write(10102);
            out.write(10103);

            a = in.read();
            b = in.read();
            c = in.read();

            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

```java
package javaclass;

import java.io.*;

public class Test085 {
    public static void main(String[] args) {
        String a = null, b = null, c = null;
        // 데코레이터 패턴으로 이 방법을 이해하면 out이 가리키는 대상은
//        FileOutputStream("d.dat")에저장하되 ObjectOutputStream에서 제공하는 방법을 사용하게 된다.
        //ObjectOutputStream은 writeInt writeDouble writeUTF등을 제공 - 전송시에 안깨진다.
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("d.dat"));
        ){
            out.writeUTF("10101");
            out.writeUTF("10102");
            out.writeUTF("10103");

        } catch (Exception e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("d.dat"));){
            a = in.readUTF();
            b = in.readUTF();
            c = in.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }
}
```

```java
package javaclass;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;

public class Test086 {
    // OutputStream InputStream : 전송단위 byte : 바이너리 파일 전송용
    // Reader Writer : 전송단위 Char : 문자로 된 데이터 전송용
    // char는 2바이트이고, 유니코드를 지원한다.
    // 유니코드는 모든 글자를 다 포용하지못한다. (6만자제한)
    // 웬만한 글자는 포용한다. 한글 + 영문 + 중국어 + 아랍어 + 일본어...
    // 확장 가능한 가변길이를 가지는 문자체제를 도입 : UTF-8 (웹 표준)
    public static void main(String[] args) {
        try(
                Writer out = new FileWriter("a.txt")
                ){
            out.write('한');
            out.write('글');
            out.write('林');
        }catch (Exception e){
            e.printStackTrace();
        }

        try(
                Reader in = new FileReader("a.txt");
        ){
            System.out.println((char)in.read());
            System.out.println((char)in.read());
            System.out.println((char)in.read());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
```

### 파일이 있는지

```java
package javaclass;

import java.io.File;

public class Test089 {
    public static void main(String[] args) {
        File f = new File("a.txt");
        boolean b = f.exists();
        System.out.println(b);
        if(b){
            //파일이 길이를 리턴한다. (long 형 자료에 주의)
            System.out.println(f.length());
        }
    }
}
```



## 문제

### 성적처리

```java
package javaclass;

import java.io.*;
class Score<T>{
    String stdId = null;
    String score = null;
    Score<T> next = null;

    Score(String i,String j, Score<T> n){
        stdId = i;
        score = j;
        next = n;
    }
}
class LinkedList<X>{
    private Score<X> head = null;
    private Score<X> tail = null;

    public LinkedList() {
        this.head = new Score<>(null, null, null);
        this.tail = head;
    }

    public LinkedList<X> add(String input, String input2){
        tail.next = new Score<>(input, input2, null);
        tail = tail.next;
        return this;
    }

    public String print(){
        StringBuffer sb = new StringBuffer();
        for(Score<X> n = head.next; n != null; n = n.next){
            sb.append(n.stdId + ":" + n.score +",");
        }
        return sb.toString();
    }
}

public class Test087 {
    public static void main(String[] args) {

        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ) {
            String sl = null;
            LinkedList<Score> l = null;
            String str = null;
            while (true) {
                System.out.println("[ M E N U ]");
                System.out.println("1. 새 자료");
                System.out.println("2. 자료 입력");
                System.out.println("3. 파일로 저장");
                System.out.println("4. 파일에서 불러오기");

                sl = br.readLine();
                switch (sl) {
                    case "1":
                        l = new LinkedList<Score>();
                        break;
                    case "2":
                        System.out.println("학번, 점수를 입력하세요.");
                        sl = br.readLine();
                        l.add(sl.split(",")[0],sl.split(",")[1]);
                        break;

                    case "3":
                        System.out.println("case 3");
                        str = l.print();
                        sl = br.readLine();

                        String title = sl;

                        BufferedWriter save = new BufferedWriter(new FileWriter(title+".txt"));
                        save.write(str);
                        save.close();

                        break;
                    case "4":
                        System.out.println("case 4");
                        sl = br.readLine();
                        String title2 = sl;
                        BufferedReader load = new BufferedReader(new FileReader(title2+".txt"));
                        String[] loadStr = load.readLine().split(",");
                        l = new LinkedList<Score>();
                        for(int i = 0; i<loadStr.length; i++ ){
                            l.add(loadStr[i].split(":")[0],loadStr[i].split(":")[1]);
                        }
                        load.close();
                    case "quit":
                        break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

```

### 서버에서 mp3 받기

```java
package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever {
    public static void main(String[] args) {

        System.out.println("Before accept()");
        try(
                ServerSocket svr = new ServerSocket(1123);
                Socket skt = svr.accept();
                ObjectInputStream ois = new ObjectInputStream(skt.getInputStream());
                OutputStream os = skt.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                ){

            System.out.println("After accept()");
            String title = ois.readUTF();
            File f = new File(title);
            boolean b = f.exists();
            System.out.println(b);
            if(b){
                oos.writeInt(200);
                oos.flush();

                InputStream file = new BufferedInputStream(new FileInputStream(title));
                int r = 0;
                while((r =file.read()) != -1){
                    System.out.println("파일 전송 준비");
                    os.write(r);
                    os.flush();
                }
                file.close();
                //파일이 길이를 리턴한다. (long 형 자료에 주의)
                System.out.println(f.length());
            }else{
                oos.writeInt(404);
                oos.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

```

```java
package socket;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try(
                Socket skt = new Socket("127.0.0.1", 1123);
                ObjectOutputStream oos = new ObjectOutputStream(skt.getOutputStream());
                InputStream is = skt.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
        ){
            oos.writeUTF("music.mp3");
            oos.flush();
            int status = ois.readInt();
            System.out.println(status);
            if(status == 200){
                System.out.println("파일 받기");
                OutputStream file = new BufferedOutputStream(new FileOutputStream("music2.mp3"));
                int r = 0;
                while((r = is.read()) != -1){
                    System.out.println("파일 수신 준비");
                    file.write(r);
                }
            }else{

            }
            ;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

```

# Day2

---

```java
package javaclass;

public class Test092 {
    public static void main(String[] args) {
        String l = "HelloWorld";
        String r = "HelloWorld";
        String t = l.substring( 2, 5);  //""들어간거는 pool에 들어가지만 ""로안들어간다. 포인터 비교가 가능
        // 그 외의 경우 (substirng 등등)은 Pool 을 쓴다는 보장이 없다
        // - 그 때는 equals로 비교한다.

        System.out.println( r == l);
        System.out.println(  "llo" == t);
        System.out.println( "llo".equals(t));

        //문자열을 숫자로 변환시에 : Double.parseDouble 이용
        int r2 = Integer.parseInt("100");
        System.out.println( r2 == 100);

        
        //메모리를 적게 먹음 , 하나있는 경우에는 이방법이 낫다.
        String tl =  "10101,100";
        int idx = tl.indexOf(",");
        System.out.println(tl.substring(tl.indexOf(",")));
        System.out.println(tl.substring(0, 5));//5위치부터 끝까지
        System.out.println(tl.substring(6));
                
        
        //메모리를 많이먹음, 가 여러개있는 경우에는 이방법이 편하다.
        String[] ls = tl.split(",");
        System.out.println(ls[0] + " " + ls[1]);
    }
}

```

## Mysql

```mysql
show database;
```

> 데이터베이스 보여줌

```mysql
create database study;
```

> 데db 만듬

```mysql
use study;
```

> 만든데이터 베이스로

```mysql
create table study01t(
	id int       not null,
    score tinyint not null
);
```

> not null 옵션의 id와 score 컬럼이있는 테이블 생성

```mysql
drop talble study01t
```

> 테이블 삭제

```mysql
insert into study01t values (10101, 100);
insert into study01t values (10102, 90);
```

> 테이블에 값 추가

```mysql
SELECT * FROM study.study01t;
```

> 테이블 조회

```mysql
insert into study01t (score, id) values  (90, 10104);
```

	> 테이블에 컬럼값으로 삽입

테이블에 있어야 하는 개념



1. 필드(Field) - 컬럼  : 자료형을 지정한다 (int, tinyint), 같은자료형의, 같은 의미의 값이 와야한다.

2. 레코드(Record) - Row : 입력의 단위  - 데이터들이 연관되어지는 묶음이어야 한다.

   이 두가지 개념이 있어야 테이블이라 할 수 있다.

```mysql
delete from study01t where id = 10101;
```

> where 값이 10101;인 레코드 지음

```mysql
delete from study01t where id < 10109;
```

> ​	where 조건을 만족하는 값들 전부지움

```mysql
delete from study01t;
```

> 전부지움

```mysql
delete from stud01t where 0 = 1;
```

> 에러 안남 - 그리고 지우지도않음

```java
delete from stud01t where 1 = 1;
```

> 전부 지움

tinyint : 1byte

int : 4byte

```mysql
update study01t set score = 110 where id = 10101;
```

> where 조건을 만족하는것에대하여 set을 동작

```mysql
update study01t set score = socre-10 where id = 10101;
```

​	> 기존의 값을 활용해서 만들 수 있다.

```mysql
update study01t set score = id - 10000 where id = 10101;
```

```mysql
update study01t set score = id - 10000 where id != 10101;
```

> 같은 레코드의 값을 이용해서 사용하게됨

```mysql
update study01t set id = 0, score = 0 where id = 10101;
```

> 2개이상의 Data를 동시에 수정

where 문장 delete, update, select와 연동

```mysql
select * from study01t where id != 10102;
```

> 10102가아니년석 보여줘

```mysql
select id from study01t where id != 10102;
```

> 특정 필드만 보여줄때

```java
select score, id from study01t where id != 10102;
```

> 스코어 아이디 컬럼순서대로 보여줘

```mysql
select score + 5, id from study01t where id != 10102;
```

> select는 데이터를 가옹해서 보여 줄 수 있다.

```mysql
SELECT score + 5 as sungjuk, id FROM study01t;
```

> 컬럼명을 바꾸어서 출력이 가능하다. (보여지는거만 바꾸었다. 이름바뀐거 아님)

```mysql
select score, score as sungjuk from study01t;
```

> 하나의 컬럼을 여러변 출력해도 무방하다.

### 실습준비

```mysql
CREATE TABLE StudentT(
	stId CHAR(5),
	name VARCHAR(9),
	addr VARCHAR(9)
);

INSERT INTO StudentT VALUES('10101','홍길동','역삼동');
INSERT INTO StudentT VALUES('10102','고길동','개포동');
INSERT INTO StudentT VALUES('10103','이기자','역삼동');
INSERT INTO StudentT VALUES('10104','박기자','한남동');
INSERT INTO StudentT VALUES('10105','김영삼','홍제동');
INSERT INTO StudentT VALUES('10106','김대중','한남동');

CREATE TABLE SubjectT(
	subId   CHAR(4), 
	name   VARCHAR(9) 
);

INSERT INTO SubjectT VALUES ('KOR1','국어1');
INSERT INTO SubjectT VALUES ('ENG1','영어1');
INSERT INTO SubjectT VALUES ('MAT1','수학1');

CREATE TABLE ScoreT(
	stId  CHAR(5),
	subId CHAR(4), 
	score TINYINT
);

INSERT INTO ScoreT VALUES('10101','KOR1',60);
INSERT INTO ScoreT VALUES('10101','ENG1',80);
INSERT INTO ScoreT VALUES('10101','MAT1',90);

INSERT INTO ScoreT VALUES('10102','KOR1',90);
INSERT INTO ScoreT VALUES('10102','MAT1',90);
INSERT INTO ScoreT VALUES('10102','ENG1',100);

INSERT INTO ScoreT VALUES('10103','KOR1',70);
INSERT INTO ScoreT VALUES('10104','KOR1',80);
INSERT INTO ScoreT VALUES('10105','KOR1',50);
INSERT INTO ScoreT VALUES('10106','KOR1',60);

INSERT INTO ScoreT VALUES('10103','ENG1',90);
INSERT INTO ScoreT VALUES('10104','ENG1',70);
INSERT INTO ScoreT VALUES('10105','ENG1',60);
INSERT INTO ScoreT VALUES('10106','ENG1',80);

INSERT INTO ScoreT VALUES('10103','MAT1',70);
INSERT INTO ScoreT VALUES('10104','MAT1',70);
INSERT INTO ScoreT VALUES('10105','MAT1',80);
INSERT INTO ScoreT VALUES('10106','MAT1',60);

CREATE TABLE Score2T (
	stId CHAR(5),
	name VARCHAR(9),
	addr VARCHAR(9),
	kor1 TINYINT,
	eng1 TINYINT,
	mat1 TINYINT
);

INSERT INTO Score2T VALUES('10101','홍길동','역삼동',60,80,90);
INSERT INTO Score2T VALUES('10102','고길동','개포동',90,90,100);
INSERT INTO Score2T VALUES('10103','이기자','역삼동',70,90,70);
INSERT INTO Score2T VALUES('10104','박기자','한남동',80,70,70);
INSERT INTO Score2T VALUES('10105','김영삼','홍제동',50,60,80);
INSERT INTO Score2T VALUES('10106','김대중','한남동',60,80,60);
```

```mysql
create table study02t(
id char(5) not null,
name varchar(10) null
);

insert into study02t values('a0001', 'abcd');
insert into study02t values('a0001', 'abcddasdskdsajdk'); --너무 길어서 에러남
insert into study02t values('a00', 'apple');
```

문자열 : ''로 감싼다. char or varchar 자료형

char : 고정길이 문자열 `char(5)` (5글자만 들어감) (학번 주민번호 ...) (빈자리는 스페이스 채움) 3자리써도 5자리씀

varchar: 가변길이 문자열 varchar(10) (10자리까지 들어간다.) 3자리쓰면 3자리씀

둘다 최대길이를 넘게 쓸 수 없다.

char가 처리속도가 훨씬 빠름



### concat

```mysql
select concat(id, '*') from study02t;
```

```
mysql > a0001*
oracle > a01    *      --'a01   '  'a0001' 'a0001 ' char(5)형태시 이렇게 고정길이
```

> mysql 문자열 붙이기



### select

```mysql
SELECT * FROM study.studentt where addr = '역삼동';
```

> 역삼동에 사는 학생 출력

```mysql
SELECT * FROM study.studentt where addr like '역%';
```

> 역으로시작하는 문자로 사는 학생출력

```mysql
SELECT * FROM study.studentt where addr like '%삼동';
```

> 삼동으로 끝나는것

```mysql
SELECT * FROM study.studentt where addr like '%포%';
```

> 포가 들어간 동에 사는 친구

### substirng

```mysql
SELECT substr(addr, 1, 2) FROM study.studentt
```

> 부분문자열 추출

```mysql
SELECT * FROM study.studentt where substr(addr, 1,2) = '역삼';
```

```mysql
select length(addr) from studentt;
```

```
> 9
```

글자 길이. utf-8인 경우는 한글은 3바이트

## aggregate functions : 5가지

==min	max	count	avg	sum== 외워두자

유일한 결과를 출력하는 성격이 있다.

```mysql
select min(score) from scoret where subid = 'KOR1';
```

```
> 50
```

> 50이 여러개라도 50이 하나

```mysql
select avg(score) from scoret where stId = '10101';
```

> 10101 학생의 평균 성적

```mysql
select * from scoret where subId = 'MAT1' and (score=60 or score=80);
```

> 수학에서 60, 80점 받은 학새은 ( or, 괄호)   +++ 필드명 윈도우에서는 대소문자 안가림 (리눅스에서는 가림)

```mysql
select * from scoret where subid = 'MAT1' and score in (60, 80);
```

> 이렇게써도됨

```mysql
select count(*) from scoret where subid = 'MAT1' and score in (60, 80);
```

> 결과 레코드의 개수를 count(*)로 알 수 있다.

studentt, subjectt, scoret는 얽혀있다.

'여러개의 테이블이 연관관계를 가지고 데이터 베이스를 구성'

```mysql
select * from studentt where addr like '%역삼%';
select * from scoret where subid ='KOR1' and stid in('10101','10103');
select * from scoret where subid ='KOR1' and stid in(select * from studentt where addr like '%역삼%');
```

여러개 나오는 결과를 이용할 때는 IN 또는  NOT IN을 사용한다.

하나의 쿼리의 결과를 이용해 다음쿼리를 돌리는걸 sub query라고 한다.

```mysql
select  avg(score) from scoret where subid ='KOR1';
select * from scoret where subid='KOR1' and score <= (select avg(score) from scoret where subid ='KOR1');
```

평균점수이하의 점수를 받은 사람들출력

유일한 서브쿼리 결과와의 비교는 = != < <= > >= 을 쓴다.

서브쿼리는 반드시 괄호로 묶어주어야 한다.

```mysql
select stid from studentt where name like '김%';
select avg(score) from scoret where subid='MAT1' and stid in(select stid from studentt where name like '김%');
```

> 김씨성을가진 학생들의 수학성적평균

```mysql
update scoret set score = score-5 where subid = 'ENG1' and  stid in (select stid from scoret where subid ='ENG1' and score<=70);
```

> 에러남 아래처럼 짜줘야함 왜냐하면

```mysql
update scoret set score = score-5 where subid = 'ENG1' and  stid in (select * from(select stid from scoret where subid ='ENG1' and score<=70) as X);
```

>  mysql 에서 자기자신을 select하면서 update하는것은 안되기 떄문에 이렇게 해줘야함 oracle은 됨

"AGGREGATE FUCTION은 GROUP BY, HAVING과 연동된다."

```mysql
select stid, avg(score) from scoret group by stid;
```

| 10101 | 76.6667 |
| ----- | ------- |
| 10102 | 93.3333 |
| 10103 | 76.6667 |
| 10104 | 73.3333 |
| 10105 | 63.3333 |
| 10106 | 66.6667 |

stid에 동일한 값을 가진 레코드를 짜매어서 평균낸 결과.

group by를 썼을때는 group by에 지정된 컬럼만 select 절에 와야한다.



평균 처리 75이하의 학생

```mysql
select stid, avg(score) from scoret group by stid where avg(score) <= 75;
```

이걸로 될거같지만 avg보다 where가 먼저 실행되므로 에러가남

where은 Aggregate function보다 먼저 수행됨

때문에 having 은 통계 처리 이후에 동작한다. 때문에 가능

```mysql
select stid, avg(score) from scoret  group by stid having avg(score) <= 75;
```

서브쿼리는 크게 3종류로 나뉜다.

1. Where절의 서브쿼리
2. from 절의 서브쿼리( inline view)
3. select절의 서브쿼리(엄청난 결과 & 엄청난 부담)

### from 절의 서브쿼리

select 결과를 마치 table 처럼 보면된다.

```mysql
select stid, round(avg(score), 2) as xx from scoret  group by stid;
```

`round(avg(score), 2)` 소수점 3번째자리에서 반올림

from절의 서브쿼리에서

```mysql
select * from (select stid, round(avg(score), 2) from scoret  group by stid) as xx where xx <= 75;
```

> 될거 같은데 안된다. 데이터 베이스마다 지원안하기도한다 (오라클에서는 됨)
>
> 원래 이런것들은 view라는것을 만들어서 쓰는게 정석이기 때문

```mysql
select * from (select stid, round(avg(score), 2) as avg from scoret group by stid) xx where avg <= 75;
```

> 하지만 평균에 alias를 설정하면 가능

```mysql
create view score2v as select stid, round(avg(score), 2) as xx from scoret  group by stid;
select * from score2v;
```

| 10101 | 76.67 |
| ----- | ----- |
| 10102 | 93.33 |
| 10103 | 76.67 |
| 10104 | 73.33 |
| 10105 | 63.33 |
| 10106 | 66.67 |

view는 테이블은 아니고 결과를 합해서 볼수있는 하나의 창

-> 보여지기만 하는 화면이므로 수정이나 삭제는 무의미하다.

### select절의 서브쿼리

```mysql
select stid, name, (select avg(score) from scoret) as avg from studentt;
```

| 10101 | 홍길동 | 75.0000 |
| ----- | ------ | ------- |
| 10102 | 고길동 | 75.0000 |
| 10103 | 이기자 | 75.0000 |
| 10104 | 박기자 | 75.0000 |
| 10105 | 김영삼 | 75.0000 |
| 10106 | 김대중 | 75.0000 |

레코드 하나마다 서브쿼리 문장도 돌아간다.

````mysql
select stid, name, (select avg(score) from scoret where stid= '10101') as avg from studentt;
````

| 10101 | 홍길동 | 76.6667 |
| ----- | ------ | ------- |
| 10102 | 고길동 | 76.6667 |
| 10103 | 이기자 | 76.6667 |
| 10104 | 박기자 | 76.6667 |
| 10105 | 김영삼 | 76.6667 |
| 10106 | 김대중 | 76.6667 |

10101의 평균을 구하니 76점으로 전부나오게됨

원래는 `select studentt.stid, studentt.name from studentt;` (우리는 생략)

```mysql
select x.stid, x.name from studentt as x;
```

```mysql
select x.stid, x.name, (select avg(score) from scoret where stid= '10101') as avg from studentt as x;
```

| 10101 | 홍길동 | 76.6667 |
| ----- | ------ | ------- |
| 10102 | 고길동 | 76.6667 |
| 10103 | 이기자 | 76.6667 |
| 10104 | 박기자 | 76.6667 |
| 10105 | 김영삼 | 76.6667 |
| 10106 | 김대중 | 76.6667 |

```mysql
select x.stid, x.name, (select avg(score) from scoret where stid = x.stid) as avg from studentt as x;
```

| 10101 | 홍길동 | 76.6667 |
| ----- | ------ | ------- |
| 10102 | 고길동 | 93.3333 |
| 10103 | 이기자 | 76.6667 |
| 10104 | 박기자 | 73.3333 |
| 10105 | 김영삼 | 63.3333 |
| 10106 | 김대중 | 66.6667 |

alias 별명을 지정해줌으로써 각각의 평균점수를 구할 수 있다.

```mysql
create table temp01t as select stid, avg(score) as avg from scoret group by stid;
```

```mysql
create view temp01v as select stid, avg(score) as avg from scoret group by stid;
```

==결과를 view로 가지고 셀렉트를하면 오버헤드가 많이들어감 생성이후에 데이터 반영 됨==

//점수를 바꾸면 view는 값이 변함

==결과를 table로 가지고 셀렉트를하면 오버헤드 많이안들어감 속도 더빠름 생성이후에 데이터는 반영 x==

// 그때의 결과의 복사품이라 값이안변함

### 등수구하기

```mysql
create table temp02t as  select x.stid , x.avg, ( select count(*)+1 from temp01t where avg> x.avg)as ran from temp01t as x;
```

임시테이블 만들면 오버헤드가 적게발생함. 근데 바뀐점수가 바로 바로 적용되지않음

이래서 새로 수정된 테이블들의 오버헤드를 적게만들기위해 수정된 점수는 몇일 뒤에 공개됩니다 라고 하게되는거

///// 추가 rank는 예약어로 되서 workbench에서 돌렸을때는 에러가 난다. 근데 cmd에서 돌리면 됨

```mysql
select * from temp02t order by ran asc;
```

| 10102 | 93.3333 | 1    |
| ----- | ------- | ---- |
| 10101 | 76.6667 | 2    |
| 10103 | 76.6667 | 2    |
| 10104 | 73.3333 | 4    |
| 10106 | 66.6667 | 5    |
| 10105 | 63.3333 | 6    |

asc는 오름차순 생략가능  desc로 주면 내림차순

- 임시테이블과 뷰는 흩어진 데이터에서 자신이 원하는 데이터로 가공 할 수 있는 방법을 제공한다.

  (비정형 데이터에서 정형화된 데이터를 만들어낸다)

- 뷰는 오버헤드가 있지만 데이터의 변경을 즉각 반영한다. 임시테이블은 오버헤드 적지만 데이터의 변경을 즉각 반영 못함

- select 서브쿼리는 오버헤드가 크다.

  (1000명의 등수를 처리한 결과를 1000명이 동시 열람하면 100만건의 쿼리가 동작하는셈 + group by 오버헤드 포함)

- 임시테이블은 이러한 부담을 극적으로 줄여준다

   (도사들은 이걸 기가막히게 잘 쓴다.)

```mysql
create  table study3t study02t(
    no int not null auto_increment primary key,
theTime datetime not null);
```

```mysql
insert into study3t values (default, now());
```

```mysql
SELECT * FROM study.study02t;
```

| a0001 | abcd       |
| ----- | ---------- |
| a0001 | helloworld |
| a00   | apple      |

mysql은 일련번호 auto_increment privmary key 사용  오라클은 sequence 이용

now()는 현재시간, 그것을 입력할때 datatime 자료형을 쓴다.

```mysql
select no, date_add(theTime, INTERVAL 1 MONTH) from study3t;
select no, date_add(theTime, INTERVAL 4 DAY) from study3t;
select no, date_add(theTime, INTERVAL 1 HOUR) from study3t;
```

시간이 추가되는 연산

datetime 자료형에 들어있는 값은 연산이 가능하다

==연산을 해야 할필요가 없을떄는 char를 쓰는게 바람직== 오버헤드가 더 적기 때문

```mysql
create  table study4t(
    no int not null auto_increment primary key,
theTime char(19) not null);
```

```mysql
insert into study4t values (default, now());
```

| 1    | 2019-07-23 17:25:28 |
| ---- | ------------------- |
| 2    | 2019-07-23 17:25:29 |
| 3    | 2019-07-23 17:25:29 |

char로 선언해서 연산은 불가능하지만 오버헤드가 적음

