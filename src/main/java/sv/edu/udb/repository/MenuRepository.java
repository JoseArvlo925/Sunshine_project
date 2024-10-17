package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sv.edu.udb.repository.domain.Menu;

import java.util.List;

@Repository
@Transactional
public class MenuRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Menu> findAll(){
        return entityManager.createQuery("SELECT m from Menu m", Menu.class).getResultList();
    }

    public Menu findById(int id){
        return entityManager.createQuery("SELECT m from Menu m WHERE m.id = :id", Menu.class)
                .setParameter("id", id).getSingleResult();
    }
}
