package ucu.edu.aed.medible.Ej12;

import java.util.List;

public class TTrieHashMap {

    private TNodoTrieHashMap raiz;

    public TTrieHashMap() {
        raiz = new TNodoTrieHashMap();
    }

    public void insertar(String palabra) {
        if (palabra != null && palabra.trim().length() > 0) {
            raiz.insertar(palabra.toLowerCase());
        }
    }

    public boolean buscar(String palabra) {
        if (palabra == null || palabra.trim().length() == 0) {
            return false;
        }

        return raiz.buscar(palabra.toLowerCase());
    }

    public List<String> predecir(String prefijo) {
        return raiz.predecir(prefijo.toLowerCase());
    }

    public void insertarTextoParaPatrones(String texto) {
        texto = texto.toLowerCase();

        for (int i = 0; i < texto.length(); i++) {
            raiz.insertarSufijo(texto.substring(i), i);
        }
    }

    public List<Integer> buscarPatron(String patron) {
        return raiz.buscarPatron(patron.toLowerCase());
    }
}