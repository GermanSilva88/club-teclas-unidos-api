package com.teclas.backend.repository;

import com.teclas.backend.model.Socio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Socio.
 * Provee métodos CRUD y consultas sobre la tabla de socios.
 */
@Repository
public interface SocioRepository extends JpaRepository<Socio, Long> {

    // Verifica si existe un socio por su DNI (útil antes de eliminar o registrar)
    boolean existsByDni(String dni);

    // Buscar un socio por su DNI (recomendado usar Optional)
    Optional<Socio> findByDni(String dni);

    // Filtros por apellido, edad o ambos
    List<Socio> findByApellido(String apellido);

    List<Socio> findByEdad(Integer edad);

    List<Socio> findByApellidoAndEdad(String apellido, Integer edad);
}
