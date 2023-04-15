package app.core.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.core.exceptions.CouponsSystemException;
import app.core.login.LoginManager;
import app.core.security.LoginRequest;
import app.core.security.LoginResponse;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/login")
public class LoginController {

	private final LoginManager loginManager;

	@PostMapping
	public LoginResponse login(@RequestBody LoginRequest loginRequest) throws CouponsSystemException {
		return loginManager.login(loginRequest.getEmail(), loginRequest.getPassword(), loginRequest.getClientType());
	}
}
