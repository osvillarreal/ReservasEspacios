package com.reservas.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.reservas.app.entity.Espacio;

public interface EspacioRepository extends JpaRepository<Espacio, Long> {
    List<Espacio> findByTipoIgnoreCase(String tipo);
}