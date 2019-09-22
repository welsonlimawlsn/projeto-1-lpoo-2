package br.com.welson.banco.comum;

import br.com.welson.banco.exception.NegocioException;

public class Validador {

    public static void naoVazio(String s) throws NegocioException {
        naoVazio(s, MensagemErro.NAO_DEVE_SER_VAZIO.getMessage());
    }

    public static void naoVazio(String s, String messagem) throws NegocioException {
        if (s == null || s.isEmpty()) {
            throw new NegocioException(messagem);
        }
    }

    public static void naoNulo(Object o) throws NegocioException {
        if (o == null) {
            throw new NegocioException(MensagemErro.NAO_DEVE_SER_NULO);
        }
    }
}
