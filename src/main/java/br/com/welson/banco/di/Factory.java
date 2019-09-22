package br.com.welson.banco.di;

import br.com.welson.banco.comum.ArraysUtil;
import br.com.welson.banco.exception.InfraestruturaException;
import br.com.welson.banco.transacao.TipoTransacao;
import br.com.welson.banco.transacao.Transacao;

import java.util.List;

public class Factory<T> {

    private List<Instancia<T>> instancias;

    private Class<T> tClass;

    public Factory(Class<T> tClass) {
        this.tClass = tClass;
        instancias = DI.getInstancias(tClass);
    }

    public T criaNovaInstacia(TipoTransacao transacao) {

        for (Instancia<T> instancia : instancias) {
            Transacao annotation = instancia.getClasse().getAnnotation(Transacao.class);
            if (annotation != null && ArraysUtil.procura(annotation.value(), transacao) >= 0) {
                return instancia.novaInstacia();
            }
        }

        throw new InfraestruturaException("A classe " + tClass.getName() + " não tem nenhuma classe de implementação anotada com " + Transacao.class.getName() + " = " + transacao);
    }
}
