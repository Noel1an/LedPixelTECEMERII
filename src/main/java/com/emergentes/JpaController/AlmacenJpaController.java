/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.JpaController;

import com.emergentes.JpaClases.Almacen;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.JpaClases.Rentas;
import java.util.ArrayList;
import java.util.List;
import com.emergentes.JpaClases.Ventas;
import com.emergentes.JpaClases.Pedidos;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taery
 */
public class AlmacenJpaController implements Serializable {

    public AlmacenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Almacen almacen) {
        if (almacen.getRentasList() == null) {
            almacen.setRentasList(new ArrayList<Rentas>());
        }
        if (almacen.getVentasList() == null) {
            almacen.setVentasList(new ArrayList<Ventas>());
        }
        if (almacen.getPedidosList() == null) {
            almacen.setPedidosList(new ArrayList<Pedidos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Rentas> attachedRentasList = new ArrayList<Rentas>();
            for (Rentas rentasListRentasToAttach : almacen.getRentasList()) {
                rentasListRentasToAttach = em.getReference(rentasListRentasToAttach.getClass(), rentasListRentasToAttach.getIdRen());
                attachedRentasList.add(rentasListRentasToAttach);
            }
            almacen.setRentasList(attachedRentasList);
            List<Ventas> attachedVentasList = new ArrayList<Ventas>();
            for (Ventas ventasListVentasToAttach : almacen.getVentasList()) {
                ventasListVentasToAttach = em.getReference(ventasListVentasToAttach.getClass(), ventasListVentasToAttach.getIdVen());
                attachedVentasList.add(ventasListVentasToAttach);
            }
            almacen.setVentasList(attachedVentasList);
            List<Pedidos> attachedPedidosList = new ArrayList<Pedidos>();
            for (Pedidos pedidosListPedidosToAttach : almacen.getPedidosList()) {
                pedidosListPedidosToAttach = em.getReference(pedidosListPedidosToAttach.getClass(), pedidosListPedidosToAttach.getIdPedido());
                attachedPedidosList.add(pedidosListPedidosToAttach);
            }
            almacen.setPedidosList(attachedPedidosList);
            em.persist(almacen);
            for (Rentas rentasListRentas : almacen.getRentasList()) {
                Almacen oldIdObjRenOfRentasListRentas = rentasListRentas.getIdObjRen();
                rentasListRentas.setIdObjRen(almacen);
                rentasListRentas = em.merge(rentasListRentas);
                if (oldIdObjRenOfRentasListRentas != null) {
                    oldIdObjRenOfRentasListRentas.getRentasList().remove(rentasListRentas);
                    oldIdObjRenOfRentasListRentas = em.merge(oldIdObjRenOfRentasListRentas);
                }
            }
            for (Ventas ventasListVentas : almacen.getVentasList()) {
                Almacen oldIdObjVenOfVentasListVentas = ventasListVentas.getIdObjVen();
                ventasListVentas.setIdObjVen(almacen);
                ventasListVentas = em.merge(ventasListVentas);
                if (oldIdObjVenOfVentasListVentas != null) {
                    oldIdObjVenOfVentasListVentas.getVentasList().remove(ventasListVentas);
                    oldIdObjVenOfVentasListVentas = em.merge(oldIdObjVenOfVentasListVentas);
                }
            }
            for (Pedidos pedidosListPedidos : almacen.getPedidosList()) {
                Almacen oldIdObjOfPedidosListPedidos = pedidosListPedidos.getIdObj();
                pedidosListPedidos.setIdObj(almacen);
                pedidosListPedidos = em.merge(pedidosListPedidos);
                if (oldIdObjOfPedidosListPedidos != null) {
                    oldIdObjOfPedidosListPedidos.getPedidosList().remove(pedidosListPedidos);
                    oldIdObjOfPedidosListPedidos = em.merge(oldIdObjOfPedidosListPedidos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Almacen almacen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Almacen persistentAlmacen = em.find(Almacen.class, almacen.getIdObj());
            List<Rentas> rentasListOld = persistentAlmacen.getRentasList();
            List<Rentas> rentasListNew = almacen.getRentasList();
            List<Ventas> ventasListOld = persistentAlmacen.getVentasList();
            List<Ventas> ventasListNew = almacen.getVentasList();
            List<Pedidos> pedidosListOld = persistentAlmacen.getPedidosList();
            List<Pedidos> pedidosListNew = almacen.getPedidosList();
            List<Rentas> attachedRentasListNew = new ArrayList<Rentas>();
            for (Rentas rentasListNewRentasToAttach : rentasListNew) {
                rentasListNewRentasToAttach = em.getReference(rentasListNewRentasToAttach.getClass(), rentasListNewRentasToAttach.getIdRen());
                attachedRentasListNew.add(rentasListNewRentasToAttach);
            }
            rentasListNew = attachedRentasListNew;
            almacen.setRentasList(rentasListNew);
            List<Ventas> attachedVentasListNew = new ArrayList<Ventas>();
            for (Ventas ventasListNewVentasToAttach : ventasListNew) {
                ventasListNewVentasToAttach = em.getReference(ventasListNewVentasToAttach.getClass(), ventasListNewVentasToAttach.getIdVen());
                attachedVentasListNew.add(ventasListNewVentasToAttach);
            }
            ventasListNew = attachedVentasListNew;
            almacen.setVentasList(ventasListNew);
            List<Pedidos> attachedPedidosListNew = new ArrayList<Pedidos>();
            for (Pedidos pedidosListNewPedidosToAttach : pedidosListNew) {
                pedidosListNewPedidosToAttach = em.getReference(pedidosListNewPedidosToAttach.getClass(), pedidosListNewPedidosToAttach.getIdPedido());
                attachedPedidosListNew.add(pedidosListNewPedidosToAttach);
            }
            pedidosListNew = attachedPedidosListNew;
            almacen.setPedidosList(pedidosListNew);
            almacen = em.merge(almacen);
            for (Rentas rentasListOldRentas : rentasListOld) {
                if (!rentasListNew.contains(rentasListOldRentas)) {
                    rentasListOldRentas.setIdObjRen(null);
                    rentasListOldRentas = em.merge(rentasListOldRentas);
                }
            }
            for (Rentas rentasListNewRentas : rentasListNew) {
                if (!rentasListOld.contains(rentasListNewRentas)) {
                    Almacen oldIdObjRenOfRentasListNewRentas = rentasListNewRentas.getIdObjRen();
                    rentasListNewRentas.setIdObjRen(almacen);
                    rentasListNewRentas = em.merge(rentasListNewRentas);
                    if (oldIdObjRenOfRentasListNewRentas != null && !oldIdObjRenOfRentasListNewRentas.equals(almacen)) {
                        oldIdObjRenOfRentasListNewRentas.getRentasList().remove(rentasListNewRentas);
                        oldIdObjRenOfRentasListNewRentas = em.merge(oldIdObjRenOfRentasListNewRentas);
                    }
                }
            }
            for (Ventas ventasListOldVentas : ventasListOld) {
                if (!ventasListNew.contains(ventasListOldVentas)) {
                    ventasListOldVentas.setIdObjVen(null);
                    ventasListOldVentas = em.merge(ventasListOldVentas);
                }
            }
            for (Ventas ventasListNewVentas : ventasListNew) {
                if (!ventasListOld.contains(ventasListNewVentas)) {
                    Almacen oldIdObjVenOfVentasListNewVentas = ventasListNewVentas.getIdObjVen();
                    ventasListNewVentas.setIdObjVen(almacen);
                    ventasListNewVentas = em.merge(ventasListNewVentas);
                    if (oldIdObjVenOfVentasListNewVentas != null && !oldIdObjVenOfVentasListNewVentas.equals(almacen)) {
                        oldIdObjVenOfVentasListNewVentas.getVentasList().remove(ventasListNewVentas);
                        oldIdObjVenOfVentasListNewVentas = em.merge(oldIdObjVenOfVentasListNewVentas);
                    }
                }
            }
            for (Pedidos pedidosListOldPedidos : pedidosListOld) {
                if (!pedidosListNew.contains(pedidosListOldPedidos)) {
                    pedidosListOldPedidos.setIdObj(null);
                    pedidosListOldPedidos = em.merge(pedidosListOldPedidos);
                }
            }
            for (Pedidos pedidosListNewPedidos : pedidosListNew) {
                if (!pedidosListOld.contains(pedidosListNewPedidos)) {
                    Almacen oldIdObjOfPedidosListNewPedidos = pedidosListNewPedidos.getIdObj();
                    pedidosListNewPedidos.setIdObj(almacen);
                    pedidosListNewPedidos = em.merge(pedidosListNewPedidos);
                    if (oldIdObjOfPedidosListNewPedidos != null && !oldIdObjOfPedidosListNewPedidos.equals(almacen)) {
                        oldIdObjOfPedidosListNewPedidos.getPedidosList().remove(pedidosListNewPedidos);
                        oldIdObjOfPedidosListNewPedidos = em.merge(oldIdObjOfPedidosListNewPedidos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = almacen.getIdObj();
                if (findAlmacen(id) == null) {
                    throw new NonexistentEntityException("The almacen with id " + id + " no longer exists.");
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
            Almacen almacen;
            try {
                almacen = em.getReference(Almacen.class, id);
                almacen.getIdObj();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The almacen with id " + id + " no longer exists.", enfe);
            }
            List<Rentas> rentasList = almacen.getRentasList();
            for (Rentas rentasListRentas : rentasList) {
                rentasListRentas.setIdObjRen(null);
                rentasListRentas = em.merge(rentasListRentas);
            }
            List<Ventas> ventasList = almacen.getVentasList();
            for (Ventas ventasListVentas : ventasList) {
                ventasListVentas.setIdObjVen(null);
                ventasListVentas = em.merge(ventasListVentas);
            }
            List<Pedidos> pedidosList = almacen.getPedidosList();
            for (Pedidos pedidosListPedidos : pedidosList) {
                pedidosListPedidos.setIdObj(null);
                pedidosListPedidos = em.merge(pedidosListPedidos);
            }
            em.remove(almacen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Almacen> findAlmacenEntities() {
        return findAlmacenEntities(true, -1, -1);
    }

    public List<Almacen> findAlmacenEntities(int maxResults, int firstResult) {
        return findAlmacenEntities(false, maxResults, firstResult);
    }

    private List<Almacen> findAlmacenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Almacen.class));
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

    public Almacen findAlmacen(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Almacen.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlmacenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Almacen> rt = cq.from(Almacen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
