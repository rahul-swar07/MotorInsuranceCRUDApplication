package com.turtlemint.MotorInsuranceCRUDApplication.controller;

import com.turtlemint.MotorInsuranceCRUDApplication.model.Customer;
import com.turtlemint.MotorInsuranceCRUDApplication.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/{requestId}")
    public Optional<Customer> getCustomer(@PathVariable long requestId){
        return customerService.getCustomer(requestId);
    }

    @PostMapping("/customer")
    public String createCustomer(@RequestBody Customer customer){
        customerService.createCustomer(customer);
        return "Created customer with id = " + customer.getCustomerId();
    }

    @GetMapping("/customer")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @PutMapping("/customer/{id}")
    public String updateCustomer(@PathVariable long id, @RequestBody Customer customer){
        customerService.updateCustomer(customer);
        return "Updated customer with id = " + id;
    }

    @DeleteMapping("/customer/{id}")
    public String deleteCustomer(@PathVariable long id){
        customerService.deleteCustomer(id);
        return "Deleted customer with id = " + id;
    }
}
