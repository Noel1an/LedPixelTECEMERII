/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.JpaController;

import com.emergentes.JpaClases.Empleados;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.JpaClases.Roles;
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
public class EmpleadosJpaController implements Serializable {

    public EmpleadosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleados empleados) {
        if (empleados.getRentasList() == null) {
            empleados.setRentasList(new ArrayList<Rentas>());
        }
        if (empleados.getVentasList() == null) {
            empleados.setVentasList(new ArrayList<Ventas>());
        }
        if (empleados.getPedidosList() == null) {
            empleados.setPedidosList(new ArrayList<Pedidos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles idRol = empleados.getIdRol();
            if (idRol != null) {
                idRol = em.getReference(idRol.getClass(), idRol.getIdRol());
                empleados.setIdRol(idRol);
            }
            List<Rentas> attachedRentasList = new ArrayList<Rentas>();
            for (Rentas rentasListRentasToAttach : empleados.getRentasList()) {
                rentasListRentasToAttach = em.getReference(rentasListRentasToAttach.getClass(), rentasListRentasToAttach.getIdRen());
                attachedRentasList.add(rentasListRentasToAttach);
            }
            empleados.setRentasList(attachedRentasList);
            List<Ventas> attachedVentasList = new ArrayList<Ventas>();
            for (Ventas ventasListVentasToAttach : empleados.getVentasList()) {
                ventasListVentasToAttach = em.getReference(ventasListVentasToAttach.getClass(), ventasListVentasToAttach.getIdVen());
                attachedVentasList.add(ventasListVentasToAttach);
            }
            empleados.setVentasList(attachedVentasList);
            List<Pedidos> attachedPedidosList = new ArrayList<Pedidos>();
            for (Pedidos pedidosListPedidosToAttach : empleados.getPedidosList()) {
                pedidosListPedidosToAttach = em.getReference(pedidosListPedidosToAttach.getClass(), pedidosListPedidosToAttach.getIdPedido());
                attachedPedidosList.add(pedidosListPedidosToAttach);
            }
            empleados.setPedidosList(attachedPedidosList);
            em.persist(empleados);
            if (idRol != null) {
                idRol.getEmpleadosList().add(empleados);
                idRol = em.merge(idRol);
            }
            for (Rentas rentasListRentas : empleados.getRentasList()) {
                Empleados oldIdEmpOfRentasListRentas = rentasListRentas.getIdEmp();
                rentasListRentas.setIdEmp(empleados);
                rentasListRentas = em.merge(rentasListRentas);
                if (oldIdEmpOfRentasListRentas != null) {
                    oldIdEmpOfRentasListRentas.getRentasList().remove(rentasListRentas);
                    oldIdEmpOfRentasListRentas = em.merge(oldIdEmpOfRentasListRentas);
                }
            }
            for (Ventas ventasListVentas : empleados.getVentasList()) {
                Empleados oldIdEmpOfVentasListVentas = ventasListVentas.getIdEmp();
                ventasListVentas.setIdEmp(empleados);
                ventasListVentas = em.merge(ventasListVentas);
                if (oldIdEmpOfVentasListVentas != null) {
                    oldIdEmpOfVentasListVentas.getVentasList().remove(ventasListVentas);
                    oldIdEmpOfVentasListVentas = em.merge(oldIdEmpOfVentasListVentas);
                }
            }
            for (Pedidos pedidosListPedidos : empleados.getPedidosList()) {
                Empleados oldIdEmpOfPedidosListPedidos = pedidosListPedidos.getIdEmp();
                pedidosListPedidos.setIdEmp(empleados);
                pedidosListPedidos = em.merge(pedidosListPedidos);
                if (oldIdEmpOfPedidosListPedidos != null) {
                    oldIdEmpOfPedidosListPedidos.getPedidosList().remove(pedidosListPedidos);
                    oldIdEmpOfPedidosListPedidos = em.merge(oldIdEmpOfPedidosListPedidos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleados empleados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleados persistentEmpleados = em.find(Empleados.class, empleados.getIdEmp());
            Roles idRolOld = persistentEmpleados.getIdRol();
            Roles idRolNew = empleados.getIdRol();
            List<Rentas> rentasListOld = persistentEmpleados.getRentasList();
            List<Rentas> rentasListNew = empleados.getRentasList();
            List<Ventas> ventasListOld = persistentEmpleados.getVentasList();
            List<Ventas> ventasListNew = empleados.getVentasList();
            List<Pedidos> pedidosListOld = persistentEmpleados.getPedidosList();
            List<Pedidos> pedidosListNew = empleados.getPedidosList();
            if (idRolNew != null) {
                idRolNew = em.getReference(idRolNew.getClass(), idRolNew.getIdRol());
                empleados.setIdRol(idRolNew);
            }
            List<Rentas> attachedRentasListNew = new ArrayList<Rentas>();
            for (Rentas rentasListNewRentasToAttach : rentasListNew) {
                rentasListNewRentasToAttach = em.getReference(rentasListNewRentasToAttach.getClass(), rentasListNewRentasToAttach.getIdRen());
                attachedRentasListNew.add(rentasListNewRentasToAttach);
            }
            rentasListNew = attachedRentasListNew;
            empleados.setRentasList(rentasListNew);
            List<Ventas> attachedVentasListNew = new ArrayList<Ventas>();
            for (Ventas ventasListNewVentasToAttach : ventasListNew) {
                ventasListNewVentasToAttach = em.getReference(ventasListNewVentasToAttach.getClass(), ventasListNewVentasToAttach.getIdVen());
                attachedVentasListNew.add(ventasListNewVentasToAttach);
            }
            ventasListNew = attachedVentasListNew;
            empleados.setVentasList(ventasListNew);
            List<Pedidos> attachedPedidosListNew = new ArrayList<Pedidos>();
            for (Pedidos pedidosListNewPedidosToAttach : pedidosListNew) {
                pedidosListNewPedidosToAttach = em.getReference(pedidosListNewPedidosToAttach.getClass(), pedidosListNewPedidosToAttach.getIdPedido());
                attachedPedidosListNew.add(pedidosListNewPedidosToAttach);
            }
            pedidosListNew = attachedPedidosListNew;
            empleados.setPedidosList(pedidosListNew);
            empleados = em.merge(empleados);
            if (idRolOld != null && !idRolOld.equals(idRolNew)) {
                idRolOld.getEmpleadosList().remove(empleados);
                idRolOld = em.merge(idRolOld);
            }
            if (idRolNew != null && !idRolNew.equals(idRolOld)) {
                idRolNew.getEmpleadosList().add(empleados);
                idRolNew = em.merge(idRolNew);
            }
            for (Rentas rentasListOldRentas : rentasListOld) {
                if (!rentasListNew.contains(rentasListOldRentas)) {
                    rentasListOldRentas.setIdEmp(null);
                    rentasListOldRentas = em.merge(rentasListOldRentas);
                }
            }
            for (Rentas rentasListNewRentas : rentasListNew) {
                if (!rentasListOld.contains(rentasListNewRentas)) {
                    Empleados oldIdEmpOfRentasListNewRentas = rentasListNewRentas.getIdEmp();
                    rentasListNewRentas.setIdEmp(empleados);
                    rentasListNewRentas = em.merge(rentasListNewRentas);
                    if (oldIdEmpOfRentasListNewRentas != null && !oldIdEmpOfRentasListNewRentas.equals(empleados)) {
                        oldIdEmpOfRentasListNewRentas.getRentasList().remove(rentasListNewRentas);
                        oldIdEmpOfRentasListNewRentas = em.merge(oldIdEmpOfRentasListNewRentas);
                    }
                }
            }
            for (Ventas ventasListOldVentas : ventasListOld) {
                if (!ventasListNew.contains(ventasListOldVentas)) {
                    ventasListOldVentas.setIdEmp(null);
                    ventasListOldVentas = em.merge(ventasListOldVentas);
                }
            }
            for (Ventas ventasListNewVentas : ventasListNew) {
                if (!ventasListOld.contains(ventasListNewVentas)) {
                    Empleados oldIdEmpOfVentasListNewVentas = ventasListNewVentas.getIdEmp();
                    ventasListNewVentas.setIdEmp(empleados);
                    ventasListNewVentas = em.merge(ventasListNewVentas);
                    if (oldIdEmpOfVentasListNewVentas != null && !oldIdEmpOfVentasListNewVentas.equals(empleados)) {
                        oldIdEmpOfVentasListNewVentas.getVentasList().remove(ventasListNewVentas);
                        oldIdEmpOfVentasListNewVentas = em.merge(oldIdEmpOfVentasListNewVentas);
                    }
                }
            }
            for (Pedidos pedidosListOldPedidos : pedidosListOld) {
                if (!pedidosListNew.contains(pedidosListOldPedidos)) {
                    pedidosListOldPedidos.setIdEmp(null);
                    pedidosListOldPedidos = em.merge(pedidosListOldPedidos);
                }
            }
            for (Pedidos pedidosListNewPedidos : pedidosListNew) {
                if (!pedidosListOld.contains(pedidosListNewPedidos)) {
                    Empleados oldIdEmpOfPedidosListNewPedidos = pedidosListNewPedidos.getIdEmp();
                    pedidosListNewPedidos.setIdEmp(empleados);
                    pedidosListNewPedidos = em.merge(pedidosListNewPedidos);
                    if (oldIdEmpOfPedidosListNewPedidos != null && !oldIdEmpOfPedidosListNewPedidos.equals(empleados)) {
                        oldIdEmpOfPedidosListNewPedidos.getPedidosList().remove(pedidosListNewPedidos);
                        oldIdEmpOfPedidosListNewPedidos = em.merge(oldIdEmpOfPedidosListNewPedidos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleados.getIdEmp();
                if (findEmpleados(id) == null) {
                    throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.");
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
            Empleados empleados;
            try {
                empleados = em.getReference(Empleados.class, id);
                empleados.getIdEmp();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleados with id " + id + " no longer exists.", enfe);
            }
            Roles idRol = empleados.getIdRol();
            if (idRol != null) {
                idRol.getEmpleadosList().remove(empleados);
                idRol = em.merge(idRol);
            }
            List<Rentas> rentasList = empleados.getRentasList();
            for (Rentas rentasListRentas : rentasList) {
                rentasListRentas.setIdEmp(null);
                rentasListRentas = em.merge(rentasListRentas);
            }
            List<Ventas> ventasList = empleados.getVentasList();
            for (Ventas ventasListVentas : ventasList) {
                ventasListVentas.setIdEmp(null);
                ventasListVentas = em.merge(ventasListVentas);
            }
            List<Pedidos> pedidosList = empleados.getPedidosList();
            for (Pedidos pedidosListPedidos : pedidosList) {
                pedidosListPedidos.setIdEmp(null);
                pedidosListPedidos = em.merge(pedidosListPedidos);
            }
            em.remove(empleados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleados> findEmpleadosEntities() {
        return findEmpleadosEntities(true, -1, -1);
    }

    public List<Empleados> findEmpleadosEntities(int maxResults, int firstResult) {
        return findEmpleadosEntities(false, maxResults, firstResult);
    }

    private List<Empleados> findEmpleadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleados.class));
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

    public Empleados findEmpleados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleados> rt = cq.from(Empleados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
