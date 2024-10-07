package com.netflix.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Serie extends Contenido{

    private int cantidadEpisodios;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episodio> episodios;
}
