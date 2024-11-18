package com.catmash;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.catmash.dto.CatDto;
import com.catmash.repository.CatRepository;
import com.catmash.repository.VoteRepository;
import com.catmash.service.CatService;

@SpringBootTest
class CatMashApplicationTests {

	@Mock
	private CatService catService;

	@Mock
	private CatRepository catRepository;

	@Mock
	private VoteRepository voteRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAllCatsWithPodium() {
		CatDto cat1 = new CatDto("1", "http://example.com/cat1.jpg", 50);
		CatDto cat2 = new CatDto("2", "http://example.com/cat2.jpg", 40);
		CatDto cat3 = new CatDto("3", "http://example.com/cat3.jpg", 30);
		when(catService.getAllCatsWithPodium()).thenReturn(Arrays.asList(cat2, cat1, cat3));
		List<CatDto> result = catService.getAllCatsWithPodium();
		assertThat(result).isNotNull().hasSize(3);
		assertThat(result.get(0).getId()).isEqualTo("2");
		assertThat(result.get(1).getId()).isEqualTo("1");
		verify(catService, times(1)).getAllCatsWithPodium();
	}

	@Test
	void testGetCatById() {
		String catId = "1";
		CatDto cat = new CatDto(catId, "http://example.com/cat1.jpg", 10);
		when(catService.getCatById(catId)).thenReturn(cat);
		CatDto result = catService.getCatById(catId);
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(catId);
		assertThat(result.getUrl()).isEqualTo("http://example.com/cat1.jpg");
		verify(catService, times(1)).getCatById(catId);
	}

	@Test
	void testGetCatById_NotFound() {
		String catId = "99";
		when(catService.getCatById(catId)).thenThrow(new RuntimeException("Cat not found"));
		assertThrows(RuntimeException.class, () -> catService.getCatById(catId));
		verify(catService, times(1)).getCatById(catId);
	}

	@Test
	void testGetTwoRandomCats() {
		CatDto cat1 = new CatDto("1", "http://example.com/cat1.jpg", 10);
		CatDto cat2 = new CatDto("2", "http://example.com/cat2.jpg", 15);
		when(catService.getTwoRandomCats()).thenReturn(Arrays.asList(cat1, cat2));
		List<CatDto> result = catService.getTwoRandomCats();
		assertThat(result).isNotNull().hasSize(2);
		assertThat(result.get(0).getId()).isEqualTo("1");
		assertThat(result.get(1).getId()).isEqualTo("2");
		verify(catService, times(1)).getTwoRandomCats();
	}

	@Test
	void testVoteForCat() {
		String catId = "1";
		doNothing().when(catService).voteForCat(catId);
		catService.voteForCat(catId);
		verify(catService, times(1)).voteForCat(catId);
	}
	

	@Test
	void testGetTotalMatchesPlayed() {
		when(catService.getTotalMatchesPlayed()).thenReturn(100L);
		long totalMatches = catService.getTotalMatchesPlayed();
		assertThat(totalMatches).isEqualTo(100L);
		verify(catService, times(1)).getTotalMatchesPlayed();
	}

}
