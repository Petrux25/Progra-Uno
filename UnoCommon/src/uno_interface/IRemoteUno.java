package uno_interface;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andpi
 */
public interface IRemoteUno extends Remote{
    public String mensaje() throws RemoteException;
    public ArrayList<String> getHand() throws RemoteException; 
    
    
    
    
}
