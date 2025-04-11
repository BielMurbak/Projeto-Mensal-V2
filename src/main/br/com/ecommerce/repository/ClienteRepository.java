package br.com.ecommerce.repository;


import br.com.ecommerce.entities.produto.ProdutoEntity;
import br.com.ecommerce.entities.user.AdministradorEntity;
import br.com.ecommerce.entities.user.ClienteEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ClienteRepository {


    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public void salvar(ClienteEntity cliente) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cliente);
        session.getTransaction().commit();
        session.close();
    }

    public List<ClienteEntity> buscarCliente(){
        Session session = sessionFactory.openSession();
        List<ClienteEntity> cliente = null;

        try {
            cliente = session.createQuery("FROM cliente a JOIN FETCH a.pessoa", ClienteEntity.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            session.close();
        }

        return cliente;
    }

    public void deletarCpfCliente(String cpf) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            ClienteEntity cliente = session
                    .createQuery("FROM cliente c WHERE c.pessoa.cpf = :cpf", ClienteEntity.class)
                    .setParameter("cpf", cpf) // Certifique-se que "cpf" é passado corretamente
                    .uniqueResult();

            if (cliente != null) {
                session.delete(cliente);
                transaction.commit();
                System.out.println("Cliente deletado com sucesso.");
            } else {
                System.out.println("Cliente não encontrado.");
            }

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

}
