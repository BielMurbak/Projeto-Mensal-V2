package  br.com.ecommerce.repository;


import br.com.ecommerce.entities.user.AdministradorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class AdministradorRepository {

    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    public AdministradorEntity buscarPorId(Long id) {
        Session session = sessionFactory.openSession();
        AdministradorEntity administrador = null;

        try {
            administrador = session.get(AdministradorEntity.class, id);
        } finally {
            session.close();
        }

        return administrador;
    }

    public AdministradorEntity buscarPorSenha(String senha) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        AdministradorEntity admin = null;

        try {
            transaction = session.beginTransaction();

            admin = session
                    .createQuery("FROM AdministradorEntity WHERE senha = :senha", AdministradorEntity.class)
                    .setParameter("senha", senha)
                    .uniqueResult();

            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Erro ao buscar Administrador por senha", e);
        } finally {
            session.close();
        }

        return admin;
    }



    public void salvar(AdministradorEntity administrador) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(administrador);
        session.getTransaction().commit();
        session.close();
    }

}
