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

/**
 *
 * @author diesv
 */
public class UnoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ClientGUI a=new ClientGUI();
        a.setVisible(true);
        // TODO code application logic here
        try{
            String name = "UNO";
            Registry registry = LocateRegistry.getRegistry("192.168.100.4",1099);
            IRemoteUno test = (IRemoteUno) registry.lookup(name);
            System.out.println(test.mensaje());
        }catch(Exception e){
            System.err.println("Error en el cliente ");
            e.printStackTrace();
        }
    }
    
}
