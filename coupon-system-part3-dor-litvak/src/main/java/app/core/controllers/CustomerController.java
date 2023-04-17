package app.core.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponsSystemException;
import app.core.service.CustomerService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RequestMapping("/api/customer")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PutMapping("/coupons/{couponId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void purchaseCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponId)
			throws CouponsSystemException {
		this.customerService.purchaseCoupon(token, couponId);
	}

	@GetMapping("/customer/coupons")
	@ResponseStatus(HttpStatus.OK)
	public List<Coupon> getCustomerCoupons(@RequestHeader("Authorization") UUID token) throws CouponsSystemException {
		return this.customerService.getCustomerCoupons(token);
	}

	@GetMapping("coupons/category")
	@ResponseStatus(HttpStatus.OK)
	public List<Coupon> getCustomerCouponsByCategory(@RequestHeader("Authorization") UUID token,
			@RequestBody Category category) throws CouponsSystemException {
		return this.customerService.getCustomerCouponsByCategory(token, category);
	}

	@GetMapping("coupons/price")
	@ResponseStatus(HttpStatus.OK)
	public List<Coupon> GetCustomerCouponsByMaxPrice(@RequestHeader("Authorization") UUID token,
			@RequestBody double maxPrice) throws CouponsSystemException {
		return this.customerService.GetCustomerCouponsByMaxPrice(token, maxPrice);
	}

	@GetMapping("info")
	@ResponseStatus(HttpStatus.OK)
	public Customer getCustomerDetails(@RequestHeader("Authorization") UUID token) throws CouponsSystemException {
		return this.customerService.getCustomerDetails(token);
	}

	@GetMapping("/coupons/all")
	@ResponseStatus(HttpStatus.OK)
	public List<Coupon> getAllCoupons(@RequestHeader("Authorization") UUID token) throws CouponsSystemException {
		return customerService.getAllCoupons(token);
	}

}
