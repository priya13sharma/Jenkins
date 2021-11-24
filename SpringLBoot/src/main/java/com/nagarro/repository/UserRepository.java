package com.nagarro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.nagarro.entities.Librarian;

public interface UserRepository extends JpaRepository<Librarian, Integer> {
	
	@Query("select e from Library e where e.email = :email")
	public Librarian getUserByUserName(@Param("email") String email);

}
