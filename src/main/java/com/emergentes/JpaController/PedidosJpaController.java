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
import com.emergentes.JpaClases.Almacen;
import com.emergentes.JpaClases.Empleados;
import com.emergentes.JpaClases.Pedidos;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taery
 */
public class PedidosJpaController implements Serializable {

    public PedidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedidos pedidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Almacen idObj = pedidos.getIdObj();
            if (idObj != null) {
                idObj = em.getReference(idObj.getClass(), idObj.getIdObj());
                pedidos.setIdObj(idObj);
            }
            Empleados idEmp = pedidos.getIdEmp();
            if (idEmp != null) {
                idEmp = em.getReference(idEmp.getClass(), idEmp.getIdEmp());
                pedidos.setIdEmp(idEmp);
            }
            em.persist(pedidos);
            if (idObj != null) {
                idObj.getPedidosList().add(pedidos);
                idObj = em.merge(idObj);
            }
            if (idEmp != null) {
                idEmp.getPedidosList().add(pedidos);
                idEmp = em.merge(idEmp);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedidos pedidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedidos persistentPedidos = em.find(Pedidos.class, pedidos.getIdPedido());
            Almacen idObjOld = persistentPedidos.getIdObj();
            Almacen idObjNew = pedidos.getIdObj();
            Empleados idEmpOld = persistentPedidos.getIdEmp();
            Empleados idEmpNew = pedidos.getIdEmp();
            if (idObjNew != null) {
                idObjNew = em.getReference(idObjNew.getClass(), idObjNew.getIdObj());
                pedidos.setIdObj(idObjNew);
            }
            if (idEmpNew != null) {
                idEmpNew = em.getReference(idEmpNew.getClass(), idEmpNew.getIdEmp());
                pedidos.setIdEmp(idEmpNew);
            }
            pedidos = em.merge(pedidos);
            if (idObjOld != null && !idObjOld.equals(idObjNew)) {
                idObjOld.getPedidosList().remove(pedidos);
                idObjOld = em.merge(idObjOld);
            }
            if (idObjNew != null && !idObjNew.equals(idObjOld)) {
                idObjNew.getPedidosList().add(pedidos);
                idObjNew = em.merge(idObjNew);
            }
            if (idEmpOld != null && !idEmpOld.equals(idEmpNew)) {
                idEmpOld.getPedidosList().remove(pedidos);
                idEmpOld = em.merge(idEmpOld);
            }
            if (idEmpNew != null && !idEmpNew.equals(idEmpOld)) {
                idEmpNew.getPedidosList().add(pedidos);
                idEmpNew = em.merge(idEmpNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pedidos.getIdPedido();
                if (findPedidos(id) == null) {
                    throw new NonexistentEntityException("The pedidos with id " + id + " no longer exists.");
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
            Pedidos pedidos;
            try {
                pedidos = em.getReference(Pedidos.class, id);
                pedidos.getIdPedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidos with id " + id + " no longer exists.", enfe);
            }
            Almacen idObj = pedidos.getIdObj();
            if (idObj != null) {
                idObj.getPedidosList().remove(pedidos);
                idObj = em.merge(idObj);
            }
            Empleados idEmp = pedidos.getIdEmp();
            if (idEmp != null) {
                idEmp.getPedidosList().remove(pedidos);
                idEmp = em.merge(idEmp);
            }
            em.remove(pedidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedidos> findPedidosEntities() {
        return findPedidosEntities(true, -1, -1);
    }

    public List<Pedidos> findPedidosEntities(int maxResults, int firstResult) {
        return findPedidosEntities(false, maxResults, firstResult);
    }

    private List<Pedidos> findPedidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedidos.class));
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

    public Pedidos findPedidos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedidos> rt = cq.from(Pedidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
