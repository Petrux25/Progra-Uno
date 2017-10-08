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
public class GameFlow implements IRemoteUno {

    private static GameFlow moves = new GameFlow();

    Deck deck = new Deck();
    ArrayList<Card> deckList = new ArrayList<Card>();
    ArrayList<Player> players = new ArrayList<Player>();
    ArrayList<Card> playedCards = new ArrayList<Card>();
    
    
    boolean reverse = false;

    int turno;

    ///// //////////Singleton //////////////
    private GameFlow() {
        this.deckList = deck.generateDeck();

    }
    ///////// Singleton ////////////////////
    public static GameFlow getInstance() {
        return moves;
    }

    /////////////////////////////////////////
    public void addPlayer(String name, String ip) {
        players.add(new Player(name, ip));
    }

    // Metodo para repartir 7 cartas a cada jugador al inicio
    public void dealFirstCards() {
        for (int i = 0; i < players.size(); i++) {
            Player player = players.get(i);
            for (int k = 0; k < 7; k++) {
                player.setCard(deckList.get(k));
                deckList.remove(k);
            }
        }

    }
    
    ///// Mecanismo para decir Uno //

    public String uno(Player player) {
        String uno = "";

        if (player.getHand().size() == 1) {
            uno = "Uno";
        }

        return uno;
    }
    
    ///// Pedir carta del deck //////////

    public Card drawCard(int quantity) {
        Card retuCard;
        if (deckList.size() == 0) {
            deckList = deck.generateDeck();
        }
        retuCard = deckList.get(0);
        deckList.remove(0);
        
        return retuCard;
    }
    
    ///////////// Cambiar de turno ///////////////////

    public void nextTurn() {

        if (reverse) {
            if (turno == 0) {
                turno = (players.size() - 1);
            } else {
                turno--;
            }
        } else {

            if (turno == players.size() - 1) {
                turno = 0;

            } else {
                turno++;
            }

        }
    }
    
    ///////////// skip /////////////////////

    public void skip() {

        if (reverse) {
            if ((turno > 1) && (turno <= players.size() - 1)) {
                turno -= 2;
            } else if (turno == 1) {
                turno = players.size() - 1;
            } else if (turno == 0) {
                turno = players.size() - 2;
            }
        } else {

            if ((turno >= 0) && (turno < (players.size() - 1))) {
                turno += 2;

            } else if (turno == (players.size() - 1)) {
                turno = 1;
            }
        }

    }
    
    
    ///////////////// habilitar el cambio de direccion 
    
    public void changeDir(){
        if(reverse){
            reverse=false;
            
        }else{
            reverse=true;
        }
    }
    
    
    public void setTurnTrue(int index){
        players.get(index).setTurn(true);
     
    }
    public void setTurnFalse(int index){
        players.get(index);
        
    }
    
    
    
            
    
    
  
    
    
    
    
    
    

    ///////////////// este es para que no caiga 
    @Override
    public String mensaje() throws RemoteException {
        return "Singleton created";
    }


    
   
            
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}