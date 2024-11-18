package com.catmash.service;

import java.util.List;

import com.catmash.dto.CatDto;

public interface CatService {

	List<CatDto> getAllCatsWithPodium();

	CatDto getCatById(String id);
	
	List<CatDto> getTwoRandomCats();
	
	void voteForCat(String catId);
	
	long getTotalMatchesPlayed();
}
