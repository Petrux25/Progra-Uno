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
                IRMIService remoteService=(IRMIService)Naming.lookup("//192.168.100.2:9999/IRMIService");
                UnoClient client=new UnoClient();
                remoteService.addObserver(client);
                
                uno=(IRemoteUno)Naming.lookup("//192.168.100.2:9998/Uno");
              
                System.out.println(uno.mensaje());
               
                
            }catch(Exception e){
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(null,"Error al conectar, el servidor no se encuentra disponible");
                jpScreen.setVisible(false);
                
                
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
    public static IRemoteUno getUno(){
        return uno;
    }
    

}
/*class JsonEncodeDemo {

   public static JSONObject prueba()  throws JSONException{
      JSONObject obj = new JSONObject();

      obj.put("name", "foo");
      obj.put("num", new Integer(100));
      obj.put("balance", new Double(1000.21));
      obj.put("is_vip", new Boolean(true));

      return obj;
   }
}*/
