package com.reservas.app.controller.logistica;

import com.reservas.app.entity.Reserva;
import com.reservas.app.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/logistica")
public class LogisticaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public String dashboard() {
        return "logistica/dashboard";
    }

    @GetMapping("/reservas-aprobadas")
    public String verReservasAprobadas(Model model) {
        List<Reserva> reservasAprobadas = reservaRepository.findByEstado("APROBADA");
        model.addAttribute("reservas", reservasAprobadas);
        return "logistica/reservas/listar_reservas_aprobadas";
    }


}
