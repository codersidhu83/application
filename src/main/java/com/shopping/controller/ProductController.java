package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.shopping.dtoResponse.Product;
import com.shopping.dtoResponse.ResponseDto;
import com.shopping.entity.PincodeEntity;
import com.shopping.repo.ProductRepo;
import com.shopping.request.ProductRequest;
import com.shopping.service.ProductService;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepo  proRepo;

	// check
	@GetMapping("/pincode")
	public ResponseDto getPincode(@RequestParam Integer pincode) {
		return productService.getPincode(pincode);
	}

	// getDescription Of Product
	@GetMapping("/search-product")
	public ResponseEntity<?> getProduct(@RequestParam String product) {
		return productService.getProduct(product);
	}

	// add product
	@PostMapping("/add-product")
	public ResponseDto addProduct(@RequestBody ProductRequest product) {
		return productService.addProduct(product);
	}
	
	// price of product
	

	// add pincode
	@PostMapping("/add-pincode")
	public ResponseDto addpincode(@RequestBody PincodeEntity pincode) {
		return productService.addPinCode(pincode);

	}

	// list of product
	@GetMapping("all-products")
	public List<Product> getProducts(){
		return productService.getProducts();
	}
}
