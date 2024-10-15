package sv.edu.udb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.repository.DetalleOrdenRepository;
import sv.edu.udb.repository.domain.DetalleOrden;
import sv.edu.udb.repository.domain.Orden;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleOrdenService {
    final private DetalleOrdenRepository repository;

    public void saveDetalle(List<DetalleOrden> detalles, Orden orden){
        repository.saveDetalle(detalles, orden);
    }
}
