package com.catmash.api;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.catmash.dto.CatApiResponse;
import com.catmash.dto.CatDto;

@Service
public class CatListService {

	private final WebClient webClient;

	public CatListService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("https://data.latelier.co").build();
	}

	public List<CatDto> getCats() {
		CatApiResponse response = webClient.get().uri("/cats.json").retrieve().bodyToMono(CatApiResponse.class).block();
		return response != null ? response.getImages() : List.of();
	}
}
