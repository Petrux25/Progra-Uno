/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deck;

import cards.Card;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author andpi
 */
public class Deck {
    private static int numberCards=19;
    private static int specialColorCards=2;
    private static int wildCards=4;

    
    private static int totalCards=108;
    
    
    
    ArrayList<Card> cards=new ArrayList<Card>();
    String[] normalCards={"Blue","Green","Red","Yellow"};
    String[] specialCards={"Draw2","Reverse","Skip","Wild","WildDraw4"};
    
   //Function to shuffle deck
    protected void shuffleDeck(){
        Collections.shuffle(cards);
        Collections.shuffle(cards);
    }
    
    //function to generate an ArrayList with the names of cards
    
    protected ArrayList<String> generateCardNamesList(){
        
   
        int progression=0;
        int contNumber=0;
        
        ArrayList<String> cardNamesList= new ArrayList<String>();
        
        //while loop to add color cards to deck
        
        while(progression<4){
            
            //for loop to add all the number cards+color
            for(int i=0;i<=numberCards;i++){
                if (contNumber<=9){
                    cardNamesList.add(normalCards[progression]+contNumber);
                    contNumber++;
                }
                else{
                    contNumber=1;
                }
                
            }
            //for loop to add reverse, skip, draw2 cards+color
            for(int j=0;j<specialColorCards;j++){
                cardNamesList.add(normalCards[progression]+"Draw2");
                cardNamesList.add(normalCards[progression]+"Skip");
                cardNamesList.add(normalCards[progression]+"Reverse");
            }
            progression++;
            contNumber=0;
        }
        
        //for loop to add wild and wildraw4 cards to deck
            for(int k=0;k<=wildCards;k++){
                cardNamesList.add("Wild");
                cardNamesList.add("WildDraw4");
            }
        return cardNamesList;    
    }
    
    
    
    
    
    
    
    
}
