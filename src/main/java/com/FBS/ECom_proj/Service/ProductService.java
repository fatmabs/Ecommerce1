package com.FBS.ECom_proj.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.FBS.ECom_proj.Model.Product;
import com.FBS.ECom_proj.Repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository prodRepo;

	
    public List<Product> getAllProducts() {
        List<Product> products = prodRepo.findAll();
        System.out.println("Products: " + products);  // Add logging here
        return products;
    }
    
    public Product getproduct(Long id) {
        	return prodRepo.findById(id).orElse(null) ;
    }

	public Product addProduct(Product prod, MultipartFile imageFile) throws IOException {
		prod.setImageName(imageFile.getOriginalFilename());
		prod.setImageType(imageFile.getContentType());
		prod.setImageData(imageFile.getBytes());
		return prodRepo.save(prod);
		
	}

	public Product updateProduct(Long id, Product product, MultipartFile imageFile) throws IOException {
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());
		return prodRepo.save(product);
		
	}

	public void deleteProduct(Long id) {
		// TODO Auto-generated method stub
		prodRepo.deleteById(id);
		
	}

	public List<Product> searchProduct(String keyword) {
		return prodRepo.findProductbykeyword(keyword);
		
	}

}
