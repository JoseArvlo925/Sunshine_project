package sv.edu.udb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.repository.ClienteRepository;
import sv.edu.udb.repository.domain.Cliente;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Cliente auth(String correo, String contrasenia){
        return clienteRepository.auth(correo, contrasenia);
    }

    public int saveCliente(Cliente cliente){
        return clienteRepository.saveCliente(cliente);
    }

    public Long emailExists(String correo){
        return clienteRepository.emailExists(correo);
    }
}
