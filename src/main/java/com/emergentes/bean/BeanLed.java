package com.emergentes.bean;
import com.emergentes.JpaClases.Empleados;
import com.emergentes.JpaClases.Roles;
import com.emergentes.JpaController.EmpleadosJpaController;

import com.emergentes.JpaController.RolesJpaController;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanLed {
    private EntityManagerFactory emf;
    private EmpleadosJpaController jpaEmpleado;
    private RolesJpaController jpaRoles;
    public BeanLed(){
    emf = Persistence.createEntityManagerFactory("LedPixelUP");
    jpaEmpleado = new EmpleadosJpaController (emf);
    jpaRoles = new RolesJpaController(emf);
    
    }
    public List<Empleados> listarTodos(){
        return jpaEmpleado.findEmpleadosEntities();
        
    }
    public List<Roles> listarTodor(){
        return jpaRoles.findRolesEntities();
    }
    public void insertar(Empleados e){
        jpaEmpleado.create(e);
    }
    public void editar (Empleados e){
        try {
            jpaEmpleado.edit(e);
        } catch (Exception ex) {
            Logger.getLogger(BeanLed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminar (Integer id){
        try {
            jpaEmpleado.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanLed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Empleados buscar (Integer id){
        return jpaEmpleado.findEmpleados(id);
    }
    public Roles buscarr (Integer id){
        return jpaRoles.findRoles(id);
    }
    
    
    
}
