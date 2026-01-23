package cl.rvillalon.Vista;

import cl.rvillalon.Controlador.Controlador;

import java.awt.*;
import javax.swing.*;

public class ComparacionDeBusquedas extends JPanel{
    Controlador ctrl;
    JPanel panelInterno = new JPanel();
    IniciarArregloBusquedas IA;
    PanelBBinaria PBB;
    PanelBRandom PBR;
    PanelBSecuencial PBS;
    PanelBHashing PBH;
    
    public ComparacionDeBusquedas(Controlador ctrl){
        this.ctrl = ctrl;
        IniciarPanel();
        IniciarPaneles();
    }

    private void IniciarPanel(){
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
    }
    
    void IniciarPaneles(){
        IA = new IniciarArregloBusquedas(ctrl);
        PBB = new PanelBBinaria();
        PBR = new PanelBRandom();
        PBS = new PanelBSecuencial();
        PBH = new PanelBHashing();
        IA.setPreferredSize(new Dimension(150, 150));
        add(IA,BorderLayout.NORTH);
        panelInterno.add(PBB);
        panelInterno.add(PBR);
        panelInterno.add(PBS);
        panelInterno.add(PBH);
        panelInterno.setLayout(new GridLayout(2, 0));
        add(panelInterno,BorderLayout.CENTER);
    }
}
