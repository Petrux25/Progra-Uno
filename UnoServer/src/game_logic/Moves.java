/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_logic;

import cards.Card;
import deck.Deck;
import java.rmi.RemoteException;
import java.util.ArrayList;
import player.Player;
import uno_interface.IRemoteUno;

/**
 *
 * @author andpi
 */
public class Moves implements IRemoteUno{
    
    private static Moves moves=new Moves();

    Deck deck=new Deck();
    ArrayList<Card> deckList=new ArrayList<Card>();
    ArrayList<Player> players=new ArrayList<Player>();
    ArrayList<Card> playedCards=new ArrayList<Card>();
    int turno;
    String der;
    String izq;
    

    ///// //////////Singleton //////////////
    private Moves(){
       this.deckList=deck.generateDeck(); 
       
    }
    public static Moves getInstance(){
        return moves;
    }
    
    
    /////////////////////////////////////////
    
    public void addPlayer(String name,String ip){
        players.add(new Player(name,ip)); 
    }
    
    
    // Metodo para repartir 7 cartas a cada jugador al inicio
    public void dealFirstCards(){
        
        for(int i=0;i<players.size();i++){
            Player player=players.get(i);
            for(int k=0;k<7;k++){
                player.setCard(deckList.get(k));
                deckList.remove(k);   
            }
 
        }    
    }
    
    public String uno(Player player){
        String uno="";
        
        if(player.getHand().size()==1){
            uno= "Uno";
        }
        
        return uno;
    }
    
    public Card drawCard(){
        Card retuCard;
        if(deckList.size()==0){
            deckList=deck.generateDeck();
        }
        retuCard=deckList.get(0);
        deckList.remove(0);  
        return retuCard;  
    }

    @Override
    public String mensaje() throws RemoteException {
        return "Singleton created";
    }
    
   
    
    

    
   
            
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
