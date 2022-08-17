package com.shopping.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.entity.AuthenticationTokensEntity;
import com.shopping.entity.UserEntity;

@Repository
public interface AuthoRepo extends JpaRepository<AuthenticationTokensEntity, Integer> {

	AuthenticationTokensEntity findByUser(UserEntity user);

	void deleteById(Integer userId);
}
