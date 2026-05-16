package ucu.edu.aed.medible.Ej11;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ContadorFrecuencias {

    private Map<String, Integer> frecuencias;//guarda palabras y sus frecuencias

    public ContadorFrecuencias() {
        frecuencias = new HashMap<>();
    }
    public void cargarLibro(String archivo) {
        try {
            Scanner scanner = new Scanner(new File(archivo));//se crea el lector de archivo, el scanner recorre el texto palabra por palabra 
            while (scanner.hasNext()) {
                String palabra = scanner.next();
                agregarPalabra(palabra);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }
    public void agregarPalabra(String palabra) {
        palabra = palabra.toLowerCase();
        palabra = palabra.replaceAll("[^\\p{L}]", "");//elimina signos de puntuación
        if (palabra.trim().length() > 0) {
            if (frecuencias.containsKey(palabra)) {
                frecuencias.put(palabra, frecuencias.get(palabra) + 1);
            } else {
                frecuencias.put(palabra, 1);//Si la palabra ya estaba en el hashmap, aumenta su frecuencia en 1, si no estaba, la agrega con frecuencia 1.
            }
        }
    }
    public List<Map.Entry<String, Integer>> obtenerTop10() {
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(frecuencias.entrySet());
        lista.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        if (lista.size() > 10) {
            return lista.subList(0, 10);
        }
        return lista;
    }
    public void mostrarTop10() {
        List<Map.Entry<String, Integer>> top10 = obtenerTop10();
        for (Map.Entry<String, Integer> entrada : top10) {
            System.out.println(entrada.getKey() + ": " + entrada.getValue());
        }
    }
    public void graficarTop10() {
        List<Map.Entry<String, Integer>> top10 = obtenerTop10();
        for (Map.Entry<String, Integer> entrada : top10) {
            System.out.print(entrada.getKey() + " (" + entrada.getValue() + "): ");
            for (int i = 0; i < entrada.getValue(); i++) {
                System.out.print("#");
            }
            System.out.println();
        }
    }
}