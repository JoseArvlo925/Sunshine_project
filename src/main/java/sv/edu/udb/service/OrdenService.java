package sv.edu.udb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.repository.OrdenRepository;
import sv.edu.udb.repository.domain.Cliente;
import sv.edu.udb.repository.domain.Orden;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenService {
    final private OrdenRepository ordenRepository;

    public List<Orden> findByCustomer(Cliente cliente){
        return ordenRepository.findByCustomer(cliente);
    }

    public int saveOrden(Orden orden){
        return ordenRepository.saveOrden(orden);
    }

    public Orden lastOrder(){
        return ordenRepository.findLastOrder();
    }
}
