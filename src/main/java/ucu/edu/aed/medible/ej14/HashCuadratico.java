package ucu.edu.aed.medible.ej14;

import java.util.Arrays;

// Tabla hash con sondeo cuadratico
public class HashCuadratico {

    public static final int TAMANO = 17;
    private static final int VACIO = -1;

    private final int[] tabla;
    private int totalColisiones;

    public HashCuadratico() {
        tabla = new int[TAMANO];
        Arrays.fill(tabla, VACIO);
        totalColisiones = 0;
    }

    public int hash(int clave) { return clave % TAMANO; }

    public int insertar(int clave) {
        int posBase = hash(clave);
        int comparaciones = 0;
        for (int i = 0; i < TAMANO; i++) {
            int pos = (posBase + i * i) % TAMANO;
            comparaciones++;
            if (tabla[pos] == VACIO) {
                tabla[pos] = clave;
                totalColisiones += i;
                return comparaciones;
            }
        }
        throw new RuntimeException("No se encontro slot libre");
    }

    public int buscarExitoso(int clave) {
        int posBase = hash(clave);
        int comparaciones = 0;
        for (int i = 0; i < TAMANO; i++) {
            int pos = (posBase + i * i) % TAMANO;
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
            int pos = (posBase + i * i) % TAMANO;
            comparaciones++;
            if (tabla[pos] == VACIO) return comparaciones;
        }
        return comparaciones;
    }

    public int getTotalColisiones() { return totalColisiones; }
    public int[] getTabla()         { return tabla; }
}