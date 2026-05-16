package ucu.edu.aed.medible.Ej7;

import ucu.edu.aed.medible.lib.Medible;
import ucu.edu.aed.medible.lib.Medicion;
import ucu.edu.aed.medible.medibles.MedicionBuscarArrayList;
import ucu.edu.aed.medible.medibles.MedicionBuscarHashMap;
import ucu.edu.aed.medible.medibles.MedicionBuscarLinkedList;
import ucu.edu.aed.medible.medibles.MedicionBuscarTreeMap;
import ucu.edu.aed.medible.medibles.MedicionBuscarTrie;
import ucu.edu.aed.medible.medibles.MedicionPredecirHashMap;
import ucu.edu.aed.medible.medibles.MedicionPredecirLinkedList;
import ucu.edu.aed.medible.medibles.MedicionPredecirTrie;
import ucu.edu.aed.tda.trie.TTrie;
import ucu.edu.aed.tda.trie.impl.Trie;
import ucu.edu.aed.utils.FileUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

public class Ejercicio7 {

    private static final int REPETICIONES = 20;
    private static final String PREFIJO = "cas";

    public static void main(String[] args) {
        TTrie<String> trie = new Trie<>();
        LinkedList<String> linkedList = new LinkedList<>();
        ArrayList<String> arrayList = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        TreeMap<String, String> treeMap = new TreeMap<>();
        LinkedList<String> palabrasParaAgregar = new LinkedList<>();
        LinkedList<String> palabrasParaBuscar = new LinkedList<>();
        FileUtils.leerLineas("ut03/listado-general-desordenado.txt", palabrasParaAgregar::add);
        FileUtils.leerLineas("ut03/listado-general-palabrasBuscar.txt", palabrasParaBuscar::add);
        for (String linea : palabrasParaAgregar) {
            String palabra = linea.trim().toLowerCase();
            if (palabra.length() > 0) {
                trie.insertar(palabra, palabra);
                linkedList.add(palabra);
                arrayList.add(palabra);
                hashMap.put(palabra, palabra);
                treeMap.put(palabra, palabra);
            }
        }
        for (int i = 0; i < palabrasParaBuscar.size(); i++) {
            String palabra = palabrasParaBuscar.get(i).trim().toLowerCase();
            palabrasParaBuscar.set(i, palabra);
        }
        ejecutarMedicionesBusqueda(linkedList, arrayList, trie, hashMap, treeMap, palabrasParaBuscar);
        ejecutarMedicionesPredecir(linkedList, trie, hashMap);
    }
    private static void ejecutarMedicionesBusqueda(LinkedList<String> linkedList, ArrayList<String> arrayList,TTrie<String> trie,HashMap<String, String> hashMap, TreeMap<String, String> treeMap,  LinkedList<String> palabrasParaBuscar) {
        LinkedList<Medible<List<String>>> medibles = new LinkedList<>();
        medibles.add(new MedicionBuscarLinkedList(linkedList));
        medibles.add(new MedicionBuscarArrayList(arrayList));
        medibles.add(new MedicionBuscarTrie(trie));
        medibles.add(new MedicionBuscarHashMap(hashMap));
        medibles.add(new MedicionBuscarTreeMap(treeMap));
        String salida = "estructura,memoria,tiempo\n";
        System.out.println("Ejercicio 7 - Parte 4: mediciones de busqueda");
        System.out.println("------------------------------------------------");
        for (Medible<List<String>> medible : medibles) {
            Medicion medicion = medible.medir(REPETICIONES, palabrasParaBuscar);
            medicion.print();
            salida = salida + medicion.toCSV() + "\n";
        }
        FileUtils.escribirLineas("salida-busquedas.csv", salida);
    }
    private static void ejecutarMedicionesPredecir(LinkedList<String> linkedList,TTrie<String> trie, HashMap<String, String> hashMap) {
        LinkedList<Medible<String>> medibles = new LinkedList<>();
        medibles.add(new MedicionPredecirTrie(trie));
        medibles.add(new MedicionPredecirLinkedList(linkedList));
        medibles.add(new MedicionPredecirHashMap(hashMap));
        String salida = "estructura,memoria,tiempo\n";
        System.out.println();
        System.out.println("Ejercicio 7 - Parte 5: mediciones de predecir con prefijo \"" + PREFIJO + "\"");
        System.out.println("----------------------------------------------------------------");
        for (Medible<String> medible : medibles) {
            Medicion medicion = medible.medir(REPETICIONES, PREFIJO);
            medicion.print();
            salida = salida + medicion.toCSV() + "\n";
        }
        FileUtils.escribirLineas("salida-predecir.csv", salida);
    }
}
