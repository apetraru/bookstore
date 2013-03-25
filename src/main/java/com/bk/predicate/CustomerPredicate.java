package com.bk.predicate;

import com.bk.model.QCustomer;
import com.mysema.query.types.Predicate;

/**
 * @author Andrei Petraru
 * Date: 5 Jan 2013
 */
public final class CustomerPredicate {
	
	private CustomerPredicate() {
	}

    public static Predicate firstOrLastNameStartsWith(final String searchTerm) {

        QCustomer customer = QCustomer.customer;

        return customer.firstname.startsWithIgnoreCase(searchTerm)
            .or(customer.lastname.startsWithIgnoreCase(searchTerm));
    }
}


