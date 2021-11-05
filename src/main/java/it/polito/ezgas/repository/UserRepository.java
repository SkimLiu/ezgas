package it.polito.ezgas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import it.polito.ezgas.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByPasswordAndEmail(String password, String email);
	List<User> findByEmail(String email);
}
