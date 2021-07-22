package why.spring.web.frontcontroller.v3.controller;

import why.spring.web.frontcontroller.ModelView;
import why.spring.web.frontcontroller.v3.ControllerV3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paraMap) {
        return new ModelView("new-form");
    }
}
