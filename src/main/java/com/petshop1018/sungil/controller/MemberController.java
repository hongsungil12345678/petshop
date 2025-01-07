package com.petshop1018.sungil.controller;

import com.petshop1018.sungil.config.LoginUser;
import com.petshop1018.sungil.domain.*;
import com.petshop1018.sungil.dto.CheckoutForm;
import com.petshop1018.sungil.dto.MemberDto;
import com.petshop1018.sungil.dto.ProductDto;
import com.petshop1018.sungil.repository.AddressRepository;
import com.petshop1018.sungil.repository.PaymentRepository;
import com.petshop1018.sungil.repository.ProductRepository;
import com.petshop1018.sungil.service.*;
import com.petshop1018.sungil.validator.CustomValidators;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final ProductRepository productRepository;
    private final PaymentRepository paymentRepository;
    private final MemberService memberService;
    private final CustomValidators.EmailValidator emailValidator;
    private final CustomValidators.NicknameValidator nicknameValidator;
    private final CustomValidators.UsernameValidator usernameValidator;
    private final CustomValidators.CheckoutFormValidator checkoutFormValidator;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final AddressRepository addressRepository;
    private final CartService cartService;
    private final AddressService addressService;
    private final OrderService orderService;
    /* 커스텀 유효성 검증을 위해 추가 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        Object target = binder.getTarget();

        if (target instanceof MemberDto.Request) {
            binder.addValidators(emailValidator);
            binder.addValidators(nicknameValidator);
            binder.addValidators(usernameValidator);
        } else if (target instanceof CheckoutForm) {
            binder.addValidators(checkoutFormValidator);
        }
    }

    @GetMapping("/members/signup")
    public String join() {
        return "members/signup";
    }

    @PostMapping("/members/joinProc")
    public String joinProc(@Valid MemberDto.Request dto, Errors errors, Model model) {
        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 값을 유지 */
            model.addAttribute("memberDto", dto);
            /* 유효성 통과 못한 필드와 메시지를 핸들링 */
            Map<String, String> validatorResult = memberService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                model.addAttribute(key, validatorResult.get(key));
            }
            /* 회원가입 페이지로 다시 리턴 */
            return "members/signup";
        }
        // 회원가입 성공시 cart 엔티티 생성해서 넣어줌
        Member member = memberService.registerMember(dto) ;
        cartService.getCartByMember(member);

        return "redirect:/members/login";
    }


    @GetMapping("/members/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        if (exception != null) {
            model.addAttribute("exception", "예외 발생 : "+exception);
        }
        return "members/signup";
    }

// 인터셉터, lazy error -> transactional 안에서 처리 혹은 dto 처리
    @GetMapping("/index")
    public String getIndex(@LoginUser MemberDto.Response dto, Model model,HttpSession session){
        // Food, Clothes Category
        List<Product> foodProducts = productService.getProductsByCategory("Food");
        List<Product> clothesProducts = productService.getProductsByCategory("Clothes & Accessories");
        // Best Selling 추가
        List<Product> bestSellingProducts = productService.getBestSellingProducts();

        model.addAttribute("foodProducts", foodProducts);
        model.addAttribute("clothesProducts", clothesProducts);
        model.addAttribute("bestSellingProducts",bestSellingProducts);
        // 로그인 한 경우 카트
        if(dto!=null){
//            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
//            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
//            model.addAttribute("cart",cart);
//            model.addAttribute("cartItems",cartItems);
            Member member = memberService.findMemberById(dto.getId());
            Cart memberCartSession = cartService.getMemberCartSession(member, session);
            log.info("GET INDEX CART SESSION QUANTITY : {}",memberCartSession.getCartQuantity());
        }

        return"shop/index";
    }
    // 페이징 추가
    @GetMapping("/shop")
    public String getShop(@LoginUser MemberDto.Response dto,
                          @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "9") int size, Model model) {

            // 상품 목록을 페이징하여 가져옵니다.
            Page<Product> productPage = productService.getProducts(page, size);

            // Page<Product>를 Page<ProductDto>로 변환
            Page<ProductDto> productDtoPage = productPage.map(ProductDto::fromEntity);

            // DTO로 변환된 데이터를 Model에 담아서 HTML로 전달
            model.addAttribute("products", productDtoPage);
            model.addAttribute("currentPage", page);  // 현재 페이지
            model.addAttribute("totalPages", productDtoPage.getTotalPages());  // 전체 페이지 수

//            model.addAttribute("memberDto",dto);
//        boolean isLoggedIn = (dto != null);
//        log.info("SHOP CONTROLLER - IS LOGGED IN  : "+isLoggedIn);
//        List<Product> products = productRepository.findAll();
//        model.addAttribute("products",products);
//        model.addAttribute("isLoggedIn", isLoggedIn);
//        if(dto!=null){
//            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
//            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
//            model.addAttribute("cart",cart);
//            model.addAttribute("cartItems",cartItems);
//        }

        return"shop/shop";
    }
