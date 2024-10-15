package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sv.edu.udb.repository.domain.DetalleOrden;
import sv.edu.udb.repository.domain.Orden;
import sv.edu.udb.repository.domain.Platillo;

import java.util.List;

@Transactional
@Repository
public class DetalleOrdenRepository {
    @PersistenceContext
    private EntityManager entityManager;

    /*
    * Debería regresar una lista de registros que especifica la orden
    * Cada registro con el mismo id de orden es un platillo
    * NOTA: revisar si al querer acceder a los atributos de los productos funcionaria
    * UPDATE: Si funciona pero al ser una query compuesta se recupera con un tipo de objeto Object identificado como array
    */
    public List<Object[]> listByOrden(final Orden orden){
        return entityManager.createNamedQuery("DetalleOrden.listByOrden").setParameter("idOrden", orden).getResultList();
    }

    public void saveDetalle(final List<DetalleOrden> detalles, Orden orden){
        for(DetalleOrden detalle : detalles) {
            detalle.setIdOrden(orden);
            entityManager.persist(detalle);
        }
    }

    public Orden orderKey(final int key){
        return entityManager.getReference(Orden.class, key);
    }

    public Platillo productKey(final int key){
        return entityManager.getReference(Platillo.class, key);
    }
}
