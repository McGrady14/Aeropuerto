/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.util.ArrayList;
import Concurrencia.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import InterfazGrafica.Principal;
/**
 *
 * @author lafuente
 */
public class Aeropuerto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int numPasajeros = 40;
        
        try {
            String strDateFormat = "dd-MMM-yyyy hh:mm:ss a:";
            SimpleDateFormat fmt = new SimpleDateFormat(strDateFormat);
            
            // Archivo de log
            Date ahora = new Date();
            PrintWriter salida = new PrintWriter(new BufferedWriter(new FileWriter("log.txt")));
            
            // Creamos el avion y la cinta de equipaje
            CintaEquipaje cinta = new CintaEquipaje(8, salida);
            Avion avion = new Avion(salida);
            Paso paso = new Paso();
            Principal ventana = new Principal(paso);
            // Pasajeros
            Pasajero pasajero;
            ArrayList<Pasajero> pasajeros = new ArrayList<>();
            for (int i=0; i<numPasajeros; i++){
                pasajero = new Pasajero(String.valueOf(i+1), cinta, ventana);
                pasajeros.add(pasajero);
            }
            
            
            // Empleados
            Empleado empleado1 = new Empleado("1", cinta, avion, ventana, paso);
            Empleado empleado2 = new Empleado("2", cinta, avion, ventana, paso);

            // Iniciamos el funcionamiento
            salida.write("----------- Simulacion del funcionamiento de un aeropuerto ----------\n");
            salida.write(fmt.format(ahora) +"Apertura del aeropuerto\n");
            System.out.print("----------- Simulacion del funcionamiento de un aeropuerto ----------\n");
            System.out.print("Apertura del aeropuerto\n");
            for (int i=0; i<pasajeros.size(); i++){
                pasajeros.get(i).start();
            }
            empleado1.start();
            empleado2.start();
            
            // Esperamos a que acaben todos los pasajeros 
            for (int i=0; i<pasajeros.size(); i++){
                try{
                    pasajeros.get(i).join();
                }catch (Exception e) {}
                
            }
            
            boolean terminado = false;
            do{
                System.out.print("");
                if(avion.getMaletas().size() == numPasajeros * 2){
                    terminado = true;
                  }
                if (terminado == true){
                  // Finalizamos el funcionamiento del aeropuerto
                  Date fin = new Date();
                  salida.write(fmt.format(fin) +"Cierre del aeropuerto");
                  System.out.print("Cierre del aeropuerto\n");
                }
            }while (terminado == false);
            // Cerramos el stream
            salida.close();
        } catch (IOException ioe) {
            System.out.println("Error IO: " + ioe.toString());
        }
        
       
        
    }
    
}
