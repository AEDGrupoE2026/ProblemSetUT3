package ucu.edu.aed.medible.ej13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Ej13Main {

    public static void main(String[] args) {
        parte1();
        parte2();
        parte3();
    }

    // Parte 1: hashCode en Object, Integer y String
    static void parte1() {
        System.out.println("=== PARTE 1 - hashCode() ===");
 
        Object obj1 = new Object();
        Object obj2 = new Object();
        System.out.println("Object obj1: " + obj1.hashCode());
        System.out.println("Object obj2: " + obj2.hashCode());

        System.out.println("Integer(42): " + Integer.valueOf(42).hashCode());
        System.out.println("Integer(99): " + Integer.valueOf(99).hashCode());

        System.out.println("Hola: " + "Hola".hashCode());
        System.out.println("new String(Hola): " + new String("Hola").hashCode() + " <- mismo contenido, mismo hash");
        System.out.println("hola: " + "hola".hashCode() + " <- distinto, la mayuscula importa");
    }

    // Parte 2: estructura interna de HashMap con 4 inserciones
    static void parte2() {
        System.out.println("\n=== PARTE 2 - Estructura interna HashMap ===");

        String[] claves = {"Hola", "HolaMundo", "HashMap", "Colecciones"};

        for (String clave : claves) {
            int hash   = clave.hashCode();
            int bucket = (hash ^ (hash >>> 16)) & 15;
            System.out.println(clave + " -> hashCode: " + hash + ", bucket: " + bucket);
        }

        HashMap<String, String> mapa = new HashMap<>();
        mapa.put("Hola", "valor1");
        mapa.put("HolaMundo", "valor2");
        mapa.put("HashMap", "valor3");
        mapa.put("Colecciones", "valor4");

        System.out.println("\nEstado del mapa:");

        for (Map.Entry<String, String> entrada : mapa.entrySet()) {
            int h = entrada.getKey().hashCode();
            int bucket = (h ^ (h >>> 16)) & 15;
            System.out.println("  bucket[" + bucket + "] -> " + entrada.getKey());
        }
    }

    // Parte 3: pruebas de equals y hashCode en Alumno
    static void parte3() {
        System.out.println("\n=== PARTE 3 - equals() y hashCode() en Alumno ===");

        Alumno a1 = new Alumno(101, "Ana Garcia",    "ana@ucu.edu.uy");
        Alumno a2 = new Alumno(101, "Ana Garcia",    "ana.garcia@ucu.edu.uy"); // mismo id
        Alumno a3 = new Alumno(202, "Bruno Pereira", "bruno@ucu.edu.uy");

        System.out.println("a1.equals(a2) = " + a1.equals(a2) + " <- true, mismo id");
        System.out.println("a1.equals(a3) = " + a1.equals(a3) + " <- false, distinto id");
        System.out.println("a1.hashCode() = " + a1.hashCode());
        System.out.println("a2.hashCode() = " + a2.hashCode() + " <- igual al de a1");

        HashSet<Alumno> conjunto = new HashSet<>();
        conjunto.add(a1);
        conjunto.add(a2);
        conjunto.add(a3);
        System.out.println("\nElementos en HashSet: " + conjunto.size() + " (esperado: 2)");

        HashMap<Alumno, String> mapaAlumnos = new HashMap<>();
        mapaAlumnos.put(a1, "Inscripto en AED");
        mapaAlumnos.put(a2, "Actualizado: AED y POO");
        mapaAlumnos.put(a3, "Inscripto en POO");
        System.out.println("Entradas en HashMap: " + mapaAlumnos.size() + " (esperado: 2)");
        
        for (Map.Entry<Alumno, String> entrada : mapaAlumnos.entrySet()) {
            System.out.println("  id=" + entrada.getKey().getId() + " -> " + entrada.getValue());
        }
    }
}