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
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "INSCRIPCIONES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscripcion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_inscripciones")
    @SequenceGenerator(name = "seq_inscripciones", sequenceName = "SEQ_INSCRIPCIONES", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "La inscripción debe estar vinculada a un curso")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CURSO_ID", nullable = false) 
    private Curso curso;

    @NotNull(message = "La inscripción debe estar vinculada a un estudiante")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ESTUDIANTE_ID", nullable = false) 
    private Usuario estudiante;

    @Column(name = "FECHA_INSCRIPCION", nullable = false, updatable = false)
    private LocalDateTime fechaInscripcion;

    // Asignar la fecha automáticamente antes de guardar en la BD
    @PrePersist
    protected void antesDeGuardar() {
        if (this.fechaInscripcion == null) {
            this.fechaInscripcion = LocalDateTime.now();
        }
    }
}
