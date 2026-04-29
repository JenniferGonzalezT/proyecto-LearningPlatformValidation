package com.duoc.LearningPlatformValidation.model;

import java.time.LocalDateTime;

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
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EVALUACIONES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evaluacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_evaluaciones")
    @SequenceGenerator(name = "seq_evaluaciones", sequenceName = "SEQ_EVALUACIONES", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "La evaluación debe estar vinculada a un curso")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURSO_ID", nullable = false)
    private Curso curso;

    @NotBlank(message = "El nombre de la evaluación no puede estar vacío")
    @Size(min = 2, max = 150, message = "El nombre debe tener entre 2 y 150 caracteres")
    @Column(name = "NOMBRE", nullable = false, length = 150)
    private String nombre;

    @Min(value = 1, message = "El puntaje máximo debe ser al menos 1")
    @Max(value = 100, message = "El puntaje máximo no puede exceder de 100")
    @Column(name = "PUNTAJE_MAXIMO", nullable = false)
    private int puntajeMaximo;

    @NotNull(message = "La fecha de la evaluación es obligatoria")
    @Future(message = "La fecha de la evaluación debe ser en el futuro")
    @Column(name = "FECHA_APLICACION", nullable = false)
    private LocalDateTime fechaAplicacion;
}
