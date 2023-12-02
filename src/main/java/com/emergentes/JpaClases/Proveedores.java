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
@Table(name = "proveedores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedores.findAll", query = "SELECT p FROM Proveedores p"),
    @NamedQuery(name = "Proveedores.findByIdProv", query = "SELECT p FROM Proveedores p WHERE p.idProv = :idProv"),
    @NamedQuery(name = "Proveedores.findByNombreProv", query = "SELECT p FROM Proveedores p WHERE p.nombreProv = :nombreProv"),
    @NamedQuery(name = "Proveedores.findByTelefono", query = "SELECT p FROM Proveedores p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Proveedores.findByDireccion", query = "SELECT p FROM Proveedores p WHERE p.direccion = :direccion")})
public class Proveedores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProv")
    private Integer idProv;
    @Size(max = 30)
    @Column(name = "NombreProv")
    private String nombreProv;
    @Size(max = 30)
    @Column(name = "Telefono")
    private String telefono;
    @Size(max = 30)
    @Column(name = "Direccion")
    private String direccion;
    @OneToMany(mappedBy = "idProv")
    private List<Compras> comprasList;

    public Proveedores() {
        this.idProv=0;
        this.nombreProv = "";
        this.telefono = "";
        this.direccion = "";
        comprasList = new ArrayList<Compras>();
    }

    public Proveedores(Integer idProv) {
        this.idProv = idProv;
    }

    public Integer getIdProv() {
        return idProv;
    }

    public void setIdProv(Integer idProv) {
        this.idProv = idProv;
    }

    public String getNombreProv() {
        return nombreProv;
    }

    public void setNombreProv(String nombreProv) {
        this.nombreProv = nombreProv;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @XmlTransient
    public List<Compras> getComprasList() {
        return comprasList;
    }

    public void setComprasList(List<Compras> comprasList) {
        this.comprasList = comprasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProv != null ? idProv.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedores)) {
            return false;
        }
        Proveedores other = (Proveedores) object;
        if ((this.idProv == null && other.idProv != null) || (this.idProv != null && !this.idProv.equals(other.idProv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.JpaClases.Proveedores[ idProv=" + idProv + " ]";
    }
    
}
