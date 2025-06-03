package com.reservas.app.controller.profesor;

import com.reservas.app.entity.Reserva;
import com.reservas.app.entity.Usuario;
import com.reservas.app.entity.Espacio;
import com.reservas.app.repository.ReservaRepository;
import com.reservas.app.repository.EspacioRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    private EspacioRepository espacioRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    // Vista principal
    @GetMapping
    public String dashboard() {
        return "profesor/dashboard";
    }

    // Mostrar formulario para nueva reserva
    @GetMapping("/nueva-reserva")
    public String nuevaReservaForm(Model model) {
        List<Espacio> espacios = espacioRepository.findAll();
        model.addAttribute("espacios", espacios);
        model.addAttribute("reserva", new Reserva());
        return "profesor/reservas/nueva_reserva";
    }

    // Guardar reserva
    @PostMapping("/guardar-reserva")
    public String guardarReserva(@ModelAttribute Reserva reserva, HttpSession session) {
        Usuario profesor = (Usuario) session.getAttribute("usuarioLogueado");
        reserva.setUsuario(profesor);
        reserva.setEstado("PENDIENTE");
        reservaRepository.save(reserva);
        return "redirect:/profesor/mis-reservas";
    }

    // Ver reservas del profesor
    @GetMapping("/mis-reservas")
    public String verMisReservas(Model model, HttpSession session) {
        Usuario profesor = (Usuario) session.getAttribute("usuarioLogueado");
        List<Reserva> reservas = reservaRepository.findByUsuarioId(profesor.getId());
        model.addAttribute("reservas", reservas);
        return "profesor/reservas/mis_reservas";
    }

    // Cancelar si está pendiente
    @GetMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id, HttpSession session) {
        Usuario profesor = (Usuario) session.getAttribute("usuarioLogueado");
        Reserva reserva = reservaRepository.findById(id).orElse(null);

        if (reserva != null && reserva.getUsuario().getId().equals(profesor.getId())) {
            if (reserva.getEstado().equalsIgnoreCase("PENDIENTE")) {
                reservaRepository.delete(reserva);
            }
        }

        return "redirect:/profesor/mis-reservas";
    }

    // Historial
    @GetMapping("/historial")
    public String verHistorial(Model model, HttpSession session) {
        Usuario profesor = (Usuario) session.getAttribute("usuarioLogueado");
        List<Reserva> historial = reservaRepository.findByUsuarioId(profesor.getId());
        model.addAttribute("historial", historial);
        return "profesor/reservas/historial";
    }
}
