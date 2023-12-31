    var currentUrl = window.location.pathname;

    // 현재 URL에 따라 스타일을 설정하는 함수
    function setActiveStyle(linkId, targetUrl) {
    var link = document.getElementById(linkId);

    if (currentUrl === targetUrl) {
    link.classList.add("active");
} else {
    link.classList.remove("active");
}
}

    // 각 링크에 대해 setActiveStyle 함수 호출
    setActiveStyle("recordAll", "/record/hitter/all");
    setActiveStyle("recordPitcher", "/record/pitcher/all");
    setActiveStyle("otherLink", "/");  // 다른 링크의 경우 예시

