package com.shopping.service;

import com.shopping.entity.AuthenticationTokensEntity;
import com.shopping.entity.UserEntity;

public interface AuthorizationService {

	void saveConfirmationToken(AuthenticationTokensEntity auth);

	public AuthenticationTokensEntity getToken(UserEntity user);

	void logout();
}
