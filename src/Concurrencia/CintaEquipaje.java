/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
/**
 *
 * @author lafuente
 */
public class CintaEquipaje {
    
    private int max;
    private ArrayList<Maleta> maletas;
    private PrintWriter salida;
    private int pos = 0;
    
    String strDateFormat = "dd-MMM-yyyy hh:mm:ss a:";
    SimpleDateFormat fmt = new SimpleDateFormat(strDateFormat);
    
    Lock cerrojo = new ReentrantLock();
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
    
    public void logging (String mensaje){
        
        //Metodo para escribir en el log
        salida.write(mensaje + "\n");
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public String toString() {
        return "CintaEquipaje{" + "max=" + max + ", maletas=" + maletas + '}';
    }
    
    
    
}
