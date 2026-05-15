package ucu.edu.aed.tda.trie.impl;

import ucu.edu.aed.tda.trie.*;
import java.util.function.Consumer;
import java.util.List;

public class Trie<T> implements TTrie<T> {

    public Trie() {

    }

    @Override
    public void recorrer(Consumer<Entry<T>> consumer) {
        
    }

    @Override
    public Entry<T> buscar(String palabra) {
        return null;
    }

    @Override
    public boolean insertar(String palabra, T dato) {
        return false;
    }

    @Override
    public List<Entry<T>> predecir(String prefijo) {
        return null;
    }
}
