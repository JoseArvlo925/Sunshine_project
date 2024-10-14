package sv.edu.udb.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.repository.domain.Cliente;
import sv.edu.udb.repository.domain.DetalleOrden;
import sv.edu.udb.repository.domain.Platillo;
import sv.edu.udb.service.PlatilloService;
import sv.edu.udb.util.Carrito;

@Controller
@RequiredArgsConstructor
@SessionAttributes("carrito")
@RequestMapping("/")
public class HomeController {
    final private PlatilloService service;
    final private Carrito carrito;

    @GetMapping("")
    public String listarPlatillos(Model model, HttpSession session){
        model.addAttribute("platillos", service.findAll());
        model.addAttribute("cliente", session.getAttribute("cliente"));
        return "usuario/index";
    }

    @GetMapping("agregar/{id}")
    public String agregarCarrito(@PathVariable(name = "id") Integer id, Model model){
        Platillo platillo = service.findById(id);

        if(platillo != null){
            DetalleOrden orden = new DetalleOrden();
            orden.setIdPlat(platillo);
            orden.setCantidad(1);

            carrito.agregarCarrito(orden, model);
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String formLogin(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute("user", cliente);
        return "/usuario/login";
    }
}