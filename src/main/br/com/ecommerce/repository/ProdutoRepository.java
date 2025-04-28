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

    public long contarProdutosCadastrados(){
        Session session = sessionFactory.openSession();

        Long totalProdutos = session.createQuery(
                "SELECT COUNT(p) FROM br.com.ecommerce.entities.produto.ProdutoEntity p",
                Long.class
        ).getSingleResult();

        session.close();
        return totalProdutos;
    }

    public void buscarPorValor(double valor1, double valor2) {
        Session session = sessionFactory.openSession();

        List<ProdutoEntity> produtos = session.createQuery(
                        "FROM br.com.ecommerce.entities.produto.ProdutoEntity p WHERE p.preco BETWEEN :precoMin AND :precoMax",
                        ProdutoEntity.class
                )
                .setParameter("precoMin", valor1)
                .setParameter("precoMax", valor2)
                .list();

        if (produtos != null && !produtos.isEmpty()) {
            for (ProdutoEntity produto : produtos) {
                System.out.println("------------------------");
                System.out.println("Codigo: " + produto.getCodigoProduto());
                System.out.println("------------------------");
                System.out.println("Nome: " + produto.getNome());
                System.out.println("------------------------");
                System.out.println("Quantidade: " + produto.getQuantidade());
                System.out.println("------------------------");
                System.out.println("Tipo: " + produto.getTipo());
                System.out.println("------------------------");
                System.out.println("Preço: " + produto.getPreco());
                System.out.println("------------------------\n\n\n");
            }
        } else {
            System.out.println("Nenhum produto encontrado para esse intervalo de valores.");
        }

        session.close();
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