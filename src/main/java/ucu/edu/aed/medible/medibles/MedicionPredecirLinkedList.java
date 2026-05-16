package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.LinkedList;
import java.util.List;

public class MedicionPredecirLinkedList extends Medible<String> {

    private final LinkedList<String> list;

    public MedicionPredecirLinkedList(LinkedList<String> list) {
        this.list = list;
    }

    @Override
    public void ejecutar(int repeticiones, String prefijo) {
        for (int i = 0; i < repeticiones; i++) {
            List<String> resultado = new LinkedList<>();
            for (String palabra : list) {
                if (palabra.startsWith(prefijo)) {
                    resultado.add(palabra);
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.list;
    }
}
