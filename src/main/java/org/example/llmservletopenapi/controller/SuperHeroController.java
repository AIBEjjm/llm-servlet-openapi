package org.example.llmservletopenapi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.llmservletopenapi.model.SuperHero;
import org.example.llmservletopenapi.service.SuperHeroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * SuperHeroController 클래스
 * 클라이언트로부터 "/superhero" URL로 요청이 들어오면 SuperHeroService를 호출하여
 * 해당 슈퍼히어로의 정보를 JSON 형식으로 응답합니다.
 * 디버깅 로그를 통해 각 처리 단계에서의 상태를 콘솔에 출력합니다.
 */
@WebServlet(name = "SuperHeroServlet", value = "/superhero")
public class SuperHeroController extends HttpServlet {
    private final SuperHeroService superHeroService = new SuperHeroService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[DEBUG] SuperHeroController.doGet() 메서드 실행 시작.");

        // 요청 파라미터에서 'id' 값을 추출
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            // id 파라미터가 없는 경우 400 Bad Request 응답
            System.err.println("[ERROR] SuperHeroController: 요청 파라미터 'id'가 누락되었습니다.");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("{\"error\": \"Missing hero ID\"}");
            return;
        }

        try {
            // SuperHeroService를 사용하여 해당 ID의 캐릭터 정보 조회
            SuperHero hero = superHeroService.getHeroById(id);
            if (hero == null) {
                // 데이터가 없는 경우 404 Not Found 응답
                System.err.println("[ERROR] SuperHeroController: ID " + id + "의 슈퍼히어로 데이터를 찾을 수 없습니다.");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().println("{\"error\": \"Hero not found\"}");
                return;
            }

            // 정상 응답: JSON 형태로 데이터를 클라이언트에 반환
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            String jsonResponse = objectMapper.writeValueAsString(hero);
            out.println(jsonResponse);
            System.out.println("[DEBUG] SuperHeroController: ID " + id + "의 데이터를 성공적으로 응답하였습니다.");
        } catch (Exception e) {
            // 내부 서버 오류 발생 시 500 Internal Server Error 응답
            System.err.println("[ERROR] SuperHeroController: 내부 서버 오류 발생!");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().println("{\"error\": \"Internal server error\"}");
            e.printStackTrace();
        }

        System.out.println("[DEBUG] SuperHeroController.doGet() 메서드 실행 종료.");
    }
}
