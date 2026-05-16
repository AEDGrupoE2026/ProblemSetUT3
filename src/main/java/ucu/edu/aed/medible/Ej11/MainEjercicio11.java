package ucu.edu.aed.medible.Ej11;

public class MainEjercicio11 {

    public static void main(String[] args) {
        ContadorFrecuencias contador = new ContadorFrecuencias();
        contador.cargarLibro("src/main/java/ucu/edu/aed/medible/Ej11/libro.txt");
        System.out.println("Top 10 palabras:");
        contador.mostrarTop10();
        System.out.println();
        System.out.println("Grafico:");
        contador.graficarTop10();
    }
}