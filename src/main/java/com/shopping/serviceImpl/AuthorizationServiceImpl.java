package com.shopping.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.AuthenticationTokensEntity;
import com.shopping.entity.UserEntity;
import com.shopping.repo.AuthoRepo;
import com.shopping.service.AuthorizationService;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	@Autowired
	AuthoRepo authoRepo;

	@Override
	public void saveConfirmationToken(AuthenticationTokensEntity auth) {
		authoRepo.save(auth);
	}

	@Override
	public AuthenticationTokensEntity getToken(UserEntity user) {
		return authoRepo.findByUser(user);
	}

	@Override
	public void logout() {
		authoRepo.deleteAll();
	}

}
