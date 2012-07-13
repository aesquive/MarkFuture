package pojos;
// Generated 29-mar-2012 18:27:14 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Usu generated by hbm2java
 */
public class Usu  implements java.io.Serializable {


     private Integer id;
     private TipUsu tipUsu;
     private String logUsu;
     private String pas;
     private Set usuPryVehs = new HashSet(0);
     private Set mdlVehs = new HashSet(0);
     private Set usuPryPryInds = new HashSet(0);
     private Set mdlPryInds = new HashSet(0);
     private Set usuMdlVehs = new HashSet(0);

    public Usu() {
    }

    public Usu(TipUsu tipUsu, String logUsu, String pas, Set usuPryVehs, Set mdlVehs, Set usuPryPryInds, Set mdlPryInds, Set usuMdlVehs) {
       this.tipUsu = tipUsu;
       this.logUsu = logUsu;
       this.pas = pas;
       this.usuPryVehs = usuPryVehs;
       this.mdlVehs = mdlVehs;
       this.usuPryPryInds = usuPryPryInds;
       this.mdlPryInds = mdlPryInds;
       this.usuMdlVehs = usuMdlVehs;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public TipUsu getTipUsu() {
        return this.tipUsu;
    }
    
    public void setTipUsu(TipUsu tipUsu) {
        this.tipUsu = tipUsu;
    }
    public String getLogUsu() {
        return this.logUsu;
    }
    
    public void setLogUsu(String logUsu) {
        this.logUsu = logUsu;
    }
    public String getPas() {
        return this.pas;
    }
    
    public void setPas(String pas) {
        this.pas = pas;
    }
    public Set getUsuPryVehs() {
        return this.usuPryVehs;
    }
    
    public void setUsuPryVehs(Set usuPryVehs) {
        this.usuPryVehs = usuPryVehs;
    }
    public Set getMdlVehs() {
        return this.mdlVehs;
    }
    
    public void setMdlVehs(Set mdlVehs) {
        this.mdlVehs = mdlVehs;
    }
    public Set getUsuPryPryInds() {
        return this.usuPryPryInds;
    }
    
    public void setUsuPryPryInds(Set usuPryPryInds) {
        this.usuPryPryInds = usuPryPryInds;
    }
    public Set getMdlPryInds() {
        return this.mdlPryInds;
    }
    
    public void setMdlPryInds(Set mdlPryInds) {
        this.mdlPryInds = mdlPryInds;
    }
    public Set getUsuMdlVehs() {
        return this.usuMdlVehs;
    }
    
    public void setUsuMdlVehs(Set usuMdlVehs) {
        this.usuMdlVehs = usuMdlVehs;
    }




}


