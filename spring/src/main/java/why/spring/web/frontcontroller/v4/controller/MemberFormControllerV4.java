package why.spring.web.frontcontroller.v4.controller;

import why.spring.web.frontcontroller.ModelView;
import why.spring.web.frontcontroller.v3.ControllerV3;
import why.spring.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberFormControllerV4 implements ControllerV4 {
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}