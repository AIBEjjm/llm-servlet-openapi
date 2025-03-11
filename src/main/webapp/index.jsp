<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.example.llmservletopenapi.service.SuperHeroService" %>
<%@ page import="org.example.llmservletopenapi.model.SuperHero" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>SuperHero Card Game</title>
    <!-- CSS 파일 로드: webapp/css/style.css -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<!-- 페이지 상단 제목 -->
<h1>슈퍼히어로 카드 갤러리</h1>

<!-- SuperHero 데이터를 API를 통해 불러와 리스트에 저장 -->
<%
    // SuperHeroService 인스턴스 생성
    SuperHeroService heroService = new SuperHeroService();
    // 1부터 100번까지의 슈퍼히어로 데이터를 저장할 리스트
    List<SuperHero> heroList = new ArrayList<>();
    for (int i = 1; i <= 100; i++) {
        try {
            SuperHero hero = heroService.getHeroById(String.valueOf(i));
            if(hero != null) {
                heroList.add(hero);
            }
        } catch(Exception e) {
            // 오류 발생 시 무시 (실제 디버깅 시 로그 확인)
        }
    }
%>

<!-- 카드들을 담을 컨테이너 -->
<div id="card-container">
    <%-- 리스트에 담긴 슈퍼히어로 데이터를 순회하여 카드 생성 --%>
    <%
        for (int i = 0; i < heroList.size(); i++) {
            SuperHero hero = heroList.get(i);
            // i는 0부터 시작하므로, 카드가 속한 페이지는 (i/20)+1
            int cardPage = (i / 20) + 1;
    %>
    <div class="card" data-page="<%= cardPage %>">
        <div class="card-inner">
            <!-- 카드 앞면 -->
            <div class="card-front">
                <!-- 이미지 영역: 실제 API의 image.url 사용 -->
                <div class="image-container">
                    <img src="<%= hero.getImage().getUrl() %>" alt="슈퍼히어로 이미지">
                    <!-- 이미지 영역 내 우측 하단에 능력치 오버레이 (powerstats) -->
                    <div class="hero-stats">
                        Intelligence: <%= hero.getPowerStats().intelligence %><br>
                        Strength: <%= hero.getPowerStats().strength %><br>
                        Speed: <%= hero.getPowerStats().speed %><br>
                        Durability: <%= hero.getPowerStats().durability %><br>
                        Power: <%= hero.getPowerStats().power %><br>
                        Combat: <%= hero.getPowerStats().combat %>
                    </div>
                </div>
                <!-- 이미지 아래에 캐릭터 이름 표시 -->
                <div class="hero-name">
                    <%= hero.getName() %>
                </div>
            </div>
            <!-- 카드 뒷면 -->
            <div class="card-back">
                <div class="hero-details">
                    <h3>Biography</h3>
                    <p>
                        Full Name: <%= hero.getBiography().getFullName() %><br>
                        First Appearance: <%= hero.getBiography().getFirstAppearance() %><br>
                        Publisher: <%= hero.getBiography().getPublisher() %>
                    </p>
                    <h3>Appearance</h3>
                    <p>
                        Gender: <%= hero.getAppearance().gender %><br>
                        Race: <%= hero.getAppearance().race %><br>
                        Height: <%= hero.getAppearance().height[0] %><br>
                        Weight: <%= hero.getAppearance().weight[0] %>
                    </p>
                    <h3>Work</h3>
                    <p>
                        Occupation: <%= hero.getWork().occupation %><br>
                        Base: <%= hero.getWork().base %>
                    </p>
                    <h3>Connections</h3>
                    <p>
                        Group Affiliation: <%= hero.getConnections().groupAffiliation %><br>
                        Relatives: <%= hero.getConnections().relatives %>
                    </p>
                </div>
            </div>
        </div>
    </div>
    <% } %>
</div>

<!-- 페이지 네비게이션 영역 -->
<div id="pagination">
    <button class="page-btn" data-page="1">1</button>
    <button class="page-btn" data-page="2">2</button>
    <button class="page-btn" data-page="3">3</button>
    <button class="page-btn" data-page="4">4</button>
    <button class="page-btn" data-page="5">5</button>
</div>

<!-- JavaScript 파일 로드: webapp/js/script.js -->
<script src="js/script.js"></script>
</body>
</html>
