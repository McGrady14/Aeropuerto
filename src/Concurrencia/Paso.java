/**
 * Clase con los metodos necesarios para poder parar y reanudar los hilos empleados.
 */
package Concurrencia;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Paso {
    // Array para ver si los hilos estan parado o no 
    // False = no parado
    // True = parado
    private boolean[] cerrado = new boolean[]{false,false};
    // Semáforo para controlar la integridad del array para controlar la ejecución de los hilos
    private Lock cerrojo = new ReentrantLock();
    private Condition parar = cerrojo.newCondition();
    
    /**
     * Método para ver si se ha pulsado el botóm para parar al empleado.
     * 
     * @param empleado 
     */
    public void mirar(int empleado)
    {
        try
        {
            cerrojo.lock();
            while(cerrado[empleado - 1])
            {
                try
                {
                    parar.await();
                } catch(InterruptedException ie){ }
            }
        }
        finally
        {
            cerrojo.unlock();
        }
    }
    
    /**
     * Método para reanudar el hilo del empleado concreto.
     * 
     * @param empleado 
     */
    public void abrir(int empleado)
    {
        try
        {
            cerrojo.lock();
            cerrado[empleado]=false;
            parar.signal();
        }
        finally
        {
            cerrojo.unlock();
        }
    }
    /**
     * Método para parar a un empleado concreto.
     * 
     * @param empleado 
     */
    public void cerrar(int empleado)
    {
        try
        {
            cerrojo.lock();
            cerrado[empleado]=true;
            System.out.println("Empleado " + empleado + " parado");
        }
        finally
        {
            cerrojo.unlock();
        }
    }
}