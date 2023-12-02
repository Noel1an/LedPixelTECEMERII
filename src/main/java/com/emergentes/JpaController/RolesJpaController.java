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
import com.emergentes.JpaClases.Empleados;
import com.emergentes.JpaClases.Roles;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taery
 */
public class RolesJpaController implements Serializable {

    public RolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Roles roles) {
        if (roles.getEmpleadosList() == null) {
            roles.setEmpleadosList(new ArrayList<Empleados>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empleados> attachedEmpleadosList = new ArrayList<Empleados>();
            for (Empleados empleadosListEmpleadosToAttach : roles.getEmpleadosList()) {
                empleadosListEmpleadosToAttach = em.getReference(empleadosListEmpleadosToAttach.getClass(), empleadosListEmpleadosToAttach.getIdEmp());
                attachedEmpleadosList.add(empleadosListEmpleadosToAttach);
            }
            roles.setEmpleadosList(attachedEmpleadosList);
            em.persist(roles);
            for (Empleados empleadosListEmpleados : roles.getEmpleadosList()) {
                Roles oldIdRolOfEmpleadosListEmpleados = empleadosListEmpleados.getIdRol();
                empleadosListEmpleados.setIdRol(roles);
                empleadosListEmpleados = em.merge(empleadosListEmpleados);
                if (oldIdRolOfEmpleadosListEmpleados != null) {
                    oldIdRolOfEmpleadosListEmpleados.getEmpleadosList().remove(empleadosListEmpleados);
                    oldIdRolOfEmpleadosListEmpleados = em.merge(oldIdRolOfEmpleadosListEmpleados);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Roles roles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles persistentRoles = em.find(Roles.class, roles.getIdRol());
            List<Empleados> empleadosListOld = persistentRoles.getEmpleadosList();
            List<Empleados> empleadosListNew = roles.getEmpleadosList();
            List<Empleados> attachedEmpleadosListNew = new ArrayList<Empleados>();
            for (Empleados empleadosListNewEmpleadosToAttach : empleadosListNew) {
                empleadosListNewEmpleadosToAttach = em.getReference(empleadosListNewEmpleadosToAttach.getClass(), empleadosListNewEmpleadosToAttach.getIdEmp());
                attachedEmpleadosListNew.add(empleadosListNewEmpleadosToAttach);
            }
            empleadosListNew = attachedEmpleadosListNew;
            roles.setEmpleadosList(empleadosListNew);
            roles = em.merge(roles);
            for (Empleados empleadosListOldEmpleados : empleadosListOld) {
                if (!empleadosListNew.contains(empleadosListOldEmpleados)) {
                    empleadosListOldEmpleados.setIdRol(null);
                    empleadosListOldEmpleados = em.merge(empleadosListOldEmpleados);
                }
            }
            for (Empleados empleadosListNewEmpleados : empleadosListNew) {
                if (!empleadosListOld.contains(empleadosListNewEmpleados)) {
                    Roles oldIdRolOfEmpleadosListNewEmpleados = empleadosListNewEmpleados.getIdRol();
                    empleadosListNewEmpleados.setIdRol(roles);
                    empleadosListNewEmpleados = em.merge(empleadosListNewEmpleados);
                    if (oldIdRolOfEmpleadosListNewEmpleados != null && !oldIdRolOfEmpleadosListNewEmpleados.equals(roles)) {
                        oldIdRolOfEmpleadosListNewEmpleados.getEmpleadosList().remove(empleadosListNewEmpleados);
                        oldIdRolOfEmpleadosListNewEmpleados = em.merge(oldIdRolOfEmpleadosListNewEmpleados);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = roles.getIdRol();
                if (findRoles(id) == null) {
                    throw new NonexistentEntityException("The roles with id " + id + " no longer exists.");
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
            Roles roles;
            try {
                roles = em.getReference(Roles.class, id);
                roles.getIdRol();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roles with id " + id + " no longer exists.", enfe);
            }
            List<Empleados> empleadosList = roles.getEmpleadosList();
            for (Empleados empleadosListEmpleados : empleadosList) {
                empleadosListEmpleados.setIdRol(null);
                empleadosListEmpleados = em.merge(empleadosListEmpleados);
            }
            em.remove(roles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Roles> findRolesEntities() {
        return findRolesEntities(true, -1, -1);
    }

    public List<Roles> findRolesEntities(int maxResults, int firstResult) {
        return findRolesEntities(false, maxResults, firstResult);
    }

    private List<Roles> findRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Roles.class));
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

    public Roles findRoles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Roles.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Roles> rt = cq.from(Roles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
