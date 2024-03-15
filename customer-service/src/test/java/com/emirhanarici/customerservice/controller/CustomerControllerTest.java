package com.emirhanarici.customerservice.controller;

import com.emirhanarici.customerservice.base.BaseControllerTest;
import com.emirhanarici.customerservice.dto.CreateCustomerRequest;
import com.emirhanarici.customerservice.dto.CustomerDto;
import com.emirhanarici.customerservice.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class CustomerControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Mocking the CustomerService
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenCreatedCustomerRequest_whenCreateCustomer_ReturnSavedCustomer() throws Exception {

        // Given
        CreateCustomerRequest request = CreateCustomerRequest.builder()
                .name("John Doe")
                .email("johndoe@gmail.com")
                .address("Turkey")
                .build();

        CustomerDto response = CustomerDto.builder()
                .name(request.getName())
                .email(request.getEmail())
                .address(request.getAddress())
                .build();

        // When
        when(customerService.createCustomer(request)).thenReturn(response);

        // Then
        mockMvc.perform(MockMvcRequestBuilders.post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(request.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(request.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value(request.getAddress()));

    }

    @Test
    public void whenGetAllCustomers_ReturnAllCustomer() throws Exception {

        //Given
        CustomerDto response = CustomerDto.builder()
                .id(1L)
                .name("John Doe")
                .email("johndoe@gmail.com")
                .address("Turkey")
                .build();

        CustomerDto response2 = CustomerDto.builder()
                .id(2L)
                .name("Jane Doe")
                .email("johndoe@gmail.com")
                .address("Turkey")
                .build();


        //When
        when(customerService.getAllCustomers()).thenReturn(List.of(response, response2));

        //Then
        mockMvc.perform(MockMvcRequestBuilders.get("/customers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("John Doe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Jane Doe"));

    }
}