package com.netflix.service;

import com.netflix.repository.IContenidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContenidoService {

    private final IContenidoRepository contenidoRepository;


}