// logout -> post 방식이지만 우회
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index";
    }

    @GetMapping("/members/address")
    public String showAddressForm(@LoginUser MemberDto.Response dto, Model model) {
        // 회원의 주소를 가져오기
        Address address = addressRepository.findByMemberId(dto.getId());
        if (address == null) {
            address = new Address(); // 주소 객체가 없으면 새로 생성
        }

        // 회원 정보와 카트 아이템 정보 가져오기
        Member member = memberService.findMemberById(dto.getId());
        List<CartItem> cartItems = cartService.getCartItems(member);

        int total =0;
        for(CartItem cartItem :cartItems){
            total+= cartItem.getProduct().getPrice()*cartItem.getQuantity();
        }
        CheckoutForm checkoutForm = new CheckoutForm();
        checkoutForm.setTotal(total);
        model.addAttribute("cartItems",cartItems);
        model.addAttribute("checkoutForm", checkoutForm);
        if(dto!=null){
            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
            //List<CartItem> cartItems = cartService.getMemberCartItem(cart);
            model.addAttribute("cart",cart);
            //model.addAttribute("cartItems",cartItems);
        }
        return "members/addressForm";  // 주소 입력 폼 렌더링
    }

    @PostMapping("/members/address")
    public String saveAddress(@Valid @ModelAttribute("checkoutForm") CheckoutForm checkoutForm,
                              @Valid @LoginUser MemberDto.Response dto, BindingResult bindingResult, Model model) {
    // 검증 오류가 있으면 다시 폼을 보여준다.
        if (bindingResult.hasErrors()) {
            // 오류가 있는 경우, 폼을 다시 렌더링
            model.addAttribute("checkoutForm", checkoutForm);
            model.addAttribute("cartItems", cartService.getCartItems(memberService.findMemberById(dto.getId())));
            return "members/addressForm";  // 오류가 있을 경우, 다시 폼을 보여줌
        }
        Member member = memberService.findMemberById(dto.getId());
        Address address = addressService.processCheckout(checkoutForm, member);
        List<CartItem> cartItems = cartService.getCartItems(member);
        // 임시 주문 생성
        Order tempOrder = orderService.createTempOrder(address, member, cartItems, checkoutForm.getPaymentMethod(), checkoutForm.getTotal());

        // tempOrder , Address 객체
        model.addAttribute("address",address);
        model.addAttribute("tempOrder",tempOrder);

        if(dto!=null){
            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
            model.addAttribute("cart",cart);
            model.addAttribute("cartItems",cartItems);
        }
        return "shop/checkout"; // 주소 입력 후 결제 페이지로 리디렉션
    }

    @GetMapping("/shop/checkout")
    public String orderCheckout(@LoginUser MemberDto.Response dto, Model model){
        if(dto!=null){
            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
            model.addAttribute("cart",cart);
            model.addAttribute("cartItems",cartItems);
        }
        return "shop/checkout";
    }

    // 필요한것 address, order , orderlist , payment

    @GetMapping("/orders/confirmation")
    public String orderConfirm(@LoginUser MemberDto.Response dto,Model model){
        // 단건
        Order completedOrder = orderService.completedOrderPayment(dto.getId());
        // Payment 가져오기
//        Payment completedPayment = paymentRepository.findByOrder(completedOrder);
        model.addAttribute("completedOrder",completedOrder);
//        model.addAttribute("completedPayment",completedPayment);
        if(dto!=null){
            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
            model.addAttribute("cart",cart);
            model.addAttribute("cartItems",cartItems);
        }
        return "members/order-confirm";
    }

    // 주문 목록 조회
    @GetMapping("/orders/list")
    public String orderList(@LoginUser MemberDto.Response dto,Model model){
        List<Order> completedOrderList = orderService.getCompletedOrdersByMember(dto.getId());

//        List<Payment> completedPaymentList = new ArrayList<>();
//        for (Order order : completedOrderList) {
//            Payment payment = paymentRepository.findByOrder(order); // 단일 Order에 대한 Payment 조회
//            if (payment != null) {
//                completedPaymentList.add(payment); // 유효한 Payment만 리스트에 추가
//            }
//        }
        model.addAttribute("completedOrderList",completedOrderList);
//        model.addAttribute("completedPaymentList",completedPaymentList);
        if(dto!=null){
            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
            model.addAttribute("cart",cart);
            model.addAttribute("cartItems",cartItems);
        }
        return "members/order-list";
    }

}



//    @GetMapping("/admin")
//    public String addProductByAdmin(@LoginUser MemberDto.Response dto, Model model){
//        model.addAttribute("memberDto",dto);
//        return"/shop/admin";
//    }
// Admin 상품 등록
//    @GetMapping("/admin/add")
//    public String getAddProductPage(@LoginUser MemberDto.Response dto, Authentication authentication, Model model){
//        if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
//            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
//            String username = oauth2User.getAttribute("username");
//            String email = oauth2User.getAttribute("email");
//            log.info("/ADMIN/ADD CONTROLLER :  EMAIL - "+email+"\t USER NAME - "+username);
//        }
//        model.addAttribute("memberDto",dto);
//        model.addAttribute("product", new AddProductDto());
//        model.addAttribute("categories", categoryService.getCategories());
//        return "/shop/add";
//    }