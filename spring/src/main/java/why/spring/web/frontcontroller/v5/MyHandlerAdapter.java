package why.spring.web.frontcontroller.v5;

import why.spring.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {
    boolean support(Object handler);
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException;
}
