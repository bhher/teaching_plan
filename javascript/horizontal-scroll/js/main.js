document.querySelectorAll(".item").forEach(function (el) {
  el.addEventListener("click", function () {
    document.querySelectorAll(".item").forEach(function (x) {
      x.classList.remove("on");
    });
    el.classList.add("on");
    el.scrollIntoView({ behavior: "smooth", inline: "center", block: "nearest" });
  });
});
