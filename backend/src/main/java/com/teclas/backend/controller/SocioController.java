package com.teclas.backend.controller;

import com.teclas.backend.model.Socio;
import com.teclas.backend.repository.SocioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/socios")
@CrossOrigin(origins = "*")
@Tag(name = "Socios", description = "Operaciones relacionadas con los socios del club")
public class SocioController {

    @Autowired
    private SocioRepository socioRepository;

    @Operation(summary = "Registrar un nuevo socio")
    @PostMapping
    public ResponseEntity<Socio> registrar(@Valid @RequestBody Socio socio) {
        Socio guardado = socioRepository.save(socio);
        return ResponseEntity.ok(guardado);
    }

    @GetMapping
    @Operation(summary = "Listar socios con filtros por apellido y edad")
    public ResponseEntity<List<Socio>> listarFiltrados(
            @RequestParam(required = false) String apellido,
            @RequestParam(required = false) Integer edad) {

        List<Socio> socios;

        if (apellido != null && edad != null) {
            socios = socioRepository.findByApellidoAndEdad(apellido, edad);
        } else if (apellido != null) {
            socios = socioRepository.findByApellido(apellido);
        } else if (edad != null) {
            socios = socioRepository.findByEdad(edad);
        } else {
            socios = socioRepository.findAll();
        }

        return ResponseEntity.ok(socios);
    }


    @Operation(summary = "Buscar un socio por DNI")
    @GetMapping("/{dni}")
    public ResponseEntity<Socio> obtenerPorDni(@PathVariable String dni) {
        Optional<Socio> socio = socioRepository.findByDni(dni);
        return socio.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un socio por DNI")
    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminar(@PathVariable String dni) {
        Optional<Socio> socio = socioRepository.findByDni(dni);
        if (socio.isPresent()) {
            socioRepository.delete(socio.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @Operation(summary = "Actualizar datos de un socio por DNI")
    @PutMapping("/{dni}")
    public ResponseEntity<Socio> actualizar(@PathVariable String dni, @Valid @RequestBody Socio socioActualizado) {
        return socioRepository.findByDni(dni).map(socio -> {
            socio.setNombre(socioActualizado.getNombre());
            socio.setApellido(socioActualizado.getApellido());
            socio.setEdad(socioActualizado.getEdad());
            socio.setFechaNacimiento(socioActualizado.getFechaNacimiento());
            socio.setDireccion(socioActualizado.getDireccion());
            socio.setTelefono(socioActualizado.getTelefono());
            return ResponseEntity.ok(socioRepository.save(socio));
        }).orElse(ResponseEntity.notFound().build());
    }

}
