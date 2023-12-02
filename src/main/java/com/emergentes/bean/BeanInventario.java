
package com.emergentes.bean;

import com.emergentes.JpaClases.Inventario;
import com.emergentes.JpaClases.Rolinventario;
import com.emergentes.JpaController.InventarioJpaController;
import com.emergentes.JpaController.RolinventarioJpaController;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanInventario {
    private EntityManagerFactory emf;
    private InventarioJpaController jpaEmpleado;
    private RolinventarioJpaController jpaRoles;
    public BeanInventario(){
    emf = Persistence.createEntityManagerFactory("LedPixelUP");
    jpaEmpleado = new InventarioJpaController (emf);
    jpaRoles = new RolinventarioJpaController(emf);
    
    }
    public List<Inventario> listarTodoi(){
        return jpaEmpleado.findInventarioEntities();
        
    }
    public List<Rolinventario>listarTodori(){
        return jpaRoles.findRolinventarioEntities();
    }
    public void insertar(Inventario e){
        jpaEmpleado.create(e);
    }
    public void editar (Inventario e){
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
    public Inventario buscar (Integer id){
        return jpaEmpleado.findInventario(id);
    }
    public Rolinventario buscarr (Integer id){
        return jpaRoles.findRolinventario(id);
    }
}
