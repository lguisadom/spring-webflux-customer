package com.nttdata.lagm.customer.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "customer")
public class Customer implements Serializable {
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private ObjectId id;
	private String lastName;
	private String firstName;
	private String dni;
	private String phone;
	private String email;
	private Integer customerTypeId;
}
