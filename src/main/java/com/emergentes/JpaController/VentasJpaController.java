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
import com.emergentes.JpaClases.Ventas;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taery
 */
public class VentasJpaController implements Serializable {

    public VentasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ventas ventas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Almacen idObjVen = ventas.getIdObjVen();
            if (idObjVen != null) {
                idObjVen = em.getReference(idObjVen.getClass(), idObjVen.getIdObj());
                ventas.setIdObjVen(idObjVen);
            }
            Empleados idEmp = ventas.getIdEmp();
            if (idEmp != null) {
                idEmp = em.getReference(idEmp.getClass(), idEmp.getIdEmp());
                ventas.setIdEmp(idEmp);
            }
            em.persist(ventas);
            if (idObjVen != null) {
                idObjVen.getVentasList().add(ventas);
                idObjVen = em.merge(idObjVen);
            }
            if (idEmp != null) {
                idEmp.getVentasList().add(ventas);
                idEmp = em.merge(idEmp);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ventas ventas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ventas persistentVentas = em.find(Ventas.class, ventas.getIdVen());
            Almacen idObjVenOld = persistentVentas.getIdObjVen();
            Almacen idObjVenNew = ventas.getIdObjVen();
            Empleados idEmpOld = persistentVentas.getIdEmp();
            Empleados idEmpNew = ventas.getIdEmp();
            if (idObjVenNew != null) {
                idObjVenNew = em.getReference(idObjVenNew.getClass(), idObjVenNew.getIdObj());
                ventas.setIdObjVen(idObjVenNew);
            }
            if (idEmpNew != null) {
                idEmpNew = em.getReference(idEmpNew.getClass(), idEmpNew.getIdEmp());
                ventas.setIdEmp(idEmpNew);
            }
            ventas = em.merge(ventas);
            if (idObjVenOld != null && !idObjVenOld.equals(idObjVenNew)) {
                idObjVenOld.getVentasList().remove(ventas);
                idObjVenOld = em.merge(idObjVenOld);
            }
            if (idObjVenNew != null && !idObjVenNew.equals(idObjVenOld)) {
                idObjVenNew.getVentasList().add(ventas);
                idObjVenNew = em.merge(idObjVenNew);
            }
            if (idEmpOld != null && !idEmpOld.equals(idEmpNew)) {
                idEmpOld.getVentasList().remove(ventas);
                idEmpOld = em.merge(idEmpOld);
            }
            if (idEmpNew != null && !idEmpNew.equals(idEmpOld)) {
                idEmpNew.getVentasList().add(ventas);
                idEmpNew = em.merge(idEmpNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ventas.getIdVen();
                if (findVentas(id) == null) {
                    throw new NonexistentEntityException("The ventas with id " + id + " no longer exists.");
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
            Ventas ventas;
            try {
                ventas = em.getReference(Ventas.class, id);
                ventas.getIdVen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ventas with id " + id + " no longer exists.", enfe);
            }
            Almacen idObjVen = ventas.getIdObjVen();
            if (idObjVen != null) {
                idObjVen.getVentasList().remove(ventas);
                idObjVen = em.merge(idObjVen);
            }
            Empleados idEmp = ventas.getIdEmp();
            if (idEmp != null) {
                idEmp.getVentasList().remove(ventas);
                idEmp = em.merge(idEmp);
            }
            em.remove(ventas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ventas> findVentasEntities() {
        return findVentasEntities(true, -1, -1);
    }

    public List<Ventas> findVentasEntities(int maxResults, int firstResult) {
        return findVentasEntities(false, maxResults, firstResult);
    }

    private List<Ventas> findVentasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ventas.class));
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

    public Ventas findVentas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ventas.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ventas> rt = cq.from(Ventas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
