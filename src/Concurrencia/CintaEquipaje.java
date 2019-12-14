/**
 * Esta clase representa la cinta de equipaje donde los pasajeros dejan sus maletas.
 * 
 */
package Concurrencia;

import java.util.ArrayList;
import java.util.concurrent.locks.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CintaEquipaje {
    
    // Máximo de maletas que caben en la cinta
    private int max;
    // Array de maletas que guarda las maletas que se introducen a la cinta de equipaje
    private ArrayList<Maleta> maletas;
    // Stream para escribir el log
    private PrintWriter salida;
    private int pos = 0;
    
    // Formato de la fecha para ponerla en el log
    String strDateFormat = "dd-MMM-yyyy hh:mm:ss a:";
    SimpleDateFormat fmt = new SimpleDateFormat(strDateFormat);
    // Cerrojo para mantener la integridad del arraylist de maletas (recurso compartido)
    Lock cerrojo = new ReentrantLock();
    // Condiciones para que no se puedan introducir mas de 8 maletas en la cinta
    // Y poder avisar a los pasajeros y a los empleado cuando pueden seguir con su
    // funcionamiento
    Condition llena = cerrojo.newCondition();
    Condition vacia = cerrojo.newCondition();

    public CintaEquipaje(int max, ArrayList<Maleta> maletas) {
        this.max = max;
        this.maletas = maletas;
    }

    public CintaEquipaje(int max, PrintWriter salida) {
        this.max = max;
        this.salida = salida;
        this.maletas = new ArrayList<Maleta>();
    }

    public ArrayList<Maleta> getMaletas() {
        return maletas;
    }

    public void setMaletas(ArrayList<Maleta> maletas) {
        this.maletas = maletas;
    }

    /**
     * Método para dejar la maleta en la cinta de maletas
     * 
     * @param maleta Objeto malera a dejar en la cinta
     * @param pasajero Un string con el identificativo del pasajero
     */
    public void dejarMaleta(Maleta maleta, String pasajero){
        cerrojo.lock();
        try{
            while(maletas.size()==max){ 
                llena.await(); 
            }
            maletas.add(maleta);
            pos = pos + 1;
            Date ahora = new Date();
            String mensaje = new String(fmt.format(ahora) + pasajero + " deja la maleta " + maleta.getIdentificador() + " en la cinta.");
            System.out.println(mensaje);
            logging(mensaje);
            vacia.signal();
        }catch(Exception e){}
        finally{
            cerrojo.unlock();
        }
    }
    /**
     * Método para extraer una maleta de la cinta de equipaje.
     * 
     * @param empleado
     * @return Maleta Maleta que ha sido extraida de la cinta
     */
    public Maleta cogerMaleta(String empleado){
        Maleta maleta = null;
        cerrojo.lock();
        try{
            while(maletas.isEmpty()){
                vacia.await();
            }
            maleta = maletas.remove(0);
            pos = pos - 1;
            Date ahora = new Date();
            String mensaje = new String(fmt.format(ahora) + empleado + " coge la maleta " + maleta.getIdentificador() + " y la lleva al avión.");
            System.out.println(mensaje);
            logging(mensaje);
            llena.signal();
        }catch(Exception e){}
        finally{
            cerrojo.unlock();
        }
        return maleta;
    }
    
    /**
     * Método para escribir el contenido de la cinta de equipaje en la intefaz gráfica.
     * 
     * @return String Contenido de la cinta
     */
    public String contenidoCinta(){
        String contenido;
        contenido = "";
        
        if (!maletas.isEmpty()){
            for (int i=0; i<maletas.size(); i++){
                contenido = contenido + " " + maletas.get(i).getIdentificador();
                if (pos != 1 && pos<8){
                    contenido = contenido + ",";
                }
            }
        }
        return contenido;
    }
    
    /**
     * Método para escribir en el log.
     * @param mensaje Nueva entrada en el log
     */
    public void logging (String mensaje){
        
        //Metodo para escribir en el log
        salida.write(mensaje + "\n");
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public String toString() {
        return "CintaEquipaje{" + "max=" + max + ", maletas=" + maletas + '}';
    }
    
    
    
}
