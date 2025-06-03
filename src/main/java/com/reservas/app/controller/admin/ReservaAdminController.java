package com.reservas.app.controller.admin;

import com.reservas.app.entity.Reserva;
import com.reservas.app.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/reservas")
public class ReservaAdminController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public String listarReservas(Model model) {
        List<Reserva> reservas = reservaRepository.findAll();
        model.addAttribute("reservas", reservas);
        return "admin/reservas/listar";
    }
}
