package com.petshop1018.sungil.service;

import com.petshop1018.sungil.domain.OrderItem;
import com.petshop1018.sungil.domain.Product;
import com.petshop1018.sungil.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final ProductService productService;
    private final OrderItemRepository orderItemRepository;

    public OrderItem createOrderItem(Long productId, int quantity) {
        Product product = productService.getProductById(productId);

        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(quantity);
        orderItem.setTotalPrice(product.getPrice() * quantity); // 총 가격 계산

        return orderItemRepository.save(orderItem);
    }
}
