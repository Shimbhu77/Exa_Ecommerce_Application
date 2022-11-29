package com.shimbhu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Customer {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	@Size(min=3,max=20,message="please enter your name with character 3 and maximum character is 8.")
	private String customerName;
	@Email(message = "please enter a valid email")
	@Column(unique = true)
	private String customerEmail;
	@Size(min=3,max=20,message="please enter your username")
	private String UserName;
	@Size(min=3,max=8,message="please enter password minimum character 3 and maximum character is 8.")
	private String Password;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
}
