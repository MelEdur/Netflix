package com.netflix.service;

import com.netflix.entity.Plan;
import com.netflix.repository.IPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final IPlanRepository planRepository;

    public List<Plan> traerPlanes(){
        return planRepository.findAll();
    }

    public Plan traerPorId(int id){
        return planRepository.findById(id).get();
    }
}
