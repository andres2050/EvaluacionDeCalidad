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
import modelo.Tbpregunta;

/**
 *
 * @author Andres
 */
public class TbpreguntaJpaController implements Serializable {

    public TbpreguntaJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbpregunta tbpregunta) {
        if (tbpregunta.getTbbloquesxpreguntaCollection() == null) {
            tbpregunta.setTbbloquesxpreguntaCollection(new ArrayList<Tbbloquesxpregunta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tbbloquesxpregunta> attachedTbbloquesxpreguntaCollection = new ArrayList<Tbbloquesxpregunta>();
            for (Tbbloquesxpregunta tbbloquesxpreguntaCollectionTbbloquesxpreguntaToAttach : tbpregunta.getTbbloquesxpreguntaCollection()) {
                tbbloquesxpreguntaCollectionTbbloquesxpreguntaToAttach = em.getReference(tbbloquesxpreguntaCollectionTbbloquesxpreguntaToAttach.getClass(), tbbloquesxpreguntaCollectionTbbloquesxpreguntaToAttach.getId());
                attachedTbbloquesxpreguntaCollection.add(tbbloquesxpreguntaCollectionTbbloquesxpreguntaToAttach);
            }
            tbpregunta.setTbbloquesxpreguntaCollection(attachedTbbloquesxpreguntaCollection);
            em.persist(tbpregunta);
            for (Tbbloquesxpregunta tbbloquesxpreguntaCollectionTbbloquesxpregunta : tbpregunta.getTbbloquesxpreguntaCollection()) {
                Tbpregunta oldIdpreguntaOfTbbloquesxpreguntaCollectionTbbloquesxpregunta = tbbloquesxpreguntaCollectionTbbloquesxpregunta.getIdpregunta();
                tbbloquesxpreguntaCollectionTbbloquesxpregunta.setIdpregunta(tbpregunta);
                tbbloquesxpreguntaCollectionTbbloquesxpregunta = em.merge(tbbloquesxpreguntaCollectionTbbloquesxpregunta);
                if (oldIdpreguntaOfTbbloquesxpreguntaCollectionTbbloquesxpregunta != null) {
                    oldIdpreguntaOfTbbloquesxpreguntaCollectionTbbloquesxpregunta.getTbbloquesxpreguntaCollection().remove(tbbloquesxpreguntaCollectionTbbloquesxpregunta);
                    oldIdpreguntaOfTbbloquesxpreguntaCollectionTbbloquesxpregunta = em.merge(oldIdpreguntaOfTbbloquesxpreguntaCollectionTbbloquesxpregunta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbpregunta tbpregunta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbpregunta persistentTbpregunta = em.find(Tbpregunta.class, tbpregunta.getId());
            Collection<Tbbloquesxpregunta> tbbloquesxpreguntaCollectionOld = persistentTbpregunta.getTbbloquesxpreguntaCollection();
            Collection<Tbbloquesxpregunta> tbbloquesxpreguntaCollectionNew = tbpregunta.getTbbloquesxpreguntaCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbbloquesxpregunta tbbloquesxpreguntaCollectionOldTbbloquesxpregunta : tbbloquesxpreguntaCollectionOld) {
                if (!tbbloquesxpreguntaCollectionNew.contains(tbbloquesxpreguntaCollectionOldTbbloquesxpregunta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbbloquesxpregunta " + tbbloquesxpreguntaCollectionOldTbbloquesxpregunta + " since its idpregunta field is not nullable.");
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
            tbpregunta.setTbbloquesxpreguntaCollection(tbbloquesxpreguntaCollectionNew);
            tbpregunta = em.merge(tbpregunta);
            for (Tbbloquesxpregunta tbbloquesxpreguntaCollectionNewTbbloquesxpregunta : tbbloquesxpreguntaCollectionNew) {
                if (!tbbloquesxpreguntaCollectionOld.contains(tbbloquesxpreguntaCollectionNewTbbloquesxpregunta)) {
                    Tbpregunta oldIdpreguntaOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta = tbbloquesxpreguntaCollectionNewTbbloquesxpregunta.getIdpregunta();
                    tbbloquesxpreguntaCollectionNewTbbloquesxpregunta.setIdpregunta(tbpregunta);
                    tbbloquesxpreguntaCollectionNewTbbloquesxpregunta = em.merge(tbbloquesxpreguntaCollectionNewTbbloquesxpregunta);
                    if (oldIdpreguntaOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta != null && !oldIdpreguntaOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta.equals(tbpregunta)) {
                        oldIdpreguntaOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta.getTbbloquesxpreguntaCollection().remove(tbbloquesxpreguntaCollectionNewTbbloquesxpregunta);
                        oldIdpreguntaOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta = em.merge(oldIdpreguntaOfTbbloquesxpreguntaCollectionNewTbbloquesxpregunta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbpregunta.getId();
                if (findTbpregunta(id) == null) {
                    throw new NonexistentEntityException("The tbpregunta with id " + id + " no longer exists.");
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
            Tbpregunta tbpregunta;
            try {
                tbpregunta = em.getReference(Tbpregunta.class, id);
                tbpregunta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbpregunta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbbloquesxpregunta> tbbloquesxpreguntaCollectionOrphanCheck = tbpregunta.getTbbloquesxpreguntaCollection();
            for (Tbbloquesxpregunta tbbloquesxpreguntaCollectionOrphanCheckTbbloquesxpregunta : tbbloquesxpreguntaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbpregunta (" + tbpregunta + ") cannot be destroyed since the Tbbloquesxpregunta " + tbbloquesxpreguntaCollectionOrphanCheckTbbloquesxpregunta + " in its tbbloquesxpreguntaCollection field has a non-nullable idpregunta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tbpregunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbpregunta> findTbpreguntaEntities() {
        return findTbpreguntaEntities(true, -1, -1);
    }

    public List<Tbpregunta> findTbpreguntaEntities(int maxResults, int firstResult) {
        return findTbpreguntaEntities(false, maxResults, firstResult);
    }

    private List<Tbpregunta> findTbpreguntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbpregunta.class));
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

    public Tbpregunta findTbpregunta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbpregunta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbpreguntaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbpregunta> rt = cq.from(Tbpregunta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
