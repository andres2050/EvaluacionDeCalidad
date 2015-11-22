/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Tbusuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Tbtipo;

/**
 *
 * @author Andres
 */
public class TbtipoJpaController implements Serializable {

    public TbtipoJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbtipo tbtipo) throws PreexistingEntityException, Exception {
        if (tbtipo.getTbusuarioCollection() == null) {
            tbtipo.setTbusuarioCollection(new ArrayList<Tbusuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tbusuario> attachedTbusuarioCollection = new ArrayList<Tbusuario>();
            for (Tbusuario tbusuarioCollectionTbusuarioToAttach : tbtipo.getTbusuarioCollection()) {
                tbusuarioCollectionTbusuarioToAttach = em.getReference(tbusuarioCollectionTbusuarioToAttach.getClass(), tbusuarioCollectionTbusuarioToAttach.getCodigo());
                attachedTbusuarioCollection.add(tbusuarioCollectionTbusuarioToAttach);
            }
            tbtipo.setTbusuarioCollection(attachedTbusuarioCollection);
            em.persist(tbtipo);
            for (Tbusuario tbusuarioCollectionTbusuario : tbtipo.getTbusuarioCollection()) {
                Tbtipo oldIdtipoOfTbusuarioCollectionTbusuario = tbusuarioCollectionTbusuario.getIdtipo();
                tbusuarioCollectionTbusuario.setIdtipo(tbtipo);
                tbusuarioCollectionTbusuario = em.merge(tbusuarioCollectionTbusuario);
                if (oldIdtipoOfTbusuarioCollectionTbusuario != null) {
                    oldIdtipoOfTbusuarioCollectionTbusuario.getTbusuarioCollection().remove(tbusuarioCollectionTbusuario);
                    oldIdtipoOfTbusuarioCollectionTbusuario = em.merge(oldIdtipoOfTbusuarioCollectionTbusuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTbtipo(tbtipo.getId()) != null) {
                throw new PreexistingEntityException("Tbtipo " + tbtipo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbtipo tbtipo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbtipo persistentTbtipo = em.find(Tbtipo.class, tbtipo.getId());
            Collection<Tbusuario> tbusuarioCollectionOld = persistentTbtipo.getTbusuarioCollection();
            Collection<Tbusuario> tbusuarioCollectionNew = tbtipo.getTbusuarioCollection();
            Collection<Tbusuario> attachedTbusuarioCollectionNew = new ArrayList<Tbusuario>();
            for (Tbusuario tbusuarioCollectionNewTbusuarioToAttach : tbusuarioCollectionNew) {
                tbusuarioCollectionNewTbusuarioToAttach = em.getReference(tbusuarioCollectionNewTbusuarioToAttach.getClass(), tbusuarioCollectionNewTbusuarioToAttach.getCodigo());
                attachedTbusuarioCollectionNew.add(tbusuarioCollectionNewTbusuarioToAttach);
            }
            tbusuarioCollectionNew = attachedTbusuarioCollectionNew;
            tbtipo.setTbusuarioCollection(tbusuarioCollectionNew);
            tbtipo = em.merge(tbtipo);
            for (Tbusuario tbusuarioCollectionOldTbusuario : tbusuarioCollectionOld) {
                if (!tbusuarioCollectionNew.contains(tbusuarioCollectionOldTbusuario)) {
                    tbusuarioCollectionOldTbusuario.setIdtipo(null);
                    tbusuarioCollectionOldTbusuario = em.merge(tbusuarioCollectionOldTbusuario);
                }
            }
            for (Tbusuario tbusuarioCollectionNewTbusuario : tbusuarioCollectionNew) {
                if (!tbusuarioCollectionOld.contains(tbusuarioCollectionNewTbusuario)) {
                    Tbtipo oldIdtipoOfTbusuarioCollectionNewTbusuario = tbusuarioCollectionNewTbusuario.getIdtipo();
                    tbusuarioCollectionNewTbusuario.setIdtipo(tbtipo);
                    tbusuarioCollectionNewTbusuario = em.merge(tbusuarioCollectionNewTbusuario);
                    if (oldIdtipoOfTbusuarioCollectionNewTbusuario != null && !oldIdtipoOfTbusuarioCollectionNewTbusuario.equals(tbtipo)) {
                        oldIdtipoOfTbusuarioCollectionNewTbusuario.getTbusuarioCollection().remove(tbusuarioCollectionNewTbusuario);
                        oldIdtipoOfTbusuarioCollectionNewTbusuario = em.merge(oldIdtipoOfTbusuarioCollectionNewTbusuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbtipo.getId();
                if (findTbtipo(id) == null) {
                    throw new NonexistentEntityException("The tbtipo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbtipo tbtipo;
            try {
                tbtipo = em.getReference(Tbtipo.class, id);
                tbtipo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbtipo with id " + id + " no longer exists.", enfe);
            }
            Collection<Tbusuario> tbusuarioCollection = tbtipo.getTbusuarioCollection();
            for (Tbusuario tbusuarioCollectionTbusuario : tbusuarioCollection) {
                tbusuarioCollectionTbusuario.setIdtipo(null);
                tbusuarioCollectionTbusuario = em.merge(tbusuarioCollectionTbusuario);
            }
            em.remove(tbtipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbtipo> findTbtipoEntities() {
        return findTbtipoEntities(true, -1, -1);
    }

    public List<Tbtipo> findTbtipoEntities(int maxResults, int firstResult) {
        return findTbtipoEntities(false, maxResults, firstResult);
    }

    private List<Tbtipo> findTbtipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbtipo.class));
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

    public Tbtipo findTbtipo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbtipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbtipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbtipo> rt = cq.from(Tbtipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
