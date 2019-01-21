package Mentenimientos;

import Persistencia.Clientes;
import Persistencia.Detalles;
import Persistencia.Servicios;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAODetalle {

    EntityManagerFactory factory;
    EntityManager em = null;

    public DAODetalle() {
        factory = Persistence.createEntityManagerFactory("contratos_ejercicioPU");
        em = factory.createEntityManager();
    }

    public boolean agregarDetalle(Detalles d) {
        boolean r = false;
        try {
            em.getTransaction().begin();
            em.persist(d);
            em.getTransaction().commit();
            r = true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ERROR AL AGREGAR UN DETALLE: " + e);
        } finally {
            em.close();
        }
        return r;
    }
    
}
