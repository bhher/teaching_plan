# config 패키지 정리 (`com.shop.config`)

이 폴더는 애플리케이션의 **공통 설정(Configuration)** 을 모아둔 패키지입니다.

## 파일별 역할

### `SecurityConfig.java`

- **역할**: Spring Security의 핵심 보안 설정을 구성합니다. (`SecurityFilterChain`)
- **주요 내용**
  - **CSRF 비활성화**
    - `csrf(csrf -> csrf.disable())`
    - 학습/로컬 환경에서 폼 동작 문제를 피하기 위한 설정으로 보입니다. (운영 환경에서는 주의)
  - **폼 로그인**
    - 로그인 페이지: `/members/login`
    - 로그인 성공 시 이동: `/`
    - username 파라미터를 `email`로 사용: `usernameParameter("email")`
    - 로그인 실패 시 이동: `/members/login/error`
  - **로그아웃**
    - 로그아웃 요청 URL: `/members/logout` (`AntPathRequestMatcher` 사용)
    - 로그아웃 성공 시 이동: `/`
  - **URL 권한(인가) 규칙**
    - 정적 리소스(`/css/**`, `/js/**`, `/img/**`)는 모두 허용
    - `/`, `/members/**`, `/item/**`, `/images/**`는 모두 허용
    - `/admin/**`는 `ADMIN` 권한 필요
    - 그 외는 인증 필요
  - **인증 실패 처리**
    - `CustomAuthenticationEntryPoint`를 entry point로 지정

### `CustomAuthenticationEntryPoint.java`

- **역할**: 인증이 필요한 요청인데 인증이 되지 않은 경우(로그인 안 됨 등) 응답 방식을 정의합니다.
- **동작**
  - `commence(...)`에서 `401 Unauthorized`로 응답을 보냅니다.
  - 웹 페이지 리다이렉트가 아니라 **HTTP 에러 응답**을 내려주는 형태입니다.

### `WebMvcConfig.java`

- **역할**: 정적 리소스(파일) 핸들링 규칙을 추가합니다. (`WebMvcConfigurer`)
- **동작**
  - URL `/images/**` 요청을 `uploadPath`(프로퍼티 값) 경로의 리소스로 매핑합니다.
  - 예: 업로드된 이미지 파일을 브라우저에서 `/images/...`로 접근 가능하게 합니다.

### `AuditConfig.java`

- **역할**: JPA Auditing 기능을 활성화하고, auditor 제공자를 빈으로 등록합니다.
- **핵심**
  - `@EnableJpaAuditing`: JPA Auditing 활성화
  - `AuditorAware<String>` 빈 등록: `AuditorAwareImpl` 사용

### `AuditorAwareImpl.java`

- **역할**: JPA Auditing이 사용할 “현재 사용자(작성자/수정자)” 값을 제공합니다.
- **동작**
  - `SecurityContextHolder`에서 현재 `Authentication`을 가져오고,
  - 존재하면 `authentication.getName()` 값을 반환합니다. (보통 username/email)
  - 인증이 없으면 빈 문자열 `""`을 반환합니다.

## Spring Security에 “보통 필요한 파일/구성요소” (이 프로젝트 기준)

Spring Security를 적용하려면 아래 구성요소들이 자주 등장합니다. 이 프로젝트에서는 일부가 이미 구현되어 있습니다.

### 1) 보안 설정 클래스 (필수급)

- **필요**: `SecurityFilterChain` 빈을 제공하는 설정
- **현재 위치**: `SecurityConfig.java`
- **담당**: 로그인/로그아웃, URL 권한 규칙, CSRF, 예외처리 등

### 2) 사용자 조회 서비스 (필수급)

- **필요**: 로그인 시 “사용자 정보를 DB에서 가져오는 로직”
- **현재 프로젝트**: `MemberService`가 `UserDetailsService`의 `loadUserByUsername(email)`을 구현해서 담당
- **담당**: email로 회원 조회 → 비밀번호/권한을 Spring Security `UserDetails`로 변환

### 3) 비밀번호 인코더 (필수급)

- **필요**: 비밀번호 해시(암호화) 및 로그인 비교
- **현재 위치**: `SecurityConfig.java`의 `PasswordEncoder` 빈 (`BCryptPasswordEncoder`)

### 4) 로그인 화면/폼 (웹 앱에서 필요)

- **필요**: `loginPage("/members/login")`에 해당하는 컨트롤러/템플릿
- **현재 프로젝트**: `/members/login` 경로를 처리하는 화면이 존재해야 정상 동작합니다.
- **CSRF**: CSRF를 활성화한다면 폼에 `_csrf` hidden input이 필요합니다.

### 5) 예외 처리 컴포넌트 (선택)

- **필요(선택)**: 인증 실패 시 401, 리다이렉트 등 원하는 응답 형태로 커스터마이징
- **현재 위치**: `CustomAuthenticationEntryPoint.java`

---

필요하면, 위 문서에 “URL별 접근 가능/불가 예시”까지 추가해서 더 직관적으로 정리해드릴 수 있어요.

