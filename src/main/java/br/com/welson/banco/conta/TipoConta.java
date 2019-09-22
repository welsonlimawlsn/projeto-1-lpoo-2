package br.com.welson.banco.conta;

public enum TipoConta {

    CORRENTE("Conta Corrente"), POUPANCA("Conta Poupança"), SALARIO("Conta Salário");

    private String value;

    TipoConta(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
