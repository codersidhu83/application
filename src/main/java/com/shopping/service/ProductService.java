package com.shopping.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.dtoResponse.Product;
import com.shopping.dtoResponse.ResponseDto;
import com.shopping.entity.PincodeEntity;
import com.shopping.request.ProductRequest;


public interface ProductService {

	public ResponseDto getPincode(Integer pincode);

	public ResponseEntity<?> getProduct(String product);

	public ResponseDto addProduct(ProductRequest product);

	public ResponseDto addPinCode(PincodeEntity pincode);

	public List<Product> getProducts();

}
