package br.com.welson.banco.comum;

public enum MensagemErro {

    VALOR_DEVE_SER_MAIOR_QUE_ZERO("O valor precisa ser maior que zero!"),
    SALDO_INSUFICIENTE("Saldo insuficiente!"),
    CONTA_EXCEDEU_NUMERO_DE_SAQUES("Essa conta excedeu o numero de saques mensal!"),
    ERRO_INTERNO("Erro interno, contate o administrador!"),
    NAO_DEVE_SER_VAZIO("Não deve ser vazio!"),
    NAO_DEVE_SER_NULO("Não deve ser nulo"),
    CONTA_NAO_ENCONTRADA("Conta não encontrada"), CONTA_ORIGEM_NAO_ENCONTRADA("Conta origem não encontrada!"), CONTA_DESTINO_NAO_ENCONTRADA("Conta destino não encontrada!");

    private String message;

    MensagemErro(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
