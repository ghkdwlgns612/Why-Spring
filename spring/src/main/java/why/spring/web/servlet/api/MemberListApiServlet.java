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
import java.util.List;

@WebServlet(name = "memberListApiServlet", urlPatterns = "/servlet/api/members")
public class MemberListApiServlet extends HttpServlet {
    private MemberRepository memberRepository = MemberRepository.getInstance();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        String s = objectMapper.writeValueAsString(members);
        response.getWriter().write(s);
    }
}
