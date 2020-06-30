package Vista;

import Controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalBorders;

public class PanelBubbleSort extends JPanel{
    Controlador ctrl;
    public static int[] arreglo;
    public static JLabel labelArreglo = new JLabel(),labelTiempo = new JLabel(),complejidad = new JLabel();
    JPanel panelDatos = new JPanel();
    public static JPanel panelGrafico = new JPanel();
    
    public PanelBubbleSort(Controlador ctrl){
        this.ctrl = ctrl;
        IniciarPanel();
        IniciarComponentes();
        
    }
    
    private void IniciarPanel() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(new MetalBorders.PaletteBorder(),"Bubble-Sort"));
        setBackground(Color.WHITE);
    }
    
    private void IniciarComponentes(){
        IniciarPanelDatos();
        IniciarPanelGrafico();
    }

    private void IniciarPanelDatos(){
        complejidad.setText("Complejidad: Ω(n²)");complejidad.setHorizontalAlignment(JLabel.CENTER);
        labelArreglo.setText("");labelArreglo.setHorizontalAlignment(JLabel.CENTER);
        labelTiempo.setText("");labelTiempo.setHorizontalAlignment(JLabel.CENTER);
        
        panelDatos.setBorder(new TitledBorder(null,"Datos",2, 0));
        panelDatos.setBackground(Color.WHITE);
        panelDatos.setLayout(new GridLayout(0,1)); 
        panelDatos.add(complejidad);
        panelDatos.add(labelArreglo);
        panelDatos.add(labelTiempo);

        panelDatos.setPreferredSize(new Dimension(100,100));
        add(panelDatos,BorderLayout.NORTH);
    }
    
    private void IniciarPanelGrafico(){
        panelGrafico.setBorder(new TitledBorder(null,"Funcionamiento Algoritmo",2, 0));
        panelGrafico.setBackground(Color.WHITE);
        panelGrafico.setLayout(new BorderLayout());
        add(panelGrafico);
    }
  

}
