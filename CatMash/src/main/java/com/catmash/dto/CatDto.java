package com.catmash.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatDto  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2228993521452228073L;

	@NotNull(message = "ID must not be null")
	private String id;
	
	@NotNull(message = "ID must not be null")
	private String url;
	
	private int score;
}
