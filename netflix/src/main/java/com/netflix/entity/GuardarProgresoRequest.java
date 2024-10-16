package com.netflix.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class GuardarProgresoRequest {

    private int time;
    private int episodio;
    private int pelicula;
    private int serie;
    private int usuario;
}
