/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.JpaClases;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author taery
 */
@Entity
@Table(name = "inventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventario.findAll", query = "SELECT i FROM Inventario i"),
    @NamedQuery(name = "Inventario.findByIdInv", query = "SELECT i FROM Inventario i WHERE i.idInv = :idInv"),
    @NamedQuery(name = "Inventario.findByObjInv", query = "SELECT i FROM Inventario i WHERE i.objInv = :objInv"),
    @NamedQuery(name = "Inventario.findByCantInv", query = "SELECT i FROM Inventario i WHERE i.cantInv = :cantInv")})
public class Inventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idInv")
    private Integer idInv;
    @Size(max = 30)
    @Column(name = "ObjInv")
    private String objInv;
    @Column(name = "CantInv")
    private Integer cantInv;
    @JoinColumn(name = "rolInv", referencedColumnName = "idRI")
    @ManyToOne
    private Rolinventario rolInv;

    public Inventario() {
        this.idInv=0;
        this.objInv="";
        this.rolInv=new Rolinventario();
    }

    public Inventario(Integer idInv) {
        this.idInv = idInv;
    }

    public Integer getIdInv() {
        return idInv;
    }

    public void setIdInv(Integer idInv) {
        this.idInv = idInv;
    }

    public String getObjInv() {
        return objInv;
    }

    public void setObjInv(String objInv) {
        this.objInv = objInv;
    }

    public Integer getCantInv() {
        return cantInv;
    }

    public void setCantInv(Integer cantInv) {
        this.cantInv = cantInv;
    }

    public Rolinventario getRolInv() {
        return rolInv;
    }

    public void setRolInv(Rolinventario rolInv) {
        this.rolInv = rolInv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInv != null ? idInv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventario)) {
            return false;
        }
        Inventario other = (Inventario) object;
        if ((this.idInv == null && other.idInv != null) || (this.idInv != null && !this.idInv.equals(other.idInv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.JpaClases.Inventario[ idInv=" + idInv + " ]";
    }
    
}
