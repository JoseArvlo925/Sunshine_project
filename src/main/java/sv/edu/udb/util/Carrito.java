package sv.edu.udb.util;

import org.springframework.ui.Model;
import sv.edu.udb.repository.domain.DetalleOrden;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    public void agregarCarrito(DetalleOrden detalle, Model model){
        List<DetalleOrden> carrito = obtenerSesion(model);
        int pos = existePlatillo(carrito, detalle.getIdPlat().getId());

        if(pos == -1){
            carrito.add(detalle);
        }else{
            DetalleOrden obj = carrito.get(pos);
            obj.aumentarCantidad(detalle.getCantidad());
            carrito.set(pos, obj);
        }
        guardarSesion(model, carrito);
    }

    public double importeTotal(List<DetalleOrden> carrito){
        double total = 0;
        for(DetalleOrden item : carrito){
            total += item.importe();
        }
        return total;
    }

    public void removerItemCarrito(Model model, int indice){
        List<DetalleOrden> carrito = obtenerSesion(model);
        carrito.remove(indice);
        guardarSesion(model, carrito);
    }

    public List<DetalleOrden> obtenerSesion(Model model){
        List<DetalleOrden> carrito;

        if(model.getAttribute("carrito") == null){
            carrito = new ArrayList<>();
        }else{
            carrito = (List<DetalleOrden>) model.getAttribute("carrito");
        }
        return carrito;
    }

    public void guardarSesion(Model model, List<DetalleOrden> carrito){
        model.addAttribute("carrito", carrito);
    }

    public int existePlatillo(List<DetalleOrden> carrito, int idPlat){
        for(int i = 0; i < carrito.size(); i++){
            if(carrito.get(i).getIdPlat().getId() == idPlat){
                return i;
            }
        }
        return -1;
    }
}
