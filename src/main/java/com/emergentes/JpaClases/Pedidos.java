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
@Table(name = "pedidos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedidos.findAll", query = "SELECT p FROM Pedidos p"),
    @NamedQuery(name = "Pedidos.findByIdPedido", query = "SELECT p FROM Pedidos p WHERE p.idPedido = :idPedido"),
    @NamedQuery(name = "Pedidos.findByNombreCli", query = "SELECT p FROM Pedidos p WHERE p.nombreCli = :nombreCli"),
    @NamedQuery(name = "Pedidos.findByCiCli", query = "SELECT p FROM Pedidos p WHERE p.ciCli = :ciCli"),
    @NamedQuery(name = "Pedidos.findByTelefonoCli", query = "SELECT p FROM Pedidos p WHERE p.telefonoCli = :telefonoCli"),
    @NamedQuery(name = "Pedidos.findByCtdPed", query = "SELECT p FROM Pedidos p WHERE p.ctdPed = :ctdPed"),
    @NamedQuery(name = "Pedidos.findByPago", query = "SELECT p FROM Pedidos p WHERE p.pago = :pago"),
    @NamedQuery(name = "Pedidos.findByFechaEntrega", query = "SELECT p FROM Pedidos p WHERE p.fechaEntrega = :fechaEntrega")})
public class Pedidos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPedido")
    private Integer idPedido;
    @Size(max = 100)
    @Column(name = "NombreCli")
    private String nombreCli;
    @Size(max = 50)
    @Column(name = "CiCli")
    private String ciCli;
    @Size(max = 20)
    @Column(name = "TelefonoCli")
    private String telefonoCli;
    @Column(name = "ctdPed")
    private Integer ctdPed;
    @Column(name = "Pago")
    private Integer pago;
    @Column(name = "FechaEntrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @JoinColumn(name = "idObj", referencedColumnName = "idObj")
    @ManyToOne
    private Almacen idObj;
    @JoinColumn(name = "idEmp", referencedColumnName = "idEmp")
    @ManyToOne
    private Empleados idEmp;

    public Pedidos() {
        this.idPedido=0;
        this.nombreCli="";
        this.ciCli="";
        this.telefonoCli="";
        this.ctdPed=0;
        this.pago=0;
        this.fechaEntrega= new Date();
        this.idObj = new Almacen();
        this.idEmp = new Empleados();
    }

    public Pedidos(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public String getCiCli() {
        return ciCli;
    }

    public void setCiCli(String ciCli) {
        this.ciCli = ciCli;
    }

    public String getTelefonoCli() {
        return telefonoCli;
    }

    public void setTelefonoCli(String telefonoCli) {
        this.telefonoCli = telefonoCli;
    }

    public Integer getCtdPed() {
        return ctdPed;
    }

    public void setCtdPed(Integer ctdPed) {
        this.ctdPed = ctdPed;
    }

    public Integer getPago() {
        return pago;
    }

    public void setPago(Integer pago) {
        this.pago = pago;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Almacen getIdObj() {
        return idObj;
    }

    public void setIdObj(Almacen idObj) {
        this.idObj = idObj;
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
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedidos)) {
            return false;
        }
        Pedidos other = (Pedidos) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.JpaClases.Pedidos[ idPedido=" + idPedido + " ]";
    }
    
}
