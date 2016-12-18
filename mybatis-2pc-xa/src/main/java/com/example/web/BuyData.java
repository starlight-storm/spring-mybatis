package com.example.web;

import com.example.service.domain.Orderer;
import com.example.service.domain.Product;

import lombok.Data;

@Data
public class BuyData {
	private Orderer orderer;
	private Product product;
}
