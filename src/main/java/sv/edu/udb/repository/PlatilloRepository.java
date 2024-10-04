package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sv.edu.udb.repository.domain.Platillo;

import java.util.List;

@Transactional
@Repository
public class PlatilloRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Platillo> findAll(){
        return entityManager.createNamedQuery("Platillo.findAll", Platillo.class).getResultList();
    }

    public Platillo findById(int id){
        return (Platillo) entityManager.createNamedQuery("Platillo.findById").setParameter("id", id).getSingleResult();
    }
}
