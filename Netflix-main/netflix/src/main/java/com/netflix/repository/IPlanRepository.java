package com.netflix.repository;

import com.netflix.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlanRepository extends JpaRepository<Plan,Integer> {
}
