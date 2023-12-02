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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "empleados")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleados.findAll", query = "SELECT e FROM Empleados e"),
    @NamedQuery(name = "Empleados.findByIdEmp", query = "SELECT e FROM Empleados e WHERE e.idEmp = :idEmp"),
    @NamedQuery(name = "Empleados.findByNombreEmp", query = "SELECT e FROM Empleados e WHERE e.nombreEmp = :nombreEmp"),
    @NamedQuery(name = "Empleados.findByApellidoEmp", query = "SELECT e FROM Empleados e WHERE e.apellidoEmp = :apellidoEmp"),
    @NamedQuery(name = "Empleados.findByCiEmp", query = "SELECT e FROM Empleados e WHERE e.ciEmp = :ciEmp"),
    @NamedQuery(name = "Empleados.findByCorreoEmp", query = "SELECT e FROM Empleados e WHERE e.correoEmp = :correoEmp"),
    @NamedQuery(name = "Empleados.findByUsuario", query = "SELECT e FROM Empleados e WHERE e.usuario = :usuario"),
    @NamedQuery(name = "Empleados.findByContrasenia", query = "SELECT e FROM Empleados e WHERE e.contrasenia = :contrasenia"),
    @NamedQuery(name = "Empleados.findBySueldo", query = "SELECT e FROM Empleados e WHERE e.sueldo = :sueldo")})
public class Empleados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEmp")
    private Integer idEmp;
    @Size(max = 50)
    @Column(name = "NombreEmp")
    private String nombreEmp;
    @Size(max = 50)
    @Column(name = "ApellidoEmp")
    private String apellidoEmp;
    @Size(max = 50)
    @Column(name = "ciEmp")
    private String ciEmp;
    @Size(max = 50)
    @Column(name = "CorreoEmp")
    private String correoEmp;
    @Size(max = 10)
    @Column(name = "Usuario")
    private String usuario;
    @Size(max = 10)
    @Column(name = "Contrasenia")
    private String contrasenia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Sueldo")
    private Float sueldo;
    @OneToMany(mappedBy = "idEmp")
    private List<Rentas> rentasList;
    @OneToMany(mappedBy = "idEmp")
    private List<Ventas> ventasList;
    @JoinColumn(name = "idRol", referencedColumnName = "idRol")
    @ManyToOne
    private Roles idRol;
    @OneToMany(mappedBy = "idEmp")
    private List<Pedidos> pedidosList;
    public Empleados() {
        this.idEmp=0;
        this.nombreEmp="";
        this.apellidoEmp="";
        this.ciEmp="";
        this.usuario="";
        this.correoEmp="";
        this.sueldo=0.0f;
        this.idRol = new Roles();
        ventasList = new ArrayList<Ventas>();
        rentasList = new ArrayList<Rentas>();
    }

    public Empleados(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getApellidoEmp() {
        return apellidoEmp;
    }

    public void setApellidoEmp(String apellidoEmp) {
        this.apellidoEmp = apellidoEmp;
    }

    public String getCiEmp() {
        return ciEmp;
    }

    public void setCiEmp(String ciEmp) {
        this.ciEmp = ciEmp;
    }

    public String getCorreoEmp() {
        return correoEmp;
    }

    public void setCorreoEmp(String correoEmp) {
        this.correoEmp = correoEmp;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Float getSueldo() {
        return sueldo;
    }

    public void setSueldo(Float sueldo) {
        this.sueldo = sueldo;
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

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
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
        hash += (idEmp != null ? idEmp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleados)) {
            return false;
        }
        Empleados other = (Empleados) object;
        if ((this.idEmp == null && other.idEmp != null) || (this.idEmp != null && !this.idEmp.equals(other.idEmp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.JpaClases.Empleados[ idEmp=" + idEmp + " ]";
        
    }
    
}
