package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.model.ProductModel;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody ProductModel model) {
		return new ResponseEntity<>(service.saveProduct(model), HttpStatus.CREATED);
	}
	
	@GetMapping("/view")
	public ResponseEntity<List<Product>> getProduct() {
		return new ResponseEntity<>(service.getProduct(), HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Product> updadateProduct(@PathVariable("id") int id, @RequestBody ProductModel model) {
		
		if(service.productExists(id)) {
			return new ResponseEntity<>(service.updateProduct(id, model), HttpStatus.OK);
		}
		
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
		if(service.productExists(id)) {
			return new ResponseEntity<>(service.deleteProduct(id), HttpStatus.NO_CONTENT);
		}
		
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	

}
