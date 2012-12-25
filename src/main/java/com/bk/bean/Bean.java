/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.bean;

import com.bk.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author ph
 */
@Component("bean")
public class Bean {

	@Autowired
	TestRepository repository;

	private String name = "gigi";

	public String getName() {
		return name;
	}

	public void change() {
		name = repository.findById(3L).getTest();
	}
}
