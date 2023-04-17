package app.core.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.exceptions.CouponsSystemException;
import app.core.security.LoginResponse;
import app.core.service.AdminService;
import app.core.service.CompanyService;
import app.core.service.CustomerService;

//@Component
@Service
public class LoginManager {

	@Autowired
	private AdminService adminService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private CustomerService customerService;

	public LoginResponse login(String email, String password, ClientType clientType) throws CouponsSystemException {
		LoginResponse loginResponse = null;
		switch (clientType) {
		case ADMIN:
			loginResponse = adminService.login(email, password);
			break;
		case COMPANY:
			loginResponse = companyService.login(email, password);
			break;
		case CUSTOMER:
			loginResponse = customerService.login(email, password);
			break;
		default:
			throw new CouponsSystemException("Invaild client type");
		}
		return loginResponse;

	}

}
