package com.example.demo.controller;

import com.example.demo.entity.request.ProductRequest;
import com.example.demo.entity.request.UnitsUpdateRequest;
import com.example.demo.entity.response.AllProductResponse;
import com.example.demo.entity.response.BulkProductsResponse;
import com.example.demo.entity.response.ProductResponse;
import com.example.demo.exceptionfamily.ProductNotFoundException;
import com.example.demo.repo.Productrepo;
import com.example.demo.service.BrandChannelValidateAndUpdateService;
import com.example.demo.service.SameBrandProductsService;
import com.example.demo.service.UpdateProductUnitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.UUID;
@Profile( {"local", "default"})
@RestController
@Slf4j
public class ProductController {
	@Autowired
	private Productrepo productrepo;
	@Autowired
	private RestTemplate resttemplate;

	@Autowired
	private SameBrandProductsService samebrandproducts;

	@Autowired
	private BrandChannelValidateAndUpdateService brandChannelValidateAndUpdateService;
	@Autowired
	private UpdateProductUnitsService updateProductUnitsService;


	@GetMapping("allproducts")
	public ResponseEntity<AllProductResponse> getAllProducts(){
		AllProductResponse allProductResponse=new AllProductResponse();
		allProductResponse.setProducts(productrepo.findAll());
		log.info(String.valueOf(allProductResponse));
		ResponseEntity<AllProductResponse> t=new ResponseEntity<>(allProductResponse, HttpStatus.OK);
		return t;
	}

	@GetMapping("/products")
	public BulkProductsResponse findProductsByBrand(@RequestParam String brandname) {
		return samebrandproducts.sameBrandProductsMethod(brandname);
	}

	@PutMapping("product/{id}")

	public ProductResponse updateUnitsInProduct(@RequestBody UnitsUpdateRequest unitUpdateValue, @PathVariable String id)
			throws ProductNotFoundException {
		return updateProductUnitsService.UpdateProductUnits(id, unitUpdateValue);
	}

	@PostMapping("product")
	public String postProductDetails(@RequestBody @Valid ProductRequest productrequest) throws Exception {
		return brandChannelValidateAndUpdateService.brandChannelValidateAndUpdateService(productrequest);
	}

	@GetMapping("/product/{id}")
	public ProductResponse displaydata(@PathVariable String id) throws ProductNotFoundException {

log.info("hai jeevan");
		return (productrepo.findById(UUID.fromString(id))
				.orElseThrow(() -> new ProductNotFoundException("product not found really")));

	}
}