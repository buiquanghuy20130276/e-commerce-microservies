package com.quanghuyfs.ecommerce.customer.service;

import com.quanghuyfs.ecommerce.customer.mapper.CustomerMapper;
import com.quanghuyfs.ecommerce.customer.request.CustomerRequest;
import com.quanghuyfs.ecommerce.customer.response.CustomerResponse;
import com.quanghuyfs.ecommerce.customer.exception.CustomerNotFoundException;
import com.quanghuyfs.ecommerce.customer.model.Customer;
import com.quanghuyfs.ecommerce.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class

CustomerService {
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id()).orElseThrow(()->new CustomerNotFoundException(String.format("Can not update customer:: No customer found with the provided id :: %s",request.id())));
        mergeCustomer(customer,request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerRequest request) {
    if (StringUtils.isNotBlank(request.firstname())){
        customer.setFirstname(request.firstname());
    }
        if (StringUtils.isNotBlank(request.lastname())){
            customer.setFirstname(request.lastname());
        }
        if (StringUtils.isNotBlank(request.email())){
            customer.setFirstname(request.email());
        }if (request.address()!=null){
            customer.setAddress(request.address());
        }
    }


    public List<CustomerResponse> findAllCustomer() {
        return repository.findAll().stream().map(mapper::fromCustomer)
                .collect(Collectors.toList());


    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()->new CustomerNotFoundException(String.format("No customer found with ID :: %s ",customerId)));

    }

    public void deleteCustomer(String customerId) {
        repository.deleteById(customerId);
    }
}
