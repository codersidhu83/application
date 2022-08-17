package com.shopping.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.shopping.dtoResponse.ResponseDto;
import com.shopping.entity.AuthenticationTokensEntity;
import com.shopping.entity.PincodeEntity;
import com.shopping.entity.ProductEntity;
import com.shopping.repo.AuthoRepo;
import com.shopping.repo.PincodeRepo;
import com.shopping.repo.ProductRepo;
import com.shopping.request.ProductRequest;
import com.shopping.service.ProductService;
import com.shopping.dtoResponse.Product;
import com.shopping.dtoResponse.ProductResponseDto;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	PincodeRepo pincodeRepo;

	@Autowired
	ProductRepo productRepo;

	@Autowired
	AuthoRepo authRepo;

	@Override
	public ResponseDto getPincode(Integer pincode) {
		List<AuthenticationTokensEntity> authList = authRepo.findAll();
		if (authList.size() > 0) {
			if (pincode == null || pincode.equals(null)) {
				return new ResponseDto("Error", "You have Not Input the Pincode");
			}
			PincodeEntity pin = pincodeRepo.findByPincode(pincode);
			if (pin != null) {
				return new ResponseDto("Product are servicable", "In:" + pin.getDays());
			}
			return new ResponseDto("Products are not servicable", "Try Different Pincode");
		}
		return new ResponseDto("Error", "Please Login");
	}

	@Override
	public ResponseEntity<?> getProduct(String product) {
		if (product.equals(null) || product == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Enter Product Detail");
		}

		List<ProductEntity> nameList = productRepo.findByName(product);

		if (nameList.size() > 0) {
			if (!authRepo.findAll().isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(nameList);
			}
			List<ProductResponseDto> productList = new ArrayList<>();
			for (ProductEntity productEntity : nameList) {
				ProductResponseDto productResponse = new ProductResponseDto();
				productResponse.setName(productEntity.getName());
				productResponse.setCode(productEntity.getCode());
				productResponse.setBrand(productEntity.getBrand());
				productList.add(productResponse);
			}
			return ResponseEntity.status(HttpStatus.OK).body(productList);
		}

		List<ProductEntity> brandList = productRepo.findByBrand(product);
		if (!brandList.isEmpty()) {
			if (!authRepo.findAll().isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(brandList);
			}
			List<ProductResponseDto> productList = new ArrayList<>();
			for (ProductEntity productEntity : brandList) {
				ProductResponseDto productResponse = new ProductResponseDto();
				productResponse.setName(productEntity.getName());
				productResponse.setCode(productEntity.getCode());
				productResponse.setBrand(productEntity.getBrand());
				productList.add(productResponse);
			}
			return ResponseEntity.status(HttpStatus.OK).body(productList);
		}

		List<ProductEntity> codeList = productRepo.findByCode(product);
		if (!codeList.isEmpty()) {
			if (!authRepo.findAll().isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(codeList);
			} else {
				List<ProductResponseDto> productList = new ArrayList<>();
				for (ProductEntity productEntity : codeList) {
					ProductResponseDto productResponse = new ProductResponseDto();
					productResponse.setName(productEntity.getName());
					productResponse.setCode(productEntity.getCode());
					productResponse.setBrand(productEntity.getBrand());
					productList.add(productResponse);
				}
				return ResponseEntity.status(HttpStatus.OK).body(productList);
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No Product Found");
	}

	@Override
	public ResponseDto addProduct(ProductRequest product) {
		List<AuthenticationTokensEntity> authList = authRepo.findAll();
		if (authList.size() > 0) {
			if (product.equals(null) || product == null) {
				return new ResponseDto("Error", "Enter Details");
			}
			List<ProductEntity> name = productRepo.findByName(product.getName());
			List<ProductEntity> code = productRepo.findByCode(product.getCode());
			if (name.size() > 0 && code.size() > 0) {
				return new ResponseDto("Error", "Already Present with same name & code");
			}
			if (code.size() > 0) {
				return new ResponseDto("Error", "Already Present with same code");
			}
			if (name.size() > 0) {
				return new ResponseDto("Error", "Already Present with same name");
			}

			ProductEntity productEntity = new ProductEntity();
			productEntity.setName(product.getName());
			productEntity.setCode(product.getCode());
			productEntity.setBrand(product.getBrand());
			productEntity.setPrice(product.getPrice());
			productEntity.setDescription(product.getDescription());
			productEntity.setUrl(product.getUrl());
			productRepo.save(productEntity);

			return new ResponseDto("Success", productEntity.getProductId());
		}
		return new ResponseDto("Error", "Please Login");
	}

	@Override
	public ResponseDto addPinCode(PincodeEntity pincode) {
		List<AuthenticationTokensEntity> authList = authRepo.findAll();
		if (authList.size() > 0) {
			pincodeRepo.save(pincode);
			return new ResponseDto("Success", "Pincode Added");
		}
		return new ResponseDto("Error", "Please Login");
	}

	@Override
	public List<Product> getProducts() {
		List<ProductEntity> productList = productRepo.findAll();
		List<Product> proList = new ArrayList<>();
		if (authRepo.findAll().isEmpty()) {
			for (ProductEntity product : productList) {
				Product prod = new Product();
				prod.setName(product.getName());
				prod.setBrand(product.getBrand());
				prod.setPrice(product.getPrice());
				proList.add(prod);
			}
			return proList;
		}
		return proList;  //exception
	}

}
