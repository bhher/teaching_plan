$(function () {
  var MOBILE_BP = 850;

  /* ---------- PC GNB: hover 시 높이 확장 (지센 .over) ---------- */
  $(".nav > ul").hover(
    function () {
      $(this).addClass("over");
    },
    function () {
      $(this).removeClass("over");
    }
  );

  /* ---------- 모바일 메뉴: 햄버거 열기 / 패널 상단 X 닫기 ---------- */
  function openMobileNav() {
    $(".mobile_nav").addClass("active");
    $(".transparency").addClass("active");
    $(".mobile_tab").attr("aria-expanded", "true");
    $("body").css("overflow", "hidden");
  }

  function closeMobileNav() {
    $(".mobile_nav").removeClass("active");
    $(".transparency").removeClass("active");
    $(".mobile_tab").attr("aria-expanded", "false");
    $(".mobile_nav .sub").slideUp(0);
    $("body").css("overflow", "");
  }

  $(".mobile_tab").on("click", function () {
    openMobileNav();
  });

  $(".mobile_close").on("click", function () {
    closeMobileNav();
  });

  $(".transparency").on("click", function () {
    closeMobileNav();
  });

  /* ---------- 모바일 아코디언 서브 ---------- */
  $(".mobile_nav > ul > li > a").on("click", function (e) {
    var $sub = $(this).next(".sub");
    if ($sub.length === 0) return;

    e.preventDefault();
    if ($sub.css("display") === "none") {
      $(".mobile_nav .sub").slideUp(300);
      $sub.slideDown(300);
    } else {
      $sub.slideUp(300);
    }
  });

  /* ---------- 리사이즈: PC로 돌아가면 모바일 메뉴 초기화 ---------- */
  $(window).on("resize", function () {
    if ($(window).width() > MOBILE_BP) {
      closeMobileNav();
    }
  });

  /* ---------- Slick Slider ---------- */
  var $slider = $(".main-slider");

  $slider.on("init", function (event, slick) {
    $(".slider-control .total").text(slick.slideCount);
    $(".slider-control .current").text(slick.currentSlide + 1);
  });

  $slider.slick({
    infinite: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3500,
    speed: 600,
    fade: true,
    cssEase: "linear",
    arrows: false,
    dots: true,
    pauseOnHover: true,
    responsive: [
      {
        breakpoint: 768,
        settings: {
          fade: false, /* 모바일에서는 슬라이드 전환 */
          speed: 400
        }
      }
    ]
  });

  $slider.on("afterChange", function (event, slick, currentSlide) {
    $(".slider-control .current").text(currentSlide + 1);
  });

  $(".slick-prev-btn").on("click", function () {
    $slider.slick("slickPrev");
  });

  $(".slick-next-btn").on("click", function () {
    $slider.slick("slickNext");
  });

  var playing = true;
  $(".slick-pause-btn").on("click", function () {
    if (playing) {
      $slider.slick("slickPause");
      $(this).text("▶").attr("aria-label", "재생");
      playing = false;
    } else {
      $slider.slick("slickPlay");
      $(this).text("❚❚").attr("aria-label", "일시정지");
      playing = true;
    }
  });
});
