
package com.emergentes.bean;

import com.emergentes.JpaClases.Almacen;
import com.emergentes.JpaClases.Pedidos;
import com.emergentes.JpaController.AlmacenJpaController;
import com.emergentes.JpaController.PedidosJpaController;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanPedidos {
   private EntityManagerFactory emf;
    private PedidosJpaController jpaPedidos;
     private AlmacenJpaController jpaAlmacen;
    public BeanPedidos(){
    emf = Persistence.createEntityManagerFactory("LedPixelUP");
    jpaPedidos = new PedidosJpaController (emf);
    jpaAlmacen = new AlmacenJpaController(emf);
    }
    public List<Pedidos> listarTodop(){
        return jpaPedidos.findPedidosEntities();
    }
    public List<Almacen> listarTodoa(){
        return jpaAlmacen.findAlmacenEntities();
    }
     public void insertar(Pedidos e){
        jpaPedidos.create(e);
    }
    public void editar (Pedidos e){
        try {
            jpaPedidos.edit(e);
        } catch (Exception ex) {
            Logger.getLogger(BeanLed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminar (Integer id){
        try {
            jpaPedidos.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanLed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Pedidos buscarp (Integer id){
        return jpaPedidos.findPedidos(id);
}
    public Almacen buscarr (Integer id){
        return jpaAlmacen.findAlmacen(id);
    }
}
