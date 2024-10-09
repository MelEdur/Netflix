package com.netflix.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Progreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progreso")
    private int idProgreso;

    @ManyToOne
    @JoinColumn(name = "id_contenido")
    private Contenido contenido;

    private int episodio;

    private int segundo;
}
