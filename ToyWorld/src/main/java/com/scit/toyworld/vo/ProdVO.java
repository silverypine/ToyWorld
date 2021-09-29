package com.scit.toyworld.vo;

import lombok.Data;

@Data
public class ProdVO {

	private String prodNum;
	private String prodName;
	private String prodClass;
	private String prodManufacturer;
	private int prodPrice;
	private int prodStock;
	private int prodBoxQuantity;
	private String originalFileName;
	private String savedFileName;
	private String empId;
}
