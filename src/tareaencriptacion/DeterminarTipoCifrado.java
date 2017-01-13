/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaencriptacion;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author David
 */
public class DeterminarTipoCifrado {
    private String criptograma;
    public static boolean ASC = true;
    public static boolean DESC = false;

    /*public DeterminarTipoCifrado(String criptograma) {
        this.criptograma = criptograma;
    }*/
    
    

    public Map saveFrecuencias() {
        
        /// Leer archivo cifrado
    
        
        String cad, anterior="",textCifra="";
        FileReader f = null;
        try {f = new FileReader("textoEncriptado.txt");} catch (FileNotFoundException ex) {}
        
        try (BufferedReader b = new BufferedReader(f)) {
            while((cad = b.readLine())!=null) {
                textCifra=textCifra+cad;
            }
            b.close();
        } catch (IOException ex) {
          }
        //System.out.println("\tTEXTO\n"+textCifra);
        
        /// Guardar las secuencias
        Map<String, Integer> frecuencia = new HashMap<>();

        for (int i = 0; i < textCifra.length(); i++) {
            Character letra = textCifra.charAt(i);
            if (frecuencia.containsKey(Character.toString(letra))) {
                frecuencia.put(Character.toString(letra), frecuencia.get(Character.toString(letra)) + 1);
            } else {
                frecuencia.put(Character.toString(letra), 1);
            }
        }
        return frecuencia;

    }

    public Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order) {

        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // Ordena la lista de los  valores
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                if (order) {
                    return o1.getValue().compareTo(o2.getValue());
                } else {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Caracter : '" + entry.getKey() + "'  Frecuencia : " + entry.getValue());
        }
    }
    
    /// Ordenar el mapa  mediante las frecuencias
    public int getTipoCifrado(Map unsortMap) {
        Map<String, Integer> sortedMapDesc = (Map<String, Integer>) sortByComparator(unsortMap, DESC);
        System.out.println("\n\tFRECUENCIAS DE CARACTERES");
        printMap(sortedMapDesc);

        
        Set conjunto = sortedMapDesc.keySet();
        Object arreglo[] = conjunto.toArray();
        String [] letras= new String[4];
        letras[0]="E";
        letras[1]="O";
        letras[2]="S";
        letras[3]="A";
        
        //COMPARACION PARA SABER SI EL TEXTO FUE CIFRADO POR TRANSPOSICION O POR SUSTITUCION
        int contarRepeticion=0;
         for(int i=0;i<letras.length;i++){
             for(int j=0;j<4;j++){
                 if(letras[i].equals((String)arreglo[j])){
                     contarRepeticion++;
                 }
             }
         }
         System.out.println("REPETICION->"+contarRepeticion);
         if(contarRepeticion>=3){
             System.out.println("TEXTO CIFRADO POR TRANSPOSICION");
             return 1;
         }
         else{
             System.out.println("Texto CIFRADO POR SUSTICTUCION");
             return 2;
         }
    } 
}
