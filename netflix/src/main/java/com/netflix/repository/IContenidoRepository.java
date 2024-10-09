package com.netflix.repository;

import com.netflix.entity.Contenido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContenidoRepository extends JpaRepository<Contenido,Integer> {

    List<Contenido> findByTituloContainingAndGeneroContaining(String titulo, String genero);
}
