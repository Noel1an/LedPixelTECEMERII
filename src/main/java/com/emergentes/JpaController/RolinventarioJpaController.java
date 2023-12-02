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
import com.emergentes.JpaClases.Inventario;
import com.emergentes.JpaClases.Rolinventario;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taery
 */
public class RolinventarioJpaController implements Serializable {

    public RolinventarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rolinventario rolinventario) {
        if (rolinventario.getInventarioList() == null) {
            rolinventario.setInventarioList(new ArrayList<Inventario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Inventario> attachedInventarioList = new ArrayList<Inventario>();
            for (Inventario inventarioListInventarioToAttach : rolinventario.getInventarioList()) {
                inventarioListInventarioToAttach = em.getReference(inventarioListInventarioToAttach.getClass(), inventarioListInventarioToAttach.getIdInv());
                attachedInventarioList.add(inventarioListInventarioToAttach);
            }
            rolinventario.setInventarioList(attachedInventarioList);
            em.persist(rolinventario);
            for (Inventario inventarioListInventario : rolinventario.getInventarioList()) {
                Rolinventario oldRolInvOfInventarioListInventario = inventarioListInventario.getRolInv();
                inventarioListInventario.setRolInv(rolinventario);
                inventarioListInventario = em.merge(inventarioListInventario);
                if (oldRolInvOfInventarioListInventario != null) {
                    oldRolInvOfInventarioListInventario.getInventarioList().remove(inventarioListInventario);
                    oldRolInvOfInventarioListInventario = em.merge(oldRolInvOfInventarioListInventario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rolinventario rolinventario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rolinventario persistentRolinventario = em.find(Rolinventario.class, rolinventario.getIdRI());
            List<Inventario> inventarioListOld = persistentRolinventario.getInventarioList();
            List<Inventario> inventarioListNew = rolinventario.getInventarioList();
            List<Inventario> attachedInventarioListNew = new ArrayList<Inventario>();
            for (Inventario inventarioListNewInventarioToAttach : inventarioListNew) {
                inventarioListNewInventarioToAttach = em.getReference(inventarioListNewInventarioToAttach.getClass(), inventarioListNewInventarioToAttach.getIdInv());
                attachedInventarioListNew.add(inventarioListNewInventarioToAttach);
            }
            inventarioListNew = attachedInventarioListNew;
            rolinventario.setInventarioList(inventarioListNew);
            rolinventario = em.merge(rolinventario);
            for (Inventario inventarioListOldInventario : inventarioListOld) {
                if (!inventarioListNew.contains(inventarioListOldInventario)) {
                    inventarioListOldInventario.setRolInv(null);
                    inventarioListOldInventario = em.merge(inventarioListOldInventario);
                }
            }
            for (Inventario inventarioListNewInventario : inventarioListNew) {
                if (!inventarioListOld.contains(inventarioListNewInventario)) {
                    Rolinventario oldRolInvOfInventarioListNewInventario = inventarioListNewInventario.getRolInv();
                    inventarioListNewInventario.setRolInv(rolinventario);
                    inventarioListNewInventario = em.merge(inventarioListNewInventario);
                    if (oldRolInvOfInventarioListNewInventario != null && !oldRolInvOfInventarioListNewInventario.equals(rolinventario)) {
                        oldRolInvOfInventarioListNewInventario.getInventarioList().remove(inventarioListNewInventario);
                        oldRolInvOfInventarioListNewInventario = em.merge(oldRolInvOfInventarioListNewInventario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rolinventario.getIdRI();
                if (findRolinventario(id) == null) {
                    throw new NonexistentEntityException("The rolinventario with id " + id + " no longer exists.");
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
            Rolinventario rolinventario;
            try {
                rolinventario = em.getReference(Rolinventario.class, id);
                rolinventario.getIdRI();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolinventario with id " + id + " no longer exists.", enfe);
            }
            List<Inventario> inventarioList = rolinventario.getInventarioList();
            for (Inventario inventarioListInventario : inventarioList) {
                inventarioListInventario.setRolInv(null);
                inventarioListInventario = em.merge(inventarioListInventario);
            }
            em.remove(rolinventario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rolinventario> findRolinventarioEntities() {
        return findRolinventarioEntities(true, -1, -1);
    }

    public List<Rolinventario> findRolinventarioEntities(int maxResults, int firstResult) {
        return findRolinventarioEntities(false, maxResults, firstResult);
    }

    private List<Rolinventario> findRolinventarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rolinventario.class));
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

    public Rolinventario findRolinventario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rolinventario.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolinventarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rolinventario> rt = cq.from(Rolinventario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
