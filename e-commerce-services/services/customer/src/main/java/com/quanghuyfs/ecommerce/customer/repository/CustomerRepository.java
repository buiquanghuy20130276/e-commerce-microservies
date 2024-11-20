package com.quanghuyfs.ecommerce.customer.repository;

import com.quanghuyfs.ecommerce.customer.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer,String> {
}
