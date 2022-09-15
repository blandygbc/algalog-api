package com.glelk.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glelk.algalog.api.domain.model.Cliente;
import com.glelk.algalog.api.model.ClienteModel;
import com.glelk.algalog.api.model.input.ClienteInputModel;

@Component
public class ClienteAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }

    public List<ClienteModel> toCollectionModel(List<Cliente> cliente) {
        return cliente.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Cliente toEntity(ClienteInputModel clienteInputModel) {
        return modelMapper.map(clienteInputModel, Cliente.class);
    }
}
