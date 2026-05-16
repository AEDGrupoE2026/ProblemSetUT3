package ucu.edu.aed.medible.Ej16;

public class Persona implements Comparable<Persona> {
    private String nombre;
    private int anioNacimiento;
    public Persona(String nombre, int anioNacimiento) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
    }
    public String getNombre() {
        return nombre;
    }
    public int getAnioNacimiento() {
        return anioNacimiento;
    }
    @Override
    public int compareTo(Persona otra) {
        return this.nombre.compareTo(otra.nombre);
    }
    @Override
    public String toString() {
        return nombre + " (" + anioNacimiento + ")";
    }
}
