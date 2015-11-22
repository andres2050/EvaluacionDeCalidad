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
import modelo.Tbencuestaxasignatura;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Tbbloquesxencuesta;
import modelo.Tbencuesta;

/**
 *
 * @author Andres
 */
public class TbencuestaJpaController implements Serializable {

    public TbencuestaJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbencuesta tbencuesta) {
        if (tbencuesta.getTbencuestaxasignaturaCollection() == null) {
            tbencuesta.setTbencuestaxasignaturaCollection(new ArrayList<Tbencuestaxasignatura>());
        }
        if (tbencuesta.getTbbloquesxencuestaCollection() == null) {
            tbencuesta.setTbbloquesxencuestaCollection(new ArrayList<Tbbloquesxencuesta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tbencuestaxasignatura> attachedTbencuestaxasignaturaCollection = new ArrayList<Tbencuestaxasignatura>();
            for (Tbencuestaxasignatura tbencuestaxasignaturaCollectionTbencuestaxasignaturaToAttach : tbencuesta.getTbencuestaxasignaturaCollection()) {
                tbencuestaxasignaturaCollectionTbencuestaxasignaturaToAttach = em.getReference(tbencuestaxasignaturaCollectionTbencuestaxasignaturaToAttach.getClass(), tbencuestaxasignaturaCollectionTbencuestaxasignaturaToAttach.getId());
                attachedTbencuestaxasignaturaCollection.add(tbencuestaxasignaturaCollectionTbencuestaxasignaturaToAttach);
            }
            tbencuesta.setTbencuestaxasignaturaCollection(attachedTbencuestaxasignaturaCollection);
            Collection<Tbbloquesxencuesta> attachedTbbloquesxencuestaCollection = new ArrayList<Tbbloquesxencuesta>();
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionTbbloquesxencuestaToAttach : tbencuesta.getTbbloquesxencuestaCollection()) {
                tbbloquesxencuestaCollectionTbbloquesxencuestaToAttach = em.getReference(tbbloquesxencuestaCollectionTbbloquesxencuestaToAttach.getClass(), tbbloquesxencuestaCollectionTbbloquesxencuestaToAttach.getId());
                attachedTbbloquesxencuestaCollection.add(tbbloquesxencuestaCollectionTbbloquesxencuestaToAttach);
            }
            tbencuesta.setTbbloquesxencuestaCollection(attachedTbbloquesxencuestaCollection);
            em.persist(tbencuesta);
            for (Tbencuestaxasignatura tbencuestaxasignaturaCollectionTbencuestaxasignatura : tbencuesta.getTbencuestaxasignaturaCollection()) {
                Tbencuesta oldIdencuestaOfTbencuestaxasignaturaCollectionTbencuestaxasignatura = tbencuestaxasignaturaCollectionTbencuestaxasignatura.getIdencuesta();
                tbencuestaxasignaturaCollectionTbencuestaxasignatura.setIdencuesta(tbencuesta);
                tbencuestaxasignaturaCollectionTbencuestaxasignatura = em.merge(tbencuestaxasignaturaCollectionTbencuestaxasignatura);
                if (oldIdencuestaOfTbencuestaxasignaturaCollectionTbencuestaxasignatura != null) {
                    oldIdencuestaOfTbencuestaxasignaturaCollectionTbencuestaxasignatura.getTbencuestaxasignaturaCollection().remove(tbencuestaxasignaturaCollectionTbencuestaxasignatura);
                    oldIdencuestaOfTbencuestaxasignaturaCollectionTbencuestaxasignatura = em.merge(oldIdencuestaOfTbencuestaxasignaturaCollectionTbencuestaxasignatura);
                }
            }
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionTbbloquesxencuesta : tbencuesta.getTbbloquesxencuestaCollection()) {
                Tbencuesta oldIdencuestaOfTbbloquesxencuestaCollectionTbbloquesxencuesta = tbbloquesxencuestaCollectionTbbloquesxencuesta.getIdencuesta();
                tbbloquesxencuestaCollectionTbbloquesxencuesta.setIdencuesta(tbencuesta);
                tbbloquesxencuestaCollectionTbbloquesxencuesta = em.merge(tbbloquesxencuestaCollectionTbbloquesxencuesta);
                if (oldIdencuestaOfTbbloquesxencuestaCollectionTbbloquesxencuesta != null) {
                    oldIdencuestaOfTbbloquesxencuestaCollectionTbbloquesxencuesta.getTbbloquesxencuestaCollection().remove(tbbloquesxencuestaCollectionTbbloquesxencuesta);
                    oldIdencuestaOfTbbloquesxencuestaCollectionTbbloquesxencuesta = em.merge(oldIdencuestaOfTbbloquesxencuestaCollectionTbbloquesxencuesta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbencuesta tbencuesta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbencuesta persistentTbencuesta = em.find(Tbencuesta.class, tbencuesta.getId());
            Collection<Tbencuestaxasignatura> tbencuestaxasignaturaCollectionOld = persistentTbencuesta.getTbencuestaxasignaturaCollection();
            Collection<Tbencuestaxasignatura> tbencuestaxasignaturaCollectionNew = tbencuesta.getTbencuestaxasignaturaCollection();
            Collection<Tbbloquesxencuesta> tbbloquesxencuestaCollectionOld = persistentTbencuesta.getTbbloquesxencuestaCollection();
            Collection<Tbbloquesxencuesta> tbbloquesxencuestaCollectionNew = tbencuesta.getTbbloquesxencuestaCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbencuestaxasignatura tbencuestaxasignaturaCollectionOldTbencuestaxasignatura : tbencuestaxasignaturaCollectionOld) {
                if (!tbencuestaxasignaturaCollectionNew.contains(tbencuestaxasignaturaCollectionOldTbencuestaxasignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbencuestaxasignatura " + tbencuestaxasignaturaCollectionOldTbencuestaxasignatura + " since its idencuesta field is not nullable.");
                }
            }
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionOldTbbloquesxencuesta : tbbloquesxencuestaCollectionOld) {
                if (!tbbloquesxencuestaCollectionNew.contains(tbbloquesxencuestaCollectionOldTbbloquesxencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbbloquesxencuesta " + tbbloquesxencuestaCollectionOldTbbloquesxencuesta + " since its idencuesta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Tbencuestaxasignatura> attachedTbencuestaxasignaturaCollectionNew = new ArrayList<Tbencuestaxasignatura>();
            for (Tbencuestaxasignatura tbencuestaxasignaturaCollectionNewTbencuestaxasignaturaToAttach : tbencuestaxasignaturaCollectionNew) {
                tbencuestaxasignaturaCollectionNewTbencuestaxasignaturaToAttach = em.getReference(tbencuestaxasignaturaCollectionNewTbencuestaxasignaturaToAttach.getClass(), tbencuestaxasignaturaCollectionNewTbencuestaxasignaturaToAttach.getId());
                attachedTbencuestaxasignaturaCollectionNew.add(tbencuestaxasignaturaCollectionNewTbencuestaxasignaturaToAttach);
            }
            tbencuestaxasignaturaCollectionNew = attachedTbencuestaxasignaturaCollectionNew;
            tbencuesta.setTbencuestaxasignaturaCollection(tbencuestaxasignaturaCollectionNew);
            Collection<Tbbloquesxencuesta> attachedTbbloquesxencuestaCollectionNew = new ArrayList<Tbbloquesxencuesta>();
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionNewTbbloquesxencuestaToAttach : tbbloquesxencuestaCollectionNew) {
                tbbloquesxencuestaCollectionNewTbbloquesxencuestaToAttach = em.getReference(tbbloquesxencuestaCollectionNewTbbloquesxencuestaToAttach.getClass(), tbbloquesxencuestaCollectionNewTbbloquesxencuestaToAttach.getId());
                attachedTbbloquesxencuestaCollectionNew.add(tbbloquesxencuestaCollectionNewTbbloquesxencuestaToAttach);
            }
            tbbloquesxencuestaCollectionNew = attachedTbbloquesxencuestaCollectionNew;
            tbencuesta.setTbbloquesxencuestaCollection(tbbloquesxencuestaCollectionNew);
            tbencuesta = em.merge(tbencuesta);
            for (Tbencuestaxasignatura tbencuestaxasignaturaCollectionNewTbencuestaxasignatura : tbencuestaxasignaturaCollectionNew) {
                if (!tbencuestaxasignaturaCollectionOld.contains(tbencuestaxasignaturaCollectionNewTbencuestaxasignatura)) {
                    Tbencuesta oldIdencuestaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura = tbencuestaxasignaturaCollectionNewTbencuestaxasignatura.getIdencuesta();
                    tbencuestaxasignaturaCollectionNewTbencuestaxasignatura.setIdencuesta(tbencuesta);
                    tbencuestaxasignaturaCollectionNewTbencuestaxasignatura = em.merge(tbencuestaxasignaturaCollectionNewTbencuestaxasignatura);
                    if (oldIdencuestaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura != null && !oldIdencuestaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura.equals(tbencuesta)) {
                        oldIdencuestaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura.getTbencuestaxasignaturaCollection().remove(tbencuestaxasignaturaCollectionNewTbencuestaxasignatura);
                        oldIdencuestaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura = em.merge(oldIdencuestaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura);
                    }
                }
            }
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionNewTbbloquesxencuesta : tbbloquesxencuestaCollectionNew) {
                if (!tbbloquesxencuestaCollectionOld.contains(tbbloquesxencuestaCollectionNewTbbloquesxencuesta)) {
                    Tbencuesta oldIdencuestaOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta = tbbloquesxencuestaCollectionNewTbbloquesxencuesta.getIdencuesta();
                    tbbloquesxencuestaCollectionNewTbbloquesxencuesta.setIdencuesta(tbencuesta);
                    tbbloquesxencuestaCollectionNewTbbloquesxencuesta = em.merge(tbbloquesxencuestaCollectionNewTbbloquesxencuesta);
                    if (oldIdencuestaOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta != null && !oldIdencuestaOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta.equals(tbencuesta)) {
                        oldIdencuestaOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta.getTbbloquesxencuestaCollection().remove(tbbloquesxencuestaCollectionNewTbbloquesxencuesta);
                        oldIdencuestaOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta = em.merge(oldIdencuestaOfTbbloquesxencuestaCollectionNewTbbloquesxencuesta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbencuesta.getId();
                if (findTbencuesta(id) == null) {
                    throw new NonexistentEntityException("The tbencuesta with id " + id + " no longer exists.");
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
            Tbencuesta tbencuesta;
            try {
                tbencuesta = em.getReference(Tbencuesta.class, id);
                tbencuesta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbencuesta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbencuestaxasignatura> tbencuestaxasignaturaCollectionOrphanCheck = tbencuesta.getTbencuestaxasignaturaCollection();
            for (Tbencuestaxasignatura tbencuestaxasignaturaCollectionOrphanCheckTbencuestaxasignatura : tbencuestaxasignaturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbencuesta (" + tbencuesta + ") cannot be destroyed since the Tbencuestaxasignatura " + tbencuestaxasignaturaCollectionOrphanCheckTbencuestaxasignatura + " in its tbencuestaxasignaturaCollection field has a non-nullable idencuesta field.");
            }
            Collection<Tbbloquesxencuesta> tbbloquesxencuestaCollectionOrphanCheck = tbencuesta.getTbbloquesxencuestaCollection();
            for (Tbbloquesxencuesta tbbloquesxencuestaCollectionOrphanCheckTbbloquesxencuesta : tbbloquesxencuestaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbencuesta (" + tbencuesta + ") cannot be destroyed since the Tbbloquesxencuesta " + tbbloquesxencuestaCollectionOrphanCheckTbbloquesxencuesta + " in its tbbloquesxencuestaCollection field has a non-nullable idencuesta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tbencuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbencuesta> findTbencuestaEntities() {
        return findTbencuestaEntities(true, -1, -1);
    }

    public List<Tbencuesta> findTbencuestaEntities(int maxResults, int firstResult) {
        return findTbencuestaEntities(false, maxResults, firstResult);
    }

    private List<Tbencuesta> findTbencuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbencuesta.class));
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

    public Tbencuesta findTbencuesta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbencuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbencuestaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbencuesta> rt = cq.from(Tbencuesta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
