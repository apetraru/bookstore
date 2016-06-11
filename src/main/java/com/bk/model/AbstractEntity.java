package com.bk.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author ph
 */
@MappedSuperclass
public class AbstractEntity implements Serializable{
	private static final long serialVersionUID = -2216228236601294268L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (this.id == null || other == null || !(this.getClass().equals(other.getClass()))) {
            return false;
        }

        AbstractEntity that = (AbstractEntity) other;

        return this.id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
