package why.spring.web.servlet.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import why.spring.domain.member.Member;
import why.spring.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "memberSaveApiServlet", urlPatterns = "/servlet/api/members/save")
public class MemberSaveApiServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        Member member = new Member(username,age);
        memberRepository.save(member);
        String s = objectMapper.writeValueAsString(member);
        response.getWriter().write(s);
    }
}
