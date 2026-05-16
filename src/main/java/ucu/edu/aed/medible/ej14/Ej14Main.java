package ucu.edu.aed.medible.ej14;

import java.util.LinkedList;

public class Ej14Main {

    static final int[] CLAVES = {45, 12, 37, 82, 29, 54, 31, 76, 18, 93, 11, 68};
    static final int[] CLAVES_NO_EXITOSAS = {20, 23, 26, 28, 42, 44};

    public static void main(String[] args) {
        sondeoLineal();
        sondeoCuadratico();
        encadenamiento();
        resumen();
    }

    static void sondeoLineal() {

        System.out.println("=== SONDEO LINEAL: h(i) = (h(0) + i) mod 17 ===");
        HashLineal hl = new HashLineal();

        for (int c : CLAVES) {
            int comps = hl.insertar(c);
            int pos = buscarPosicion(hl.getTabla(), c);
            System.out.println("clave=" + c + " h(k)=" + hl.hash(c) + " posicion=" + pos + " comparaciones=" + comps);
        }

        imprimirTabla(hl.getTabla());
        System.out.println("Total colisiones: " + hl.getTotalColisiones());

        System.out.println("\nBusqueda exitosa:");
        int totalE = 0;

        for (int c : CLAVES) {
            int comp = hl.buscarExitoso(c);
            totalE = totalE + comp;
            System.out.println("  " + c + " -> " + comp + " comparaciones");
        }

        System.out.println("Promedio: " + (double) totalE / CLAVES.length);

        System.out.println("\nBusqueda no exitosa (claves: 20, 23, 26, 28, 42, 44):");
        int totalNE = 0;

        for (int c : CLAVES_NO_EXITOSAS) {
            int comp = hl.buscarNoExitoso(c);
            totalNE = totalNE + comp;
            System.out.println("  " + c + " -> " + comp + " comparaciones");
        }

        System.out.println("Promedio: " + (double) totalNE / CLAVES_NO_EXITOSAS.length);
    }

    static void sondeoCuadratico() {

        System.out.println("\n=== SONDEO CUADRATICO: h(i) = (h(0) + i^2) mod 17 ===");
        HashCuadratico hc = new HashCuadratico();

        for (int c : CLAVES) {
            hc.insertar(c);
            int pos = buscarPosicion(hc.getTabla(), c);
            System.out.println("clave=" + c + " h(k)=" + hc.hash(c) + " posicion=" + pos);
        }

        imprimirTabla(hc.getTabla());
        System.out.println("Total colisiones: " + hc.getTotalColisiones());

        System.out.println("\nBusqueda exitosa:");
        int totalE = 0;

        for (int c : CLAVES) {
            int comp = hc.buscarExitoso(c);
            totalE = totalE + comp;
            System.out.println("  " + c + " -> " + comp + " comparaciones");
        }

        System.out.println("Promedio: " + (double) totalE / CLAVES.length);

        System.out.println("\nBusqueda no exitosa (claves: 20, 23, 26, 28, 42, 44):");
        int totalNE = 0;

        for (int c : CLAVES_NO_EXITOSAS) {
            int comp = hc.buscarNoExitoso(c);
            totalNE = totalNE + comp;
            System.out.println("  " + c + " -> " + comp + " comparaciones");
        }

        System.out.println("Promedio: " + (double) totalNE / CLAVES_NO_EXITOSAS.length);
    }

    static void encadenamiento() {
        System.out.println("\n=== ENCADENAMIENTO SEPARADO ===");
        HashEncadenamiento he = new HashEncadenamiento();
        for (int c : CLAVES) he.insertar(c);

        LinkedList<Integer>[] t = he.getTabla();
        System.out.println("Estado final:");

        for (int i = 0; i < HashEncadenamiento.TAMANO; i++) {
            System.out.println("  bucket[" + i + "]: " + (t[i].isEmpty() ? "---" : t[i].toString()));
        }

        System.out.println("Total colisiones: " + he.getTotalColisiones());

        System.out.println("\nBusqueda exitosa:");
        int totalE = 0;

        for (int c : CLAVES) {
            int comp = he.buscarExitoso(c);
            totalE = totalE + comp;
            System.out.println("  " + c + " -> " + comp + " comparaciones");
        }

        System.out.println("Promedio: " + (double) totalE / CLAVES.length);

        System.out.println("\nBusqueda no exitosa (claves: 20, 23, 26, 28, 42, 44):");
        int totalNE = 0;

        for (int c : CLAVES_NO_EXITOSAS) {
            int comp = he.buscarNoExitoso(c);
            totalNE = totalNE + comp;
            System.out.println("  " + c + " -> " + comp + " comparaciones");
        }

        System.out.println("Promedio: " + (double) totalNE / CLAVES_NO_EXITOSAS.length);
    }

    static void resumen() {
        System.out.println("\n=== RESUMEN ===");

        HashLineal hl         = new HashLineal();
        HashCuadratico hc     = new HashCuadratico();
        HashEncadenamiento he = new HashEncadenamiento();

        for (int c : CLAVES) { 
            hl.insertar(c); hc.insertar(c); he.insertar(c);
        }

        int exitosoL = 0; int noExitosoL = 0;
        int exitosoC = 0; int noExitosoC = 0;
        int exitosoE = 0; int noExitosoE = 0;

        for (int c : CLAVES) {
            exitosoL = exitosoL + hl.buscarExitoso(c);
            exitosoC = exitosoC + hc.buscarExitoso(c);
            exitosoE = exitosoE + he.buscarExitoso(c);
        }

        for (int c : CLAVES_NO_EXITOSAS) {
            noExitosoL = noExitosoL + hl.buscarNoExitoso(c);
            noExitosoC = noExitosoC + hc.buscarNoExitoso(c);
            noExitosoE = noExitosoE + he.buscarNoExitoso(c);
        }

        System.out.println("Sondeo Lineal     colisiones: " + hl.getTotalColisiones() + " prom exitosa: " + (double) exitosoL / CLAVES.length + " prom no exitosa: " + (double) noExitosoL / CLAVES_NO_EXITOSAS.length);
        System.out.println("Sondeo Cuadratico colisiones: " + hc.getTotalColisiones() + " prom exitosa: " + (double) exitosoC / CLAVES.length + " prom no exitosa: " + (double) noExitosoC / CLAVES_NO_EXITOSAS.length);
        System.out.println("Encadenamiento    colisiones: " + he.getTotalColisiones() + " prom exitosa: " + (double) exitosoE / CLAVES.length + " prom no exitosa: " + (double) noExitosoE / CLAVES_NO_EXITOSAS.length);

        System.out.println("\nConclusiones:");
        System.out.println("Encadenamiento tiene menos colisiones y mejor promedio en busquedas exitosas.");
        System.out.println("Sondeo cuadratico mejora al lineal porque evita el clustering primario.");
        System.out.println("Sondeo lineal es el mas simple pero el mas afectado por clustering.");
        System.out.println("Ganador para este conjunto: encadenamiento separado.");
    }

    static int buscarPosicion(int[] tabla, int clave) {
        
        for (int i = 0; i < tabla.length; i++) if (tabla[i] == clave) return i;
        return -1;
    }

    static void imprimirTabla(int[] tabla) {
        System.out.println("Estado final:");

        for (int i = 0; i < tabla.length; i++) {
            System.out.println("  [" + i + "]: " + (tabla[i] == -1 ? "---" : tabla[i]));
        }
    }
}