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
 - ```
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

아래는 분석한 내용을 바탕으로 코드 **순서도**와 **유저 플로우(User Flow)**를 작성하여 자세히 정리한 결과입니다.  

---

### **1. 코드 순서도**  
(아래의 순서도는 주요 흐름을 간략화한 것입니다. 상세 코드는 이를 바탕으로 확장될 수 있습니다.)  

#### **회원가입 및 로그인**  
1. **회원가입**  
   - `MemberController.register()` 호출  
     - 사용자 입력 데이터 유효성 검증 → `MemberService.saveMember()` 호출  
       - 비밀번호 암호화 → `MemberRepository.save()`로 데이터 저장  

2. **로그인**  
   - Spring Security의 `/login` 엔드포인트  
     - `CustomAuthenticationProvider.authenticate()` 호출  
       - 사용자 정보 확인 및 인증 처리  
     - 성공 시: `CustomLoginSuccessHandler`에서 리디렉션  

---

#### **상품 탐색 및 장바구니**  
1. **상품 목록 조회**  
   - `ProductController.getProducts()` 호출  
     - `ProductService.getAllProducts()` → `ProductRepository.findAll()`  
     - 조회된 상품 데이터를 View로 전달  

2. **상품 상세 정보 조회**  
   - `ProductController.getProductDetail(Long productId)` 호출  
     - `ProductService.getProductById(productId)` → `ProductRepository.findById()`  

3. **장바구니 추가**  
   - `CartController.addToCart()` 호출  
     - 사용자가 상품과 수량을 입력 → `CartItemService.addCartItem()` 호출  
       - 기존 장바구니 확인 및 아이템 추가  

---

#### **체크아웃 및 결제**  
1. **체크아웃 화면 로드**  
   - `OrderController.checkout()` 호출  
     - 장바구니 정보를 조회 → `CartService.getCartItemsByUserId()`  

2. **주문 생성**  
   - `OrderController.placeOrder()` 호출  
     - `OrderService.createOrder()`  
       - 주문 엔티티 생성 → `OrderRepository.save()`  

3. **결제 처리**  
   - 결제 정보 입력 → `PaymentService.processPayment()` 호출  
     - 결제 성공 시 `OrderStatus`를 업데이트  

---

#### **OAuth2 소셜 로그인**  
1. **소셜 로그인 요청**  
   - `/oauth2/authorization/{provider}` 요청 → Spring Security OAuth2 필터 처리  
     - `CustomOAuth2UserService.loadUser()` 호출  
       - 소셜 사용자 정보 매핑  
     - 성공 시 `CustomOAuth2AuthenticationSuccessHandler`에서 사용자 처리  

---

### **2. 유저 플로우(User Flow)**  

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

### **3. 프로젝트 전반 설명**  

#### **기능별 주요 구성**  

1. **보안(Security)**  
   - Spring Security와 OAuth2를 활용하여 인증/인가를 관리합니다.  
   - 권한(Role-based Access Control)으로 접근 제한을 구현합니다.  

2. **도메인 설계**  
   - `Member`, `Product`, `Cart`, `Order` 등의 엔티티 중심으로 설계되었습니다.  
   - 각 엔티티는 명확한 역할을 가지고 있으며, 관계를 통해 데이터 무결성을 유지합니다.  

3. **서비스 계층**  
   - 비즈니스 로직은 `Service` 계층에서 처리됩니다.  
   - 데이터 접근은 `Repository` 계층을 통해 이루어집니다.  

4. **검증 및 예외 처리**  
   - 데이터 유효성 검증은 DTO 계층에서 처리됩니다.  
   - 글로벌 예외 처리기가 추가되어 오류를 관리합니다.  

5. **RESTful 설계**  
   - 대부분의 컨트롤러는 RESTful 스타일로 작성되었습니다.  
   - 클라이언트와 서버 간 통신은 JSON을 기본으로 사용합니다.  

6. **파일 업로드**  
   - `FileService`를 통해 파일 업로드 및 저장 기능을 구현합니다.  

---

### **순서도 예시**  
아래는 회원가입 및 장바구니 추가 과정을 시각적으로 간단히 나타낸 순서도입니다.

```plaintext
회원가입:  
1. 사용자가 회원가입 요청 → 2. 데이터 유효성 검증 → 3. Member 엔티티 생성 → 4. MemberRepository.save()  
→ 5. 회원가입 성공  

