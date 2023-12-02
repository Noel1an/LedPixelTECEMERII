/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.JpaController;

import com.emergentes.JpaClases.Inventario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.JpaClases.Rolinventario;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taery
 */
public class InventarioJpaController implements Serializable {

    public InventarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Inventario inventario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolinventario rolInv = inventario.getRolInv();
            if (rolInv != null) {
                rolInv = em.getReference(rolInv.getClass(), rolInv.getIdRI());
                inventario.setRolInv(rolInv);
            }
            em.persist(inventario);
            if (rolInv != null) {
                rolInv.getInventarioList().add(inventario);
                rolInv = em.merge(rolInv);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Inventario inventario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inventario persistentInventario = em.find(Inventario.class, inventario.getIdInv());
            Rolinventario rolInvOld = persistentInventario.getRolInv();
            Rolinventario rolInvNew = inventario.getRolInv();
            if (rolInvNew != null) {
                rolInvNew = em.getReference(rolInvNew.getClass(), rolInvNew.getIdRI());
                inventario.setRolInv(rolInvNew);
            }
            inventario = em.merge(inventario);
            if (rolInvOld != null && !rolInvOld.equals(rolInvNew)) {
                rolInvOld.getInventarioList().remove(inventario);
                rolInvOld = em.merge(rolInvOld);
            }
            if (rolInvNew != null && !rolInvNew.equals(rolInvOld)) {
                rolInvNew.getInventarioList().add(inventario);
                rolInvNew = em.merge(rolInvNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = inventario.getIdInv();
                if (findInventario(id) == null) {
                    throw new NonexistentEntityException("The inventario with id " + id + " no longer exists.");
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
            Inventario inventario;
            try {
                inventario = em.getReference(Inventario.class, id);
                inventario.getIdInv();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inventario with id " + id + " no longer exists.", enfe);
            }
            Rolinventario rolInv = inventario.getRolInv();
            if (rolInv != null) {
                rolInv.getInventarioList().remove(inventario);
                rolInv = em.merge(rolInv);
            }
            em.remove(inventario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Inventario> findInventarioEntities() {
        return findInventarioEntities(true, -1, -1);
    }

    public List<Inventario> findInventarioEntities(int maxResults, int firstResult) {
        return findInventarioEntities(false, maxResults, firstResult);
    }

    private List<Inventario> findInventarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Inventario.class));
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

    public Inventario findInventario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inventario.class, id);
        } finally {
            em.close();
        }
    }

    public int getInventarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Inventario> rt = cq.from(Inventario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
