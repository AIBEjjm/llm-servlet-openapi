/* script.js */

document.addEventListener('DOMContentLoaded', function() {
    console.log("[DEBUG] script.js: DOMContentLoaded 이벤트 발생. 스크립트 실행 시작.");

    // 페이지 네비게이션 이벤트 처리
    const pageButtons = document.querySelectorAll('.page-btn');
    pageButtons.forEach(function(btn) {
        btn.addEventListener('click', function() {
            const selectedPage = btn.getAttribute('data-page');
            console.log("[DEBUG] script.js: 페이지 " + selectedPage + " 선택됨.");
            showPage(selectedPage);
        });
    });

    // 초기 페이지 설정 (1페이지)
    showPage(1);

    // 카드 flip 효과: 각 카드의 내부 요소(.card-inner)에 클릭 이벤트 추가
    const cardInners = document.querySelectorAll('.card-inner');
    cardInners.forEach(cardInner => {
        cardInner.addEventListener('click', () => {
            cardInner.classList.toggle('flipped');
        });
    });

    // 로딩바 업데이트: 모든 이미지의 로드 이벤트를 감지하여 진행률 업데이트
    const images = document.querySelectorAll('#card-container img');
    const totalImages = images.length;
    let loadedImages = 0;

    function updateProgress() {
        loadedImages++;
        const progressPercent = Math.round((loadedImages / totalImages) * 100);
        document.getElementById('progress-bar').style.width = progressPercent + "%";
        document.getElementById('progress-percent').textContent = progressPercent + "%";
        if (loadedImages === totalImages) {
            // 모든 이미지 로드 완료 시 로딩 오버레이 숨기고 메인 콘텐츠 표시
            document.getElementById('loading-overlay').style.display = "none";
            document.getElementById('main-content').style.display = "block";
        }
    }

    images.forEach(function(img) {
        if (img.complete) {
            updateProgress();
        } else {
            img.addEventListener('load', updateProgress);
            img.addEventListener('error', updateProgress);
        }
    });

    console.log("[DEBUG] script.js: 스크립트 실행 완료.");
});

function showPage(pageNumber) {
    const cards = document.querySelectorAll('.card');
    cards.forEach(function(card) {
        card.style.display = (card.getAttribute('data-page') === pageNumber.toString()) ? "block" : "none";
    });
}
