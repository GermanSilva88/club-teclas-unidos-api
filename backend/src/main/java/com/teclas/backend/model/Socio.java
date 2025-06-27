package com.teclas.backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Schema(description = "Entidad que representa a un socio del club")
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del socio", example = "1")
    private Long id;

    @Column(nullable = false)
    @Schema(description = "DNI del socio", example = "33445566")
    private String dni;

    @Column(nullable = false)
    @Schema(description = "Apellido del socio", example = "Ramirez")
    private String apellido;

    @Column(nullable = false)
    @Schema(description = "Nombre del socio", example = "Carla")
    private String nombre;

    @Schema(description = "Edad del socio", example = "30")
    private int edad;

    @Schema(description = "Fecha de nacimiento del socio", example = "1994-04-22")
    private LocalDate fechaNacimiento;

    @Schema(description = "Dirección del socio", example = "Calle Siempre Viva 123")
    private String direccion;

    @Schema(description = "Teléfono de contacto del socio", example = "123456789")
    private String telefono;

    public Socio() {
    }

    public Socio(String dni, String apellido, String nombre, int edad,
                 LocalDate fechaNacimiento, String direccion, String telefono) {
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    // Setters
    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
