/* script.js */

/**
 * DOMContentLoaded 이벤트 후 실행되는 스크립트
 * - 모든 카드에 클릭 이벤트를 추가하여 flip 효과를 구현합니다.
 * - 페이지 네비게이션 버튼 클릭 시, 해당 페이지의 카드만 표시하도록 합니다.
 * - 초기 페이지를 1페이지로 설정합니다.
 */
document.addEventListener('DOMContentLoaded', function() {
    console.log("[DEBUG] script.js: DOMContentLoaded 이벤트 발생. 스크립트 실행 시작.");

    // 모든 카드에 클릭 이벤트 추가 (flip 효과)
    const cards = document.querySelectorAll('.card-inner');
    cards.forEach(function(card) {
        card.addEventListener('click', function() {
            card.classList.toggle('flipped');
        });
    });

    // 페이지 네비게이션 버튼 이벤트 처리
    const pageButtons = document.querySelectorAll('.page-btn');
    pageButtons.forEach(function(btn) {
        btn.addEventListener('click', function() {
            const selectedPage = btn.getAttribute('data-page');
            console.log("[DEBUG] script.js: 페이지 " + selectedPage + " 선택됨.");
            showPage(selectedPage);
        });
    });

    // 초기 페이지 설정 (1페이지 보여주기)
    showPage(1);

    console.log("[DEBUG] script.js: 스크립트 실행 완료.");
});

/**
 * 선택한 페이지에 해당하는 카드만 보이도록 처리하는 함수
 * @param {number|string} pageNumber - 보여줄 페이지 번호
 */
function showPage(pageNumber) {
    const cards = document.querySelectorAll('.card');
    cards.forEach(function(card) {
        // 카드의 data-page 속성 값과 비교하여, 일치하면 표시, 아니면 숨김 처리
        if (card.getAttribute('data-page') === pageNumber.toString()) {
            card.style.display = 'block';
        } else {
            card.style.display = 'none';
        }
    });
}
