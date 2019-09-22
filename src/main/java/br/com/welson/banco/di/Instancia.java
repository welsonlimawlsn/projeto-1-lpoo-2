package br.com.welson.banco.di;

public class Instancia<T> {

    private Class<T> classe;

    public Instancia(Class<T> classe) {
        this.classe = classe;
    }

    public T novaInstacia() {
        try {
            Object instanciaPronta = DI.procuraInstanciaSigleton(classe);
            if (instanciaPronta != null) {
                return (T) instanciaPronta;
            }
            T instance = classe.newInstance();
            DI.getInstanciasSigleton().add(instance);
            return instance;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Class<T> getClasse() {
        return classe;
    }
}
