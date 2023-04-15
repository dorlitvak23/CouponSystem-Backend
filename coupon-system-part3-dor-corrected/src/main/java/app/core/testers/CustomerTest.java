//package app.core.testers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import app.core.entities.Category;
//import app.core.entities.Coupon;
//import app.core.exceptions.CouponsSystemException;
//import app.core.login.ClientType;
//import app.core.login.LoginManager;
//import app.core.service.CustomerService;
//
//@Component
//@Order(3)
//public class CustomerTest implements CommandLineRunner {
//
//	@Autowired
//	private LoginManager loginManager;
//
//	private CustomerService customerService;
//
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("====================================");
//		System.out.println("welcome to customer's test");
//		System.out.println("====================================");
//		loginTest();
//		purchaseCouponTest();
//		getCoustumerCoupons();
//		getCustomerCouponsByCategory();
//		GetCustomerCouponsByMaxPrice();
//		getCustomerDetailsTest();
//
//	}
//
//	public void loginTest() {
//		try {
//			System.out.println("====================================");
//			System.out.println("Success customer's login");
//			System.out.println("====================================");
//			customerService = (CustomerService) loginManager.login("dor@mail.com", "hghghg", ClientType.CUSTOMER);
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void purchaseCouponTest() {
//		try {
//			Coupon c = customerService.getOneCoupon(2);
//			Coupon d = customerService.getOneCoupon(3);
//			System.out.println("Coupon purchased successfully");
//			customerService.purchaseCoupon(c.getId());
//			System.out.println("Coupon purchased successfully");
//			customerService.purchaseCoupon(d.getId());
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCoustumerCoupons() {
//		try {
//			System.out.println("getCustomerCoupons()");
//			System.out.println(customerService.getCustomerCoupons());
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCustomerCouponsByCategory() {
//		try {
//			System.out.println("getCustomerCouponsByCategory(ELECTRICITY)");
//			System.out.println(customerService.getCustomerCouponsByCategory(Category.ELECTRICITY));
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void GetCustomerCouponsByMaxPrice() {
//		try {
//			System.out.println("getCustomerCouponsByMaxPrice(9000)");
//			System.out.println(customerService.GetCustomerCouponsByMaxPrice(9000));
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCustomerDetailsTest() {
//		try {
//			System.out.println("getCustomerDetails()");
//			System.out.println(customerService.getCustomerDetails());
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//}
