/**
 * Clase que representa a los pasajeros que acuden al aeropuerto.
 * Esta clase hereda de la clase Thread.
 */
package Concurrencia;

import InterfazGrafica.Principal;


import java.util.ArrayList;

public class Pasajero extends Thread{
    // Numero de maletas de cada pasajero
    private static final int numMaletas = 2;
    // Nombre identificativo de cada pasajero
    private String identificador;
    // Variable para guardar el objeto del avion creado en el main
    private CintaEquipaje cinta;
    // Array de maletas que guarda las maletas que se introducen a la cinta de equipaje.
    private ArrayList<Maleta> maletas;
    
    public Pasajero(String id, CintaEquipaje cinta){
        identificador = "Pasajero" + id;
        this.cinta = cinta;
        this.maletas = new ArrayList<>();
    }
    public Pasajero(String id, CintaEquipaje cinta, ArrayList<Maleta> maletas){
        identificador = "Pasajero" +id;
        this.cinta = cinta;
        this.maletas = maletas;
    }

    /**
     * Método de inicializacion de los hilos pasajero.
     * 
     */
    @Override
    public void run(){
        Maleta maleta;
        for (int i=0; i<numMaletas; i++){
            maleta = new Maleta(identificador + "-M" + String.valueOf(i+1));
            try
            {
                sleep((int)(500+500*Math.random()));
            } catch(InterruptedException e){  }
            cinta.dejarMaleta(maleta, identificador);            
        }
    }
    // Getters y Setters 
    public CintaEquipaje getCinta() {
        return cinta;
    }

    public void setCinta(CintaEquipaje cinta) {
        this.cinta = cinta;
    }
    
    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public ArrayList<Maleta> getMaletas() {
        return maletas;
    }

    public void setMaletas(ArrayList<Maleta> maletas) {
        this.maletas = maletas;
    }
    
    @Override
    public String toString() {
        return "Pasajero{" + "identificador=" + identificador + ", maletas=" + maletas + '}';
    }
    
    
}