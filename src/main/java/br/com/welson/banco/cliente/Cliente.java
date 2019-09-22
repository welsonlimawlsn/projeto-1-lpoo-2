package br.com.welson.banco.cliente;

import br.com.welson.banco.comum.AbstractEntity;

public class Cliente extends AbstractEntity<String> {

    private String nome;

    private String cpf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String getId() {
        return cpf;
    }

    @Override
    public void setId(String s) {
        cpf = s;
    }
}
