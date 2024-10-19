package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sv.edu.udb.repository.domain.Platillo;

import java.util.List;

@Repository
@Transactional
public class PlatilloRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Platillo> findAll(){
        return entityManager.createNamedQuery("Platillo.findAll", Platillo.class).getResultList();
    }

    public Platillo findById(int id){
        return (Platillo) entityManager.createNamedQuery("Platillo.findById").setParameter("id", id).getSingleResult();
    }

    public void save(Platillo platillo){
        entityManager.persist(platillo);
    }

    public void delete(Platillo platillo){
        entityManager.remove(platillo);
    }

    public void modificar(Platillo obj){
        String query = "UPDATE Platillo p SET p.nombre = :nombre, p.precio = :precio, p.imagen = :imagen," +
                " p.idTipo = :tipo WHERE p.id = :id";
        entityManager.createQuery(query)
                .setParameter("nombre", obj.getNombre())
                .setParameter("precio", obj.getPrecio())
                .setParameter("imagen", obj.getImagen())
                .setParameter("tipo", obj.getIdTipo())
                .setParameter("id", obj.getId())
                .executeUpdate();
        entityManager.flush();
        entityManager.clear();
    }

    public List<Platillo> desayunos(){
        return entityManager.createNamedQuery("Platillo.desayunos", Platillo.class).getResultList();
    }

    public List<Platillo> almuerzos(){
        return entityManager.createNamedQuery("Platillo.almuerzos", Platillo.class).getResultList();
    }

    public List<Platillo> cenas(){
        return entityManager.createNamedQuery("Platillo.cenas", Platillo.class).getResultList();
    }
}
