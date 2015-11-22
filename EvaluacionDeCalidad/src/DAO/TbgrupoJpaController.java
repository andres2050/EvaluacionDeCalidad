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
import modelo.Tbgrupoxasignaturaxprofesor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Tbgrupo;
import modelo.Tbusuarioxgrupoxasignatura;

/**
 *
 * @author Andres
 */
public class TbgrupoJpaController implements Serializable {

    public TbgrupoJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbgrupo tbgrupo) throws PreexistingEntityException, Exception {
        if (tbgrupo.getTbgrupoxasignaturaxprofesorCollection() == null) {
            tbgrupo.setTbgrupoxasignaturaxprofesorCollection(new ArrayList<Tbgrupoxasignaturaxprofesor>());
        }
        if (tbgrupo.getTbusuarioxgrupoxasignaturaCollection() == null) {
            tbgrupo.setTbusuarioxgrupoxasignaturaCollection(new ArrayList<Tbusuarioxgrupoxasignatura>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Tbgrupoxasignaturaxprofesor> attachedTbgrupoxasignaturaxprofesorCollection = new ArrayList<Tbgrupoxasignaturaxprofesor>();
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach : tbgrupo.getTbgrupoxasignaturaxprofesorCollection()) {
                tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach = em.getReference(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach.getClass(), tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach.getId());
                attachedTbgrupoxasignaturaxprofesorCollection.add(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesorToAttach);
            }
            tbgrupo.setTbgrupoxasignaturaxprofesorCollection(attachedTbgrupoxasignaturaxprofesorCollection);
            Collection<Tbusuarioxgrupoxasignatura> attachedTbusuarioxgrupoxasignaturaCollection = new ArrayList<Tbusuarioxgrupoxasignatura>();
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach : tbgrupo.getTbusuarioxgrupoxasignaturaCollection()) {
                tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach = em.getReference(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach.getClass(), tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach.getId());
                attachedTbusuarioxgrupoxasignaturaCollection.add(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach);
            }
            tbgrupo.setTbusuarioxgrupoxasignaturaCollection(attachedTbusuarioxgrupoxasignaturaCollection);
            em.persist(tbgrupo);
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor : tbgrupo.getTbgrupoxasignaturaxprofesorCollection()) {
                Tbgrupo oldIdgrupoOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor = tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor.getIdgrupo();
                tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor.setIdgrupo(tbgrupo);
                tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor = em.merge(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor);
                if (oldIdgrupoOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor != null) {
                    oldIdgrupoOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor);
                    oldIdgrupoOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor = em.merge(oldIdgrupoOfTbgrupoxasignaturaxprofesorCollectionTbgrupoxasignaturaxprofesor);
                }
            }
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura : tbgrupo.getTbusuarioxgrupoxasignaturaCollection()) {
                Tbgrupo oldIdgrupoOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura = tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura.getIdgrupo();
                tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura.setIdgrupo(tbgrupo);
                tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura = em.merge(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura);
                if (oldIdgrupoOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura != null) {
                    oldIdgrupoOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura);
                    oldIdgrupoOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura = em.merge(oldIdgrupoOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTbgrupo(tbgrupo.getIdentificacion()) != null) {
                throw new PreexistingEntityException("Tbgrupo " + tbgrupo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbgrupo tbgrupo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbgrupo persistentTbgrupo = em.find(Tbgrupo.class, tbgrupo.getIdentificacion());
            Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollectionOld = persistentTbgrupo.getTbgrupoxasignaturaxprofesorCollection();
            Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollectionNew = tbgrupo.getTbgrupoxasignaturaxprofesorCollection();
            Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollectionOld = persistentTbgrupo.getTbusuarioxgrupoxasignaturaCollection();
            Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollectionNew = tbgrupo.getTbusuarioxgrupoxasignaturaCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionOldTbgrupoxasignaturaxprofesor : tbgrupoxasignaturaxprofesorCollectionOld) {
                if (!tbgrupoxasignaturaxprofesorCollectionNew.contains(tbgrupoxasignaturaxprofesorCollectionOldTbgrupoxasignaturaxprofesor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbgrupoxasignaturaxprofesor " + tbgrupoxasignaturaxprofesorCollectionOldTbgrupoxasignaturaxprofesor + " since its idgrupo field is not nullable.");
                }
            }
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionOldTbusuarioxgrupoxasignatura : tbusuarioxgrupoxasignaturaCollectionOld) {
                if (!tbusuarioxgrupoxasignaturaCollectionNew.contains(tbusuarioxgrupoxasignaturaCollectionOldTbusuarioxgrupoxasignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbusuarioxgrupoxasignatura " + tbusuarioxgrupoxasignaturaCollectionOldTbusuarioxgrupoxasignatura + " since its idgrupo field is not nullable.");
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
            tbgrupo.setTbgrupoxasignaturaxprofesorCollection(tbgrupoxasignaturaxprofesorCollectionNew);
            Collection<Tbusuarioxgrupoxasignatura> attachedTbusuarioxgrupoxasignaturaCollectionNew = new ArrayList<Tbusuarioxgrupoxasignatura>();
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach : tbusuarioxgrupoxasignaturaCollectionNew) {
                tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach = em.getReference(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach.getClass(), tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach.getId());
                attachedTbusuarioxgrupoxasignaturaCollectionNew.add(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach);
            }
            tbusuarioxgrupoxasignaturaCollectionNew = attachedTbusuarioxgrupoxasignaturaCollectionNew;
            tbgrupo.setTbusuarioxgrupoxasignaturaCollection(tbusuarioxgrupoxasignaturaCollectionNew);
            tbgrupo = em.merge(tbgrupo);
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor : tbgrupoxasignaturaxprofesorCollectionNew) {
                if (!tbgrupoxasignaturaxprofesorCollectionOld.contains(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor)) {
                    Tbgrupo oldIdgrupoOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor = tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.getIdgrupo();
                    tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.setIdgrupo(tbgrupo);
                    tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor = em.merge(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor);
                    if (oldIdgrupoOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor != null && !oldIdgrupoOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.equals(tbgrupo)) {
                        oldIdgrupoOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor);
                        oldIdgrupoOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor = em.merge(oldIdgrupoOfTbgrupoxasignaturaxprofesorCollectionNewTbgrupoxasignaturaxprofesor);
                    }
                }
            }
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura : tbusuarioxgrupoxasignaturaCollectionNew) {
                if (!tbusuarioxgrupoxasignaturaCollectionOld.contains(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura)) {
                    Tbgrupo oldIdgrupoOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura = tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.getIdgrupo();
                    tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.setIdgrupo(tbgrupo);
                    tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura = em.merge(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura);
                    if (oldIdgrupoOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura != null && !oldIdgrupoOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.equals(tbgrupo)) {
                        oldIdgrupoOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura);
                        oldIdgrupoOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura = em.merge(oldIdgrupoOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbgrupo.getIdentificacion();
                if (findTbgrupo(id) == null) {
                    throw new NonexistentEntityException("The tbgrupo with id " + id + " no longer exists.");
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
            Tbgrupo tbgrupo;
            try {
                tbgrupo = em.getReference(Tbgrupo.class, id);
                tbgrupo.getIdentificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbgrupo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbgrupoxasignaturaxprofesor> tbgrupoxasignaturaxprofesorCollectionOrphanCheck = tbgrupo.getTbgrupoxasignaturaxprofesorCollection();
            for (Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesorCollectionOrphanCheckTbgrupoxasignaturaxprofesor : tbgrupoxasignaturaxprofesorCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbgrupo (" + tbgrupo + ") cannot be destroyed since the Tbgrupoxasignaturaxprofesor " + tbgrupoxasignaturaxprofesorCollectionOrphanCheckTbgrupoxasignaturaxprofesor + " in its tbgrupoxasignaturaxprofesorCollection field has a non-nullable idgrupo field.");
            }
            Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollectionOrphanCheck = tbgrupo.getTbusuarioxgrupoxasignaturaCollection();
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionOrphanCheckTbusuarioxgrupoxasignatura : tbusuarioxgrupoxasignaturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbgrupo (" + tbgrupo + ") cannot be destroyed since the Tbusuarioxgrupoxasignatura " + tbusuarioxgrupoxasignaturaCollectionOrphanCheckTbusuarioxgrupoxasignatura + " in its tbusuarioxgrupoxasignaturaCollection field has a non-nullable idgrupo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(tbgrupo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbgrupo> findTbgrupoEntities() {
        return findTbgrupoEntities(true, -1, -1);
    }

    public List<Tbgrupo> findTbgrupoEntities(int maxResults, int firstResult) {
        return findTbgrupoEntities(false, maxResults, firstResult);
    }

    private List<Tbgrupo> findTbgrupoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbgrupo.class));
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

    public Tbgrupo findTbgrupo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbgrupo.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbgrupoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbgrupo> rt = cq.from(Tbgrupo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
