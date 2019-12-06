/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuida;

import InterfazGrafica.Visualizacion;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

/**
 *
 * @author lafuente
 */
public class Cliente {
     public static void main(String args[]){
         
        Visualizacion ventana = new Visualizacion();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Inicio: ");
            reader.readLine();
        }catch (Exception e){
            System.out.println("Error: " + e);
        }
        
        InterfazContenido cont = null;
        System.out.println("Empieza Cliente");
           try{
               cont = (InterfazContenido) Naming.lookup("//127.0.0.1/Contenido");
               
           }catch (Exception e){
               System.out.println("Error: " + e);
           }
           while(true){
               try{
                   
//                    System.out.println("Contenido Cinta: " + cont.contenidoCinta());
//                    System.out.println("Contenido Avion: " + cont.contenidoAvion());
                    ventana.contenidoCinta(cont.contenidoCinta());
                    ventana.contenidoAvion(cont.contenidoAvion());
                }catch (Exception e){
                    System.out.println("Error: " + e);
                }
         }
     }
    
    
}
