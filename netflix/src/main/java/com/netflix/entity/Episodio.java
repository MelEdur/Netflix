package com.netflix.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_episodio")
    private int idEpisodio;

    private String titulo;

    private int numero;

    private String descripcion;

    private String imagen;

    private String avance;

    private String reproduccion;
}
