/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Tbbloquesxpregunta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Tbbloque;
import modelo.Tbbloquesxencuesta;

/**
 *
 * @author Andres
 */
public class TbbloqueJpaController implements Serializable {

    public TbbloqueJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbbloque tbbloque) {
        if (tbbloque.getTbbloquesxpreguntaCollection() == null) {
            tbbloque.setTbbloquesxpreguntaCollection(new ArrayList<Tbbloquesxpregunta>());
        }
        if (tbbloque.getTbbloquesxencuestaCollection() == null) {
            tbbloque.setTbbloquesxencuestaCollection(new ArrayList<Tbbloquesxencuesta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tbbloquesxpregunta> attachedTbbloquesxpreguntaCollection = new ArrayList<Tbbloquesxpregunta>();
            for (Tbbloquesxpregunta tbbloquesxpreguntaCollectionTbbloquesxpreguntaToAttach : tbbloque.getTbbloquesxpreguntaCollection()) {
                tbbloquesxpreguntaCollectionTbbloquesxpreguntaToAttach = em.getReference(tbbloquesxpreguntaCollectionTbbloquesxpreguntaToAttach.getClass(), tbbloquesxpreguntaCollectionTbbloquesxpreguntaToAttach.getId());
                attachedTbbloquesxpreguntaCollection.add(tbbloquesxpreguntaCollectionTbbloquesxpreguntaToAttach);
            }
            tbbloque.setTbbloquesxpreguntaCollection(attachedTbbloquesxpreguntaCollection);
            Collection<Tbbloquesxencuesta> attachedTbbloquesxencuestaCollection = new ArrayList<Tbbloquesxencuesta>();
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionTbbloquesxencuestaToAttach : tbbloque.getTbbloquesxencuestaCollection()) {
                tbbloquesxencuestaCollectionTbbloquesxencuestaToAttach = em.getReference(tbbloquesxencuestaCollectionTbbloquesxencuestaToAttach.getClass(), tbbloquesxencuestaCollectionTbbloquesxencuestaToAttach.getId());
                attachedTbbloquesxencuestaCollection.add(tbbloquesxencuestaCollectionTbbloquesxencuestaToAttach);
            }
            tbbloque.setTbbloquesxencuestaCollection(attachedTbbloquesxencuestaCollection);
            em.persist(tbbloque);
            for (Tbbloquesxpregunta tbbloquesxpreguntaCollectionTbbloquesxpregunta : tbbloque.getTbbloquesxpreguntaCollection()) {
                Tbbloque oldIdbloqueOfTbbloquesxpreguntaCollectionTbbloquesxpregunta = tbbloquesxpreguntaCollectionTbbloquesxpregunta.getIdbloque();
                tbbloquesxpreguntaCollectionTbbloquesxpregunta.setIdbloque(tbbloque);
                tbbloquesxpreguntaCollectionTbbloquesxpregunta = em.merge(tbbloquesxpreguntaCollectionTbbloquesxpregunta);
                if (oldIdbloqueOfTbbloquesxpreguntaCollectionTbbloquesxpregunta != null) {
                    oldIdbloqueOfTbbloquesxpreguntaCollectionTbbloquesxpregunta.getTbbloquesxpreguntaCollection().remove(tbbloquesxpreguntaCollectionTbbloquesxpregunta);
                    oldIdbloqueOfTbbloquesxpreguntaCollectionTbbloquesxpregunta = em.merge(oldIdbloqueOfTbbloquesxpreguntaCollectionTbbloquesxpregunta);
                }
            }
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionTbbloquesxencuesta : tbbloque.getTbbloquesxencuestaCollection()) {
                Tbbloque oldIdbloqueOfTbbloquesxencuestaCollectionTbbloquesxencuesta = tbbloquesxencuestaCollectionTbbloquesxencuesta.getIdbloque();
                tbbloquesxencuestaCollectionTbbloquesxencuesta.setIdbloque(tbbloque);
                tbbloquesxencuestaCollectionTbbloquesxencuesta = em.merge(tbbloquesxencuestaCollectionTbbloquesxencuesta);
                if (oldIdbloqueOfTbbloquesxencuestaCollectionTbbloquesxencuesta != null) {
                    oldIdbloqueOfTbbloquesxencuestaCollectionTbbloquesxencuesta.getTbbloquesxencuestaCollection().remove(tbbloquesxencuestaCollectionTbbloquesxencuesta);
                    oldIdbloqueOfTbbloquesxencuestaCollectionTbbloquesxencuesta = em.merge(oldIdbloqueOfTbbloquesxencuestaCollectionTbbloquesxencuesta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbbloque tbbloque) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbbloque persistentTbbloque = em.find(Tbbloque.class, tbbloque.getId());
            Collection<Tbbloquesxpregunta> tbbloquesxpreguntaCollectionOld = persistentTbbloque.getTbbloquesxpreguntaCollection();
            Collection<Tbbloquesxpregunta> tbbloquesxpreguntaCollectionNew = tbbloque.getTbbloquesxpreguntaCollection();
            Collection<Tbbloquesxencuesta> tbbloquesxencuestaCollectionOld = persistentTbbloque.getTbbloquesxencuestaCollection();
            Collection<Tbbloquesxencuesta> tbbloquesxencuestaCollectionNew = tbbloque.getTbbloquesxencuestaCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbbloquesxpregunta tbbloquesxpreguntaCollectionOldTbbloquesxpregunta : tbbloquesxpreguntaCollectionOld) {
                if (!tbbloquesxpreguntaCollectionNew.contains(tbbloquesxpreguntaCollectionOldTbbloquesxpregunta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbbloquesxpregunta " + tbbloquesxpreguntaCollectionOldTbbloquesxpregunta + " since its idbloque field is not nullable.");
                }
            }
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionOldTbbloquesxencuesta : tbbloquesxencuestaCollectionOld) {
                if (!tbbloquesxencuestaCollectionNew.contains(tbbloquesxencuestaCollectionOldTbbloquesxencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbbloquesxencuesta " + tbbloquesxencuestaCollectionOldTbbloquesxencuesta + " since its idbloque field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tbbloquesxpregunta> attachedTbbloquesxpreguntaCollectionNew = new ArrayList<Tbbloquesxpregunta>();
            for (Tbbloquesxpregunta tbbloquesxpreguntaCollectionNewTbbloquesxpreguntaToAttach : tbbloquesxpreguntaCollectionNew) {
                tbbloquesxpreguntaCollectionNewTbbloquesxpreguntaToAttach = em.getReference(tbbloquesxpreguntaCollectionNewTbbloquesxpreguntaToAttach.getClass(), tbbloquesxpreguntaCollectionNewTbbloquesxpreguntaToAttach.getId());
                attachedTbbloquesxpreguntaCollectionNew.add(tbbloquesxpreguntaCollectionNewTbbloquesxpreguntaToAttach);
            }
            tbbloquesxpreguntaCollectionNew = attachedTbbloquesxpreguntaCollectionNew;
            tbbloque.setTbbloquesxpreguntaCollection(tbbloquesxpreguntaCollectionNew);
            Collection<Tbbloquesxencuesta> attachedTbbloquesxencuestaCollectionNew = new ArrayList<Tbbloquesxencuesta>();
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionNewTbbloquesxencuestaToAttach : tbbloquesxencuestaCollectionNew) {
                tbbloquesxencuestaCollectionNewTbbloquesxencuestaToAttach = em.getReference(tbbloquesxencuestaCollectionNewTbbloquesxencuestaToAttach.getClass(), tbbloquesxencuestaCollectionNewTbbloquesxencuestaToAttach.getId());
                attachedTbbloquesxencuestaCollectionNew.add(tbbloquesxencuestaCollectionNewTbbloquesxencuestaToAttach);
            }
            tbbloquesxencuestaCollectionNew = attachedTbbloquesxencuestaCollectionNew;
            tbbloque.setTbbloquesxencuestaCollection(tbbloquesxencuestaCollectionNew);
            tbbloque = em.merge(tbbloque);
            for (Tbbloquesxpregunta tbbloquesxpreguntaCollectionNewTbbloquesxpregunta : tbbloquesxpreguntaCollectionNew) {
                if (!tbbloquesxpreguntaCollectionOld.contains(tbbloquesxpreguntaCollectionNewTbbloquesxpregunta)) {
                    Tbbloque oldIdbloqueOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta = tbbloquesxpreguntaCollectionNewTbbloquesxpregunta.getIdbloque();
                    tbbloquesxpreguntaCollectionNewTbbloquesxpregunta.setIdbloque(tbbloque);
                    tbbloquesxpreguntaCollectionNewTbbloquesxpregunta = em.merge(tbbloquesxpreguntaCollectionNewTbbloquesxpregunta);
                    if (oldIdbloqueOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta != null && !oldIdbloqueOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta.equals(tbbloque)) {
                        oldIdbloqueOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta.getTbbloquesxpreguntaCollection().remove(tbbloquesxpreguntaCollectionNewTbbloquesxpregunta);
                        oldIdbloqueOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta = em.merge(oldIdbloqueOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta);
                    }
                }
            }
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionNewTbbloquesxencuesta : tbbloquesxencuestaCollectionNew) {
                if (!tbbloquesxencuestaCollectionOld.contains(tbbloquesxencuestaCollectionNewTbbloquesxencuesta)) {
                    Tbbloque oldIdbloqueOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta = tbbloquesxencuestaCollectionNewTbbloquesxencuesta.getIdbloque();
                    tbbloquesxencuestaCollectionNewTbbloquesxencuesta.setIdbloque(tbbloque);
                    tbbloquesxencuestaCollectionNewTbbloquesxencuesta = em.merge(tbbloquesxencuestaCollectionNewTbbloquesxencuesta);
                    if (oldIdbloqueOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta != null && !oldIdbloqueOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta.equals(tbbloque)) {
                        oldIdbloqueOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta.getTbbloquesxencuestaCollection().remove(tbbloquesxencuestaCollectionNewTbbloquesxencuesta);
                        oldIdbloqueOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta = em.merge(oldIdbloqueOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbbloque.getId();
                if (findTbbloque(id) == null) {
                    throw new NonexistentEntityException("The tbbloque with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbbloque tbbloque;
            try {
                tbbloque = em.getReference(Tbbloque.class, id);
                tbbloque.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbbloque with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbbloquesxpregunta> tbbloquesxpreguntaCollectionOrphanCheck = tbbloque.getTbbloquesxpreguntaCollection();
            for (Tbbloquesxpregunta tbbloquesxpreguntaCollectionOrphanCheckTbbloquesxpregunta : tbbloquesxpreguntaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbbloque (" + tbbloque + ") cannot be destroyed since the Tbbloquesxpregunta " + tbbloquesxpreguntaCollectionOrphanCheckTbbloquesxpregunta + " in its tbbloquesxpreguntaCollection field has a non-nullable idbloque field.");
            }
            Collection<Tbbloquesxencuesta> tbbloquesxencuestaCollectionOrphanCheck = tbbloque.getTbbloquesxencuestaCollection();
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionOrphanCheckTbbloquesxencuesta : tbbloquesxencuestaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbbloque (" + tbbloque + ") cannot be destroyed since the Tbbloquesxencuesta " + tbbloquesxencuestaCollectionOrphanCheckTbbloquesxencuesta + " in its tbbloquesxencuestaCollection field has a non-nullable idbloque field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tbbloque);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbbloque> findTbbloqueEntities() {
        return findTbbloqueEntities(true, -1, -1);
    }

    public List<Tbbloque> findTbbloqueEntities(int maxResults, int firstResult) {
        return findTbbloqueEntities(false, maxResults, firstResult);
    }

    private List<Tbbloque> findTbbloqueEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbbloque.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Tbbloque findTbbloque(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbbloque.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbbloqueCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbbloque> rt = cq.from(Tbbloque.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
