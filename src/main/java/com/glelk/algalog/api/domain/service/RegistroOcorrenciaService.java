package com.glelk.algalog.api.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glelk.algalog.api.domain.model.Entrega;
import com.glelk.algalog.api.domain.model.Ocorrencia;

@Service
public class RegistroOcorrenciaService {

    @Autowired
    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }
}
