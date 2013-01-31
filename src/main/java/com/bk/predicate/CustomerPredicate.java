package com.bk.predicate;

import com.bk.model.QCustomer;
import com.mysema.query.types.Predicate;

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

    public static Predicate test() {
        QCustomer customer = QCustomer.customer;

        return customer.id.eq(5L).or(firstOrLastNameStartsWith("gigi"));
    }
}


