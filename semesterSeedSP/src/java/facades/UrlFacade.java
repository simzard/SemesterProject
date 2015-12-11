package facades;

import entity.Url;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class UrlFacade {

    private EntityManagerFactory emf;
    //private Map<Integer, String> urls = new HashMap();

    //private static int counterId = 0;
    public UrlFacade(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int addUrl(String httpTestUrl) {
        EntityManager em = getEntityManager();

        Url url = new Url(httpTestUrl);

        try {
            em.getTransaction().begin();
            em.persist(url);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        //urls.put(counterId, httpTestUrl);
        return url.getId();
    }

    public String getUrl(int id) {
        EntityManager em = getEntityManager();

        Url url = em.find(Url.class, id);

        return (url != null) ? url.getUrlString() : null;
    }

    public void deleteUrl(int id) {
        EntityManager em = getEntityManager();

        Url url = em.find(Url.class, id);

        try {
            em.getTransaction().begin();
            em.remove(url);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    public List<Url> getUrls() {
        EntityManager em = getEntityManager();

        List<Url> urls;

        try {
            TypedQuery query = em.createQuery("SELECT u FROM Url u", Url.class);
            urls = query.getResultList();
        } finally {
            em.close();
        }
        return urls;
    }

}
