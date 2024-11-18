package com.catmash.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CatApiResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7205095294987510315L;
	private List<CatDto> images;
}
