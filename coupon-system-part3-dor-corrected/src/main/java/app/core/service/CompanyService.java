package app.core.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponsSystemException;
import app.core.login.ClientType;
import app.core.security.Information;
import app.core.security.LoginResponse;

@Service
@Transactional
public class CompanyService extends ClientService {

	@Override
	public LoginResponse login(String email, String password) throws CouponsSystemException {
		Company opt = this.companyRepo.findByEmailAndPassword(email, password)
				.orElseThrow(() -> new CouponsSystemException("Email or password is wrong"));
		Information information = new Information(opt.getId(), email, LocalDateTime.now().plusDays(1),
				ClientType.COMPANY);
		UUID tokenToSend = tokenManager.addToken(information);
		return new LoginResponse(tokenToSend, opt.getId(), opt.getName(), email, ClientType.COMPANY);

	}

	public Coupon addCoupon(UUID token, Coupon coupon) throws CouponsSystemException {
		int companyId = tokenManager.getCompanyId(token);
		if (couponRepo.existsByCompanyIdAndTitle(companyId, coupon.getTitle())) {
			throw new CouponsSystemException("this coupon is already existed");
		} else {
			companyRepo.findById(companyId).get().addCoupon(coupon);
			couponRepo.save(coupon);
		}
		return coupon;
	}

	public Coupon updateCoupon(UUID token, Coupon coupon, int couponID) throws CouponsSystemException {
		tokenManager.getCompanyId(token);

		Coupon updatedCoupon = couponRepo.findById(coupon.getId())
				.orElseThrow(() -> new CouponsSystemException("Coupon not found"));
		updatedCoupon.setCategory(coupon.getCategory());
		updatedCoupon.setTitle(coupon.getTitle());
		updatedCoupon.setDescription(coupon.getDescription());
		updatedCoupon.setStartDate(coupon.getStartDate());
		updatedCoupon.setEndDate(coupon.getEndDate());
		updatedCoupon.setAmount(coupon.getAmount());
		updatedCoupon.setPrice(coupon.getPrice());
		updatedCoupon.setImage(coupon.getImage());
		return couponRepo.saveAndFlush(updatedCoupon);
	}

	public void deleteCoupon(UUID token, int CouponID) throws CouponsSystemException {
		tokenManager.getCompanyId(token);

		if (!(couponRepo.existsById(CouponID))) {
			throw new CouponsSystemException("the coupon is not exist");
		}
		couponRepo.deleteById(CouponID);
	}

	public List<Coupon> getCompanyCoupons(UUID token) throws CouponsSystemException {
		int companyId = tokenManager.getCompanyId(token);

		List<Coupon> companyCoupons = couponRepo.findAllByCompanyId(companyId);
		return companyCoupons;
	}

	public List<Coupon> getCompanyCouponsByCategory(UUID token, Category category) throws CouponsSystemException {
		int companyId = tokenManager.getCompanyId(token);
		List<Coupon> companyCouponsByCategory = couponRepo.findAllByCompanyIdAndCategory(companyId, category);
		return companyCouponsByCategory;
	}

	public List<Coupon> getCompanyCouponsByMaxPrice(UUID token, double price) throws CouponsSystemException {
		int companyId = tokenManager.getCompanyId(token);
		List<Coupon> companyCouponsByPrice = couponRepo.findByCompanyIdAndPriceLessThanEqual(companyId, price);
		return companyCouponsByPrice;
	}

	public Company getCompanyDetails(UUID token) throws CouponsSystemException {
		int companyId = tokenManager.getCompanyId(token);
		return companyRepo.findById(companyId)
				.orElseThrow(() -> new CouponsSystemException("Company " + companyId + " not found"));
	}

	public Coupon getOneCoupon(UUID token, int couponID) throws CouponsSystemException {
		tokenManager.getCompanyId(token);
		return couponRepo.findById(couponID).orElseThrow(() -> new CouponsSystemException("Coupon not found"));
	}

}
