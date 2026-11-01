const nav = document.querySelector(".site-nav");

function updateNav() {
  if (!nav) return;
  nav.classList.toggle("is-scrolled", window.scrollY > 40);
}

updateNav();
window.addEventListener("scroll", updateNav, { passive: true });

/* 모바일: 메뉴 클릭 시 collapse 닫기 */
document.querySelectorAll("#navMenu .nav-link").forEach((link) => {
  link.addEventListener("click", () => {
    const menu = document.getElementById("navMenu");
    if (menu && menu.classList.contains("show")) {
      const collapse = bootstrap.Collapse.getInstance(menu)
        || new bootstrap.Collapse(menu, { toggle: false });
      collapse.hide();
    }
  });
});

const form = document.querySelector(".contact-form");
if (form) {
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    alert("메시지가 전송된 것처럼 보이는 데모입니다.");
    form.reset();
  });
}
