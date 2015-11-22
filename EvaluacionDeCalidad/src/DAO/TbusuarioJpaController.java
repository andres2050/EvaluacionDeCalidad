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
import modelo.Tbtipo;
import modelo.Tbrespuestaxpreguntasxbloquesxencuesta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Tbusuarioxgrupoxasignatura;
import modelo.Tbencuestasxusuario;
import modelo.Tbusuario;

/**
 *
 * @author Andres
 */
public class TbusuarioJpaController implements Serializable {

    public TbusuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbusuario tbusuario) throws PreexistingEntityException, Exception {
        if (tbusuario.getTbrespuestaxpreguntasxbloquesxencuestaCollection() == null) {
            tbusuario.setTbrespuestaxpreguntasxbloquesxencuestaCollection(new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>());
        }
        if (tbusuario.getTbusuarioxgrupoxasignaturaCollection() == null) {
            tbusuario.setTbusuarioxgrupoxasignaturaCollection(new ArrayList<Tbusuarioxgrupoxasignatura>());
        }
        if (tbusuario.getTbencuestasxusuarioCollection() == null) {
            tbusuario.setTbencuestasxusuarioCollection(new ArrayList<Tbencuestasxusuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbtipo idtipo = tbusuario.getIdtipo();
            if (idtipo != null) {
                idtipo = em.getReference(idtipo.getClass(), idtipo.getId());
                tbusuario.setIdtipo(idtipo);
            }
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> attachedTbrespuestaxpreguntasxbloquesxencuestaCollection = new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach : tbusuario.getTbrespuestaxpreguntasxbloquesxencuestaCollection()) {
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach = em.getReference(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach.getClass(), tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach.getId());
                attachedTbrespuestaxpreguntasxbloquesxencuestaCollection.add(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach);
            }
            tbusuario.setTbrespuestaxpreguntasxbloquesxencuestaCollection(attachedTbrespuestaxpreguntasxbloquesxencuestaCollection);
            Collection<Tbusuarioxgrupoxasignatura> attachedTbusuarioxgrupoxasignaturaCollection = new ArrayList<Tbusuarioxgrupoxasignatura>();
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach : tbusuario.getTbusuarioxgrupoxasignaturaCollection()) {
                tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach = em.getReference(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach.getClass(), tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach.getId());
                attachedTbusuarioxgrupoxasignaturaCollection.add(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignaturaToAttach);
            }
            tbusuario.setTbusuarioxgrupoxasignaturaCollection(attachedTbusuarioxgrupoxasignaturaCollection);
            Collection<Tbencuestasxusuario> attachedTbencuestasxusuarioCollection = new ArrayList<Tbencuestasxusuario>();
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach : tbusuario.getTbencuestasxusuarioCollection()) {
                tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach = em.getReference(tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach.getClass(), tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach.getId());
                attachedTbencuestasxusuarioCollection.add(tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach);
            }
            tbusuario.setTbencuestasxusuarioCollection(attachedTbencuestasxusuarioCollection);
            em.persist(tbusuario);
            if (idtipo != null) {
                idtipo.getTbusuarioCollection().add(tbusuario);
                idtipo = em.merge(idtipo);
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta : tbusuario.getTbrespuestaxpreguntasxbloquesxencuestaCollection()) {
                Tbusuario oldCodigousuarioOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.getCodigousuario();
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.setCodigousuario(tbusuario);
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = em.merge(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                if (oldCodigousuarioOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta != null) {
                    oldCodigousuarioOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                    oldCodigousuarioOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = em.merge(oldCodigousuarioOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                }
            }
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura : tbusuario.getTbusuarioxgrupoxasignaturaCollection()) {
                Tbusuario oldCodigousuarioOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura = tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura.getCodigousuario();
                tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura.setCodigousuario(tbusuario);
                tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura = em.merge(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura);
                if (oldCodigousuarioOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura != null) {
                    oldCodigousuarioOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura);
                    oldCodigousuarioOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura = em.merge(oldCodigousuarioOfTbusuarioxgrupoxasignaturaCollectionTbusuarioxgrupoxasignatura);
                }
            }
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionTbencuestasxusuario : tbusuario.getTbencuestasxusuarioCollection()) {
                Tbusuario oldCodigousuarioOfTbencuestasxusuarioCollectionTbencuestasxusuario = tbencuestasxusuarioCollectionTbencuestasxusuario.getCodigousuario();
                tbencuestasxusuarioCollectionTbencuestasxusuario.setCodigousuario(tbusuario);
                tbencuestasxusuarioCollectionTbencuestasxusuario = em.merge(tbencuestasxusuarioCollectionTbencuestasxusuario);
                if (oldCodigousuarioOfTbencuestasxusuarioCollectionTbencuestasxusuario != null) {
                    oldCodigousuarioOfTbencuestasxusuarioCollectionTbencuestasxusuario.getTbencuestasxusuarioCollection().remove(tbencuestasxusuarioCollectionTbencuestasxusuario);
                    oldCodigousuarioOfTbencuestasxusuarioCollectionTbencuestasxusuario = em.merge(oldCodigousuarioOfTbencuestasxusuarioCollectionTbencuestasxusuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTbusuario(tbusuario.getCodigo()) != null) {
                throw new PreexistingEntityException("Tbusuario " + tbusuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbusuario tbusuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbusuario persistentTbusuario = em.find(Tbusuario.class, tbusuario.getCodigo());
            Tbtipo idtipoOld = persistentTbusuario.getIdtipo();
            Tbtipo idtipoNew = tbusuario.getIdtipo();
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionOld = persistentTbusuario.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionNew = tbusuario.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollectionOld = persistentTbusuario.getTbusuarioxgrupoxasignaturaCollection();
            Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollectionNew = tbusuario.getTbusuarioxgrupoxasignaturaCollection();
            Collection<Tbencuestasxusuario> tbencuestasxusuarioCollectionOld = persistentTbusuario.getTbencuestasxusuarioCollection();
            Collection<Tbencuestasxusuario> tbencuestasxusuarioCollectionNew = tbusuario.getTbencuestasxusuarioCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionOld) {
                if (!tbrespuestaxpreguntasxbloquesxencuestaCollectionNew.contains(tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbrespuestaxpreguntasxbloquesxencuesta " + tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta + " since its codigousuario field is not nullable.");
                }
            }
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionOldTbusuarioxgrupoxasignatura : tbusuarioxgrupoxasignaturaCollectionOld) {
                if (!tbusuarioxgrupoxasignaturaCollectionNew.contains(tbusuarioxgrupoxasignaturaCollectionOldTbusuarioxgrupoxasignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbusuarioxgrupoxasignatura " + tbusuarioxgrupoxasignaturaCollectionOldTbusuarioxgrupoxasignatura + " since its codigousuario field is not nullable.");
                }
            }
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionOldTbencuestasxusuario : tbencuestasxusuarioCollectionOld) {
                if (!tbencuestasxusuarioCollectionNew.contains(tbencuestasxusuarioCollectionOldTbencuestasxusuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbencuestasxusuario " + tbencuestasxusuarioCollectionOldTbencuestasxusuario + " since its codigousuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idtipoNew != null) {
                idtipoNew = em.getReference(idtipoNew.getClass(), idtipoNew.getId());
                tbusuario.setIdtipo(idtipoNew);
            }
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew = new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach : tbrespuestaxpreguntasxbloquesxencuestaCollectionNew) {
                tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach = em.getReference(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach.getClass(), tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach.getId());
                attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew.add(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach);
            }
            tbrespuestaxpreguntasxbloquesxencuestaCollectionNew = attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew;
            tbusuario.setTbrespuestaxpreguntasxbloquesxencuestaCollection(tbrespuestaxpreguntasxbloquesxencuestaCollectionNew);
            Collection<Tbusuarioxgrupoxasignatura> attachedTbusuarioxgrupoxasignaturaCollectionNew = new ArrayList<Tbusuarioxgrupoxasignatura>();
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach : tbusuarioxgrupoxasignaturaCollectionNew) {
                tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach = em.getReference(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach.getClass(), tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach.getId());
                attachedTbusuarioxgrupoxasignaturaCollectionNew.add(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignaturaToAttach);
            }
            tbusuarioxgrupoxasignaturaCollectionNew = attachedTbusuarioxgrupoxasignaturaCollectionNew;
            tbusuario.setTbusuarioxgrupoxasignaturaCollection(tbusuarioxgrupoxasignaturaCollectionNew);
            Collection<Tbencuestasxusuario> attachedTbencuestasxusuarioCollectionNew = new ArrayList<Tbencuestasxusuario>();
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach : tbencuestasxusuarioCollectionNew) {
                tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach = em.getReference(tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach.getClass(), tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach.getId());
                attachedTbencuestasxusuarioCollectionNew.add(tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach);
            }
            tbencuestasxusuarioCollectionNew = attachedTbencuestasxusuarioCollectionNew;
            tbusuario.setTbencuestasxusuarioCollection(tbencuestasxusuarioCollectionNew);
            tbusuario = em.merge(tbusuario);
            if (idtipoOld != null && !idtipoOld.equals(idtipoNew)) {
                idtipoOld.getTbusuarioCollection().remove(tbusuario);
                idtipoOld = em.merge(idtipoOld);
            }
            if (idtipoNew != null && !idtipoNew.equals(idtipoOld)) {
                idtipoNew.getTbusuarioCollection().add(tbusuario);
                idtipoNew = em.merge(idtipoNew);
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionNew) {
                if (!tbrespuestaxpreguntasxbloquesxencuestaCollectionOld.contains(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta)) {
                    Tbusuario oldCodigousuarioOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.getCodigousuario();
                    tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.setCodigousuario(tbusuario);
                    tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = em.merge(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                    if (oldCodigousuarioOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta != null && !oldCodigousuarioOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.equals(tbusuario)) {
                        oldCodigousuarioOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                        oldCodigousuarioOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = em.merge(oldCodigousuarioOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                    }
                }
            }
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura : tbusuarioxgrupoxasignaturaCollectionNew) {
                if (!tbusuarioxgrupoxasignaturaCollectionOld.contains(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura)) {
                    Tbusuario oldCodigousuarioOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura = tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.getCodigousuario();
                    tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.setCodigousuario(tbusuario);
                    tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura = em.merge(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura);
                    if (oldCodigousuarioOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura != null && !oldCodigousuarioOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.equals(tbusuario)) {
                        oldCodigousuarioOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura.getTbusuarioxgrupoxasignaturaCollection().remove(tbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura);
                        oldCodigousuarioOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura = em.merge(oldCodigousuarioOfTbusuarioxgrupoxasignaturaCollectionNewTbusuarioxgrupoxasignatura);
                    }
                }
            }
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionNewTbencuestasxusuario : tbencuestasxusuarioCollectionNew) {
                if (!tbencuestasxusuarioCollectionOld.contains(tbencuestasxusuarioCollectionNewTbencuestasxusuario)) {
                    Tbusuario oldCodigousuarioOfTbencuestasxusuarioCollectionNewTbencuestasxusuario = tbencuestasxusuarioCollectionNewTbencuestasxusuario.getCodigousuario();
                    tbencuestasxusuarioCollectionNewTbencuestasxusuario.setCodigousuario(tbusuario);
                    tbencuestasxusuarioCollectionNewTbencuestasxusuario = em.merge(tbencuestasxusuarioCollectionNewTbencuestasxusuario);
                    if (oldCodigousuarioOfTbencuestasxusuarioCollectionNewTbencuestasxusuario != null && !oldCodigousuarioOfTbencuestasxusuarioCollectionNewTbencuestasxusuario.equals(tbusuario)) {
                        oldCodigousuarioOfTbencuestasxusuarioCollectionNewTbencuestasxusuario.getTbencuestasxusuarioCollection().remove(tbencuestasxusuarioCollectionNewTbencuestasxusuario);
                        oldCodigousuarioOfTbencuestasxusuarioCollectionNewTbencuestasxusuario = em.merge(oldCodigousuarioOfTbencuestasxusuarioCollectionNewTbencuestasxusuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = tbusuario.getCodigo();
                if (findTbusuario(id) == null) {
                    throw new NonexistentEntityException("The tbusuario with id " + id + " no longer exists.");
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
            Tbusuario tbusuario;
            try {
                tbusuario = em.getReference(Tbusuario.class, id);
                tbusuario.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbusuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheck = tbusuario.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheckTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbusuario (" + tbusuario + ") cannot be destroyed since the Tbrespuestaxpreguntasxbloquesxencuesta " + tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheckTbrespuestaxpreguntasxbloquesxencuesta + " in its tbrespuestaxpreguntasxbloquesxencuestaCollection field has a non-nullable codigousuario field.");
            }
            Collection<Tbusuarioxgrupoxasignatura> tbusuarioxgrupoxasignaturaCollectionOrphanCheck = tbusuario.getTbusuarioxgrupoxasignaturaCollection();
            for (Tbusuarioxgrupoxasignatura tbusuarioxgrupoxasignaturaCollectionOrphanCheckTbusuarioxgrupoxasignatura : tbusuarioxgrupoxasignaturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbusuario (" + tbusuario + ") cannot be destroyed since the Tbusuarioxgrupoxasignatura " + tbusuarioxgrupoxasignaturaCollectionOrphanCheckTbusuarioxgrupoxasignatura + " in its tbusuarioxgrupoxasignaturaCollection field has a non-nullable codigousuario field.");
            }
            Collection<Tbencuestasxusuario> tbencuestasxusuarioCollectionOrphanCheck = tbusuario.getTbencuestasxusuarioCollection();
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionOrphanCheckTbencuestasxusuario : tbencuestasxusuarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbusuario (" + tbusuario + ") cannot be destroyed since the Tbencuestasxusuario " + tbencuestasxusuarioCollectionOrphanCheckTbencuestasxusuario + " in its tbencuestasxusuarioCollection field has a non-nullable codigousuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tbtipo idtipo = tbusuario.getIdtipo();
            if (idtipo != null) {
                idtipo.getTbusuarioCollection().remove(tbusuario);
                idtipo = em.merge(idtipo);
            }
            em.remove(tbusuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbusuario> findTbusuarioEntities() {
        return findTbusuarioEntities(true, -1, -1);
    }

    public List<Tbusuario> findTbusuarioEntities(int maxResults, int firstResult) {
        return findTbusuarioEntities(false, maxResults, firstResult);
    }

    private List<Tbusuario> findTbusuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbusuario.class));
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

    public Tbusuario findTbusuario(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbusuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbusuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbusuario> rt = cq.from(Tbusuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
