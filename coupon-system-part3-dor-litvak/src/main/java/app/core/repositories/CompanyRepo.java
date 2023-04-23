package app.core.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.core.entities.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {

	Optional<Company> findByEmailAndPassword(String email, String password);

	public boolean existsByName(String name);

	public boolean existsByEmail(String email);

	boolean existsByEmailAndIdNot(String email, int id);

}
