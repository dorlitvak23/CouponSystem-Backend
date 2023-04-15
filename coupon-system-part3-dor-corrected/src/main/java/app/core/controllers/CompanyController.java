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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponsSystemException;
import app.core.service.CompanyService;

@RequestMapping("/api/company/")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@PostMapping("coupons")
	@ResponseStatus(HttpStatus.CREATED)
	public Coupon addCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon)
			throws CouponsSystemException {
		return companyService.addCoupon(token, coupon);
	}

	@PutMapping("coupons/{CouponID}")
	@ResponseStatus(HttpStatus.OK)
	public Coupon updateCoupon(@RequestHeader("Authorization") UUID token, @RequestBody Coupon coupon,
			@PathVariable int CouponID) throws CouponsSystemException {
		return this.companyService.updateCoupon(token, coupon, CouponID);
	}

	@DeleteMapping("coupons/{CouponID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int CouponID)
			throws CouponsSystemException {
		this.companyService.deleteCoupon(token, CouponID);
	}

	@GetMapping("coupons/all")
	@ResponseStatus(HttpStatus.OK)
	public List<Coupon> getCompanyCoupons(@RequestHeader("Authorization") UUID token) throws CouponsSystemException {
		return companyService.getCompanyCoupons(token);
	}

	@GetMapping("coupons/category")
	@ResponseStatus(HttpStatus.OK)
	public List<Coupon> getCompanyCouponsByCategory(@RequestHeader("Authorization") UUID token,
			@RequestParam Category category) throws CouponsSystemException {
		return this.companyService.getCompanyCouponsByCategory(token, category);
	}

	@GetMapping("coupons/price")
	@ResponseStatus(HttpStatus.OK)
	public List<Coupon> getCompanyCouponsByMaxPrice(@RequestHeader("Authorization") UUID token,
			@RequestParam double price) throws CouponsSystemException {
		return this.companyService.getCompanyCouponsByMaxPrice(token, price);
	}

	@GetMapping("info")
	@ResponseStatus(HttpStatus.OK)
	public Company getCompanyDetails(@RequestHeader("Authorization") UUID token) throws CouponsSystemException {
		return this.companyService.getCompanyDetails(token);
	}

	@GetMapping("/coupons/{couponID}")
	@ResponseStatus(HttpStatus.OK)
	public Coupon getOneCoupon(@RequestHeader("Authorization") UUID token, @PathVariable int couponID)
			throws CouponsSystemException {
		return companyService.getOneCoupon(token, couponID);
	}
}
