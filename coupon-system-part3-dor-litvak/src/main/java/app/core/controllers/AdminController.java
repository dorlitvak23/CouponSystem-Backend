package app.core.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponsSystemException;
import app.core.service.AdminService;

@RequestMapping("/api/admin/")
@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("company")
	@ResponseStatus(HttpStatus.CREATED)
	public Company addCompany(@RequestHeader("Authorization") UUID token, @RequestBody Company company)
			throws CouponsSystemException {
		return this.adminService.addCompany(token, company);
	}

	@PutMapping("company")
	@ResponseStatus(HttpStatus.OK)
	public Company updateCompany(@RequestHeader("Authorization") UUID token, @RequestBody Company company)
			throws CouponsSystemException {
		return this.adminService.updateCompany(token, company);
	}

	@DeleteMapping("company/{companyID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyID)
			throws CouponsSystemException {
		this.adminService.deleteCompany(token, companyID);
	}

	@GetMapping("company/all")
	@ResponseStatus(HttpStatus.OK)
	public List<Company> getAllCompanies(@RequestHeader("Authorization") UUID token) throws CouponsSystemException {
		return this.adminService.getAllCompanies(token);
	}

	@GetMapping("company/{companyID}")
	@ResponseStatus(HttpStatus.OK)
	public Company getOneCompany(@RequestHeader("Authorization") UUID token, @PathVariable int companyID)
			throws CouponsSystemException {
		return this.adminService.getOneCompany(token, companyID);
	}

	@PostMapping("customer")
	@ResponseStatus(HttpStatus.CREATED)
	public void addCustomer(@RequestHeader("Authorization") UUID token, @RequestBody Customer customer)
			throws CouponsSystemException {
		this.adminService.addCustomer(token, customer);
	}

	@PutMapping("customer")
	@ResponseStatus(HttpStatus.OK)
	public Customer updateCustomer(@RequestHeader("Authorization") UUID token, @RequestBody Customer customer)
			throws CouponsSystemException {
		return this.adminService.updateCustomer(token, customer);
	}

	@DeleteMapping("customer/{customerID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerID)
			throws CouponsSystemException {
		this.adminService.deleteCustomer(token, customerID);
	}

	@GetMapping("customer/all")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getAllCustomers(@RequestHeader("Authorization") UUID token) throws CouponsSystemException {
		return this.adminService.getAllCustomers(token);
	}

	@GetMapping("customer/{customerID}")
	@ResponseStatus(HttpStatus.OK)
	public Customer getOneCustomer(@RequestHeader("Authorization") UUID token, @PathVariable int customerID)
			throws CouponsSystemException {
		return this.adminService.getOneCustomer(token, customerID);
	}
}
