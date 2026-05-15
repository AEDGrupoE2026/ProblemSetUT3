package ucu.edu.aed.tda.trie.impl;

import ucu.edu.aed.tda.trie.TNodoTrie;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
import ucu.edu.aed.tda.trie.Entry;
import java.util.HashMap;
import java.util.Map;

public class NodoTrie<T> implements TNodoTrie<T> {
  
    private T dato;
    private boolean esPalabra;
    private final Map<Character, NodoTrie<T>> hijos;

    public NodoTrie() {
        this.dato = null;
        this.esPalabra = false;
        this.hijos = new HashMap<>();
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

    @Override
    public T getDato() {
        return esPalabra ? dato : null;
    }

    @Override
    public boolean esPalabra() {
        return esPalabra;
    }
}
