package com.reservas.app.controller.admin;

import com.reservas.app.entity.Espacio;
import com.reservas.app.repository.EspacioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/espacios")
public class EspacioAdminController {

    @Autowired
    private EspacioRepository espacioRepository;

    @GetMapping
    public String listarEspacios(Model model) {
        model.addAttribute("espacios", espacioRepository.findAll());
        return "admin/espacios/listar";
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("espacio", new Espacio());
        return "admin/espacios/crear";
    }

    @PostMapping("/guardar")
    public String guardarEspacio(@ModelAttribute Espacio espacio) {
        espacioRepository.save(espacio);
        return "redirect:/admin/espacios";
    }

    @GetMapping("/editar/{id}")
    public String editarEspacio(@PathVariable Long id, Model model) {
        Espacio espacio = espacioRepository.findById(id).orElse(null);
        model.addAttribute("espacio", espacio);
        return "admin/espacios/editar";
    }

    @PostMapping("/actualizar")
    public String actualizarEspacio(@ModelAttribute Espacio espacio) {
        espacioRepository.save(espacio);
        return "redirect:/admin/espacios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEspacio(@PathVariable Long id) {
        espacioRepository.deleteById(id);
        return "redirect:/admin/espacios";
    }
}
