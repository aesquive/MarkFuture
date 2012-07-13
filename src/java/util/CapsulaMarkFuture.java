/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import base.Dao;
import java.util.HashSet;
import java.util.Set;
import pojos.DatVeh;
import pojos.EtpVeh;
import pojos.MdlVeh;
import pojos.PryVeh;
import pojos.RelMdlVeh;
import pojos.VarVeh;

/**
 *
 * @author alberto
 */
public class CapsulaMarkFuture {
    
    private MdlVeh modelo;
    private double WOE;
    private final Dao dao;

    
    public CapsulaMarkFuture(MdlVeh modelo,double WOE){
        this.dao=new Dao();
        this.modelo=modelo;
        this.WOE=WOE;
    }
    /**
     * @return the modelo
     */
    public MdlVeh getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(MdlVeh modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the WOE
     */
    public double getWOE() {
        return WOE;
    }

    /**
     * @param WOE the WOE to set
     */
    public void setWOE(double WOE) {
        this.WOE = WOE;
    }

    public MdlVeh modificar() {
        
        Set<RelMdlVeh> nueva=new HashSet<RelMdlVeh>();
        Set<RelMdlVeh> relMdlVehs = modelo.getRelMdlVehs();
        for(RelMdlVeh rel:relMdlVehs){
            PryVeh proyecto = rel.getPryVeh().clone();
            modificarProyecto(proyecto);
            RelMdlVeh nuevaRel=new RelMdlVeh(modelo, proyecto);
            nueva.add(nuevaRel);
        }
        modelo.setRelMdlVehs(nueva);
        return modelo;
    }

    private void modificarProyecto(PryVeh proyecto) {
        modificarDatosVehiculo(proyecto);
        modificarEtapasVehiculo(proyecto);
    }

    private void modificarDatosVehiculo(PryVeh proyecto) {
        Set<DatVeh> nuevos=new HashSet<DatVeh>();
        Set<DatVeh> datVehs = proyecto.getDatVehs();
        Set<Integer> numerosVars=llenarSet(1,2,3,4,5,6,7,8,9,11,12,13,14,15,16,17,18,19,22,23);
        
        
        for(DatVeh dato:datVehs){
            dato=dao.getDatVeh(dato.getId()).clone();
           
            VarVeh varVeh = dato.getVarVeh();
            if(varVeh!=null && numerosVars.contains(varVeh.getId())){
                dato.setVal(modificarValor(dato.getVal()));
                nuevos.add(dato);
                System.out.println("el dato "+varVeh.getId()+" vale = "+dato.getVal());
            }else if(varVeh!=null && !numerosVars.contains(varVeh.getId())){
                nuevos.add(dato);
            }
        }
        proyecto.setDatVehs(nuevos);
    }

    private Set<Integer> llenarSet(int ... numeros) {
        Set<Integer> nums=new HashSet<Integer>();
        for(int t=0;t<numeros.length;t++){
            nums.add(numeros[t]);
        }
        return nums;
    }

    private String modificarValor(String val) {
        if(val!=null && !val.equals("")){
            boolean porc=false;
            if(val.contains("%")){
                porc=true;
                val=val.replace("%", "");
            }
            double valorNumerico = Double.parseDouble(val);
            double valorFinal=valorNumerico*WOE;
            String valStr= porc ? String.valueOf(valorFinal)+"%" : String.valueOf(valorFinal);
            return valStr;
        }
        return val;
    }

    private void modificarEtapasVehiculo(PryVeh proyecto) {
        Set<EtpVeh> nuevas=new HashSet<EtpVeh>();
        Set<EtpVeh> etpVehs = proyecto.getEtpVehs();
        for(EtpVeh etp:etpVehs){
            etp=dao.getEtpVeh(etp.getId()).clone();
            VarVeh varVeh = etp.getVarVeh();
            if(varVeh!=null && varVeh.getId()==35){
                String val = etp.getVal();
                Double valDo=Double.parseDouble(val)*WOE;
                etp.setVal(String.valueOf(valDo.intValue()));
            }
            nuevas.add(etp);
        }
        proyecto.setEtpVehs(nuevas);
    }

    
    
    private Set<DatVeh> clonarDatos(PryVeh proyecto) {
        Set<DatVeh> datVehs = proyecto.getDatVehs();
        Set<DatVeh> nuevo=new HashSet<DatVeh>();
        for(DatVeh d:datVehs){
            nuevo.add(d.clone());
        }
        return nuevo;
    }
    
}
