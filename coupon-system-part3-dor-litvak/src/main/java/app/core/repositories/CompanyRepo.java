package app.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.core.entities.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {

	Optional<Company> findByEmailAndPassword(String email, String password);

	public boolean existsByNameOrEmail(String name, String email);

}
