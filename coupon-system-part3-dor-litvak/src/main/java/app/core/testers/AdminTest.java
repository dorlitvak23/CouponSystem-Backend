//package app.core.testers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import app.core.entities.Company;
//import app.core.entities.Customer;
//import app.core.exceptions.CouponsSystemException;
//import app.core.login.ClientType;
//import app.core.login.LoginManager;
//import app.core.service.AdminService;
//
//@Component
//@Order(1)
//public class AdminTest implements CommandLineRunner {
//
//	@Autowired
//	private LoginManager loginManager;
//
//	private AdminService adminService;
//
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("====================================");
//		System.out.println("Welcome to admin's test");
//		System.out.println("====================================");
//		loginTest();
//		addCompanyTest();
//		updateComapnyTest();
//		deleteCompanyTest();
//		getAllCompaniesTest();
//		getOneCompanyTest();
//		addCustomerTest();
//		updateCustomerTest();
//		deleteCustomerTest();
//		getAllCustomersTest();
//		getOneCustomerTest();
//
//	}
//
//	public void loginTest() {
//		try {
//			System.out.println("====================================");
//			System.out.println("Success administrator login");
//			System.out.println("====================================");
//			adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMIN);
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void addCompanyTest() {
//		Company company1 = new Company(0, "Osem", "osem@mail.com", "12345");
//		Company company2 = new Company(0, "Apple", "apple@mail.com", "qwert");
//		Company company3 = new Company(0, "Samsung", "samsung@mail.com", "asdfg");
//		try {
//			System.out.println("addComapny()");
//			adminService.addCompany(company1);
//			System.out.println("Company added succesfully " + company1);
//			adminService.addCompany(company2);
//			System.out.println("Company added succesfully " + company2);
//			adminService.addCompany(company3);
//			System.out.println("Company added succesfully " + company3);
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void updateComapnyTest() {
//		try {
//			System.out.println("updateComapny()");
//			Company c = adminService.getOneCompany(2);
//			System.out.println(c);
//			c.setEmail("apple@mail");
//			c.setPassword("lkjhg");
//			adminService.updateCompany(c);
//			System.out.println("Company updated succesfully " + c);
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void deleteCompanyTest() {
//		try {
//			System.out.println("DeleteComapny()");
//			System.out.println("Company deleted succesfully " + adminService.getOneCompany(1));
//			adminService.deleteCompany(1);
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getAllCompaniesTest() {
//		try {
//			System.out.println("getAllCompanies()");
//			System.out.println(adminService.getAllCompanies());
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getOneCompanyTest() {
//		try {
//			System.out.println("getOneCompany()");
//			System.out.println(adminService.getOneCompany(3));
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void addCustomerTest() {
//		Customer customer1 = new Customer(0, "Dor", "Litvak", "dor@mail.com", "hghghg");
//		Customer customer2 = new Customer(0, "israel", "israeli", "israel@mail.com", "fdfdfd");
//		Customer customer3 = new Customer(0, "avi", "cohen", "avi@mail.com", "873434");
//		Customer customer4 = new Customer(0, "yossi", "levy", "yossi@mail.com", "873434");
//		try {
//			adminService.addCustomer(customer1);
//			System.out.println("Customer added succesfully " + customer1);
//			adminService.addCustomer(customer2);
//			System.out.println("Customer added succesfully " + customer2);
//			adminService.addCustomer(customer3);
//			System.out.println("Customer added succesfully " + customer3);
//			adminService.addCustomer(customer4);
//			System.out.println("Customer added succesfully " + customer4);
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void updateCustomerTest() {
//		try {
//			System.out.println("updateCustomer()");
//			Customer customer5 = adminService.getOneCustomer(2);
//			System.out.println(customer5);
//			customer5.setFirstName("israeli");
//			customer5.setLastName("israel");
//			customer5.setEmail("israel2@mail");
//			customer5.setPassword("123456789");
//			adminService.updateCustomer(customer5);
//			System.out.println("Customer updated succesfully " + customer5);
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void deleteCustomerTest() {
//		try {
//			System.out.println("deleteCustomer()");
//			System.out.println("Customer deleted succesfully " + adminService.getOneCustomer(4));
//			adminService.deleteCustomer(4);
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getAllCustomersTest() {
//		try {
//			System.out.println("getAllCustomers()");
//			System.out.println(adminService.getAllCustomers());
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getOneCustomerTest() {
//		try {
//			System.out.println("getOneCustomer()");
//			System.out.println(adminService.getOneCustomer(3));
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//}