package com.bk.predicate;

import com.bk.model.QCustomer;
import com.mysema.query.types.Predicate;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: ph
 * Date: 1/5/13
 */
public class CustomerPredicate {

    @Transactional(readOnly = true)
    public static Predicate firstOrLastNameStartsWith(final String searchTerm) {

        QCustomer customer = QCustomer.customer;

        return customer.firstname.startsWithIgnoreCase(searchTerm)
            .or(customer.lastname.startsWithIgnoreCase(searchTerm));
    }
}
