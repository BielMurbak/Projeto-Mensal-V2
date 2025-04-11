package br.com.ecommerce.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClienteVarejo {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }


}
