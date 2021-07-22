package why.spring.web.frontcontroller.v3.controller;

import why.spring.domain.member.Member;
import why.spring.domain.member.MemberRepository;
import why.spring.web.frontcontroller.ModelView;
import why.spring.web.frontcontroller.Myview;
import why.spring.web.frontcontroller.v2.ControllerV2;
import why.spring.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {
    private MemberRepository memberRepository =  MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paraMap) {
        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("members");
        mv.getModel().put("members",members);
        return mv;
    }
}
