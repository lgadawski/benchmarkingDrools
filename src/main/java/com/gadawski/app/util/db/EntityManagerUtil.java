package com.gadawski.app.util.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
	 * 
	 */
    private static EntityManager m_entityManager;

    /**
     * @return
     */
    public static EntityManagerFactory getEntityManagerFactory() {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("hsqldb-ds");
        return emf;
    }

    /**
     * Persist object to db.
     * 
     * @param object
     *            to be saved to db
     */
    public static void persist(Object object) {
        m_entityManager = getEntityManagerFactory().createEntityManager();

        EntityTransaction transaction = m_entityManager.getTransaction();
        transaction.begin();
        m_entityManager.persist(object);
        transaction.commit();

        closeEntityManager();
    }

    /**
     * Closes entitymanager
     */
    private static void closeEntityManager() {
        m_entityManager.close();
    }
}
