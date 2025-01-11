# Petshop

## 프로젝트 개요
- Petshop은 지금까지 배운 내용을 토대로 하나의 프로젝트를 만들면 좋겠다고 생각들어 만들게 되었습니다.
- Petshop은 온라인 반려동물 용품 쇼핑몰입니다. 사용자에 따라 ROLE을 기반으로한 접근 가능한 동작을 구분하여 구성하였습니다.
- ADMIN은 상품을 등록하고, 사용자는 회원가입하고, 다양한 상품들을 장바구니에 담고, 찜하고, 주문하고, 외부 결제 API와 연동하여 결제를 구현하였습니다.
- 개발 기간 : 2024/10/18 ~ 2024/12/23 

## 시연 영상
  - URL  : https://youtu.be/OEzpm7L-wh0
  - 캡쳐 화면 : [png2pdf.pdf](https://github.com/user-attachments/files/18376865/png2pdf.pdf)
  - ROLE을 기반으로 각 회원이 접근 가능한 페이지를 구분하였고, 실제 회원가입, 로그인, 장바구니, 카트, 찜, 상품 등록,
    포트원 API를 활용한 결제 등을 구현한 모습과실제 데이터베이스에 적용된 모습을 확인할 수 있습니다.

## 주요 기능
- **회원 관리**: 회원가입 , 로그인, OAuth2.0 (google) 을 통한 회원 가입 및 로그인
- **상품**: 카테고리 및 태그를 이용한 필터링하여 결과값을 페이징하여 제공받습니다.
- **상품 상세 보기**: 선택한 상품의 상세 정보를 확인할 수 있습니다.
- **장바구니**: 관심 있는 상품을 장바구니에 추가하고 관리할 수 있습니다.
- **주문 및 결제**: 주문을 완료하고 외부 API를 이용하여 결제할 수 있습니다.
- **리뷰 및 평점**: 상품에 대한 리뷰와 평점을 남길 수 있습니다.
- **위시리스트**: 관심 있는 상품을 위시리스트에 추가하여 나중에 다시 확인할 수 있습니다.

## 기술 스택

- **백엔드**
  - Java (version 17),Spring Boot (version 3.3.4)
  - Spring Data JPA
  - Spring Security
  - OAuth2 Client 2.0
  - Spring Validation
  - Thymeleaf
- **데이터베이스**
  - H2 Database
- **프론트엔드**
  - HTML, CSS, JavaScript
  - Thymeleaf Extras Spring Security6
- **JSON 처리**
  - Jackson Databind
  - Jackson Core
- **기타 라이브러리**
  - Lombok
- **결제**
  - 포트원 API

## 프로젝트 구조
 - **프로젝트의 주요 디렉토리와 파일 구조입니다**:
 ```
 └─ java
   └─ main
      └─ com
         └─ petshop1018
            └─ sungil
               │  SungilApplication.class
               │
               ├─ config
               │  ├─ CustomAuthenticationProvider.class
               │  ├─ CustomLoginFailureHandler.class
               │  ├─ CustomLoginSuccessHandler.class
               │  ├─ CustomUserDetails.class
               │  ├─ CustomUserDetailsService.class
               │  ├─ LoginUser.class
               │  ├─ LoginUserArgumentResolver.class
               │  ├─ SecurityConfig.class
               │  └─ WebConfig.class
               │
               ├─ controller
               │  ├─ CartController.class
               │  ├─ MemberController.class
               │  ├─ OrderController.class
               │  └─ ProductController.class
               │
               ├─ domain
               │  ├─ Address.class
               │  ├─ BaseTimeEntity.class
               │  ├─ Cart.class
               │  ├─ CartItem.class
               │  ├─ Category.class
               │  ├─ CategoryInitialize.class
               │  ├─ Member.class
               │  ├─ Order.class
               │  ├─ OrderItem.class
               │  ├─ OrderStatus.class
               │  ├─ Payment.class
               │  ├─ PaymentMethod.class
               │  ├─ PaymentStatus.class
               │  ├─ Product.class
               │  ├─ ProductCategory.class
               │  ├─ ProductInitializer.class
               │  ├─ Review.class
               │  ├─ Role.class
               │  ├─ Wishlist.class
               │  └─ WishlistItem.class
               │
               ├─ dto
               │  ├─ AddCartItemDto.class
               │  ├─ AddProductDto.class
               │  ├─ CategoryDto.class
               │  ├─ CheckoutForm.class
               │  ├─ LoginDto.class
               │  ├─ MemberDto.class
               │  ├─ OrderItemDto.class
               │  ├─ ProductDto.class
               │  ├─ ReviewDto.class
               │  └─ UpdateMemberDto.class
               │
               ├─ oauth
               │  ├─ CustomOAuth2AuthenticationSuccessHandler.class
               │  ├─ CustomOAuth2UserService.class
               │  └─ OAuthAttributes.class
               │
               ├─ repository
               │  ├─ AddressRepository.class
               │  ├─ CartItemRepository.class
               │  ├─ CartRepository.class
               │  ├─ CategoryRepository.class
               │  ├─ MemberRepository.class
               │  ├─ OrderItemRepository.class
               │  ├─ OrderRepository.class
               │  ├─ PaymentRepository.class
               │  ├─ ProductCategoryRepository.class
               │  ├─ ProductRepository.class
               │  ├─ ReviewRepository.class
               │  ├─ WishlistItemRepository.class
               │  └─ WishlistRepository.class
               │
               ├─ service
               │  ├─ AddressService.class
               │  ├─ CartItemService.class
               │  ├─ CartService.class
               │  ├─ CategoryService.class
               │  ├─ FileService.class
               │  ├─ MemberService.class
               │  ├─ OrderItemService.class
               │  ├─ OrderService.class
               │  ├─ ProductService.class
               │  ├─ ReviewService.class
               │  ├─ WishListItemService.class
               │  └─ WishListService.class
               │
               └─ validator
                  ├─ AbstractValidator.class
                  ├─ CustomValidators.class
                  │  ├─ CheckoutFormValidator.class
                  │  ├─ EmailValidator.class
                  │  ├─ NicknameValidator.class
                  │  └─ UsernameValidator.class 
 ```

##  ERD
- ERD는 다음과 같습니다

![ERD](https://github.com/user-attachments/assets/f2c9b629-a12d-45b6-bfc2-eaafbc666580)

---


### Spring Security 및 OAuth2.0 클래스별 흐름 및 작동 원리

#### 1. Spring Security 주요 클래스와 역할

1. **SecurityConfig**
   - **역할:**
     - Spring Security의 전반적인 설정을 담당합니다.
     - HTTP 요청별 보안 규칙, 인증/인가 처리 방식, OAuth2 설정 등을 정의합니다.
     - CSRF 설정, 권한 설정, 로그인/로그아웃 설정 등을 정의합니다.
   - **구성요소:**
     - **HttpSecurity:** 각 요청에 대한 권한 설정.
     - **PasswordEncoder:** 비밀번호 암호화를 위한 `BCryptPasswordEncoder` 설정.
     - **CustomAuthenticationProvider:** 사용자 인증을 커스터마이징.
     - **OAuth2 관련 핸들러 설정:**
       - **CustomOAuth2UserService:** OAuth2 사용자 정보를 처리.
       - **CustomOAuth2AuthenticationSuccessHandler:** 로그인 성공 후 로직 처리.

2. **CustomAuthenticationProvider**
   - **역할:** 사용자 인증을 처리하는 커스텀 인증 프로바이더.
   - **주요 메서드:**
     - `authenticate`: 사용자 이름과 비밀번호를 검증. 성공 시 `UsernamePasswordAuthenticationToken`을 반환.
     - `supports`: 지원하는 인증 객체 타입을 지정.

3. **CustomLoginFailureHandler**
   - **역할:** 로그인 실패 시 처리하는 핸들러.
   - **주요 메서드:**
     - `onAuthenticationFailure`: 다양한 인증 예외 상황에 맞는 에러 메시지를 설정하고, 실패 시 특정 URL로 리다이렉트.

4. **CustomLoginSuccessHandler**
   - **역할:** 로그인 성공 시 처리하는 핸들러.
   - **주요 메서드:**
     - `onAuthenticationSuccess`: 로그인한 사용자 정보를 세션에 저장하고, 성공 후 사용자 권한(ROLE_USER, ROLE_ADMIN)에 따라 특정 URL로 리다이렉트.

5. **CustomUserDetails**
   - **역할:** Spring Security의 UserDetails 인터페이스를 구현하여 사용자 세부 정보를 캡슐화.
   - **주요 필드 및 메서드:**
     - `getPassword`, `getUsername`, `getAuthorities` 등.

6. **CustomUserDetailsService**
   - **역할:** 사용자 정보를 데이터베이스에서 조회하여 `UserDetails` 형태로 반환.
   - **주요 메서드:**
     - `loadUserByUsername`: 사용자 이름을 기반으로 사용자 정보를 조회.

7. **LoginUser**
   - **역할:** 세션 관련 중복 코드를 제거하기 위한 어노테이션.

8. **LoginUserArgumentResolver**
   - **역할:** `@LoginUser` 어노테이션이 붙은 파라미터를 처리하는 리졸버.
   - **주요 메서드:**
     - `supportsParameter`: `@LoginUser` 어노테이션과 파라미터 타입을 확인.
     - `resolveArgument`: 세션에서 사용자 정보를 가져옴.

9. **WebConfig**
   - **역할:** Spring MVC에서 정적 리소스를 처리하고, `LoginUserArgumentResolver`를 추가하는 설정 클래스.

#### 2. OAuth2 관련 클래스와 역할

1. **OAuthAttributes**
   - **역할:**
     - `of()` 메서드를 통해 제공자별로 사용자 정보 매핑.
     - 사용자 정보를 표준화된 형태로 변환.
     - `toEntity()` 메서드를 통해 매핑된 사용자 정보를 엔티티로 변환 및 Role 설정.
   - **동작:**
     - `of()` 메서드 호출.
     - 제공된 OAuth2 사용자 정보를 매핑.
     - `toEntity()` 메서드로 사용자 엔티티로 변환.

2. **CustomOAuth2UserService**
   - **역할:**
     - OAuth2 로그인 시 사용자 정보를 처리.
     - 새 사용자면 DB에 저장하고, 기존 사용자면 정보를 갱신.
   - **동작:**
     - `loadUser(OAuth2UserRequest userRequest)` 호출.
     -  제공된 OAuth2User 정보를 가져옴.
     -  사용자 정보를 DB에 저장 또는 업데이트.

3. **CustomOAuth2AuthenticationSuccessHandler**
   - **역할:**
     - OAuth2 인증 성공 후 사용자 처리.
   - **동작:**
     - OAuth2 인증 성공 후 호출됨.
     - 사용자의 권한에 따라 다른 URL로 리다이렉션.

---

### 전체 동작 흐름

1. **사용자 요청**
   - 사용자가 로그인 페이지에서 사용자 이름과 비밀번호를 입력하고 로그인 요청을 보냅니다.

2. **CustomAuthenticationProvider**
   - `authenticate` 메서드를 통해 사용자 이름과 비밀번호를 검증합니다.
   - 사용자 정보가 일치하면 `UsernamePasswordAuthenticationToken`을 반환하여 인증을 완료합니다.

3. **인증 성공**
   - `CustomLoginSuccessHandler`가 호출되어 로그인한 사용자 정보를 세션에 저장하고, 성공 후 특정 URL로 리다이렉트합니다.

4. **인증 실패**
   - `CustomLoginFailureHandler`가 호출되어 실패 이유에 따라 적절한 에러 메시지를 설정하고, 실패 후 특정 URL로 리다이렉트합니다.

5. **CSRF 설정 및 OAuth2 로그인**
   - `SecurityConfig`에서 CSRF 보호와 OAuth2 로그인 설정을 관리합니다.
   - OAuth2 로그인 성공 시 `CustomOAuth2AuthenticationSuccessHandler`가 호출됩니다.

6. **리소스 핸들링**
   - `WebConfig`에서 정적 리소스 경로를 설정하고, `LoginUserArgumentResolver`를 추가하여 세션에서 사용자 정보를 가져옵니다.

---

### Security 및 OAuth2 플로우

1. **Spring Security 인증 플로우**
   ```plaintext
   [Spring Security Filter Chain]  // Spring Security 필터 체인
      |
      v
   [CustomAuthenticationProvider]  // 사용자 인증을 처리하는 커스텀 인증 프로바이더
      |-- 사용자 ID, 비밀번호 검증
      |-- 성공 시: Authentication 객체 생성
      |-- 실패 시: 인증 예외 발생
   [CustomUserDetailsService]  // 사용자 정보를 데이터베이스에서 조회하는 서비스
      |
      v
   [CustomUserDetails]  // 사용자 세부 정보를 캡슐화한 클래스
      |
      v
   [CustomLoginSuccessHandler]  // 로그인 성공 시 후속 처리 핸들러 (성공 시 리다이렉션)
      |-- 사용자 권한 확인 및 리다이렉션
      v
   [CustomLoginFailureHandler]  // 로그인 실패 시 후속 처리 핸들러 (실패 시 특정 URL로 리다이렉트)
   ```

2. **OAuth2 인증 플로우**
   ```plaintext
   [User OAuth2 Login Request]  // 사용자 OAuth2 로그인 요청
      |
      v
   [Spring Security Filter Chain]  // Spring Security에서 요청을 필터링
      |
      v
   [OAuth2 Login Filter]  // OAuth2 로그인 필터가 요청을 처리
      |
      v
   [CustomOAuth2UserService]  // 사용자 정보를 로드하는 서비스
      |-- OAuth2 로그인 시 사용자 정보를 로드, 사용자 정보를 저장/업데이트 및 역할 설정
      v
   [OAuthAttributes]  // 사용자 속성을 매핑
      |
      v
   [OAuth2AuthenticationToken]  // 인증 토큰이 생성, 인증 성공
      |
      v
   [CustomOAuth2AuthenticationSuccessHandler]  // 로그인 성공 후 처리
      |-- 권한 확인 및 리다이렉션
      v
   [Application Controller 처리]
   ```

3. **HTTP 요청에 따른 보안 흐름**
   ```plaintext
   [HTTP 요청 (예: /admin, /login)]
      |
      v
   [SecurityFilterChain]
      |
      v
   [HttpSecurity 설정 매핑]
      |-- /admin → ROLE_ADMIN 권한 필요
      |-- /user → ROLE_USER 권한 필요
      v
   [인가 확인]
      |-- 인증된 사용자만 접근 가능
      |-- 인증 실패 시: AccessDeniedHandler 호출
   ```

4. **권한 관리:**
   - `/admin/**`: ROLE_ADMIN 권한만 접근 가능.
   - `/user/**`: ROLE_USER, ROLE_ADMIN 모두 접근 가능.
   - `/oauth2/**`: OAuth2 인증으로 처리.

5. **예외 처리:**
   - 인증 실패: `CustomLoginFailureHandler`에서 처리.
   - 권한 부족: `AccessDeniedHandler`에서 처리.

6. **비밀번호 보안:**
   - 비밀번호는 `BCryptPasswordEncoder`로 암호화하여 저장.

---


## 1. 유저 플로우 (User Flow)

#### **1) 회원가입 및 로그인**  
- 사용자는 `회원가입` 또는 `로그인`을 통해 애플리케이션에 접근할 수 있습니다.  
- 로그인 성공 후, 사용자 권한(`ROLE_USER`, `ROLE_ADMIN`, `ROLE_SOCIAL`)에 따라 접근 가능한 페이지가 달라집니다.  

#### **2) 상품 탐색 및 관리**  
- 사용자는 상품 목록 페이지에서 상품을 탐색할 수 있습니다.  
- 상품 검색, 필터링을 통해 원하는 상품을 찾습니다.  

#### **3) 장바구니 관리**  
- 사용자가 상품을 장바구니에 담아 저장합니다.  
- 필요 시 수량을 변경하거나 삭제합니다.  

#### **4) 결제 및 주문**  
- 사용자가 결제를 진행하고, 주문을 생성합니다.  
- 결제가 성공적으로 완료되면 주문 내역이 저장됩니다.  

#### **5) OAuth2 소셜 로그인**  
- Google, Facebook 등의 OAuth2 제공자를 통해 소셜 로그인할 수 있습니다.  
- 로그인 성공 시 새로운 계정을 생성하거나 기존 계정과 연결됩니다.  

---

## 2. 회원가입 및 로그인

### 2.1 회원가입 및 로그인

#### **회원가입 (Sign Up)**
1. 유저가 회원가입 페이지에 접근  
    - `/register` URL로 접근.
2. 유저 입력 데이터를 폼 제출  
    - `MemberController.register()` 메서드가 요청을 처리.
3. 데이터 검증 및 저장  
    - `MemberService.saveMember()`에서 비밀번호 암호화 및 DB에 저장.
    - `MemberRepository.save()`로 DB에 저장.
4. 회원가입 완료 후 페이지 이동  
    - 회원가입 완료 메시지를 출력 후 로그인 페이지로 리다이렉트.

#### **로그인 (Login)**
1. 유저가 로그인 페이지에 접근  
    - `/login` URL로 접근.
2. Spring Security 인증 처리  
    - `CustomAuthenticationProvider`가 사용자 인증.
3. 결과 처리  
    - 성공 시: `CustomLoginSuccessHandler`가 홈 페이지로 리다이렉트.
    - 실패 시: `CustomLoginFailureHandler`가 에러 메시지 표시.

---

### 2.2 상품 조회 및 장바구니 관리

#### **상품 조회 (View Products)**
1. 유저가 상품 리스트 페이지에 접근  
    - `/products` URL 호출.
2. 상품 데이터 조회  
    - `ProductController.getProducts()`에서 요청을 처리.
    - `ProductService.getAllProducts()`를 통해 상품 리스트를 가져옴.
3. 상품 목록 출력  
    - `ProductRepository.findAll()`로 상품 데이터 조회 후, 뷰에 전달하여 출력.

#### **장바구니 추가 (Add to Cart)**
1. 유저가 상품을 장바구니에 추가  
    - `/cart/add` URL 호출.
2. 장바구니 로직 처리  
    - `CartController.addToCart()`에서 요청 처리.
    - `CartItemService.addCartItem()`가 장바구니에 상품을 추가하거나 수량을 업데이트.
    - `CartItemRepository.save()`로 DB에 장바구니 아이템을 저장.

---

### 2.3 주문 생성 및 결제

#### **주문 생성 (Create Order)**
1. 유저가 장바구니 페이지에서 "주문하기" 클릭  
    - `/checkout` URL 호출.
2. 주문 생성 로직 처리  
    - `OrderController.placeOrder()`가 요청을 처리.
    - `OrderService.createOrder()`에서 주문 엔티티를 생성하고 DB에 저장.
    - `OrderRepository.save()`로 DB에 저장.
3. 주문 완료 후 상태 업데이트 및 리다이렉트  
    - 주문 상태가 PENDING에서 COMPLETED로 업데이트됨.

#### **결제 (Payment)**
1. 결제 정보 입력 및 처리  
    - Portone API를 이용하여 결제 시스템 연동, 실제 결제 시스템 적용
    - `PaymentService.processPayment()`에서 결제 정보를 처리.
    - 결제 성공 시, 주문 상태를 TEMPORARY->COMPLETED로 업데이트.
    - 실패 시, 에러 메시지를 사용자에게 전달.

---

### 2.4 OAuth2 소셜 로그인
1. 소셜 로그인 요청  
    - `/oauth2/authorization/{provider}` URL 호출.
2. Spring Security OAuth2 인증 처리  
    - `CustomOAuth2UserService.loadUser()`에서 사용자 정보를 매핑.
3. 소셜 계정과 기존 계정 연결 또는 새 계정 생성  
    - OAuth2 로그인 후 기존 계정이 있을 경우 연결하고, 없으면 새 계정을 생성.

---

## 3. 코드 순서도

### 3.1 회원가입 요청 처리
```
[MemberController.register()]
   |
   v
[DTO 생성 및 유효성 검사] (회원가입 폼)
   |
   v
[MemberService.saveMember()]
   |
   v
[비밀번호 암호화] (BCryptPasswordEncoder)
   |
   v
[MemberRepository.save()]
   |
   v
[회원가입 완료 → View 리턴] (회원가입 완료 메시지)
```

---

### 3.2 로그인 요청 처리
```
[Spring Security Filter]
   |
   v
[CustomAuthenticationProvider]
|
   v

[CustomUserDetailsService] (사용자 정보 로드, DB에서 사용자 정보 조회)
   |
   v
[CustomUserDetails] (사용자 정보를 담고 있는 객체)
   |
   v
[LoginUser] (로그인 시 사용자 정보를 담는 객체)
   |
   v
[LoginUserArgumentResolver] (컨트롤러 메서드에서 로그인 사용자 정보 자동 주입)
   |
   |
   v
[CustomLoginSuccessHandler (성공 시 리다이렉션)]
   |
   v
[CustomLoginFailureHandler (실패 시 에러 메시지)]
   |
   v
[WebConfig] (웹 관련 설정, 인터셉터 등록 및 설정)
```

---

### 3.3 상품 조회 및 장바구니 추가
-	상품 조회 
```
[ProductController.getProducts()]
   |
   v
[ProductService.getAllProducts()]
   |
   v
[ProductRepository.findAll()]
   |
   v
[상품 데이터 View에 전달] (상품 목록 출력)
```
---

-	장바구니 추가
```
[CartController.addToCart()]
   |
   v
[CartItemService.addCartItem()]
   |
   v
[CartItemRepository.save()]
   |
   v
[장바구니 데이터 View에 갱신] (장바구니 UI 갱신)
```


---

### 3.4 주문 생성 및 결제 처리
-	주문 생성 
```
[OrderController.placeOrder()]
   |
   v
[OrderService.createOrder()]
   |
   v
[OrderRepository.save()]
   |
   v
[주문 완료 → View 리턴] (주문 확인 페이지)
```
---

-	결제 처리 
```
[PaymentService.processPayment()]
   |
   v
[결제 성공 → Order 상태 업데이트] (PENDING → COMPLETED)
   |
   v
[결제 완료 → View 리턴] (결제 완료 페이지)
```

---

## 4. 적용 사항

### 4.1 보안(Security)
- Spring Security로 세션 기반 인증 및 OAuth2 소셜 로그인 처리.
- 비밀번호 암호화: 회원가입 시 `BCryptPasswordEncoder`를 사용해 비밀번호 암호화.
- 권한 관리: 사용자 권한에 따라 접근 제한. 예: 일반 유저는 상품 보기, 장바구니 추가 가능. 관리자만 상품 수정, 삭제 가능.

### 4.2 데이터 유효성 검증
- DTO 검증: 회원가입, 로그인 시 유효성 검사를 DTO에서 처리. (예: 이메일 포맷, 비밀번호 길이 등)
- 예외 처리: 잘못된 입력은 예외를 발생시켜 적절한 메시지를 사용자에게 전달.

### 4.3 서비스 분리
- 서비스 계층 분리: 각 비즈니스 로직은 서비스 계층에서 처리하고, 컨트롤러는 요청을 받아 서비스 호출만 담당.

### 4.4 결제 처리
- 결제 API를 외부 서비스(PortOne API)와 연동하여 처리.
- 결제 성공 시 `Product`- (OrderQuantity 증가, stockQuantity 감소), `OrderStatus`(Pending->Confirmed), `PaymentStatus`(Temporary -> Completed), `Payment`- (transaction_ID, storeID) 저장.

---

## 5. 프로젝트 구조

### 5.1 Member 관련 기능
- `MemberController`: 회원가입 및 로그인 처리.
- `MemberService`: 회원 가입, 비밀번호 암호화, 유효성 검사.
- `MemberRepository`: DB와의 연결.

### 5.2 상품 관련 기능
- `ProductController`: 상품 조회, 상세 조회 처리.
- `ProductService`: 상품 리스트 처리.
- `ProductRepository`: 상품 DB 조회.

### 5.3 장바구니 기능
- `CartController`: 장바구니 추가/삭제 처리.
- `CartItemService`: 장바구니 아이템 관리.
- `CartItemRepository`: 장바구니 아이템 DB 저장.

### 5.4 주문 및 결제 처리
- `OrderController`: 주문 생성.
- `OrderService`: 주문 생성, 결제 처리.
- `OrderRepository`: 주문 DB 저장.
- `PaymentService`: 결제 처리 및 상태 업데이트.

### 5.5 OAuth2 로그인
- `CustomOAuth2UserService`: 소셜 로그인 처리.
- `CustomAuthenticationProvider`: 사용자 인증 처리.


감사합니다.
----

