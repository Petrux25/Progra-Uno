/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cards;
import uno_interface.IRemoteUno;


/**
 *
 * @author andpi
 */
public class Card extends CardBase implements IRemoteUno{
   public Card(){
   }
   
   //public Card(String name, int value, CardType type){
     //   super(name,value);

   
        
        
    //}

    @Override
    public String mensaje() {
        return "Hola duggo";
        
    }
    
    
}
