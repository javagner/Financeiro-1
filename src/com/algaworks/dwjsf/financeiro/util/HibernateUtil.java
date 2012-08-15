package com.algaworks.dwjsf.financeiro.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		try {
			AnnotationConfiguration configuration = new AnnotationConfiguration().configure("com/algaworks/dwjsf/financeiro/util/hibernate.cfg.xml");
			sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}

	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

}
