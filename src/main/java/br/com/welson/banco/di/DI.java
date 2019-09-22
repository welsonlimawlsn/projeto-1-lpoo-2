package br.com.welson.banco.di;

import br.com.welson.banco.exception.InfraestruturaException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DI {

    private static List<Object> instanciasSigleton = new ArrayList<>();

    public static <T, R extends T> R injeta(Class<T> type) {

        Object instanciaPronta = procuraInstanciaSigleton(type);

        if (instanciaPronta != null) {
            return (R) instanciaPronta;
        }

        List<Instancia<R>> instancias = getInstancias(type);

        if (instancias.size() == 0) {
            throw new InfraestruturaException("A classe " + type.getName() + " não tem nenhuma implementação");
        }

        if (instancias.size() > 1) {
            throw new InfraestruturaException("A classe " + type.getName() + " tem mais de uma implementação");
        }

        return instancias.get(0).novaInstacia();
    }

    public static <T, R extends T> List<Instancia<R>> getInstancias(Class<T> type) {

        List<Instancia<R>> instancias = new ArrayList<>();

        for (Class<?> classe : RegistroClassesDI.CLASSES_DISPONIVEIS) {
            if (procuraTipo(classe, type)) {
                instancias.add(new Instancia<>((Class<R>) classe));
            }
        }

        return instancias;
    }

    static <T> Object procuraInstanciaSigleton(Class<T> type) {
        List<Object> list = instanciasSigleton.stream().filter(instancia -> procuraTipo(instancia.getClass(), type)).collect(Collectors.toList());

        return list.size() == 1 ? list.get(0) : null;
    }

    private static <T> boolean procuraTipo(Class<?> classe, Class<T> type) {
        if (classe.equals(Object.class)) {
            return false;
        }

        if (classe.equals(type)) {
            return true;
        }

        return procuraTipo(classe.getSuperclass(), type);
    }

    static List<Object> getInstanciasSigleton() {
        return instanciasSigleton;
    }
}
