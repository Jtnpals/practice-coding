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

