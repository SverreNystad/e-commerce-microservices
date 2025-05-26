package com.ecommerce.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.exception.ProductPurchaseException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;


    public Integer createProduct(ProductRequest request) {
        Product product = mapper.toEntity(request);
        product = repository.save(product);
        return product.getId();
    }

    public List<ProductPurchaseDTO> purchaseProduct(List<ProductPurchaseRequest> requests) {
        var productIds = requests
            .stream()
            .map(ProductPurchaseRequest::productId)
            .collect(Collectors.toList());
        var storedProducts = repository.findAllByIdInOrderById(productIds);
        if (storedProducts.size() != productIds.size()) {
            throw new ProductPurchaseException("Some products not found for purchase");
        }
        var storesRequest = requests
            .stream()
            .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
            .toList();

        var purchasedProducts = new ArrayList<ProductPurchaseDTO>();
        
        for (int i = 0; i < storedProducts.size(); i++) {
            var storedProduct = storedProducts.get(i);
            var productRequest = storesRequest.get(i);
            if (storedProduct.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock for product ID: " + storedProduct.getId());
            }
            var newAvailableQuantity = storedProduct.getAvailableQuantity() - productRequest.quantity();
            storedProduct.setAvailableQuantity(newAvailableQuantity);
            repository.save(storedProduct);
            purchasedProducts.add(mapper.toProductPurchaseDTO(storedProduct, productRequest.quantity()));
        }
        return purchasedProducts;
    }

    public ProductDTO findById(Integer productId) {
        return repository.findById(productId)
            .map(mapper::toDTO)
            .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<ProductDTO> findAll() {
        return repository.findAll()
            .stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }

}