장바구니 추가:  
1. 사용자가 상품을 선택 후 장바구니 추가 요청  
2. CartItemService.addCartItem() 호출  
3. 기존 장바구니 확인  
  - 3-1. 동일 상품 존재: 수량 업데이트  
  - 3-2. 동일 상품 없음: 새 CartItem 추가  
4. CartRepository.save() → 장바구니 갱신 완료  
```

---

더 자세한 코드 또는 흐름도 생성이 필요하다면 알려주세요! 😊
아래는 제공된 PetShop 프로젝트의 코드를 분석한 결과를 바탕으로 회원가입, 로그인, 상품 조회, 장바구니 추가, 주문 및 결제 처리 등을 포함한 **유저 플로우(User Flow)** 및 **코드 순서도**를 작성한 것입니다. **GitHub 형식**과 비슷하게 작성하여 코드 흐름을 쉽게 이해할 수 있도록 구성했습니다.

---

# **PetShop 프로젝트 코드 순서도 및 유저 플로우**

## **1. 유저 플로우 (User Flow)**

### **1.1 회원가입 및 로그인**

#### **회원가입 (Sign Up)**

1. **유저가 회원가입 페이지에 접근**
   - `/register` URL 호출.
2. **유저 입력 데이터를 폼 제출**
   - `MemberController.register()`가 요청 처리.
3. **데이터 검증 및 저장**
   - `MemberService.saveMember()`에서 비밀번호 암호화 및 DB 저장.
   - `MemberRepository.save()`로 DB에 저장.

#### **로그인 (Login)**

1. **유저가 로그인 페이지에 접근**
   - `/login` URL 호출.
2. **Spring Security 인증 처리**
   - `CustomAuthenticationProvider`가 사용자 인증.
3. **결과 처리**
   - 성공 시: `CustomLoginSuccessHandler`가 홈 페이지로 리다이렉트.
   - 실패 시: `CustomLoginFailureHandler`가 에러 메시지 표시.

---

### **1.2 상품 조회 및 장바구니 관리**

#### **상품 조회 (View Products)**

1. **유저가 상품 리스트 페이지에 접근**
   - `/products` URL 호출.
2. **상품 데이터 조회**
   - `ProductController.getProducts()`가 요청 처리.
   - `ProductService.getAllProducts()`를 통해 상품 리스트 가져옴.
   - 결과를 View에 전달하여 출력.

#### **장바구니 추가 (Add to Cart)**

1. **유저가 상품을 장바구니에 추가**
   - `/cart/add` URL 호출.
2. **장바구니 로직 처리**
   - `CartController.addToCart()`에서 요청 처리.
   - `CartItemService.addCartItem()`이 장바구니에 상품 추가.
   - 중복 상품이면 수량 업데이트, 아니면 새 상품 추가.

---

### **1.3 주문 생성 및 결제**

#### **주문 생성 (Create Order)**

1. **유저가 장바구니 페이지에서 "주문하기" 클릭**
   - `/checkout` URL 호출.
2. **주문 생성 로직 처리**
   - `OrderController.placeOrder()`가 요청 처리.
   - `OrderService.createOrder()`에서 주문 엔티티 생성 및 저장.
   - `OrderRepository.save()`로 DB에 저장.

#### **결제 (Payment)**

1. **결제 정보 입력 및 처리**
   - `PaymentService.processPayment()`가 요청 처리.
   - 성공 시 주문 상태를 `COMPLETED`로 업데이트.

---

### **1.4 OAuth2 소셜 로그인**

1. **소셜 로그인 요청**
   - `/oauth2/authorization/{provider}` URL 호출.
2. **Spring Security OAuth2 인증 처리**
   - `CustomOAuth2UserService.loadUser()`가 사용자 정보 매핑.
   - 성공 시 기존 계정과 연결하거나 새 계정 생성.

---

## **2. 코드 순서도**

### **2.1 회원가입 요청 처리**

```plaintext
[MemberController.register()]
   |
   v
