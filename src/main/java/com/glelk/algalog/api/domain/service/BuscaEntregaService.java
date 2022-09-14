package com.glelk.algalog.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glelk.algalog.api.domain.exception.EntidadeNaoEncontradaException;
import com.glelk.algalog.api.domain.model.Entrega;
import com.glelk.algalog.api.domain.repository.EntregaRepository;

@Service
public class BuscaEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrga não encontrada"));
    }

}
