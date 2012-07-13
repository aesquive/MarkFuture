package beans.Simulacion;

import auxiliar.CandleBean;
import auxiliar.GraficasSimulacion;
import auxiliar.InversionSimulacionBean;
import auxiliar.SimulacionCapital;
import auxiliar.SimulacionMinistracion;
import auxiliar.SimuladorGeneral;
import base.Dao;
import calculador.vehiculo.DistribuidorModeloVehiculo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import markFuture.DistribuidorMarkFuture;
import pojos.MdlVeh;
import pojos.Usu;
import util.CapsulaMarkFuture;
import util.Funciones;

/**
 *
 * @author Quants
 */
public class GeneralSimulacion {

    private static String sufijo = "/home/alberto/apache-tomcat-7.0.14/webapps/Irradius/";
    public static String delimitadorArchivos = "/";
    private DistribuidorMarkFuture distribuidor;
    
    private InversionSimulacionBean inversionSimulacionBean;
    private GraficasSimulacion graficasBalance;
    private GraficasSimulacion graficasFinancieras;
    private GraficasSimulacion graficasParametricas;
    private SimulacionMinistracion simulacionMinistracionBean;
    private CandleBean candleBean;
    private SimulacionCapital simulacionCapital;
    
    private SimuladorGeneral simuladorGeneral;
    
    private List<MdlVeh> modelos;
    private List<Double> WOES;
    
    
    private Usu usu;
  
    public GeneralSimulacion() {
//       parseLinea("55-.25-8,55-.25-8,55-.25-8,55-.25-8");
        System.out.println("termine aqui");
        sacarProyectoActual();
        System.out.println("genere dist");
        distribuidor.modelarPrincipal();
        this.inversionSimulacionBean = new InversionSimulacionBean(distribuidor);
        this.simulacionMinistracionBean = new SimulacionMinistracion(distribuidor);
        this.simulacionCapital = new SimulacionCapital(distribuidor);
        this.simuladorGeneral=new SimuladorGeneral(distribuidor.getDistribuidorGeneralProyectos().clone());
        ponerGraficas();
    }
    
    
    public static void main(String[] args) {
        GeneralSimulacion general=new GeneralSimulacion();
    }

    private void ponerGraficas() {
        List<String> listaBalance = new LinkedList<String>();
        listaBalance.add("veh_bal_roe");
        listaBalance.add("veh_bal_roa");
        listaBalance.add("veh_bal_ebi");
        listaBalance.add("veh_pal_ope");
        listaBalance.add("veh_pal_apa");
        listaBalance.add("veh_liq_anu");//
        listaBalance.add("veh_cap_tra_anu");
        listaBalance.add("veh_cob_deu");
        listaBalance.add("veh_rot_inv");
        listaBalance.add("veh_dia_cta_por_cob");
        listaBalance.add("veh_dia_cta_por_pag");
        listaBalance.add("veh_cob_ser_deu");
        this.graficasBalance = new GraficasSimulacion( distribuidor, listaBalance);
        List<String> listaFinancieras = new LinkedList<String>();
        listaFinancieras.add("veh_mar_ope");
        listaFinancieras.add("veh_mar_ant_imp");
        listaFinancieras.add("veh_mar_net");
        listaFinancieras.add("veh_bal_efe_anu");
        listaFinancieras.add("veh_bal_cta_cob_anu");
        listaFinancieras.add("veh_bal_inv_viv_anu");
        listaFinancieras.add("veh_bal_tot_act_anu");
        listaFinancieras.add("veh_bal_cta_por_pag_anu");
        listaFinancieras.add("veh_bal_deu_anu");
        listaFinancieras.add("veh_bal_tot_pas_anu");
        listaFinancieras.add("veh_bal_cap_anu");
        listaFinancieras.add("veh_utl_per_anu");
        listaFinancieras.add("veh_utl_ret_anu");
        listaFinancieras.add("veh_bal_tot_pat_anu");
        this.graficasFinancieras = new GraficasSimulacion( distribuidor, listaFinancieras);
        List<String> listaParametrias = new LinkedList<String>();
        listaParametrias.add("veh_cet_uni_edf_anu");
        listaParametrias.add("veh_cet_uni_edf_acu_anu");
        listaParametrias.add("veh_cet_uni_dis_anu");
        listaParametrias.add("veh_cet_uni_dis_acu_anu");
        listaParametrias.add("veh_cet_uni_ven_anu");
        listaParametrias.add("veh_cet_uni_ven_acu_anu");
        this.graficasParametricas = new GraficasSimulacion( distribuidor, listaParametrias);
    }

