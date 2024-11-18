package com.catmash.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catmash.api.CatListService;
import com.catmash.dto.CatDto;
import com.catmash.entity.Cat;
import com.catmash.repository.CatRepository;
import com.catmash.service.CatSyncService;

@Service
public class CatSyncServiceImpl implements CatSyncService {

	private final CatRepository catRepository;
	private final CatListService catListService;

	public CatSyncServiceImpl(CatRepository catRepository, CatListService catListService) {
		this.catRepository = catRepository;
		this.catListService = catListService;
	}

	@Transactional
	@Override
	public void synchronizeCats() {
		 List<CatDto> cats = catListService.getCats();
	        for (CatDto catDto : cats) {
	            if (!catRepository.existsById(catDto.getId())) {
	                Cat newCat = new Cat();
	                newCat.setId(catDto.getId());
	                newCat.setUrl(catDto.getUrl());
	                catRepository.save(newCat);
	            }
	        }

	}

}
