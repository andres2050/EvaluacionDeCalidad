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
import modelo.Tbbloque;
import modelo.Tbpregunta;
import modelo.Tbrespuestaxpreguntasxbloquesxencuesta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Tbbloquesxpregunta;

/**
 *
 * @author Andres
 */
public class TbbloquesxpreguntaJpaController implements Serializable {

    public TbbloquesxpreguntaJpaController() {
        emf = Persistence.createEntityManagerFactory("EvaluacionPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tbbloquesxpregunta tbbloquesxpregunta) {
        if (tbbloquesxpregunta.getTbrespuestaxpreguntasxbloquesxencuestaCollection() == null) {
            tbbloquesxpregunta.setTbrespuestaxpreguntasxbloquesxencuestaCollection(new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbbloque idbloque = tbbloquesxpregunta.getIdbloque();
            if (idbloque != null) {
                idbloque = em.getReference(idbloque.getClass(), idbloque.getId());
                tbbloquesxpregunta.setIdbloque(idbloque);
            }
            Tbpregunta idpregunta = tbbloquesxpregunta.getIdpregunta();
            if (idpregunta != null) {
                idpregunta = em.getReference(idpregunta.getClass(), idpregunta.getId());
                tbbloquesxpregunta.setIdpregunta(idpregunta);
            }
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> attachedTbrespuestaxpreguntasxbloquesxencuestaCollection = new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach : tbbloquesxpregunta.getTbrespuestaxpreguntasxbloquesxencuestaCollection()) {
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach = em.getReference(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach.getClass(), tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach.getId());
                attachedTbrespuestaxpreguntasxbloquesxencuestaCollection.add(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuestaToAttach);
            }
            tbbloquesxpregunta.setTbrespuestaxpreguntasxbloquesxencuestaCollection(attachedTbrespuestaxpreguntasxbloquesxencuestaCollection);
            em.persist(tbbloquesxpregunta);
            if (idbloque != null) {
                idbloque.getTbbloquesxpreguntaCollection().add(tbbloquesxpregunta);
                idbloque = em.merge(idbloque);
            }
            if (idpregunta != null) {
                idpregunta.getTbbloquesxpreguntaCollection().add(tbbloquesxpregunta);
                idpregunta = em.merge(idpregunta);
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta : tbbloquesxpregunta.getTbrespuestaxpreguntasxbloquesxencuestaCollection()) {
                Tbbloquesxpregunta oldIdbloquexpreguntaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexpregunta();
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.setIdbloquexpregunta(tbbloquesxpregunta);
                tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = em.merge(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                if (oldIdbloquexpreguntaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta != null) {
                    oldIdbloquexpreguntaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                    oldIdbloquexpreguntaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta = em.merge(oldIdbloquexpreguntaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionTbrespuestaxpreguntasxbloquesxencuesta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tbbloquesxpregunta tbbloquesxpregunta) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Tbbloquesxpregunta persistentTbbloquesxpregunta = em.find(Tbbloquesxpregunta.class, tbbloquesxpregunta.getId());
            Tbbloque idbloqueOld = persistentTbbloquesxpregunta.getIdbloque();
            Tbbloque idbloqueNew = tbbloquesxpregunta.getIdbloque();
            Tbpregunta idpreguntaOld = persistentTbbloquesxpregunta.getIdpregunta();
            Tbpregunta idpreguntaNew = tbbloquesxpregunta.getIdpregunta();
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionOld = persistentTbbloquesxpregunta.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionNew = tbbloquesxpregunta.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            List<String> illegalOrphanMessages = null;
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionOld) {
                if (!tbrespuestaxpreguntasxbloquesxencuestaCollectionNew.contains(tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tbrespuestaxpreguntasxbloquesxencuesta " + tbrespuestaxpreguntasxbloquesxencuestaCollectionOldTbrespuestaxpreguntasxbloquesxencuesta + " since its idbloquexpregunta field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idbloqueNew != null) {
                idbloqueNew = em.getReference(idbloqueNew.getClass(), idbloqueNew.getId());
                tbbloquesxpregunta.setIdbloque(idbloqueNew);
            }
            if (idpreguntaNew != null) {
                idpreguntaNew = em.getReference(idpreguntaNew.getClass(), idpreguntaNew.getId());
                tbbloquesxpregunta.setIdpregunta(idpreguntaNew);
            }
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew = new ArrayList<Tbrespuestaxpreguntasxbloquesxencuesta>();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach : tbrespuestaxpreguntasxbloquesxencuestaCollectionNew) {
                tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach = em.getReference(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach.getClass(), tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach.getId());
                attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew.add(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuestaToAttach);
            }
            tbrespuestaxpreguntasxbloquesxencuestaCollectionNew = attachedTbrespuestaxpreguntasxbloquesxencuestaCollectionNew;
            tbbloquesxpregunta.setTbrespuestaxpreguntasxbloquesxencuestaCollection(tbrespuestaxpreguntasxbloquesxencuestaCollectionNew);
            tbbloquesxpregunta = em.merge(tbbloquesxpregunta);
            if (idbloqueOld != null && !idbloqueOld.equals(idbloqueNew)) {
                idbloqueOld.getTbbloquesxpreguntaCollection().remove(tbbloquesxpregunta);
                idbloqueOld = em.merge(idbloqueOld);
            }
            if (idbloqueNew != null && !idbloqueNew.equals(idbloqueOld)) {
                idbloqueNew.getTbbloquesxpreguntaCollection().add(tbbloquesxpregunta);
                idbloqueNew = em.merge(idbloqueNew);
            }
            if (idpreguntaOld != null && !idpreguntaOld.equals(idpreguntaNew)) {
                idpreguntaOld.getTbbloquesxpreguntaCollection().remove(tbbloquesxpregunta);
                idpreguntaOld = em.merge(idpreguntaOld);
            }
            if (idpreguntaNew != null && !idpreguntaNew.equals(idpreguntaOld)) {
                idpreguntaNew.getTbbloquesxpreguntaCollection().add(tbbloquesxpregunta);
                idpreguntaNew = em.merge(idpreguntaNew);
            }
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionNew) {
                if (!tbrespuestaxpreguntasxbloquesxencuestaCollectionOld.contains(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta)) {
                    Tbbloquesxpregunta oldIdbloquexpreguntaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.getIdbloquexpregunta();
                    tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.setIdbloquexpregunta(tbbloquesxpregunta);
                    tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = em.merge(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                    if (oldIdbloquexpreguntaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta != null && !oldIdbloquexpreguntaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.equals(tbbloquesxpregunta)) {
                        oldIdbloquexpreguntaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta.getTbrespuestaxpreguntasxbloquesxencuestaCollection().remove(tbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                        oldIdbloquexpreguntaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta = em.merge(oldIdbloquexpreguntaOfTbrespuestaxpreguntasxbloquesxencuestaCollectionNewTbrespuestaxpreguntasxbloquesxencuesta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tbbloquesxpregunta.getId();
                if (findTbbloquesxpregunta(id) == null) {
                    throw new NonexistentEntityException("The tbbloquesxpregunta with id " + id + " no longer exists.");
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
            Tbbloquesxpregunta tbbloquesxpregunta;
            try {
                tbbloquesxpregunta = em.getReference(Tbbloquesxpregunta.class, id);
                tbbloquesxpregunta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tbbloquesxpregunta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Tbrespuestaxpreguntasxbloquesxencuesta> tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheck = tbbloquesxpregunta.getTbrespuestaxpreguntasxbloquesxencuestaCollection();
            for (Tbrespuestaxpreguntasxbloquesxencuesta tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheckTbrespuestaxpreguntasxbloquesxencuesta : tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tbbloquesxpregunta (" + tbbloquesxpregunta + ") cannot be destroyed since the Tbrespuestaxpreguntasxbloquesxencuesta " + tbrespuestaxpreguntasxbloquesxencuestaCollectionOrphanCheckTbrespuestaxpreguntasxbloquesxencuesta + " in its tbrespuestaxpreguntasxbloquesxencuestaCollection field has a non-nullable idbloquexpregunta field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tbbloque idbloque = tbbloquesxpregunta.getIdbloque();
            if (idbloque != null) {
                idbloque.getTbbloquesxpreguntaCollection().remove(tbbloquesxpregunta);
                idbloque = em.merge(idbloque);
            }
            Tbpregunta idpregunta = tbbloquesxpregunta.getIdpregunta();
            if (idpregunta != null) {
                idpregunta.getTbbloquesxpreguntaCollection().remove(tbbloquesxpregunta);
                idpregunta = em.merge(idpregunta);
            }
            em.remove(tbbloquesxpregunta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tbbloquesxpregunta> findTbbloquesxpreguntaEntities() {
        return findTbbloquesxpreguntaEntities(true, -1, -1);
    }

    public List<Tbbloquesxpregunta> findTbbloquesxpreguntaEntities(int maxResults, int firstResult) {
        return findTbbloquesxpreguntaEntities(false, maxResults, firstResult);
    }

    private List<Tbbloquesxpregunta> findTbbloquesxpreguntaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tbbloquesxpregunta.class));
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

    public Tbbloquesxpregunta findTbbloquesxpregunta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tbbloquesxpregunta.class, id);
        } finally {
            em.close();
        }
    }

    public int getTbbloquesxpreguntaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tbbloquesxpregunta> rt = cq.from(Tbbloquesxpregunta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
