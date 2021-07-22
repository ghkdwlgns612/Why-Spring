package why.spring.web.frontcontroller.v2.controller;

import why.spring.domain.member.Member;
import why.spring.domain.member.MemberRepository;
import why.spring.web.frontcontroller.Myview;
import why.spring.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public Myview process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username,age);
        memberRepository.save(member);

        request.setAttribute("member",member);
        return new Myview("/WEB-INF/views/save-result.jsp");
    }
}
