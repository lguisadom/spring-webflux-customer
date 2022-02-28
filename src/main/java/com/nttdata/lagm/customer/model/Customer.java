package com.nttdata.lagm.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "customer")
public class Customer {
	@Id
    private Long id;
    private String lastName;
    private String firstName;
    private String dni;
    private String phone;
    private String email;
    private Integer customerTypeId;
}
