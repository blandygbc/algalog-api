package com.glelk.algalog.api.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glelk.algalog.api.domain.model.Cliente;
import com.glelk.algalog.api.domain.model.Entrega;
import com.glelk.algalog.api.domain.model.StatusEntrega;
import com.glelk.algalog.api.domain.repository.ClienteRepository;
import com.glelk.algalog.api.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private CatalogoClienteService catalogoClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(LocalDateTime.now());

        return entregaRepository.save(entrega);
    }
}
