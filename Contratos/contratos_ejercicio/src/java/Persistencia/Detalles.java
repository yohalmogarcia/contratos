/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

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

/**
 *
 * @author yohalmo.garciausam
 */
@Entity
@Table(name = "detalles")
@NamedQueries({
    @NamedQuery(name = "Detalles.findAll", query = "SELECT d FROM Detalles d")})
public class Detalles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_det")
    private Integer idDet;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_det")
    private Double totalDet;
    @JoinColumn(name = "clientes_id_cli", referencedColumnName = "id_cli")
    @ManyToOne
    private Clientes clientesIdCli;
    @JoinColumn(name = "servicios_id_ser", referencedColumnName = "id_ser")
    @ManyToOne
    private Servicios serviciosIdSer;

    public Detalles() {
    }

    public Detalles(Integer idDet) {
        this.idDet = idDet;
    }

    public Integer getIdDet() {
        return idDet;
    }

    public void setIdDet(Integer idDet) {
        this.idDet = idDet;
    }

    public Double getTotalDet() {
        return totalDet;
    }

    public void setTotalDet(Double totalDet) {
        this.totalDet = totalDet;
    }

    public Clientes getClientesIdCli() {
        return clientesIdCli;
    }

    public void setClientesIdCli(Clientes clientesIdCli) {
        this.clientesIdCli = clientesIdCli;
    }

    public Servicios getServiciosIdSer() {
        return serviciosIdSer;
    }

    public void setServiciosIdSer(Servicios serviciosIdSer) {
        this.serviciosIdSer = serviciosIdSer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDet != null ? idDet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Detalles)) {
            return false;
        }
        Detalles other = (Detalles) object;
        if ((this.idDet == null && other.idDet != null) || (this.idDet != null && !this.idDet.equals(other.idDet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Detalles[ idDet=" + idDet + " ]";
    }
    
}
