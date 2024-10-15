package sv.edu.udb.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.repository.domain.DetalleOrden;
import sv.edu.udb.repository.domain.Orden;
import sv.edu.udb.repository.domain.Platillo;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Disabled
public class DetalleOrdenRepositoryTest {
    @Autowired
    DetalleOrdenRepository detalleOrdenRepository;

    @Test
    @Transactional
    void shouldListAllDetailsByOrder(){
        final int expectedIdOrder = 17;
        final Orden actualOrder = detalleOrdenRepository.orderKey(expectedIdOrder);
        assertNotNull(actualOrder);

        final List<Object[]> detalleOrdenList = detalleOrdenRepository.listByOrden(actualOrder);
        assertNotNull(detalleOrdenList);
        assertEquals(1, detalleOrdenList.size());

        /*Acá se recorre la lista con ForEach y se accede a los datos mediante los indices del Array
         * recordar que el Array inicia en indice 0
         * NO se aplica casting dado que se recuperan datos de tipo String los cuales van a imprimirse*/
        for (Object[] detalle : detalleOrdenList){
            System.out.println(detalle[0] +"\n"+ detalle[1] +"\n"+
                    detalle[2] +"\n"+ detalle[3]);
            System.out.println("*****************FIN*****************");
        }
        System.out.println("**************Fin de lista*******************");
    }
    @Test
    @Disabled
    void shouldInsertDetail(){
        final int expectedOrderId = 40;
        final int expectedProductKey = 16;
        final BigDecimal expectedPrice = BigDecimal.valueOf(100);
        final int expectedQuantity = 15;
        final Orden actualOrder = detalleOrdenRepository.orderKey(expectedOrderId);
        assertNotNull(actualOrder);
        final Platillo actualProduct = detalleOrdenRepository.productKey(expectedProductKey);
        assertNotNull(actualProduct);

        final DetalleOrden orderDetail = DetalleOrden.builder()
                .idOrden(actualOrder)
                .precio(expectedPrice)
                .cantidad(expectedQuantity)
                .idPlat(actualProduct)
                .build();
        assertNotNull(orderDetail);

        //final int res = detalleOrdenRepository.saveDetalle(orderDetail);
        //assertTrue(res > 0);
    }
}
