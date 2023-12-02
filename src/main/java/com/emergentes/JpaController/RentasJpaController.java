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
import com.emergentes.JpaClases.Rentas;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author taery
 */
public class RentasJpaController implements Serializable {

    public RentasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rentas rentas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Almacen idObjRen = rentas.getIdObjRen();
            if (idObjRen != null) {
                idObjRen = em.getReference(idObjRen.getClass(), idObjRen.getIdObj());
                rentas.setIdObjRen(idObjRen);
            }
            Empleados idEmp = rentas.getIdEmp();
            if (idEmp != null) {
                idEmp = em.getReference(idEmp.getClass(), idEmp.getIdEmp());
                rentas.setIdEmp(idEmp);
            }
            em.persist(rentas);
            if (idObjRen != null) {
                idObjRen.getRentasList().add(rentas);
                idObjRen = em.merge(idObjRen);
            }
            if (idEmp != null) {
                idEmp.getRentasList().add(rentas);
                idEmp = em.merge(idEmp);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rentas rentas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rentas persistentRentas = em.find(Rentas.class, rentas.getIdRen());
            Almacen idObjRenOld = persistentRentas.getIdObjRen();
            Almacen idObjRenNew = rentas.getIdObjRen();
            Empleados idEmpOld = persistentRentas.getIdEmp();
            Empleados idEmpNew = rentas.getIdEmp();
            if (idObjRenNew != null) {
                idObjRenNew = em.getReference(idObjRenNew.getClass(), idObjRenNew.getIdObj());
                rentas.setIdObjRen(idObjRenNew);
            }
            if (idEmpNew != null) {
                idEmpNew = em.getReference(idEmpNew.getClass(), idEmpNew.getIdEmp());
                rentas.setIdEmp(idEmpNew);
            }
            rentas = em.merge(rentas);
            if (idObjRenOld != null && !idObjRenOld.equals(idObjRenNew)) {
                idObjRenOld.getRentasList().remove(rentas);
                idObjRenOld = em.merge(idObjRenOld);
            }
            if (idObjRenNew != null && !idObjRenNew.equals(idObjRenOld)) {
                idObjRenNew.getRentasList().add(rentas);
                idObjRenNew = em.merge(idObjRenNew);
            }
            if (idEmpOld != null && !idEmpOld.equals(idEmpNew)) {
                idEmpOld.getRentasList().remove(rentas);
                idEmpOld = em.merge(idEmpOld);
            }
            if (idEmpNew != null && !idEmpNew.equals(idEmpOld)) {
                idEmpNew.getRentasList().add(rentas);
                idEmpNew = em.merge(idEmpNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rentas.getIdRen();
                if (findRentas(id) == null) {
                    throw new NonexistentEntityException("The rentas with id " + id + " no longer exists.");
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
            Rentas rentas;
            try {
                rentas = em.getReference(Rentas.class, id);
                rentas.getIdRen();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rentas with id " + id + " no longer exists.", enfe);
            }
            Almacen idObjRen = rentas.getIdObjRen();
            if (idObjRen != null) {
                idObjRen.getRentasList().remove(rentas);
                idObjRen = em.merge(idObjRen);
            }
            Empleados idEmp = rentas.getIdEmp();
            if (idEmp != null) {
                idEmp.getRentasList().remove(rentas);
                idEmp = em.merge(idEmp);
            }
            em.remove(rentas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rentas> findRentasEntities() {
        return findRentasEntities(true, -1, -1);
    }

    public List<Rentas> findRentasEntities(int maxResults, int firstResult) {
        return findRentasEntities(false, maxResults, firstResult);
    }

    private List<Rentas> findRentasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rentas.class));
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

    public Rentas findRentas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rentas.class, id);
        } finally {
            em.close();
        }
    }

    public int getRentasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rentas> rt = cq.from(Rentas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
