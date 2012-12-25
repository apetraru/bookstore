/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author ph
 */
@Entity
public class Test {

	@Id
	private Long id;
	private String test;

	public Long getId() {
		return id;
	}

	public String getTest() {
		return test;
	}
}
