package why.spring.web.frontcontroller.v2.controller;

import why.spring.domain.member.Member;
import why.spring.domain.member.MemberRepository;
import why.spring.web.frontcontroller.Myview;
import why.spring.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository =  MemberRepository.getInstance();
    @Override
    public Myview process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Member> members = memberRepository.findAll();
        request.setAttribute("members",members);
        return new Myview("/WEB-INF/views/members.jsp");
    }
}
