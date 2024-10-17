package sv.edu.udb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.repository.MenuRepository;
import sv.edu.udb.repository.domain.Menu;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    final private MenuRepository repository;

    public List<Menu> findAll(){
        return repository.findAll();
    }

    public Menu findById(int id){
        return repository.findById(id);
    }
}
