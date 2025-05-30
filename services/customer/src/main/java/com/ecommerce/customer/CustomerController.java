package com.ecommerce.customer;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    
    private final CustomerService service;

    @PostMapping
    public ResponseEntity<String> createCustomer(
        @RequestBody @Valid CustomerRequest request
    ) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
        @RequestBody @Valid CustomerRequest request
    ) {
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getCustomer(
        @RequestBody @Valid CustomerRequest request
    ) {
        var customers = service.findAllCustomers(request);
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> customerExists(
        @PathVariable("customer-id") String customerId
    ) {
        boolean exists = service.existsById(customerId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerDTO> getCustomerById(
        @PathVariable("customer-id") String customerId
    ) {
        var customer = service.findCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(
        @PathVariable("customer-id") String customerId
    ) {
        service.deleteCustomer(customerId);
        return ResponseEntity.noContent().build();
    }

}
