package app.core.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponsSystemException;
import app.core.login.ClientType;
import app.core.security.Information;
import app.core.security.LoginResponse;

@Service
@Transactional
public class CustomerService extends ClientService {

	@Override
	public LoginResponse login(String email, String password) throws CouponsSystemException {
		Customer opt = this.customerRepo.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new CouponsSystemException("Email or password is wrong"));
		Information information = new Information(opt.getId(), email, LocalDateTime.now().plusDays(1),
				ClientType.CUSTOMER);
		UUID tokenToSend = tokenManager.addToken(information);
		return new LoginResponse(tokenToSend, opt.getId(), opt.getFirstName(), email, ClientType.CUSTOMER);

	}

	public Coupon purchaseCoupon(UUID token, int couponId) throws CouponsSystemException {

		int customerId = tokenManager.getCustomerId(token);
		Customer customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new CouponsSystemException("Customer not exist"));
		Coupon couponToBePurchased = couponRepo.findById(couponId)
				.orElseThrow(() -> new CouponsSystemException("Coupon not exist"));
		if (customerRepo.existsByIdAndCouponsId(customerId, couponId)) {
			throw new CouponsSystemException("Coupon already exist, customers can't buy same coupon twice");
		}
		if (couponToBePurchased.getAmount() <= 0) {
			throw new CouponsSystemException("Coupon sold out, buy another one");
		}
		if (couponToBePurchased.getEndDate().isBefore(LocalDate.now())) {
			throw new CouponsSystemException("Coupon expiration ended, buy another one");
		}
		customer.addCoupon(couponToBePurchased);

		couponToBePurchased.setAmount(couponToBePurchased.getAmount() - 1);
		return couponRepo.saveAndFlush(couponToBePurchased);
	}

	public List<Coupon> getCustomerCoupons(UUID token) throws CouponsSystemException {
		int customerId = tokenManager.getCustomerId(token);

		return couponRepo.findAllByCustomersId(customerId);
	}

	public List<Coupon> getCustomerCouponsByCategory(UUID token, Category category) throws CouponsSystemException {
		int customerId = tokenManager.getCustomerId(token);

		return couponRepo.findAllByCustomersIdAndCategory(customerId, category);
	}

	public List<Coupon> GetCustomerCouponsByMaxPrice(UUID token, double maxPrice) throws CouponsSystemException {
		int customerId = tokenManager.getCustomerId(token);

		return couponRepo.findAllByCustomersIdAndPriceLessThanEqual(customerId, maxPrice);
	}

	public Customer getCustomerDetails(UUID token) throws CouponsSystemException {
		int customerId = tokenManager.getCustomerId(token);
		return customerRepo.findById(customerId)
				.orElseThrow(() -> new CouponsSystemException("This customer isn't exist"));
	}

	public Coupon getOneCoupon(UUID token, int couponId) throws CouponsSystemException {
		tokenManager.getCustomerId(token);

		return couponRepo.findById(couponId).orElseThrow(() -> new CouponsSystemException("this coupon isn't exist"));
	}

	public List<Coupon> getAllCoupons(UUID token) throws CouponsSystemException {
		tokenManager.getCustomerId(token);
		return couponRepo.findAll();
	}

}
