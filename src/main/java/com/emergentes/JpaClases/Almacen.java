/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.JpaClases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author taery
 */
@Entity
@Table(name = "almacen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Almacen.findAll", query = "SELECT a FROM Almacen a"),
    @NamedQuery(name = "Almacen.findByIdObj", query = "SELECT a FROM Almacen a WHERE a.idObj = :idObj"),
    @NamedQuery(name = "Almacen.findByNombreObj", query = "SELECT a FROM Almacen a WHERE a.nombreObj = :nombreObj"),
    @NamedQuery(name = "Almacen.findByStockObj", query = "SELECT a FROM Almacen a WHERE a.stockObj = :stockObj")})
public class Almacen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idObj")
    private Integer idObj;
    @Size(max = 30)
    @Column(name = "NombreObj")
    private String nombreObj;
    @Column(name = "StockObj")
    private Integer stockObj;
    @OneToMany(mappedBy = "idObjRen")
    private List<Rentas> rentasList;
    @OneToMany(mappedBy = "idObjVen")
    private List<Ventas> ventasList;
    @OneToMany(mappedBy = "idObj")
    private List<Pedidos> pedidosList;

    public Almacen() {
        this.idObj=0;
        this.nombreObj="";
        this.stockObj=0;
        rentasList = new ArrayList<Rentas>();
        ventasList = new ArrayList<Ventas>();
        pedidosList = new ArrayList<Pedidos>();
    }

    public Almacen(Integer idObj) {
        this.idObj = idObj;
    }

    public Integer getIdObj() {
        return idObj;
    }

    public void setIdObj(Integer idObj) {
        this.idObj = idObj;
    }

    public String getNombreObj() {
        return nombreObj;
    }

    public void setNombreObj(String nombreObj) {
        this.nombreObj = nombreObj;
    }

    public Integer getStockObj() {
        return stockObj;
    }

    public void setStockObj(Integer stockObj) {
        this.stockObj = stockObj;
    }

    @XmlTransient
    public List<Rentas> getRentasList() {
        return rentasList;
    }

    public void setRentasList(List<Rentas> rentasList) {
        this.rentasList = rentasList;
    }

    @XmlTransient
    public List<Ventas> getVentasList() {
        return ventasList;
    }

    public void setVentasList(List<Ventas> ventasList) {
        this.ventasList = ventasList;
    }

    @XmlTransient
    public List<Pedidos> getPedidosList() {
        return pedidosList;
    }

    public void setPedidosList(List<Pedidos> pedidosList) {
        this.pedidosList = pedidosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idObj != null ? idObj.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Almacen)) {
            return false;
        }
        Almacen other = (Almacen) object;
        if ((this.idObj == null && other.idObj != null) || (this.idObj != null && !this.idObj.equals(other.idObj))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.JpaClases.Almacen[ idObj=" + idObj + " ]";
    }
    
}
