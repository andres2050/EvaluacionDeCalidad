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
import modelo.Tbasignatura;
import modelo.Tbgrupo;
import modelo.Tbusuario;
import modelo.Tbusuarioxgrupoxasignatura;

/**
 *
 * @author Andres
 */
public class TbusuarioxgrupoxasignaturaJpaController implements Serializable {

    public TbusuarioxgrupoxasignaturaJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignatura) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbasignatura codigoasignatura = tbusuarioxgrupoxasignatura.getCodigoasignatura();
            if (codigoasignatura != null) {
                codigoasignatura = em.getReference(codigoasignatura.getClass(), codigoasignatura.getCodigo());
                tbusuarioxgrupoxasignatura.setCodigoasignatura(codigoasignatura);
            }
            Tbgrupo idgrupo = tbusuarioxgrupoxasignatura.getIdgrupo();
            if (idgrupo != null) {
                idgrupo = em.getReference(idgrupo.getClass(), idgrupo.getIdentificacion());
                tbusuarioxgrupoxasignatura.setIdgrupo(idgrupo);
            }
            Tbusuario codigousuario = tbusuarioxgrupoxasignatura.getCodigousuario();
            if (codigousuario != null) {
                codigousuario = em.getReference(codigousuario.getClass(), codigousuario.getCodigo());
                tbusuarioxgrupoxasignatura.setCodigousuario(codigousuario);
            }
            em.persist(tbusuarioxgrupoxasignatura);
            if (codigoasignatura != null) {
                codigoasignatura.getTbusuarioxgrupoxasignaturaCollection().add(tbusuarioxgrupoxasignatura);
                codigoasignatura = em.merge(codigoasignatura);
            }
            if (idgrupo != null) {
                idgrupo.getTbusuarioxgrupoxasignaturaCollection().add(tbusuarioxgrupoxasignatura);
                idgrupo = em.merge(idgrupo);
            }
            if (codigousuario != null) {
                codigousuario.getTbusuarioxgrupoxasignaturaCollection().add(tbusuarioxgrupoxasignatura);
                codigousuario = em.merge(codigousuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignatura) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbusuarioxgrupoxasignatura persistentTbusuarioxgrupoxasignatura = em.find(Tbusuarioxgrupoxasignatura.class, tbusuarioxgrupoxasignatura.getId());
            Tbasignatura codigoasignaturaOld = persistentTbusuarioxgrupoxasignatura.getCodigoasignatura();
            Tbasignatura codigoasignaturaNew = tbusuarioxgrupoxasignatura.getCodigoasignatura();
            Tbgrupo idgrupoOld = persistentTbusuarioxgrupoxasignatura.getIdgrupo();
            Tbgrupo idgrupoNew = tbusuarioxgrupoxasignatura.getIdgrupo();
            Tbusuario codigousuarioOld = persistentTbusuarioxgrupoxasignatura.getCodigousuario();
            Tbusuario codigousuarioNew = tbusuarioxgrupoxasignatura.getCodigousuario();
            if (codigoasignaturaNew != null) {
                codigoasignaturaNew = em.getReference(codigoasignaturaNew.getClass(), codigoasignaturaNew.getCodigo());
                tbusuarioxgrupoxasignatura.setCodigoasignatura(codigoasignaturaNew);
            }
            if (idgrupoNew != null) {
                idgrupoNew = em.getReference(idgrupoNew.getClass(), idgrupoNew.getIdentificacion());
                tbusuarioxgrupoxasignatura.setIdgrupo(idgrupoNew);
            }
            if (codigousuarioNew != null) {
                codigousuarioNew = em.getReference(codigousuarioNew.getClass(), codigousuarioNew.getCodigo());
                tbusuarioxgrupoxasignatura.setCodigousuario(codigousuarioNew);
            }
            tbusuarioxgrupoxasignatura = em.merge(tbusuarioxgrupoxasignatura);
            if (codigoasignaturaOld != null && !codigoasignaturaOld.equals(codigoasignaturaNew)) {
                codigoasignaturaOld.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignatura);
                codigoasignaturaOld = em.merge(codigoasignaturaOld);
            }
            if (codigoasignaturaNew != null && !codigoasignaturaNew.equals(codigoasignaturaOld)) {
                codigoasignaturaNew.getTbusuarioxgrupoxasignaturaCollection().add(tbusuarioxgrupoxasignatura);
                codigoasignaturaNew = em.merge(codigoasignaturaNew);
            }
            if (idgrupoOld != null && !idgrupoOld.equals(idgrupoNew)) {
                idgrupoOld.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignatura);
                idgrupoOld = em.merge(idgrupoOld);
            }
            if (idgrupoNew != null && !idgrupoNew.equals(idgrupoOld)) {
                idgrupoNew.getTbusuarioxgrupoxasignaturaCollection().add(tbusuarioxgrupoxasignatura);
                idgrupoNew = em.merge(idgrupoNew);
            }
            if (codigousuarioOld != null && !codigousuarioOld.equals(codigousuarioNew)) {
                codigousuarioOld.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignatura);
                codigousuarioOld = em.merge(codigousuarioOld);
            }
            if (codigousuarioNew != null && !codigousuarioNew.equals(codigousuarioOld)) {
                codigousuarioNew.getTbusuarioxgrupoxasignaturaCollection().add(tbusuarioxgrupoxasignatura);
                codigousuarioNew = em.merge(codigousuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbusuarioxgrupoxasignatura.getId();
                if (findTbusuarioxgrupoxasignatura(id) == null) {
                    throw new NonexistentEntityException("The tbusuarioxgrupoxasignatura with id " + id + " no longer exists.");
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
            Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignatura;
            try {
                tbusuarioxgrupoxasignatura = em.getReference(Tbusuarioxgrupoxasignatura.class, id);
                tbusuarioxgrupoxasignatura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbusuarioxgrupoxasignatura with id " + id + " no longer exists.", enfe);
            }
            Tbasignatura codigoasignatura = tbusuarioxgrupoxasignatura.getCodigoasignatura();
            if (codigoasignatura != null) {
                codigoasignatura.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignatura);
                codigoasignatura = em.merge(codigoasignatura);
            }
            Tbgrupo idgrupo = tbusuarioxgrupoxasignatura.getIdgrupo();
            if (idgrupo != null) {
                idgrupo.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignatura);
                idgrupo = em.merge(idgrupo);
            }
            Tbusuario codigousuario = tbusuarioxgrupoxasignatura.getCodigousuario();
            if (codigousuario != null) {
                codigousuario.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignatura);
                codigousuario = em.merge(codigousuario);
            }
            em.remove(tbusuarioxgrupoxasignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbusuarioxgrupoxasignatura> findTbusuarioxgrupoxasignaturaEntities() {
        return findTbusuarioxgrupoxasignaturaEntities(true, -1, -1);
    }

    public List<Tbusuarioxgrupoxasignatura> findTbusuarioxgrupoxasignaturaEntities(int maxResults, int firstResult) {
        return findTbusuarioxgrupoxasignaturaEntities(false, maxResults, firstResult);
    }

    private List<Tbusuarioxgrupoxasignatura> findTbusuarioxgrupoxasignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbusuarioxgrupoxasignatura.class));
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

    public Tbusuarioxgrupoxasignatura findTbusuarioxgrupoxasignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbusuarioxgrupoxasignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbusuarioxgrupoxasignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbusuarioxgrupoxasignatura> rt = cq.from(Tbusuarioxgrupoxasignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
