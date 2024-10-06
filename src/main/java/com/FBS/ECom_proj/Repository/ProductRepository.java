package com.FBS.ECom_proj.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.FBS.ECom_proj.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	
	@Query("SELECT p FROM Product p WHERE "+
			"LOWER(p.brand) LIKE LOWER(CONCAT( '%', :keyword, '%' )) OR "+
			"LOWER(p.category) LIKE LOWER(CONCAT( '%', :keyword, '%' ))OR "+
			"LOWER(p.name) LIKE LOWER(CONCAT( '%', :keyword, '%' )) OR "+
			"LOWER(p.description) LIKE LOWER(CONCAT( '%', :keyword, '%' )) ")
	public List<Product> findProductbykeyword(String keyword);

}
