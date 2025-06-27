package com.teclas.backend.repository;

import com.teclas.backend.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para la entidad Socio.
 * Provee métodos CRUD y consultas sobre la tabla de socios.
 */
@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

    // Acá podés agregar métodos de búsqueda personalizados si necesitás
    boolean existsByDni(String dni);

    Socio findByDni(String dni);
}
