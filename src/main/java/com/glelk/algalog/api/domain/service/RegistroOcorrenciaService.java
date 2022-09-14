package com.glelk.algalog.api.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glelk.algalog.api.domain.exception.NegocioException;
import com.glelk.algalog.api.domain.model.Entrega;
import com.glelk.algalog.api.domain.model.Ocorrencia;
import com.glelk.algalog.api.domain.repository.EntregaRepository;

@Service
public class RegistroOcorrenciaService {

    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }
}
