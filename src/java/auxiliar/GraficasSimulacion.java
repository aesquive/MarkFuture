/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliar;

import calculador.vehiculo.DistribuidorModeloVehiculo;
import java.util.List;
import markFuture.DistribuidorMarkFuture;
import util.ParametrosMatrizBid;

/**
 *
 * @author alberto
 */
public class GraficasSimulacion{

    
    private DistribuidorMarkFuture distribuidor;
    private List<String> atributos;
    private List<ParametrosMatrizBid> rutas;
    private String numeroModelo;
    
    
    public GraficasSimulacion(DistribuidorMarkFuture distribuidor,List<String> atributos){
        this.distribuidor=distribuidor;
        this.atributos=atributos;
        llenarGraficas();
    }

    /**
     * @return the atributos
     */
    public List<String> getAtributos() {
        return atributos;
    }

    /**
     * @param atributos the atributos to set
     */
    public void setAtributos(List<String> atributos) {
        this.atributos = atributos;
    }


    /**
     * @return the rutas
     */
    public List<ParametrosMatrizBid> getRutas() {
        return rutas;
    }

    /**
     * @param rutas the rutas to set
     */
    public void setRutas(List<ParametrosMatrizBid> rutas) {
        this.rutas = rutas;
    }

    private void llenarGraficas() {
        distribuidor.generarEdoResultados();
        setRutas(distribuidor.obtenerGraficas(atributos));
    }
    
    
    
}
