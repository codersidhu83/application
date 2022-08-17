package com.shopping.service;

import com.shopping.dtoResponse.ResponseDto;
import com.shopping.request.UserSignInReq;
import com.shopping.request.UserSignupReq;

public interface UserService {

	public ResponseDto signUp(UserSignupReq user);

	public ResponseDto signIn(UserSignInReq user);

	public ResponseDto logout();
}
