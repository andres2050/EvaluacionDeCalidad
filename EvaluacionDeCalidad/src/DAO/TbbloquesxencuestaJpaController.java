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
import modelo.Tbbloque;
import modelo.Tbencuesta;
import modelo.Tbrespuestaxpreguntasxbloquesxencuesta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Tbbloquesxencuesta;

/**
 *
 * @author Andres
 */
public class TbbloquesxencuestaJpaController implements Serializable {

    public TbbloquesxencuestaJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbbloquesxencuesta tbbloquesxencuesta) {
        if (tbbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection() == null) {
            tbbloquesxencuesta.setTbrespuestaxpreguntasxbloquesxencuestaCollection(new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbbloque idbloque = tbbloquesxencuesta.getIdbloque();
            if (idbloque != null) {
                idbloque = em.getReference(idbloque.getClass(), idbloque.getId());
                tbbloquesxencuesta.setIdbloque(idbloque);
            }
            Tbencuesta idencuesta = tbbloquesxencuesta.getIdencuesta();
            if (idencuesta != null) {
                idencuesta = em.getReference(idencuesta.getClass(), idencuesta.getId());
                tbbloquesxencuesta.setIdencuesta(idencuesta);
            }
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> attachedTbrespuestaxpreguntasxbloquesxencuestaCollection = new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach : tbbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection()) {
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach = em.getReference(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach.getClass(), tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach.getId());
                attachedTbrespuestaxpreguntasxbloquesxencuestaCollection.add(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach);
            }
            tbbloquesxencuesta.setTbrespuestaxpreguntasxbloquesxencuestaCollection(attachedTbrespuestaxpreguntasxbloquesxencuestaCollection);
            em.persist(tbbloquesxencuesta);
            if (idbloque != null) {
                idbloque.getTbbloquesxencuestaCollection().add(tbbloquesxencuesta);
                idbloque = em.merge(idbloque);
            }
            if (idencuesta != null) {
                idencuesta.getTbbloquesxencuestaCollection().add(tbbloquesxencuesta);
                idencuesta = em.merge(idencuesta);
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta : tbbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection()) {
                Tbbloquesxencuesta oldIdbloquexencuestaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexencuesta();
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.setIdbloquexencuesta(tbbloquesxencuesta);
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = em.merge(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                if (oldIdbloquexencuestaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta != null) {
                    oldIdbloquexencuestaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                    oldIdbloquexencuestaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = em.merge(oldIdbloquexencuestaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbbloquesxencuesta tbbloquesxencuesta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbbloquesxencuesta persistentTbbloquesxencuesta = em.find(Tbbloquesxencuesta.class, tbbloquesxencuesta.getId());
            Tbbloque idbloqueOld = persistentTbbloquesxencuesta.getIdbloque();
            Tbbloque idbloqueNew = tbbloquesxencuesta.getIdbloque();
            Tbencuesta idencuestaOld = persistentTbbloquesxencuesta.getIdencuesta();
            Tbencuesta idencuestaNew = tbbloquesxencuesta.getIdencuesta();
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionOld = persistentTbbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionNew = tbbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionOld) {
                if (!tbrespuestaxpreguntasxbloquesxencuestaCollectionNew.contains(tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbrespuestaxpreguntasxbloquesxencuesta " + tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta + " since its idbloquexencuesta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idbloqueNew != null) {
                idbloqueNew = em.getReference(idbloqueNew.getClass(), idbloqueNew.getId());
                tbbloquesxencuesta.setIdbloque(idbloqueNew);
            }
            if (idencuestaNew != null) {
                idencuestaNew = em.getReference(idencuestaNew.getClass(), idencuestaNew.getId());
                tbbloquesxencuesta.setIdencuesta(idencuestaNew);
            }
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew = new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach : tbrespuestaxpreguntasxbloquesxencuestaCollectionNew) {
                tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach = em.getReference(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach.getClass(), tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach.getId());
                attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew.add(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach);
            }
            tbrespuestaxpreguntasxbloquesxencuestaCollectionNew = attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew;
            tbbloquesxencuesta.setTbrespuestaxpreguntasxbloquesxencuestaCollection(tbrespuestaxpreguntasxbloquesxencuestaCollectionNew);
            tbbloquesxencuesta = em.merge(tbbloquesxencuesta);
            if (idbloqueOld != null && !idbloqueOld.equals(idbloqueNew)) {
                idbloqueOld.getTbbloquesxencuestaCollection().remove(tbbloquesxencuesta);
                idbloqueOld = em.merge(idbloqueOld);
            }
            if (idbloqueNew != null && !idbloqueNew.equals(idbloqueOld)) {
                idbloqueNew.getTbbloquesxencuestaCollection().add(tbbloquesxencuesta);
                idbloqueNew = em.merge(idbloqueNew);
            }
            if (idencuestaOld != null && !idencuestaOld.equals(idencuestaNew)) {
                idencuestaOld.getTbbloquesxencuestaCollection().remove(tbbloquesxencuesta);
                idencuestaOld = em.merge(idencuestaOld);
            }
            if (idencuestaNew != null && !idencuestaNew.equals(idencuestaOld)) {
                idencuestaNew.getTbbloquesxencuestaCollection().add(tbbloquesxencuesta);
                idencuestaNew = em.merge(idencuestaNew);
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionNew) {
                if (!tbrespuestaxpreguntasxbloquesxencuestaCollectionOld.contains(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta)) {
                    Tbbloquesxencuesta oldIdbloquexencuestaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexencuesta();
                    tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.setIdbloquexencuesta(tbbloquesxencuesta);
                    tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = em.merge(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                    if (oldIdbloquexencuestaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta != null && !oldIdbloquexencuestaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.equals(tbbloquesxencuesta)) {
                        oldIdbloquexencuestaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                        oldIdbloquexencuestaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = em.merge(oldIdbloquexencuestaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbbloquesxencuesta.getId();
                if (findTbbloquesxencuesta(id) == null) {
                    throw new NonexistentEntityException("The tbbloquesxencuesta with id " + id + " no longer exists.");
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
            Tbbloquesxencuesta tbbloquesxencuesta;
            try {
                tbbloquesxencuesta = em.getReference(Tbbloquesxencuesta.class, id);
                tbbloquesxencuesta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbbloquesxencuesta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheck = tbbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheckTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbbloquesxencuesta (" + tbbloquesxencuesta + ") cannot be destroyed since the Tbrespuestaxpreguntasxbloquesxencuesta " + tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheckTbrespuestaxpreguntasxbloquesxencuesta + " in its tbrespuestaxpreguntasxbloquesxencuestaCollection field has a non-nullable idbloquexencuesta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tbbloque idbloque = tbbloquesxencuesta.getIdbloque();
            if (idbloque != null) {
                idbloque.getTbbloquesxencuestaCollection().remove(tbbloquesxencuesta);
                idbloque = em.merge(idbloque);
            }
            Tbencuesta idencuesta = tbbloquesxencuesta.getIdencuesta();
            if (idencuesta != null) {
                idencuesta.getTbbloquesxencuestaCollection().remove(tbbloquesxencuesta);
                idencuesta = em.merge(idencuesta);
            }
            em.remove(tbbloquesxencuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbbloquesxencuesta> findTbbloquesxencuestaEntities() {
        return findTbbloquesxencuestaEntities(true, -1, -1);
    }

    public List<Tbbloquesxencuesta> findTbbloquesxencuestaEntities(int maxResults, int firstResult) {
        return findTbbloquesxencuestaEntities(false, maxResults, firstResult);
    }

    private List<Tbbloquesxencuesta> findTbbloquesxencuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbbloquesxencuesta.class));
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

    public Tbbloquesxencuesta findTbbloquesxencuesta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbbloquesxencuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbbloquesxencuestaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbbloquesxencuesta> rt = cq.from(Tbbloquesxencuesta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
