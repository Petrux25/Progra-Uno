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
    CardType type;
    CardColor color;
    public CardBase(){}
    
    public CardBase(String imageName,String name, int value, CardType type){
        this.imageName=imageName;
        this.name=name;
        this.value=value;
        this.type=type;
        assignColor(name);    
    }
    
    
    //Function to assign the color of the cards, using Enum values.
    
    private  void assignColor(String name){
       name=name.toLowerCase();
       
      if(name.equals("blue")){
          this.color=CardColor.BLUE;
      } 
      if(name.equals("green")){
          this.color=CardColor.GREEN;          
      }
       if(name.equals("red")){
             this.color=CardColor.RED;    
      }
       if(name.equals("yellow")){
          this.color=CardColor.YELLOW;
      } 
   }
    
    
}
