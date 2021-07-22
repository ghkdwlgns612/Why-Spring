package why.spring.web.frontcontroller.v3;

import why.spring.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface ControllerV3 {
    ModelView process(Map<String, String> paraMap);
}