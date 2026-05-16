package ucu.edu.aed.medible.medibles;

import ucu.edu.aed.medible.lib.Medible;

import java.util.List;
import java.util.TreeMap;

public class MedicionBuscarTreeMap extends Medible<List<String>> {

    private TreeMap<String, String> treeMap;

    public MedicionBuscarTreeMap(TreeMap<String, String> treeMap) {
        this.treeMap = treeMap;
    }

    @Override
    public void ejecutar(int repeticiones, List<String> palabras) {
        for (int i = 0; i < repeticiones; i++) {
            for (String palabra : palabras) {
                treeMap.containsKey(palabra);
            }
        }
    }

    @Override
    public Object getObjetoAMedirMemoria() {
        return this.treeMap;
    }
}
