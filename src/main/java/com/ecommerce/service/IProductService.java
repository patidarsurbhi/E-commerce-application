package com.ecommerce.service;

import java.util.List;

import com.ecommerce.model.Product;

public interface IProductService {
	
	public Product addProduct(Product product);
	
	public List<Product> getAllProduct();
	
	public String deleteProduct(Long id);
	
	public Product updateProduct(Product product);
	
    public Product getProductById(Long id); 

}
