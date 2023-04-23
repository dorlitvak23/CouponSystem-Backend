package app.core.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.exceptions.CouponsSystemException;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {

	boolean existsByCompanyIdAndTitle(int companyId, String couponTitle);

	List<Coupon> findAllByCompanyId(int companyId) throws CouponsSystemException;

	List<Coupon> findAllByCustomersId(int customerId) throws CouponsSystemException;

	List<Coupon> findAllByCompanyIdAndCategory(int companyId, Category category) throws CouponsSystemException;

	List<Coupon> findByCompanyIdAndPriceLessThanEqual(int companyId, double price) throws CouponsSystemException;

	List<Coupon> findAllByCustomersIdAndCategory(int customerId, Category category) throws CouponsSystemException;

	List<Coupon> findAllByCustomersIdAndPriceLessThanEqual(int customerId, double maxPrice)
			throws CouponsSystemException;

	@Modifying
	@Query(value = "delete from `customers_vs_coupons` where coupon_id in(select id from `coupon` where end_date < now());", nativeQuery = true)
	void deleteExpiredPurchasedCoupons() throws CouponsSystemException;

	@Modifying
	@Query(value = "delete from `coupon` where `end_date` < now();", nativeQuery = true)
	void deleteExpiredCoupons() throws CouponsSystemException;

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM customers_vs_coupons WHERE coupon_id IN "
			+ "(SELECT id FROM coupon WHERE company_id = ?1)", nativeQuery = true)
	void deletePurchasesByCompanyId(int companyId);

}
