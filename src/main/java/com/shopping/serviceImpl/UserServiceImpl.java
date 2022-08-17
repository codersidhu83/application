package com.shopping.serviceImpl;

import java.security.MessageDigest;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Pattern;
import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shopping.dtoResponse.ResponseDto;
import com.shopping.entity.AuthenticationTokensEntity;
import com.shopping.entity.UserEntity;
import com.shopping.exception.AuthenticationFailException;
import com.shopping.exception.CustomException;
import com.shopping.repo.UserRepo;
import com.shopping.request.UserSignInReq;
import com.shopping.request.UserSignupReq;
import com.shopping.service.AuthorizationService;
import com.shopping.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo userRepo;

	@Autowired
	AuthorizationService authorizationService;

	@Override
	@Transactional
	public ResponseDto signUp(UserSignupReq user) {
		// check if the user is already present
		if (Objects.nonNull(userRepo.findByEmail(user.getEmail()))) {
			throw new CustomException("User Already Present");
		}

		// encrypt
		String encPass = user.getConfirmPassword();
		if (user.getPassword() == user.getConfirmPassword() || user.getPassword().equals(user.getConfirmPassword())) {
			try {
				encPass = hashPassword(user.getConfirmPassword());
			} catch (NoSuchAlgorithmException ex) {
				ex.printStackTrace();
			}
		} else {
			return new ResponseDto("Error", "Enter Same Password");
		}

		// valid email
		String email1 = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pattern = Pattern.compile(email1);

		if (!pattern.matcher(user.getEmail()).matches()) {
			return new ResponseDto("Error", "Enter valid Email");
		}

		UserEntity users = new UserEntity();
		users.setEmail(user.getEmail());
		users.setName(user.getFirstName() + user.getLastName());
		users.setPassword(encPass);
		userRepo.save(users);

		return new ResponseDto("Success","Registered");
	}

	public String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	@Override
	public ResponseDto signIn(UserSignInReq user) {
		ResponseDto dto = new ResponseDto();
		// find user by email
		UserEntity users = userRepo.findByEmail(user.getEmail());
		if (Objects.isNull(users)) {
			throw new AuthenticationFailException("User is Not Valid");
		}

		// decrypt
		try {
			if (!users.getPassword().equals(hashPassword(user.getPassword()))) {
				throw new AuthenticationFailException("Wrong Password");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// if password match
		// AuthenticationTokensEntity token=authorizationService.getToken(users);
		// retrieve the token
		// if(Objects.isNull(token)) {
		// throw new CustomException("Token is not present");
		// }

		// token generate
		final AuthenticationTokensEntity auth = new AuthenticationTokensEntity(users);
		authorizationService.saveConfirmationToken(auth);
		dto.setStatus("success");
		dto.setMessage("Hello " + users.getName());
		return dto;
	}

	@Override
	public ResponseDto logout() {
		ResponseDto dto = new ResponseDto();
		authorizationService.logout();
		dto.setStatus("Success");
		dto.setStatus("Logout");
		return dto;
	}

}
