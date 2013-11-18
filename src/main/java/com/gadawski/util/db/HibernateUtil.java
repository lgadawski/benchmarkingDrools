package com.gadawski.util.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Utility class for opening sessions between db and application.
 * 
 * @author l.gadawski@gmail.com
 * 
 */
public class HibernateUtil {
	/**
	 * 
	 */
	private static SessionFactory sessionFactory;// = buildSessionFactory();
	private static ServiceRegistry serviceRegistry;

	/**
	 * @return
	 */
	private static SessionFactory buildSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.configure();
		serviceRegistry = new ServiceRegistryBuilder().applySettings(
				configuration.getProperties()).buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
		// return new Configuration().configure().buildSessionFactory();
	}

	/**
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return buildSessionFactory();//sessionFactory;
	}

	/**
	 * Close caches and connection pools.
	 */
	public static void shutdown() {
		getSessionFactory().close();
	}

}
