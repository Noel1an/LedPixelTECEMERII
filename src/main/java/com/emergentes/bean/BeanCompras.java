
package com.emergentes.bean;

import com.emergentes.JpaClases.Compras;
import com.emergentes.JpaClases.Proveedores;
import com.emergentes.JpaController.ComprasJpaController;
import com.emergentes.JpaController.ProveedoresJpaController;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanCompras {
    private EntityManagerFactory emf;
    private ComprasJpaController jpaCompra;
    private ProveedoresJpaController jpaProve;
    public BeanCompras(){
    emf = Persistence.createEntityManagerFactory("LedPixelUP");
    jpaCompra = new ComprasJpaController (emf);
    jpaProve = new ProveedoresJpaController(emf);
    
    }
    public List<Compras> listarTodoc(){
        return jpaCompra.findComprasEntities();
    }
    public List<Proveedores>listarTodori(){
        return jpaProve.findProveedoresEntities();
    }
    public void insertar(Compras e){
        jpaCompra.create(e);
    }
    public void editar (Compras e){
        try {
            jpaCompra.edit(e);
        } catch (Exception ex) {
            Logger.getLogger(BeanLed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminar (Integer id){
        try {
            jpaCompra.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanLed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Compras buscar (Integer id){
        return jpaCompra.findCompras(id);
    }
    public Proveedores buscarr (Integer id){
        return jpaProve.findProveedores(id);
    }
}
