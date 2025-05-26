package com.ecommerce.product;

import org.springframework.stereotype.Service;

import com.ecommerce.category.Category;

import jakarta.validation.constraints.Positive;

@Service
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(
                    Category.builder()
                        .id(request.categoryId())
                        .build())
                .build();
    }


    public ProductDTO toDTO(Product product) {
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getAvailableQuantity(),
            product.getPrice(),
            product.getCategory().getId(),
            product.getCategory().getName(),
            product.getCategory().getDescription()
        );
    }


    public ProductPurchaseDTO toProductPurchaseDTO(Product storedProduct,
            double quantity) {
        return new ProductPurchaseDTO(
            storedProduct.getId(),
            storedProduct.getName(),
            storedProduct.getDescription(),
            storedProduct.getPrice(),
            quantity
        );
    }

}
