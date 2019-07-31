[TOC]

# Day1

---

## tomcat

web은 실제로 파일 다운로드  소켓 프로그램과 기본은 같다.



클라이언트 : 웹브라우저

서버: 톰캣 (Web Application Server : Was)

브라웢가 톰캣에 접속해서 서버의 파일을 다운로드 받아 보여준다.

-- 톰캣 서버 하나에는 여러개의 Web Application이 설치가능 하다. 이것을 Context라고 한다

Context가 가져야 하는것 : 홈 디렉토리, 접근 경로, 

C:\StudyHome\ 폴더 생성. (mkdir c:\StudyHome)



클라이언트는 홈디렉토리 아래의 모든 파일을 다운 가능하다. 단 WEB-INF는 접근이 불가능하다. (내부 설정파일 등을 놓는다.)

classes : java class 파일을 놓는 곳

lib : 동작에 필요한 jar파일을 놓는곳

D:\Server\apache-tomcat-8.0.45\webapps\ROOT\WEB-INF 위치에서 web.xml 파일 복사

server.xml 의 Host 아래에 Context를 아래와 같이추가.

```xml
      <Host name="localhost"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">

	<Context docBase="D:\Github\StudyHome" path="/study" reloadable="true"></Context>

```

```java
package apple;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorld2 extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("HelloWorld2");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("HelloWorld 2 : ^^*");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

}
```

```java
//javac -d C:\StudyHome\WEB-INF\classes -classpath servlet-api.jar HelloWorld2.java
// HelloWorld2.java
```

- 서블릿의 동작 흐름

  0. WAS 쪽에 적절히 설정이 되어있다고 가정한다

  1. 브라우저에서 /study/study01 로 요청이 들어온다.

  2. /study 로 경로를 설정한 Context 있나? -있음

  3. 2에서 찾은 Context의 docBase폴더 (C:\StudyHome)아래의 /WEB-INF/web.xml 찾음

  4. 거기의 url-pattern 중  /study01에 해당하는 설정 ? 있음

     이것은 어떤 서블릿 이름을 지명하나? abcd

  5. abcd로 이르므 붙여진 서블릿 클래스가 있나? apple.HelloWorld2

  6. apple.HelloWorld2f를 찾아 -> 인스턴스 생성 -> 오버라이딩 하는 sevice 함수를 호출한다.

  7. `System.out.println("HelloWorld2");` 서버 쪽에서 동작

  8. `PrintWriter out = response.getWriter();`를 이용하여 내보내는 것은 요청으로 들어온 브라우저 쪽으로 내보내진다.

  9. 요청했던 브라우저는 응답으로 전달된 내용을 받아 화면에 출력한다.

```java
package example;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.*;

public class HelloWorld extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "hr", "hr");
            Statement stmt = conn.createStatement();
            String sql = "SELECT SYSDATE FROM DUAL";
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()){
                System.out.println(rs.getString(1));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

```

```java

<?xml version="1.0" encoding="ISO-8859-1"?>

<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1"
  metadata-complete="true">

  <display-name>Welcome to Tomcat</display-name>
  <description>
     Welcome to Tomcat
  </description>

    <servlet>
        <servlet-name>time</servlet-name>
        <servlet-class>example.HelloWorld</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>time</servlet-name>
        <url-pattern>/time</url-pattern>
    </servlet-mapping>

</web-app>

```

## 문제

```java
package apple;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

public class AGOG extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html;charset=euc-kr");
        PrintWriter out = res.getWriter();
        out.println("<table border=\"1\">");

        for(int i=1; i<10; i++){
            out.println("<tr>");
            for(int j=1; j<10; j++){
                String r = Integer.toHexString((int) (Math.random()*256));
                String g = Integer.toHexString((int) (Math.random()*256));
                String b = Integer.toHexString((int) (Math.random()*256));
                out.println("<td bgcolor=\"#"+r+g+b+"\">");
                out.println(i + "x" + j + "=" + (i*j));
                out.println("</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
    }
}

```

# Day2