[DTO 생성 및 유효성 검사]
   |
   v
[MemberService.saveMember()]
   |
   v
[비밀번호 암호화]
   |
   v
[MemberRepository.save()]
   |
   v
[회원가입 완료 → View 리턴]
```

---

### **2.2 로그인 요청 처리**

```plaintext
[Spring Security Filter]
   |
   v
[CustomAuthenticationProvider.authenticate()]
   |
   v
[CustomLoginSuccessHandler (성공 시 리다이렉션)]
   |
   v
[CustomLoginFailureHandler (실패 시 에러 메시지)]
```

---

### **2.3 상품 조회 및 장바구니 추가**

#### **상품 조회**

```plaintext
[ProductController.getProducts()]
   |
   v
[ProductService.getAllProducts()]
   |
   v
[ProductRepository.findAll()]
   |
   v
[상품 데이터 View에 전달]
```

#### **장바구니 추가**

```plaintext
[CartController.addToCart()]
   |
   v
[CartItemService.addCartItem()]
   |
   v
[CartItemRepository.save()]
   |
   v
[장바구니 데이터 View에 갱신]
```

---

### **2.4 주문 생성 및 결제 처리**

#### **주문 생성**

```plaintext
[OrderController.placeOrder()]
   |
   v
[OrderService.createOrder()]
   |
   v
[OrderRepository.save()]
   |
   v
[주문 완료 → View 리턴]
```

#### **결제 처리**

```plaintext
[PaymentService.processPayment()]
   |
   v
[결제 성공 → Order 상태 업데이트]
   |
   v
