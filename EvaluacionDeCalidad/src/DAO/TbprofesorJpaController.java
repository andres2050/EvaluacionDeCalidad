/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Tbgrupoxasignaturaxprofesor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Tbprofesor;

/**
 *
 * @author Andres
 */
public class TbprofesorJpaController implements Serializable {

    public TbprofesorJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbprofesor tbprofesor) throws PreexistingEntityException, Exception {
        if (tbprofesor.getTbgrupoxasignaturaxprofesorCollection() == null) {
            tbprofesor.setTbgrupoxasignaturaxprofesorCollection(new ArrayList<Tbgrupoxasignaturaxprofesor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tbgrupoxasignaturaxprofesor> attachedTbgrupoxasignaturaxprofesorCollection = new ArrayList<Tbgrupoxasignaturaxprofesor>();
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach : tbprofesor.getTbgrupoxasignaturaxprofesorCollection()) {
                tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach = em.getReference(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach.getClass(), tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach.getId());
                attachedTbgrupoxasignaturaxprofesorCollection.add(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach);
            }
            tbprofesor.setTbgrupoxasignaturaxprofesorCollection(attachedTbgrupoxasignaturaxprofesorCollection);
            em.persist(tbprofesor);
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor : tbprofesor.getTbgrupoxasignaturaxprofesorCollection()) {
                Tbprofesor oldCedulaOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor = tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor.getCedula();
                tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor.setCedula(tbprofesor);
                tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor = em.merge(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor);
                if (oldCedulaOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor != null) {
                    oldCedulaOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor);
                    oldCedulaOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor = em.merge(oldCedulaOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTbprofesor(tbprofesor.getCedula()) != null) {
                throw new PreexistingEntityException("Tbprofesor " + tbprofesor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbprofesor tbprofesor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbprofesor persistentTbprofesor = em.find(Tbprofesor.class, tbprofesor.getCedula());
            Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollectionOld = persistentTbprofesor.getTbgrupoxasignaturaxprofesorCollection();
            Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollectionNew = tbprofesor.getTbgrupoxasignaturaxprofesorCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionOldTbgrupoxasignaturaxprofesor : tbgrupoxasignaturaxprofesorCollectionOld) {
                if (!tbgrupoxasignaturaxprofesorCollectionNew.contains(tbgrupoxasignaturaxprofesorCollectionOldTbgrupoxasignaturaxprofesor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbgrupoxasignaturaxprofesor " + tbgrupoxasignaturaxprofesorCollectionOldTbgrupoxasignaturaxprofesor + " since its cedula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tbgrupoxasignaturaxprofesor> attachedTbgrupoxasignaturaxprofesorCollectionNew = new ArrayList<Tbgrupoxasignaturaxprofesor>();
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesorToAttach : tbgrupoxasignaturaxprofesorCollectionNew) {
                tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesorToAttach = em.getReference(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesorToAttach.getClass(), tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesorToAttach.getId());
                attachedTbgrupoxasignaturaxprofesorCollectionNew.add(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesorToAttach);
            }
            tbgrupoxasignaturaxprofesorCollectionNew = attachedTbgrupoxasignaturaxprofesorCollectionNew;
            tbprofesor.setTbgrupoxasignaturaxprofesorCollection(tbgrupoxasignaturaxprofesorCollectionNew);
            tbprofesor = em.merge(tbprofesor);
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor : tbgrupoxasignaturaxprofesorCollectionNew) {
                if (!tbgrupoxasignaturaxprofesorCollectionOld.contains(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor)) {
                    Tbprofesor oldCedulaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor = tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.getCedula();
                    tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.setCedula(tbprofesor);
                    tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor = em.merge(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor);
                    if (oldCedulaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor != null && !oldCedulaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.equals(tbprofesor)) {
                        oldCedulaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor);
                        oldCedulaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor = em.merge(oldCedulaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = tbprofesor.getCedula();
                if (findTbprofesor(id) == null) {
                    throw new NonexistentEntityException("The tbprofesor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbprofesor tbprofesor;
            try {
                tbprofesor = em.getReference(Tbprofesor.class, id);
                tbprofesor.getCedula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbprofesor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollectionOrphanCheck = tbprofesor.getTbgrupoxasignaturaxprofesorCollection();
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionOrphanCheckTbgrupoxasignaturaxprofesor : tbgrupoxasignaturaxprofesorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbprofesor (" + tbprofesor + ") cannot be destroyed since the Tbgrupoxasignaturaxprofesor " + tbgrupoxasignaturaxprofesorCollectionOrphanCheckTbgrupoxasignaturaxprofesor + " in its tbgrupoxasignaturaxprofesorCollection field has a non-nullable cedula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tbprofesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbprofesor> findTbprofesorEntities() {
        return findTbprofesorEntities(true, -1, -1);
    }

    public List<Tbprofesor> findTbprofesorEntities(int maxResults, int firstResult) {
        return findTbprofesorEntities(false, maxResults, firstResult);
    }

    private List<Tbprofesor> findTbprofesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbprofesor.class));
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

    public Tbprofesor findTbprofesor(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbprofesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbprofesorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbprofesor> rt = cq.from(Tbprofesor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
