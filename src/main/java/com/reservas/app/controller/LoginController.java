package com.reservas.app.controller;

import com.reservas.app.entity.Usuario;
import com.reservas.app.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Página de inicio: formulario de login
    @GetMapping("/")
    public String mostrarFormularioLogin(Model model) {
        model.addAttribute("usuariosAdmin", usuarioRepository.findByRolIgnoreCase("ADMINISTRADOR"));
        model.addAttribute("usuariosCoord", usuarioRepository.findByRolIgnoreCase("COORDINADOR"));
        model.addAttribute("usuariosEst", usuarioRepository.findByRolIgnoreCase("ESTUDIANTE"));
        model.addAttribute("usuariosProf", usuarioRepository.findByRolIgnoreCase("PROFESOR"));
        model.addAttribute("usuariosLog", usuarioRepository.findByRolIgnoreCase("LOGISTICA"));
        return "login";
    }

    // Procesar login
    @PostMapping("/login")
    public String login(@RequestParam String correo,
                        @RequestParam String contrasena,
                        HttpSession session,
                        Model model) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            session.setAttribute("usuarioLogueado", usuario);

            switch (usuario.getRol().toUpperCase()) {
                case "ADMINISTRADOR":
                    return "redirect:/admin";
                case "COORDINADOR":
                    return "redirect:/coordinador";
                case "ESTUDIANTE":
                    return "redirect:/estudiante";
                case "PROFESOR":
                    return "redirect:/profesor";
                case "LOGISTICA":
                    return "redirect:/logistica";
                default:
                    model.addAttribute("error", "Rol no válido.");
                    return "login";
            }
        } else {
            model.addAttribute("error", "Credenciales inválidas.");
            return "login";
        }
    }
    

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
