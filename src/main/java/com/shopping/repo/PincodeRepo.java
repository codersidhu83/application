package com.shopping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.shopping.entity.PincodeEntity;

@Repository
public interface PincodeRepo extends JpaRepository<PincodeEntity, Integer> {

//	@Query(value = "insert into PINCODE (pincode) VALUES (?1)", nativeQuery = true)
//	void insertPincode(Integer pincode);

	PincodeEntity findByPincode(Integer pincode);

}
