package com.shopping.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.shopping.entity.ProductEntity;

@Repository
public interface ProductRepo extends JpaRepository<ProductEntity, Integer> {

	List<ProductEntity> findByName(String name);

	List<ProductEntity> findByBrand(String brand);
	
	List<ProductEntity> findByCode(String code);
	
//	@Query("SELECT u FROM ProductEntity u WHERE u.code = code")
//	List<ProductEntity> findByCodes(@Param("code") String code);

}
