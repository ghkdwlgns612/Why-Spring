package why.spring.web.frontcontroller.v2;

import why.spring.web.frontcontroller.Myview;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV2 {
    public Myview process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
