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

