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
import modelo.Tbgrupo;
import modelo.Tbprofesor;
import modelo.Tbinformes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Tbrespuestaxpreguntasxbloquesxencuesta;
import modelo.Tbencuestasxusuario;
import modelo.Tbgrupoxasignaturaxprofesor;

/**
 *
 * @author Andres
 */
public class TbgrupoxasignaturaxprofesorJpaController implements Serializable {

    public TbgrupoxasignaturaxprofesorJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesor) {
        if (tbgrupoxasignaturaxprofesor.getTbinformesCollection() == null) {
            tbgrupoxasignaturaxprofesor.setTbinformesCollection(new ArrayList<Tbinformes>());
        }
        if (tbgrupoxasignaturaxprofesor.getTbrespuestaxpreguntasxbloquesxencuestaCollection() == null) {
            tbgrupoxasignaturaxprofesor.setTbrespuestaxpreguntasxbloquesxencuestaCollection(new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>());
        }
        if (tbgrupoxasignaturaxprofesor.getTbencuestasxusuarioCollection() == null) {
            tbgrupoxasignaturaxprofesor.setTbencuestasxusuarioCollection(new ArrayList<Tbencuestasxusuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbasignatura idasignatura = tbgrupoxasignaturaxprofesor.getIdasignatura();
            if (idasignatura != null) {
                idasignatura = em.getReference(idasignatura.getClass(), idasignatura.getCodigo());
                tbgrupoxasignaturaxprofesor.setIdasignatura(idasignatura);
            }
            Tbgrupo idgrupo = tbgrupoxasignaturaxprofesor.getIdgrupo();
            if (idgrupo != null) {
                idgrupo = em.getReference(idgrupo.getClass(), idgrupo.getIdentificacion());
                tbgrupoxasignaturaxprofesor.setIdgrupo(idgrupo);
            }
            Tbprofesor cedula = tbgrupoxasignaturaxprofesor.getCedula();
            if (cedula != null) {
                cedula = em.getReference(cedula.getClass(), cedula.getCedula());
                tbgrupoxasignaturaxprofesor.setCedula(cedula);
            }
            Collection<Tbinformes> attachedTbinformesCollection = new ArrayList<Tbinformes>();
            for (Tbinformes tbinformesCollectionTbinformesToAttach : tbgrupoxasignaturaxprofesor.getTbinformesCollection()) {
                tbinformesCollectionTbinformesToAttach = em.getReference(tbinformesCollectionTbinformesToAttach.getClass(), tbinformesCollectionTbinformesToAttach.getId());
                attachedTbinformesCollection.add(tbinformesCollectionTbinformesToAttach);
            }
            tbgrupoxasignaturaxprofesor.setTbinformesCollection(attachedTbinformesCollection);
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> attachedTbrespuestaxpreguntasxbloquesxencuestaCollection = new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach : tbgrupoxasignaturaxprofesor.getTbrespuestaxpreguntasxbloquesxencuestaCollection()) {
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach = em.getReference(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach.getClass(), tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach.getId());
                attachedTbrespuestaxpreguntasxbloquesxencuestaCollection.add(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach);
            }
            tbgrupoxasignaturaxprofesor.setTbrespuestaxpreguntasxbloquesxencuestaCollection(attachedTbrespuestaxpreguntasxbloquesxencuestaCollection);
            Collection<Tbencuestasxusuario> attachedTbencuestasxusuarioCollection = new ArrayList<Tbencuestasxusuario>();
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach : tbgrupoxasignaturaxprofesor.getTbencuestasxusuarioCollection()) {
                tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach = em.getReference(tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach.getClass(), tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach.getId());
                attachedTbencuestasxusuarioCollection.add(tbencuestasxusuarioCollectionTbencuestasxusuarioToAttach);
            }
            tbgrupoxasignaturaxprofesor.setTbencuestasxusuarioCollection(attachedTbencuestasxusuarioCollection);
            em.persist(tbgrupoxasignaturaxprofesor);
            if (idasignatura != null) {
                idasignatura.getTbgrupoxasignaturaxprofesorCollection().add(tbgrupoxasignaturaxprofesor);
                idasignatura = em.merge(idasignatura);
            }
            if (idgrupo != null) {
                idgrupo.getTbgrupoxasignaturaxprofesorCollection().add(tbgrupoxasignaturaxprofesor);
                idgrupo = em.merge(idgrupo);
            }
            if (cedula != null) {
                cedula.getTbgrupoxasignaturaxprofesorCollection().add(tbgrupoxasignaturaxprofesor);
                cedula = em.merge(cedula);
            }
            for (Tbinformes tbinformesCollectionTbinformes : tbgrupoxasignaturaxprofesor.getTbinformesCollection()) {
                Tbgrupoxasignaturaxprofesor oldIdgrupoxasignaturaxprofesorOfTbinformesCollectionTbinformes = tbinformesCollectionTbinformes.getIdgrupoxasignaturaxprofesor();
                tbinformesCollectionTbinformes.setIdgrupoxasignaturaxprofesor(tbgrupoxasignaturaxprofesor);
                tbinformesCollectionTbinformes = em.merge(tbinformesCollectionTbinformes);
                if (oldIdgrupoxasignaturaxprofesorOfTbinformesCollectionTbinformes != null) {
                    oldIdgrupoxasignaturaxprofesorOfTbinformesCollectionTbinformes.getTbinformesCollection().remove(tbinformesCollectionTbinformes);
                    oldIdgrupoxasignaturaxprofesorOfTbinformesCollectionTbinformes = em.merge(oldIdgrupoxasignaturaxprofesorOfTbinformesCollectionTbinformes);
                }
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta : tbgrupoxasignaturaxprofesor.getTbrespuestaxpreguntasxbloquesxencuestaCollection()) {
                Tbgrupoxasignaturaxprofesor oldIdgrupoxasignaturaxprofesorOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.getIdgrupoxasignaturaxprofesor();
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.setIdgrupoxasignaturaxprofesor(tbgrupoxasignaturaxprofesor);
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = em.merge(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                if (oldIdgrupoxasignaturaxprofesorOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta != null) {
                    oldIdgrupoxasignaturaxprofesorOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                    oldIdgrupoxasignaturaxprofesorOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = em.merge(oldIdgrupoxasignaturaxprofesorOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                }
            }
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionTbencuestasxusuario : tbgrupoxasignaturaxprofesor.getTbencuestasxusuarioCollection()) {
                Tbgrupoxasignaturaxprofesor oldIdgrupoxasignaturaxprofesorOfTbencuestasxusuarioCollectionTbencuestasxusuario = tbencuestasxusuarioCollectionTbencuestasxusuario.getIdgrupoxasignaturaxprofesor();
                tbencuestasxusuarioCollectionTbencuestasxusuario.setIdgrupoxasignaturaxprofesor(tbgrupoxasignaturaxprofesor);
                tbencuestasxusuarioCollectionTbencuestasxusuario = em.merge(tbencuestasxusuarioCollectionTbencuestasxusuario);
                if (oldIdgrupoxasignaturaxprofesorOfTbencuestasxusuarioCollectionTbencuestasxusuario != null) {
                    oldIdgrupoxasignaturaxprofesorOfTbencuestasxusuarioCollectionTbencuestasxusuario.getTbencuestasxusuarioCollection().remove(tbencuestasxusuarioCollectionTbencuestasxusuario);
                    oldIdgrupoxasignaturaxprofesorOfTbencuestasxusuarioCollectionTbencuestasxusuario = em.merge(oldIdgrupoxasignaturaxprofesorOfTbencuestasxusuarioCollectionTbencuestasxusuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbgrupoxasignaturaxprofesor persistentTbgrupoxasignaturaxprofesor = em.find(Tbgrupoxasignaturaxprofesor.class, tbgrupoxasignaturaxprofesor.getId());
            Tbasignatura idasignaturaOld = persistentTbgrupoxasignaturaxprofesor.getIdasignatura();
            Tbasignatura idasignaturaNew = tbgrupoxasignaturaxprofesor.getIdasignatura();
            Tbgrupo idgrupoOld = persistentTbgrupoxasignaturaxprofesor.getIdgrupo();
            Tbgrupo idgrupoNew = tbgrupoxasignaturaxprofesor.getIdgrupo();
            Tbprofesor cedulaOld = persistentTbgrupoxasignaturaxprofesor.getCedula();
            Tbprofesor cedulaNew = tbgrupoxasignaturaxprofesor.getCedula();
            Collection<Tbinformes> tbinformesCollectionOld = persistentTbgrupoxasignaturaxprofesor.getTbinformesCollection();
            Collection<Tbinformes> tbinformesCollectionNew = tbgrupoxasignaturaxprofesor.getTbinformesCollection();
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionOld = persistentTbgrupoxasignaturaxprofesor.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionNew = tbgrupoxasignaturaxprofesor.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            Collection<Tbencuestasxusuario> tbencuestasxusuarioCollectionOld = persistentTbgrupoxasignaturaxprofesor.getTbencuestasxusuarioCollection();
            Collection<Tbencuestasxusuario> tbencuestasxusuarioCollectionNew = tbgrupoxasignaturaxprofesor.getTbencuestasxusuarioCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbinformes tbinformesCollectionOldTbinformes : tbinformesCollectionOld) {
                if (!tbinformesCollectionNew.contains(tbinformesCollectionOldTbinformes)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbinformes " + tbinformesCollectionOldTbinformes + " since its idgrupoxasignaturaxprofesor field is not nullable.");
                }
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionOld) {
                if (!tbrespuestaxpreguntasxbloquesxencuestaCollectionNew.contains(tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbrespuestaxpreguntasxbloquesxencuesta " + tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta + " since its idgrupoxasignaturaxprofesor field is not nullable.");
                }
            }
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionOldTbencuestasxusuario : tbencuestasxusuarioCollectionOld) {
                if (!tbencuestasxusuarioCollectionNew.contains(tbencuestasxusuarioCollectionOldTbencuestasxusuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbencuestasxusuario " + tbencuestasxusuarioCollectionOldTbencuestasxusuario + " since its idgrupoxasignaturaxprofesor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idasignaturaNew != null) {
                idasignaturaNew = em.getReference(idasignaturaNew.getClass(), idasignaturaNew.getCodigo());
                tbgrupoxasignaturaxprofesor.setIdasignatura(idasignaturaNew);
            }
            if (idgrupoNew != null) {
                idgrupoNew = em.getReference(idgrupoNew.getClass(), idgrupoNew.getIdentificacion());
                tbgrupoxasignaturaxprofesor.setIdgrupo(idgrupoNew);
            }
            if (cedulaNew != null) {
                cedulaNew = em.getReference(cedulaNew.getClass(), cedulaNew.getCedula());
                tbgrupoxasignaturaxprofesor.setCedula(cedulaNew);
            }
            Collection<Tbinformes> attachedTbinformesCollectionNew = new ArrayList<Tbinformes>();
            for (Tbinformes tbinformesCollectionNewTbinformesToAttach : tbinformesCollectionNew) {
                tbinformesCollectionNewTbinformesToAttach = em.getReference(tbinformesCollectionNewTbinformesToAttach.getClass(), tbinformesCollectionNewTbinformesToAttach.getId());
                attachedTbinformesCollectionNew.add(tbinformesCollectionNewTbinformesToAttach);
            }
            tbinformesCollectionNew = attachedTbinformesCollectionNew;
            tbgrupoxasignaturaxprofesor.setTbinformesCollection(tbinformesCollectionNew);
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew = new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach : tbrespuestaxpreguntasxbloquesxencuestaCollectionNew) {
                tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach = em.getReference(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach.getClass(), tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach.getId());
                attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew.add(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach);
            }
            tbrespuestaxpreguntasxbloquesxencuestaCollectionNew = attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew;
            tbgrupoxasignaturaxprofesor.setTbrespuestaxpreguntasxbloquesxencuestaCollection(tbrespuestaxpreguntasxbloquesxencuestaCollectionNew);
            Collection<Tbencuestasxusuario> attachedTbencuestasxusuarioCollectionNew = new ArrayList<Tbencuestasxusuario>();
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach : tbencuestasxusuarioCollectionNew) {
                tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach = em.getReference(tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach.getClass(), tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach.getId());
                attachedTbencuestasxusuarioCollectionNew.add(tbencuestasxusuarioCollectionNewTbencuestasxusuarioToAttach);
            }
            tbencuestasxusuarioCollectionNew = attachedTbencuestasxusuarioCollectionNew;
            tbgrupoxasignaturaxprofesor.setTbencuestasxusuarioCollection(tbencuestasxusuarioCollectionNew);
            tbgrupoxasignaturaxprofesor = em.merge(tbgrupoxasignaturaxprofesor);
            if (idasignaturaOld != null && !idasignaturaOld.equals(idasignaturaNew)) {
                idasignaturaOld.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesor);
                idasignaturaOld = em.merge(idasignaturaOld);
            }
            if (idasignaturaNew != null && !idasignaturaNew.equals(idasignaturaOld)) {
                idasignaturaNew.getTbgrupoxasignaturaxprofesorCollection().add(tbgrupoxasignaturaxprofesor);
                idasignaturaNew = em.merge(idasignaturaNew);
            }
            if (idgrupoOld != null && !idgrupoOld.equals(idgrupoNew)) {
                idgrupoOld.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesor);
                idgrupoOld = em.merge(idgrupoOld);
            }
            if (idgrupoNew != null && !idgrupoNew.equals(idgrupoOld)) {
                idgrupoNew.getTbgrupoxasignaturaxprofesorCollection().add(tbgrupoxasignaturaxprofesor);
                idgrupoNew = em.merge(idgrupoNew);
            }
            if (cedulaOld != null && !cedulaOld.equals(cedulaNew)) {
                cedulaOld.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesor);
                cedulaOld = em.merge(cedulaOld);
            }
            if (cedulaNew != null && !cedulaNew.equals(cedulaOld)) {
                cedulaNew.getTbgrupoxasignaturaxprofesorCollection().add(tbgrupoxasignaturaxprofesor);
                cedulaNew = em.merge(cedulaNew);
            }
            for (Tbinformes tbinformesCollectionNewTbinformes : tbinformesCollectionNew) {
                if (!tbinformesCollectionOld.contains(tbinformesCollectionNewTbinformes)) {
                    Tbgrupoxasignaturaxprofesor oldIdgrupoxasignaturaxprofesorOfTbinformesCollectionNewTbinformes = tbinformesCollectionNewTbinformes.getIdgrupoxasignaturaxprofesor();
                    tbinformesCollectionNewTbinformes.setIdgrupoxasignaturaxprofesor(tbgrupoxasignaturaxprofesor);
                    tbinformesCollectionNewTbinformes = em.merge(tbinformesCollectionNewTbinformes);
                    if (oldIdgrupoxasignaturaxprofesorOfTbinformesCollectionNewTbinformes != null && !oldIdgrupoxasignaturaxprofesorOfTbinformesCollectionNewTbinformes.equals(tbgrupoxasignaturaxprofesor)) {
                        oldIdgrupoxasignaturaxprofesorOfTbinformesCollectionNewTbinformes.getTbinformesCollection().remove(tbinformesCollectionNewTbinformes);
                        oldIdgrupoxasignaturaxprofesorOfTbinformesCollectionNewTbinformes = em.merge(oldIdgrupoxasignaturaxprofesorOfTbinformesCollectionNewTbinformes);
                    }
                }
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionNew) {
                if (!tbrespuestaxpreguntasxbloquesxencuestaCollectionOld.contains(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta)) {
                    Tbgrupoxasignaturaxprofesor oldIdgrupoxasignaturaxprofesorOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.getIdgrupoxasignaturaxprofesor();
                    tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.setIdgrupoxasignaturaxprofesor(tbgrupoxasignaturaxprofesor);
                    tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = em.merge(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                    if (oldIdgrupoxasignaturaxprofesorOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta != null && !oldIdgrupoxasignaturaxprofesorOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.equals(tbgrupoxasignaturaxprofesor)) {
                        oldIdgrupoxasignaturaxprofesorOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                        oldIdgrupoxasignaturaxprofesorOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = em.merge(oldIdgrupoxasignaturaxprofesorOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                    }
                }
            }
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionNewTbencuestasxusuario : tbencuestasxusuarioCollectionNew) {
                if (!tbencuestasxusuarioCollectionOld.contains(tbencuestasxusuarioCollectionNewTbencuestasxusuario)) {
                    Tbgrupoxasignaturaxprofesor oldIdgrupoxasignaturaxprofesorOfTbencuestasxusuarioCollectionNewTbencuestasxusuario = tbencuestasxusuarioCollectionNewTbencuestasxusuario.getIdgrupoxasignaturaxprofesor();
                    tbencuestasxusuarioCollectionNewTbencuestasxusuario.setIdgrupoxasignaturaxprofesor(tbgrupoxasignaturaxprofesor);
                    tbencuestasxusuarioCollectionNewTbencuestasxusuario = em.merge(tbencuestasxusuarioCollectionNewTbencuestasxusuario);
                    if (oldIdgrupoxasignaturaxprofesorOfTbencuestasxusuarioCollectionNewTbencuestasxusuario != null && !oldIdgrupoxasignaturaxprofesorOfTbencuestasxusuarioCollectionNewTbencuestasxusuario.equals(tbgrupoxasignaturaxprofesor)) {
                        oldIdgrupoxasignaturaxprofesorOfTbencuestasxusuarioCollectionNewTbencuestasxusuario.getTbencuestasxusuarioCollection().remove(tbencuestasxusuarioCollectionNewTbencuestasxusuario);
                        oldIdgrupoxasignaturaxprofesorOfTbencuestasxusuarioCollectionNewTbencuestasxusuario = em.merge(oldIdgrupoxasignaturaxprofesorOfTbencuestasxusuarioCollectionNewTbencuestasxusuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbgrupoxasignaturaxprofesor.getId();
                if (findTbgrupoxasignaturaxprofesor(id) == null) {
                    throw new NonexistentEntityException("The tbgrupoxasignaturaxprofesor with id " + id + " no longer exists.");
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
            Tbgrupoxasignaturaxprofesor tbgrupoxasignaturaxprofesor;
            try {
                tbgrupoxasignaturaxprofesor = em.getReference(Tbgrupoxasignaturaxprofesor.class, id);
                tbgrupoxasignaturaxprofesor.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbgrupoxasignaturaxprofesor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbinformes> tbinformesCollectionOrphanCheck = tbgrupoxasignaturaxprofesor.getTbinformesCollection();
            for (Tbinformes tbinformesCollectionOrphanCheckTbinformes : tbinformesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbgrupoxasignaturaxprofesor (" + tbgrupoxasignaturaxprofesor + ") cannot be destroyed since the Tbinformes " + tbinformesCollectionOrphanCheckTbinformes + " in its tbinformesCollection field has a non-nullable idgrupoxasignaturaxprofesor field.");
            }
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheck = tbgrupoxasignaturaxprofesor.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheckTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbgrupoxasignaturaxprofesor (" + tbgrupoxasignaturaxprofesor + ") cannot be destroyed since the Tbrespuestaxpreguntasxbloquesxencuesta " + tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheckTbrespuestaxpreguntasxbloquesxencuesta + " in its tbrespuestaxpreguntasxbloquesxencuestaCollection field has a non-nullable idgrupoxasignaturaxprofesor field.");
            }
            Collection<Tbencuestasxusuario> tbencuestasxusuarioCollectionOrphanCheck = tbgrupoxasignaturaxprofesor.getTbencuestasxusuarioCollection();
            for (Tbencuestasxusuario tbencuestasxusuarioCollectionOrphanCheckTbencuestasxusuario : tbencuestasxusuarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbgrupoxasignaturaxprofesor (" + tbgrupoxasignaturaxprofesor + ") cannot be destroyed since the Tbencuestasxusuario " + tbencuestasxusuarioCollectionOrphanCheckTbencuestasxusuario + " in its tbencuestasxusuarioCollection field has a non-nullable idgrupoxasignaturaxprofesor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tbasignatura idasignatura = tbgrupoxasignaturaxprofesor.getIdasignatura();
            if (idasignatura != null) {
                idasignatura.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesor);
                idasignatura = em.merge(idasignatura);
            }
            Tbgrupo idgrupo = tbgrupoxasignaturaxprofesor.getIdgrupo();
            if (idgrupo != null) {
                idgrupo.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesor);
                idgrupo = em.merge(idgrupo);
            }
            Tbprofesor cedula = tbgrupoxasignaturaxprofesor.getCedula();
            if (cedula != null) {
                cedula.getTbgrupoxasignaturaxprofesorCollection().remove(tbgrupoxasignaturaxprofesor);
                cedula = em.merge(cedula);
            }
            em.remove(tbgrupoxasignaturaxprofesor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbgrupoxasignaturaxprofesor> findTbgrupoxasignaturaxprofesorEntities() {
        return findTbgrupoxasignaturaxprofesorEntities(true, -1, -1);
    }

    public List<Tbgrupoxasignaturaxprofesor> findTbgrupoxasignaturaxprofesorEntities(int maxResults, int firstResult) {
        return findTbgrupoxasignaturaxprofesorEntities(false, maxResults, firstResult);
    }

    private List<Tbgrupoxasignaturaxprofesor> findTbgrupoxasignaturaxprofesorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbgrupoxasignaturaxprofesor.class));
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

    public Tbgrupoxasignaturaxprofesor findTbgrupoxasignaturaxprofesor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbgrupoxasignaturaxprofesor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbgrupoxasignaturaxprofesorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbgrupoxasignaturaxprofesor> rt = cq.from(Tbgrupoxasignaturaxprofesor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
