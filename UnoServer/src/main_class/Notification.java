/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main_class;

import java.util.Observable;

/**
 *
 * @author diesv
 */
public class Notification extends Observable {
     
    static Notification noti = new Notification();

    public static Notification getInstance() {
        return noti;
    }

    public void sendNotifi(String message) {
        setChanged();
        notifyObservers("n" + message);

    }

    public Notification() {

    }
    
    
}
