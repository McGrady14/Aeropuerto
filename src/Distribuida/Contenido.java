/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Distribuida;

import Concurrencia.CintaEquipaje;
import Concurrencia.Avion;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
/**
 *
 * @author lafuente
 */
public class Contenido extends UnicastRemoteObject implements InterfazContenido{
    public Contenido() throws RemoteException{}
    private CintaEquipaje cinta;
    private Avion avion;
    
    @Override
    public String contenidoAvion()throws RemoteException{
        String contenido;
        contenido = avion.contenidoAvion();
        return contenido;
    }
    @Override
    public String contenidoCinta()throws RemoteException{
        String contenido;
        contenido = cinta.contenidoCinta();
        return contenido;
    }

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
