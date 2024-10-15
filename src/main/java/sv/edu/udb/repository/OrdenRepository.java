package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sv.edu.udb.repository.domain.Cliente;
import sv.edu.udb.repository.domain.Orden;

import java.util.List;

@Transactional
@Repository
public class OrdenRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Orden> findByCustomer(final Cliente cliente){
        return entityManager.createNamedQuery("Orden.findByCustomer", Orden.class).setParameter("id_cli", cliente).getResultList();
    }

    public int saveOrden(final Orden orden){
        entityManager.persist(orden);
        return 1;
    }

    public Orden findLastOrder(){
        TypedQuery<Orden> query = entityManager.createQuery(
                "Select o FROM Orden o ORDER BY o.id DESC", Orden.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }

    /*Con este metodo se hace una referencia a la llave foránea*/
    public Cliente foreignKey(final int key){
        return entityManager.getReference(Cliente.class, key);
    }
}