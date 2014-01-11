package com.gadawski.app.db.jpa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.gadawski.util.db.jpa.EntityManagerUtil;

/**
 * @author l.gadawski@gmail.com
 * 
 */
public class EntityManagerTest {
    /**
     * 
     */
    private EntityManagerUtil m_entityManager;

    @Before
    public void getEntityManager() {
        this.m_entityManager = EntityManagerUtil.getInstance();
    }

    @After
    public void cleanup() {
        this.m_entityManager.close();
    }

    /**
     * Tests persisting Object not defined in persistence.xml
     */
    @Test
    public void testPersistingObjectsNotDeclared() {
        this.m_entityManager.createEMandInitilizeTransaction();
        this.m_entityManager.beginTransaction();
        this.m_entityManager.saveObject(new TestClass());
        this.m_entityManager.commitTransaction();
    }

    public static class TestClass {
        int a;
        int b;

        TestClass() {
        }
    }
}
