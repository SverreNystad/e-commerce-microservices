package com.ecommerce.customer;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.exception.CustomerNotFoundException;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                    "Customer not found with ID: " + request.id()
                ));
        mergerCustomer(customer, request);
        repository.save(customer);
    }

    private void mergerCustomer(Customer existing, CustomerRequest request) {
        if (StringUtils.isNotBlank(request.firstName())) {
            existing.setFirstName(request.firstName());
        }
        if (StringUtils.isNotBlank(request.lastName())) {
            existing.setLastName(request.lastName());
        }
        if (StringUtils.isNotBlank(request.email())) {
            existing.setEmail(request.email());
        }
        if (request.address() != null) {
            existing.setAddress(request.address());
        }

    }

	public List<CustomerDTO> findAllCustomers(CustomerRequest request) {
		return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
	}

    public boolean existsById(String customerId) {
        if (StringUtils.isBlank(customerId)) {
            return false;
        }
        return repository.existsById(customerId);
    }

    public CustomerDTO findCustomerById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                    "Customer not found with ID: " + customerId
                ));
    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }

}
