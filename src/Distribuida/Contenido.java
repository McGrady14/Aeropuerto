/**
 * Objeto subido al registro por el servidor. 
 * Contiene los servicios utilizados por el cliente.
 */
package Distribuida;

import Concurrencia.CintaEquipaje;
import Concurrencia.Avion;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class Contenido extends UnicastRemoteObject implements InterfazContenido{
    
// Variable que guarda el objeto de la cinta de equipaje
    private CintaEquipaje cinta;
    // Variable que guarda el objeto del avión
    private Avion avion;    
    // Constructor
    public Contenido() throws RemoteException{}
    
    
    /**
     * Método que implementa uno de los servicios expuestos en la interfaz compartida.
     * Retorna el contenido del avión.
     * @return
     * @throws RemoteException 
     */
    @Override
    public String contenidoAvion()throws RemoteException{
        String contenido;
        contenido = avion.contenidoAvion();
        return contenido;
    }
    /**
     * Método que implementa uno de los servicios expuesto en la interfaz compartida.
     * Retorna el contenido de la cinta de equipaje.
     * @return String
     * @throws RemoteException 
     */
    @Override
    public String contenidoCinta()throws RemoteException{
        String contenido;
        contenido = cinta.contenidoCinta();
        return contenido;
    }
    
    // Getters y Setters
    public CintaEquipaje getCinta() {
        return cinta;
    }

    public void setCinta(CintaEquipaje cinta) {
        this.cinta = cinta;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }
    
    
    
}
