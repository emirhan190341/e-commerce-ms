package com.emirhanarici.customerservice.service;

import com.emirhanarici.customerservice.dto.CreateCustomerRequest;
import com.emirhanarici.customerservice.dto.CustomerDto;
import com.emirhanarici.customerservice.dto.UpdateCustomerRequest;
import com.emirhanarici.customerservice.exception.CustomerNotFoundException;
import com.emirhanarici.customerservice.exception.EmailAlreadyExistsException;
import com.emirhanarici.customerservice.mapper.CustomerMapper;
import com.emirhanarici.customerservice.model.Customer;
import com.emirhanarici.customerservice.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    public CustomerDto createCustomer(CreateCustomerRequest createCustomerRequest) {

        if (customerRepository.existsByEmail(createCustomerRequest.getEmail())) {
            throw new EmailAlreadyExistsException(createCustomerRequest.getEmail());
        }

        Customer customer = customerRepository.save(CustomerMapper.toCustomer(createCustomerRequest));

        return CustomerMapper.toCustomerDto(customer);

    }

    public List<CustomerDto> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::toCustomerDto)
                .toList();

    }


    public CustomerDto getCustomerById(Long customerId) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Account not found: " + customerId));

        return CustomerMapper.toCustomerDto(customer);
    }

    @Transactional
    public CustomerDto updateCustomerById(Long customerId, @Valid UpdateCustomerRequest updateCustomerRequest) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Account not found: " + customerId));

        CustomerMapper.mapForUpdating(customer, updateCustomerRequest);

        return CustomerMapper.toCustomerDto(customerRepository.save(customer));


    }
}
