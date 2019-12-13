/**
 * Interfaz compartida que expone los servicios que provee el servidor.
 * 
 */
package Distribuida;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazContenido extends Remote{
    
    /**
     * Método para devolver el contenido de la cinta de equipaje.
     * 
     * @return String
     * @throws RemoteException 
     */
    String contenidoCinta() throws RemoteException;
    /**
     * Método para devolver el contenido del avión.
     * 
     * @return String
     * @throws RemoteException 
     */
    String contenidoAvion() throws RemoteException;
    
}
