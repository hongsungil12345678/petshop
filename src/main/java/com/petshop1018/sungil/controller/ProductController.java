package com.petshop1018.sungil.controller;

import com.petshop1018.sungil.config.LoginUser;
import com.petshop1018.sungil.domain.*;
import com.petshop1018.sungil.dto.*;
import com.petshop1018.sungil.repository.ProductRepository;
import com.petshop1018.sungil.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final FileService fileService;
    private final CategoryService categoryService;
    private final CartService cartService;
    private final MemberService memberService;
    private final WishListService wishListService;
    private final ReviewService reviewService;

    @GetMapping("/admin/addFile")
    public String getAddFile(@LoginUser MemberDto.Response dto, Authentication authentication, Model model) throws Exception {
        if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = oauth2User.getAttribute("username");
            String email = oauth2User.getAttribute("email");
            log.info("/ADMIN/ADD CONTROLLER :  EMAIL - "+email+"\t USER NAME - "+username);
        }
        if(dto!=null){
            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
            model.addAttribute("cart",cart);
            model.addAttribute("cartItems",cartItems);
        }
        model.addAttribute("memberDto",dto);
        model.addAttribute("product", new AddProductDto());
        // 최상위 카테고리 가져오기
        List<CategoryDto> categories = categoryService.getAllCategories();
        Map<Long, List<CategoryDto>> subCategoriesMap = categoryService.getSubCategoriesMap(categories);

        model.addAttribute("categories", categories);
        model.addAttribute("subCategoriesMap", subCategoriesMap);

        return "shop/add";
    }

    @PostMapping("/admin/file")
    public String addFile(@LoginUser MemberDto.Response dto, AddProductDto addProductDto, @RequestParam("imgFile") MultipartFile imgFile, Model model) throws Exception {
        Set<Long> resultCategoryIds = addProductDto.getCategoryIds();
        productService.createProduct(addProductDto, resultCategoryIds, imgFile);
//        model.addAttribute("memberDto", dto);
//        log.info("ParentCategory Id : "+addProductDto.getParentCategoryId());
        return "redirect:/index";
    }

    // 상품 상세 조회 메서드
    @GetMapping("/index/single-product/{productId}")
    public String getProductDetail(@LoginUser MemberDto.Response dto, @PathVariable Long productId,Model model) {
        Product product = productService.getProductById(productId);
        // 상품 카테고리 조회
        Set<Category> productCategories = productService.getProductCategories(productId);

        // 로그인 여부에 따라 Cart, Wishlist 버튼
        // 로그인된 사용자가 있으면 isLoggedIn을 true로 설정 -> 타임리프 sec 사용
        //boolean isLoggedIn = (dto != null);
        //log.info("PRODUCT CONTROLLER - IS LOGGED IN  : "+isLoggedIn);
        //model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("product", product);

//        model.addAttribute("memberDto",dto);
        model.addAttribute("productCategories", productCategories);
        if(dto!=null){
            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
            model.addAttribute("cart",cart);
            model.addAttribute("cartItems",cartItems);
        }

        return "shop/single-product";
    }
