/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.JpaClases;

import java.io.Serializable;
import java.lang.reflect.Array;
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
@Table(name = "rolinventario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rolinventario.findAll", query = "SELECT r FROM Rolinventario r"),
    @NamedQuery(name = "Rolinventario.findByIdRI", query = "SELECT r FROM Rolinventario r WHERE r.idRI = :idRI"),
    @NamedQuery(name = "Rolinventario.findByNRinv", query = "SELECT r FROM Rolinventario r WHERE r.nRinv = :nRinv"),
    @NamedQuery(name = "Rolinventario.findByCodInv", query = "SELECT r FROM Rolinventario r WHERE r.codInv = :codInv")})
public class Rolinventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRI")
    private Integer idRI;
    @Size(max = 30)
    @Column(name = "NRinv")
    private String nRinv;
    @Size(max = 3)
    @Column(name = "CodInv")
    private String codInv;
    @OneToMany(mappedBy = "rolInv")
    private List<Inventario> inventarioList;

    public Rolinventario() {
        this.idRI=0;
        this.nRinv="";
        this.codInv="";
        inventarioList = new ArrayList<Inventario>();
    }

    public Rolinventario(Integer idRI) {
        this.idRI = idRI;
    }

    public Integer getIdRI() {
        return idRI;
    }

    public void setIdRI(Integer idRI) {
        this.idRI = idRI;
    }

    public String getNRinv() {
        return nRinv;
    }

    public void setNRinv(String nRinv) {
        this.nRinv = nRinv;
    }

    public String getCodInv() {
        return codInv;
    }

    public void setCodInv(String codInv) {
        this.codInv = codInv;
    }

    @XmlTransient
    public List<Inventario> getInventarioList() {
        return inventarioList;
    }

    public void setInventarioList(List<Inventario> inventarioList) {
        this.inventarioList = inventarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRI != null ? idRI.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rolinventario)) {
            return false;
        }
        Rolinventario other = (Rolinventario) object;
        if ((this.idRI == null && other.idRI != null) || (this.idRI != null && !this.idRI.equals(other.idRI))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.JpaClases.Rolinventario[ idRI=" + idRI + " ]";
    }
    
}