---

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<!--submit을 누르면, action에 지정된 페이지를 요청한다.-->
<!--form안의 input에 입력된 정보를 물고간다.-->
<!--이 정보는 요청받은 서블릿의 request.getParameter로 추출한다.-->
<!--name에 지정된 값을 키값으로 사용한다.-->
<!--checkbox는 체크 후에 넘기면 value에 지정된 값이 넘어가고 체크없이 넘기면 null 반환-->
<body>
    <form method="POST" action="form_test2">
        <input type="text" name="gul" size="50"/>
        <input type="checkbox" name="abcd" value="apple"/>
        <input type="checkbox" name="xyzz" value="banana"/>
<!--        같은 이름을 가진 radio는 하나만 체크 가능하다.-->
<!--        checked="checked"하면 기본으로 체크됨-->
        <input type="radio" name="method" value="minus" checked="checked"/>
        <input type="radio" name="method" value="plus"/>
<!--        type="test"와 같은데 입력 내용이 보여지지 않는다.-->
        <input type="password" name="pwd" />
<!--        여러줄을 입력할 때 사용한다. HelloWorld와 같이 태그안에 들어가는 내용은 디폴트로 보여준다.-->
<!--        cols="30" rows="10"  cols 좌우  rows 상하-->
        <br/>
        <textarea name="content" id="" cols="30" rows="10">Hello World</textarea>

<!--        흔히 콤보박스라는 선택창이 나오고 그중의 하나를 선택할 수 있다.-->
        <select name="fruit" id="">
            <option value="apple">사과</option>
            <option value="banana">브내으너</option>
            <option value="orange">어우뤤지</option>
            <option value="kiwi">키위</option>
        </select>

        <input type="submit"/>
    </form>
</body>
</html>
```

```java
package apple;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FormTestServlet extends HttpServlet {
    //request : 요청과 관련되어진 함수 모음
    //response 응답과 관련되어진 함수가 모여있다.
    //sendRediredt => 브라우저에게 해당경로로 요청할것을 지시
    //getParameter => 리퀘스트
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String gul = req.getParameter("gul");
        System.out.println("gul :" + gul);
        String abcd = req.getParameter("abcd");
        String xyzz = req.getParameter("xyzz");
        System.out.println(abcd);
        System.out.println(xyzz);
        res.sendRedirect("/study/test_2.html");
        String method = req.getParameter("method");
        System.out.println(method);
        String pwd = req.getParameter("pwd");
        System.out.println(pwd);
        String content = req.getParameter("content");
        System.out.println(content);
        String fruit = req.getParameter("fruit");
        System.out.println(fruit);
        //http://localhost:8081/study/form_test
    }
}

```

## Tokenizer

```java
package main;

import java.util.StringTokenizer;

public class Test107 {
    public static void main(String[] args) {
        String l = "올해당췌 이것은 글을 쓴 내용에 해당하는 #해쉬태그 문장입니다.";
        StringTokenizer st = new StringTokenizer(l); //split 보다 퍼포먼스가 좋음
        while (st.hasMoreTokens()){
            String tkn = st.nextToken();
            System.out.println(tkn);
            System.out.println(tkn.indexOf("해당") != -1);
            System.out.println(tkn.startsWith("해당"));//금칙어는 앞에서 나오는 경우가많음
            if(tkn.charAt(0) == '#'){
                System.out.println(tkn);
            }
        }
    }
}
```

## DAO

```java
package apple;

import java.util.ArrayList;
import java.util.List;

public class BangMyungDAO_FakeImple implements BangMyungDAO {
    private static List<BangMyungVO> data = new ArrayList<>();
    @Override
    public void add(BangMyungVO vo) throws Exception {
        data.add(vo);
    }

    @Override
    public List<BangMyungVO> findAll() throws Exception {
        return data;
    }
}
```

```java
package apple;

import java.util.List;

