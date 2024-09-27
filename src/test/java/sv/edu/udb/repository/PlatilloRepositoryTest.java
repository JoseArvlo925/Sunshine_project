package sv.edu.udb.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.repository.domain.Platillo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PlatilloRepositoryTest {
    @Autowired
    PlatilloRepository platilloRepository;

    @Test
    @Transactional
    void shouldListAllProducts(){
        final int expectedSize = 2;
        final List<Platillo> platilloList = platilloRepository.findAll();
        assertNotNull(platilloList);
        assertEquals(expectedSize, platilloList.size());

        for(Platillo platillo : platilloList){
            System.out.println(platillo.getId());
            System.out.println(platillo.getNombre());
            System.out.println(platillo.getPrecio());
            System.out.println(platillo.getImagen());
            System.out.println(platillo.getIdTipo().getId());
            System.out.println(platillo.getIdTipo().getTipo());
            System.out.println("****************FIN*****************");
        }
        System.out.println("\nFIN DE LISTA");
    }

    @Test
    @Transactional
    void shouldFindAProduct_When_IdExists(){
        final int expectedId = 16;
        final Platillo actualPlatillo = platilloRepository.findById(expectedId);
        assertNotNull(actualPlatillo);

        System.out.println(actualPlatillo.getId() +"\n"+ actualPlatillo.getNombre() +"\n"+
                actualPlatillo.getPrecio() +"\n"+ actualPlatillo.getImagen() +"\n"+ actualPlatillo.getIdTipo().getTipo());
    }
}
