package org.example.llmservletopenapi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.llmservletopenapi.model.SuperHero;
import org.example.llmservletopenapi.service.SuperHeroService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * HeroController 서블릿
 * - 클라이언트가 "/heroes" URL로 요청하면 SuperHeroService를 통해 1~100번의 슈퍼히어로 데이터를
 *   한 번만 불러와 request 속성("heroList")에 저장한 후 index.jsp로 포워딩합니다.
 */
@WebServlet(name = "HeroController", urlPatterns = {"/heroes"})
public class HeroController extends HttpServlet {
    private final SuperHeroService heroService = new SuperHeroService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("[DEBUG] HeroController: /heroes 요청 처리 시작");
        List<SuperHero> heroList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            try {
                SuperHero hero = heroService.getHeroById(String.valueOf(i));
                if (hero != null) {
                    heroList.add(hero);
                }
            } catch (Exception e) {
                System.err.println("[ERROR] HeroController: ID " + i + " 데이터 불러오기 오류");
            }
        }
        req.setAttribute("heroList", heroList);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
        System.out.println("[DEBUG] HeroController: /heroes 요청 처리 완료");
    }
}
