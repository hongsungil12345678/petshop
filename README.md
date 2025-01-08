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
프로젝트의 주요 디렉토리와 파일 구조입니다
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

### 주요 구성 요소

- **config 패키지**:
  - 보안 설정과 관련된 클래스를 포함하고 있습니다.
  - `SecurityConfig`: 전체 보안 설정을 관리합니다.
  - `CustomAuthenticationProvider`: 사용자 인증을 커스터마이징하기 위한 클래스.
  - `CustomLoginSuccessHandler`: 로그인 성공 시 처리를 담당합니다.
  - `CustomLoginFailureHandler`: 로그인 실패 시 처리를 담당합니다.

- **controller 패키지**:
  - 웹 요청을 처리하는 컨트롤러 클래스를 포함하고 있습니다.
  - `CartController`: 장바구니 관련 요청을 처리합니다.
  - `MemberController`: 회원 관리 관련 요청을 처리합니다.
  - `OrderController`: 주문 관련 요청을 처리합니다.
  - `ProductController`: 상품 관련 요청을 처리합니다.

- **domain 패키지**:
  - 데이터베이스 엔티티 클래스를 포함하고 있습니다.
  - `Member`: 회원 정보를 저장합니다.
  - `Order`: 주문 정보를 저장합니다.
  - `Product`: 상품 정보를 저장합니다.
  - `CartItem`: 장바구니 아이템 정보를 저장합니다.

- **dto 패키지**:
  - 데이터 전송 객체를 포함하고 있습니다.
  - `LoginDto`: 로그인 요청 데이터를 담습니다.
  - `MemberDto`: 회원 정보를 담습니다.
  - `ProductDto`: 상품 정보를 담습니다.

- **repository 패키지**:
  - 데이터베이스와 상호작용하는 리포지토리를 포함하고 있습니다.
  - `MemberRepository`: 회원 정보를 조회 및 관리합니다.
  - `OrderRepository`: 주문 정보를 조회 및 관리합니다.
  - `ProductRepository`: 상품 정보를 조회 및 관리합니다.

- **service 패키지**:
  - 비즈니스 로직을 처리하는 서비스를 포함하고 있습니다.
  - `MemberService`: 회원 관련 비즈니스 로직을 처리합니다.
  - `OrderService`: 주문 관련 비즈니스 로직을 처리합니다.
  - `ProductService`: 상품 관련 비즈니스 로직을 처리합니다.

- **validator 패키지**:
  - 유효성 검사를 처리하는 클래스를 포함하고 있습니다.
  - `AbstractValidator`: 공통 유효성 검사 로직을 처리합니다.
  - `CustomValidators`: 다양한 커스텀 유효성 검사 로직을 포함합니다.

이런 방식으로 작성하면 중요한 부분을 강조하면서도 전체 구조를 명확히 전달할 수 있어. 추가적으로 궁금한 사항이나 더 필요한 정보가 있으면 언제든지 말해줘! 😊📚



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

