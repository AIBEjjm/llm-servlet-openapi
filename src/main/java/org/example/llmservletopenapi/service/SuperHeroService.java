package org.example.llmservletopenapi.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.llmservletopenapi.model.SuperHero;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * SuperHeroService 클래스
 * SuperHeroAPI에 HTTP 요청을 보내 캐릭터 정보를 받아오는 역할을 합니다.
 * 디버깅 로그를 통해 각 메서드의 실행 여부를 확인할 수 있습니다.
 */
public class SuperHeroService {
    private final String heroKey;
    private final HttpClient httpClient;
    final ObjectMapper objectMapper;

    // 생성자: .env 파일에서 HERO_KEY를 로드하고, HttpClient와 ObjectMapper를 초기화합니다.
    public SuperHeroService() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        this.heroKey = dotenv.get("HERO_KEY");

        if (this.heroKey == null || this.heroKey.isEmpty()) {
            System.err.println("[ERROR] SuperHeroService: HERO_KEY가 누락되었거나 로드되지 않았습니다.");
            throw new RuntimeException("🚨 ERROR: HERO_KEY is missing or not loaded!");
        } else {
            System.out.println("[DEBUG] SuperHeroService: HERO_KEY가 정상적으로 로드되었습니다.");
        }

        this.httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        this.objectMapper = new ObjectMapper();

        System.out.println("[DEBUG] SuperHeroService가 정상적으로 생성되었습니다.");
    }

    /**
     * 주어진 id의 슈퍼히어로 정보를 SuperHeroAPI를 통해 가져옵니다.
     * @param id 슈퍼히어로의 id (예: "70")
     * @return SuperHero 객체 (JSON 데이터를 변환한 결과)
     * @throws Exception API 호출 시 발생할 수 있는 예외
     */
    public SuperHero getHeroById(String id) throws Exception {
        String url = "https://superheroapi.com/api/" + heroKey + "/" + id;
        System.out.println("[DEBUG] SuperHeroService.getHeroById: API 요청 URL = " + url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.body().trim().startsWith("<")) {
            System.err.println("[ERROR] SuperHeroService.getHeroById: API 응답이 HTML 형식입니다. URL과 API Key를 확인하세요.");
            System.err.println("[ERROR] 응답 내용: " + response.body());
            return null;
        }

        SuperHero hero = objectMapper.readValue(response.body(), SuperHero.class);
        System.out.println("[DEBUG] SuperHeroService.getHeroById: ID " + id + "의 데이터를 성공적으로 파싱하였습니다.");
        return hero;
    }

    public static void main(String[] args) {
        System.out.println("[DEBUG] SuperHeroService의 main 메서드가 실행되었습니다.");
        SuperHeroService service = new SuperHeroService();
        int totalCharacters = 5;

        for (int i = 1; i <= totalCharacters; i++) {
            try {
                SuperHero hero = service.getHeroById(String.valueOf(i));
                if (hero != null) {
                    String prettyJson = service.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(hero);
                    System.out.println("[DEBUG] SuperHero Data for ID " + i + " 출력 성공:");
                    System.out.println(prettyJson);
                } else {
                    System.err.println("[ERROR] SuperHeroService.main: ID " + i + "의 데이터를 받아오지 못했습니다.");
                }
            } catch (Exception e) {
                System.err.println("[ERROR] SuperHeroService.main: ID " + i + " 데이터 요청 중 오류 발생!");
                e.printStackTrace();
            }
        }
        System.out.println("[DEBUG] SuperHeroService의 main 메서드가 정상적으로 종료되었습니다.");
    }
}
