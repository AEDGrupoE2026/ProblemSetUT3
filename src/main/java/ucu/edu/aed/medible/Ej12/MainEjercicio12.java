package ucu.edu.aed.medible.Ej12;

import java.util.List;

public class MainEjercicio12 {

    public static void main(String[] args) {
        TTrieHashMap trie = new TTrieHashMap();

        trie.insertar("casa");
        trie.insertar("casamiento");
        trie.insertar("casco");
        trie.insertar("perro");
        trie.insertar("persona");
        trie.insertar("programa");
        trie.insertar("programar");

        System.out.println("Buscar casa: " + trie.buscar("casa"));
        System.out.println("Buscar casita: " + trie.buscar("casita"));

        System.out.println();
        System.out.println("Autocompletar con cas:");

        List<String> predicciones = trie.predecir("cas");

        for (String palabra : predicciones) {
            System.out.println(palabra);
        }

        System.out.println();

        TTrieHashMap triePatrones = new TTrieHashMap();

        String texto = "la casa es grande y la casa tiene perro";
        triePatrones.insertarTextoParaPatrones(texto);

        System.out.println("Posiciones donde aparece casa:");

        List<Integer> posiciones = triePatrones.buscarPatron("casa");

        for (Integer posicion : posiciones) {
            System.out.println(posicion);
        }
    }
}