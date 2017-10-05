
import cards.Card;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import player.Player;
import uno_interface.IRemoteUno;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andpi
 */
public class MainServer {
    public MainServer(){
        try{
            String name="UNO";
            IRemoteUno interfaceUno=new Card();
            IRemoteUno stub=(IRemoteUno) UnicastRemoteObject.exportObject(interfaceUno,0);
            
    
            Registry registry=LocateRegistry.createRegistry(1099);
            
            registry.rebind(name,stub);
            System.out.println("Server running");
            
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new MainServer();
        Player player=new Player("caca","1213");
        System.out.println(player.getName());
        Player player2=new Player("culo","1213");
        System.out.println(player2.getName());
        // TODO code application logic here
    }
    
}
