//package app.core.testers;
//
//import java.time.LocalDate;
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
//import app.core.service.CompanyService;
//
//@Component
//@Order(2)
//public class CompanyTest implements CommandLineRunner {
//
//	@Autowired
//	private LoginManager loginManager;
//
//	private CompanyService companyService;
//
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("====================================");
//		System.out.println("welcome to company's test");
//		System.out.println("====================================");
//		loginTest();
//		addCouponTest();
//		updateCouponTest();
//		deleteCouponTest();
//		getCompanyCouponsTest();
//		getAllCouponsByCategoryTest();
//		getAllCompanyCouponsByPriceTest();
//		getCompanyDetailsTest();
//
//	}
//
//	public void loginTest() {
//		try {
//			System.out.println("====================================");
//			System.out.println("Success company's login");
//			System.out.println("====================================");
//			companyService = (CompanyService) loginManager.login("samsung@mail.com", "asdfg", ClientType.COMPAMY);
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void addCouponTest() {
//		Coupon coupon1 = new Coupon(0, null, Category.FOOD, "Pizza", "w/olives", LocalDate.now(),
//				LocalDate.of(2023, 12, 31), 100, 999.9, "Good", null);
//		Coupon coupon2 = new Coupon(0, null, Category.ELECTRICITY, "Tv", "75inch", LocalDate.now(),
//				LocalDate.of(2023, 02, 28), 50, 6999.9, "Excellent", null);
//		Coupon coupon3 = new Coupon(0, null, Category.VACATION, "Thailand", "Bangkok", LocalDate.now(),
//				LocalDate.of(2025, 01, 01), 10, 10000.9, "Best", null);
//		Coupon coupon4 = new Coupon(0, null, Category.ELECTRICITY, "Tv2", "75inch2", LocalDate.now(),
//				LocalDate.of(2023, 01, 28), 52, 2999.9, "Excellent", null);
//
//		try {
//			System.out.println("addCoupon()");
//			companyService.addCoupon(coupon1);
//			System.out.println("Coupon added succesfully " + coupon1);
//			companyService.addCoupon(coupon2);
//			System.out.println("Coupon added succesfully " + coupon2);
//			companyService.addCoupon(coupon3);
//			System.out.println("Coupon added succesfully " + coupon3);
//			companyService.addCoupon(coupon4);
//			System.out.println("Coupon added succesfully " + coupon4);
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//
//	}
//
//	public void updateCouponTest() {
//		try {
//			System.out.println("updateCoupon()");
//			Coupon c = companyService.getOneCoupon(1);
//			System.out.println(c);
//			c.setCategory(Category.ELECTRICITY);
//			c.setTitle("Oven");
//			c.setDescription("LG");
//			c.setStartDate(LocalDate.now());
//			c.setEndDate(LocalDate.of(2023, 02, 02));
//			c.setAmount(700);
//			c.setPrice(1999);
//			c.setImage("Top");
//			System.out.println("Update coupon done" + c);
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void deleteCouponTest() {
//		try {
//			System.out.println("deleteCouponTest()");
//			companyService.deleteCoupon(1);
//			System.out.println("Coupon deleted succesfully");
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCompanyCouponsTest() {
//		try {
//			System.out.println("getCompanyCoupons()");
//			System.out.println(companyService.getCompanyCoupons());
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getAllCouponsByCategoryTest() {
//		try {
//			System.out.println("getAllCouponsByCategory(ELECTRICITY)");
//			System.out.println(companyService.getCompanyCouponsByCategory(Category.ELECTRICITY));
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
////	not finished
//	public void getAllCompanyCouponsByPriceTest() {
//		try {
//			System.out.println("getAllCompanyCouponsByPrice(15000)");
//			System.out.println(companyService.getCompanyCouponsByMaxPrice(15000));
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//	public void getCompanyDetailsTest() {
//		try {
//			System.out.println("getCompanyDetails()");
//			System.out.println(companyService.getCompanyDetails());
//			System.out.println("====================================");
//		} catch (CouponsSystemException e) {
//			System.out.println(e);
//		}
//	}
//
//}
