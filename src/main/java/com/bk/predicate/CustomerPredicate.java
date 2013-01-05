package com.bk.predicate;

import com.bk.model.QCustomer;
import com.mysema.query.types.Predicate;
import javax.persistence.PersistenceContext;

/**
 * User: ph
 * Date: 1/5/13
 */
public class CustomerPredicate {

    public static Predicate firstOrLastNameStartsWith(final String searchTerm) {

        QCustomer customer = QCustomer.customer;

        return customer.firstname.startsWithIgnoreCase(searchTerm)
            .or(customer.lastname.startsWithIgnoreCase(searchTerm));
    }
}
