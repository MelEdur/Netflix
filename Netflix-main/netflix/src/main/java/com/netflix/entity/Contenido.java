package com.netflix.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public abstract class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contenido")
    private int idContenido;

    private String titulo;

    @ElementCollection
    @CollectionTable(name = "contenido_alias", joinColumns = @JoinColumn(name = "id_contenido"))
    private List<String> alias;

    private String descripcion;

    private String genero;

    private String imagen;

    private String avance;

    private int popularidad;

    private boolean atp;
}
