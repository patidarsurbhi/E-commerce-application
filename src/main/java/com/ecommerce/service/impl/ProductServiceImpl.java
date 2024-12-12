package com.ecommerce.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.constants.AppConstant;
import com.ecommerce.exception.NotblankException;
import com.ecommerce.exception.ResourceAlreadyExistException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{

	@Autowired
	private ProductRepository productRepository;
	@Override
	public Product addProduct(Product product) {
		// TODO Auto-generated method stub
			 if (productRepository.existsByName(product.getName())) {
			        throw new ResourceAlreadyExistException(AppConstant.PRODUCT_ALREADY_EXISTS);
			    }
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		List<Product> getAll=this.productRepository.findAll();
		return getAll;
	}

	@Override
	public String deleteProduct(Long id) {
		// TODO Auto-generated method stub
		this.productRepository.deleteById(id);
		return AppConstant.PRODUCT_DELETED_SUCCESS_FULLY;
	}

	@Override
	public Product updateProduct(Product product) {
		 
        Optional<Product> existingProductOpt = productRepository.findById(product.getId());
        if (existingProductOpt.isPresent()) {
            
            Product existingProduct = existingProductOpt.get();
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setStock(product.getStock());
            // Save the updated product
            return productRepository.save(existingProduct);
        } else {
            throw new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND_WITHTHIS_ID + product.getId());
        }
		
	}

	@Override
	public Product getProductById(Long id) {
		 return productRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND_WITHTHIS_ID+ id));
	}

}
