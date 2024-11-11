package com.quanghuyfs.ecommerce.customer;

import com.quanghuyfs.ecommerce.exception.CustomerNotFoundException;
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

    public Long createCustomer(CustomerRequest request) {
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

    public Boolean existsById(Long customerId) {
        return repository.findById(customerId).isPresent();
    }

    public CustomerResponse findById(Long customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(()->new CustomerNotFoundException(String.format("No customer found with ID :: %s ",customerId)));

    }

    public void deleteCustomer(Long customerId) {
        repository.deleteById(customerId);
    }
}
