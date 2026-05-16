package ucu.edu.aed.medible.ej14;

import java.util.LinkedList;

// Tabla hash con encadenamiento separado: cada bucket es una lista enlazada
public class HashEncadenamiento {

    public static final int TAMANO = 17;

    private final LinkedList<Integer>[] tabla;
    private int totalColisiones;

    @SuppressWarnings("unchecked")
    public HashEncadenamiento() {
        tabla = new LinkedList[TAMANO];
        for (int i = 0; i < TAMANO; i++) {
            tabla[i] = new LinkedList<>();
        }
        totalColisiones = 0;
    }

    public int hash(int clave) { return clave % TAMANO; }

    public int insertar(int clave) {
        int pos = hash(clave);
        if (!tabla[pos].isEmpty()) totalColisiones++;
        tabla[pos].addLast(clave);
        return 1;
    }

    public int buscarExitoso(int clave) {
        int pos = hash(clave);
        int comparaciones = 0;
        for (int elem : tabla[pos]) {
            comparaciones++;
            if (elem == clave) return comparaciones;
        }
        return comparaciones;
    }

    public int buscarNoExitoso(int clave) {
        int pos = hash(clave);
        int comparaciones = 0;
        for (int elem : tabla[pos]) comparaciones++;
        return comparaciones;
    }

    public int getTotalColisiones()         { return totalColisiones; }
    public LinkedList<Integer>[] getTabla() { return tabla; }
}