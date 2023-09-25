package ar.unrn.tp.jpa.service;

import ar.unrn.tp.api.ClienteInterfaz;
import ar.unrn.tp.modelo.Cliente;
import ar.unrn.tp.modelo.Tarjeta;
import ar.unrn.tp.modelo.TipoTarjeta;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteServicio implements ClienteInterfaz {

    private String consultaCrear = "SELECT c FROM Cliente c WHERE c.dni = '";
    private String consultaBuscarId = "SELECT c FROM Cliente c WHERE c.id =";

    private EntityManagerFactory emf; //= Persistence.createEntityManagerFactory("objectdb:myDbFile.odb");
    private EntityManager em;//= emf.createEntityManager();
    private EntityTransaction tx;//= em.getTransaction();

    public ClienteServicio(String servicio) {
        this.emf = Persistence.createEntityManagerFactory("jpa-mysql");
    }

    @Override
    public void crearCliente(String nombre, String apellido, String dni, String email) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        List<Cliente> clientes;
        try {
            this.tx.begin();

            // hay que validar que el dni no este registrado ya

            TypedQuery<Cliente> q = this.em.createQuery(consultaCrear + dni + "'", Cliente.class);
            clientes = q.getResultList();
            if (!clientes.isEmpty())
                throw new RuntimeException("El cliente ya se encuentra registrado");

            Cliente cliente = new Cliente(nombre, apellido, dni, email);
            this.em.persist(cliente);

            this.tx.commit();

        } catch (Exception e) {
            this.tx.rollback();
            throw new RuntimeException(e);

        } finally {
            if (this.em.isOpen())
                this.em.close();
//            if (this.emf.isOpen())
//                this.emf.close();
        }
    }

    @Override
    public void modificarCliente(Long idCliente, String nombre, String apellido, String dni, String email) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        List<Cliente> clientes;
        try {
            this.tx.begin();

            // hay que validar que el dni no este registrado ya

            TypedQuery<Cliente> q = this.em.createQuery(consultaBuscarId + idCliente, Cliente.class);
            clientes = q.getResultList();


            if (clientes.isEmpty())
                throw new RuntimeException("El cliente no esta registrado");

            Cliente cliente = this.em.getReference(Cliente.class, idCliente);
            cliente.setNombre(nombre);
            cliente.setDni(dni);
            cliente.setApellido(apellido);
            cliente.setEmail(email);

            this.em.persist(cliente);

            this.tx.commit();

        } catch (Exception e) {
            this.tx.rollback();
            throw new RuntimeException(e);

        } finally {
            if (this.em.isOpen())
                this.em.close();
//            if (this.emf.isOpen())
//                this.emf.close();
        }

    }

    @Override
    public void agregarTarjeta(Long idCliente, String numero, String tipo) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        List<Cliente> clientes;
        try {
            this.tx.begin();

            // hay que validar que el dni no este registrado ya

            TypedQuery<Cliente> q = this.em.createQuery(consultaBuscarId + idCliente, Cliente.class);
            clientes = q.getResultList();


            if (clientes.isEmpty())
                throw new RuntimeException("El cliente no esta registrado");

            Cliente cliente = em.getReference(Cliente.class, idCliente);
            cliente.agregarTarjeta(new Tarjeta(Integer.parseInt(numero), TipoTarjeta.valueOf(tipo.toUpperCase())));


            this.em.persist(cliente);

            this.tx.commit();

        } catch (Exception e) {
            this.tx.rollback();
            throw new RuntimeException(e);

        } finally {
            if (this.em.isOpen())
                this.em.close();
//            if (this.emf.isOpen())
//                this.emf.close();
        }

    }

    @Override
    public List listarTarjetas(Long idCliente) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        List<Cliente> clientes = new ArrayList<>();
        List<Tarjeta> tarjetas = new ArrayList<>();
        try {
            this.tx.begin();

            TypedQuery<Cliente> q = this.em.createQuery(consultaBuscarId + idCliente, Cliente.class);
            clientes = q.getResultList();


            if (clientes.isEmpty())
                throw new RuntimeException("El cliente no esta registrado");

            Cliente cliente = this.em.getReference(Cliente.class, idCliente);
            tarjetas = cliente.getTarjetas();

            //System.out.println("TARJETAS: "+tarjetas);
            this.tx.commit();

        } catch (Exception e) {
            this.tx.rollback();
            throw new RuntimeException(e);

        } finally {
            if (this.em.isOpen())
                this.em.close();
//            if (this.emf.isOpen())
//                this.emf.close();
        }
        return tarjetas;
    }
}

