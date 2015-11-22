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
import modelo.Tbasignatura;
import modelo.Tbgrupoxasignaturaxprofesor;
import modelo.Tbusuarioxgrupoxasignatura;

/**
 *
 * @author Andres
 */
public class TbasignaturaJpaController implements Serializable {

    public TbasignaturaJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbasignatura tbasignatura) throws PreexistingEntityException, Exception {
        if (tbasignatura.getTbencuestaxasignaturaCollection() == null) {
            tbasignatura.setTbencuestaxasignaturaCollection(new ArrayList<Tbencuestaxasignatura>());
        }
        if (tbasignatura.getTbgrupoxasignaturaxprofesorCollection() == null) {
            tbasignatura.setTbgrupoxasignaturaxprofesorCollection(new ArrayList<Tbgrupoxasignaturaxprofesor>());
        }
        if (tbasignatura.getTbusuarioxgrupoxasignaturaCollection() == null) {
            tbasignatura.setTbusuarioxgrupoxasignaturaCollection(new ArrayList<Tbusuarioxgrupoxasignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tbencuestaxasignatura> attachedTbencuestaxasignaturaCollection = new ArrayList<Tbencuestaxasignatura>();
            for (Tbencuestaxasignatura tbencuestaxasignaturaCollectionTbencuestaxasignaturaToAttach : tbasignatura.getTbencuestaxasignaturaCollection()) {
                tbencuestaxasignaturaCollectionTbencuestaxasignaturaToAttach = em.getReference(tbencuestaxasignaturaCollectionTbencuestaxasignaturaToAttach.getClass(), tbencuestaxasignaturaCollectionTbencuestaxasignaturaToAttach.getId());
                attachedTbencuestaxasignaturaCollection.add(tbencuestaxasignaturaCollectionTbencuestaxasignaturaToAttach);
            }
            tbasignatura.setTbencuestaxasignaturaCollection(attachedTbencuestaxasignaturaCollection);
            Collection<Tbgrupoxasignaturaxprofesor> attachedTbgrupoxasignaturaxprofesorCollection = new ArrayList<Tbgrupoxasignaturaxprofesor>();
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach : tbasignatura.getTbgrupoxasignaturaxprofesorCollection()) {
                tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach = em.getReference(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach.getClass(), tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach.getId());
                attachedTbgrupoxasignaturaxprofesorCollection.add(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach);
            }
            tbasignatura.setTbgrupoxasignaturaxprofesorCollection(attachedTbgrupoxasignaturaxprofesorCollection);
            Collection<Tbusuarioxgrupoxasignatura> attachedTbusuarioxgrupoxasignaturaCollection = new ArrayList<Tbusuarioxgrupoxasignatura>();
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach : tbasignatura.getTbusuarioxgrupoxasignaturaCollection()) {
                tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach = em.getReference(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach.getClass(), tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach.getId());
                attachedTbusuarioxgrupoxasignaturaCollection.add(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach);
            }
            tbasignatura.setTbusuarioxgrupoxasignaturaCollection(attachedTbusuarioxgrupoxasignaturaCollection);
            em.persist(tbasignatura);
            for (Tbencuestaxasignatura tbencuestaxasignaturaCollectionTbencuestaxasignatura : tbasignatura.getTbencuestaxasignaturaCollection()) {
                Tbasignatura oldIdasignaturaOfTbencuestaxasignaturaCollectionTbencuestaxasignatura = tbencuestaxasignaturaCollectionTbencuestaxasignatura.getIdasignatura();
                tbencuestaxasignaturaCollectionTbencuestaxasignatura.setIdasignatura(tbasignatura);
                tbencuestaxasignaturaCollectionTbencuestaxasignatura = em.merge(tbencuestaxasignaturaCollectionTbencuestaxasignatura);
                if (oldIdasignaturaOfTbencuestaxasignaturaCollectionTbencuestaxasignatura != null) {
                    oldIdasignaturaOfTbencuestaxasignaturaCollectionTbencuestaxasignatura.getTbencuestaxasignaturaCollection().remove(tbencuestaxasignaturaCollectionTbencuestaxasignatura);
                    oldIdasignaturaOfTbencuestaxasignaturaCollectionTbencuestaxasignatura = em.merge(oldIdasignaturaOfTbencuestaxasignaturaCollectionTbencuestaxasignatura);
                }
            }
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor : tbasignatura.getTbgrupoxasignaturaxprofesorCollection()) {
                Tbasignatura oldIdasignaturaOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor = tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor.getIdasignatura();
                tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor.setIdasignatura(tbasignatura);
                tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor = em.merge(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor);
                if (oldIdasignaturaOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor != null) {
                    oldIdasignaturaOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor);
                    oldIdasignaturaOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor = em.merge(oldIdasignaturaOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor);
                }
            }
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura : tbasignatura.getTbusuarioxgrupoxasignaturaCollection()) {
                Tbasignatura oldCodigoasignaturaOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura = tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura.getCodigoasignatura();
                tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura.setCodigoasignatura(tbasignatura);
                tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura = em.merge(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura);
                if (oldCodigoasignaturaOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura != null) {
                    oldCodigoasignaturaOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura);
                    oldCodigoasignaturaOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura = em.merge(oldCodigoasignaturaOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTbasignatura(tbasignatura.getCodigo()) != null) {
                throw new PreexistingEntityException("Tbasignatura " + tbasignatura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbasignatura tbasignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbasignatura persistentTbasignatura = em.find(Tbasignatura.class, tbasignatura.getCodigo());
            Collection<Tbencuestaxasignatura> tbencuestaxasignaturaCollectionOld = persistentTbasignatura.getTbencuestaxasignaturaCollection();
            Collection<Tbencuestaxasignatura> tbencuestaxasignaturaCollectionNew = tbasignatura.getTbencuestaxasignaturaCollection();
            Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollectionOld = persistentTbasignatura.getTbgrupoxasignaturaxprofesorCollection();
            Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollectionNew = tbasignatura.getTbgrupoxasignaturaxprofesorCollection();
            Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollectionOld = persistentTbasignatura.getTbusuarioxgrupoxasignaturaCollection();
            Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollectionNew = tbasignatura.getTbusuarioxgrupoxasignaturaCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbencuestaxasignatura tbencuestaxasignaturaCollectionOldTbencuestaxasignatura : tbencuestaxasignaturaCollectionOld) {
                if (!tbencuestaxasignaturaCollectionNew.contains(tbencuestaxasignaturaCollectionOldTbencuestaxasignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbencuestaxasignatura " + tbencuestaxasignaturaCollectionOldTbencuestaxasignatura + " since its idasignatura field is not nullable.");
                }
            }
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionOldTbgrupoxasignaturaxprofesor : tbgrupoxasignaturaxprofesorCollectionOld) {
                if (!tbgrupoxasignaturaxprofesorCollectionNew.contains(tbgrupoxasignaturaxprofesorCollectionOldTbgrupoxasignaturaxprofesor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbgrupoxasignaturaxprofesor " + tbgrupoxasignaturaxprofesorCollectionOldTbgrupoxasignaturaxprofesor + " since its idasignatura field is not nullable.");
                }
            }
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionOldTbusuarioxgrupoxasignatura : tbusuarioxgrupoxasignaturaCollectionOld) {
                if (!tbusuarioxgrupoxasignaturaCollectionNew.contains(tbusuarioxgrupoxasignaturaCollectionOldTbusuarioxgrupoxasignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbusuarioxgrupoxasignatura " + tbusuarioxgrupoxasignaturaCollectionOldTbusuarioxgrupoxasignatura + " since its codigoasignatura field is not nullable.");
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
            tbasignatura.setTbencuestaxasignaturaCollection(tbencuestaxasignaturaCollectionNew);
            Collection<Tbgrupoxasignaturaxprofesor> attachedTbgrupoxasignaturaxprofesorCollectionNew = new ArrayList<Tbgrupoxasignaturaxprofesor>();
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesorToAttach : tbgrupoxasignaturaxprofesorCollectionNew) {
                tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesorToAttach = em.getReference(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesorToAttach.getClass(), tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesorToAttach.getId());
                attachedTbgrupoxasignaturaxprofesorCollectionNew.add(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesorToAttach);
            }
            tbgrupoxasignaturaxprofesorCollectionNew = attachedTbgrupoxasignaturaxprofesorCollectionNew;
            tbasignatura.setTbgrupoxasignaturaxprofesorCollection(tbgrupoxasignaturaxprofesorCollectionNew);
            Collection<Tbusuarioxgrupoxasignatura> attachedTbusuarioxgrupoxasignaturaCollectionNew = new ArrayList<Tbusuarioxgrupoxasignatura>();
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach : tbusuarioxgrupoxasignaturaCollectionNew) {
                tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach = em.getReference(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach.getClass(), tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach.getId());
                attachedTbusuarioxgrupoxasignaturaCollectionNew.add(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach);
            }
            tbusuarioxgrupoxasignaturaCollectionNew = attachedTbusuarioxgrupoxasignaturaCollectionNew;
            tbasignatura.setTbusuarioxgrupoxasignaturaCollection(tbusuarioxgrupoxasignaturaCollectionNew);
            tbasignatura = em.merge(tbasignatura);
            for (Tbencuestaxasignatura tbencuestaxasignaturaCollectionNewTbencuestaxasignatura : tbencuestaxasignaturaCollectionNew) {
                if (!tbencuestaxasignaturaCollectionOld.contains(tbencuestaxasignaturaCollectionNewTbencuestaxasignatura)) {
                    Tbasignatura oldIdasignaturaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura = tbencuestaxasignaturaCollectionNewTbencuestaxasignatura.getIdasignatura();
                    tbencuestaxasignaturaCollectionNewTbencuestaxasignatura.setIdasignatura(tbasignatura);
                    tbencuestaxasignaturaCollectionNewTbencuestaxasignatura = em.merge(tbencuestaxasignaturaCollectionNewTbencuestaxasignatura);
                    if (oldIdasignaturaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura != null && !oldIdasignaturaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura.equals(tbasignatura)) {
                        oldIdasignaturaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura.getTbencuestaxasignaturaCollection().remove(tbencuestaxasignaturaCollectionNewTbencuestaxasignatura);
                        oldIdasignaturaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura = em.merge(oldIdasignaturaOfTbencuestaxasignaturaCollectionNewTbencuestaxasignatura);
                    }
                }
            }
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor : tbgrupoxasignaturaxprofesorCollectionNew) {
                if (!tbgrupoxasignaturaxprofesorCollectionOld.contains(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor)) {
                    Tbasignatura oldIdasignaturaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor = tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.getIdasignatura();
                    tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.setIdasignatura(tbasignatura);
                    tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor = em.merge(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor);
                    if (oldIdasignaturaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor != null && !oldIdasignaturaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.equals(tbasignatura)) {
                        oldIdasignaturaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor);
                        oldIdasignaturaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor = em.merge(oldIdasignaturaOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor);
                    }
                }
            }
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura : tbusuarioxgrupoxasignaturaCollectionNew) {
                if (!tbusuarioxgrupoxasignaturaCollectionOld.contains(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura)) {
                    Tbasignatura oldCodigoasignaturaOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura = tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.getCodigoasignatura();
                    tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.setCodigoasignatura(tbasignatura);
                    tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura = em.merge(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura);
                    if (oldCodigoasignaturaOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura != null && !oldCodigoasignaturaOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.equals(tbasignatura)) {
                        oldCodigoasignaturaOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura);
                        oldCodigoasignaturaOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura = em.merge(oldCodigoasignaturaOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = tbasignatura.getCodigo();
                if (findTbasignatura(id) == null) {
                    throw new NonexistentEntityException("The tbasignatura with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbasignatura tbasignatura;
            try {
                tbasignatura = em.getReference(Tbasignatura.class, id);
                tbasignatura.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbasignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbencuestaxasignatura> tbencuestaxasignaturaCollectionOrphanCheck = tbasignatura.getTbencuestaxasignaturaCollection();
            for (Tbencuestaxasignatura tbencuestaxasignaturaCollectionOrphanCheckTbencuestaxasignatura : tbencuestaxasignaturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbasignatura (" + tbasignatura + ") cannot be destroyed since the Tbencuestaxasignatura " + tbencuestaxasignaturaCollectionOrphanCheckTbencuestaxasignatura + " in its tbencuestaxasignaturaCollection field has a non-nullable idasignatura field.");
            }
            Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollectionOrphanCheck = tbasignatura.getTbgrupoxasignaturaxprofesorCollection();
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionOrphanCheckTbgrupoxasignaturaxprofesor : tbgrupoxasignaturaxprofesorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbasignatura (" + tbasignatura + ") cannot be destroyed since the Tbgrupoxasignaturaxprofesor " + tbgrupoxasignaturaxprofesorCollectionOrphanCheckTbgrupoxasignaturaxprofesor + " in its tbgrupoxasignaturaxprofesorCollection field has a non-nullable idasignatura field.");
            }
            Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollectionOrphanCheck = tbasignatura.getTbusuarioxgrupoxasignaturaCollection();
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionOrphanCheckTbusuarioxgrupoxasignatura : tbusuarioxgrupoxasignaturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbasignatura (" + tbasignatura + ") cannot be destroyed since the Tbusuarioxgrupoxasignatura " + tbusuarioxgrupoxasignaturaCollectionOrphanCheckTbusuarioxgrupoxasignatura + " in its tbusuarioxgrupoxasignaturaCollection field has a non-nullable codigoasignatura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tbasignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbasignatura> findTbasignaturaEntities() {
        return findTbasignaturaEntities(true, -1, -1);
    }

    public List<Tbasignatura> findTbasignaturaEntities(int maxResults, int firstResult) {
        return findTbasignaturaEntities(false, maxResults, firstResult);
    }

    private List<Tbasignatura> findTbasignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbasignatura.class));
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

    public Tbasignatura findTbasignatura(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbasignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbasignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbasignatura> rt = cq.from(Tbasignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
