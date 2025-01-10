# Petshop

## 프로젝트 개요
Petshop은 온라인 반려동물 용품 쇼핑몰입니다. 사용자는 다양한 반려동물 용품을 탐색하고, 장바구니에 담고, 주문하고, 결제할 수 있습니다. 
이 프로젝트는 반려동물 용품의 원활한 쇼핑 경험을 제공하기 위해 설계되었습니다.

## 주요 기능
- **회원 관리**: 사용자 등록, 로그인, 프로필 관리 기능을 제공합니다.
- **상품 브라우징**: 카테고리별로 상품을 탐색하고 검색할 수 있습니다.
- **상품 상세 보기**: 선택한 상품의 상세 정보를 확인할 수 있습니다.
- **장바구니**: 관심 있는 상품을 장바구니에 추가하고 관리할 수 있습니다.
- **주문 및 결제**: 주문을 완료하고 다양한 결제 방법을 통해 결제할 수 있습니다.
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

## 설치 및 실행 방법
1. 저장소 클론
   ```sh
   git clone https://github.com/hongsungil12345678/petshop1018.git

## 프로젝트 구조
 - **프로젝트의 주요 디렉토리와 파일 구조입니다**:
 - ``` └─ java
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

   

## 보안 설정

### 주요 클래스 및 역할

1. **CustomAuthenticationProvider**
   - **역할**: 사용자 인증을 처리하는 커스텀 인증 프로바이더.
   - **주요 메서드**:
     - `authenticate`: 사용자 이름과 비밀번호를 검증. 성공 시 `UsernamePasswordAuthenticationToken`을 반환.
     - `supports`: 지원하는 인증 객체 타입을 지정.

2. **CustomLoginFailureHandler**
   - **역할**: 로그인 실패 시 처리하는 핸들러.
   - **주요 메서드**:
     - `onAuthenticationFailure`: 다양한 인증 예외 상황에 맞는 에러 메시지를 설정하고, 실패 시 특정 URL로 리다이렉트.

3. **CustomLoginSuccessHandler**
   - **역할**: 로그인 성공 시 처리하는 핸들러.
   - **주요 메서드**:
     - `onAuthenticationSuccess`: 로그인한 사용자 정보를 세션에 저장하고, 성공 후 특정 URL로 리다이렉트.

4. **CustomUserDetails**
   - **역할**: Spring Security의 `UserDetails` 인터페이스를 구현하여 사용자 세부 정보를 캡슐화.
   - **주요 필드 및 메서드**:
     - `getPassword`, `getUsername`, `getAuthorities` 등.

5. **CustomUserDetailsService**
   - **역할**: 사용자 정보를 데이터베이스에서 조회하여 `UserDetails` 형태로 반환.
   - **주요 메서드**:
     - `loadUserByUsername`: 사용자 이름을 기반으로 사용자 정보를 조회.

6. **LoginUser**
   - **역할**: 세션 관련 중복 코드를 제거하기 위한 어노테이션.

7. **LoginUserArgumentResolver**
   - **역할**: `@LoginUser` 어노테이션이 붙은 파라미터를 처리하는 리졸버.
   - **주요 메서드**:
     - `supportsParameter`: `@LoginUser` 어노테이션과 파라미터 타입을 확인.
     - `resolveArgument`: 세션에서 사용자 정보를 가져옴.

8. **SecurityConfig**
   - **역할**: 보안 설정을 관리하는 클래스.
   - **주요 설정**:
     - CSRF 설정, 권한 설정, 로그인/로그아웃 설정, OAuth2 로그인 설정 등.

9. **WebConfig**
   - **역할**: Spring MVC에서 정적 리소스를 처리하고, `LoginUserArgumentResolver`를 추가하는 설정 클래스.

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





# PetShop 프로젝트 코드 순서도 및 유저 플로우
## 1. 프로젝트 개요




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

