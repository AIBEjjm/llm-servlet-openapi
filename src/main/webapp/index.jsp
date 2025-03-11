<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.example.llmservletopenapi.model.SuperHero" %>
<%@ page import="org.example.llmservletopenapi.service.SuperHeroService" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>슈퍼히어로 카드 갤러리</title>
    <!-- CSS 파일 로드: webapp/css/style.css -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<!-- 로딩 오버레이: 페이지 중앙에 메시지, 로딩바, 퍼센트 표시 -->
<div id="loading-overlay">
    <div id="loading-message">카드들을 가져오는 중입니다..</div>
    <div id="progress-bar-container">
        <div id="progress-bar"></div>
    </div>
    <div id="progress-percent">0%</div>
</div>

<!-- 메인 콘텐츠: 서버 사이드에서 데이터를 불러와 렌더링 -->
<div id="main-content" style="display: none;">
    <h1>슈퍼히어로 카드 갤러리</h1>
    <%
        // SuperHeroService를 사용하여 1부터 100번의 데이터를 불러옵니다.
        SuperHeroService heroService = new SuperHeroService();
        List<SuperHero> heroList = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            try {
                SuperHero hero = heroService.getHeroById(String.valueOf(i));
                if (hero != null) {
                    heroList.add(hero);
                }
            } catch (Exception e) {
                System.err.println("[ERROR] ID " + i + " 데이터를 불러오는 중 오류 발생");
            }
        }
    %>
    <div id="card-container">
        <%
            for (int i = 0; i < heroList.size(); i++) {
                SuperHero hero = heroList.get(i);
                int cardPage = (i / 20) + 1;
                // hero의 이미지 URL이 있으면 사용, 없으면 기본 이미지 (images/heroImg.png) 사용
                String imageUrl = (hero.getImage() != null && hero.getImage().getUrl() != null
                        && !hero.getImage().getUrl().isEmpty())
                        ? hero.getImage().getUrl()
                        : "images/heroImg.png";
        %>
        <div class="card" data-page="<%= cardPage %>">
            <div class="card-inner">
                <!-- 카드 앞면 -->
                <div class="card-front">
                    <div class="image-container">
                        <img src="<%= imageUrl %>" alt="슈퍼히어로 이미지">
                        <div class="hero-stats">
                            Intelligence: <%= hero.getPowerStats().intelligence %><br>
                            Strength: <%= hero.getPowerStats().strength %><br>
                            Speed: <%= hero.getPowerStats().speed %><br>
                            Durability: <%= hero.getPowerStats().durability %><br>
                            Power: <%= hero.getPowerStats().power %><br>
                            Combat: <%= hero.getPowerStats().combat %>
                        </div>
                    </div>
                    <div class="hero-name"><%= hero.getName() %></div>
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
                            Height: <%= hero.getAppearance().height != null ? hero.getAppearance().height[0] : "N/A" %><br>
                            Weight: <%= hero.getAppearance().weight != null ? hero.getAppearance().weight[0] : "N/A" %>
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
    <div id="pagination">
        <button class="page-btn" data-page="1">1</button>
        <button class="page-btn" data-page="2">2</button>
        <button class="page-btn" data-page="3">3</button>
        <button class="page-btn" data-page="4">4</button>
        <button class="page-btn" data-page="5">5</button>
    </div>
</div>
<!-- JavaScript 파일 로드: webapp/js/script.js -->
<script src="js/script.js"></script>
</body>
</html>
