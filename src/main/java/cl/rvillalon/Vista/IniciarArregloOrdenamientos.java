package cl.rvillalon.Vista;

import cl.rvillalon.Controlador.Controlador;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicBorders;

public class IniciarArregloOrdenamientos extends JPanel{
    JPanel panelDatos = new JPanel(),panelProcesos = new JPanel();
    public static JFileChooser selector;
    public static FileFilter filtro = new FileNameExtensionFilter("Archivos Separados por Comas (.csv)", "csv");
    public static int[] arreglo = null;
    public static JLabel labelValidacionArreglo;
    Controlador ctrl;
    public static JTextField fieldArreglo = new JTextField();
    public static JButton obtenerArchivo = new JButton("Abrir"), iniciarProcesos = new BasicArrowButton(SwingConstants.EAST, Color.WHITE, Color.BLACK, Color.BLACK, Color.BLACK);
    JLabel labelInicioGeneral = new JLabel("Iniciar Procesos"),labelArreglo = new JLabel("Datos"),labelDigitacion = new JLabel("Datos por teclado"),labelArchivo = new JLabel("Datos por archivo.csv");
    
    public IniciarArregloOrdenamientos(Controlador ctrl){
        this.ctrl = ctrl;
        IniciarPanel();
        IniciarComponentes();
        ConectaControlador(ctrl);
    }

    private void IniciarPanel() {
        setLayout(new GridLayout(0, 2));
        setBackground(Color.WHITE);
    }

    private void IniciarComponentes(){
        IniciarSubPaneles();
        IniciarLabels();
        IniciarField();
        ImprimirArreglo();
        IniciarBotones();
        AgregarComponentes();
    }
    
    private void IniciarSubPaneles() {
        panelDatos.setBorder(new TitledBorder("Obtencion de Datos"));
        panelDatos.setBackground(Color.WHITE);
        panelDatos.setLayout(new GridLayout(2, 0));
        
        panelProcesos.setBorder(new TitledBorder("Procesos"));
        panelProcesos.setBackground(Color.WHITE);
        panelProcesos.setLayout(new GridLayout(2, 0));
    }

    private void IniciarLabels(){
        labelArchivo.setHorizontalAlignment(JLabel.CENTER);
        labelDigitacion.setHorizontalAlignment(JLabel.CENTER);
        labelArreglo.setHorizontalAlignment(JLabel.CENTER);
        labelInicioGeneral.setHorizontalAlignment(JLabel.CENTER);
    }
    private void IniciarBotones(){
        obtenerArchivo.setActionCommand("obtenerArchivo");
        iniciarProcesos.setActionCommand("iniciarProcesosOrdenamientos");        
    }

    private void IniciarField(){
        fieldArreglo.setToolTipText("Digite los numeros y separelos por una coma, Luego pulse ENTER para guardar los datos");     
        fieldArreglo.setActionCommand("fieldArreglo");
        fieldArreglo.setHorizontalAlignment(JLabel.CENTER);
    }
    
    void ImprimirArreglo() {
        if(arreglo == null){
            labelValidacionArreglo = new JLabel("Arreglo Vacio");labelValidacionArreglo.setHorizontalAlignment(JLabel.CENTER);
            labelValidacionArreglo.setBorder(new BasicBorders.FieldBorder(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY));
        }
    }
          
    private void AgregarComponentes() {
        panelDatos.add(labelArchivo);
        panelDatos.add(obtenerArchivo);
        panelDatos.add(labelDigitacion);
        panelDatos.add(fieldArreglo);
        
        panelProcesos.add(labelArreglo);
        panelProcesos.add(labelValidacionArreglo);
        panelProcesos.add(labelInicioGeneral);
        panelProcesos.add(iniciarProcesos);
        
        add(panelDatos);
        add(panelProcesos);
    }
    
    private void ConectaControlador(Controlador ctrl){
        fieldArreglo.addKeyListener(ctrl);
        obtenerArchivo.addActionListener(ctrl);
        iniciarProcesos.addActionListener(ctrl);
    }
}
