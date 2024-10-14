package sv.edu.udb.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import sv.edu.udb.repository.domain.Cliente;

import java.util.List;

@Transactional
@Repository
public class ClienteRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Cliente> findAll(){
        return entityManager.createNamedQuery("Cliente.findAll", Cliente.class).getResultList();
    }

    public Cliente findById(int id){
        return (Cliente) entityManager.createNamedQuery("Cliente.findByID").setParameter("id", id).getSingleResult();
    }

    public Long emailExists(String correo){
        return (Long) entityManager.createNamedQuery("Cliente.countEmail").setParameter("correo", correo).getSingleResult();
    }

    public Cliente auth(String correo, String contrasenia){
        try {
            return (Cliente) entityManager.createNamedQuery("Cliente.Auth")
                    .setParameter("correo", correo)
                    .setParameter("contrasenia", contrasenia)
                    .getSingleResult();
        }catch (Exception ex){
            return null;
        }
    }

    public int saveCliente(final Cliente cliente){
        entityManager.persist(cliente);
        return 1;
    }
}
