package com.reservas.app.controller;

import com.reservas.app.entity.Usuario;
import com.reservas.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AdminInsertController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/crear-admin")
    public String crearAdmin() {
        Usuario admin = new Usuario();
        admin.setNombre("Admin");
        admin.setCorreo("admin@gmail.com");
        admin.setContrasena("1234");
        admin.setRol("ADMINISTRADOR");
        usuarioRepository.save(admin);
        return "Administrador creado exitosamente";
    }
}
