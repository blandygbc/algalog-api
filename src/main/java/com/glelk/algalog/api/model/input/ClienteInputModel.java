package com.glelk.algalog.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class ClienteInputModel {

    @NotBlank
    private String nome;
    @Valid
    @NotBlank
    private String email;
    @NotBlank
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
