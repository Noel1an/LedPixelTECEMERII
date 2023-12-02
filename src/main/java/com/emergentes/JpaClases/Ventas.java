/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.JpaClases;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author taery
 */
@Entity
@Table(name = "ventas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventas.findAll", query = "SELECT v FROM Ventas v"),
    @NamedQuery(name = "Ventas.findByIdVen", query = "SELECT v FROM Ventas v WHERE v.idVen = :idVen"),
    @NamedQuery(name = "Ventas.findByCtdVen", query = "SELECT v FROM Ventas v WHERE v.ctdVen = :ctdVen"),
    @NamedQuery(name = "Ventas.findByFecha", query = "SELECT v FROM Ventas v WHERE v.fecha = :fecha")})
public class Ventas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVen")
    private Integer idVen;
    @Column(name = "ctdVen")
    private Integer ctdVen;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "idObjVen", referencedColumnName = "idObj")
    @ManyToOne
    private Almacen idObjVen;
    @JoinColumn(name = "idEmp", referencedColumnName = "idEmp")
    @ManyToOne
    private Empleados idEmp;

    public Ventas() {
        this.idVen=0;
        this.ctdVen=0;
        this.fecha= new Date();
        this.idEmp=new Empleados();
        this.idObjVen=new Almacen();
        
    }
    

    public Ventas(Integer idVen) {
        this.idVen = idVen;
    }

    public Integer getIdVen() {
        return idVen;
    }

    public void setIdVen(Integer idVen) {
        this.idVen = idVen;
    }

    public Integer getCtdVen() {
        return ctdVen;
    }

    public void setCtdVen(Integer ctdVen) {
        this.ctdVen = ctdVen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Almacen getIdObjVen() {
        return idObjVen;
    }

    public void setIdObjVen(Almacen idObjVen) {
        this.idObjVen = idObjVen;
    }

    public Empleados getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Empleados idEmp) {
        this.idEmp = idEmp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVen != null ? idVen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventas)) {
            return false;
        }
        Ventas other = (Ventas) object;
        if ((this.idVen == null && other.idVen != null) || (this.idVen != null && !this.idVen.equals(other.idVen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.JpaClases.Ventas[ idVen=" + idVen + " ]";
    }
    
}
