package sv.edu.udb.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sv.edu.udb.repository.domain.Cliente;
import sv.edu.udb.repository.domain.Mensaje;
import sv.edu.udb.service.ClienteService;


@Controller
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {
    final private ClienteService clienteService;
    private Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("/auth")
    public String validar(Cliente cliente, HttpSession session, Model model){
        String temp = cliente.getCorreo();
        cliente = clienteService.auth(cliente.getCorreo(), cliente.getContrasenia());
        if(cliente != null){
            session.setAttribute("cliente", cliente.getId());
            return "redirect:/";
        } else {
            cliente = new Cliente();
            cliente.setCorreo(temp);
            model.addAttribute("user", cliente);
            model.addAttribute("mensaje", new Mensaje("error", "Usuario no encontrado!"));
            return "usuario/login";
        }
    }

    @GetMapping("/logout")
    public String salir(HttpSession session){
        session.removeAttribute("cliente");
        return "redirect:/";
    }
}