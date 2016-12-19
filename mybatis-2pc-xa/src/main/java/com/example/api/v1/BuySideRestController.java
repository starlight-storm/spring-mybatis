package com.example.api.v1;

import java.net.URI;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.service.BuySideService;

/*
 * 確認用に作ったRestContorllerのため、簡易な作りであることは、ご了承ください。
 */
@RestController
@RequestMapping("/v1/buydata")
public class BuySideRestController {
	@Autowired
	private BuySideService buySideService;
	
	@SuppressWarnings("rawtypes")
	@GetMapping
    public HashMap<String, List> findBuyData() {
		return buySideService.findBuyData();
    }
	
	// {"orderer":{"id":"8","firstName":"A","lastName":"Z", "shippingCode":"999"},"product":{"code":"999","name":"Note"}}
	@PostMapping
	public ResponseEntity<BuyData> createProduct(@RequestBody BuyData buyData, UriComponentsBuilder uriBuilder) {
		buySideService.createBuyData(buyData.getOrderer(), buyData.getProduct());
		
        URI location = uriBuilder
        		.path("v1/buydata/{id}")
                .buildAndExpand(buyData.getOrderer().getId())
                .toUri();
        return ResponseEntity
        		.created(location)
        		.body(buyData);
	}
}
