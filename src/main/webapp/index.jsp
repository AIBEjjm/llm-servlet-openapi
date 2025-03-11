<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>슈퍼히어로 카드 갤러리</title>
    <!-- CSS 파일 로드: webapp/css/style.css -->
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<!-- 페이지 상단 제목 -->
<h1>슈퍼히어로 카드 갤러리</h1>

<!-- 카드들을 담을 컨테이너 -->
<div id="card-container">
    <%-- JSP 스크립트릿을 이용하여 1부터 100까지의 카드 생성
         각 카드에는 data-page 속성을 부여하여 페이지네이션을 구현합니다.
         한 페이지에는 20개의 카드(가로 5장 x 세로 4줄)가 표시됩니다. --%>
    <%
        for (int i = 1; i <= 100; i++) {
            // 'page'는 JSP 내장 변수와 충돌하므로, 다른 변수명(cardPage) 사용
            int cardPage = ((i - 1) / 20) + 1;
    %>
    <div class="card" data-page="<%= cardPage %>">
        <div class="card-inner">
            <!-- 카드 앞면 -->
            <div class="card-front">
                <!-- 히어로 이미지 (예시 이미지) -->
                <img src="https://via.placeholder.com/150" alt="슈퍼히어로 이미지">
                <!-- 이미지 아래 밑줄과 함께 히어로 이름 표시 -->
                <div class="hero-name">
                    히어로 이름 <%= i %>
                </div>
                <!-- 이미지 우측 하단에 능력치를 줄바꿈하여 표시 -->
                <div class="hero-stats">
                    힘: 90<br>
                    속도: 80<br>
                    전투: 85
                </div>
            </div>
            <!-- 카드 뒷면 -->
            <div class="card-back">
                <div class="hero-details">
                    <h3>전기</h3>
                    <p>예시 전기 정보</p>
                    <h3>외형</h3>
                    <p>예시 외형 정보</p>
                    <h3>직업</h3>
                    <p>예시 직업 정보</p>
                    <h3>연결</h3>
                    <p>예시 연결 정보</p>
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