    /**
     * @return the inversionSimulacionBean
     */
    public InversionSimulacionBean getInversionSimulacionBean() {
        return inversionSimulacionBean;
    }

    /**
     * @param inversionSimulacionBean the inversionSimulacionBean to set
     */
    public void setInversionSimulacionBean(InversionSimulacionBean inversionSimulacionBean) {
        this.inversionSimulacionBean = inversionSimulacionBean;
    }

    /**
     * @return the simulacionMinistracionBean
     */
    public SimulacionMinistracion getSimulacionMinistracionBean() {
        return simulacionMinistracionBean;
    }

    /**
     * @param simulacionMinistracionBean the simulacionMinistracionBean to set
     */
    public void setSimulacionMinistracionBean(SimulacionMinistracion simulacionMinistracionBean) {
        this.simulacionMinistracionBean = simulacionMinistracionBean;
    }

    private void sacarProyectoActual() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(getSufijo() + "MarkFuture" + delimitadorArchivos + "mark.conf"));
            String linea = reader.readLine();
            parseLinea(linea);
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(GeneralSimulacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void parseLinea(String linea) {
        modelos=new LinkedList<MdlVeh>();
        WOES=new LinkedList<Double>();
        Dao dao=new Dao();
        String[] split = linea.split(",");
        System.out.println(split.length);
        for(String parte:split){
            System.out.println(parte);
            String[] split1 = parte.split("-");
            this.modelos.add(dao.getModelo(Integer.parseInt(split1[0])));
            this.WOES.add(Double.parseDouble(split1[1]));
            this.usu=dao.getUsu(Integer.parseInt(split1[2]));
        }
        System.out.println("salgo de for");
        this.modelos=modificarInputs();
    }



    /**
     * @return the sufijo
     */
    public static String getSufijo() {
        return sufijo;
    }

    /**
     * @param aSufijo the sufijo to set
     */
    public static void setSufijo(String aSufijo) {
        sufijo = aSufijo;
    }

    /**
     * @return the graficasBalance
     */
    public GraficasSimulacion getGraficasBalance() {
        return graficasBalance;
    }

    /**
     * @param graficasBalance the graficasBalance to set
     */
    public void setGraficasBalance(GraficasSimulacion graficasBalance) {
        this.graficasBalance = graficasBalance;
    }

    /**
     * @return the graficasFinancieras
     */
    public GraficasSimulacion getGraficasFinancieras() {
        return graficasFinancieras;
    }

    /**
     * @param graficasFinancieras the graficasFinancieras to set
     */
    public void setGraficasFinancieras(GraficasSimulacion graficasFinancieras) {
        this.graficasFinancieras = graficasFinancieras;
    }

    /**
     * @return the graficasParametricas
     */
    public GraficasSimulacion getGraficasParametricas() {
        return graficasParametricas;
    }

    /**
     * @param graficasParametricas the graficasParametricas to set
     */
    public void setGraficasParametricas(GraficasSimulacion graficasParametricas) {
        this.graficasParametricas = graficasParametricas;
    }
    /**
     * @return the candleBean
     */
    /**
     * @return the simulacionCapital
     */
    public SimulacionCapital getSimulacionCapital() {
        return simulacionCapital;
    }

    /**
     * @param simulacionCapital the simulacionCapital to set
     */
    public void setSimulacionCapital(SimulacionCapital simulacionCapital) {
        this.simulacionCapital = simulacionCapital;
    }


    /**
     * @return the simuladorGeneral
     */
    public SimuladorGeneral getSimuladorGeneral() {
        return simuladorGeneral;
    }

    
    
    
    /**
     * @param simuladorGeneral the simuladorGeneral to set
     */
    public void setSimuladorGeneral(SimuladorGeneral simuladorGeneral) {
        this.simuladorGeneral = simuladorGeneral;
    }

    private List<MdlVeh> modificarInputs() {
        List<DistribuidorModeloVehiculo> distribuidores=new LinkedList<DistribuidorModeloVehiculo>();
        for(int t=0;t<modelos.size();t++){
            CapsulaMarkFuture capsula=new CapsulaMarkFuture(modelos.get(t), WOES.get(t));
            modelos.set(t, capsula.modificar());
            DistribuidorModeloVehiculo dist = new DistribuidorModeloVehiculo(Funciones.sacarProyectos(modelos.get(t)), true,18,12);
            distribuidores.add(dist);
        }
        this.distribuidor=new DistribuidorMarkFuture(distribuidores);
        return modelos;
    }

   
}
