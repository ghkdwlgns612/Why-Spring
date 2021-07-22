package why.spring.web.frontcontroller.v2.controller;

import why.spring.web.frontcontroller.Myview;
import why.spring.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {
    @Override
    public Myview process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        return new Myview("/WEB-INF/views/new-form.jsp");
    }
}
