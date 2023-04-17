package app.core.thread;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.core.exceptions.CouponsSystemException;
import app.core.repositories.CouponRepo;

@Component
public class CouponExpirationDailyJob {

	@Autowired
	private CouponRepo couponRepo;

	@Transactional
	@Scheduled(cron = "0 00 00 ? * *")
//	@Scheduled(timeUnit = TimeUnit.SECONDS, fixedRate = 10)
	public void run() {
		try {
			couponRepo.deleteExpiredPurchasedCoupons();
			couponRepo.deleteExpiredCoupons();
		} catch (CouponsSystemException e) {
			System.out.println(e);
		}
	}
}
