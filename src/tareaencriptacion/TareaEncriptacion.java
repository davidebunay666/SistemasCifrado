/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareaencriptacion;

import java.io.IOException;

/**
 *
 * @author David
 */
public class TareaEncriptacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //int clave=20;
      
        
        //Sustitucion s= new Sustitucion();
        //s.encriptar(clave);
        String clave="MEGABUCK";
        Transposicion t=new Transposicion(clave);
        t.Encriptar();
    }
}