// tags로 변경
    @GetMapping("/shop/category/{categoryName}")
    public String getProductByCategory(@PathVariable String categoryName,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "9") int size, Model model){
        //boolean isLoggedIn = (dto != null);
//        log.info("CATEGORY TAG : {}",categoryName);
        Page<Product> productsByCategory = productService.getProductsByCategory(categoryName, page, size);
//        for(Product product:productsByCategory){
//            log.info("CategoryTag RESULT : {}",product.getTitle());
//        }
        //model.addAttribute("isLoggedIn",isLoggedIn);
        Page<ProductDto> productDtoPage = productsByCategory.map(ProductDto::fromEntity);
        model.addAttribute("products",productDtoPage);// html 페이지 재사용
        model.addAttribute("categoryTag",categoryName);
        model.addAttribute("currentPage", page);  // 현재 페이지
        model.addAttribute("totalPages", productDtoPage.getTotalPages());  // 전체 페이지 수
//        if(dto!=null){
//            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
//            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
//            model.addAttribute("cart",cart);
//            model.addAttribute("cartItems",cartItems);
//        }

        return "shop/shop-category";

    }

    @GetMapping("/shop/tag/{categoryTag}")
    public String getProductByTag(@PathVariable String categoryTag,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "9") int size, Model model){
        //boolean isLoggedIn = (dto != null);
//        log.info("CATEGORY TAG : {}",categoryTag);
//        List<Product> productsByCategory = productService.getProductsByCategoryTags(categoryTag);
//        for(Product product:productsByCategory){
//            log.info("CategoryTag RESULT : {}",product.getTitle());
//        }

        Page<Product> products = productService.getProductsByCategoryTags(categoryTag, page, size);
        //model.addAttribute("isLoggedIn",isLoggedIn);
        Page<ProductDto> productDtoPage = products.map(ProductDto::fromEntity);
        model.addAttribute("products",productDtoPage);// html 페이지 재사용
        model.addAttribute("categoryTag",categoryTag);
        model.addAttribute("currentPage", page);  // 현재 페이지
        model.addAttribute("totalPages", productDtoPage.getTotalPages());  // 전체 페이지 수
//        if(dto!=null){
//            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
//            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
//            model.addAttribute("cart",cart);
//            model.addAttribute("cartItems",cartItems);
//        }

        return "shop/shop-category";

    }

    @PostMapping("/cart/add/{productId}")
    public String addCart(@LoginUser MemberDto.Response dto, @PathVariable Long productId,
                          @RequestParam("quantity") int quantity,
                          @RequestParam("color") String color,
                          @RequestParam("size") String size, Model model,HttpSession session) {

        Member member = memberService.findMemberById(dto.getId());
        Product product = productService.findProduct(productId);
        //  Cart 추가
        Cart cart = cartService.addItemToCart(member, productId, quantity,color,size);
        List<CartItem> cartItems = cartService.getMemberCartItem(cart);

//        model.addAttribute("memberDto",dto);
        model.addAttribute("cart",cart);
        model.addAttribute("cartItems",cartItems);
        session.setAttribute("cart",cart);
        session.setAttribute("cartItems",cartItems);
        return "shop/cart";
    }

    @PostMapping("/wishlist/add/{productId}")
    public String addWish(@LoginUser MemberDto.Response dto, @PathVariable Long productId, Model model) {
//        log.info("WISHLIST BUTTON CLICK:{}",dto.getEmail());
        Member member = memberService.findMemberById(dto.getId());
        Product product = productService.findProduct(productId);
        // wishlist 추가
        Wishlist wishlist = wishListService.addItemToWishlist(member,productId);
        List<WishlistItem> wishlistItemList = wishListService.getMemberWishlistItem(wishlist);
        model.addAttribute("wishlist",wishlist);
        model.addAttribute("wishlistItemList",wishlistItemList);
//        if(dto!=null){
//            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
//            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
//            model.addAttribute("cart",cart);
//            model.addAttribute("cartItems",cartItems);
//        }

        return "shop/wish-list";
    }


    @GetMapping("/order/checkout")
    public String getOrderCheckout(Model model, HttpSession session){

        @SuppressWarnings("unchecked")
        List<OrderItemDto> orderData = (List<OrderItemDto>) session.getAttribute("orderData");
        if (orderData == null) {
            return "redirect:/"; // 주문 데이터가 없으면 홈으로 리디렉션
        }
        model.addAttribute("orderData", orderData);
        // 세션에서 주문 데이터 제거
        session.removeAttribute("orderData");
        return "shop/checkout";
    }

    @GetMapping("/members/cart")
    public String getMemberCartList(@LoginUser MemberDto.Response dto,Model model){
        log.info("CONTROLLER - /MEMBERS/CART : {}",dto.getId());
        Member member = memberService.findMemberById(dto.getId());
        Cart cart = cartService.getCartByMember(member);
        List<CartItem> cartItems = cartService.getMemberCartItem(cartService.getCartByMember(member));
        model.addAttribute("memberDto",dto);
        model.addAttribute("cart",cart);
        model.addAttribute("cartItems",cartItems);

        return "shop/cart";
    }

    @GetMapping("/members/wishlist")
    public String getMemberWishList(@LoginUser MemberDto.Response dto,Model model){
        log.info("CONTROLLER - /MEMBERS/WISHLIST : {}",dto.getId());
        Member member = memberService.findMemberById(dto.getId());
        List<WishlistItem> wishlistItemList = wishListService.getWishlistItems(member);
        model.addAttribute("wishlistItemList",wishlistItemList);
//        if(dto!=null){
//            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
//            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
//            model.addAttribute("cart",cart);
//            model.addAttribute("cartItems",cartItems);
//        }
        return "shop/wish-list";
    }

    // 가격 필터링
    @GetMapping("/shop/filter")
    public String filterProductsByPrice(@LoginUser MemberDto.Response dto,
            @RequestParam("minPrice") double minPrice,
            @RequestParam("maxPrice") double maxPrice,
            Model model) {
//        boolean isLoggedIn = (dto != null);
//        log.info("SHOP CONTROLLER - IS LOGGED IN  : "+isLoggedIn);
//        model.addAttribute("isLoggedIn",isLoggedIn);
        List<Product> filteredProducts = productService.filterProductsByPrice(minPrice, maxPrice);
//        for(Product p:filteredProducts){
//            log.info("PRODUCT PRICE FILTERING : {}",p);
//            log.info("PRODUCT PRICE : {}",p.getPrice());
//        }
        model.addAttribute("categoryTag","shop");
        model.addAttribute("products", filteredProducts);
//        if(dto!=null){
//            Cart cart = cartService.getCartByMember(memberService.findMemberById(dto.getId()));
//            List<CartItem> cartItems = cartService.getMemberCartItem(cart);
//            model.addAttribute("cart",cart);
//            model.addAttribute("cartItems",cartItems);
//        }

        return "shop/shop-category";
    }
    @PostMapping("/shop/addReview")
    public String addReview(@LoginUser MemberDto.Response dto,@ModelAttribute ReviewDto reviewDto, RedirectAttributes redirectAttributes) {
        Review savedReview = reviewService.addReview(reviewDto.getProductId(), dto.getId(), reviewDto.getRating(), reviewDto.getContent());
        redirectAttributes.addFlashAttribute("review", savedReview);
        redirectAttributes.addFlashAttribute("message", "Review added successfully!");
        return "redirect:/index/single-product/" + reviewDto.getProductId(); // 리뷰를 제출한 후 상품 상세 페이지로 리다이렉트
    }

    // 첫 페이지 요청 처리
    @GetMapping("/shop/paging")
    public String showProductList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "9") int size,
            Model model) {

        // 상품 목록을 페이징하여 가져옵니다.
        Page<Product> productPage = productService.getProducts(page, size);
        log.info("SHOP PAGING CONTROLLER SIZE : {} PAGE : {}",size,page);
        // Page<Product>를 Page<ProductDto>로 변환
        Page<ProductDto> productDtoPage = productPage.map(ProductDto::fromEntity);

        // DTO로 변환된 데이터를 Model에 담아서 HTML로 전달
        model.addAttribute("products", productDtoPage);
        model.addAttribute("currentPage", page);  // 현재 페이지
        model.addAttribute("totalPages", productDtoPage.getTotalPages());  // 전체 페이지 수

        return "shop/shop";
    }
}

