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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author David
 */
public class Transposicion {
    String clave="";
    String texto="";
    public Transposicion(String clav){
        this.clave=clav;
    }
    
    
    public void Encriptar() throws IOException{
        
         /// Leer el  texto plano
        
        String cadena, anterior="",text="";
        FileReader f = null;
        try {f = new FileReader("textoplano.txt");} catch (FileNotFoundException ex) {}
        
        try (BufferedReader b = new BufferedReader(f)) {
            while((cadena = b.readLine())!=null) {
                text=text+cadena;
            }
            b.close();
        } catch (IOException ex) {
          }
        System.out.println("\tTEXTO\n"+text);
        
        /// determinar el tamanio de la  matriz
        
        int columnas=this.clave.length();
        double filas= Math.ceil(((float)(text.length())/(float)(this.clave.length())));
        System.out.println("DIVISION:"+(float)(text.length())/(float)(this.clave.length())+"TAMANIO TEXTO:"+text.length());
        int filasEntero= (int)(filas+2);
        Object [][] matriz=new Object[filasEntero][columnas];
        System.out.println("Filas:"+filasEntero+"Columnas:"+columnas);
        
        
        
        
        
        // llenar la primea fila de la matriz con los caracteres de la clave
        List listaClaves=new ArrayList();
        for(int i=0;i<columnas;i++){
            matriz[0][i]=clave.charAt(i);
            listaClaves.add(matriz[0][i]);
        }
        // ordenar la lista de caracateres de la clave
        Collections.sort(listaClaves);
        
        //System.out.println("ListaCLAVES:"+listaClaves.get(0));
        //System.out.println("ListaCLAVES:"+listaClaves.get(1));
        //System.out.println("ListaCLAVES:"+listaClaves.get(2));
        //System.out.println("ListaCLAVES:"+listaClaves.get(3));
        
        // llenar la segunda fila con los valores del orden de los caracteres
         boolean bandera=false;
         int comparado=-1;
         for(int i=0;i<listaClaves.size();i++){
             for(int j=0;j<listaClaves.size();j++){
                 if((char)listaClaves.get(i)==(char)matriz[0][j] && bandera==false &&comparado!=j){ 
                     matriz[1][j]=i+1;
                     bandera=true;
                     comparado=j;
                 }
             }
             bandera=false;
         }
         
  
         
         
         
         // llenar las demas filas de la matriz con los caracteres del texto plano
         int cont=2;
         String filaCaracteres="";
         for(int i=0;i<text.length();i++){
             
             filaCaracteres+=text.charAt(i);
             if(filaCaracteres.length()==columnas){
                for(int j=0;j<columnas;j++){
                    matriz[cont][j]=filaCaracteres.charAt(j);
                    
                }
                cont=cont+1;
                filaCaracteres="";
             }
             
         }
         // llenar la fila en la que los caraacteres no llenan toda la fila
          System.out.println("filaCaracteres->"+filaCaracteres);
          if(filaCaracteres.length()>=1){
               String abecedario="abcdefghijklmnopqrstuvwxyz";
                int cont2=0;
                for(int j=0;j<columnas;j++){
                      if(j<filaCaracteres.length())
                          matriz[cont][j]=filaCaracteres.charAt(j);
                      else{
                          matriz[cont][j]=abecedario.charAt(cont2);
                          cont2++;
                      }
                }
          }
          
           /// Cifrar el texto, es decir concatenar segun los valores de la segunda fila
          String textoCifrado="";
           for(int i=0;i<listaClaves.size();i++){
             for(int j=0;j<listaClaves.size();j++){
                 if((char)listaClaves.get(i)==(char)matriz[0][j]){ 
                     for(int k=2;k<filasEntero;k++){
                         textoCifrado+=matriz[k][j];
                     }
                 }
             }
         }
         
        System.out.println("TextoCifrado->"+textoCifrado.toUpperCase());
         
         /// Crear el archivo encriptado
        BufferedWriter archivoEncriptado=new BufferedWriter(new FileWriter("textoEncriptado.txt"));
        archivoEncriptado.write(textoCifrado.toUpperCase());
        archivoEncriptado.close();
         
         
        // imprimir matriz
        System.out.println("\tMATRIZ");
        for(int i=0;i<filasEntero;i++){
            for(int j=0;j<columnas;j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
