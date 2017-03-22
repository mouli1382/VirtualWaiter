package in.mobifirst.virtualwaiter;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by narasimha.gonapa on 10-03-2017.
 */

@IgnoreExtraProperties
public class Chair {

    public String chairName;
    public Boolean help;
    public Boolean tea;
    public Boolean water;
    public Boolean pen;
    public Boolean tissue;
    public Boolean fruit;
    public Boolean snack;
    public Boolean note;


    public Chair() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Chair(String chairName, Boolean help, Boolean tea, Boolean water, Boolean note, Boolean pen, Boolean tissue, Boolean snack, Boolean fruit) {
        this.chairName = chairName;
        this.help = help;
        this.water = water;
        this.tea = tea;
        this.pen = pen;
        this.tissue = tissue;
        this.note = note;
        this.snack = snack;
        this.fruit = fruit;

    }

    void setChairName(String chairName) {
        this.chairName = chairName;
    }
    String getChairName() {
        return chairName;
    }

   void setNote(Boolean note) {
        this.note = note;
    }
    Boolean getNote() {
        return note;
   }

    void setFruit(Boolean fruit){
        this.fruit = fruit;
    }
    Boolean getFruit() {
        return fruit;
    }

    void setSnack (Boolean snack) {this.snack = snack; }
    Boolean getSnack() {return snack;}

    void setHelp(Boolean help){
        this.help = help;
    }
    Boolean getHelp() {
        return help;
    }

    void setTea(Boolean tea){
        this.tea = tea;
    }
    Boolean getTea() {
        return tea;
    }

    void setWater(Boolean water) {
        this.water = water;
    }
    Boolean getWater() {
        return  water;
    }

    void setPen(Boolean pen) {
        this.pen = pen;
    }
    Boolean getPen() {
        return  pen;
    }

    void setTissue (Boolean tissue) {this.tissue = tissue;}
    Boolean getTissue() { return tissue;}



    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("chairName", chairName);
        result.put("water", water);
        result.put("tea", tea);
        result.put("note", note);
        result.put("tissue", tissue);
        result.put("help", help);
        result.put("pen", pen);
        result.put("snack", snack);
        result.put("fruit", fruit);

        return result;
    }

}
