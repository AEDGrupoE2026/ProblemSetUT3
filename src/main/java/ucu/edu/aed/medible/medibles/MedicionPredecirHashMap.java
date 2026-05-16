package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MedicionPredecirHashMap extends Medible<String> {

    private HashMap<String, String> hashMap;

    public MedicionPredecirHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void ejecutar(int repeticiones, String prefijo) {
        for (int i = 0; i < repeticiones; i++) {
            List<String> resultado = new LinkedList<>();

            for (String palabra : hashMap.keySet()) {
                if (palabra.startsWith(prefijo)) {
                    resultado.add(palabra);
                }
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.hashMap;
    }
}
