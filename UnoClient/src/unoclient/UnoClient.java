/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoclient;

import gui_client.ClientGUI;

import gui_client.JPlayerScreen;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import uno_interface.IRemoteUno;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import uno_interface.IRMIService;
import uno_interface.IRemoteObserver;
import javax.swing.JOptionPane;


/**
 *
 * @author diesv
 */
public class UnoClient extends UnicastRemoteObject implements IRemoteObserver{
    static IRemoteUno uno;
    
    protected UnoClient() throws RemoteException{
        super();
    }
  
    private static final long serialVersionUID=1L;
    public static void main(String[] args) {
        System.setProperty("java.security.policy","file:./java.policy");
        
        
        JPlayerScreen jpScreen=new JPlayerScreen();
        jpScreen.setVisible(true);
        
      
        
            if(System.getSecurityManager()==null){
            System.setSecurityManager(new RMISecurityManager());
            try{
                IRMIService remoteService=(IRMIService)Naming.lookup("//192.168.100.8:9999/IRMIService");
                UnoClient client=new UnoClient();
                remoteService.addObserver(client);
                
                uno=(IRemoteUno)Naming.lookup("//192.168.100.8:9998/Uno");
            
            }catch(Exception e){
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null,"Error al conectar, el servidor no se encuentra disponible");
                jpScreen.setVisible(false);
                
                
            }
        
        }
     
    }

    @Override
    public void update(Object observable, Object updateMsg) throws RemoteException {
        System.out.println("HOLA "+updateMsg);
        ClientGUI client = ClientGUI.getInstance();
        client.setLastCardPlayed();
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            Logger.getLogger(UnoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(updateMsg.toString().startsWith("n")){
            System.out.println("Se cambio el color");
        }
        
        int up=getUno().isChanged();
        if(up==1){
            JOptionPane.showMessageDialog(null, "El color se ha cambiado");
            System.out.println("holaaaaaa");
        }
        if(up==2){
            JOptionPane.showMessageDialog(null, "Sudo kill duglot");
        }
            
        client.updateDeckView();
        client.setPlayersInfo();
        
    }
    public static IRemoteUno getUno(){
        return uno;
    }
    

}
