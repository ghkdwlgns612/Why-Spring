package why.spring.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@WebServlet(name = "requestParamServlet",urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String username = request.getParameter("username");
//        Enumeration<String> parameterNames = request.getParameterNames();
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        String[] usernames = request.getParameterValues("usernames");

        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paraName -> System.out.println(paraName + "= " + request.getParameter(paraName)));
        System.out.println("[전체 파라미터 조회] - end");

        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = request.getParameterValues("username");
        for(String name : usernames) {
            System.out.println("name = " + name);
        }

        response.getWriter().write("OK");
    }
}
