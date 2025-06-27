package com.teclas.backend.controller;

import com.teclas.backend.model.Socio;
import com.teclas.backend.repository.SocioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public ResponseEntity<Socio> registrar(@RequestBody Socio socio) {
        Socio guardado = socioRepository.save(socio);
        return ResponseEntity.ok(guardado);
    }

    @Operation(summary = "Listar todos los socios")
    @GetMapping
    public ResponseEntity<List<Socio>> listarTodos() {
        List<Socio> socios = socioRepository.findAll();
        return ResponseEntity.ok(socios);
    }

    @Operation(summary = "Buscar un socio por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Socio> obtenerPorId(@PathVariable Long id) {
        Optional<Socio> socio = socioRepository.findById(id);
        return socio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar un socio por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (socioRepository.existsById(id)) {
            socioRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Actualizar datos de un socio")
    @PutMapping("/{id}")
    public ResponseEntity<Socio> actualizar(@PathVariable Long id, @RequestBody Socio socioActualizado) {
        return socioRepository.findById(id).map(socioExistente -> {
            socioExistente.setNombre(socioActualizado.getNombre());
            socioExistente.setApellido(socioActualizado.getApellido());
            socioExistente.setDni(socioActualizado.getDni());
            socioExistente.setEdad(socioActualizado.getEdad());
            socioExistente.setFechaNacimiento(socioActualizado.getFechaNacimiento());
            socioExistente.setDireccion(socioActualizado.getDireccion());
            socioExistente.setTelefono(socioActualizado.getTelefono());
            Socio actualizado = socioRepository.save(socioExistente);
            return ResponseEntity.ok(actualizado);
        }).orElse(ResponseEntity.notFound().build());
    }
}
