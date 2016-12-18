package com.example.web;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.BuySideService;

/*
 * レスポンスのステータスなど、ちゃんとしたRESTfulではないので、その辺は考慮してください。
 */
@RestController
public class BuySideRestController {
	@Autowired
	private BuySideService buySideService;
	
	@SuppressWarnings("rawtypes")
	@GetMapping("/buy")
    public HashMap<String, List> findBuyData() {
		return buySideService.findBuyData();
    }
	
	// {"orderer":{"id":"8","firstName":"A","lastName":"Z", "shippingCode":"999"},"product":{"code":"999","name":"Note"}}
	@SuppressWarnings("rawtypes")
	@PostMapping("/buy")
	public HashMap<String, List> createProduct(@RequestBody BuyData buyData) {
		return buySideService.createBuyData(buyData.getOrderer(), buyData.getProduct());
	}
}
