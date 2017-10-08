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
public class Card extends CardBase implements IRemoteUno,Serializable{
    /*public Card(){
        
    }*/
    
   public Card(String imageName,String name,int value, CardType type){
       super(imageName,name,value,type);
       
   }
   public Card(){
       
       
   }

    
   

   
   
    @Override
    public String mensaje() {
       
        return "hola duggo";
        
    }
    
    
}
