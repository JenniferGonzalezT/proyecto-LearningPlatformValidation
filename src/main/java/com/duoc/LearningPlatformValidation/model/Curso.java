package com.duoc.LearningPlatformValidation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CURSOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cursos")
    @SequenceGenerator(name = "seq_cursos", sequenceName = "SEQ_CURSOS", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "El nombre del curso no puede estar vacío")
    @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres")
    @Column(name = "NOMBRE", nullable = false, length = 150)
    private String nombre;

    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres")
    @Column(name = "DESCRIPCION", length = 500)
    private String descripcion;

    @NotNull(message = "El curso debe tener un profesor asignado")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFESOR_ID", nullable = false) 
    private Usuario profesor;
}
