package com.reservas.app.controller.admin;

import com.reservas.app.entity.Usuario;
import com.reservas.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioAdminController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar estudiantes
    @GetMapping("/estudiantes")
    public String listarEstudiantes(Model model) {
        List<Usuario> estudiantes = usuarioRepository.findByRolIgnoreCase("ESTUDIANTE");
        model.addAttribute("usuarios", estudiantes);
        model.addAttribute("rol", "Estudiante");
        return "admin/usuarios/estudiantes/listar";
    }

    // Mostrar formulario para crear estudiante
    @GetMapping("/estudiantes/crear")
    public String mostrarFormularioCrearEstudiante(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("rol", "ESTUDIANTE");
        return "admin/usuarios/estudiantes/crear";
    }

    // Guardar estudiante
    @PostMapping("/estudiantes/guardar")
    public String guardarEstudiante(@ModelAttribute Usuario usuario) {
        usuario.setRol("ESTUDIANTE");
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios/estudiantes";
    }

    // Editar estudiante
    @GetMapping("/estudiantes/editar/{id}")
    public String editarEstudiante(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "admin/usuarios/estudiantes/editar";
    }

    @PostMapping("/estudiantes/actualizar")
    public String actualizarEstudiante(@ModelAttribute Usuario usuario) {
        usuario.setRol("ESTUDIANTE");
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios/estudiantes";
    }

    // Eliminar estudiante
    @GetMapping("/estudiantes/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/admin/usuarios/estudiantes";
    }
    
    // Listar profesores
    @GetMapping("/profesores")
    public String listarProfesores(Model model) {
        List<Usuario> profesores = usuarioRepository.findByRolIgnoreCase("PROFESOR");
        model.addAttribute("usuarios", profesores);
        model.addAttribute("rol", "Profesor");
        return "admin/usuarios/profesores/listar";
    }

    // Crear profesor
    @GetMapping("/profesores/crear")
    public String mostrarFormularioCrearProfesor(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("rol", "PROFESOR");
        return "admin/usuarios/profesores/crear";
    }

    @PostMapping("/profesores/guardar")
    public String guardarProfesor(@ModelAttribute Usuario usuario) {
        usuario.setRol("PROFESOR");
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios/profesores";
    }

    // Editar profesor
    @GetMapping("/profesores/editar/{id}")
    public String editarProfesor(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "admin/usuarios/profesores/editar";
    }

    @PostMapping("/profesores/actualizar")
    public String actualizarProfesor(@ModelAttribute Usuario usuario) {
        usuario.setRol("PROFESOR");
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios/profesores";
    }

    // Eliminar profesor
    @GetMapping("/profesores/eliminar/{id}")
    public String eliminarProfesor(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/admin/usuarios/profesores";
    }

 // Listar coordinadores
    @GetMapping("/coordinadores")
    public String listarCoordinadores(Model model) {
        List<Usuario> coordinadores = usuarioRepository.findByRolIgnoreCase("COORDINADOR");
        model.addAttribute("usuarios", coordinadores);
        model.addAttribute("rol", "Coordinador");
        return "admin/usuarios/coordinadores/listar";
    }

    // Crear coordinador
    @GetMapping("/coordinadores/crear")
    public String mostrarFormularioCrearCoordinador(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("rol", "COORDINADOR");
        return "admin/usuarios/coordinadores/crear";
    }

    @PostMapping("/coordinadores/guardar")
    public String guardarCoordinador(@ModelAttribute Usuario usuario) {
        usuario.setRol("COORDINADOR");
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios/coordinadores";
    }

    // Editar coordinador
    @GetMapping("/coordinadores/editar/{id}")
    public String editarCoordinador(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "admin/usuarios/coordinadores/editar";
    }

    @PostMapping("/coordinadores/actualizar")
    public String actualizarCoordinador(@ModelAttribute Usuario usuario) {
        usuario.setRol("COORDINADOR");
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios/coordinadores";
    }

    // Eliminar coordinador
    @GetMapping("/coordinadores/eliminar/{id}")
    public String eliminarCoordinador(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/admin/usuarios/coordinadores";
    }
    
 // Listar personal de logística
    @GetMapping("/logistica")
    public String listarLogistica(Model model) {
        List<Usuario> logistica = usuarioRepository.findByRolIgnoreCase("LOGISTICA");
        model.addAttribute("usuarios", logistica);
        model.addAttribute("rol", "Logística");
        return "admin/usuarios/logistica/listar";
    }

    // Crear usuario de logística
    @GetMapping("/logistica/crear")
    public String mostrarFormularioCrearLogistica(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("rol", "LOGISTICA");
        return "admin/usuarios/logistica/crear";
    }

    @PostMapping("/logistica/guardar")
    public String guardarLogistica(@ModelAttribute Usuario usuario) {
        usuario.setRol("LOGISTICA");
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios/logistica";
    }

    // Editar usuario de logística
    @GetMapping("/logistica/editar/{id}")
    public String editarLogistica(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "admin/usuarios/logistica/editar";
    }

    @PostMapping("/logistica/actualizar")
    public String actualizarLogistica(@ModelAttribute Usuario usuario) {
        usuario.setRol("LOGISTICA");
        usuarioRepository.save(usuario);
        return "redirect:/admin/usuarios/logistica";
    }

    // Eliminar usuario de logística
    @GetMapping("/logistica/eliminar/{id}")
    public String eliminarLogistica(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
        return "redirect:/admin/usuarios/logistica";
    }

}
