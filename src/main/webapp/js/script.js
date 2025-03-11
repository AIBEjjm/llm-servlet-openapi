/* script.js */

document.addEventListener('DOMContentLoaded', function() {
    console.log("[DEBUG] script.js: DOMContentLoaded 이벤트 발생. 스크립트 실행 시작.");

    // 카드 flip 효과 추가
    const cards = document.querySelectorAll('.card-inner');
    cards.forEach(function(card) {
        card.addEventListener('click', function() {
            card.classList.toggle('flipped');
        });
    });

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

    // 로딩바 업데이트: 카드 컨테이너 내 모든 이미지 로드 감지
    const images = document.querySelectorAll('#card-container img');
    const totalImages = images.length;
    let loadedImages = 0;

    function updateProgress() {
        loadedImages++;
        const progressPercent = Math.round((loadedImages / totalImages) * 100);
        document.getElementById('progress-bar').style.width = progressPercent + '%';
        document.getElementById('progress-percent').textContent = progressPercent + '%';
        if (loadedImages === totalImages) {
            // 모든 이미지 로드 완료 시 로딩 오버레이 숨기고 메인 콘텐츠 표시
            document.getElementById('loading-overlay').style.display = 'none';
            document.getElementById('main-content').style.display = 'block';
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

/**
 * 선택한 페이지에 해당하는 카드만 표시하는 함수
 * @param {number|string} pageNumber - 보여줄 페이지 번호
 */
function showPage(pageNumber) {
    const cards = document.querySelectorAll('.card');
    cards.forEach(function(card) {
        if (card.getAttribute('data-page') === pageNumber.toString()) {
            card.style.display = 'block';
        } else {
            card.style.display = 'none';
        }
    });
}