public interface BangMyungDAO {
    public void add(BangMyungVO vo) throws Exception;
    public List<BangMyungVO> findAll() throws Exception;
}
```

```java
package apple;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//bangmyung_list로 요청이 들어오면 보여지게 세팅해보자
public class BangMyungList extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        BangMyungDAO dao = new BangMyungDAO_OracleImpl();
        BangMyungVO vo = new BangMyungVO();
        List<BangMyungVO> ls = null;
        try {
            ls = dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String gul = req.getParameter("gul");
        vo.setGul(gul);
        try {
            dao.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        res.setContentType("text/html;charset=euc-kr"); //한글이 깨질때 이용


        PrintWriter out = res.getWriter();


        out.println("<html>");
        out.println("<body>");
        out.println("<table border=\"1\" cellspacing='2' cellpadding='6'>");
        int i = 0;

        for(BangMyungVO lvo : ls){

            String color = ((++i % 2) == 0) ? "#aabbcc" : "#ccddee";
            out.println("<tr bgColor='"+color+"'>");
            out.println("<td>");
            out.println(lvo.getNo());
            out.println("</td>");
            out.println("<td>");
            out.println(lvo.getGul());
            out.println("</td>");
            out.println("<td>");
            out.println(lvo.getTheTime());
            out.println("</td>");

            out.println("</tr>");

        }
        out.println("<form method=\"GET\" action=\"bangmyung_add2\">");

        out.println("<input type=\"text\" name=\"gul\" size=\"50\"/>");
        out.println("<input type=\"submit\"/>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

    }

}
```

데이터를 다루는 클래스는 독립시켜서 만드는 경향이 강하다.(별도의 클래스)

A는 데이터베이스와 관련된 기능 구현B는 웹 프로그래밍과 관련된 기능 구현원래대로라면 A끝나고 결과 코드를 B에 넘겨주고 B는 그걸 받아서 일한다.

비효율적 : 동시에 일할수 있음

BangMyungDAO - 방명록에 필요한 데이터 입출력 기능을 모아서 추상화 했다.

:: add, findAllBangmyungDao_FakeImpl : DB 연동을 비슷하게 흉내내줌add를 이용해서 레코드를 쌓고, findAll로 쌓인 레코드를 출력함B는 FackeImpl을 이용하여 작업에 착수한다.

A는 OracleImple을 구현한다. (jdbc 코드 작성)

양쪽이 다 테스트가 끝나서 통합에 들어간다.

Fack -> Oracle로

데이터를 다루는 코드를 독립딘 클래스로 만들 디 그 작업들을 추상화한 인터페이스를 기반으로 만드는 설계기법을 DAO(Data Access Object) Pattern 이라고 한다.

```java
package np;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LifeServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(this.toString());
    }
}
```

```
np.LifeServlet@55763006
np.LifeServlet@55763006
np.LifeServlet@55763006
np.LifeServlet@55763006
np.LifeServlet@55763006
```

호출될 때 마다 같은 주소 리턴.

하나의 인스턴스를 계속 재활용하고 있음

여러사람이 접속해도 인스턴스는 1개만 생김

- 동일한 인스턴스가 계속 재사용되고 있다. : 메모리관리에는 장점이다.
- 한꺼번에 많이 들어와도 적게 메모리를 소모한다.



```java
package np;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LifeServlet extends HttpServlet {
    private  int i = 0;
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println(this.toString());

        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        i=i+1;
        for (int i = 0; i < 20000; i++) {
            out.println(this.i);
        }
        out.println("</html></body>");


        out.flush();
        out.close();
    }
}
```

다음과 같은 코드로 동시 접속이 일어나면 멀티 쓰레드로 동작함

i값이 달라짐 동일한 인스턴스를 참조해서

서블릿을 쓸 때에는 동시에 여러쓰레드가 접근할 수 있음을 염두해 두어야한다.

->해결방법 1 로컬 변수만 사용

-> 동기화

```java
package np;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LifeServlet extends HttpServlet {
    private int i = 0;
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println(this.toString());

        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        //synchronized로 동기화
        synchronized (this) {
            i = i + 1;
            for (int i = 0; i < 20000; i++) {
                out.println(this.i);
            }
        }
        out.println("</html></body>");
        out.flush();
        out.close();
    }
}
```

```java
package np;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfigServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("service");
    }
}

