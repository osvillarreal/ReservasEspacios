package com.reservas.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.reservas.app.entity.Usuario;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCorreo(String correo);    
    List<Usuario> findByRolIgnoreCase(String rol);
}
