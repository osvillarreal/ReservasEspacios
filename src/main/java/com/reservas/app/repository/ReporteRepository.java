package com.reservas.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.reservas.app.entity.Reporte;

public interface ReporteRepository extends JpaRepository<Reporte, Long> {
}