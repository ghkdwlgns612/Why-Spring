package why.spring.web.frontcontroller.v5;

import why.spring.web.frontcontroller.ModelView;
import why.spring.web.frontcontroller.Myview;
import why.spring.web.frontcontroller.v3.controller.MemberFormControllerV3;
import why.spring.web.frontcontroller.v3.controller.MemberListControllerV3;
import why.spring.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import why.spring.web.frontcontroller.v4.controller.MemberFormControllerV4;
import why.spring.web.frontcontroller.v4.controller.MemberListControllerV4;
import why.spring.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import why.spring.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import why.spring.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontContorllerServletV5",urlPatterns = "/front-controller/v5/*")
public class FrontContorllerServletV5 extends HttpServlet {

    private Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        initHandlerMappingMap();
        initHandlerAdapters();
        Object handler = handlerMappingMap.get(requestURI);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyHandlerAdapter handlerAdapter = getHandlerAdapter(handler);
        ModelView mv = handlerAdapter.handle(request, response, handler);

        Myview myview = viewResolver(mv.getViewName());
        myview.render(mv.getModel(),request,response);

    }

    public MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.support(handler))
                return adapter;
        } throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다. handler=" + handler);
    }

    private Myview viewResolver(String viewName) {
        return new Myview("/WEB-INF/views/" + viewName + ".jsp");
    }
}