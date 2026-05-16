package ucu.edu.aed.medible.ej14;

import java.util.Arrays;

public class HashLineal {

    public static final int TAMANO = 17;
    private static final int VACIO = -1;

    private final int[] tabla;
    private int totalColisiones;

    public HashLineal() {
        tabla = new int[TAMANO];
        Arrays.fill(tabla, VACIO);
        totalColisiones = 0;
    }

    public int hash(int clave) { return clave % TAMANO; }

    public int insertar(int clave) {
        int posBase = hash(clave);
        int comparaciones = 0;
        for (int i = 0; i < TAMANO; i++) {
            int pos = (posBase + i) % TAMANO;
            comparaciones++;
            if (tabla[pos] == VACIO) {
                tabla[pos] = clave;
                totalColisiones += i;
                return comparaciones;
            }
        }
        throw new RuntimeException("Tabla llena");
    }

    public int buscarExitoso(int clave) {
        int posBase = hash(clave);
        int comparaciones = 0;
        for (int i = 0; i < TAMANO; i++) {
            int pos = (posBase + i) % TAMANO;
            comparaciones++;
            if (tabla[pos] == clave) return comparaciones;
            if (tabla[pos] == VACIO) break;
        }
        return comparaciones;
    }

    public int buscarNoExitoso(int clave) {
        int posBase = hash(clave);
        int comparaciones = 0;
        for (int i = 0; i < TAMANO; i++) {
            int pos = (posBase + i) % TAMANO;
            comparaciones++;
            if (tabla[pos] == VACIO) return comparaciones;
        }
        return comparaciones;
    }

    public int getTotalColisiones() { return totalColisiones; }
    public int[] getTabla()         { return tabla; }
}