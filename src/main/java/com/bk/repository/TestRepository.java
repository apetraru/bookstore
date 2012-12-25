/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bk.repository;

import com.bk.model.Test;
import org.springframework.data.repository.Repository;

/**
 *
 * @author ph
 */
public interface TestRepository extends Repository<Test, Long> {
	Test findById(Long id);
	Test save(Test test);
}
