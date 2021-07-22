package why.spring.web.frontcontroller.v5.adapter;

import why.spring.web.frontcontroller.ModelView;
import why.spring.web.frontcontroller.v3.ControllerV3;
import why.spring.web.frontcontroller.v4.ControllerV4;
import why.spring.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean support(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
        ControllerV4 controller = (ControllerV4) handler;
        Map<String, String> paraMap = createParaMap(request);
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paraMap, model);
        ModelView mv = new ModelView(viewName);
        mv.setModel(model);
        return mv;
    }

    private Map<String, String> createParaMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paraName -> paramMap.put(paraName, request.getParameter(paraName)));
        return paramMap;
    }
}
