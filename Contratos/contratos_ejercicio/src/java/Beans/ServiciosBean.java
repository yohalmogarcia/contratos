package Beans;

import Mentenimientos.DAOServicios;
import Persistencia.Servicios;
import java.sql.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ServiciosBean {    
    private Servicios servicio;
    private String fechaServicio;    
    private List<Servicios> listaServicios;
    private java.util.Date fechaUtil; 
    private boolean edit;
    
    public ServiciosBean() {        
    }    
    
    @PostConstruct
    public void init() {
        DAOServicios dao = new DAOServicios();
        this.listaServicios = dao.listadoServicios();
        this.servicio= new Servicios(); 
        this.edit = false;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    public java.util.Date getFechaUtil() {
        return fechaUtil;
    }

    public void setFechaUtil(java.util.Date fechaUtil) {
        this.fechaUtil = fechaUtil;
    }

    public List<Servicios> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Servicios> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public String getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(String fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
    
    public void guardarServicio() {
        DAOServicios dao = new DAOServicios();
        fechaUtil = servicio.getFechaSer();
        long mili = fechaUtil.getTime();
        Date fecha = new Date(mili);        
        servicio.setFechaSer(fecha); 
        boolean respuesta;
        System.out.println("EDIT: "+edit);
        if(edit){
            respuesta = dao.modificarServicio(servicio);
        }else{
            respuesta = dao.agregarServicio(servicio);            
        }
        if(respuesta){
            dao = new DAOServicios();
            this.servicio= new Servicios();
            this.listaServicios = dao.listadoServicios();
            System.out.println("EXITO");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Guardar Datos","Datos Guardados Exitosamente");
            FacesContext.getCurrentInstance().addMessage(null, message);                                
        }else{
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Guardar Datos","Ocurrio un error al guardar los datos");
            FacesContext.getCurrentInstance().addMessage(null, message);                                
        }
    }   
    
    
    public void eliminarServicio(Servicios s){
        DAOServicios dao = new DAOServicios();
        boolean respuesta = dao.eliminarServicio(s);
        System.out.println("RESPUESTA ELIMINAR: "+respuesta);
        if(respuesta){
            System.out.println("ELIMINADO");
            dao = new DAOServicios();
            this.servicio= new Servicios();
            this.listaServicios = dao.listadoServicios();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminacion Exitosa","Se eliminó el registro exitosamente.");
            FacesContext.getCurrentInstance().addMessage(null, message);                    
        }else{
            System.out.println("NO SE PUDO ELIMINAR");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al Eliminar","Ocurrio un error al intentar eliminar el registro.");
            FacesContext.getCurrentInstance().addMessage(null, message);        
        }        
    }
    
    public void verDetalleServicio(Servicios s){
        this.edit=true;        
        this.servicio = s;        
        
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,"Eliminacion Exitosa",this.servicio.getNombreSer());
//        FacesContext.getCurrentInstance().addMessage(null, message);                    
//        System.out.println(servicio.getNombreSer());
    }

}

    