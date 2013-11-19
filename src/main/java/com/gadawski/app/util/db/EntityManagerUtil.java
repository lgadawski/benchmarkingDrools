package com.gadawski.app.util.db;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Gives access to EntityManagerFactory which controls connection to the db,
 * also gives access to EntityManager instances.
 * 
 * @author l.gadawski
 * 
 */
public class EntityManagerUtil {
	/**
	 * @return
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("hsqldb-ds");
		return emf;
	}
}
