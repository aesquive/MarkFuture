package markFuture;

import auxiliar.InversionSimulacionBean;
import auxiliar.SimulacionCapital;
import auxiliar.SimulacionMinistracion;
import base.Dao;
import calculador.vehiculo.DistribuidorModeloVehiculo;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import pojos.DatVeh;
import pojos.MdlVeh;
import pojos.PryVeh;
import pojos.VarVeh;
import util.Funciones;
import util.ParametrosMatrizBid;

/**
 *
 * @author alberto
 */
public class DistribuidorMarkFuture {

    

    private List<DistribuidorModeloVehiculo> distribuidores;
    private DistribuidorModeloVehiculo distribuidorGeneralProyectos;

    
    public DistribuidorMarkFuture(DistribuidorModeloVehiculo distribuidorModeloVehiculo){
        this.distribuidorGeneralProyectos=distribuidorModeloVehiculo;
    }
    
    public DistribuidorMarkFuture(List<DistribuidorModeloVehiculo> distribuidores) {
        this.distribuidores = distribuidores;
        distribuidorGeneralProyectos = new DistribuidorModeloVehiculo(sumarProyectos(distribuidores), true, 18, 12);
    }

    /**
     * @return the distribuidores
     */
    public List<DistribuidorModeloVehiculo> getDistribuidores() {
        return distribuidores;

    }

    /**
     * @param distribuidores the distribuidores to set
     */
    public void setDistribuidores(List<DistribuidorModeloVehiculo> distribuidores) {
        this.distribuidores = distribuidores;
    }

    public void llenarBeanInversion(InversionSimulacionBean aThis) {
        getDistribuidorGeneralProyectos().llenarBeanInversion(aThis);
    }

    public void modelarPrincipal() {
        getDistribuidorGeneralProyectos().modelarPrincipal();
    }

    public void llenarBeanGrafica(SimulacionMinistracion aThis) {
        getDistribuidorGeneralProyectos().llenarBeanGrafica(aThis);
    }

    public void llenarBeanSimulacionCapital(SimulacionCapital aThis) {
        getDistribuidorGeneralProyectos().llenarBeanSimulacionCapital(aThis);
    }

    public void modificarSimulacionCapital(SimulacionCapital aThis) {
        getDistribuidorGeneralProyectos().modificarSimulacionCapital(aThis);
    }

    public void generarEdoResultados() {
        getDistribuidorGeneralProyectos().generarEdoResultados();
    }

    public List<ParametrosMatrizBid> obtenerGraficas(List<String> atributos) {
        return getDistribuidorGeneralProyectos().obtenerGraficas(atributos);
    }

    private List<PryVeh> sumarProyectos(List<DistribuidorModeloVehiculo> distribuidores) {
        sumarDatosGenerales();
//        sumarDatosEtapas();
        return sacarProyectosDistribuidores();
    }

    private static List<PryVeh> sacarProyectos(int i) {
        Dao dao=new Dao();
        MdlVeh modelo = dao.getModelo(i);
        return Funciones.sacarProyectos(modelo);
    }
    
    public static void main(String[] args) {
        DistribuidorModeloVehiculo disMod=new DistribuidorModeloVehiculo(sacarProyectos(55), false, 18,12);
        List<DistribuidorModeloVehiculo> listDIs=new LinkedList<DistribuidorModeloVehiculo>();
        listDIs.add(disMod);
        DistribuidorMarkFuture dis=new DistribuidorMarkFuture(listDIs);
        dis.modelarPrincipal();
    }

    private void sumarDatosGenerales() {
        Set<Integer> numerosVars = llenarSet(1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 13, 14, 15, 16, 17, 18, 19, 22, 23);
        
        for (Integer idVarActual : numerosVars) {
            double sumaTotalVariable = 0;
            for (DistribuidorModeloVehiculo distActual : distribuidores) {
                sumaTotalVariable += sacarVariable(idVarActual, distActual);
            }
            for (DistribuidorModeloVehiculo dist : distribuidores) {
                modificarDatosProyectos(idVarActual, sumaTotalVariable, dist);
            }
        }
    }

    private double sacarVariable(int idVariable, DistribuidorModeloVehiculo distribuidorModeloVehiculo) {
        double suma = 0;
        for (PryVeh proyectoActual : distribuidorModeloVehiculo.getProyectos()) {
            Set<DatVeh> datVehs = proyectoActual.getDatVehs();
            for (DatVeh dato : datVehs) {
                VarVeh varVeh = dato.getVarVeh();
                if (varVeh != null && varVeh.getId() == idVariable) {
                    suma += (Double) castearVAlor(dato.getVal());
                }
            }
        }
        return suma / distribuidorModeloVehiculo.getProyectos().size();
    }

    private Object castearVAlor(String val) {
        Object regreso = null;
        if (val.contains("%")) {
            regreso = val.substring(0, val.length() - 1);
        } else if (val.contains(",")) {
            regreso = regreso != null ? regreso.toString().replace(",", "") : val.replace(",", "");
        } else {
            regreso = val;
        }
        try {
            return Double.parseDouble(String.valueOf(regreso));

        } catch (NumberFormatException num) {
            return val;
        }

    }


    private Set<Integer> llenarSet(int... numeros) {
        Set<Integer> nums = new HashSet<Integer>();
        for (int t = 0; t < numeros.length; t++) {
            nums.add(numeros[t]);
        }
        return nums;
    }

    private void modificarDatosProyectos(Integer idVarActual, double sumaTotalVariable, DistribuidorModeloVehiculo dist) {
        
        for (PryVeh proyecto : dist.getProyectos()) {
            Set<DatVeh> datVehs = proyecto.getDatVehs();
            for (DatVeh dato : datVehs) {
                VarVeh varVeh = dato.getVarVeh();
                if (varVeh != null && varVeh.getId().toString().equals(idVarActual.toString())
                    ) {
                    dato.setVal(String.valueOf(sumaTotalVariable));
                }
            }
        }
    }

    private List<PryVeh> sacarProyectosDistribuidores() {
        List<PryVeh> todos=new LinkedList<PryVeh>();
        int indice=1;
        for(DistribuidorModeloVehiculo dist:distribuidores){
            for(PryVeh pry:dist.getProyectos()){
                pry.setNomPry(pry.getNomPry()+"("+indice+")");
                todos.add(pry);
            }
            indice++;
        }
        return todos;
    }

    /**
     * @return the distribuidorGeneralProyectos
     */
    public DistribuidorModeloVehiculo getDistribuidorGeneralProyectos() {
        return distribuidorGeneralProyectos;
    }

    /**
     * @param distribuidorGeneralProyectos the distribuidorGeneralProyectos to set
     */
    public void setDistribuidorGeneralProyectos(DistribuidorModeloVehiculo distribuidorGeneralProyectos) {
        this.distribuidorGeneralProyectos = distribuidorGeneralProyectos;
    }
}
