package com.glelk.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.glelk.algalog.api.assembler.ClienteAssembler;
import com.glelk.algalog.api.domain.model.Cliente;
import com.glelk.algalog.api.domain.repository.ClienteRepository;
import com.glelk.algalog.api.domain.service.CatalogoClienteService;
import com.glelk.algalog.api.model.ClienteModel;
import com.glelk.algalog.api.model.input.ClienteInputModel;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CatalogoClienteService catalogoClienteService;
    @Autowired
    private ClienteAssembler clienteAssembler;

    @GetMapping
    public List<ClienteModel> listar() {
        return clienteAssembler.toCollectionModel(clienteRepository.findAll());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> buscarCliente(@PathVariable Long clienteId) {
        return clienteRepository.findById(clienteId)
                .map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionarCliente(@Valid @RequestBody ClienteInputModel clienteInputModel) {
        Cliente cliente = clienteAssembler.toEntity(clienteInputModel);
        return catalogoClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteModel> atualizarCliente(@PathVariable Long clienteId,
            @Valid @RequestBody ClienteInputModel clienteInputModel) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = clienteAssembler.toEntity(clienteInputModel);
        cliente.setId(clienteId);
        ClienteModel clienteModel = clienteAssembler.toModel(catalogoClienteService.salvar(cliente));
        return ResponseEntity.ok(clienteModel);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> removerCliente(@PathVariable Long clienteId) {
        if (!clienteRepository.existsById(clienteId)) {
            return ResponseEntity.notFound().build();
        }
        catalogoClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }
}
