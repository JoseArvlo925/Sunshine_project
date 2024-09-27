package sv.edu.udb.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sv.edu.udb.repository.domain.Cliente;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClienteRepositoryTest {
    @Autowired
    ClienteRepository clienteRepository;

    @Test
    @Disabled
    void shouldInsertCustomer(){
        final String nombre = "Luis";
        final String apellido = "Arias";
        final String telefono = "4456-2289";
        final String correo = "correo@yahoo.com.sv";
        final String contrasenia = "contrasenia123";

        Cliente cliente = Cliente.builder()
                .nombres(nombre)
                .apellidos(apellido)
                .telefono(telefono)
                .correo(correo)
                .contrasenia(contrasenia)
                .build();
        final int res = clienteRepository.saveCliente(cliente);

        if(res > 0)
            System.out.println("Se debe haber registrado cliente");
        else
            System.out.println("Ocurrió un error");
    }

    @Test
    void shouldListAllCustomers(){
        final List<Cliente> listaClientes = clienteRepository.findAll();

        for(Cliente cliente : listaClientes){
            System.out.println(cliente.getId());
            System.out.println(cliente.getNombres());
            System.out.println(cliente.getApellidos());
            System.out.println(cliente.getTelefono());
            System.out.println(cliente.getCorreo());
            System.out.println(cliente.getContrasenia());
            System.out.println("**********FIN**********");
        }

        System.out.println("**********FIN Lista de clientes***************");
    }

    @Test
    void shouldGetCustomer_When_IdExists(){
        final int expectedId = 16;
        final Cliente expectedCustomer = clienteRepository.findById(expectedId);
        assertNotNull(expectedCustomer);

        System.out.println(expectedCustomer.getId());
        System.out.println(expectedCustomer.getNombres());
        System.out.println(expectedCustomer.getApellidos());
        System.out.println(expectedCustomer.getTelefono());
        System.out.println(expectedCustomer.getCorreo());
        System.out.println(expectedCustomer.getContrasenia());
    }

    @Test
    void shouldGetANumber_When_EmailExists(){
        final String expectedCorreo = "joaearvlo925@gmail.com";
        final Long expectedNumber = 1L;
        final Long emailExists = clienteRepository.emailExists(expectedCorreo);
        assertEquals(expectedNumber, (long) emailExists);
    }

    @Test
    void shouldGetACustomer_When_Exists(){
        final int expectedId = 21;
        final String expectedEmail = "correo@yahoo.com.sv";
        final String expectedPasword = "contrasenia123";
        final Cliente actualCustomer = clienteRepository.auth(expectedEmail, expectedPasword);
        assertNotNull(actualCustomer);

        assertEquals(expectedId, actualCustomer.getId());
        System.out.println(actualCustomer.getNombres());
        System.out.println(actualCustomer.getApellidos());
    }
}
