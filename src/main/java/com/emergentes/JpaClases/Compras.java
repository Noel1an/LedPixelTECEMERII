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
@Table(name = "compras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compras.findAll", query = "SELECT c FROM Compras c"),
    @NamedQuery(name = "Compras.findByIdCompra", query = "SELECT c FROM Compras c WHERE c.idCompra = :idCompra"),
    @NamedQuery(name = "Compras.findByDescCompra", query = "SELECT c FROM Compras c WHERE c.descCompra = :descCompra"),
    @NamedQuery(name = "Compras.findByCtdCompra", query = "SELECT c FROM Compras c WHERE c.ctdCompra = :ctdCompra"),
    @NamedQuery(name = "Compras.findByMonto", query = "SELECT c FROM Compras c WHERE c.monto = :monto"),
    @NamedQuery(name = "Compras.findByFechaCompra", query = "SELECT c FROM Compras c WHERE c.fechaCompra = :fechaCompra"),
    @NamedQuery(name = "Compras.findByEstado", query = "SELECT c FROM Compras c WHERE c.estado = :estado")})
public class Compras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCompra")
    private Integer idCompra;
    @Size(max = 30)
    @Column(name = "DescCompra")
    private String descCompra;
    @Column(name = "ctdCompra")
    private Integer ctdCompra;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Monto")
    private Float monto;
    @Column(name = "FechaCompra")
    @Temporal(TemporalType.DATE)
    private Date fechaCompra;
    @Size(max = 10)
    @Column(name = "estado")
    private String estado;
    @JoinColumn(name = "idProv", referencedColumnName = "idProv")
    @ManyToOne
    private Proveedores idProv;

    public Compras() {
        this.idCompra=0;
        this.descCompra="";
        this.ctdCompra=0;
        this.monto=0.0f;
        this.fechaCompra=new Date();
        this.estado="";
        this.idProv= new Proveedores();
    }

    public Compras(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getDescCompra() {
        return descCompra;
    }

    public void setDescCompra(String descCompra) {
        this.descCompra = descCompra;
    }

    public Integer getCtdCompra() {
        return ctdCompra;
    }

    public void setCtdCompra(Integer ctdCompra) {
        this.ctdCompra = ctdCompra;
    }

    public Float getMonto() {
        return monto;
    }

    public void setMonto(Float monto) {
        this.monto = monto;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Proveedores getIdProv() {
        return idProv;
    }

    public void setIdProv(Proveedores idProv) {
        this.idProv = idProv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compras)) {
            return false;
        }
        Compras other = (Compras) object;
        if ((this.idCompra == null && other.idCompra != null) || (this.idCompra != null && !this.idCompra.equals(other.idCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.emergentes.JpaClases.Compras[ idCompra=" + idCompra + " ]";
    }
    
}
