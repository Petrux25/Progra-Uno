/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoclient;

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
        // TODO code application logic here
        try{
            String name = "UNO";
            Registry registry = LocateRegistry.getRegistry("25.7.195.205");
            IRemoteUno test = (IRemoteUno) registry.lookup(name);
            System.out.println(test);
        }catch(Exception e){
            System.err.println("Error en el cliente ");
            e.printStackTrace();
        }
    }
    
}
