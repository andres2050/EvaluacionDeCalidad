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
import modelo.Tbencuestasxusuario;
import modelo.Tbencuestaxasignatura;
import modelo.Tbgrupoxasignaturaxprofesor;
import modelo.Tbusuario;

/**
 *
 * @author Andres
 */
public class TbencuestasxusuarioJpaController implements Serializable {

    public TbencuestasxusuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbencuestasxusuario tbencuestasxusuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbencuestaxasignatura idencuestaxasignatura = tbencuestasxusuario.getIdencuestaxasignatura();
            if (idencuestaxasignatura != null) {
                idencuestaxasignatura = em.getReference(idencuestaxasignatura.getClass(), idencuestaxasignatura.getId());
                tbencuestasxusuario.setIdencuestaxasignatura(idencuestaxasignatura);
            }
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesor = tbencuestasxusuario.getIdgrupoxasignaturaxprofesor();
            if (idgrupoxasignaturaxprofesor != null) {
                idgrupoxasignaturaxprofesor = em.getReference(idgrupoxasignaturaxprofesor.getClass(), idgrupoxasignaturaxprofesor.getId());
                tbencuestasxusuario.setIdgrupoxasignaturaxprofesor(idgrupoxasignaturaxprofesor);
            }
            Tbusuario codigousuario = tbencuestasxusuario.getCodigousuario();
            if (codigousuario != null) {
                codigousuario = em.getReference(codigousuario.getClass(), codigousuario.getCodigo());
                tbencuestasxusuario.setCodigousuario(codigousuario);
            }
            em.persist(tbencuestasxusuario);
            if (idencuestaxasignatura != null) {
                idencuestaxasignatura.getTbencuestasxusuarioCollection().add(tbencuestasxusuario);
                idencuestaxasignatura = em.merge(idencuestaxasignatura);
            }
            if (idgrupoxasignaturaxprofesor != null) {
                idgrupoxasignaturaxprofesor.getTbencuestasxusuarioCollection().add(tbencuestasxusuario);
                idgrupoxasignaturaxprofesor = em.merge(idgrupoxasignaturaxprofesor);
            }
            if (codigousuario != null) {
                codigousuario.getTbencuestasxusuarioCollection().add(tbencuestasxusuario);
                codigousuario = em.merge(codigousuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbencuestasxusuario tbencuestasxusuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbencuestasxusuario persistentTbencuestasxusuario = em.find(Tbencuestasxusuario.class, tbencuestasxusuario.getId());
            Tbencuestaxasignatura idencuestaxasignaturaOld = persistentTbencuestasxusuario.getIdencuestaxasignatura();
            Tbencuestaxasignatura idencuestaxasignaturaNew = tbencuestasxusuario.getIdencuestaxasignatura();
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesorOld = persistentTbencuestasxusuario.getIdgrupoxasignaturaxprofesor();
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesorNew = tbencuestasxusuario.getIdgrupoxasignaturaxprofesor();
            Tbusuario codigousuarioOld = persistentTbencuestasxusuario.getCodigousuario();
            Tbusuario codigousuarioNew = tbencuestasxusuario.getCodigousuario();
            if (idencuestaxasignaturaNew != null) {
                idencuestaxasignaturaNew = em.getReference(idencuestaxasignaturaNew.getClass(), idencuestaxasignaturaNew.getId());
                tbencuestasxusuario.setIdencuestaxasignatura(idencuestaxasignaturaNew);
            }
            if (idgrupoxasignaturaxprofesorNew != null) {
                idgrupoxasignaturaxprofesorNew = em.getReference(idgrupoxasignaturaxprofesorNew.getClass(), idgrupoxasignaturaxprofesorNew.getId());
                tbencuestasxusuario.setIdgrupoxasignaturaxprofesor(idgrupoxasignaturaxprofesorNew);
            }
            if (codigousuarioNew != null) {
                codigousuarioNew = em.getReference(codigousuarioNew.getClass(), codigousuarioNew.getCodigo());
                tbencuestasxusuario.setCodigousuario(codigousuarioNew);
            }
            tbencuestasxusuario = em.merge(tbencuestasxusuario);
            if (idencuestaxasignaturaOld != null && !idencuestaxasignaturaOld.equals(idencuestaxasignaturaNew)) {
                idencuestaxasignaturaOld.getTbencuestasxusuarioCollection().remove(tbencuestasxusuario);
                idencuestaxasignaturaOld = em.merge(idencuestaxasignaturaOld);
            }
            if (idencuestaxasignaturaNew != null && !idencuestaxasignaturaNew.equals(idencuestaxasignaturaOld)) {
                idencuestaxasignaturaNew.getTbencuestasxusuarioCollection().add(tbencuestasxusuario);
                idencuestaxasignaturaNew = em.merge(idencuestaxasignaturaNew);
            }
            if (idgrupoxasignaturaxprofesorOld != null && !idgrupoxasignaturaxprofesorOld.equals(idgrupoxasignaturaxprofesorNew)) {
                idgrupoxasignaturaxprofesorOld.getTbencuestasxusuarioCollection().remove(tbencuestasxusuario);
                idgrupoxasignaturaxprofesorOld = em.merge(idgrupoxasignaturaxprofesorOld);
            }
            if (idgrupoxasignaturaxprofesorNew != null && !idgrupoxasignaturaxprofesorNew.equals(idgrupoxasignaturaxprofesorOld)) {
                idgrupoxasignaturaxprofesorNew.getTbencuestasxusuarioCollection().add(tbencuestasxusuario);
                idgrupoxasignaturaxprofesorNew = em.merge(idgrupoxasignaturaxprofesorNew);
            }
            if (codigousuarioOld != null && !codigousuarioOld.equals(codigousuarioNew)) {
                codigousuarioOld.getTbencuestasxusuarioCollection().remove(tbencuestasxusuario);
                codigousuarioOld = em.merge(codigousuarioOld);
            }
            if (codigousuarioNew != null && !codigousuarioNew.equals(codigousuarioOld)) {
                codigousuarioNew.getTbencuestasxusuarioCollection().add(tbencuestasxusuario);
                codigousuarioNew = em.merge(codigousuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbencuestasxusuario.getId();
                if (findTbencuestasxusuario(id) == null) {
                    throw new NonexistentEntityException("The tbencuestasxusuario with id " + id + " no longer exists.");
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
            Tbencuestasxusuario tbencuestasxusuario;
            try {
                tbencuestasxusuario = em.getReference(Tbencuestasxusuario.class, id);
                tbencuestasxusuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbencuestasxusuario with id " + id + " no longer exists.", enfe);
            }
            Tbencuestaxasignatura idencuestaxasignatura = tbencuestasxusuario.getIdencuestaxasignatura();
            if (idencuestaxasignatura != null) {
                idencuestaxasignatura.getTbencuestasxusuarioCollection().remove(tbencuestasxusuario);
                idencuestaxasignatura = em.merge(idencuestaxasignatura);
            }
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesor = tbencuestasxusuario.getIdgrupoxasignaturaxprofesor();
            if (idgrupoxasignaturaxprofesor != null) {
                idgrupoxasignaturaxprofesor.getTbencuestasxusuarioCollection().remove(tbencuestasxusuario);
                idgrupoxasignaturaxprofesor = em.merge(idgrupoxasignaturaxprofesor);
            }
            Tbusuario codigousuario = tbencuestasxusuario.getCodigousuario();
            if (codigousuario != null) {
                codigousuario.getTbencuestasxusuarioCollection().remove(tbencuestasxusuario);
                codigousuario = em.merge(codigousuario);
            }
            em.remove(tbencuestasxusuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbencuestasxusuario> findTbencuestasxusuarioEntities() {
        return findTbencuestasxusuarioEntities(true, -1, -1);
    }

    public List<Tbencuestasxusuario> findTbencuestasxusuarioEntities(int maxResults, int firstResult) {
        return findTbencuestasxusuarioEntities(false, maxResults, firstResult);
    }

    private List<Tbencuestasxusuario> findTbencuestasxusuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbencuestasxusuario.class));
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

    public Tbencuestasxusuario findTbencuestasxusuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbencuestasxusuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbencuestasxusuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbencuestasxusuario> rt = cq.from(Tbencuestasxusuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
