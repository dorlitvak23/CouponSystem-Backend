package app.core.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.exceptions.CouponsSystemException;
import app.core.repositories.CompanyRepo;
import app.core.repositories.CouponRepo;
import app.core.repositories.CustomerRepo;
import app.core.security.LoginResponse;
import app.core.security.TokenManager;

@Service
@Transactional
public abstract class ClientService {

	@Autowired
	protected CompanyRepo companyRepo;

	@Autowired
	protected CouponRepo couponRepo;

	@Autowired
	protected CustomerRepo customerRepo;

	@Autowired
	protected TokenManager tokenManager;

	public abstract LoginResponse login(String email, String password) throws CouponsSystemException;

}
