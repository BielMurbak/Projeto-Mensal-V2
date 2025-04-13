package br.com.ecommerce.repository;

import br.com.ecommerce.entities.user.AdministradorEntity;
import br.com.ecommerce.entities.user.PessoaEntity;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        List<ProdutoEntity> produtos = session.createQuery(
                "FROM br.com.ecommerce.entities.produto.ProdutoEntity ORDER BY codigoProduto",
                ProdutoEntity.class
        ).list();

        session.close();
        return produtos;
    }

    public ProdutoEntity buscarPorCodigo(int codigoProduto) {
        Session session = sessionFactory.openSession();
        ProdutoEntity produto = session
                .createQuery("FROM br.com.ecommerce.entities.produto.ProdutoEntity WHERE codigoProduto = :codigo", ProdutoEntity.class)
                .setParameter("codigo", codigoProduto)
                .uniqueResult();
        session.close();
        return produto;
    }

    public void atualizar(ProdutoEntity produto) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.merge(produto); // Atualiza o produto já existente

        session.getTransaction().commit();
        session.close();
    }

    public void salvar(ProdutoEntity produto) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(produto);
        session.getTransaction().commit();
        session.close();

    }

    public void deletarNomeProduto(String nome) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            ProdutoEntity produto = session
                    .createQuery("FROM produto WHERE nome = :nome", ProdutoEntity.class)
                    .setParameter("nome", nome)
                    .uniqueResult();

            if (produto != null) {
                session.delete(produto);
                transaction.commit();
                System.out.println("Produto deletado com sucesso.");
            } else {
                System.out.println("Produto não encontrado.");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

}