```

```
init
service
service
service
service
```

서블릿 인스턴스는 재활용된다.

인스턴스는 재활용을 위해 내부적으로 적재되는데

적재되는 시점에 호출되는 함수가 init - 최초 요청시에만 호출된다.

```xml
    <servlet>
        <servlet-name>config</servlet-name>
        <servlet-class>np.ConfigServlet</servlet-class>
        <init-param>
            <param-name>apple</param-name>
            <param-value>blabla</param-value>
        </init-param>
    </servlet>
```

```java
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");

        String val = config.getInitParameter("apple");
        System.out.println(val);//web.xml과 연관있음
    }
```

```
init
blabla
```

web.xml에 설정된 내용을 읽어 들일 수 있는 방법을 제공한다.

### WEB-INF의 절대경로

```java
        ServletContext application = config.getServletContext();
        String path = application.getRealPath("/WEB-INF/");
        System.out.println(path);
```

```
D:\Github\untitled\out\artifacts\untitled_war_exploded\WEB-INF\
```

WEB-INF 폴더의 절대경로값을 얻어온다.

브라우저가 접근 못하는 폴더가 업로드 파일을 놓기에 가장 적합하다.

허락받고 (돈내고) 다운 받을 때 FileInputStream으로 읽어 내보낸다.

그 경우에 getRealPath는 유용하게 쓰인다.

```java
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("service");
        String l = req.getContextPath(); //context 경로
        System.out.println(l);
        String m = req.getRequestURI(); //IP주소 다음 나오는 전체경로
        System.out.println(m);
        String n = req.getRemoteAddr();//접속한 브라우저의 IP주소값
        System.out.println(n);
        String o = req.getHeader("User-Agent"); //접속한 브라우저와 운영체제 정보를 담은 문자열이 얻어짐
        System.out.println(o);// 이것을 이용하여 모바일로 접근한 건지 컴퓨터로 접근한건지 알 수 있고
    }// 모바일용 화면과 컴용 화면을 구분하여 제공가능
