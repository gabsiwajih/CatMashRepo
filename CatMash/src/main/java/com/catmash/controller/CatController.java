package com.catmash.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catmash.dto.CatDto;
import com.catmash.service.CatService;

@RestController
@RequestMapping("/api/cats")
public class CatController {

	private final CatService catService;

	public CatController(CatService catService) {
		this.catService = catService;
	}

	@GetMapping("all")
	public List<CatDto> getAllCatsWithPodium() {
		return catService.getAllCatsWithPodium();
	}

	@GetMapping("vote")
	public List<CatDto> getTwoCatsForVote() {
		return catService.getTwoRandomCats();
	}

	@PostMapping("/vote/{id}")
	public void voteForCat(@PathVariable String id) {
		catService.voteForCat(id);
	}

	@GetMapping("/votes")
	public long getTotalMatchesPlayed() {
		return catService.getTotalMatchesPlayed();
	}
}
