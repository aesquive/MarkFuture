package pojos;
// Generated 29-mar-2012 18:27:14 by Hibernate Tools 3.2.1.GA



/**
 * MapCalVeh generated by hbm2java
 */
public class MapCalVeh  implements java.io.Serializable {


     private Integer id;
     private MdlVeh mdlVeh;
     private TipMapCalVeh tipMapCalVeh;
     private Integer coordX;
     private Integer coordY;
     private String val;
     private Integer color;

    public MapCalVeh() {
    }

    public MapCalVeh(MdlVeh mdlVeh, TipMapCalVeh tipMapCalVeh, Integer coordX, Integer coordY, String val, Integer color) {
       this.mdlVeh = mdlVeh;
       this.tipMapCalVeh = tipMapCalVeh;
       this.coordX = coordX;
       this.coordY = coordY;
       this.val = val;
       this.color = color;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public MdlVeh getMdlVeh() {
        return this.mdlVeh;
    }
    
    public void setMdlVeh(MdlVeh mdlVeh) {
        this.mdlVeh = mdlVeh;
    }
    public TipMapCalVeh getTipMapCalVeh() {
        return this.tipMapCalVeh;
    }
    
    public void setTipMapCalVeh(TipMapCalVeh tipMapCalVeh) {
        this.tipMapCalVeh = tipMapCalVeh;
    }
    public Integer getCoordX() {
        return this.coordX;
    }
    
    public void setCoordX(Integer coordX) {
        this.coordX = coordX;
    }
    public Integer getCoordY() {
        return this.coordY;
    }
    
    public void setCoordY(Integer coordY) {
        this.coordY = coordY;
    }
    public String getVal() {
        return this.val;
    }
    
    public void setVal(String val) {
        this.val = val;
    }
    public Integer getColor() {
        return this.color;
    }
    
    public void setColor(Integer color) {
        this.color = color;
    }




}

