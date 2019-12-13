/**
 * Clase que representa a cada empleado del aeropuerto.
 * Esta clase hereda de la clase Thread.
 */
package Concurrencia;

import InterfazGrafica.Principal;

public class Empleado extends Thread{
    
    // Identificador del empleado
    private String identificador;
    // Variable para guardar el objeto de la cinta de equipaje (recurso compartido)
    private CintaEquipaje cinta;
    // Variable para guardar el objeto del avion creado en el main
    private final Avion avion;
    // Variable para guardar el objeto  de la interfaz gráfica 
    private Principal ventana;
    // Representa la acción esta realizando el empleado
    private String estado;
    // Variable para guardar el objeto para parar y reanudar los hilos de los empleados
    private Paso paso;
    // Numero del identificador
    private int id;
    
    public Empleado(String identificador, CintaEquipaje cinta, Avion avion, Principal ventana, Paso paso) {
        this.identificador = "Empleado" + identificador;
        id = Integer.parseInt(identificador);
        this.cinta = cinta;
        this.avion = avion;
        this.ventana = ventana;
        this.paso = paso;
    }

    /**
     * Metodo de inicializacion de los hilos empleado.
     * 
     */
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
    /**
     * Método para imprimir el estado de cada empleado en la intefaz gráfica
     */
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
    // Getters y Setters
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
    
}