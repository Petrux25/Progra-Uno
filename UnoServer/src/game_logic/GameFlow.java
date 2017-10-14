/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_logic;

import cards.Card;
import cards.ECardColor;
import cards.ECardType;

import deck.Deck;
import java.rmi.RemoteException;
import java.util.ArrayList;
import player.Player;
import uno_interface.IRemoteUno;
import main_class.MainServer;
import main_class.MainServer.Notificacion;

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
    public boolean addPlayer(String name, String ip) throws RemoteException {
        boolean exists=false;
        
        if(validateName(name)){
            exists=true;
            
        }else{
        players.add(new Player(name, ip));
        }
        System.out.println(exists);
        return exists;
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

    public void firstCard() {

        lastCard = deck.getCardFromDeck();
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
    public void changeDir() {
        if (reverse) {
            reverse = false;

        } else {
            reverse = true;
        }
    }

    public void setTurnTrue(int index) {
        players.get(index).setTurn(true);

    }

    public void setTurnFalse(int index) {
        players.get(index);

    }

    /////////////////////////////////////////
    @Override
    public void wildChangeColor(String color) throws RemoteException {

        if (color.startsWith("R")) {
            this.lastCard.setColor(ECardColor.RED);
        }
        if (color.startsWith("G")) {
            this.lastCard.setColor(ECardColor.GREEN);

        }
        if (color.startsWith("B")) {
            this.lastCard.setColor(ECardColor.BLUE);
        }
        if (color.startsWith("Y")) {
            this.lastCard.setColor(ECardColor.YELLOW);
        }

    }

    private Card getLastCard() {
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

       
       
    
    //////////////////////////////////////////////////////////////
    @Override
    public ArrayList<String> getHand(String playerName) throws RemoteException {
        ArrayList<String> nombreCarta = new ArrayList<>();
        ArrayList<Card> hand = new ArrayList<>();
        for (int p = 0; p < players.size(); p++) {
            Player playerComp = players.get(p);
            if ((playerComp.getName()).equals(playerName)) {
                hand = playerComp.getHand();
                for (int i = 0; i < hand.size(); i++) {
                    nombreCarta.add(hand.get(i).getImageName());
                }

            }

        }

        return nombreCarta;

    }
////////////// methot to get players from the players Arraylist

    public ArrayList<Player> getPlayers() {
        return players;
    }

    ///////////////////////// Method to validate that the player exists
    private boolean validateName(String player) {
        boolean compPlayer = false;
        if(players.size()!=0){
        for (int c = 0; c < players.size(); c++) {

          
            if (players.get(c).getName().equals(player)) {
                compPlayer = true;
                break;
            }
        }
        }
        return compPlayer;
    }
////////////////////////Method to get the index of a player /////////////

    private int getPlayerIndex(String wantedPlayer) {
        int index = -1;
        for (int c = 0; c < players.size(); c++) {
            if (players.get(c).getName().equals(wantedPlayer)) {
                index = c;
                break;
            }
        }
        return index;

    }

    //////////////////////// Method to 
//////////////////// metodo para poner ultima carta
    @Override
    public boolean validateLastCard(String nameComp, String playerComp) throws RemoteException {
        boolean compBool = false;
      

        for (int c = 0; c < players.size(); c++) {
            if (players.get(c).getName().equals(playerComp)) {
                if (validateCard(c, nameComp)) {
                    compBool = true;
                    break;
                }

                break;

            }

        }
        return compBool;
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private ArrayList<Card> getHandOfPlayer(int playerIndex) {
        ArrayList<Card> playerHand=players.get(playerIndex).getHand();
        return playerHand;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private boolean validateCard(int playerIndex, String cardNameComp) {
        ArrayList<Card> handOfPlayer = getHandOfPlayer(playerIndex);
        Card compCard=null;

        boolean compBool = false;
        for (int d = 0; d < (handOfPlayer.size()); d++) {

            compCard = handOfPlayer.get(d);
            
           
            char compChar=compCard.getName().charAt(compCard.getName().length()-1);
            char lastChar=lastCard.getName().charAt(lastCard.getName().length()-1);
        
            //Compara si la carta con el lastCard y valida si se puede jugar la card
            if (compCard.getName().equals(cardNameComp)) {

                if (compCard.getType().equals(ECardType.WILD)) {
                    compBool = true;
                    
                    //////////// 

                } else if (compCard.getType().equals(ECardType.WILDDRAW4)) {
                    compBool = true;

                    //////////// meter metodo para tomar 4
                } else if (compCard.getColor() == lastCard.getColor()) {
                    compBool = true;

                    //////////// meter metodo que ejecute otros metodos segun el tipo, ya sea tome 2, skip, reversa
                } //else if (Character.isDigit(compChar)&&Character.isDigit(lastChar)){
                    if(compChar==lastChar){
                        compBool = true;
                    }
               // }
                break;
                
            }
            /////////////////////////////////////////////////////////////////////////////////////////
        }
        
        
        if (compBool == true) {
                setLastCard(compCard);
                handOfPlayer.remove(compCard);
            }
        return compBool;
    }

    private void setLastCard(Card lastCard) {
        this.lastCard = lastCard;
    }

  
    @Override
    public String getLastCardPlayed() throws RemoteException {
        return lastCard.getImageName();
    }

    @Override
    public String dealCardForPlayer(String playerName) throws RemoteException {
        String cardRet = "";
      
        if (deckList.size() == 0) {
            deckList = deck.generateDeck();

        }
        for (int pb = 0; pb < players.size(); pb++) {
            if (players.get(pb).getName().equals(playerName)) {
                Card reqCard = deckList.get(0);
                deckList.remove(0);
                players.get(pb).setCard(reqCard);
                cardRet = reqCard.getImageName();

            }
        }
        return cardRet;
    }

    @Override
    public void notifyColor(String color) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Notify(String message) {
        MainServer noti = new MainServer();
        Notificacion ficacion = noti.new Notificacion();
        ficacion.sendNotifi(message);
    }

    @Override
    public ArrayList<String> getPlayersNames()throws RemoteException{
        String playerInfo;
        ArrayList<String> cardsOfAllPlayers= new ArrayList<String>();
        for(int p=0;p<players.size();p++){
            playerInfo=players.get(p).getName();
            cardsOfAllPlayers.add(playerInfo);  
        }
        return cardsOfAllPlayers; 
    }

    @Override
    public ArrayList<String> getPlayersNumberOfCards() throws RemoteException {
        String numberOfCards;
        ArrayList<String> cardsQuantity= new ArrayList<String>();
        for(int p=0;p<players.size();p++){
            numberOfCards=Integer.toString(players.get(p).getHand().size());
            cardsQuantity.add(numberOfCards);  
        }
        return cardsQuantity; 
        
    }
}
    
   
