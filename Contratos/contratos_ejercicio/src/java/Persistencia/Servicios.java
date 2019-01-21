/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yohalmo.garciausam
 */
@Entity
@Table(name = "servicios")
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s")})
public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ser")
    private Integer idSer;
    @Column(name = "nombre_ser")
    private String nombreSer;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio_ser")
    private Double precioSer;
    @Column(name = "fecha_ser")
    @Temporal(TemporalType.DATE)
    private Date fechaSer;
    @Column(name = "descuento_ser")
    private Double descuentoSer;
    @OneToMany(mappedBy = "serviciosIdSer")
    private List<Detalles> detallesList;

    public Servicios() {
    }

    public Servicios(Integer idSer) {
        this.idSer = idSer;
    }

    public Integer getIdSer() {
        return idSer;
    }

    public void setIdSer(Integer idSer) {
        this.idSer = idSer;
    }

    public String getNombreSer() {
        return nombreSer;
    }

    public void setNombreSer(String nombreSer) {
        this.nombreSer = nombreSer;
    }

    public Double getPrecioSer() {
        return precioSer;
    }

    public void setPrecioSer(Double precioSer) {
        this.precioSer = precioSer;
    }

    public Date getFechaSer() {
        return fechaSer;
    }

    public void setFechaSer(Date fechaSer) {
        this.fechaSer = fechaSer;
    }

    public Double getDescuentoSer() {
        return descuentoSer;
    }

    public void setDescuentoSer(Double descuentoSer) {
        this.descuentoSer = descuentoSer;
    }

    public List<Detalles> getDetallesList() {
        return detallesList;
    }

    public void setDetallesList(List<Detalles> detallesList) {
        this.detallesList = detallesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSer != null ? idSer.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.idSer == null && other.idSer != null) || (this.idSer != null && !this.idSer.equals(other.idSer))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Servicios[ idSer=" + idSer + " ]";
    }
    
}
