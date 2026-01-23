package cl.rvillalon;

import cl.rvillalon.Vista.Vista;

public class ClasePrincipal {
    public static void main(String [] args){
        Hilo hilo1 = new Hilo();
        javax.swing.SwingUtilities.invokeLater(hilo1);
    }
}

class Hilo implements Runnable{
    @Override
    public void run() {
        Vista vista = new Vista();
    }
}
