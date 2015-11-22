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
import modelo.Tbasignatura;
import modelo.Tbencuesta;
import modelo.Tbinformes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Tbrespuestaxpreguntasxbloquesxencuesta;
import modelo.Tbencuestasxusuario;
import modelo.Tbencuestaxasignatura;

/**
 *
 * @author Andres
 */
public class TbencuestaxasignaturaJpaController implements Serializable {

    public TbencuestaxasignaturaJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbencuestaxasignatura tbencuestaxasignatura) {
        if (tbencuestaxasignatura.getTbinformesCollection() == null) {
            tbencuestaxasignatura.setTbinformesCollection(new ArrayList<Tbinformes>());
        }
        if (tbencuestaxasignatura.getTbrespuestaxpreguntasxbloquesxencuestaCollection() == null) {
            tbencuestaxasignatura.setTbrespuestaxpreguntasxbloquesxencuestaCollection(new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>());
        }
        if (tbencuestaxasignatura.getTbencuestasxusuarioCollection() == null) {
            tbencuestaxasignatura.setTbencuestasxusuarioCollection(new ArrayList<Tbencuestasxusuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbasignatura idasignatura = tbencuestaxasignatura.getIdasignatura();
            if (idasignatura != null) {
                idasignatura = em.getReference(idasignatura.getClass(), idasignatura.getCodigo());
                tbencuestaxasignatura.setIdasignatura(idasignatura);
            }
            Tbencuesta idencuesta = tbencuestaxasignatura.getIdencuesta();
            if (idencuesta != null) {
                idencuesta = em.getReference(idencuesta.getClass(), idencuesta.getId());
                tbencuestaxasignatura.setIdencuesta(idencuesta);
            }
            Collection<Tbinformes> attachedTbinformesCollection = new ArrayList<Tbinformes>();
            for (Tbinformes tbinformesCollectionTbinformesToAttach : tbencuestaxasignatura.getTbinformesCollection()) {
                tbinformesCollectionTbinformesToAttach = em.getReference(tbinformesCollectionTbinformesToAttach.getClass(), tbinformesCollectionTbinformesToAttach.getId());
                attachedTbinformesCollection.add(tbinformesCollectionTbinformesToAttach);
            }
            tbencuestaxasignatura.setTbinformesCollection(attachedTbinformesCollection);
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> attachedTbrespuestaxpreguntasxbloquesxencuestaCollection = new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach : tbencuestaxasignatura.getTbrespuestaxpreguntasxbloquesxencuestaCollection()) {
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach = em.getReference(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach.getClass(), tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach.getId());
                attachedTbrespuestaxpreguntasxbloquesxencuestaCollection.add(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach);
            }
            tbencuestaxasignatura.setTbrespuestaxpreguntasxbloquesxencuestaCollection(attachedTbrespuestaxpreguntasxbloquesxencuestaCollection);
            Collection<Tbencuestasxusuario> attachedTbencuestasxusuarioCollection = new ArrayList<Tbencuestasxusuario>();
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach : tbencuestaxasignatura.getTbencuestasxusuarioCollection()) {
                tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach = em.getReference(tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach.getClass(), tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach.getId());
                attachedTbencuestasxusuarioCollection.add(tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach);
            }
            tbencuestaxasignatura.setTbencuestasxusuarioCollection(attachedTbencuestasxusuarioCollection);
            em.persist(tbencuestaxasignatura);
            if (idasignatura != null) {
                idasignatura.getTbencuestaxasignaturaCollection().add(tbencuestaxasignatura);
                idasignatura = em.merge(idasignatura);
            }
            if (idencuesta != null) {
                idencuesta.getTbencuestaxasignaturaCollection().add(tbencuestaxasignatura);
                idencuesta = em.merge(idencuesta);
            }
            for (Tbinformes tbinformesCollectionTbinformes : tbencuestaxasignatura.getTbinformesCollection()) {
                Tbencuestaxasignatura oldIdencuestaxasignaturaOfTbinformesCollectionTbinformes = tbinformesCollectionTbinformes.getIdencuestaxasignatura();
                tbinformesCollectionTbinformes.setIdencuestaxasignatura(tbencuestaxasignatura);
                tbinformesCollectionTbinformes = em.merge(tbinformesCollectionTbinformes);
                if (oldIdencuestaxasignaturaOfTbinformesCollectionTbinformes != null) {
                    oldIdencuestaxasignaturaOfTbinformesCollectionTbinformes.getTbinformesCollection().remove(tbinformesCollectionTbinformes);
                    oldIdencuestaxasignaturaOfTbinformesCollectionTbinformes = em.merge(oldIdencuestaxasignaturaOfTbinformesCollectionTbinformes);
                }
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta : tbencuestaxasignatura.getTbrespuestaxpreguntasxbloquesxencuestaCollection()) {
                Tbencuestaxasignatura oldIdencuestaxasignaturaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.getIdencuestaxasignatura();
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.setIdencuestaxasignatura(tbencuestaxasignatura);
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = em.merge(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                if (oldIdencuestaxasignaturaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta != null) {
                    oldIdencuestaxasignaturaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                    oldIdencuestaxasignaturaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = em.merge(oldIdencuestaxasignaturaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                }
            }
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionTbencuestasxusuario : tbencuestaxasignatura.getTbencuestasxusuarioCollection()) {
                Tbencuestaxasignatura oldIdencuestaxasignaturaOfTbencuestasxusuarioCollectionTbencuestasxusuario = tbencuestasxusuarioCollectionTbencuestasxusuario.getIdencuestaxasignatura();
                tbencuestasxusuarioCollectionTbencuestasxusuario.setIdencuestaxasignatura(tbencuestaxasignatura);
                tbencuestasxusuarioCollectionTbencuestasxusuario = em.merge(tbencuestasxusuarioCollectionTbencuestasxusuario);
                if (oldIdencuestaxasignaturaOfTbencuestasxusuarioCollectionTbencuestasxusuario != null) {
                    oldIdencuestaxasignaturaOfTbencuestasxusuarioCollectionTbencuestasxusuario.getTbencuestasxusuarioCollection().remove(tbencuestasxusuarioCollectionTbencuestasxusuario);
                    oldIdencuestaxasignaturaOfTbencuestasxusuarioCollectionTbencuestasxusuario = em.merge(oldIdencuestaxasignaturaOfTbencuestasxusuarioCollectionTbencuestasxusuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbencuestaxasignatura tbencuestaxasignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbencuestaxasignatura persistentTbencuestaxasignatura = em.find(Tbencuestaxasignatura.class, tbencuestaxasignatura.getId());
            Tbasignatura idasignaturaOld = persistentTbencuestaxasignatura.getIdasignatura();
            Tbasignatura idasignaturaNew = tbencuestaxasignatura.getIdasignatura();
            Tbencuesta idencuestaOld = persistentTbencuestaxasignatura.getIdencuesta();
            Tbencuesta idencuestaNew = tbencuestaxasignatura.getIdencuesta();
            Collection<Tbinformes> tbinformesCollectionOld = persistentTbencuestaxasignatura.getTbinformesCollection();
            Collection<Tbinformes> tbinformesCollectionNew = tbencuestaxasignatura.getTbinformesCollection();
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionOld = persistentTbencuestaxasignatura.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionNew = tbencuestaxasignatura.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            Collection<Tbencuestasxusuario> tbencuestasxusuarioCollectionOld = persistentTbencuestaxasignatura.getTbencuestasxusuarioCollection();
            Collection<Tbencuestasxusuario> tbencuestasxusuarioCollectionNew = tbencuestaxasignatura.getTbencuestasxusuarioCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbinformes tbinformesCollectionOldTbinformes : tbinformesCollectionOld) {
                if (!tbinformesCollectionNew.contains(tbinformesCollectionOldTbinformes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbinformes " + tbinformesCollectionOldTbinformes + " since its idencuestaxasignatura field is not nullable.");
                }
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionOld) {
                if (!tbrespuestaxpreguntasxbloquesxencuestaCollectionNew.contains(tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbrespuestaxpreguntasxbloquesxencuesta " + tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta + " since its idencuestaxasignatura field is not nullable.");
                }
            }
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionOldTbencuestasxusuario : tbencuestasxusuarioCollectionOld) {
                if (!tbencuestasxusuarioCollectionNew.contains(tbencuestasxusuarioCollectionOldTbencuestasxusuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbencuestasxusuario " + tbencuestasxusuarioCollectionOldTbencuestasxusuario + " since its idencuestaxasignatura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idasignaturaNew != null) {
                idasignaturaNew = em.getReference(idasignaturaNew.getClass(), idasignaturaNew.getCodigo());
                tbencuestaxasignatura.setIdasignatura(idasignaturaNew);
            }
            if (idencuestaNew != null) {
                idencuestaNew = em.getReference(idencuestaNew.getClass(), idencuestaNew.getId());
                tbencuestaxasignatura.setIdencuesta(idencuestaNew);
            }
            Collection<Tbinformes> attachedTbinformesCollectionNew = new ArrayList<Tbinformes>();
            for (Tbinformes tbinformesCollectionNewTbinformesToAttach : tbinformesCollectionNew) {
                tbinformesCollectionNewTbinformesToAttach = em.getReference(tbinformesCollectionNewTbinformesToAttach.getClass(), tbinformesCollectionNewTbinformesToAttach.getId());
                attachedTbinformesCollectionNew.add(tbinformesCollectionNewTbinformesToAttach);
            }
            tbinformesCollectionNew = attachedTbinformesCollectionNew;
            tbencuestaxasignatura.setTbinformesCollection(tbinformesCollectionNew);
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew = new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach : tbrespuestaxpreguntasxbloquesxencuestaCollectionNew) {
                tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach = em.getReference(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach.getClass(), tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach.getId());
                attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew.add(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach);
            }
            tbrespuestaxpreguntasxbloquesxencuestaCollectionNew = attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew;
            tbencuestaxasignatura.setTbrespuestaxpreguntasxbloquesxencuestaCollection(tbrespuestaxpreguntasxbloquesxencuestaCollectionNew);
            Collection<Tbencuestasxusuario> attachedTbencuestasxusuarioCollectionNew = new ArrayList<Tbencuestasxusuario>();
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach : tbencuestasxusuarioCollectionNew) {
                tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach = em.getReference(tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach.getClass(), tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach.getId());
                attachedTbencuestasxusuarioCollectionNew.add(tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach);
            }
            tbencuestasxusuarioCollectionNew = attachedTbencuestasxusuarioCollectionNew;
            tbencuestaxasignatura.setTbencuestasxusuarioCollection(tbencuestasxusuarioCollectionNew);
            tbencuestaxasignatura = em.merge(tbencuestaxasignatura);
            if (idasignaturaOld != null && !idasignaturaOld.equals(idasignaturaNew)) {
                idasignaturaOld.getTbencuestaxasignaturaCollection().remove(tbencuestaxasignatura);
                idasignaturaOld = em.merge(idasignaturaOld);
            }
            if (idasignaturaNew != null && !idasignaturaNew.equals(idasignaturaOld)) {
                idasignaturaNew.getTbencuestaxasignaturaCollection().add(tbencuestaxasignatura);
                idasignaturaNew = em.merge(idasignaturaNew);
            }
            if (idencuestaOld != null && !idencuestaOld.equals(idencuestaNew)) {
                idencuestaOld.getTbencuestaxasignaturaCollection().remove(tbencuestaxasignatura);
                idencuestaOld = em.merge(idencuestaOld);
            }
            if (idencuestaNew != null && !idencuestaNew.equals(idencuestaOld)) {
                idencuestaNew.getTbencuestaxasignaturaCollection().add(tbencuestaxasignatura);
                idencuestaNew = em.merge(idencuestaNew);
            }
            for (Tbinformes tbinformesCollectionNewTbinformes : tbinformesCollectionNew) {
                if (!tbinformesCollectionOld.contains(tbinformesCollectionNewTbinformes)) {
                    Tbencuestaxasignatura oldIdencuestaxasignaturaOfTbinformesCollectionNewTbinformes = tbinformesCollectionNewTbinformes.getIdencuestaxasignatura();
                    tbinformesCollectionNewTbinformes.setIdencuestaxasignatura(tbencuestaxasignatura);
                    tbinformesCollectionNewTbinformes = em.merge(tbinformesCollectionNewTbinformes);
                    if (oldIdencuestaxasignaturaOfTbinformesCollectionNewTbinformes != null && !oldIdencuestaxasignaturaOfTbinformesCollectionNewTbinformes.equals(tbencuestaxasignatura)) {
                        oldIdencuestaxasignaturaOfTbinformesCollectionNewTbinformes.getTbinformesCollection().remove(tbinformesCollectionNewTbinformes);
                        oldIdencuestaxasignaturaOfTbinformesCollectionNewTbinformes = em.merge(oldIdencuestaxasignaturaOfTbinformesCollectionNewTbinformes);
                    }
                }
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionNew) {
                if (!tbrespuestaxpreguntasxbloquesxencuestaCollectionOld.contains(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta)) {
                    Tbencuestaxasignatura oldIdencuestaxasignaturaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.getIdencuestaxasignatura();
                    tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.setIdencuestaxasignatura(tbencuestaxasignatura);
                    tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = em.merge(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                    if (oldIdencuestaxasignaturaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta != null && !oldIdencuestaxasignaturaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.equals(tbencuestaxasignatura)) {
                        oldIdencuestaxasignaturaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                        oldIdencuestaxasignaturaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = em.merge(oldIdencuestaxasignaturaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                    }
                }
            }
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionNewTbencuestasxusuario : tbencuestasxusuarioCollectionNew) {
                if (!tbencuestasxusuarioCollectionOld.contains(tbencuestasxusuarioCollectionNewTbencuestasxusuario)) {
                    Tbencuestaxasignatura oldIdencuestaxasignaturaOfTbencuestasxusuarioCollectionNewTbencuestasxusuario = tbencuestasxusuarioCollectionNewTbencuestasxusuario.getIdencuestaxasignatura();
                    tbencuestasxusuarioCollectionNewTbencuestasxusuario.setIdencuestaxasignatura(tbencuestaxasignatura);
                    tbencuestasxusuarioCollectionNewTbencuestasxusuario = em.merge(tbencuestasxusuarioCollectionNewTbencuestasxusuario);
                    if (oldIdencuestaxasignaturaOfTbencuestasxusuarioCollectionNewTbencuestasxusuario != null && !oldIdencuestaxasignaturaOfTbencuestasxusuarioCollectionNewTbencuestasxusuario.equals(tbencuestaxasignatura)) {
                        oldIdencuestaxasignaturaOfTbencuestasxusuarioCollectionNewTbencuestasxusuario.getTbencuestasxusuarioCollection().remove(tbencuestasxusuarioCollectionNewTbencuestasxusuario);
                        oldIdencuestaxasignaturaOfTbencuestasxusuarioCollectionNewTbencuestasxusuario = em.merge(oldIdencuestaxasignaturaOfTbencuestasxusuarioCollectionNewTbencuestasxusuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbencuestaxasignatura.getId();
                if (findTbencuestaxasignatura(id) == null) {
                    throw new NonexistentEntityException("The tbencuestaxasignatura with id " + id + " no longer exists.");
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
            Tbencuestaxasignatura tbencuestaxasignatura;
            try {
                tbencuestaxasignatura = em.getReference(Tbencuestaxasignatura.class, id);
                tbencuestaxasignatura.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbencuestaxasignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbinformes> tbinformesCollectionOrphanCheck = tbencuestaxasignatura.getTbinformesCollection();
            for (Tbinformes tbinformesCollectionOrphanCheckTbinformes : tbinformesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbencuestaxasignatura (" + tbencuestaxasignatura + ") cannot be destroyed since the Tbinformes " + tbinformesCollectionOrphanCheckTbinformes + " in its tbinformesCollection field has a non-nullable idencuestaxasignatura field.");
            }
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheck = tbencuestaxasignatura.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheckTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbencuestaxasignatura (" + tbencuestaxasignatura + ") cannot be destroyed since the Tbrespuestaxpreguntasxbloquesxencuesta " + tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheckTbrespuestaxpreguntasxbloquesxencuesta + " in its tbrespuestaxpreguntasxbloquesxencuestaCollection field has a non-nullable idencuestaxasignatura field.");
            }
            Collection<Tbencuestasxusuario> tbencuestasxusuarioCollectionOrphanCheck = tbencuestaxasignatura.getTbencuestasxusuarioCollection();
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionOrphanCheckTbencuestasxusuario : tbencuestasxusuarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbencuestaxasignatura (" + tbencuestaxasignatura + ") cannot be destroyed since the Tbencuestasxusuario " + tbencuestasxusuarioCollectionOrphanCheckTbencuestasxusuario + " in its tbencuestasxusuarioCollection field has a non-nullable idencuestaxasignatura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tbasignatura idasignatura = tbencuestaxasignatura.getIdasignatura();
            if (idasignatura != null) {
                idasignatura.getTbencuestaxasignaturaCollection().remove(tbencuestaxasignatura);
                idasignatura = em.merge(idasignatura);
            }
            Tbencuesta idencuesta = tbencuestaxasignatura.getIdencuesta();
            if (idencuesta != null) {
                idencuesta.getTbencuestaxasignaturaCollection().remove(tbencuestaxasignatura);
                idencuesta = em.merge(idencuesta);
            }
            em.remove(tbencuestaxasignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbencuestaxasignatura> findTbencuestaxasignaturaEntities() {
        return findTbencuestaxasignaturaEntities(true, -1, -1);
    }

    public List<Tbencuestaxasignatura> findTbencuestaxasignaturaEntities(int maxResults, int firstResult) {
        return findTbencuestaxasignaturaEntities(false, maxResults, firstResult);
    }

    private List<Tbencuestaxasignatura> findTbencuestaxasignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbencuestaxasignatura.class));
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

    public Tbencuestaxasignatura findTbencuestaxasignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbencuestaxasignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbencuestaxasignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbencuestaxasignatura> rt = cq.from(Tbencuestaxasignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
