package cl.rvillalon.Vista;

import cl.rvillalon.Controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

public class Vista extends JFrame{
    public static JPanel panelPrincipal = new JPanel();
    Controlador ctrl = new Controlador();
    JTabbedPane navPaneles = new JTabbedPane();
    ComparacionOrdenamientos co = new ComparacionOrdenamientos(ctrl);
    ComparacionDeBusquedas cb = new ComparacionDeBusquedas(ctrl);

    public Vista() {
        IniciarVentana();
        IniciarComponentes();
    }
    
    private void IniciarVentana() {
        setSize(1280, 960);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }


    private void IniciarComponentes(){
        IniciarPanel();
        IniciarJLabbedPane();
    }
    
    private void IniciarPanel() {
        getContentPane().add(panelPrincipal);
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setLayout(new BorderLayout());
    }
    
    private void IniciarJLabbedPane(){
        navPaneles.add("Ordenamientos",co);
        navPaneles.add("Busquedas",cb);
        panelPrincipal.add(navPaneles,BorderLayout.CENTER);
    }


    
    
    
}
