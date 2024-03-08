package com.emirhanarici.customerservice.mapper;

import com.emirhanarici.customerservice.dto.CreateCustomerRequest;
import com.emirhanarici.customerservice.dto.CustomerDto;
import com.emirhanarici.customerservice.dto.UpdateCustomerRequest;
import com.emirhanarici.customerservice.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerMapper {


    public static Customer toCustomer(CustomerDto customerDto) {
        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .email(customerDto.getEmail())
                .address(customerDto.getAddress())
                .build();
    }

    public static Customer toCustomer(CreateCustomerRequest createCustomerRequest) {
        return Customer.builder()
                .name(createCustomerRequest.getName())
                .email(createCustomerRequest.getEmail())
                .address(createCustomerRequest.getAddress())
                .build();
    }

    public static CustomerDto toCustomerDto(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }

    public static void mapForUpdating(Customer customerToBeUpdate, UpdateCustomerRequest updateCustomerRequest) {

        customerToBeUpdate.setName(updateCustomerRequest.getName());
        customerToBeUpdate.setAddress(updateCustomerRequest.getAddress());

    }
}
