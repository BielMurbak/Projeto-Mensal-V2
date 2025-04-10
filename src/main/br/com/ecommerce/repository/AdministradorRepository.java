package  br.com.ecommerce.repository;


import br.com.ecommerce.entities.user.AdministradorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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


    public void salvar(AdministradorEntity administrador) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(administrador);
        session.getTransaction().commit();
        session.close();
    }

}
