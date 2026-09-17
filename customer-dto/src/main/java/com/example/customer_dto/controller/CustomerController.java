package com.example.customer_dto.controller;

import com.example.customer_dto.model.Customer;
import com.example.customer_dto.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.saveCustomer(customer);
}

   @PutMapping("/{id}")
   public Customer updateById(@PathVariable Long id, @RequestBody Customer customer){
            return customerService.updateById(id, customer);
        }

   @PatchMapping("/{id}/name")
   public Customer updateByName(@PathVariable Long id, @RequestBody Customer customer) {
            return customerService.updateByName(id, customer);
        }

   @DeleteMapping("/{id}")
   public void deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
    }
}