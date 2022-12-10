package pooco.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexionJPA {
    private static ConexionJPA instance;
    private EntityManagerFactory entityManagerFactory;

    private ConexionJPA(){
        entityManagerFactory = Persistence.createEntityManagerFactory("PooCo-OnlineStorePU");
    }
    
    public static synchronized ConexionJPA getInstance(){
        return instance == null ? instance = new ConexionJPA() : instance;
    }

    public void shutdown()
    {
        if (entityManagerFactory!=null)
        {
            entityManagerFactory.close();
        }

    }
    
    public EntityManager getEntityManager() {
        EntityManagerFactory emf = getEntityManagerFactory();
        return emf.createEntityManager();
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}