```

```
service
/study
/study/config
127.0.0.1
Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36
```



# Day3

---

## null

```sql
CREATE TABLE TEMP20T(data char(3) null);
INSERT INTO temp20t VALUES ('abc');
INSERT INTO temp20t VALUES (null);
--INSERT INTO temp20t VALUES ('null'); 4글자 문자열이라 들어가지 못함
```

null은 빈 값을 의미한다.

```sql
--select * from temp20t where data  = null;
select * from temp20t where data is null;
```

null값과의 비교는 is를 이용한다.

```java
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Test110 {
    public static void main(String[] args) {
        String data = "xyz";
//        String data = null;  //data에 null일 때는 에러난다. 문자열로들어가서 캐릭터셋 크기를 벗어나기때문에
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","hr", "hr");
                Statement stmt = conn.createStatement();
        ){ String sql = "INSERT INTO temp20t VALUES ('"+ data +"')";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

조건문으로 널인지 검사

```java
String sql = (data !=null) ? "INSERT INTO temp20t VALUES ('"+ data +"')" : "INSERT INTO temp20t VALUES (null)";
```

컬럼수가 많아지면 이방식으로 감당 불가

2개 컬럼이면 4번 비교 3개면 8개비교 ....

이런 문제때문에 PreparedStatement를 더 선호한다.

아예 char는 무조건 4자리이상 잡게하는 경우도 있다.

## PreparedStatement

```java
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test111 {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","hr", "hr");
                // Statement가 어떤 SQL문장이든 실행하는 범용적인데 반해서
                //PreparedStatement는 생성시에 준비한 그 문장만 실행 할 수 있다.
                // 대신 ? 영역을 setString, setInteger 등을 이용하여 채울 수 있다.
                //(순서가 1부터 시작하는것에 주의)
                //execute시에 매개변수 없음
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO temp20t VALUES ( ? )");
        ){
            stmt.setString(1, null); // null도 잘 들어감
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

null 값을 넣을때 '' 를 붙여야 할지를 결정하기 위해 이런저런 고민을 할 필요가 없어진다.

## String...

```java
package main;

public class Test112 {
    // 매개변수로 String 을 0..* 개를 넣어도 에러가 안나는 선언 방식
    // 실은 String... 은 String[]과 동일하다.
    public static void test(String... args){
        System.out.println(args.length);
    }
    public static void main(String[] args) {
        test("apple");
        test();
        test("apple", "banana");
    }
}
```

## Object...

```java
  public static void test2(Object... args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] == null) {
                System.out.println("null");
            } else if (args[i] instanceof Integer) {
                int r = ((Integer) args[i]).intValue();
                System.out.println(r + 1);
            } else if (args[i] instanceof Double) {
                Double r = ((Double) args[i]).doubleValue();
                System.out.println(r + 0.1);
            } else if (args[i] instanceof String) {
                String r = ((String) args[i]);
                System.out.println(r + 1);
            }
        }
    }

    //Object arg_1 = 100; auto boxing -> 100을 new Integer(100)로 자동변환
    //Object arg_3 = 3.14; auto boxing -> 3.14을 new Double(100)로 자동변환
    //Object arg_4 = null;
    public static void main(String[] args) {
        test2(100, "Hello", 3.14, null);


        test("apple");
        test();
        test("apple", "banana");
    }
}
```

```java
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test113 {
    public static int update(String sql, Object... args){
        int rc = 0;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (
                Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","hr", "hr");

                PreparedStatement stmt = conn.prepareStatement(sql);
        ){
            for (int i = 0; i < args.length; i++) {
                if (args[i] == null) {
                    stmt.setString(1, null);
                } else if (args[i] instanceof Integer) {
                    int r = ((Integer) args[i]).intValue();
                    stmt.setInt(1, r);
                } else if (args[i] instanceof Double) {
                    Double r = ((Double) args[i]).doubleValue();
                    stmt.setDouble(1, r);
                } else if (args[i] instanceof String) {
                    String r = ((String) args[i]);
                    stmt.setString(1, r);
                }
            }

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rc;
    }
    public static void main(String[] args) {
        String sql = "insert into temp20t values (?)";
        update(sql, "lee");
    }
}
```

트랜잭션 처리 문제가 있음

## Anonymous class

```java
package main;

interface ITemp{
    void print();
}

public class Test114 {
    public static void main(String[] args) {
        // 익명 클래스 : ITemp를 상속받고 메소드 오버라이딩
        // 이름이 없어서 재사용은 불가능하다.
        // 클래스 선언하고 인스턴스 생성하고는 끝
        // (A t = new B());
        final int i = 100; //final로 선언된 로컬변수는 anonymous class안에서 사용 가능하다
        int j = 100;
        ITemp t = new ITemp() {
            @Override
            public void print() {
                System.out.println("안녕" + i + j); //이부분은 에러안남
                //j++; // 에러남 값을 변경하는것은 안됨
            }
        };
        t.print();
    }
}

```

## Cookie

```java
package main;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StateServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("hi");
        String use = req.getParameter("use");
        System.out.println(use);

        if("addCookie".equals(use)){
            Cookie ck = new Cookie("name", "apple");
            res.addCookie(ck);
        }else if("readCookie".equals(use)){
            Cookie[] cks = req.getCookies();
            if(cks != null){
                for (int i = 0; i < cks.length; i++) {
                    System.out.println(cks[i].getName());
                    System.out.println(cks[i].getValue());
                }
            }
        }else{
            System.out.println("Cookie is null");
        }
    }// 요청 -> 응답, 요청의 방법이 두가지 GET/POST있다. GET은 엽서, POST는 택배
}//엽서(신속, 경량, 보안x)  택배(느림, 중량, 보안o)
// 엽서의 주소와 내용을 구분하는 선의 역할 : ?
// ?오른쪽의 내용은 key=value 형태로 구성되고 request.getParameter를 이용하여 추출할 수 있다.
//거의 대부분 GET 방식이고, form에서 method="POST"로 지정된 경우만 POST방식
```

Cookie : 장바구니, 로그인정보



### 코드정렬 스타일

Sun style -> 자바스타일

```java
if("addCookie".equals(use)){
            Cookie ck = new Cookie("name", "apple");
            res.addCookie(ck);
        }else if("readCookie".equals(use)){
            Cookie[] cks = req.getCookies();
            if(cks != null){
                for (int i = 0; i < cks.length; i++) {
                    System.out.println(cks[i].getName());
                    System.out.println(cks[i].getValue());
                }
            }
        }else{
            System.out.println("Cookie is null");
        }
