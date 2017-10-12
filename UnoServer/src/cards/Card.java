/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cards;
import java.io.Serializable;
import uno_interface.IRemoteUno;


/**
 *
 * @author andpi
 */
public class Card extends CardBase implements Serializable{
    /*public Card(){
        
    }*/
    
   public Card(String imageName,String name,int value, String type){
       super(imageName,name,value,type);
       
   }
   public Card(){
       
       
   }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    
   

   
   
   
    
    
}
