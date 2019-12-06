/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Concurrencia;

import InterfazGrafica.Principal;
/**
 *
 * @author lafuente
 */
public class Empleado extends Thread{
    
    private String identificador;
    private CintaEquipaje cinta;
    private final Avion avion;
    private Principal ventana;
    private String estado;
    private Paso paso;
    private int id;
    
    public Empleado(String identificador, CintaEquipaje cinta, Avion avion, Principal ventana, Paso paso) {
        this.identificador = "Empleado" + identificador;
        id = Integer.parseInt(identificador);
        this.cinta = cinta;
        this.avion = avion;
        this.ventana = ventana;
        this.paso = paso;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public CintaEquipaje getCinta() {
        return cinta;
    }

    public void setCinta(CintaEquipaje cinta) {
        this.cinta = cinta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        return "Empleado{" + "identificador=" + identificador + ", cinta=" + cinta + '}';
    }
    
    @Override
    public void run(){
        Maleta maleta;
        while (true){
            
            maleta = cinta.cogerMaleta(identificador);
            ventana.contenidoCinta(cinta.contenidoCinta());
            estado = "Llevando " + maleta.getIdentificador() + " al avión";
            ponerContenidoEmpleado();
            paso.mirar(id);
            try
            {
                sleep((int)(400+700*Math.random()));
            } catch(InterruptedException e){ }
            avion.dejarMaleta(maleta, identificador);
            ventana.contenidoAvion(avion.contenidoAvion());
            estado = "Volviendo a la cinta de maletas";
            ponerContenidoEmpleado();
            paso.mirar(id);
            try
            {
                sleep((int)(400+700*Math.random()));
            } catch(InterruptedException e){ }
            
        }
        
        
    }
    public void ponerContenidoEmpleado(){
            
            switch(identificador){
                case "Empleado1":
                    ventana.contenidoEmpleado1(estado);
                    break;
                case "Empleado2":
                    ventana.contenidoEmpleado2(estado);
                    break;
                default:
                    ventana.contenidoEmpleado1("");
                    ventana.contenidoEmpleado2("");
                    break;
            }
        }
}