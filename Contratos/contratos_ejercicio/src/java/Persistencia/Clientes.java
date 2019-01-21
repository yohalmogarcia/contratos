/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
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

/**
 *
 * @author yohalmo.garciausam
 */
@Entity
@Table(name = "clientes")
@NamedQueries({
    @NamedQuery(name = "Clientes.findAll", query = "SELECT c FROM Clientes c")})
public class Clientes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cli")
    private Integer idCli;
    @Column(name = "nombre_cli")
    private String nombreCli;
    @Column(name = "apellido_cli")
    private String apellidoCli;
    @Column(name = "direccion_cli")
    private String direccionCli;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pago_cli")
    private Double pagoCli;
    @Column(name = "cancelado")
    private Integer cancelado;
    @OneToMany(mappedBy = "clientesIdCli")
    private List<Detalles> detallesList;

    public Clientes() {
    }

    public Clientes(Integer idCli) {
        this.idCli = idCli;
    }

    public Integer getCancelado() {
        return cancelado;
    }

    public void setCancelado(Integer cancelado) {
        this.cancelado = cancelado;
    }

    
    
    public Integer getIdCli() {
        return idCli;
    }

    public void setIdCli(Integer idCli) {
        this.idCli = idCli;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public String getApellidoCli() {
        return apellidoCli;
    }

    public void setApellidoCli(String apellidoCli) {
        this.apellidoCli = apellidoCli;
    }

    public String getDireccionCli() {
        return direccionCli;
    }

    public void setDireccionCli(String direccionCli) {
        this.direccionCli = direccionCli;
    }

    public Double getPagoCli() {
        return pagoCli;
    }

    public void setPagoCli(Double pagoCli) {
        this.pagoCli = pagoCli;
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
        hash += (idCli != null ? idCli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clientes)) {
            return false;
        }
        Clientes other = (Clientes) object;
        if ((this.idCli == null && other.idCli != null) || (this.idCli != null && !this.idCli.equals(other.idCli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Clientes[ idCli=" + idCli + " ]";
    }
    
}
