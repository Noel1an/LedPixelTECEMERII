
package com.emergentes.bean;

import com.emergentes.JpaClases.Ventas;
import com.emergentes.JpaController.VentasJpaController;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanVenta {
    private EntityManagerFactory emf;
    private VentasJpaController jpaVenta;
    public BeanVenta(){
    emf = Persistence.createEntityManagerFactory("LedPixelUP");
    jpaVenta = new VentasJpaController (emf);
    
    }
    public List<Ventas> listarTodov(){
        return jpaVenta.findVentasEntities();
    }
     public void insertar(Ventas e){
        jpaVenta.create(e);
    }
    public void editar (Ventas e){
        try {
            jpaVenta.edit(e);
        } catch (Exception ex) {
            Logger.getLogger(BeanLed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminar (Integer id){
        try {
            jpaVenta.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanLed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Ventas buscarv (Integer id){
        return jpaVenta.findVentas(id);
    }
}
