package com.emirhanarici.customerservice.controller;

import com.emirhanarici.customerservice.dto.CreateCustomerRequest;
import com.emirhanarici.customerservice.dto.CustomerDto;
import com.emirhanarici.customerservice.dto.UpdateCustomerRequest;
import com.emirhanarici.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;


    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CreateCustomerRequest createCustomerRequest) {
        return ResponseEntity
                .ok(customerService.createCustomer(createCustomerRequest));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity
                .ok(customerService.getAllCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") final Long customerId) {
        return ResponseEntity
                .ok(customerService.getCustomerById(customerId));
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomerById(@PathVariable("customerId") final Long customerId, @RequestBody @Valid UpdateCustomerRequest updateCustomerRequest) {
        return ResponseEntity
                .ok(customerService.updateCustomerById(customerId, updateCustomerRequest));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomerById(customerId);

        return ResponseEntity
                .ok("Customer deleted successfully");
    }


}
