package sv.edu.udb.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sv.edu.udb.repository.domain.Mensaje;
import sv.edu.udb.repository.domain.Menu;
import sv.edu.udb.repository.domain.Platillo;
import sv.edu.udb.service.MenuService;
import sv.edu.udb.service.PlatilloService;
import sv.edu.udb.util.UploadFileService;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/administrador")
public class AdministradorController {
    final private PlatilloService service;
    final private MenuService menuService;
    final private UploadFileService fileService;
    private Logger logger = LoggerFactory.getLogger(AdministradorController.class);

    @GetMapping("")
    public String listarPlatillos(Model model, HttpSession session){
        model.addAttribute("platillos", service.findAll());
        model.addAttribute("menu", menuService.findAll());
        model.addAttribute("cliente", session.getAttribute("cliente"));
        return "administrador/home";
    }

    @GetMapping("/nuevo")
    public String nuevoPlatillo(Model model, HttpSession session){
        model.addAttribute("menu", menuService.findAll());
        model.addAttribute("cliente", session.getAttribute("cliente"));
        return "/administrador/nuevo";
    }

    @PostMapping("/modificar/{id}")
    public String modificarPlatillo(@PathVariable(name = "id") Integer id, Platillo platillo,
                                    @RequestParam(name = "file") MultipartFile file,
                                    @RequestParam(name = "tipo") int idMenu,
                                    RedirectAttributes attributes) throws IOException{
        Platillo temp = service.findById(id);
        Menu menu = menuService.findById(idMenu);

        if(!file.isEmpty()){
            String nombreImagen = fileService.saveImage(file);
            if(temp.getImagen().equals("default.jpg"))
                fileService.deleteImage(temp.getImagen());

            temp.setImagen(nombreImagen);
        }

        temp.setNombre(platillo.getNombre());
        temp.setPrecio(platillo.getPrecio());
        temp.setIdTipo(menu);
        service.modificar(temp);
        attributes.addFlashAttribute("mensaje", new Mensaje("success","Platillo modificado"));
        return "redirect:/administrador";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPlatillo(@PathVariable(name = "id") Integer id){
        Platillo platillo = service.findById(id);
        if(!platillo.getImagen().equals("default.jpg"))
            fileService.deleteImage(platillo.getImagen());

        service.delete(platillo);
        return "redirect:/administrador";
    }

    @PostMapping("/registrar")
    public String registrarPlatillo(Platillo platillo, @RequestParam(name = "file")MultipartFile file,
                                    @RequestParam(name = "tipo") int idMenu,
                                    RedirectAttributes attributes) throws IOException {
        Menu menu = Menu.builder()
                .id(idMenu)
                .build();
        String nombreImagen = fileService.saveImage(file);

        platillo.setImagen(nombreImagen);
        platillo.setIdTipo(menu);
        logger.info("Platillo: {}", platillo.toString());

        service.save(platillo);
        attributes.addFlashAttribute("mensaje", new Mensaje("success","Platillo registrado"));
        return "redirect:/administrador";
    }
}
