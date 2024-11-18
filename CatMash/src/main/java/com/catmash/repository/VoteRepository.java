package com.catmash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catmash.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
	
}
