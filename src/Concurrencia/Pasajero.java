/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concurrencia;

import InterfazGrafica.Principal;


import java.util.ArrayList;
/**
 *
 * @author lafuente
 */
public class Pasajero extends Thread{
    
    private static final int numMaletas = 2;
    private String identificador;
    private CintaEquipaje cinta;
    private ArrayList<Maleta> maletas;
    private Principal ventana;
    
    public Pasajero(String id, CintaEquipaje cinta, Principal ventana){
        identificador = "Pasajero" + id;
        this.cinta = cinta;
        this.maletas = new ArrayList<>();
        this.ventana = ventana;
    }
    public Pasajero(String id, CintaEquipaje cinta, ArrayList<Maleta> maletas){
        identificador = "Pasajero" +id;
        this.cinta = cinta;
        this.maletas = maletas;
    }

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
}