```



AT&T -> C 스타일

```java
if("addCookie".equals(use))
	   {
            Cookie ck = new Cookie("name", "apple");
            res.addCookie(ck);
        }else if("readCookie".equals(use))
		 {
            Cookie[] cks = req.getCookies();
            if(cks != null)
            {
                for (int i = 0; i < cks.length; i++) 
                {
                    System.out.println(cks[i].getName());
                    System.out.println(cks[i].getValue());
                }
            }
        }else
         {
            System.out.println("Cookie is null");
        }
```

## Session

```java
public class SessionServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        System.out.println(session.isNew());
    }
}
```

```
true
false
false
```

처음에만 true 

`http://localhost:8081/study/state?use=readCookie` 으로 접속시

```
readCookie
JSESSIONID
176F541B42E932FAE088D1685F597E06
Idea-d69c23e3
6251ddd4-5adb-4f63-9bc4-8322404bdt527
```

세션 동작방식

1. 기억장소 생성 Map<String, Object> wb ->ws
2. 절대로 곂치지 않는 값 생성 ws
3. 쿠키에 쓴다 ws->wb

새 브라우저 들어올떄마다 반복  각각의 브라우저마다 세션 등록

세션은 - 서버쪽에 저장되는 기억공간 브라우저마다 개별적으로 생성해서 쿠키에다 써줌

최초 getSession() 호출시에는 고유넘버가 없이 요청이 들어온다.

- 기억장소생성 Map<String, Object>형태
- 고유넘버 생성
- JSESSIONID 키 값으로 고유넘버를 쿠키에 저장

그 이후에 getSession()호출 : 고유넘버를 물고왔으니그걸로 기억장소를 찾는다.

"브라우저마다 개별적인 기억장소가 생성된다."



쿠키와 세션은 많이 비슷하다 - 브라우저마다 개별적인 기억공간

1. 쿠키는 브라우저에, 세션은 서버에
2. 쿠키는 파일, 세션은 Map<String, Object>에 저장
3. 쿠키는 조작이 가능할 수도 있지만 세션은 어려워..

초반엔 세션, 요즘은 쿠키를 많이쓰는 경향이 있다.

|      | light     | heavy |
| ---- | --------- | ----- |
| WAS  | 오버헤드1 | 27    |
| DB   | 오버헤드1 | 4~8   |

was쪽이 트래픽에 취약하다.

L4 스위치로 요청이 몰림 -> 똑같이 세팅된 was 여러대로 골고루 보냄

분산시켜서 서버 다운을 방지함

이렇게 분산해서 WAS를 여러대 이용하면 각 WAS의 세션 ID를 공유해야하는데 동기화 해주는 과정에서 오버헤드 발생

아니면 한번 들어온 was로만 보내주거나 독립적인 로그인 전용 was를 만드는경우도 있음

Cookie에다 ID를 심으면 이러한 문제를 해결가능

예전에는 분산시스템을 자주 사용하지않았지만 요새는 분산시스템을 많이 쓰게되면서 세션보다 쿠키를 선호하게됨

## JSP

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    int i = 0; <%-- 멤버변수 --%>
%>
<%
    int j = 0; <%-- 로컬변수 --%>
%>
<html>
<head>
    <title>Title</title>
