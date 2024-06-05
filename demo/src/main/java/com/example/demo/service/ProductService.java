package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.model.ProductModel;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	public Product saveProduct(ProductModel model) {
		
		Product product = new Product();
		
		product.setName(model.getName());
		product.setPrice(model.getPrice());
		product.setQuantity(model.getQuantity());
		
		repository.save(product);
		
		return product;	
	}
	
	public List<Product> getProduct() {
		
		List<Product> product = new ArrayList<Product>();
		
		product = repository.findAll();
		
		return product;
		
	}
	
	public Boolean productExists(int id) {
		
		Optional<Product> product = repository.findById(id);
		
		return product.isPresent();
	}
	
	public Product updateProduct(int id, ProductModel model) {
		
		Optional<Product> product = repository.findById(id);
			
		if(!(model.getName().isEmpty())) {
			product.get().setName(model.getName());
		}
			
		if(!(model.getQuantity() == null)) {
			product.get().setQuantity(model.getQuantity());
		}
			
		if(!(model.getPrice() == null)) {
			product.get().setPrice(model.getPrice());
		}
		
		repository.save(product.get());
		
		return product.get();
		
	}
	
	public Product deleteProduct(int id) {
		
		Optional<Product> product = repository.findById(id);
 		
		repository.deleteById(id);
		
		return product.get();
		
	}

}
