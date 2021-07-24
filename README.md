# Why-Spring


**자바**를 공부하고 이제 **Spring**을 사용하려고 하는데 이것을 왜 사용하는지 알고 사용하면

효과가 좋을 것 같아서 프로젝트를 수행하였습니다. 아래의 순서로 프로젝트를 진행하였습니다.


### 1. HttpServletRequest, HttpServletResponse의 다양한 활용

이 부분은 HTTP의 요청과 응답을 중심으로 어떤 내용이 오고 가는지와 어떤 내용을 우리가 설정하고 보내야하는지에 대해서 알아봤습니다. 그리고 이것을 이용하여 **GET, POST, HTTP API**를 직접 생성하여 주고 받았습니다.

그러나 여기서 큰 문제는 response시 너무 많은 코드들이 들어가고 로직이 섞여있다는 문제가 있었습니다. 아래의 예시입니다.

```java
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html");
        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write(" <meta charset=\"UTF-8\">");
        w.write(" <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write(" <thead>");
        w.write(" <th>id</th>");
        w.write(" <th>username</th>");
        w.write(" <th>age</th>");
        w.write(" </thead>");
        w.write(" <tbody>");

        for (Member member : members) {
            w.write(" <tr>");
            w.write(" <td>" + member.getId() + "</td>");
            w.write(" <td>" + member.getUsername() + "</td>");
            w.write(" <td>" + member.getAge() + "</td>");
            w.write(" </tr>");
        }
        w.write(" </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }
```

그래서 그것을 분리하기 위해 **JSP**라는 **템플릿 엔진**을 사용하였습니다.


### 2. JSP의 사용

JSP로 HTML코드 내에 Java언어를 집어넣었습니다.

그래도 아직 Java언어와 HTML언어가 구분되지 않아서 불편했습니다.

```java
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
 MemberRepository memberRepository = MemberRepository.getInstance();
 System.out.println("save.jsp");
 String username = request.getParameter("username");
 int age = Integer.parseInt(request.getParameter("age"));
 Member member = new Member(username, age);
 System.out.println("member = " + member);
 memberRepository.save(member);
%>

<html>
<head>
 <meta charset="UTF-8">
</head>
<body>
성공
<ul>
 <li>id=<%=member.getId()%></li>
 <li>username=<%=member.getUsername()%></li>
 <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
```

위쪽에 코드들을 다른 방법으로 전해 줄 수 없을까? HTML내에서 필요한 정보만 전달해주면 된다고 생각했기 때문에 그것을 따로 만들기로 했습니다. 그것이 바로 **Model**입니다.


### 3. Request의 Model화

위의 문제를 Model로 전달해보겠습니다.

Model을 만들기보다는 **Request객체**를 **Model**로 사용하여 전달해주겠습니다.

그러나 공통적인 부분이 생깁니다. 아래의 예시입니다.

```java
RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
 dispatcher.forward(request, response);
```

그리고 **ViewPath의 앞부분이 중복**이되고 response는 코드에서 사용되지 않습니다.

이러한 문제를 해결하기위해서 **Front-Controller**를 5단계로 나누어 만들어봤습니다.


### 4. Front-Controller

ControllerV1 : Controller에서 바로 JSP호출.

ControllerV2 : View의 분리.

ControllerV3 : Model 추가.

ControllerV4 : FrontController의 ModelAndView생성.

ControllerV5 : 무조건 ModelAndView를 반환하게하는 어댑터 개념 적용.


### 5. Spring MVC적용

실제 @RequestMapping, ModelAndView를 사용하여 위의 컨트롤러 및 로직구현.
