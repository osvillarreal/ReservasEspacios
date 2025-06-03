package com.reservas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.reservas.app.entity.Reserva;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    // Todas las reservas de un usuario
    List<Reserva> findByUsuarioId(Long usuarioId);

    // Reservas filtradas por estado
    List<Reserva> findByEstadoIgnoreCase(String estado);

    // Reservas de un usuario con estado específico (opcional para historial más limpio)
    List<Reserva> findByUsuarioIdAndEstadoIgnoreCase(Long usuarioId, String estado);

	List<Reserva> findByEstado(String estado);
    
}