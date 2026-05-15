package ucu.edu.aed.tda.generic_trie;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class NodoGenerico<T extends Comparable<T>> implements TNodoGenerico<T> {
    private  T dato;
    private  List<NodoGenerico<T>> hijos;

    public NodoGenerico(T dato) {
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    @Override
    public T getDato() {
        return dato;
    }

    @Override
    public boolean agregarHijo(T padre, T hijo) {
        if (dato.compareTo(padre) == 0) {
            hijos.add(new NodoGenerico<>(hijo));
            return true;
        }
        for (NodoGenerico<T> h : hijos) {
            if (h.agregarHijo(padre, hijo)) return true;
        }
        return false;
    }

    @Override
    public TNodoGenerico<T> eliminar(Comparable<T> criterio) {
        hijos.removeIf(h -> criterio.compareTo(h.getDato()) == 0);
        for (NodoGenerico<T> h : hijos) {
            h.eliminar(criterio);
        }
        return this;
    }

    @Override
    public TNodoGenerico<T> buscar(Comparable<T> criterio) {
        if (criterio.compareTo(dato) == 0) return this;
        for (NodoGenerico<T> h : hijos) {
            TNodoGenerico<T> encontrado = h.buscar(criterio);
            if (encontrado != null) return encontrado;
        }
        return null;
    }

    @Override
    public TNodoGenerico<T> obtenerPadre(Comparable<T> criterio) {
        for (NodoGenerico<T> h : hijos) {
            if (criterio.compareTo(h.getDato()) == 0) return this;
            TNodoGenerico<T> padre = h.obtenerPadre(criterio);
            if (padre != null) return padre;
        }
        return null;
    }

    @Override
    public void preOrden(Consumer<TNodoGenerico<T>> consumidor) {
        consumidor.accept(this);
        for (NodoGenerico<T> h : hijos) h.preOrden(consumidor);
    }

    @Override
    public void inOrden(Consumer<TNodoGenerico<T>> consumidor) {
        if (!hijos.isEmpty()) hijos.get(0).inOrden(consumidor);
        consumidor.accept(this);
        for (int i = 1; i < hijos.size(); i++) hijos.get(i).inOrden(consumidor);
    }

    @Override
    public void postOrden(Consumer<TNodoGenerico<T>> consumidor) {
        for (NodoGenerico<T> h : hijos) h.postOrden(consumidor);
        consumidor.accept(this);
    }

    @Override
    public int altura() {
        if (hijos.isEmpty()) return 0;
        int max = 0;
        for (NodoGenerico<T> h : hijos) {
            max = Math.max(max, h.altura());
        }
        return 1 + max;
    }

    @Override
    public int grado() {
        return hijos.size();
    }

    @Override
    public void vaciar() {
        hijos.clear();
    }

    @Override
    public List<T> obtenerHijos() {
        List<T> lista = new ArrayList<>();
        for (NodoGenerico<T> h : hijos) lista.add(h.getDato());
        return lista;
    }
}