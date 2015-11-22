/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Tbencuestaxasignatura;
import modelo.Tbgrupoxasignaturaxprofesor;
import modelo.Tbinformes;

/**
 *
 * @author Andres
 */
public class TbinformesJpaController implements Serializable {

    public TbinformesJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbinformes tbinformes) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbencuestaxasignatura idencuestaxasignatura = tbinformes.getIdencuestaxasignatura();
            if (idencuestaxasignatura != null) {
                idencuestaxasignatura = em.getReference(idencuestaxasignatura.getClass(), idencuestaxasignatura.getId());
                tbinformes.setIdencuestaxasignatura(idencuestaxasignatura);
            }
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesor = tbinformes.getIdgrupoxasignaturaxprofesor();
            if (idgrupoxasignaturaxprofesor != null) {
                idgrupoxasignaturaxprofesor = em.getReference(idgrupoxasignaturaxprofesor.getClass(), idgrupoxasignaturaxprofesor.getId());
                tbinformes.setIdgrupoxasignaturaxprofesor(idgrupoxasignaturaxprofesor);
            }
            em.persist(tbinformes);
            if (idencuestaxasignatura != null) {
                idencuestaxasignatura.getTbinformesCollection().add(tbinformes);
                idencuestaxasignatura = em.merge(idencuestaxasignatura);
            }
            if (idgrupoxasignaturaxprofesor != null) {
                idgrupoxasignaturaxprofesor.getTbinformesCollection().add(tbinformes);
                idgrupoxasignaturaxprofesor = em.merge(idgrupoxasignaturaxprofesor);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbinformes tbinformes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbinformes persistentTbinformes = em.find(Tbinformes.class, tbinformes.getId());
            Tbencuestaxasignatura idencuestaxasignaturaOld = persistentTbinformes.getIdencuestaxasignatura();
            Tbencuestaxasignatura idencuestaxasignaturaNew = tbinformes.getIdencuestaxasignatura();
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesorOld = persistentTbinformes.getIdgrupoxasignaturaxprofesor();
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesorNew = tbinformes.getIdgrupoxasignaturaxprofesor();
            if (idencuestaxasignaturaNew != null) {
                idencuestaxasignaturaNew = em.getReference(idencuestaxasignaturaNew.getClass(), idencuestaxasignaturaNew.getId());
                tbinformes.setIdencuestaxasignatura(idencuestaxasignaturaNew);
            }
            if (idgrupoxasignaturaxprofesorNew != null) {
                idgrupoxasignaturaxprofesorNew = em.getReference(idgrupoxasignaturaxprofesorNew.getClass(), idgrupoxasignaturaxprofesorNew.getId());
                tbinformes.setIdgrupoxasignaturaxprofesor(idgrupoxasignaturaxprofesorNew);
            }
            tbinformes = em.merge(tbinformes);
            if (idencuestaxasignaturaOld != null && !idencuestaxasignaturaOld.equals(idencuestaxasignaturaNew)) {
                idencuestaxasignaturaOld.getTbinformesCollection().remove(tbinformes);
                idencuestaxasignaturaOld = em.merge(idencuestaxasignaturaOld);
            }
            if (idencuestaxasignaturaNew != null && !idencuestaxasignaturaNew.equals(idencuestaxasignaturaOld)) {
                idencuestaxasignaturaNew.getTbinformesCollection().add(tbinformes);
                idencuestaxasignaturaNew = em.merge(idencuestaxasignaturaNew);
            }
            if (idgrupoxasignaturaxprofesorOld != null && !idgrupoxasignaturaxprofesorOld.equals(idgrupoxasignaturaxprofesorNew)) {
                idgrupoxasignaturaxprofesorOld.getTbinformesCollection().remove(tbinformes);
                idgrupoxasignaturaxprofesorOld = em.merge(idgrupoxasignaturaxprofesorOld);
            }
            if (idgrupoxasignaturaxprofesorNew != null && !idgrupoxasignaturaxprofesorNew.equals(idgrupoxasignaturaxprofesorOld)) {
                idgrupoxasignaturaxprofesorNew.getTbinformesCollection().add(tbinformes);
                idgrupoxasignaturaxprofesorNew = em.merge(idgrupoxasignaturaxprofesorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbinformes.getId();
                if (findTbinformes(id) == null) {
                    throw new NonexistentEntityException("The tbinformes with id " + id + " no longer exists.");
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
            Tbinformes tbinformes;
            try {
                tbinformes = em.getReference(Tbinformes.class, id);
                tbinformes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbinformes with id " + id + " no longer exists.", enfe);
            }
            Tbencuestaxasignatura idencuestaxasignatura = tbinformes.getIdencuestaxasignatura();
            if (idencuestaxasignatura != null) {
                idencuestaxasignatura.getTbinformesCollection().remove(tbinformes);
                idencuestaxasignatura = em.merge(idencuestaxasignatura);
            }
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesor = tbinformes.getIdgrupoxasignaturaxprofesor();
            if (idgrupoxasignaturaxprofesor != null) {
                idgrupoxasignaturaxprofesor.getTbinformesCollection().remove(tbinformes);
                idgrupoxasignaturaxprofesor = em.merge(idgrupoxasignaturaxprofesor);
            }
            em.remove(tbinformes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbinformes> findTbinformesEntities() {
        return findTbinformesEntities(true, -1, -1);
    }

    public List<Tbinformes> findTbinformesEntities(int maxResults, int firstResult) {
        return findTbinformesEntities(false, maxResults, firstResult);
    }

    private List<Tbinformes> findTbinformesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbinformes.class));
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

    public Tbinformes findTbinformes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbinformes.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbinformesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbinformes> rt = cq.from(Tbinformes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
