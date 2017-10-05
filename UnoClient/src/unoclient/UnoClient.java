/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoclient;

import gui_client.ClientGUI;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import uno_interface.IRemoteUno;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author diesv
 */
public class UnoClient extends Observable implements Observer {

    @Override
    public void update(Observable o, Object o1) {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Connect();
    }
    public static void Connect(){
         try{
            String name = "UNO";
            Registry registry = LocateRegistry.getRegistry("192.168.100.4",1099);
            IRemoteUno test = (IRemoteUno) registry.lookup(name);
            System.out.println(test.mensaje());
            
           // registry.lookup("holap amiguito");
            
        }catch(Exception e){
            System.err.println("Error en el cliente ");
            e.printStackTrace();
            
        }
    }

}
