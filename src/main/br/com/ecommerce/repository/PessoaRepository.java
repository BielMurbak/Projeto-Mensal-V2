package br.com.ecommerce.repository;

import br.com.ecommerce.entities.cliente.PessoaEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PessoaRepository {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public PessoaEntity buscarPorId(Long id) {
        Session session = sessionFactory.openSession();
        PessoaEntity pessoa = null;

        try {
            pessoa = session.get(PessoaEntity.class, id);
        } finally {
            session.close();
        }

        return pessoa;
    }

    public void salvar(PessoaEntity pessoa) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(pessoa);
        session.getTransaction().commit();
        session.close();
    }
}
