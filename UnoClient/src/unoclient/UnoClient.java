/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoclient;

import gui_client.ClientGUI;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import uno_interface.IRemoteUno;
import java.util.Observable;
import java.util.Observer;
import uno_interface.IRMIService;
import uno_interface.IRemoteObserver;

/**
 *
 * @author diesv
 */
public class UnoClient extends UnicastRemoteObject implements IRemoteObserver{
    
    protected UnoClient() throws RemoteException{
        super();
    }
    
    private static final long serialVersionUID=1L;
    public static void main(String[] args) {
        if(System.getSecurityManager()==null){
            System.setSecurityManager(new RMISecurityManager());
            try{
                IRMIService remoteService=(IRMIService)Naming.lookup("//localhost:9999/IRMIService");
                UnoClient client=new UnoClient();
                remoteService.addObserver(client);
                
            }catch(Exception e){
                e.printStackTrace();
                
            }
        
        }
     
    }
   /* public static void Connect(){
         try{
            String name = "UNO";
            
            Registry registry = LocateRegistry.getRegistry("25.66.13.91",1099);
            IRemoteUno test = (IRemoteUno) registry.lookup(name);
         
           
            System.out.println(test.mensaje());
            
       
            
        }catch(Exception e){
            System.err.println("Error en el cliente ");
            e.printStackTrace();
            
        }
    }*/

    @Override
    public void update(Object observable, Object updateMsg) throws RemoteException {
        System.out.println("got message"+updateMsg); //To change body of generated methods, choose Tools | Templates.
    }

}
