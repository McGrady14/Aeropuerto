/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concurrencia;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.concurrent.locks.*;
import java.util.ArrayList;
import java.util.*;
/**
 *
 * @author lafuente
 */
public class Avion {
    
    private ArrayList<Maleta> maletas;
    private Lock cerrojo = new ReentrantLock();
    private PrintWriter salida;
    
    String strDateFormat = "dd-MMM-yyyy hh:mm:ss a:";
    SimpleDateFormat fmt = new SimpleDateFormat(strDateFormat);
    
    public Avion(PrintWriter salida){
        maletas = new ArrayList<>();
        this.salida = salida;
    }
    
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
    public String contenidoAvion(){
        String contenido;
        contenido = "";
        if (!maletas.isEmpty()){
            for (int i=0; i<maletas.size(); i++){
            contenido = contenido + " " + maletas.get(i).getIdentificador() + ",";
            }
        }
        return contenido;
    }
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
