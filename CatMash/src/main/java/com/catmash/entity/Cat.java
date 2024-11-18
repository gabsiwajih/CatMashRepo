package com.catmash.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cat {

	@Id
	@NotNull(message = "ID must not be null")
	private String id;

	@NotNull(message = "URL must not be null")
	private String url;

	@Column(nullable = false, columnDefinition = "INT DEFAULT 0")
	private int score;
}
