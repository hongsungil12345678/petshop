# PetShop 프로젝트 코드 순서도 및 유저 플로우

## 1. 유저 플로우 (User Flow)

### 1.1 회원가입 및 로그인

- **회원가입 (Sign Up)**:

  1. 유저가 회원가입 페이지에 접근합니다.
  2. 입력 폼에서 정보를 입력하고 제출합니다.
  3. `MemberController`가 요청을 받아 DTO를 생성하여 `MemberService`로 전달합니다.
  4. `MemberService`가 비즈니스 로직을 처리하고 `MemberRepository`를 통해 데이터를 저장합니다.

- **로그인 (Login)**:

  1. 유저가 로그인 페이지에 접근합니다.
  2. 입력 폼에서 아이디와 비밀번호를 입력하고 제출합니다.
  3. Spring Security의 `CustomAuthenticationProvider`가 인증을 처리합니다.
  4. 성공 시 `CustomLoginSuccessHandler`가 유저를 메인 페이지로 리다이렉트합니다.
  5. 실패 시 `CustomLoginFailureHandler`가 에러 메시지를 표시합니다.

### 1.2 상품 조회 및 장바구니 추가

- **상품 조회 (Product Viewing)**:

  1. 유저가 상품 리스트 페이지에 접근합니다.
  2. `ProductController`가 요청을 처리하고 상품 데이터를 조회하여 뷰에 전달합니다.

- **장바구니 추가 (Add to Cart)**:

  1. 유저가 특정 상품을 장바구니에 추가합니다.
  2. `CartController`가 요청을 받아 `CartItemService`로 전달합니다.
  3. `CartItemService`는 장바구니 항목을 생성하고 `CartItemRepository`를 통해 저장합니다.

### 1.3 주문 생성 및 결제

- **주문 생성 (Create Order)**:

  1. 유저가 장바구니 페이지에서 "주문하기" 버튼을 클릭합니다.
  2. `OrderController`가 요청을 처리하여 주문 DTO를 생성합니다.
  3. `OrderService`가 비즈니스 로직을 처리하고 `OrderRepository`를 통해 주문 데이터를 저장합니다.

- **결제 (Payment)**:

  1. 유저가 결제 정보를 입력합니다.
  2. `PaymentService`가 결제를 처리합니다.
  3. 성공 시 주문 상태를 `COMPLETED`로 업데이트합니다.

---

## 2. 코드 순서도

### 2.1 회원가입 요청 처리

```plaintext
[MemberController]
   |
   v
[DTO 생성]
   |
   v
[MemberService]
   |
   v
[MemberRepository (DB 저장)]
```

### 2.2 로그인 요청 처리

```plaintext
[Spring Security Filter]
   |
   v
[CustomAuthenticationProvider]
   |
   v
[CustomLoginSuccessHandler or CustomLoginFailureHandler]
```

### 2.3 상품 조회 및 장바구니 추가

```plaintext
[ProductController]
   |
   v
[ProductService]
   |
   v
[ProductRepository (DB 조회)]

[CartController]
   |
   v
[CartItemService]
   |
   v
[CartItemRepository (DB 저장)]
```

### 2.4 주문 생성 및 결제 처리

```plaintext
[OrderController]
   |
   v
[OrderService]
   |
   v
[OrderRepository (DB 저장)]
   |
   v
[PaymentService]
   |
   v
[결제 처리 후 상태 업데이트]
```

---

## 3. 추가 고려 사항

- **Validation**:
  모든 폼 입력 데이터는 `validator` 패키지에서 검증됩니다.
- **예외 처리**:
  주요 로직에서 발생할 수 있는 예외는 서비스 계층에서 처리되며, 컨트롤러는 사용자 친화적인 에러 메시지를 제공합니다.
- **OAuth2**:
  소셜 로그인은 `CustomOAuth2UserService`와 `CustomOAuth2AuthenticationSuccessHandler`를 통해 처리됩니다.

---

## 4. 순서도 사용 방법

위의 플로우와 순서도를 기반으로 애플리케이션의 동작을 이해하고, 추가 기능 개발 시 적절한 위치에 코드를 추가하거나 수정할 수 있습니다.

