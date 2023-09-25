package ar.unrn.tp.jpa.service;

import ar.unrn.tp.api.PromocionInterfaz;
import ar.unrn.tp.modelo.*;

import javax.persistence.*;
import java.time.LocalDate;

public class PromocionServicio implements PromocionInterfaz {
    private EntityManagerFactory emf; //= Persistence.createEntityManagerFactory("objectdb:myDbFile.odb");
    private EntityManager em;//= emf.createEntityManager();
    private EntityTransaction tx;//= em.getTransaction();

    public PromocionServicio(String servicio) {
        this.emf = Persistence.createEntityManagerFactory("jpa-mysql");

    }
    @Override
    public void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde, LocalDate fechaHasta,
                                         float porcentaje) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();

        try {
            tx.begin();
            // validar que las fechas no se superpongan

            Tienda tienda = em.find(Tienda.class, 13L);
            Promocion tarjetaPromocion = new TarjetaPromocion(true,
                    fechaDesde, fechaHasta, (double) porcentaje, marcaTarjeta.toUpperCase());
            //   System.out.println(tarjetaPromocion);
            //  em.persist(tarjetaPromocion);

            tienda.setTarjetaPromocion(tarjetaPromocion);
            tienda.setTarjetaPromocion(new TarjetaPromocion(true,
                    fechaDesde, fechaHasta, (double) porcentaje, marcaTarjeta.toUpperCase()));

            tx.commit();

        } catch (Exception e) {
            System.out.println("ERROR: "+ e.getMessage());
            tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }
    }

    @Override
    public void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate fechaHasta, float porcentaje) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        Marca marca;
        try {
            tx.begin();
            // validar que las fechas no se superpongan

            TypedQuery<Marca> qm = em.createQuery("select m from Marca m where m.nombre=:nombre", Marca.class);
            qm.setParameter("nombre", marcaProducto);
            marca = qm.getSingleResult();
            if (marca == null)
                throw new RuntimeException("La marca ingresada no existe.");

            Tienda tienda = em.find(Tienda.class, 31L);
            tienda.setMarcaPromocion(new MarcaPromocion(true, fechaDesde, fechaHasta, (double) porcentaje, marca.getNombre()));

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }

    }

    @Override
    public void agregarPromocionMarca(MarcaPromocion marcaPromocion) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        Marca marca;

        try {
            tx.begin();
            // validar que las fechas no se superpongan

            TypedQuery<Marca> qm = em.createQuery("select m from Marca m where m.nombre=:nombreMarca", Marca.class);
            qm.setParameter("nombreMarca", marcaPromocion.getMarca());
            marca = qm.getSingleResult();
            if (marca == null)
                throw new RuntimeException("La marca ingresada no existe.");

            Tienda tienda = em.find(Tienda.class, 31L);
            tienda.setMarcaPromocion(marcaPromocion);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }

    }

    @Override
    public void agregarPromocionTarjeta(TarjetaPromocion tarjetaPromocion) {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();
        Tarjeta tarjeta;

        try {
            tx.begin();
            // validar que las fechas no se superpongan

            TypedQuery<Tarjeta> qm = em.createQuery("select t from Tarjeta t where t.tipoTarjeta=:nombreTarjeta", Tarjeta.class);
            qm.setParameter("nombreTarjeta", TipoTarjeta.valueOf( tarjetaPromocion.getTarjeta()));
            tarjeta = qm.getSingleResult();
            if (tarjeta == null)
                throw new RuntimeException("La Tarjeta ingresada no existe.");

            Tienda tienda = em.find(Tienda.class, 31L);
            tienda.setTarjetaPromocion(tarjetaPromocion);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }

    }

    @Override
    public void crearTienda() {
        this.em = emf.createEntityManager();
        this.tx = em.getTransaction();

        try {
            tx.begin();
            Tienda tienda = new Tienda();
            em.persist(tienda);

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            if (em.isOpen())
                em.close();
//            if (emf.isOpen())
//                emf.close();
        }
    }

}
