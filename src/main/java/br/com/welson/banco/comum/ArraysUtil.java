package br.com.welson.banco.comum;

public class ArraysUtil {

    public static <T> int procura(T[] array, T objeto) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(objeto))
                return i;
        }
        return -1;
    }
}