[결제 완료 → View 리턴]
```

---

## **3. 추가 고려 사항**

1. **보안(Security)**
   - Spring Security를 기반으로 세션 및 OAuth2 인증 처리.
   - 사용자 권한(`ROLE_USER`, `ROLE_ADMIN`, `ROLE_SOCIAL`)에 따른 접근 제한.

2. **데이터 유효성 검증**
   - DTO 계층에서 입력 데이터 검증.
   - 잘못된 입력은 예외를 발생시켜 적절히 처리.

3. **서비스 분리**
   - 컨트롤러 → 서비스 → 레포지토리 계층으로 비즈니스 로직과 데이터 접근을 분리.

4. **결제 처리**
   - 결제 관련 로직은 별도의 `PaymentService`에서 관리.

---

**위 구조는 GitHub에서 제공된 코드 흐름에 맞춘 분석 결과입니다. 필요 시 각 로직의 상세 구현 및 개선점을 추가로 논의할 수 있습니다.** 😊
### **Spring Security 및 OAuth2.0 클래스별 흐름 및 작동 원리**

PetShop 프로젝트에서 Spring Security와 OAuth2.0 인증이 어떻게 동작하는지 각 클래스의 역할과 동작 흐름을 설명하고, 이를 플로우 다이어그램 형태로 정리합니다.

---

## **1. Spring Security 주요 클래스와 역할**

### **1.1 SecurityConfig**

- **역할**:
  - Spring Security의 전반적인 설정을 담당.
  - HTTP 요청별 보안 규칙, 인증/인가 처리 방식, OAuth2 설정 등을 정의.

- **구성요소**:
  - `HttpSecurity`: 각 요청에 대한 권한 설정.
  - `PasswordEncoder`: 비밀번호 암호화를 위한 `BCryptPasswordEncoder` 설정.
  - `CustomAuthenticationProvider`: 사용자 인증을 커스터마이징.
  - OAuth2 관련 핸들러 설정:
    - `CustomOAuth2UserService`: OAuth2 사용자 정보를 처리.
    - `CustomOAuth2AuthenticationSuccessHandler`: 로그인 성공 후 로직 처리.

---

### **1.2 CustomAuthenticationProvider**

- **역할**:
  - 기본 `AuthenticationProvider`를 확장하여 사용자 인증을 커스터마이징.
  - ID와 비밀번호 기반의 로그인 처리.

- **동작**:
  1. `authenticate(Authentication authentication)` 메서드 호출.
  2. 사용자가 입력한 ID와 비밀번호를 검증.
  3. 성공 시 `UsernamePasswordAuthenticationToken` 반환.

---

### **1.3 CustomLoginSuccessHandler**

- **역할**:
  - 로그인 성공 후 사용자 리다이렉션 로직을 담당.

- **동작**:
  1. 인증 성공 후 호출됨.
  2. 사용자 권한(`ROLE_USER`, `ROLE_ADMIN`)에 따라 다른 페이지로 리다이렉션.

---

### **1.4 CustomLoginFailureHandler**

- **역할**:
  - 로그인 실패 시 에러 메시지와 리다이렉션 처리.

- **동작**:
  1. 인증 실패 시 호출됨.
  2. 예외를 분석하여 적절한 에러 메시지를 설정.
  3. 로그인 페이지로 리다이렉션.

---

## **2. OAuth2 관련 클래스와 역할**

### **2.1 CustomOAuth2UserService**

- **역할**:
  - OAuth2 로그인 시 사용자 정보를 처리.
  - 새 사용자면 DB에 저장하고, 기존 사용자면 정보를 갱신.

- **동작**:
  1. `loadUser(OAuth2UserRequest userRequest)` 호출.
  2. 제공된 `OAuth2User` 정보를 가져옴.
  3. 사용자 정보를 DB에 저장 또는 업데이트.

---

### **2.2 CustomOAuth2AuthenticationSuccessHandler**

- **역할**:
  - OAuth2 인증 성공 후 사용자 처리.

- **동작**:
  1. OAuth2 인증 성공 후 호출됨.
  2. 사용자의 권한에 따라 다른 URL로 리다이렉션.

---

## **3. Security 및 OAuth2 플로우**

### **3.1 Spring Security 인증 플로우**

```plaintext
[유저 요청: 로그인]  
   |
   v
[Spring Security Filter Chain]
   |
   v
[CustomAuthenticationProvider]
   |
   |-- 사용자 ID, 비밀번호 검증
   |-- 성공 시: Authentication 객체 생성
   |-- 실패 시: 인증 예외 발생
   |
   v
[AuthenticationSuccessHandler (성공)]
   |
   |-- 사용자 권한 확인 및 리다이렉션
   |
   v
[Application Controller 처리]

[AuthenticationFailureHandler (실패)]
   |
   v
[에러 메시지 및 리다이렉션]
```

---

### **3.2 OAuth2 인증 플로우**

```plaintext
[유저 요청: OAuth2 로그인]  
   |
   v
[Spring Security Filter Chain]
   |
   v
[OAuth2 로그인 요청 처리]
   |
   v
[CustomOAuth2UserService]
   |
   |-- 사용자 정보 조회 및 매핑
   |-- DB에 사용자 저장 또는 업데이트
   |
   v
[OAuth2AuthenticationSuccessHandler]
   |
   |-- 권한 확인 및 리다이렉션
   |
   v
[Application Controller 처리]
```

---

## **4. 전체 인증 및 인가 흐름**

### **HTTP 요청에 따른 보안 흐름**

```plaintext
[HTTP 요청 (예: /admin, /login)]  
   |
   v
[SecurityFilterChain]
   |
   v
