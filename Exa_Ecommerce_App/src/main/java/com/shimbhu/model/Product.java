package com.shimbhu.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productId;
	
	@Min(value = 1,message= " Product price should be more then 0")
	private Integer productPrice;
	@NotBlank(message = "Enter product Name")
	private String productName;
	@NotBlank(message = "Enter product Brand name")
	private String brand;
	@Min(value = 1,message= " Product price should be more then 0")
	private Integer productStock;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;
}
