package br.com.welson.banco.exception;

import br.com.welson.banco.comum.MensagemErro;

public class InfraestruturaException extends RuntimeException {

    public InfraestruturaException(String message) {
        super(message);
    }

    public InfraestruturaException(String message, Throwable cause) {
        super(message, cause);
    }

    public InfraestruturaException(MensagemErro message) {
        super(message.getMessage());
    }

    public InfraestruturaException(MensagemErro message, Throwable cause) {
        super(message.getMessage(), cause);
    }
}
