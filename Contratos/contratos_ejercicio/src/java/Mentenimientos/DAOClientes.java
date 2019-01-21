package Mentenimientos;

import Persistencia.Clientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

public class DAOClientes {
    EntityManagerFactory factory;
    EntityManager em = null;

    public DAOClientes() {
        factory = Persistence.createEntityManagerFactory("contratos_ejercicioPU");
        em = factory.createEntityManager();
    }
    
    public boolean agregarClienteSP(Clientes c) {
        boolean r = false;
        try {
            em.getTransaction().begin();
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("sp_insCliente");
            storedProcedure.registerStoredProcedureParameter("nombre", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("apellido", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("direccion", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("pago", Double.class, ParameterMode.IN);
            storedProcedure.setParameter("nombre", c.getNombreCli());
            storedProcedure.setParameter("apellido", c.getApellidoCli());
            storedProcedure.setParameter("direccion", c.getDireccionCli());
            storedProcedure.setParameter("pago", 0.0);
            storedProcedure.execute();
            em.getTransaction().commit();
            r=true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ERROR AL AGREGAR: " + e);
        }finally{
            em.close();
        }
        return r;
    }
    
    public boolean modificarCliente(Clientes c){
        boolean r = false;
        Clientes cliente;
        try {
            cliente = em.find(Clientes.class, c.getIdCli());
            em.getTransaction().begin();
            cliente.setNombreCli(c.getNombreCli());
            cliente.setApellidoCli(c.getApellidoCli());
            em.getTransaction().commit();
            r=true;
        } catch (Exception e) {
            System.out.println("ERROR AL ACTUALIZAR INFO DE CLIENTE: "+e);
            em.getTransaction().rollback();            
        }finally{
            em.close();
        }
        return r;
    }
    
    public int ultimoCliente(){
        int r =-1;
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT MAX(c.idCli) FROM Clientes c");
            em.getTransaction().commit();
            String resultado=q.getResultList().get(0).toString();
            //System.out.println(resultado);
            r = Integer.parseInt(resultado);
        } catch (Exception e) {
            System.out.println("ERROR AL BUSCAR EL ULTIMO CLIENTE. "+e);
        }finally{
            em.close();
        }
        return r;
    }
    
    public boolean cambiarEstadoCliente(Clientes c){
        boolean r = false;
        Clientes cliente;
        try {
            cliente = em.find(Clientes.class, c.getIdCli());
            em.getTransaction().begin();
            cliente.setCancelado(c.getCancelado());            
            em.getTransaction().commit();
            r=true;
        } catch (Exception e) {
            System.out.println("ERROR AL ACTUALIZAR ESTADO DE CLIENTE: "+e);
            em.getTransaction().rollback();            
        }finally{
            em.close();
        }
        return r;
    }
    
    public boolean eliminarCliente(Clientes c){
        boolean r = false;
        Clientes cliente;
        try {
            cliente = em.find(Clientes.class, c.getIdCli());
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
            r=true;
        } catch (Exception e) {
            System.out.println("ERROR AL ELIMINAR UN CLIENTE: "+e);
            em.getTransaction().rollback();            
        }finally{
            em.close();
        }
        return r;
    }
    
    public List listadoClientes(){
        List<Clientes> listado = null;
        try {
            em.getTransaction().begin();
            Query q = em.createQuery("SELECT c FROM Clientes c");
            em.getTransaction().commit();
            listado = q.getResultList();            
        } catch (Exception e) {
            System.out.println("ERROR AL CONSULTAR TODOS LOS CLIENTES. "+e);
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
        return listado;
    }
}
