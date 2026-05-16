package ucu.edu.aed.medible.Ej16;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ucu.edu.aed.tda.generic_trie.ArbolGenerico;
import ucu.edu.aed.tda.generic_trie.TNodoGenerico;

public class Ejercicio {

    public boolean esDescendiente(ArbolGenerico<Persona> arbol, Persona ancestro, Persona posibleDesc) {
        TNodoGenerico<Persona> nodoAncestro = arbol.getRaiz().buscar(ancestro);
        if (nodoAncestro == null) return false;
        return nodoAncestro.buscar(posibleDesc) != null;
    }
    public int calcularAltura(ArbolGenerico<Persona> arbol) {
        return altura(arbol.getRaiz());
    }
    private int altura(TNodoGenerico<Persona> nodo) {
        if (nodo == null) return -1;
        int maxAltura = -1;
        for (Persona hijo : nodo.obtenerHijos()) {
            TNodoGenerico<Persona> nodoHijo = nodo.buscar(hijo);
            int alturaHijo = altura(nodoHijo);
            if (alturaHijo > maxAltura) {
                maxAltura = alturaHijo;
            }
        }
        return maxAltura + 1;
    }
    private int contarPersonas(TNodoGenerico<Persona> nodo) {
        if (nodo == null) return 0;
        int count = 1;
        for (Persona hijo : nodo.obtenerHijos()) {
            TNodoGenerico<Persona> nodoHijo = nodo.buscar(hijo);
            count += contarPersonas(nodoHijo);
        }
        return count;
    }
    public Persona ancestroComun(ArbolGenerico<Persona> arbol, Persona p1, Persona p2) {
        List<Persona> camino1 = obtenerCamino(arbol, p1);
        List<Persona> camino2 = obtenerCamino(arbol, p2);
        Persona ancestro = null;
        for (int i = 0; i < Math.min(camino1.size(), camino2.size()); i++) {
            if (camino1.get(i).equals(camino2.get(i))) {
                ancestro = camino1.get(i);
            } else break;
        }
        return ancestro;
    }
    private List<Persona> obtenerCamino(ArbolGenerico<Persona> arbol, Persona persona) {
        List<Persona> camino = new ArrayList<>();
        TNodoGenerico<Persona> nodo = arbol.getRaiz().buscar(persona);
        while (nodo != null) {
            camino.add(0, nodo.getDato());
            nodo = arbol.getRaiz().obtenerPadre(nodo.getDato());
        }
        return camino;
    }
    public void obtenerGeneracion(ArbolGenerico<Persona> arbol, int nivel) {
        Queue<TNodoGenerico<Persona>> cola = new LinkedList<>();
        cola.add(arbol.getRaiz());
        int actualNivel = 0;
        while (!cola.isEmpty()) {
            int size = cola.size();
            for (int i = 0; i < size; i++) {
                TNodoGenerico<Persona> nodo = cola.poll();
                if (actualNivel == nivel) {
                    System.out.println(nodo.getDato());
                }
                for (Persona hijo : nodo.obtenerHijos()) {
                    cola.add(nodo.buscar(hijo));
                }
            }
            actualNivel++;
        }
    }
    public void listarDescendientes(ArbolGenerico<Persona> arbol, Persona persona) {
        TNodoGenerico<Persona> nodo = arbol.getRaiz().buscar(persona);
        if (nodo != null) {
            nodo.preOrden(n -> System.out.println(n.getDato()));
        }
    }
    public static void main(String[] args) {
        Ejercicio ej = new Ejercicio();
        Persona abuela = new Persona("Abuela", 1940);
        ArbolGenerico<Persona> arbol = new ArbolGenerico<>(abuela);
        //Aca se crean los tres primeros hijos
        Persona hijo1 = new Persona("Hijo1", 1965);
        Persona hijo2 = new Persona("Hijo2", 1968);
        Persona hijo3 = new Persona("Hijo3", 1970);
        arbol.agregarHijo(abuela, hijo1);
        arbol.agregarHijo(abuela, hijo2);
        arbol.agregarHijo(abuela, hijo3);
        //Aca se crean los nietos
        Persona nieto1 = new Persona("Nieto1", 1990);
        Persona nieto2 = new Persona("Nieto2", 1992);
        arbol.agregarHijo(hijo1, nieto1);
        arbol.agregarHijo(hijo1, nieto2);
        Persona nieto3 = new Persona("Nieto3", 1995);
        arbol.agregarHijo(hijo2, nieto3);
        //Aca se crean los bisnietos
        Persona bis1 = new Persona("Bisnieto1", 2015);
        Persona bis2 = new Persona("Bisnieto2", 2018);
        Persona bis3 = new Persona("Bisnieto3", 2020);
        arbol.agregarHijo(nieto1, bis1);
        arbol.agregarHijo(nieto2, bis2);
        arbol.agregarHijo(nieto3, bis3);
        //Pruebas para verificar fuuncionamiento
        System.out.println("Descendientes de Hijo1:");
        ej.listarDescendientes(arbol, hijo1);

        System.out.println("\nAltura del árbol:");
        System.out.println(ej.calcularAltura(arbol));

        System.out.println("\nCantidad total de personas:");
        System.out.println(ej.contarPersonas(arbol.getRaiz()));
        System.out.println("\nGeneración 2 (nietos):");
        ej.obtenerGeneracion(arbol, 2);

        System.out.println("\nAncestro común de Nieto1 y Nieto2:");
        System.out.println(ej.ancestroComun(arbol, nieto1, nieto2));

        System.out.println("\n¿Bisnieto1 es descendiente de Hijo1?");
        System.out.println(ej.esDescendiente(arbol, hijo1, bis1));
        
    }
}