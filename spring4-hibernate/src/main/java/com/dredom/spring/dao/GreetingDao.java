package com.dredom.spring.dao;

import org.springframework.stereotype.Repository;

import com.dredom.spring.model.Greeting;

/**
 * Hibernate Data Access Object for Greeting.
 * Table greeting.
 * <p>Annotation (at)Repository ensures exception translation into helpful
 * Spring presistence exception hierarchy.
 *
 * @author  auntiedt
 * @version 1.0, Jul 14, 2014 Written
 */
@Repository
public class GreetingDao extends AbstractDao<Greeting> {

}
