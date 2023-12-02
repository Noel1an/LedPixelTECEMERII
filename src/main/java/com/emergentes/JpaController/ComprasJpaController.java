/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.JpaController;

import com.emergentes.JpaClases.Compras;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.JpaClases.Proveedores;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taery
 */
public class ComprasJpaController implements Serializable {

    public ComprasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compras compras) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores idProv = compras.getIdProv();
            if (idProv != null) {
                idProv = em.getReference(idProv.getClass(), idProv.getIdProv());
                compras.setIdProv(idProv);
            }
            em.persist(compras);
            if (idProv != null) {
                idProv.getComprasList().add(compras);
                idProv = em.merge(idProv);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compras compras) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compras persistentCompras = em.find(Compras.class, compras.getIdCompra());
            Proveedores idProvOld = persistentCompras.getIdProv();
            Proveedores idProvNew = compras.getIdProv();
            if (idProvNew != null) {
                idProvNew = em.getReference(idProvNew.getClass(), idProvNew.getIdProv());
                compras.setIdProv(idProvNew);
            }
            compras = em.merge(compras);
            if (idProvOld != null && !idProvOld.equals(idProvNew)) {
                idProvOld.getComprasList().remove(compras);
                idProvOld = em.merge(idProvOld);
            }
            if (idProvNew != null && !idProvNew.equals(idProvOld)) {
                idProvNew.getComprasList().add(compras);
                idProvNew = em.merge(idProvNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compras.getIdCompra();
                if (findCompras(id) == null) {
                    throw new NonexistentEntityException("The compras with id " + id + " no longer exists.");
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
            Compras compras;
            try {
                compras = em.getReference(Compras.class, id);
                compras.getIdCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compras with id " + id + " no longer exists.", enfe);
            }
            Proveedores idProv = compras.getIdProv();
            if (idProv != null) {
                idProv.getComprasList().remove(compras);
                idProv = em.merge(idProv);
            }
            em.remove(compras);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compras> findComprasEntities() {
        return findComprasEntities(true, -1, -1);
    }

    public List<Compras> findComprasEntities(int maxResults, int firstResult) {
        return findComprasEntities(false, maxResults, firstResult);
    }

    private List<Compras> findComprasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compras.class));
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

    public Compras findCompras(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compras.class, id);
        } finally {
            em.close();
        }
    }

    public int getComprasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compras> rt = cq.from(Compras.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
