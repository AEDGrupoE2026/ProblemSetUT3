package ucu.edu.aed.medible.ej13;

import java.util.Objects;

public class Alumno {

    private int id;
    private String nombreCompleto;
    private String email;

    public Alumno(int id, String nombreCompleto, String email) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public String getEmail() { return email; }

    // Dos alumnos son iguales si tienen el mismo id
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Alumno otro = (Alumno) obj;
        return this.id == otro.id;
    }

    // hashCode debe usar los mismos campos que equals, si no se rompe el contrato
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Alumno{id=" + id + ", nombre='" + nombreCompleto + "', email='" + email + "'}";
    }
}