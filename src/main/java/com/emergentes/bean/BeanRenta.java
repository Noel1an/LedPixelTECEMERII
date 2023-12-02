/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.bean;

import com.emergentes.JpaClases.Rentas;
import com.emergentes.JpaClases.Ventas;
import com.emergentes.JpaController.RentasJpaController;
import com.emergentes.JpaController.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author taery
 */
public class BeanRenta {
    private EntityManagerFactory emf;
    private RentasJpaController jpaVenta;
    public BeanRenta(){
    emf = Persistence.createEntityManagerFactory("LedPixelUP");
    jpaVenta = new RentasJpaController (emf);
    
    }
    public List<Rentas> listarTodoren(){
        return jpaVenta.findRentasEntities();
    }
     public void insertarren(Rentas e){
        jpaVenta.create(e);
    }
    public void editarren (Rentas e){
        try {
            jpaVenta.edit(e);
        } catch (Exception ex) {
            Logger.getLogger(BeanLed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void eliminarren (Integer id){
        try {
            jpaVenta.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanLed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public Rentas buscarren (Integer id){
        return jpaVenta.findRentas(id);
    }
}
