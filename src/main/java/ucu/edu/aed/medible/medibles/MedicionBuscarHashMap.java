package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.HashMap;
import java.util.List;

public class MedicionBuscarHashMap extends Medible<List<String>> {

    private HashMap<String, String> hashMap;

    public MedicionBuscarHashMap(HashMap<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> palabras) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : palabras) {
                hashMap.containsKey(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.hashMap;
    }
}
