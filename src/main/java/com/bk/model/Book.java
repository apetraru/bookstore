package com.bk.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author ph
 */

@Entity
public class Book extends AbstractEntity {

	@Column(nullable = false)
	private String name;

	private String description;

	@Column(nullable = false)
	BigDecimal price;

	private Integer stock;
}
