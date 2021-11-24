package com.nagarro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.entities.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

}
