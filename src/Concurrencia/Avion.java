/**
 * Esta clase representa el avion dentro del aeropuerto.
 * Se encarga de mantener los objetos maleta dentro del avion 
 * a medida que van saliendo de la cinta.
 */
package Concurrencia;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.concurrent.locks.*;
import java.util.ArrayList;
import java.util.*;

public class Avion {
    
    // Array para guardar las maletas
    private ArrayList<Maleta> maletas;
    // Semaforo para que las maletas se dejen dentro del avion de una en una
    private Lock cerrojo = new ReentrantLock();
    private PrintWriter salida;
    
    // Formato de la fecha
    String strDateFormat = "dd-MMM-yyyy hh:mm:ss a:";
    SimpleDateFormat fmt = new SimpleDateFormat(strDateFormat);
    
   /**
    * Constructor
    * 
    * @param salida 
    */ 
    public Avion(PrintWriter salida){
        maletas = new ArrayList<>();
        this.salida = salida;
    }
    /**
     * Método para dejar la maleta en el avión 
     * 
     * @param maleta
     * @param empleado 
     */
    public void dejarMaleta(Maleta maleta, String empleado){
        cerrojo.lock();
        try{
            maletas.add(maleta);
            Date ahora = new Date();
            String mensaje = new String(fmt.format(ahora) + empleado + " deja la maleta " + maleta.getIdentificador() + " en el avión.");
            logging(mensaje);
            System.out.println(mensaje);
        }catch(Exception e){}
        finally{
            cerrojo.unlock();
        }
    }
    /**
     * Método para imprimir el contenido del avion en la interfaz gráfica.
     * 
     * @return String
     */
    public String contenidoAvion(){
        String contenido;
        contenido = "";
        for (int i=0; i<maletas.size(); i++){
        contenido = contenido + " " + maletas.get(i).getIdentificador() + ",";
        }
        return contenido;
    }
    /**
     * Método para escribir en el log 
     * @param mensaje 
     */
    public void logging (String mensaje){
        salida.write(mensaje + "\n");
    }

    public ArrayList<Maleta> getMaletas() {
        return maletas;
    }

    public void setMaletas(ArrayList<Maleta> maletas) {
        this.maletas = maletas;
    }
    
    
}