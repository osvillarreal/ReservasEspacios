package com.reservas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.reservas.app.entity.Calendario;

public interface CalendarioRepository extends JpaRepository<Calendario, Long> {
}