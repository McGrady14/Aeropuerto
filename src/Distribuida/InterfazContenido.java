/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuida;


import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author lafuente
 */
public interface InterfazContenido extends Remote{
    
    String contenidoCinta() throws RemoteException;
    String contenidoAvion() throws RemoteException;
    
}
