package com.reservas.app.controller.coordinador;

import com.reservas.app.entity.Reserva;
import com.reservas.app.repository.ReservaRepository;
import com.reservas.app.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/coordinador")
public class CoordinadorController {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Vista principal del coordinador
    @GetMapping
    public String dashboard() {
        return "coordinador/dashboard";
    }

    // Ver solicitudes pendientes de reserva
    @GetMapping("/solicitudes")
    public String verSolicitudesPendientes(Model model) {
        List<Reserva> solicitudes = reservaRepository.findByEstadoIgnoreCase("PENDIENTE");
        model.addAttribute("solicitudes", solicitudes);
        return "coordinador/reservas/listar_solicitudes";
    }

    // Aprobar solicitud
    @GetMapping("/solicitud/aprobar/{id}")
    public String aprobarReserva(@PathVariable Long id) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        if (reserva != null) {
            reserva.setEstado("APROBADA");
            reservaRepository.save(reserva);
        }
        return "redirect:/coordinador/solicitudes";
    }

    // Rechazar solicitud
    @GetMapping("/solicitud/rechazar/{id}")
    public String rechazarReserva(@PathVariable Long id) {
        Reserva reserva = reservaRepository.findById(id).orElse(null);
        if (reserva != null) {
            reserva.setEstado("RECHAZADA");
            reservaRepository.save(reserva);
        }
        return "redirect:/coordinador/solicitudes";
    }

    @GetMapping("/reservas-aprobadas")
    public String verReservasAprobadas(Model model) {
        List<Reserva> reservasAprobadas = reservaRepository.findByEstadoIgnoreCase("APROBADA");
        model.addAttribute("reservas", reservasAprobadas);
        return "coordinador/reservas/listar_reservas_aprobadas";
    }

    // Ver contacto del usuario
    @GetMapping("/usuario/{id}")
    public String verContactoUsuario(@PathVariable Long id, Model model) {
        model.addAttribute("usuario", usuarioRepository.findById(id).orElse(null));
        return "coordinador/usuarios/ver_usuario";
    }
}
