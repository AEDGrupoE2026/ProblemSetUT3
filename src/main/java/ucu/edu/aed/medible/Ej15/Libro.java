package ucu.edu.aed.medible.Ej15;

import java.util.HashSet;
public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int anio;

    public Libro(String isbn, String titulo, String autor, int anio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // mismo objeto
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return isbn != null && isbn.equals(libro.isbn);
    }
    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }
    public static void main(String[] args) {
        Libro l1 = new Libro("12345", "El Quijote", "Cervantes", 1605);
        Libro l2 = new Libro("12345", "Don Quijote", "Miguel de Cervantes", 1605);
        HashSet<Libro> set = new HashSet<>();
        set.add(l1);
        set.add(l2);
        System.out.println("Tamaño del HashSet: " + set.size());

    }
}

/* 1) 
El que deberiamo susar para determinar la identidad logica de un libro es el ISBN, 
debido a que es un identificador unico para cada libro, 
mientras que el titulo, autor y año pueden ser compartidos por varios libros. 
El ISBN es un codigo estandarizado que permite identificar de manera unica cada edicion de un libro,
lo que lo convierte en el atributo mas adecuado para determinar la identidad logica de un libro.

    3)
Si equals usa ISBN y hashCode usa titulo, es un error porque viola el contrato entre equals y hashCode.
El contrato establece que si dos objetos son iguales según equals, entonces deben tener el mismo hashCode.
Si equals se basa en ISBN, entonces dos libros con el mismo ISBN serán considerados iguales, 
pero si hashCode se basa en el titulo, podrían tener hashCodes diferentes si tienen títulos distintos.
Esto puede causar problemas al usar estos objetos en colecciones basadas en hash.
    5)
Lo que deberia ocurrir es que el HashSet considere a ambos libros como iguales y solo almacene uno de ellos,
debido a que ambos tienen el mismo ISBN. El tamaño del HashSet debería ser 1
    6)
Los test que podria utilizar para verificar el correcto funcionamiento de equals y hashCode son:
Test Reflexivo: Verificar que un libro es igual a sí mismo.
Test Simétrico: Verificar que si un libro A es igual a un libro B, entonces B es igual a A.
Test Transitivo: Verificar que si un libro A es igual a un libro B, y B es igual a un libro C, entonces A es igual a C.
Test Consistente: Verificar que múltiples llamadas a equals con los mismos objetos devuelvan el mismo resultado.
Test Compatibilidad con hashCode: Verificar que si dos libros son iguales según equals, entonces tienen el mismo hashCode.
*/