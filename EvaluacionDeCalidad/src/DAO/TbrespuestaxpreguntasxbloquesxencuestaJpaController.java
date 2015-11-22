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
import modelo.Tbbloquesxencuesta;
import modelo.Tbbloquesxpregunta;
import modelo.Tbencuestaxasignatura;
import modelo.Tbgrupoxasignaturaxprofesor;
import modelo.Tbrespuestaxpreguntasxbloquesxencuesta;
import modelo.Tbusuario;

/**
 *
 * @author Andres
 */
public class TbrespuestaxpreguntasxbloquesxencuestaJpaController implements Serializable {

    public TbrespuestaxpreguntasxbloquesxencuestaJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuesta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbbloquesxencuesta idbloquexencuesta = tbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexencuesta();
            if (idbloquexencuesta != null) {
                idbloquexencuesta = em.getReference(idbloquexencuesta.getClass(), idbloquexencuesta.getId());
                tbrespuestaxpreguntasxbloquesxencuesta.setIdbloquexencuesta(idbloquexencuesta);
            }
            Tbbloquesxpregunta idbloquexpregunta = tbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexpregunta();
            if (idbloquexpregunta != null) {
                idbloquexpregunta = em.getReference(idbloquexpregunta.getClass(), idbloquexpregunta.getId());
                tbrespuestaxpreguntasxbloquesxencuesta.setIdbloquexpregunta(idbloquexpregunta);
            }
            Tbencuestaxasignatura idencuestaxasignatura = tbrespuestaxpreguntasxbloquesxencuesta.getIdencuestaxasignatura();
            if (idencuestaxasignatura != null) {
                idencuestaxasignatura = em.getReference(idencuestaxasignatura.getClass(), idencuestaxasignatura.getId());
                tbrespuestaxpreguntasxbloquesxencuesta.setIdencuestaxasignatura(idencuestaxasignatura);
            }
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesor = tbrespuestaxpreguntasxbloquesxencuesta.getIdgrupoxasignaturaxprofesor();
            if (idgrupoxasignaturaxprofesor != null) {
                idgrupoxasignaturaxprofesor = em.getReference(idgrupoxasignaturaxprofesor.getClass(), idgrupoxasignaturaxprofesor.getId());
                tbrespuestaxpreguntasxbloquesxencuesta.setIdgrupoxasignaturaxprofesor(idgrupoxasignaturaxprofesor);
            }
            Tbusuario codigousuario = tbrespuestaxpreguntasxbloquesxencuesta.getCodigousuario();
            if (codigousuario != null) {
                codigousuario = em.getReference(codigousuario.getClass(), codigousuario.getCodigo());
                tbrespuestaxpreguntasxbloquesxencuesta.setCodigousuario(codigousuario);
            }
            em.persist(tbrespuestaxpreguntasxbloquesxencuesta);
            if (idbloquexencuesta != null) {
                idbloquexencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().add(tbrespuestaxpreguntasxbloquesxencuesta);
                idbloquexencuesta = em.merge(idbloquexencuesta);
            }
            if (idbloquexpregunta != null) {
                idbloquexpregunta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().add(tbrespuestaxpreguntasxbloquesxencuesta);
                idbloquexpregunta = em.merge(idbloquexpregunta);
            }
            if (idencuestaxasignatura != null) {
                idencuestaxasignatura.getTbrespuestaxpreguntasxbloquesxencuestaCollection().add(tbrespuestaxpreguntasxbloquesxencuesta);
                idencuestaxasignatura = em.merge(idencuestaxasignatura);
            }
            if (idgrupoxasignaturaxprofesor != null) {
                idgrupoxasignaturaxprofesor.getTbrespuestaxpreguntasxbloquesxencuestaCollection().add(tbrespuestaxpreguntasxbloquesxencuesta);
                idgrupoxasignaturaxprofesor = em.merge(idgrupoxasignaturaxprofesor);
            }
            if (codigousuario != null) {
                codigousuario.getTbrespuestaxpreguntasxbloquesxencuestaCollection().add(tbrespuestaxpreguntasxbloquesxencuesta);
                codigousuario = em.merge(codigousuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuesta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbrespuestaxpreguntasxbloquesxencuesta persistentTbrespuestaxpreguntasxbloquesxencuesta = em.find(Tbrespuestaxpreguntasxbloquesxencuesta.class, tbrespuestaxpreguntasxbloquesxencuesta.getId());
            Tbbloquesxencuesta idbloquexencuestaOld = persistentTbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexencuesta();
            Tbbloquesxencuesta idbloquexencuestaNew = tbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexencuesta();
            Tbbloquesxpregunta idbloquexpreguntaOld = persistentTbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexpregunta();
            Tbbloquesxpregunta idbloquexpreguntaNew = tbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexpregunta();
            Tbencuestaxasignatura idencuestaxasignaturaOld = persistentTbrespuestaxpreguntasxbloquesxencuesta.getIdencuestaxasignatura();
            Tbencuestaxasignatura idencuestaxasignaturaNew = tbrespuestaxpreguntasxbloquesxencuesta.getIdencuestaxasignatura();
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesorOld = persistentTbrespuestaxpreguntasxbloquesxencuesta.getIdgrupoxasignaturaxprofesor();
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesorNew = tbrespuestaxpreguntasxbloquesxencuesta.getIdgrupoxasignaturaxprofesor();
            Tbusuario codigousuarioOld = persistentTbrespuestaxpreguntasxbloquesxencuesta.getCodigousuario();
            Tbusuario codigousuarioNew = tbrespuestaxpreguntasxbloquesxencuesta.getCodigousuario();
            if (idbloquexencuestaNew != null) {
                idbloquexencuestaNew = em.getReference(idbloquexencuestaNew.getClass(), idbloquexencuestaNew.getId());
                tbrespuestaxpreguntasxbloquesxencuesta.setIdbloquexencuesta(idbloquexencuestaNew);
            }
            if (idbloquexpreguntaNew != null) {
                idbloquexpreguntaNew = em.getReference(idbloquexpreguntaNew.getClass(), idbloquexpreguntaNew.getId());
                tbrespuestaxpreguntasxbloquesxencuesta.setIdbloquexpregunta(idbloquexpreguntaNew);
            }
            if (idencuestaxasignaturaNew != null) {
                idencuestaxasignaturaNew = em.getReference(idencuestaxasignaturaNew.getClass(), idencuestaxasignaturaNew.getId());
                tbrespuestaxpreguntasxbloquesxencuesta.setIdencuestaxasignatura(idencuestaxasignaturaNew);
            }
            if (idgrupoxasignaturaxprofesorNew != null) {
                idgrupoxasignaturaxprofesorNew = em.getReference(idgrupoxasignaturaxprofesorNew.getClass(), idgrupoxasignaturaxprofesorNew.getId());
                tbrespuestaxpreguntasxbloquesxencuesta.setIdgrupoxasignaturaxprofesor(idgrupoxasignaturaxprofesorNew);
            }
            if (codigousuarioNew != null) {
                codigousuarioNew = em.getReference(codigousuarioNew.getClass(), codigousuarioNew.getCodigo());
                tbrespuestaxpreguntasxbloquesxencuesta.setCodigousuario(codigousuarioNew);
            }
            tbrespuestaxpreguntasxbloquesxencuesta = em.merge(tbrespuestaxpreguntasxbloquesxencuesta);
            if (idbloquexencuestaOld != null && !idbloquexencuestaOld.equals(idbloquexencuestaNew)) {
                idbloquexencuestaOld.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuesta);
                idbloquexencuestaOld = em.merge(idbloquexencuestaOld);
            }
            if (idbloquexencuestaNew != null && !idbloquexencuestaNew.equals(idbloquexencuestaOld)) {
                idbloquexencuestaNew.getTbrespuestaxpreguntasxbloquesxencuestaCollection().add(tbrespuestaxpreguntasxbloquesxencuesta);
                idbloquexencuestaNew = em.merge(idbloquexencuestaNew);
            }
            if (idbloquexpreguntaOld != null && !idbloquexpreguntaOld.equals(idbloquexpreguntaNew)) {
                idbloquexpreguntaOld.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuesta);
                idbloquexpreguntaOld = em.merge(idbloquexpreguntaOld);
            }
            if (idbloquexpreguntaNew != null && !idbloquexpreguntaNew.equals(idbloquexpreguntaOld)) {
                idbloquexpreguntaNew.getTbrespuestaxpreguntasxbloquesxencuestaCollection().add(tbrespuestaxpreguntasxbloquesxencuesta);
                idbloquexpreguntaNew = em.merge(idbloquexpreguntaNew);
            }
            if (idencuestaxasignaturaOld != null && !idencuestaxasignaturaOld.equals(idencuestaxasignaturaNew)) {
                idencuestaxasignaturaOld.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuesta);
                idencuestaxasignaturaOld = em.merge(idencuestaxasignaturaOld);
            }
            if (idencuestaxasignaturaNew != null && !idencuestaxasignaturaNew.equals(idencuestaxasignaturaOld)) {
                idencuestaxasignaturaNew.getTbrespuestaxpreguntasxbloquesxencuestaCollection().add(tbrespuestaxpreguntasxbloquesxencuesta);
                idencuestaxasignaturaNew = em.merge(idencuestaxasignaturaNew);
            }
            if (idgrupoxasignaturaxprofesorOld != null && !idgrupoxasignaturaxprofesorOld.equals(idgrupoxasignaturaxprofesorNew)) {
                idgrupoxasignaturaxprofesorOld.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuesta);
                idgrupoxasignaturaxprofesorOld = em.merge(idgrupoxasignaturaxprofesorOld);
            }
            if (idgrupoxasignaturaxprofesorNew != null && !idgrupoxasignaturaxprofesorNew.equals(idgrupoxasignaturaxprofesorOld)) {
                idgrupoxasignaturaxprofesorNew.getTbrespuestaxpreguntasxbloquesxencuestaCollection().add(tbrespuestaxpreguntasxbloquesxencuesta);
                idgrupoxasignaturaxprofesorNew = em.merge(idgrupoxasignaturaxprofesorNew);
            }
            if (codigousuarioOld != null && !codigousuarioOld.equals(codigousuarioNew)) {
                codigousuarioOld.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuesta);
                codigousuarioOld = em.merge(codigousuarioOld);
            }
            if (codigousuarioNew != null && !codigousuarioNew.equals(codigousuarioOld)) {
                codigousuarioNew.getTbrespuestaxpreguntasxbloquesxencuestaCollection().add(tbrespuestaxpreguntasxbloquesxencuesta);
                codigousuarioNew = em.merge(codigousuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbrespuestaxpreguntasxbloquesxencuesta.getId();
                if (findTbrespuestaxpreguntasxbloquesxencuesta(id) == null) {
                    throw new NonexistentEntityException("The tbrespuestaxpreguntasxbloquesxencuesta with id " + id + " no longer exists.");
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
            Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuesta;
            try {
                tbrespuestaxpreguntasxbloquesxencuesta = em.getReference(Tbrespuestaxpreguntasxbloquesxencuesta.class, id);
                tbrespuestaxpreguntasxbloquesxencuesta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbrespuestaxpreguntasxbloquesxencuesta with id " + id + " no longer exists.", enfe);
            }
            Tbbloquesxencuesta idbloquexencuesta = tbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexencuesta();
            if (idbloquexencuesta != null) {
                idbloquexencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuesta);
                idbloquexencuesta = em.merge(idbloquexencuesta);
            }
            Tbbloquesxpregunta idbloquexpregunta = tbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexpregunta();
            if (idbloquexpregunta != null) {
                idbloquexpregunta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuesta);
                idbloquexpregunta = em.merge(idbloquexpregunta);
            }
            Tbencuestaxasignatura idencuestaxasignatura = tbrespuestaxpreguntasxbloquesxencuesta.getIdencuestaxasignatura();
            if (idencuestaxasignatura != null) {
                idencuestaxasignatura.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuesta);
                idencuestaxasignatura = em.merge(idencuestaxasignatura);
            }
            Tbgrupoxasignaturaxprofesor idgrupoxasignaturaxprofesor = tbrespuestaxpreguntasxbloquesxencuesta.getIdgrupoxasignaturaxprofesor();
            if (idgrupoxasignaturaxprofesor != null) {
                idgrupoxasignaturaxprofesor.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuesta);
                idgrupoxasignaturaxprofesor = em.merge(idgrupoxasignaturaxprofesor);
            }
            Tbusuario codigousuario = tbrespuestaxpreguntasxbloquesxencuesta.getCodigousuario();
            if (codigousuario != null) {
                codigousuario.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuesta);
                codigousuario = em.merge(codigousuario);
            }
            em.remove(tbrespuestaxpreguntasxbloquesxencuesta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbrespuestaxpreguntasxbloquesxencuesta> findTbrespuestaxpreguntasxbloquesxencuestaEntities() {
        return findTbrespuestaxpreguntasxbloquesxencuestaEntities(true, -1, -1);
    }

    public List<Tbrespuestaxpreguntasxbloquesxencuesta> findTbrespuestaxpreguntasxbloquesxencuestaEntities(int maxResults, int firstResult) {
        return findTbrespuestaxpreguntasxbloquesxencuestaEntities(false, maxResults, firstResult);
    }

    private List<Tbrespuestaxpreguntasxbloquesxencuesta> findTbrespuestaxpreguntasxbloquesxencuestaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbrespuestaxpreguntasxbloquesxencuesta.class));
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

    public Tbrespuestaxpreguntasxbloquesxencuesta findTbrespuestaxpreguntasxbloquesxencuesta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbrespuestaxpreguntasxbloquesxencuesta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbrespuestaxpreguntasxbloquesxencuestaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbrespuestaxpreguntasxbloquesxencuesta> rt = cq.from(Tbrespuestaxpreguntasxbloquesxencuesta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
