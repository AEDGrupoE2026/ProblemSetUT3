package ucu.edu.aed.medible.Ej12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TNodoTrieHashMap {

    private Map<Character, TNodoTrieHashMap> hijos;
    private boolean esPalabra;
    private List<Integer> posiciones;
    public TNodoTrieHashMap() {
        hijos = new HashMap<>();
        esPalabra = false;
        posiciones = new ArrayList<>();
    }
    public void insertar(String palabra) {
        TNodoTrieHashMap nodoActual = this;
        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            if (!nodoActual.hijos.containsKey(letra)) {
                nodoActual.hijos.put(letra, new TNodoTrieHashMap());
            }
            nodoActual = nodoActual.hijos.get(letra);
        }
        nodoActual.esPalabra = true;
    }

    public boolean buscar(String palabra) {
        TNodoTrieHashMap nodo = buscarNodo(palabra);
        if (nodo == null) {
            return false;
        }

        return nodo.esPalabra;
    }
    public List<String> predecir(String prefijo) {
        List<String> resultado = new ArrayList<>();
        TNodoTrieHashMap nodo = buscarNodo(prefijo);
        if (nodo != null) {
            nodo.obtenerPalabras(prefijo, resultado);
        }
        return resultado;
    }
    private TNodoTrieHashMap buscarNodo(String texto) {
        TNodoTrieHashMap nodoActual = this;
        for (int i = 0; i < texto.length(); i++) {
            char letra = texto.charAt(i);
            if (!nodoActual.hijos.containsKey(letra)) {
                return null;
            }
            nodoActual = nodoActual.hijos.get(letra);
        }
        return nodoActual;
    }

    private void obtenerPalabras(String palabraActual, List<String> resultado) {
        if (esPalabra) {
            resultado.add(palabraActual);
        }
        for (Character letra : hijos.keySet()) {
            hijos.get(letra).obtenerPalabras(palabraActual + letra, resultado);
        }
    }

    public void insertarSufijo(String sufijo, int posicion) {
        TNodoTrieHashMap nodoActual = this;
        for (int i = 0; i < sufijo.length(); i++) {
            char letra = sufijo.charAt(i);
            if (!nodoActual.hijos.containsKey(letra)) {
                nodoActual.hijos.put(letra, new TNodoTrieHashMap());
            }
            nodoActual = nodoActual.hijos.get(letra);
            nodoActual.posiciones.add(posicion);
        }
    }
    public List<Integer> buscarPatron(String patron) {
        TNodoTrieHashMap nodo = buscarNodo(patron);
        if (nodo == null) {
            return new ArrayList<>();
        }
        return nodo.posiciones;
    }
}