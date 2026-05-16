
package ucu.edu.aed.tda.generic_trie;

import java.util.function.Consumer;

public class ArbolGenerico<T extends Comparable<T>> implements TArbolGenerico<T> {
    private NodoGenerico<T> raiz;

    public ArbolGenerico(T raizDato) {
        this.raiz = new NodoGenerico<>(raizDato);
    }
    @Override
    public boolean agregarHijo(Comparable<T> padre, T hijo) {
        return raiz.agregarHijo((T) padre, hijo);
    }
    @Override
    public void eliminar(Comparable<T> criterio) {
        if (criterio.compareTo(raiz.getDato()) == 0) {
            raiz = null;
        } else {
            raiz.eliminar(criterio);
        }
    }
    public TNodoGenerico<T> getRaiz() {
        return raiz;
    }
    @Override
    public T obtenerPadre(Comparable<T> criterio) {
        TNodoGenerico<T> padre = raiz.obtenerPadre(criterio);
        return padre != null ? padre.getDato() : null;
    }
    @Override
    public T buscar(Comparable<T> criterio) {
        TNodoGenerico<T> nodo = raiz.buscar(criterio);
        return nodo != null ? nodo.getDato() : null;
    }
    @Override
    public void preOrden(Consumer<T> consumidor) {
        raiz.preOrden(n -> consumidor.accept(n.getDato()));
    }
    @Override
    public void inOrden(Consumer<T> consumidor) {
        raiz.inOrden(n -> consumidor.accept(n.getDato()));
    }
    @Override
    public void postOrden(Consumer<T> consumidor) {
        raiz.postOrden(n -> consumidor.accept(n.getDato()));
    }
    @Override
    public void vaciar() {
        raiz = null;
    }
    @Override
    public int grado(Comparable<T> nodo) {
        TNodoGenerico<T> encontrado = raiz.buscar(nodo);
        return encontrado != null ? encontrado.grado() : -1;
    }
    @Override
    public int altura(Comparable<T> nodo) {
        TNodoGenerico<T> encontrado = raiz.buscar(nodo);
        return encontrado != null ? encontrado.altura() : -1;
    }
}
