package com.reservas.app.controller.estudiante;

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

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    private EspacioRepository espacioRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    // Dashboard del estudiante
    @GetMapping
    public String dashboard() {
        return "estudiante/dashboard";
    }

    // Mostrar formulario para nueva reserva (espacios deportivos)
    @GetMapping("/nueva-reserva")
    public String nuevaReservaForm(Model model) {
        List<Espacio> espacios = espacioRepository.findByTipoIgnoreCase("DEPORTIVO");
        model.addAttribute("espacios", espacios);
        model.addAttribute("reserva", new Reserva());
        return "estudiante/reservas/nueva_reserva";
    }

    // Guardar la solicitud de reserva
    @PostMapping("/guardar-reserva")
    public String guardarReserva(@ModelAttribute Reserva reserva, HttpSession session) {
        Usuario estudiante = (Usuario) session.getAttribute("usuarioLogueado");
        reserva.setUsuario(estudiante);
        reserva.setEstado("PENDIENTE");
        reservaRepository.save(reserva);
        return "redirect:/estudiante/mis-reservas";
    }

    // Mostrar reservas activas
    @GetMapping("/mis-reservas")
    public String verMisReservas(Model model, HttpSession session) {
        Usuario estudiante = (Usuario) session.getAttribute("usuarioLogueado");
        List<Reserva> reservas = reservaRepository.findByUsuarioId(estudiante.getId());
        model.addAttribute("reservas", reservas);
        return "estudiante/reservas/mis_reservas";
    }

    // Cancelar reserva si aún no ha pasado
    @GetMapping("/cancelar/{id}")
    public String cancelarReserva(@PathVariable Long id, HttpSession session) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        Usuario estudiante = (Usuario) session.getAttribute("usuarioLogueado");

        if (reserva != null && reserva.getUsuario().getId().equals(estudiante.getId())) {
            if (reserva.getFechaInicio().isAfter(LocalDateTime.now())) {
                reservaRepository.delete(reserva);
            }
        }

        return "redirect:/estudiante/mis-reservas";
    }

    // Ver historial personal
    @GetMapping("/historial")
    public String verHistorial(Model model, HttpSession session) {
        Usuario estudiante = (Usuario) session.getAttribute("usuarioLogueado");
        List<Reserva> historial = reservaRepository.findByUsuarioId(estudiante.getId());
        model.addAttribute("historial", historial);
        return "estudiante/reservas/historial";
    }
}
