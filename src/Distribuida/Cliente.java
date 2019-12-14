/**
 * Clase cliente.
 * Utiliza el objeto subido para por el servidor.
 */
package Distribuida;

import InterfazGrafica.Visualizacion;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;


public class Cliente {
     public static void main(String args[]){
        // Interfaz gráfica 
        Visualizacion ventana = new Visualizacion();    
        // Interfaz compartida entre el servidor y el cliente
        InterfazContenido cont = null;
        System.out.println("Empieza Cliente");
           try{
               // Descargamos el objeto compartido
               cont = (InterfazContenido) Naming.lookup("//127.0.0.1/Contenido");
               
           }catch (Exception e){
               System.out.println("Error: " + e);
           }
           while(true){
               try{
                   // Utilizamos el servicio
                    ventana.contenidoCinta(cont.contenidoCinta());
                    ventana.contenidoAvion(cont.contenidoAvion());
                }catch (Exception e){
                    System.out.println("Error: " + e);
                }
         }
     }
    
    
}
