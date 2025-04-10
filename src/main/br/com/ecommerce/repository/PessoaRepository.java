package br.com.ecommerce.repository;


import br.com.ecommerce.entities.user.AdministradorEntity;
import br.com.ecommerce.entities.user.PessoaEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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


        public void deletarPorCpf(String cpf) {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                // Primeiro, busca o Administrador ligado a essa Pessoa
                AdministradorEntity admin = session
                        .createQuery("FROM AdministradorEntity WHERE pessoaEntity.cpf = :cpf", AdministradorEntity.class)
                        .setParameter("cpf", cpf)
                        .uniqueResult();

                if (admin != null) {
                    session.delete(admin); // Deleta o Administrador (vai deletar Pessoa junto se tiver cascade)
                    transaction.commit();
                    System.out.println("Administrador e Pessoa deletados com sucesso!");
                } else {
                    // Caso não seja um administrador, deleta só a Pessoa
                    PessoaEntity pessoa = session
                            .createQuery("FROM PessoaEntity WHERE cpf = :cpf", PessoaEntity.class)
                            .setParameter("cpf", cpf)
                            .uniqueResult();

                    if (pessoa != null) {
                        session.delete(pessoa);
                        transaction.commit();
                        System.out.println("Pessoa deletada com sucesso!");
                    } else {
                        System.out.println("Pessoa com CPF " + cpf + " não encontrada.");
                    }
                }

            } catch (Exception e) {
                if (transaction != null) transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }
        }


    }



