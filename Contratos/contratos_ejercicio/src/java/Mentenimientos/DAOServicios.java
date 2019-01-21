package Mentenimientos;

import Persistencia.Servicios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class DAOServicios {
    
    EntityManagerFactory factory;
    EntityManager em = null;
    
    public DAOServicios() {
        factory = Persistence.createEntityManagerFactory("contratos_ejercicioPU");
        em = factory.createEntityManager();
    }
    
    public boolean agregarServicio(Servicios s){
        boolean r = false;
        try {
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            r=true;
        } catch (Exception e) {
            System.out.println("ERROR AL AGREGAR UN NUEVO SERVICIO. "+e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        return r;
    }
    
    public boolean modificarServicio(Servicios s){
         boolean r = false;
        Servicios servicio;
        try {
            servicio = em.find(Servicios.class, s.getIdSer() );
            em.getTransaction().begin();
            servicio.setNombreSer(s.getNombreSer());
            servicio.setPrecioSer(s.getPrecioSer());
            servicio.setFechaSer(s.getFechaSer());
            servicio.setDescuentoSer(s.getDescuentoSer());
            em.getTransaction().commit();
            r=true;
        } catch (Exception e) {
            System.out.println("ERROR AL ACTUALIZAR INFO DE SERVICIO: "+e);
            em.getTransaction().rollback(); 
            r=false;
        }finally{
            em.close();
        }
        return r;
    }
    
    public boolean eliminarServicio(Servicios s){
        boolean r = false;
        Servicios servicio;
        try {
            servicio = em.find(Servicios.class, s.getIdSer());
            em.getTransaction().begin();
            em.remove(servicio);
            em.getTransaction().commit();
            r=true;
        } catch (Exception e) {
            System.out.println("ERROR AL ELIMINAR UN SERVICIO: "+e);
            em.getTransaction().rollback();    
            return false;
        }finally{
            em.close();
        }
        return r;
    }
    
    public int ultimoServicio(){
        int r =-1;
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT MAX(s.idSer) FROM Servicios s");
            em.getTransaction().commit();
            String resultado=q.getResultList().get(0).toString();
            //System.out.println(resultado);
            r = Integer.parseInt(resultado);
        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR EL ULTIMO SERVICIO. "+e);
        }finally{
            em.close();
        }
        return r;
    }
    
    public List listadoServicios(){
        List<Servicios> listado = null;
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT s FROM Servicios s");
            em.getTransaction().commit();
            listado = q.getResultList();            
        } catch (Exception e) {
            System.out.println("ERROR AL CONSULTAR TODOS LOS SERVICIOS. "+e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        return listado;
    }
    
}
