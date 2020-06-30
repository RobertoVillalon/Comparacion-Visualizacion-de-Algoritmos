package Vista;

import Controlador.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class ComparacionOrdenamientos extends JPanel{
    Controlador ctrl;
    JPanel panelInterno = new JPanel();
    IniciarArregloOrdenamientos IA;
    PanelHeapSort PHS;
    PanelBubbleSort PBS;
    PanelMergeSort PMS;
    PanelQuickSort PQS;

    public ComparacionOrdenamientos(Controlador ctrl){
        this.ctrl = ctrl;
        IniciarPanel();
        IniciarPaneles();
    }

    private void IniciarPanel(){
        setBackground(Color.BLACK);
        setLayout(new BorderLayout());
    }
    
    private void IniciarPaneles(){
        IA = new IniciarArregloOrdenamientos(ctrl);
        PHS = new PanelHeapSort(ctrl);
        PBS = new PanelBubbleSort(ctrl);
        PMS = new PanelMergeSort(ctrl);
        PQS = new PanelQuickSort(ctrl);
        IA.setPreferredSize(new Dimension(150, 150));
        add(IA,BorderLayout.NORTH); 
        panelInterno.add(PHS);
        panelInterno.add(PBS);
        panelInterno.add(PMS);
        panelInterno.add(PQS);
        panelInterno.setLayout(new GridLayout(2, 0));
        add(panelInterno,BorderLayout.CENTER);
    }
}
