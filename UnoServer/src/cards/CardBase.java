package cards;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andpi
 */
public class CardBase {
    String imageName;
    String name;
    int value;
    String type;
    String color;
    public CardBase(){}
    
    public CardBase(String imageName,String name, int value, String type){
        this.imageName=imageName;
        this.name=name;
        this.value=value;
        this.type=type;
        assignColor(name);    
    }
    
    
    //Function to assign the color of the cards, using Enum values.
    
    private  void assignColor(String name){
     
       
      if(name.startsWith("B")){
          this.color="Blue";
      } 
      if(name.startsWith("G")){
          this.color="Green";         
      }
       if(name.startsWith("R")){
             this.color="Red";    
      }
       if(name.startsWith("Y")){
          this.color="Yellow";
      } 
   }
    
    
}
