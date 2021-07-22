package why.spring.web.frontcontroller.v3;

import why.spring.web.frontcontroller.ModelView;
import why.spring.web.frontcontroller.Myview;
import why.spring.web.frontcontroller.v2.ControllerV2;
import why.spring.web.frontcontroller.v2.controller.MemberFormControllerV2;
import why.spring.web.frontcontroller.v2.controller.MemberListControllerV2;
import why.spring.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import why.spring.web.frontcontroller.v3.controller.MemberFormControllerV3;
import why.spring.web.frontcontroller.v3.controller.MemberListControllerV3;
import why.spring.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(uri);
        if (controller == null)
        {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paraMap = createMap(request);
        ModelView mv = controller.process(paraMap);

        String viewName = mv.getViewName();
        Myview myview = viewResolver(viewName);
        myview.render(mv.getModel(),request,response);
    }

    private Myview viewResolver(String viewName) {
        return new Myview("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String,String> createMap(HttpServletRequest request) {
        Map<String, String> paraMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paraName -> paraMap.put(paraName, request.getParameter(paraName)));
        return paraMap;
    }
}
