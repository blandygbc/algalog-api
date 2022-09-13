package com.glelk.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.glelk.algalog.api.domain.model.Entrega;
import com.glelk.algalog.api.model.EntregaModel;
import com.glelk.algalog.api.model.input.EntregaInputModel;

@Component
public class EntregaAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EntregaModel toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaModel.class);
    }

    public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInputModel entregaInputModel) {
        return modelMapper.map(entregaInputModel, Entrega.class);
    }
}
