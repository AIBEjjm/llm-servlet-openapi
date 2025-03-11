package org.example.llmservletopenapi.service;

import io.github.cdimascio.dotenv.Dotenv;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SuperHeroService {
    private final String heroKey;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public SuperHeroService() {
        // .env 파일에서 HERO_KEY 값 로드
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        this.heroKey = dotenv.get("HERO_KEY");

        // API Key가 정상적으로 로드되었는지 확인
        if (this.heroKey == null || this.heroKey.isEmpty()) {
            throw new RuntimeException("🚨 ERROR: HERO_KEY is missing or not loaded!");
        }

        this.httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // 302 Redirect 자동 처리
                .build();

        this.objectMapper = new ObjectMapper();
    }

    /**
     * 주어진 id의 슈퍼히어로 정보를 SuperHeroAPI를 통해 가져온다.
     * @param id 슈퍼히어로의 id (예: "70")
     * @return API 응답 JSON 문자열
     * @throws Exception API 호출 시 발생할 수 있는 예외
     */
    public String getHeroById(String id) throws Exception {
        String url = "https://superheroapi.com/api/" + heroKey + "/" + id;

        // URL이 올바르게 설정되었는지 확인
        System.out.println("🔗 API Request URL: " + url);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36") // 브라우저처럼 요청
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // HTML 응답 감지 (API Key 오류, 차단, HTML 에러 페이지)
        if (response.body().trim().startsWith("<")) {
            System.err.println("❌ API 응답이 HTML 형식입니다. URL과 API Key를 확인하세요.");
            System.err.println(response.body()); // 실제 HTML 내용 출력
            return null;
        }

        return response.body();
    }

    public static void main(String[] args) {
        SuperHeroService service = new SuperHeroService();
        try {
            // ID 1번 데이터 테스트
            String heroData = service.getHeroById("1");
            if (heroData != null) {
                // JSON 파싱 및 예쁘게 출력
                JsonNode jsonNode = service.objectMapper.readTree(heroData);
                String prettyJson = service.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
                System.out.println("✅ SuperHero Data for ID 1:");
                System.out.println(prettyJson);
            }
        } catch (Exception e) {
            System.err.println("API Call Failed!");
            e.printStackTrace();
        }
    }
}
