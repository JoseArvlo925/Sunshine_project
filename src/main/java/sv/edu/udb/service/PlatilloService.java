package sv.edu.udb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.repository.PlatilloRepository;
import sv.edu.udb.repository.domain.Platillo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatilloService {
    final private PlatilloRepository repository;

    public List<Platillo> findAll(){
        return repository.findAll();
    }
}
