package com.ecommerce.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.exception.BusinessException;

import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductClient {
    
    @Value("${application.config.product-url}")
    private String productUrl;
    private final RestTemplate restTemplate;


    public List<PurchaseDTO> purchaseProducts(List<PurchaseRequest> requestBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        headers.set(CONTENT_TYPE, "application/json");
        HttpEntity<List<PurchaseRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
        ParameterizedTypeReference<List<PurchaseDTO>> responseType = 
                new ParameterizedTypeReference<>() {};
        ResponseEntity<List<PurchaseDTO>> responseEntity = 
                restTemplate.exchange(
                    productUrl + "/purchase",
                    POST,
                    requestEntity,
                    responseType
        );
        if (responseEntity.getStatusCode().isError()) {
            throw new BusinessException("Failed to purchase products: " + responseEntity.getStatusCode());
        }
        return responseEntity.getBody();

    }
}
