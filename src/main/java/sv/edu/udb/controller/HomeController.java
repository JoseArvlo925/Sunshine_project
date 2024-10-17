package sv.edu.udb.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sv.edu.udb.repository.domain.*;
import sv.edu.udb.service.ClienteService;
import sv.edu.udb.service.DetalleOrdenService;
import sv.edu.udb.service.OrdenService;
import sv.edu.udb.service.PlatilloService;
import sv.edu.udb.util.Carrito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@SessionAttributes("carrito")
@RequestMapping("/")
public class HomeController {
    final private PlatilloService service;
    final private ClienteService clienteService;
    final private OrdenService ordenService;
    final private DetalleOrdenService detalleService;
    final private Carrito carrito;
    private Logger logger = LoggerFactory.getLogger(HomeController.class);

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

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        Cliente cliente = new Cliente();
        model.addAttribute("user", cliente);
        return "usuario/registro";
    }

    @PostMapping("/registrar")
    public String registrar(Cliente cliente, Model model){
        int exist = clienteService.emailExists(cliente.getCorreo()).intValue();
        model.addAttribute("user", cliente);
        if (model.containsAttribute("cliente"))
            model.addAttribute("cliente", null);

        if(exist == 0) {
            cliente.setTipo("USER");
            int res = clienteService.saveCliente(cliente);
            if (res != 0) {
                model.addAttribute("mensaje", new Mensaje("success", "Registro existoso"));
                return "/usuario/login";
            } else {
                model.addAttribute("mensaje", new Mensaje("error", "Fallo al registrar"));
                return "/usuario/registro";
            }
        }else{
            model.addAttribute("mensaje", new Mensaje("error", "Email ya existe"));
            return "/usuario/registro";
        }
    }

    @GetMapping("/carrito")
    public String carrito(Model model, HttpSession session){
        Integer id = (Integer) session.getAttribute("cliente");
        List<DetalleOrden> detalles = carrito.obtenerSesion(model);

        Double total = 0.0;
        for(DetalleOrden detalle : detalles){
            detalle.setPrecio(detalle.getIdPlat().getPrecio());
            total += detalle.importe();
        }

        model.addAttribute("cart", detalles);
        model.addAttribute("total", total);
        if(id != null) {
            Cliente cliente = clienteService.findById(id);
            logger.info("Cliente recibido: {}", cliente.getNombres());
            model.addAttribute("cliente", session.getAttribute("cliente"));
            if(!detalles.isEmpty()) {
                model.addAttribute("active", true);
            }else{
                model.addAttribute("active", false);
            }
        }else{
            model.addAttribute("active", false);
        }


        return"/usuario/orden";
    }

    @GetMapping("/carrito/remove/{id}")
    public String borrar(@PathVariable(name = "id") Integer id,
            Model model){
        List<DetalleOrden> detalles = carrito.obtenerSesion(model);
        int indice = 0;
        for(DetalleOrden detalle : detalles){
            if(Objects.equals(id, detalle.getIdPlat().getId()))
                indice = detalles.indexOf(detalle);
        }

        detalles.remove(indice);
        logger.info("indice obtenido: {}", indice);
        model.addAttribute("cart", detalles);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/guardar")
    public String guardarCarrito(Model model, HttpSession session, RedirectAttributes attributes){
        List<DetalleOrden> detalles = carrito.obtenerSesion(model);
        Cliente cliente = clienteService.findById((int)session.getAttribute("cliente"));
        Double total = 0.0;
        for(DetalleOrden detalle : detalles){
            detalle.setPrecio(detalle.getIdPlat().getPrecio());
            total += detalle.importe();
        }

        Orden orden = Orden.builder()
                .idCli(cliente)
                .estado("Pendiente")
                .fechaPed(LocalDate.now())
                .detalleList(detalles)
                .total(BigDecimal.valueOf(total))
                .build();

        ordenService.saveOrden(orden);

        Orden last = ordenService.lastOrder();
        detalleService.saveDetalle(detalles, last);
        detalles.clear();
        attributes.addFlashAttribute("mensaje", new Mensaje("success", "Orden recibida"));
        return "redirect:/";
    }

    @GetMapping("/detalles")
    public String listarDetalles(Model model, HttpSession session){
        Integer idCliente = (Integer) session.getAttribute("cliente");
        Cliente cliente = clienteService.findById(idCliente);
        //List<DetalleOrden> detalles = carrito.obtenerSesion(model); -->Pruebas
        List<Orden> ordenes = ordenService.findByCustomer(cliente);


        model.addAttribute("cliente", idCliente);
        model.addAttribute("ordenes", ordenes);
        return "/usuario/detalles";
    }
}
