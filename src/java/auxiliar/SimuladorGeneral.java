package auxiliar;

import calculador.vehiculo.DistribuidorModeloVehiculo;
import guardadores.GuardadorModelo;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import markFuture.DistribuidorMarkFuture;
import org.primefaces.model.chart.MeterGaugeChartModel;
import pojos.MdlVeh;
import pojos.PryVeh;

/**
 *
 * @author alberto
 */
public class SimuladorGeneral {

    private DistribuidorMarkFuture distribuidor;
    private InversionSimulacionBean inversionSimulacionBean;
    private SimulacionMinistracion simulacionMinistracionBean;
    private SimulacionCapital simulacionCapital;
    private MeterGaugeChartModel tacometro;
    private DistribuidorModeloVehiculo distribuidorModeloVehiculo;
    
    public SimuladorGeneral(DistribuidorModeloVehiculo distribuidor){
        this.distribuidorModeloVehiculo=distribuidor;
        this.distribuidor=new DistribuidorMarkFuture(distribuidorModeloVehiculo);
        this.distribuidor.modelarPrincipal();
        generarBeansIndependientes();
        llenarBeans();
    }

    private void generarBeansIndependientes() {
        this.setInversionSimulacionBean(new InversionSimulacionBean(getDistribuidor()));
        this.setSimulacionCapital(new SimulacionCapital(getDistribuidor()));
        this.setSimulacionMinistracionBean(new SimulacionMinistracion(getDistribuidor()));
        
    }

    public String simulacionTotal(){
        this.setDistribuidor(simulacionMinistracionBean.preSimulacion());
        simulacionCapital.setDistribuidor(getDistribuidor());
        this.setDistribuidor(simulacionCapital.preSimulacion());
        this.inversionSimulacionBean.setDistribuidor(getDistribuidor());
        this.setDistribuidor(inversionSimulacionBean.preSimulacion());
        getDistribuidor().modelarPrincipal();
        inversionSimulacionBean.setDistribuidor(getDistribuidor());
        simulacionCapital.setDistribuidor(getDistribuidor());
        simulacionMinistracionBean.setDistribuidor(getDistribuidor());
        inversionSimulacionBean.postSimulacion();
        simulacionCapital.postSimulacion();
        simulacionMinistracionBean.postSimulacion();
        llenarBeans();
        return "";
    }
  

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

    private void llenarBeans() {
        setTacometro(inversionSimulacionBean.getTacometro());
    }

    /**
     * @return the tacometro
     */
    public MeterGaugeChartModel getTacometro() {
        return tacometro;
    }

    /**
     * @param tacometro the tacometro to set
     */
    public void setTacometro(MeterGaugeChartModel tacometro) {
        this.tacometro = tacometro;
    }
    


    private List<DistribuidorModeloVehiculo> generarDistribuidores() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * @return the distribuidor
     */
    public DistribuidorMarkFuture getDistribuidor() {
        return distribuidor;
    }

    /**
     * @param distribuidor the distribuidor to set
     */
    public void setDistribuidor(DistribuidorMarkFuture distribuidor) {
        this.distribuidor = distribuidor;
    }
    
}
