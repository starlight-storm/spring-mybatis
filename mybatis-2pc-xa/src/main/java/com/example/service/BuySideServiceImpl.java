package com.example.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.service.domain.Orderer;
import com.example.service.domain.Product;
import com.example.service.repository.orderer.OrdererRepository;
import com.example.service.repository.product.ProductRepository;

@Service
@Transactional
public class BuySideServiceImpl implements BuySideService {
	@Autowired
	private OrdererRepository ordererRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@SuppressWarnings("rawtypes")
	public HashMap<String,List> findBuyData() {
		List<Orderer> oderers = ordererRepository.findOrderers();
		//oderers.forEach(System.out::println);
		
		List<Product> products = productRepository.findProducts();
		//products.forEach(System.out::println);
		
		return new HashMap<String, List>() {
			private static final long serialVersionUID = 1L;
			{put("orderer", oderers); put("product", products);}
		};
	}

	@SuppressWarnings("rawtypes")
	public HashMap<String,List> createBuyData(Orderer orderer, Product product) {
		ordererRepository.createOrderer(orderer);
		productRepository.createProduct(product);
		return findBuyData();
	}
}