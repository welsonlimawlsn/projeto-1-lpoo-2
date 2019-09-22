package br.com.welson.banco.exception;

import br.com.welson.banco.comum.MensagemErro;

public class NegocioException extends Exception {

    public NegocioException(MensagemErro message) {
        super(message.getMessage());
    }

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(MensagemErro message, Throwable cause) {
        super(message.getMessage(), cause);
    }
}
