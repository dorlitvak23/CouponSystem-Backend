package app.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.core.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

	Optional<Customer> findByEmailAndPassword(String email, String password);

	public boolean existsByEmail(String email);

	public boolean existsByIdAndCouponsId(int customerId, int couponId);

}
