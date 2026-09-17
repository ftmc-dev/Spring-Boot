package com.example.customer_dto.service;

import com.example.customer_dto.model.Customer;
import com.example.customer_dto.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
    }

    public Customer saveCustomer(Customer customer){
        if(customerRepository.existsByEmail(customer.getEmail())){
           throw new RuntimeException("Customer already exists");
    }
        return customerRepository.save(customer);
}

    public Customer updateById(Long id, Customer customer) {
        if(customerRepository.findById(id).isPresent()){
            Customer newCustomer = customerRepository.findById(id).get();
            newCustomer.setFirstname(customer.getFirstname());
            newCustomer.setLastname(customer.getLastname());
            newCustomer.setEmail(customer.getEmail());
            newCustomer.setPhone(customer.getPhone());

            return customerRepository.save(newCustomer);
        }
        throw new RuntimeException("Customer not found");
    }

    public void deleteById(Long id) {
        if(customerRepository.findById(id).isEmpty()){
            throw new RuntimeException("Customer not found");
        }
        customerRepository.deleteById(id);
    }

    public Customer updateByName(Long id, Customer customer) {
        if(customerRepository.findById(id).isPresent()){

            Customer newCustomer = customerRepository.findById(id).get();
            newCustomer.setFirstname(customer.getFirstname());
            newCustomer.setLastname(customer.getLastname());

            return customerRepository.save(newCustomer);
        }
        throw new RuntimeException("Customer not found");
    }
}
