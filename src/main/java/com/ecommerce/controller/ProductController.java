package com.ecommerce.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.ecommerce.model.Product;
import com.ecommerce.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private IProductService productService;
	
	@PostMapping("/add")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product){
	Product product1=	this.productService.addProduct(product);
	return new ResponseEntity<Product>(product1,HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
    public ResponseEntity<List<Product>> getAllList()
    {
		List<Product> getAll=this.productService.getAllProduct();
		return new ResponseEntity<List<Product>>(getAll,HttpStatus.OK);
    }
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id)
	{
		this.productService.deleteProduct(id);
		return new ResponseEntity<String>("product delete success Fully",HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product)
	{
	  Product pro=	this.productService.updateProduct(product);
	  return new ResponseEntity<Product>(pro,HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Product> getProductByid(@PathVariable Long id)
	{
	Product product=this.productService.getProductById(id);
	return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
}
