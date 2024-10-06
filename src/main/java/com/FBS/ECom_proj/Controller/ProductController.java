package com.FBS.ECom_proj.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.FBS.ECom_proj.Model.Product;
import com.FBS.ECom_proj.Service.ProductService;



@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductService prodservice;
	

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getproducts(){
		List<Product> products=prodservice.getAllProducts();
		return new ResponseEntity<>(products,HttpStatus.OK);
		 
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getproduct(@PathVariable Long id){
		Product product=prodservice.getproduct(id);
		if(product!= null) {
			return new ResponseEntity<>(product,HttpStatus.OK);

		}else 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> saveproduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
		try {
			Product product1 =prodservice.addProduct(product , imageFile);
			return new ResponseEntity<>("product created",HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@GetMapping("/product/{id}/image")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id){
		Product product= prodservice.getproduct(id);
		return ResponseEntity.ok().contentType(org.springframework.http.MediaType.valueOf(product.getImageType())).body(product.getImageData());
		
	}
	@PutMapping("/product/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestPart Product product, @RequestPart MultipartFile imageFile){
		try {
			Product product1= prodservice.updateProduct(id, product, imageFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);	
			}
		return new ResponseEntity<String>("Product UPDATED", HttpStatus.OK);
 		
		
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id){
		
		if(prodservice.getproduct(id)!=null) {
			prodservice.deleteProduct(id);
			return new ResponseEntity<String>("DELETED", HttpStatus.OK);

		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
		
		return new ResponseEntity<List<Product>>(prodservice.searchProduct(keyword), HttpStatus.OK);
	}
	

}
