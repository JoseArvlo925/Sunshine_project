package sv.edu.udb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sv.edu.udb.service.PlatilloService;

@Controller
@RequiredArgsConstructor
public class AppInitializer {
    final private PlatilloService service;

    @GetMapping({"/", "/index"})
    public String listarPlatillos(Model model){
        model.addAttribute("platillos", service.findAll());
        return "index";
    }
}
