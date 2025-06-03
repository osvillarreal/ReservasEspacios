package com.reservas.app.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    // Panel principal con las tres opciones
    @GetMapping
    public String mostrarDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/usuarios")
    public String menuUsuarios() {
        return "admin/usuarios/index";
    }

}
