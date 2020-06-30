package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.metal.MetalBorders;

public class PanelBHashing extends JPanel{
    public static int[] arreglo;
    public static JLabel labelComplejidad = new JLabel(),labelConfirmacion = new JLabel(),labelTiempo = new JLabel(), labelNodisponible = new JLabel("Visualizacion No Disponible");
    JPanel panelDatos = new JPanel();
    public static JPanel panelGrafico = new JPanel();
    
    public PanelBHashing(){
        IniciarPanel();
        IniciarComponentes();   
    }
    
    private void IniciarPanel() {
        setLayout(new BorderLayout());
        setBorder(new TitledBorder(new MetalBorders.PaletteBorder(),"Busqueda Por Hashing"));
        setBackground(Color.WHITE);
    }
    
    private void IniciarComponentes(){
        IniciarPanelDatos();
        IniciarPanelGrafico();
    }

    private void IniciarPanelDatos(){
        labelComplejidad.setText("Complejidad: O(1)");labelComplejidad.setHorizontalAlignment(JLabel.CENTER);
        labelConfirmacion.setText("");labelConfirmacion.setHorizontalAlignment(JLabel.CENTER);
        labelTiempo.setText("");labelTiempo.setHorizontalAlignment(JLabel.CENTER);
        panelDatos.setBorder(new TitledBorder(null,"Datos",2, 0));
        panelDatos.setLayout(new GridLayout(0,1));
        panelDatos.setBackground(Color.WHITE);
        panelDatos.setPreferredSize(new Dimension(100, 100));
        panelDatos.add(labelComplejidad);
        panelDatos.add(labelConfirmacion);
        panelDatos.add(labelTiempo);
        add(panelDatos,BorderLayout.NORTH);
    }
    
    private void IniciarPanelGrafico(){
        panelGrafico.setBorder(new TitledBorder(null,"Funcionamiento Algoritmo",2, 0));
        panelGrafico.setBackground(Color.WHITE);
        panelGrafico.setLayout(new BorderLayout());
        labelNodisponible.setHorizontalAlignment(JLabel.CENTER);
        add(panelGrafico);
    }

}
