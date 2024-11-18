package com.catmash.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catmash.dto.CatDto;
import com.catmash.entity.Cat;
import com.catmash.entity.Vote;
import com.catmash.repository.CatRepository;
import com.catmash.repository.VoteRepository;
import com.catmash.service.CatService;

@Service
public class CatServiceImpl implements CatService {

	private final CatRepository catRepository;
	private final VoteRepository voteRepository;

	public CatServiceImpl(CatRepository catRepository, VoteRepository voteRepository) {
		this.catRepository = catRepository;
		this.voteRepository = voteRepository;
	}

	
	/**
	 * Retrieves a list of all cats sorted by their scores in descending order and swaps the first 
	 * and second cats to display the second-ranked cat in the first position (podium effect).
	 */
	@Override
	public List<CatDto> getAllCatsWithPodium() {
		List<Cat> cats = catRepository.findAll().stream()
				.sorted((cat1, cat2) -> Integer.compare(cat2.getScore(), cat1.getScore())).collect(Collectors.toList());

		if (cats.size() > 1) {
			Cat second = cats.get(1);
			Cat first = cats.get(0);
			cats.remove(1);
			cats.add(0, second);
			cats.set(1, first);
		}

		return cats.stream().map(cat -> new CatDto(cat.getId(), cat.getUrl(), cat.getScore()))
				.collect(Collectors.toList());
	}

	@Override
	public CatDto getCatById(String id) {
		Cat cat = catRepository.findById(id).orElseThrow(() -> new RuntimeException("Cat not found with id: " + id));
		return new CatDto(cat.getId(), cat.getUrl(), cat.getScore());
	}

	@Override
	public List<CatDto> getTwoRandomCats() {
		return catRepository.findTwoRandomCats().stream()
				.map(cat -> new CatDto(cat.getId(), cat.getUrl(), cat.getScore())).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void voteForCat(String catId) {
		Cat cat = catRepository.findById(catId)
				.orElseThrow(() -> new IllegalArgumentException("Cat not found with id: " + catId));
		Vote vote = new Vote();
		vote.setCat(cat);
		voteRepository.save(vote);
		cat.setScore(cat.getScore() + 1);
		catRepository.save(cat);
	}

	@Override
	public long getTotalMatchesPlayed() {
		 return voteRepository.count(); 
	}

}
