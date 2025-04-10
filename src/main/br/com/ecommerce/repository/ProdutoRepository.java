package br.com.ecommerce.repository;

import br.com.ecommerce.entities.user.PessoaEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import br.com.ecommerce.entities.produto.ProdutoEntity;
import java.util.List;

public class ProdutoRepository {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public List<ProdutoEntity> listarTodos() {
        Session session = sessionFactory.openSession();
        List<ProdutoEntity> produtos = session.createQuery("FROM ProdutoEntity", ProdutoEntity.class).list();
        session.close();
        return produtos;
    }

    public ProdutoEntity buscarPorId(Long id) {
        Session session = sessionFactory.openSession();
        ProdutoEntity produto = null;

        try {
            produto = session.get(ProdutoEntity.class, id);
        } finally {
            session.close();
        }

        return produto;

    }

    public void salvar(ProdutoEntity produto) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(produto);
        session.getTransaction().commit();
        session.close();

    }
}