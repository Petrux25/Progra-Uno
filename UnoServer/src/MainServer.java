
import cards.Card;
import java.io.Serializable;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import player.Player;
import uno_interface.IRemoteUno;
import uno_interface.IRMIService;
import uno_interface.IRemoteObserver;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andpi
 */
public class MainServer extends Observable implements IRMIService {
   /*ublic MainServer(){
        
        
        
        
        
        
        
        try{
            String name="UNO";
            IRemoteUno interfaceUno=new Card();
            IRemoteUno stub=(IRemoteUno) UnicastRemoteObject.exportObject(interfaceUno,0);
            
    
            Registry registry=LocateRegistry.createRegistry(1099);
            
            registry.rebind(name,stub);
            System.out.println("Server running");
            
            
            
            
        }catch(Exception e){
            e.printStackTrace();
    }*/
   
    
    private class WrappedObserver implements Observer, Serializable{
        private static final long serilVersionUID=1L;
        private IRemoteObserver remoteObserver=null;
        public WrappedObserver(IRemoteObserver remoteObserver){
            this.remoteObserver=remoteObserver;
            
        }
        
        

        @Override
        public void update(Observable o, Object arg) {
          try{
              remoteObserver.update(o.toString(), arg);
              
          }catch(Exception e){
              System.out.println("Remote exception removing observer:"+this);
              o.deleteObserver(this);
              
          }
        }
        
    }

    /**
     * @param args the command line arguments
     */
  

    @Override
    public void addObserver(IRemoteObserver o) throws RemoteException {
        WrappedObserver mObserver =new WrappedObserver(o);
        addObserver(mObserver);
        System.out.println("Added observer"+mObserver);
    }
    Thread thread=new Thread(){
        
        @Override
        public void run(){
            while(true){
                try{
                    Thread.sleep(5*1000);
                }catch(Exception e){
                    //
                    
                }
                setChanged();
                notifyObservers(new Date());
            }
        };
    };
    public MainServer(){
        thread.start();
        
    }
    
      public static void main(String[] args) {
          if(System.getSecurityManager()==null){
              System.setSecurityManager(new RMISecurityManager());
          }
          
          try{
              Registry rmiRegistry=LocateRegistry.createRegistry(9999);
             
              IRMIService rmiService = (IRMIService) UnicastRemoteObject.exportObject(new MainServer(),9999);
              rmiRegistry.bind("IRMIService",rmiService);
              
          }catch(Exception e){
              e.printStackTrace();
          }
        /* MainServer();
        Player player=new Player("caca","1213");
        System.out.println(player.getName());
        Player player2=new Player("culo","1213");
        System.out.println(player2.getName());
        // TODO code application logic here*/
    }
    
    
}
