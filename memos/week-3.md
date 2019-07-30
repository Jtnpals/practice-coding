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

