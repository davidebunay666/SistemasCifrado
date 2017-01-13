/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaencriptacion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author David
 */
public class Sustitucion {
  
    public void encriptar(String clave) throws IOException{
        
       /// Leer el  texto plano
        
        String cont, anterior="",text="";
        int claveEntera=Integer.parseInt(clave) ;
        FileReader f = null;
        try {f = new FileReader("textoplano.txt");} catch (FileNotFoundException ex) {}
        
        try (BufferedReader b = new BufferedReader(f)) {
            while((cont = b.readLine())!=null) {
                text=text+cont;
            }
            b.close();
        } catch (IOException ex) {
          }
        System.out.println("\tTEXTO\n"+text);
        
        
        
       // realizar la sustitucion de cada caracter segun el valor de la clave
        String textoaux="";
        for (int i=0;i<text.length();i++){
            int aux=0,valor=0;
            aux=text.charAt(i);
            if(aux>96 && aux<123){
                     
                valor=aux+claveEntera;
                while(valor>122){
                    valor=valor-(122-97+1);
                }
            }
            else{
                valor=aux;
            }
           
            textoaux+= (char) (valor);
        }
        
        
        /// Crear el archivo encriptado
        BufferedWriter archivoEncriptado=new BufferedWriter(new FileWriter("textoEncriptado.txt"));
        archivoEncriptado.write(textoaux.toUpperCase());
        archivoEncriptado.close();
        
        
        System.out.println("TEXTO CIFRADO\n"+textoaux);
       
    }

}
