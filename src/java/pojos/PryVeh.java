package pojos;
// Generated 29-mar-2012 18:27:14 by Hibernate Tools 3.2.1.GA


import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * PryVeh generated by hbm2java
 */
public class PryVeh  implements java.io.Serializable , Cloneable{


     private Integer id;
     private TipMon tipMon;
     private String nomPry;
     private Calendar fch;
     private Set datVehs = new HashSet(0);
     private Set etpTirVehs = new HashSet(0);
     private Set usuPryVehs = new HashSet(0);
     private Set matVehs = new HashSet(0);
     private Set relMdlVehs = new HashSet(0);
     private Set etpVehs = new HashSet(0);

    public PryVeh() {
    }

    public PryVeh(TipMon tipMon, String nomPry, Calendar fch, Set datVehs, Set etpTirVehs, Set usuPryVehs, Set matVehs, Set relMdlVehs, Set etpVehs) {
       this.tipMon = tipMon;
       this.nomPry = nomPry;
       this.fch = fch;
       this.datVehs = datVehs;
       this.etpTirVehs = etpTirVehs;
       this.usuPryVehs = usuPryVehs;
       this.matVehs = matVehs;
       this.relMdlVehs = relMdlVehs;
       this.etpVehs = etpVehs;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public TipMon getTipMon() {
        return this.tipMon;
    }
    
    public void setTipMon(TipMon tipMon) {
        this.tipMon = tipMon;
    }
    public String getNomPry() {
        return this.nomPry;
    }
    
    public void setNomPry(String nomPry) {
        this.nomPry = nomPry;
    }
    public Calendar getFch() {
        return this.fch;
    }
    
    public void setFch(Calendar fch) {
        this.fch = fch;
    }
    public Set getDatVehs() {
        return this.datVehs;
    }
    
    public void setDatVehs(Set datVehs) {
        this.datVehs = datVehs;
    }
    public Set getEtpTirVehs() {
        return this.etpTirVehs;
    }
    
    public void setEtpTirVehs(Set etpTirVehs) {
        this.etpTirVehs = etpTirVehs;
    }
    public Set getUsuPryVehs() {
        return this.usuPryVehs;
    }
    
    public void setUsuPryVehs(Set usuPryVehs) {
        this.usuPryVehs = usuPryVehs;
    }
    public Set getMatVehs() {
        return this.matVehs;
    }
    
    public void setMatVehs(Set matVehs) {
        this.matVehs = matVehs;
    }
    public Set getRelMdlVehs() {
        return this.relMdlVehs;
    }
    
    public void setRelMdlVehs(Set relMdlVehs) {
        this.relMdlVehs = relMdlVehs;
    }
    public Set getEtpVehs() {
        return this.etpVehs;
    }
    
    public void setEtpVehs(Set etpVehs) {
        this.etpVehs = etpVehs;
    }



    @Override
    public PryVeh clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException ex) {
            System.out.println(" no se puede duplicar");
        }
        return (PryVeh)obj;
    }

}


