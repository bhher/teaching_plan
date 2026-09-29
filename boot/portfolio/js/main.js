/* Nav scroll + reveal on scroll */

const nav = document.querySelector(".site-nav");

function updateNav() {
  if (!nav) return;
  nav.classList.toggle("is-scrolled", window.scrollY > 40);
}

updateNav();
window.addEventListener("scroll", updateNav, { passive: true });

const reveals = document.querySelectorAll(".reveal");

if ("IntersectionObserver" in window) {
  const io = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          entry.target.classList.add("is-visible");
          io.unobserve(entry.target);
        }
      });
    },
    { threshold: 0.15, rootMargin: "0px 0px -40px 0px" }
  );

  reveals.forEach((el) => io.observe(el));
} else {
  reveals.forEach((el) => el.classList.add("is-visible"));
}

/* Contact form: demo only */
const form = document.querySelector(".contact-form");
if (form) {
  form.addEventListener("submit", (e) => {
    e.preventDefault();
    alert("메시지가 전송된 것처럼 보이는 데모입니다.");
    form.reset();
  });
}