</head>
<body><%-- jsp 주석  html 요청하듯이 요청하면 된다.--%>
<%=i++%>
<%=j++%>
</body>
</html>
```

18 0

새로고침을하면 i만 증가함

jsp 파일을 요청하면 톰캣은 이것을 *.java 파일로 변환한다. (서블릿코드)

이것을 컴파일하고, 인스턴스르 만들어, 적재하고, 인스턴스 재활용한다.(jsp알고보면 서블릿)

"서블릿 이후 ASP의 등장으로 위기를 느낀 자바쪽에서 ASP 비슷하거 만든게 JSP"

```java
package main;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class JSPServlet extends HttpServlet {
    private ServletContext application = null;
    private ServletConfig config = null;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        application = config.getServletContext();
    }
    //--------------------------------------------------------
    //<%! ... %> 은 여기에 온다.
    int i = 0;
    //--------------------------------------------------------
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter out = res.getWriter();
        //--------------------------------------------------------
        //<% ... %> 은 여기에 온다.
        int j = 0;
        //<%= ... %> 은 out.print(...)
        out.print(i++);
        out.print(j++);
        //--------------------------------------------------------
        out.flush();
        out.close();
    }
}
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    out.println(config.toString()); //올바른코드
%>
</body>
</html>
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
//    out.println(config.toString());// 올바르지않은 코드 변수선언 함수선언만가능
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
</body>
</html>
```

```jsp

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    public void print(){
        System.out.println("올바른코드");
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
</body>
</html>
```

## 문제

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    String getColor(){
        char[] chs = "0123456789abcdef".toCharArray();
        StringBuffer sb = new StringBuffer();

        for(int i=0; i<6; i++){
            int idx = (int) (Math.random()*16);
            sb.append(chs[idx]);
        }
        return sb.toString();
    }
%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<table>
    <%
        for(int i = 1; i< 10; i++){
        %><tr><%
            for(int j = 1; j< 10; j++){
        %><td bgcolor="#<%=getColor()%>">
        <%=j%> * <%=i%> = <%=i*j%>
        </td>
    <%
            }
        %>
        </tr><%
        }
    %>
</table>
</body>
</html>
```

랜덤색 구구단

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Exception error = (Exception) session.getAttribute("error");
    StringBuffer sb = new StringBuffer();
    for(StackTraceElement str : error.getStackTrace()){
        sb.append(str.toString());
        sb.append("<br>");
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%=sb.toString() %>
</body>
</html>

```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" import="apple.BangMyungVO, java.util.List" %>
<%@ page import="apple.BangMyungDAO" %>
<%@ page import="apple.BangMyungDAO_OracleImpl" %>
<%
    // 1.변수선언
    List<BangMyungVO> rl = null;
    Exception err = null;
    // 2. DB연동
    BangMyungDAO dao = new BangMyungDAO_OracleImpl();
    try {
        rl = dao.findAll();
    } catch (Exception e) {
        err = e;
    }
    // 3. 흐름 만들기
    if (rl == null || err != null) {
        session.setAttribute("error", err);
        response.sendRedirect("/study/error.jsp");
    } else {
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
<table>
<%--    <%= rl.toString()%>--%>
    <%
        int i = 1;
        for (BangMyungVO lvo : rl) {
            String color = ((++i % 2) == 0) ? "#aabbcc" : "#ccddee";
    %>
    <tr bgColor='<%=color%>'>
        <td><%=lvo.getNo()%></td>
        <td><%=lvo.getGul()%></td>
        <td><%=lvo.getTheTime()%></td>
    </tr>
    <%
        }
    %>
</table>
<form method="post" action="/study/bangmyung_add.jsp">
    <input type="text" name="gul"/>
    <input type="submit"/>
</form>
</div>
</body>
</html>
<%
    }
%>
```

```jsp
<%@ page import="apple.BangMyungVO" %>
<%@ page import="apple.BangMyungDAO" %>
<%@ page import="apple.BangMyungDAO_OracleImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Exception err = null;
    String gul = request.getParameter("gul");

    BangMyungVO pvo = new BangMyungVO();
    pvo.setGul(gul);

    BangMyungDAO dao = new BangMyungDAO_OracleImpl();
    try{
        dao.add(pvo);
    }catch (Exception e){
        err = e;
    }

    if(err != null){
        session.setAttribute("error", err);
        response.sendRedirect("/study/error.jsp");
    }else{
        response.sendRedirect("/study/bangmyung_list.jsp");
    }
%>
<html>
<head>
    <title>Title</title>
</head>
<body>

</body>
</html>
```





