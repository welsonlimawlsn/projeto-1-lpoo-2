package br.com.welson.banco.transacao;

public class TransacaoTransferencia extends TransacaoFinanceira {

    private Integer agenciaDestino;
    private Integer numeroDestino;

    public Integer getAgenciaDestino() {
        return agenciaDestino;
    }

    public void setAgenciaDestino(Integer agenciaDestino) {
        this.agenciaDestino = agenciaDestino;
    }

    public Integer getNumeroDestino() {
        return numeroDestino;
    }

    public void setNumeroDestino(Integer numeroDestino) {
        this.numeroDestino = numeroDestino;
    }
}
