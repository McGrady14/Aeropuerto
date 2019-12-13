/**
 * Clase que representa las maletas de los pasajeros.
 * 
 */
package Concurrencia;


public class Maleta {
    // Nombre del identificador de la maleta
    private String identificador;
    
    public Maleta(String id){
        identificador = id;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    
    
}