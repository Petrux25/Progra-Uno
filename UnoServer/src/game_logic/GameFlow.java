/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_logic;

import cards.Card;
import cards.CardColor;
import cards.CardType;
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
    Card lastCard;
    //firstCard();
    
    boolean reverse = false;

    int turno;

    ///// //////////Singleton //////////////
    private GameFlow() {
        firstCard();
        this.deckList = deck.generateDeck();
    }
    ///////// Singleton ////////////////////
    public static GameFlow getInstance() {
        return moves;
    }

    
    
    /////////////////////////////////////////
    @Override
    public void addPlayer(String name, String ip) throws RemoteException {
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
    
    public void firstCard(){
        
        lastCard=deck.getCardFromDeck();
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
    /////////////////////////////////////////
    
    
    
    public void wildChangeColor(String color){
        
        if(color.startsWith("R")){
            this.lastCard.setColor(CardColor.RED);
        }
        if(color.startsWith("G")){
            this.lastCard.setColor(CardColor.GREEN);
        }
        if(color.startsWith("B")){
            this.lastCard.setColor(CardColor.BLUE);
        }
        if(color.startsWith("Y")){
            this.lastCard.setColor(CardColor.YELLOW);
        }
        
    }
    
    

    public Card getLastCard() {
        return lastCard;
    }
    
    

   /* public void setLastCard(Card selectedCard,Card lastCard) {
        
        if(selectedCard.getColor()==lastCard.getColor() || selectedCard.getValue()==lastCard.getValue()){
            
            
        }else if(selectedCard.getType()==CardType.WILD){
            
            
            
        }else if(selectedCard.getType()==CardType.WILDDRAW4){
                
          
            
            
            
        }
            
        }
        this.lastCard = lastCard;*/
        
            
    
    
    
    
    
   /* public void flow(){
        
        if(lastCard==null){
            dealFirstCards();    
        }
        else if (lastCard==){
            
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }*/
    


    ///////////////// este es para que no caiga 
    @Override
    public String mensaje() throws RemoteException {
        return "Singleton created";
    }

    /*@Override
    public ArrayList<String> getHand() throws RemoteException {
       dealFirstCards();
       Player pig = players.get(0);
       ArrayList<String> nombreCarta = new ArrayList<>();
       ArrayList<Card> hand = new ArrayList<>();
       hand = pig.getHand();
       for(int i=0; i<hand.size();i++){
           nombreCarta.add(hand.get(i).getImageName());
       }
       return nombreCarta;
       
       
    }*/

    


    
    
    @Override
    public ArrayList<String> getHand() throws RemoteException {
       dealFirstCards();
       Player pig = players.get(0);
       ArrayList<String> nombreCarta = new ArrayList<>();
       ArrayList<Card> hand = new ArrayList<>();
       hand = pig.getHand();
       for(int i=0; i<hand.size();i++){
           nombreCarta.add(hand.get(i).getImageName());
       }
       return nombreCarta;
        
    }

    public  ArrayList<Player> getPlayers() {
        return players;
    }
//////////////////// metodo para poner ultima carta
    @Override
    public boolean validateLastCard(String nameComp,String playerComp) throws RemoteException {
        boolean compBool=false;
        Card compCard;
        System.out.println(compBool);
        
        for(int c=0;c<players.size();c++){
                System.out.println(players.get(c).getName());
                System.out.println(playerComp);
                System.out.println("-------------------------------------------");
            
            if(players.get(c).getName().equals(playerComp)){
                
                
                for(int d=0;d<(players.get(c).getHand().size());d++){
                    
                    compCard=players.get(c).getHand().get(d);
                    System.out.println(compCard.getName());
                    System.out.println(nameComp);
                    System.out.println("--------------------|||||||||||-----------------------");
                    
                    
                    //Compara si la carta con el lastCard y valida si se puede jugar la card
                    if(compCard.getName().equals(nameComp)){
                        System.out.println("skrt");
                        if((compCard.getColor()==lastCard.getColor())|| (compCard.getName().equals(lastCard.getName()))||(compCard.getType()==lastCard.getType())||(compCard.getType().equals(CardType.WILD))||(compCard.getType()==CardType.WILDDRAW4)){
                            System.out.println("true");
                            compBool=true;
                            lastCard=compCard;     
                            break;
                        }
                        
                        
                    } 
                }
                break; 
                
            }
           
        }
        return compBool;
        
        

    }

    @Override
    public String getFirstCard() throws RemoteException {
      return lastCard.getImageName();
    }
    
    
    


    
   
            
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
