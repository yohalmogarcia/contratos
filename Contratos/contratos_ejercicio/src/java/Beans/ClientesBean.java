package Beans;

import Mentenimientos.DAOClientes;
import Mentenimientos.DAODetalle;
import Mentenimientos.DAOServicios;
import Persistencia.Clientes;
import Persistencia.Detalles;
import Persistencia.Servicios;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ClientesBean {
    
    private Clientes cliente;
    private Detalles detalle;
    private Servicios servicio;
    private List<Clientes> listadoClientes;
    private List<Servicios> listadoServicios;
    private DAOClientes dao;

    public ClientesBean() {
    }
    
    @PostConstruct
    public void init(){
        dao= new DAOClientes();
        DAOServicios daoS = new DAOServicios();
        this.listadoClientes = dao.listadoClientes();
        this.listadoServicios=daoS.listadoServicios();
        this.cliente = new Clientes();
        this.detalle=new Detalles();
        this.servicio = new Servicios();
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }
    
    

    public Detalles getDetalle() {
        return detalle;
    }

    public void setDetalle(Detalles detalle) {
        this.detalle = detalle;
    }    

    public List<Servicios> getListadoServicios() {
        return listadoServicios;
    }

    public void setListadoServicios(List<Servicios> listadoServicios) {
        this.listadoServicios = listadoServicios;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public List<Clientes> getListadoClientes() {
        return listadoClientes;
    }

    public void setListadoClientes(List<Clientes> listadoClientes) {
        this.listadoClientes = listadoClientes;
    }
    
    public void guardar(){
        DAOClientes daoCl = new DAOClientes();
        DAODetalle daoDe = new DAODetalle();
        if(daoCl.agregarClienteSP(cliente)){
            daoCl= new DAOClientes();
            this.cliente.setIdCli(daoCl.ultimoCliente());
            this.detalle.setServiciosIdSer(servicio);
            this.detalle.setClientesIdCli(cliente);
            if(daoDe.agregarDetalle(detalle)){
                this.cliente = new Clientes();
                dao = new DAOClientes();
                DAOServicios daoSe = new DAOServicios();
                this.listadoServicios = daoSe.listadoServicios();
                this.listadoClientes=dao.listadoClientes();
                System.out.println("EXITO");
            }else{
                System.out.println("ERROR:");
            }
        }
        
    }
    
}
