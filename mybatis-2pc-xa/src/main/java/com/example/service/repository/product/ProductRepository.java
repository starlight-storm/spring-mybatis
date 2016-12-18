package com.example.service.repository.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.service.domain.Product;

@Mapper
public interface ProductRepository {
	List<Product> findProducts();
	void createProduct(Product product);
}
