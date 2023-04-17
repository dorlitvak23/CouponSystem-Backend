package app.core.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponsSystemException;
import app.core.login.ClientType;
import app.core.security.Information;
import app.core.security.LoginResponse;

@Service
@Transactional
public class AdminService extends ClientService {

	private final String email = "admin@admin.com";
	private final String password = "admin";

	@Override
	public LoginResponse login(String email, String password) throws CouponsSystemException {
		if ((email.equalsIgnoreCase(this.email) && password.equals(this.password)) == false) {
			throw new CouponsSystemException("Email or password is wrong");
		}
		Information information = new Information(-1, email, LocalDateTime.now().plusDays(1), ClientType.ADMIN);
		UUID tokenToSend = tokenManager.addToken(information);
		return new LoginResponse(tokenToSend, -1, "Admin", email, ClientType.ADMIN);
	}

	public Company addCompany(UUID token, Company company) throws CouponsSystemException {
		tokenManager.getAdminId(token);
		if (companyRepo.existsByNameOrEmail(company.getName(), company.getPassword())) {
			throw new CouponsSystemException("This company's already exist");
		} else {
			companyRepo.save(company);
		}
		return company;
	}

	public Company updateCompany(UUID token, Company company) throws CouponsSystemException {
		tokenManager.getAdminId(token);

		Company updatedCompany = companyRepo.findById(company.getId())
				.orElseThrow(() -> new CouponsSystemException("Company not found"));
		if (!(updatedCompany.getName().equals(company.getName()))) {
			throw new CouponsSystemException("Updated failed, can't change company's name");
		}
		updatedCompany.setEmail(company.getEmail());
		updatedCompany.setPassword(company.getPassword());
		return companyRepo.saveAndFlush(updatedCompany);
	}

	public void deleteCompany(UUID token, int companyID) throws CouponsSystemException {
		tokenManager.getAdminId(token);

		if (!(companyRepo.existsById(companyID))) {
			throw new CouponsSystemException("This company isn't exist");
		}
		companyRepo.deleteById(companyID);
	}

	public List<Company> getAllCompanies(UUID token) throws CouponsSystemException {
		tokenManager.getAdminId(token);
		return companyRepo.findAll();
	}

	public Company getOneCompany(UUID token, int companyID) throws CouponsSystemException {
		tokenManager.getAdminId(token);

		return companyRepo.findById(companyID).orElseThrow(() -> new CouponsSystemException("Company not found"));
	}

	public void addCustomer(UUID token, Customer customer) throws CouponsSystemException {
		tokenManager.getAdminId(token);

		if (customerRepo.existsByEmail(customer.getEmail())) {
			throw new CouponsSystemException("This customer isn't exist");
		} else {
			customerRepo.save(customer);
		}
	}

	public Customer updateCustomer(UUID token, Customer customer) throws CouponsSystemException {
		tokenManager.getAdminId(token);

		customerRepo.saveAndFlush(customer);
		return customer;
	}

	public void deleteCustomer(UUID token, int customerID) throws CouponsSystemException {
		tokenManager.getAdminId(token);

		customerRepo.deleteById(customerID);
	}

	public List<Customer> getAllCustomers(UUID token) throws CouponsSystemException {
		tokenManager.getAdminId(token);

		return customerRepo.findAll();
	}

	public Customer getOneCustomer(UUID token, int customerID) throws CouponsSystemException {
		tokenManager.getAdminId(token);

		return customerRepo.findById(customerID).orElseThrow(() -> new CouponsSystemException("Customer not found"));
	}
}