[HttpSecurity 설정 매핑]
   |
   |-- /admin → ROLE_ADMIN 권한 필요
   |-- /user → ROLE_USER 권한 필요
   |
   v
[인가 확인]
   |
   |-- 인증된 사용자만 접근 가능
   |-- 인증 실패 시: AccessDeniedHandler 호출
```

---

## **5. 추가 고려 사항**

1. **권한 관리**:
   - `/admin/**`: `ROLE_ADMIN` 권한만 접근 가능.
   - `/user/**`: `ROLE_USER`, `ROLE_ADMIN` 모두 접근 가능.
   - `/oauth2/**`: OAuth2 인증으로 처리.

2. **예외 처리**:
   - 인증 실패: `CustomLoginFailureHandler`에서 처리.
   - 권한 부족: `AccessDeniedHandler`에서 처리.

3. **비밀번호 보안**:
   - 비밀번호는 `BCryptPasswordEncoder`로 암호화하여 저장.

---


```
[MemberController] 
   |
   v
[DTO 생성] (회원 가입, 로그인 등의 요청을 DTO로 변환)
   |
   v
[SecurityConfig] (Spring Security 설정: 인증, 권한 설정)
   |
   v
[CustomAuthenticationProvider] (인증 제공자, 사용자 인증 처리)
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
   v
[CustomLoginSuccessHandler] (로그인 성공 시 후속 처리)
   |
   v
[CustomLoginFailureHandler] (로그인 실패 시 후속 처리)
   |
   v
[WebConfig] (웹 관련 설정, 인터셉터 등록 및 설정)
```

### 각 클래스의 역할:
1. **MemberController**: 클라이언트의 요청을 받는 컨트롤러로, 회원가입, 로그인 등의 요청을 처리합니다. 요청을 DTO로 변환 후 서비스로 전달합니다.
2. **DTO 생성**: Controller에서 받은 요청을 DTO로 변환하여 서비스로 전달하는 역할을 합니다. DTO는 클라이언트와의 데이터 교환을 위한 객체입니다.
3. **SecurityConfig**: Spring Security 설정을 담당합니다. 인증 및 권한 관련 설정을 포함하며, 애플리케이션의 보안을 설정하는 역할을 합니다.
4. **CustomAuthenticationProvider**: 사용자 인증을 담당하는 클래스입니다. 로그인 시 사용자 정보를 인증하고, 인증이 성공하면 `Authentication` 객체를 반환합니다.
5. **CustomUserDetailsService**: 사용자의 상세 정보를 DB에서 조회하여 `UserDetails` 객체를 반환하는 서비스입니다. `CustomAuthenticationProvider`에서 사용됩니다.
6. **CustomUserDetails**: Spring Security의 `UserDetails` 인터페이스를 구현하는 클래스입니다. 사용자 정보를 담고 있으며, `CustomUserDetailsService`에서 생성됩니다.
7. **LoginUser**: 로그인한 사용자 정보를 담고 있는 객체로, 주로 로그인 성공 시 사용됩니다.
8. **LoginUserArgumentResolver**: `@LoginUser` 어노테이션을 사용하여 컨트롤러 메서드에서 로그인된 사용자 정보를 자동으로 주입해주는 역할을 합니다.
9. **CustomLoginSuccessHandler**: 로그인 성공 후 수행할 동작을 처리하는 클래스입니다. 예를 들어, 로그인 성공 시 리디렉션을 설정하거나 추가적인 후속 작업을 처리합니다.
10. **CustomLoginFailureHandler**: 로그인 실패 후 처리하는 클래스입니다. 실패한 이유를 처리하거나 에러 메시지를 반환하는 등의 역할을 합니다.
11. **WebConfig**: 웹 관련 설정을 담당합니다. 인터셉터나 필터, 기타 웹 컴포넌트를 설정할 수 있습니다.

### 동작 순서 (로그인 과정):
1. **SecurityConfig**가 설정된 후 Spring Security가 초기화됩니다.
2. 사용자가 로그인 요청을 보내면, **CustomAuthenticationProvider**가 인증 처리를 담당합니다.
3. **CustomAuthenticationProvider**는 **CustomUserDetailsService**를 사용해 DB에서 사용자 정보를 조회하고, **CustomUserDetails**를 반환합니다.
4. 로그인 성공 시, **CustomLoginSuccessHandler**가 호출되어 후속 처리(리디렉션 등)가 이루어집니다.
5. 로그인 실패 시, **CustomLoginFailureHandler**가 호출되어 실패 처리가 이루어집니다.
6. **LoginUserArgumentResolver**는 로그인한 사용자 정보를 컨트롤러 메서드에 주입합니다.

이러한 흐름을 통해 Spring Security에서 사용자 인증 및 후속 처리가 이루어집니다.







---


---

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

---

```
### **2.2 로그인 요청 처리 (Security 관련 내용 포함)**

```plaintext
[Spring Security Filter]  // Spring Security 필터 체인
   |
   v
[CustomAuthenticationProvider]  // 사용자 인증을 처리하는 커스텀 인증 프로바이더
   |
   v
[CustomUserDetailsService]  // 사용자 정보를 데이터베이스에서 조회하는 서비스
   |
   v
[CustomUserDetails]  // 사용자 세부 정보를 캡슐화한 클래스
   |
   v
[CustomLoginSuccessHandler]  // 로그인 성공 시 후속 처리 핸들러 (성공 시 리다이렉션)
   |
   v
[CustomLoginFailureHandler]  // 로그인 실패 시 후속 처리 핸들러 (실패 시 에러 메시지)
```


## PetShop 프로젝트 코드 순서도 및 유저 플로우

### 1. 유저 플로우 (User Flow)

#### 1.1 회원가입 및 로그인

**회원가입 (Sign Up)**

1. **유저가 회원가입 페이지에 접근**
   - `/register` URL 호출.
2. **유저 입력 데이터를 폼 제출**
   - `MemberController.register()`가 요청 처리.
3. **데이터 검증 및 저장**
   - `MemberService.saveMember()`에서 비밀번호 암호화 및 DB 저장.
   - `MemberRepository.save()`로 DB에 저장.

**로그인 (Login)**

1. **유저가 로그인 페이지에 접근**
   - `/login` URL 호출.
2. **Spring Security 인증 처리**
   - `CustomAuthenticationProvider`가 사용자 인증.
3. **결과 처리**
   - 성공 시: `CustomLoginSuccessHandler`가 홈 페이지로 리다이렉트.
   - 실패 시: `CustomLoginFailureHandler`가 에러 메시지 표시.

---

#### 1.2 상품 조회 및 장바구니 관리

**상품 조회 (View Products)**

1. **유저가 상품 리스트 페이지에 접근**
   - `/products` URL 호출.
2. **상품 데이터 조회**
   - `ProductController.getProducts()`가 요청 처리.
   - `ProductService.getAllProducts()`를 통해 상품 리스트 가져옴.
   - 결과를 View에 전달하여 출력.

**장바구니 추가 (Add to Cart)**

1. **유저가 상품을 장바구니에 추가**
   - `/cart/add` URL 호출.
2. **장바구니 로직 처리**
   - `CartController.addToCart()`에서 요청 처리.
   - `CartItemService.addCartItem()`이 장바구니에 상품 추가.
   - 중복 상품이면 수량 업데이트, 아니면 새 상품 추가.

---

#### 1.3 주문 생성 및 결제

**주문 생성 (Create Order)**

1. **유저가 장바구니 페이지에서 "주문하기" 클릭**
   - `/checkout` URL 호출.
2. **주문 생성 로직 처리**
   - `OrderController.placeOrder()`가 요청 처리.
   - `OrderService.createOrder()`에서 주문 엔티티 생성 및 저장.
   - `OrderRepository.save()`로 DB에 저장.

**결제 (Payment)**

1. **결제 정보 입력 및 처리**
   - `PaymentService.processPayment()`가 요청 처리.
   - 성공 시 주문 상태를 `COMPLETED`로 업데이트.

---

### 2. 코드 순서도



### **2.2 로그인 요청 처리 (Security 관련 내용 포함)**

```plaintext
[Spring Security Filter]  // Spring Security 필터 체인
   |
   v
[CustomAuthenticationProvider]  // 사용자 인증을 처리하는 커스텀 인증 프로바이더
   |
   v
[CustomUserDetailsService]  // 사용자 정보를 데이터베이스에서 조회하는 서비스
   |
   v
[CustomUserDetails]  // 사용자 세부 정보를 캡슐화한 클래스
   |
   v
[CustomLoginSuccessHandler]  // 로그인 성공 시 후속 처리 핸들러 (성공 시 리다이렉션)
   |
   v
[CustomLoginFailureHandler]  // 로그인 실패 시 후속 처리 핸들러 (실패 시 에러 메시지)
```

이제 각 클래스의 역할이 주석으로 추가되어 더 명확하게 이해할 수 있습니다. 추가로 필요한 부분이 있으면 언제든지 알려주세요! 😊
#### 2.1 회원가입 요청 처리

```plaintext
[MemberController.register()]
   |
   v
[DTO 생성 및 유효성 검사]
   |
   v
[MemberService.saveMember()]
   |
   v
[비밀번호 암호화]
   |
   v
[MemberRepository.save()]
   |
   v
[회원가입 완료 → View 리턴]
```

#### 2.2 로그인 요청 처리

```plaintext
[Spring Security Filter]
   |
   v
[CustomAuthenticationProvider.authenticate()]
   |
   v
[CustomLoginSuccessHandler (성공 시 리다이렉션)]
   |
   v
[CustomLoginFailureHandler (실패 시 에러 메시지)]
```

#### 2.3 상품 조회 및 장바구니 추가

**상품 조회**

```plaintext
[ProductController.getProducts()]
   |
   v
[ProductService.getAllProducts()]
   |
   v
[ProductRepository.findAll()]
   |
   v
[상품 데이터 View에 전달]
```

**장바구니 추가**

```plaintext
[CartController.addToCart()]
   |
   v
[CartItemService.addCartItem()]
   |
   v
[CartItemRepository.save()]
   |
   v
[장바구니 데이터 View에 갱신]
```

---

#### 2.4 주문 생성 및 결제 처리

**주문 생성**

```plaintext
[OrderController.placeOrder()]
   |
   v
[OrderService.createOrder()]
   |
   v
[OrderRepository.save()]
   |
   v
[주문 완료 → View 리턴]
```

**결제 처리**

```plaintext
[PaymentService.processPayment()]
   |
   v
[결제 성공 → Order 상태 업데이트]
   |
   v
[결제 완료 → View 리턴]
```

---

## 3. 추가 고려 사항

1. **보안(Security)**
   - Spring Security를 기반으로 세션 및 OAuth2 인증 처리.
   - 사용자 권한(`ROLE_USER`, `ROLE_ADMIN`, `ROLE_SOCIAL`)에 따른 접근 제한.

2. **데이터 유효성 검증**
   - DTO 계층에서 입력 데이터 검증.
   - 잘못된 입력은 예외를 발생시켜 적절히 처리.

3. **서비스 분리**
   - 컨트롤러 → 서비스 → 레포지토리 계층으로 비즈니스 로직과 데이터 접근을 분리.

4. **결제 처리**
   - 결제 관련 로직은 별도의 `PaymentService`에서 관리.

---

이제 README 파일이 훨씬 자연스럽고 읽기 쉽게 되었습니다. 😊 필요한 부분이 있다면 언제든지 알려주세요!

