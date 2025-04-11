package br.com.ecommerce.repository;


import br.com.ecommerce.entities.endereco.EnderecoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EnderecoRepository {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public void salvar(EnderecoEntity endereco) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(endereco);
        session.getTransaction().commit();
        session.close();
    }
}
