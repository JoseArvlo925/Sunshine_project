package sv.edu.udb.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.repository.domain.Cliente;
import sv.edu.udb.repository.domain.Orden;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrdenRepositoryTest {
    @Autowired
    OrdenRepository ordenRepository;

    /*Por alguna razon, al poner la anotación de transactional le surjen altercados con el metodo persist de
    * entityManager
    * Tomar en cuenta que la clase ordenRepository ya posee una anotación @Transactional a nivel de clase*/
    @Test
    @Disabled
    void shouldInsertANewOrder(){
        final int expectedIdCustomer = 20;
        final BigDecimal total = BigDecimal.valueOf(100);
        final String estado = "Entregado";
        final Cliente actualCliente = ordenRepository.foreignKey(expectedIdCustomer);
        assertNotNull(actualCliente);
        final int expectedResult = 1;

         Orden orden = Orden.builder()
                 .idCli(actualCliente)
                 .fechaPed(LocalDate.of(2024, 9, 12))
                 .total(total)
                 .estado(estado)
                 .build();
         final int res = ordenRepository.saveOrden(orden);

         assertEquals(expectedResult ,res);
    }

    @Test
    @Transactional
    void shouldFindOrder_When_CustomerExists(){
        final int expectedIdCustomer = 20;
        final int expectedSize = 1;
        final Cliente actualCliente = ordenRepository.foreignKey(expectedIdCustomer);
        assertNotNull(actualCliente);

        final List<Orden> orderList = ordenRepository.findByCustomer(actualCliente);
        assertNotNull(orderList);
        assertEquals(expectedSize, orderList.size());

        for (Orden orden : orderList){
            System.out.println(orden.getId() +"\n"+ orden.getIdCli().getNombres() +"\n"+
                    orden.getFechaPed() +"\n"+ orden.getTotal() +"\n"+ orden.getEstado());
            System.out.println("**********Fin de orden*****************");
        }
        System.out.println("*********Fin de lista*************");
    }
}
