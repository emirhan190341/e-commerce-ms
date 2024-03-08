package com.emirhanarici.customerservice.dto;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private Long id;
    private String name;
    private String email;
    private String address;

}
