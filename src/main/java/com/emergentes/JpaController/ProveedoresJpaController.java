/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.JpaController;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.JpaClases.Compras;
import com.emergentes.JpaClases.Proveedores;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taery
 */
public class ProveedoresJpaController implements Serializable {

    public ProveedoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedores proveedores) {
        if (proveedores.getComprasList() == null) {
            proveedores.setComprasList(new ArrayList<Compras>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Compras> attachedComprasList = new ArrayList<Compras>();
            for (Compras comprasListComprasToAttach : proveedores.getComprasList()) {
                comprasListComprasToAttach = em.getReference(comprasListComprasToAttach.getClass(), comprasListComprasToAttach.getIdCompra());
                attachedComprasList.add(comprasListComprasToAttach);
            }
            proveedores.setComprasList(attachedComprasList);
            em.persist(proveedores);
            for (Compras comprasListCompras : proveedores.getComprasList()) {
                Proveedores oldIdProvOfComprasListCompras = comprasListCompras.getIdProv();
                comprasListCompras.setIdProv(proveedores);
                comprasListCompras = em.merge(comprasListCompras);
                if (oldIdProvOfComprasListCompras != null) {
                    oldIdProvOfComprasListCompras.getComprasList().remove(comprasListCompras);
                    oldIdProvOfComprasListCompras = em.merge(oldIdProvOfComprasListCompras);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedores proveedores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedores persistentProveedores = em.find(Proveedores.class, proveedores.getIdProv());
            List<Compras> comprasListOld = persistentProveedores.getComprasList();
            List<Compras> comprasListNew = proveedores.getComprasList();
            List<Compras> attachedComprasListNew = new ArrayList<Compras>();
            for (Compras comprasListNewComprasToAttach : comprasListNew) {
                comprasListNewComprasToAttach = em.getReference(comprasListNewComprasToAttach.getClass(), comprasListNewComprasToAttach.getIdCompra());
                attachedComprasListNew.add(comprasListNewComprasToAttach);
            }
            comprasListNew = attachedComprasListNew;
            proveedores.setComprasList(comprasListNew);
            proveedores = em.merge(proveedores);
            for (Compras comprasListOldCompras : comprasListOld) {
                if (!comprasListNew.contains(comprasListOldCompras)) {
                    comprasListOldCompras.setIdProv(null);
                    comprasListOldCompras = em.merge(comprasListOldCompras);
                }
            }
            for (Compras comprasListNewCompras : comprasListNew) {
                if (!comprasListOld.contains(comprasListNewCompras)) {
                    Proveedores oldIdProvOfComprasListNewCompras = comprasListNewCompras.getIdProv();
                    comprasListNewCompras.setIdProv(proveedores);
                    comprasListNewCompras = em.merge(comprasListNewCompras);
                    if (oldIdProvOfComprasListNewCompras != null && !oldIdProvOfComprasListNewCompras.equals(proveedores)) {
                        oldIdProvOfComprasListNewCompras.getComprasList().remove(comprasListNewCompras);
                        oldIdProvOfComprasListNewCompras = em.merge(oldIdProvOfComprasListNewCompras);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedores.getIdProv();
                if (findProveedores(id) == null) {
                    throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.");
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
            Proveedores proveedores;
            try {
                proveedores = em.getReference(Proveedores.class, id);
                proveedores.getIdProv();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedores with id " + id + " no longer exists.", enfe);
            }
            List<Compras> comprasList = proveedores.getComprasList();
            for (Compras comprasListCompras : comprasList) {
                comprasListCompras.setIdProv(null);
                comprasListCompras = em.merge(comprasListCompras);
            }
            em.remove(proveedores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedores> findProveedoresEntities() {
        return findProveedoresEntities(true, -1, -1);
    }

    public List<Proveedores> findProveedoresEntities(int maxResults, int firstResult) {
        return findProveedoresEntities(false, maxResults, firstResult);
    }

    private List<Proveedores> findProveedoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedores.class));
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

    public Proveedores findProveedores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedores.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedores> rt = cq.from(Proveedores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
