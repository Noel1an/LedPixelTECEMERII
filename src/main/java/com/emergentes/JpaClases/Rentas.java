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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author taery
 */
@Entity
@Table(name = "rentas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rentas.findAll", query = "SELECT r FROM Rentas r"),
    @NamedQuery(name = "Rentas.findByIdRen", query = "SELECT r FROM Rentas r WHERE r.idRen = :idRen"),
    @NamedQuery(name = "Rentas.findByCtdRen", query = "SELECT r FROM Rentas r WHERE r.ctdRen = :ctdRen"),
    @NamedQuery(name = "Rentas.findByMonto", query = "SELECT r FROM Rentas r WHERE r.monto = :monto"),
    @NamedQuery(name = "Rentas.findByFechaEntrega", query = "SELECT r FROM Rentas r WHERE r.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Rentas.findByFechaDev", query = "SELECT r FROM Rentas r WHERE r.fechaDev = :fechaDev"),
    @NamedQuery(name = "Rentas.findByNombreCliR", query = "SELECT r FROM Rentas r WHERE r.nombreCliR = :nombreCliR"),
    @NamedQuery(name = "Rentas.findByCi", query = "SELECT r FROM Rentas r WHERE r.ci = :ci"),
    @NamedQuery(name = "Rentas.findByTelefono", query = "SELECT r FROM Rentas r WHERE r.telefono = :telefono")})
public class Rentas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRen")
    private Integer idRen;
    @Column(name = "ctdRen")
    private Integer ctdRen;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Monto")
    private Float monto;
    @Column(name = "fechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Column(name = "fechaDev")
    @Temporal(TemporalType.DATE)
    private Date fechaDev;
    @Size(max = 100)
    @Column(name = "NombreCliR")
    private String nombreCliR;
    @Size(max = 30)
    @Column(name = "Ci")
    private String ci;
    @Size(max = 30)
    @Column(name = "Telefono")
    private String telefono;
    @JoinColumn(name = "idObjRen", referencedColumnName = "idObj")
    @ManyToOne
    private Almacen idObjRen;
    @JoinColumn(name = "idEmp", referencedColumnName = "idEmp")
    @ManyToOne
    private Empleados idEmp;

    public Rentas() {
        this.idRen=0;
        this.ctdRen=0;
        this.monto=0.0f;
        this.fechaEntrega=new Date();
        this.fechaDev = new Date();
        this.nombreCliR="";
        this.ci="";
        this.telefono="";
        this.idObjRen=new Almacen();
        this.idEmp = new Empleados();
    }

    public Rentas(Integer idRen) {
        this.idRen = idRen;
    }

    public Integer getIdRen() {
        return idRen;
    }

    public void setIdRen(Integer idRen) {
        this.idRen = idRen;
    }

    public Integer getCtdRen() {
        return ctdRen;
    }

    public void setCtdRen(Integer ctdRen) {
        this.ctdRen = ctdRen;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaDev() {
        return fechaDev;
    }

    public void setFechaDev(Date fechaDev) {
        this.fechaDev = fechaDev;
    }

    public String getNombreCliR() {
        return nombreCliR;
    }

    public void setNombreCliR(String nombreCliR) {
        this.nombreCliR = nombreCliR;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Almacen getIdObjRen() {
        return idObjRen;
    }

    public void setIdObjRen(Almacen idObjRen) {
        this.idObjRen = idObjRen;
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
        hash += (idRen != null ? idRen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rentas)) {
            return false;
        }
        Rentas other = (Rentas) object;
        if ((this.idRen == null && other.idRen != null) || (this.idRen != null && !this.idRen.equals(other.idRen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.JpaClases.Rentas[ idRen=" + idRen + " ]";
    }
    
}
