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

    public Platillo findById(Integer id){
        return repository.findById(id);
    }

    public void save(Platillo platillo){
        repository.save(platillo);
    }

    public void delete(Platillo platillo){
        repository.delete(platillo);
    }

    public void modificar(Platillo platillo){
        repository.modificar(platillo);
    }

    public List<Platillo> desayunos(){
        return repository.desayunos();
    }

    public List<Platillo> almuerzos(){
        return repository.almuerzos();
    }

    public List<Platillo> cenas(){
        return repository.cenas();
    }
}
