package com.netflix.repository;

import com.netflix.entity.Episodio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEpisodioRepository extends JpaRepository<Episodio,Integer> {
}
