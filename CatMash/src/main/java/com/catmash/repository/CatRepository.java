package com.catmash.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.catmash.entity.Cat;

public interface CatRepository extends JpaRepository<Cat, String> {

	@Query(value = "SELECT * FROM cat ORDER BY RAND() LIMIT 2", nativeQuery = true)
	List<Cat> findTwoRandomCats();
}
