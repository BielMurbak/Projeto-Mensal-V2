package br.com.ecommerce.repository;


import br.com.ecommerce.entities.compra.HistoricoDeComprasEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HistoricoDeComprasRepository {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public void salvar(HistoricoDeComprasEntity historicoDeCompras) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(historicoDeCompras);
        session.getTransaction().commit();
        session.close();

    }
}
