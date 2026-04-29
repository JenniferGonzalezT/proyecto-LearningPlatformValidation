package com.duoc.LearningPlatformValidation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USUARIOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuarios")
    @SequenceGenerator(name = "seq_usuarios", sequenceName = "SEQ_USUARIOS", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres")
    @Column(name = "NOMBRE", nullable = false, length = 150)
    private String nombre;

    @NotBlank(message = "El correo no puede estar vacío")
    @Email(message = "Debe proporcionar un formato de correo válido")
    @Size(max = 150, message = "El correo debe tener máximo 150 caracteres")
    @Column(name = "CORREO", nullable = false, unique = true, length = 150)
    private String correo;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 8, max = 255, message = "La contraseña debe tener al menos 8 caracteres")
    @Column(name = "CONTRASENIA", nullable = false, length = 255)
    private String contrasenia;

    @NotBlank(message = "El rol no puede estar vacío")
    @Pattern(regexp = "^(estudiante|profesor)$", message = "El rol debe ser 'estudiante' o 'profesor'")
    @Column(name = "ROL", nullable = false, length = 20)
    private String rol;
}
