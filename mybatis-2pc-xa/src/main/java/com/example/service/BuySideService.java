package com.example.service;

import java.util.HashMap;
import java.util.List;

import com.example.service.domain.Orderer;
import com.example.service.domain.Product;

public interface BuySideService {
	@SuppressWarnings("rawtypes")
	HashMap<String,List> findBuyData();

	void createBuyData(Orderer customer, Product product);
}
