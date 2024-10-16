package com.netflix.repository;

import com.netflix.entity.Contenido;
import com.netflix.entity.Serie;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContenidoRepository extends JpaRepository<Contenido,Integer> {

    List<Contenido> findByTituloContainingAndGeneroContaining(String titulo, String genero, Sort sort);

    Serie findByEpisodiosIdEpisodio(int idEpisodio);
}
