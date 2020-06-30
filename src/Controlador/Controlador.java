package Controlador;

import Modelo.Modelo;
import Vista.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

public class Controlador implements ActionListener,KeyListener{
    Modelo modelo = new Modelo();
    
    @Override
    public void actionPerformed(ActionEvent ae){
        String accion = ae.getActionCommand();
        
        switch(accion){
            case "obtenerArchivo":
                if(ae.getSource() == IniciarArregloOrdenamientos.obtenerArchivo){
                    IniciarArregloOrdenamientos.selector = new JFileChooser();
                    IniciarArregloOrdenamientos.selector.setFileFilter(IniciarArregloOrdenamientos.filtro);
                    IniciarArregloOrdenamientos.selector.showOpenDialog(IniciarArregloOrdenamientos.selector);
                    
                    try{
                        String path = IniciarArregloOrdenamientos.selector.getSelectedFile().getAbsolutePath();
                        int arreglo[] = modelo.LecturaArchivo(path);
                        IniciarArregloOrdenamientos.arreglo = arreglo;
                        String aux = "";
                        for(int i = 0; i < arreglo.length; i++){
                            aux+=arreglo[i];
                            if(i + 1 != arreglo.length){
                                aux+="-";   
                            }
                            IniciarArregloOrdenamientos.labelValidacionArreglo.setText(aux);
                            IniciarArregloOrdenamientos.iniciarProcesos.setEnabled(true);

                        }
                    }catch(NullPointerException e){}
                }
                
                if(ae.getSource() == IniciarArregloBusquedas.obtenerArchivo){
                    IniciarArregloBusquedas.selector = new JFileChooser();
                    IniciarArregloBusquedas.selector.setFileFilter(IniciarArregloBusquedas.filtro);
                    IniciarArregloBusquedas.selector.showOpenDialog(IniciarArregloBusquedas.selector);
                    
                    try{
                        String path = IniciarArregloBusquedas.selector.getSelectedFile().getAbsolutePath();
                        int arreglo[] = modelo.LecturaArchivo(path);
                        IniciarArregloBusquedas.arreglo = arreglo;
                        String aux = "";
                        for(int i = 0; i < arreglo.length; i++){
                            aux+=arreglo[i];
                            if(i + 1 != arreglo.length){
                                aux+="-";   
                            }
                            IniciarArregloBusquedas.labelValidacionArreglo.setText(aux);
                            IniciarArregloBusquedas.iniciarProcesos.setEnabled(true);
                        }
                    }catch(NullPointerException e){}
                }
            break;
            case "obtenerDatosOrdenamientos":
                int arreglo[] = IniciarArregloOrdenamientos.arreglo;
                IniciarArregloBusquedas.arreglo = arreglo;
                String aux = "";
                if(IniciarArregloBusquedas.arreglo == null){
                    JOptionPane.showMessageDialog(null, "No hay datos disponibles", "Error", 2);
                    break;
                }else{
                    for(int i = 0; i < IniciarArregloBusquedas.arreglo.length; i++){
                        aux+=IniciarArregloBusquedas.arreglo[i];
                        if(i + 1 != IniciarArregloBusquedas.arreglo.length){
                            aux+="-";   
                        }
                    }
                    IniciarArregloBusquedas.labelValidacionArreglo.setText(aux);
                }

            break;
            case "iniciarProcesosOrdenamientos":
                
                if(IniciarArregloOrdenamientos.labelValidacionArreglo.getText().equals("Arreglo Vacio")){
                    JOptionPane.showMessageDialog(null, "Arreglo no iniciado","Error",2);
                }
                else{
                    PanelBubbleSort.panelGrafico.removeAll();PanelHeapSort.panelGrafico.removeAll();PanelMergeSort.panelGrafico.removeAll();PanelQuickSort.panelGrafico.removeAll();
                    Thread bubbleSort = CrearHilo("Bubble-Sort");
                    Thread heapSort = CrearHilo("Heap-Sort");
                    Thread mergeSort = CrearHilo("Merge-Sort");
                    Thread quickSort = CrearHilo("Quick-Sort");
                    PanelBubbleSort.arreglo = PanelHeapSort.arreglo = PanelMergeSort.arreglo = PanelQuickSort.arreglo = IniciarArregloOrdenamientos.arreglo;
                    PanelBubbleSort.labelArreglo.setText("Cantidad de datos ordenados: "+PanelBubbleSort.arreglo.length);
                    PanelHeapSort.labelArreglo.setText("Cantidad de datos ordenados: "+PanelHeapSort.arreglo.length);
                    PanelMergeSort.labelArreglo.setText("Cantidad de datos ordenados: "+PanelMergeSort.arreglo.length);
                    PanelQuickSort.labelArreglo.setText("Cantidad de datos ordenados: "+PanelQuickSort.arreglo.length);
                    long tiempoBubbleSort = -1,tiempoHeapSort = -1,tiempoMergeSort = -1,tiempoQuickSort = -1;
                        tiempoBubbleSort = modelo.TiempoEjecucionOrdenamientos("BubbleSort", PanelBubbleSort.arreglo);
                        PanelBubbleSort.labelTiempo.setText("Tiempo en Ordenar(ms): "+tiempoBubbleSort);
                        tiempoHeapSort = modelo.TiempoEjecucionOrdenamientos("HeapSort", PanelHeapSort.arreglo);
                        PanelHeapSort.labelTiempo.setText("Tiempo en Ordenar(ms): "+tiempoHeapSort);
                        tiempoMergeSort = modelo.TiempoEjecucionOrdenamientos("MergeSort", PanelMergeSort.arreglo);
                        PanelMergeSort.labelTiempo.setText("Tiempo en Ordenar(ms): "+tiempoMergeSort);
                        tiempoQuickSort = modelo.TiempoEjecucionOrdenamientos("QuickSort", PanelQuickSort.arreglo);
                        PanelQuickSort.labelTiempo.setText("Tiempo en Ordenar(ms): "+tiempoQuickSort);
                    
                    IniciarArregloOrdenamientos.iniciarProcesos.setEnabled(false);
                    VentanaGraficosTiempo VGT = new VentanaGraficosTiempo(tiempoBubbleSort, tiempoHeapSort, tiempoMergeSort, tiempoQuickSort, "Ordenamientos");
                    ComenzarHilo(bubbleSort);
                    ComenzarHilo(heapSort);
                    ComenzarHilo(mergeSort);
                    ComenzarHilo(quickSort);
                }
            break;
            
            case "iniciarProcesosBusquedas":
                if(IniciarArregloBusquedas.labelValidacionArreglo.getText().equals("Arreglo Vacio")){
                    JOptionPane.showMessageDialog(null, "Arreglo no iniciado","Error",2);
                }
                else{
                    int dato = Integer.parseInt(IniciarArregloBusquedas.fieldDato.getText());
                    
                    if(modelo.BusquedaSecuencial(IniciarArregloBusquedas.arreglo, dato) == -1){
                        PanelBBinaria.labelConfirmacion.setText("Dato: No Encontrado");
                        PanelBRandom.labelConfirmacion.setText("Dato: No Encontrado");
                        PanelBHashing.labelConfirmacion.setText("Dato: No Encontrado");
                        PanelBSecuencial.labelConfirmacion.setText("Dato: No Encontrado");   
                    }
                    else{
                        Thread busquedaBinaria = CrearHilo("BBinaria");
                        Thread busquedaRandom = CrearHilo("BRandom");
                        Thread busquedaHashing = CrearHilo("BHashing");
                        Thread busquedaSecuencial = CrearHilo("BSecuencial");
                        PanelBBinaria.arreglo = PanelBHashing.arreglo = PanelBRandom.arreglo = PanelBSecuencial.arreglo = IniciarArregloBusquedas.arreglo;
                        PanelBBinaria.panelGrafico.removeAll();PanelBRandom.panelGrafico.removeAll();PanelBHashing.panelGrafico.removeAll();PanelBSecuencial.panelGrafico.removeAll();
                        PanelBRandom.labelConfirmacion.setText("Dato: Encontrado");
                        PanelBHashing.labelConfirmacion.setText("Dato: Encontrado");
                        PanelBSecuencial.labelConfirmacion.setText("Dato: Encontrado");
                        long tiempoBBinaria = -1,tiempoBSecuencial = -1,tiempoBRandom = -1,tiempoBHashing = -1;
                            tiempoBBinaria = modelo.TiempoEjecucionBusquedas("BBinaria", PanelBBinaria.arreglo,dato);
                            if(tiempoBBinaria == -1){
                                PanelBBinaria.labelConfirmacion.setText("Dato: Error");
                                PanelBBinaria.labelTiempo.setText("Error en la busqueda Binaria, Procure ordenar");
                            }
                            else{
                                PanelBBinaria.labelConfirmacion.setText("Dato: Encontrado");
                                PanelBBinaria.labelTiempo.setText("Tiempo de Busqueda(ms): "+tiempoBBinaria);                                
                            }
                            tiempoBSecuencial = modelo.TiempoEjecucionBusquedas("BSecuencial", PanelBSecuencial.arreglo,dato);
                            PanelBSecuencial.labelTiempo.setText("Tiempo de Busqueda(ms): "+tiempoBSecuencial);
                            tiempoBRandom = modelo.TiempoEjecucionBusquedas("BRandom", PanelBRandom.arreglo,dato);
                            PanelBRandom.labelTiempo.setText("Tiempo de Busqueda(ms): "+tiempoBRandom);
                            tiempoBHashing = modelo.TiempoEjecucionBusquedas("BHashing", PanelBHashing.arreglo,dato);
                            PanelBHashing.labelTiempo.setText("Tiempo de Busqueda(ms): "+tiempoBHashing);

                        IniciarArregloOrdenamientos.iniciarProcesos.setEnabled(false);
                        VentanaGraficosTiempo VGT = new VentanaGraficosTiempo(tiempoBBinaria, tiempoBSecuencial, tiempoBRandom, tiempoBHashing, "Busquedas");
                        ComenzarHilo(busquedaBinaria);
                        ComenzarHilo(busquedaHashing);
                        ComenzarHilo(busquedaRandom);
                        ComenzarHilo(busquedaSecuencial);
                        
                    }     
                }
            break;
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        int key = (int)ke.getKeyChar();
        
        if(key == 10){
            if(ke.getSource() == IniciarArregloOrdenamientos.fieldArreglo){
                String aux = IniciarArregloOrdenamientos.fieldArreglo.getText();
                int[] arreglo = modelo.LecturaCuadroTexto(aux);
                IniciarArregloOrdenamientos.arreglo = arreglo;
                aux = "";
                for(int i = 0; i < arreglo.length; i++){
                    aux+=arreglo[i];
                    if(i + 1 != arreglo.length){
                        aux+="-";   
                    }
                    IniciarArregloOrdenamientos.labelValidacionArreglo.setText(aux);
                    IniciarArregloOrdenamientos.iniciarProcesos.setEnabled(true);
                }
            }
            
            if(ke.getSource() == IniciarArregloBusquedas.fieldArreglo){
                String aux = IniciarArregloBusquedas.fieldArreglo.getText();
                int[] arreglo = modelo.LecturaCuadroTexto(aux);
                IniciarArregloBusquedas.arreglo = arreglo;
                aux = "";
                for(int i = 0; i < arreglo.length; i++){
                    aux+=arreglo[i];
                    if(i + 1 != arreglo.length){
                        aux+="-";   
                    }
                    IniciarArregloBusquedas.iniciarProcesos.setEnabled(true);
                    IniciarArregloBusquedas.labelValidacionArreglo.setText(aux);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent ke){}

    @Override
    public void keyReleased(KeyEvent ke){}
    
    String convertirArregloOrdenamientos(){
        String aux1 = "";
        
        for(int i = 0; i < IniciarArregloOrdenamientos.arreglo.length; i++){
            aux1+=PanelBubbleSort.arreglo[i];
            if(i + 1 != PanelBubbleSort.arreglo.length){
                aux1+="-";   
            }
        }
        
        return aux1;
    }
    
    String convertirArregloBusquedas(){
        String aux1 = "";
        
        for(int i = 0; i < IniciarArregloBusquedas.arreglo.length; i++){
            aux1+=PanelBBinaria.arreglo[i];
            if(i + 1 != PanelBBinaria.arreglo.length){
                aux1+="-";   
            }
        }
        
        return aux1;
    }
    
    boolean hiloGraficoOrdenamientos(String tipo){
        PanelDemostracionAlgoritmosOrdenamiento PDAO = new PanelDemostracionAlgoritmosOrdenamiento(20);
        switch(tipo){
            case "Bubble-Sort":
                PanelBubbleSort.panelGrafico.add(PDAO);
                PanelBubbleSort.panelGrafico.repaint();
                PanelBubbleSort.panelGrafico.revalidate();
                PDAO.arreglo = PDAO.LlenarArreglo();
                
                try {
                    PDAO.BubbleSort(PDAO.arreglo);
                    PDAO.repaint();
                    
                    return true;
                }catch (InterruptedException ex){
                    ex.getStackTrace();
                    return false;
                }
                
            case "Heap-Sort":
                PanelHeapSort.panelGrafico.add(PDAO);
                PanelHeapSort.panelGrafico.repaint();
                PanelHeapSort.panelGrafico.revalidate();
                PDAO.arreglo = PDAO.LlenarArreglo();
                
                try {
                    PDAO.HeapSort(PDAO.arreglo);
                    PDAO.repaint();
                    
                    return true;
                }catch (InterruptedException ex){
                    ex.getStackTrace();
                    return false;
                }
                
            case "Merge-Sort":
                PanelMergeSort.panelGrafico.add(PDAO);
                PanelMergeSort.panelGrafico.repaint();
                PanelMergeSort.panelGrafico.revalidate();
                PDAO.arreglo = PDAO.LlenarArreglo();
                    
                try {
                    PDAO.MergeSort(PDAO.arreglo,0,PDAO.arreglo.length - 1);
                    PDAO.repaint();
                    
                    return true;
                }catch (InterruptedException ex){
                    ex.getStackTrace();
                    return false;
                }

            case "Quick-Sort":
                PanelQuickSort.panelGrafico.add(PDAO);
                PanelQuickSort.panelGrafico.repaint();
                PanelQuickSort.panelGrafico.revalidate();
                PDAO.arreglo = PDAO.LlenarArreglo();
                
                try {
                    PDAO.QuickSort(PDAO.arreglo,0,PDAO.arreglo.length - 1);
                    PDAO.repaint();
                    
                    return true;
                }catch (InterruptedException ex){
                    ex.getStackTrace();
                    return false;
                }
                
        }
        
        return true;
    }
    
    boolean hiloGraficoBusquedas(String tipo){
        PanelDemostracionAlgoritmosBusqueda PDAB = new PanelDemostracionAlgoritmosBusqueda(20);
        int dato;
        switch(tipo){
            case "BBinaria":
                PanelBBinaria.panelGrafico.add(PDAB);
                PanelBBinaria.panelGrafico.repaint();
                PanelBBinaria.panelGrafico.revalidate();
                PDAB.arreglo = PDAB.LlenarArreglo();
                PDAB.arreglo = PDAB.BubbleSort(PDAB.arreglo);
                dato = PDAB.ObtenerDato(PDAB.arreglo);
                PDAB.tipo = "BBinaria";
                try {
                    PDAB.BusquedaBinaria(PDAB.arreglo, dato);         
                    PDAB.repaint();
                } catch (InterruptedException ex) {
                    ex.getStackTrace();
                    return false;
                }               
            break;
            case "BRandom":
                PanelBRandom.panelGrafico.add(PDAB);
                PanelBRandom.panelGrafico.repaint();
                PanelBRandom.panelGrafico.revalidate();
                PDAB.arreglo = PDAB.LlenarArreglo();
                dato = PDAB.ObtenerDato(PDAB.arreglo);
                PDAB.tipo = "BRandom";
                try {
                    PDAB.BusquedaRandom(PDAB.arreglo, dato);         
                    PDAB.repaint();
                } catch (InterruptedException ex) {
                    ex.getStackTrace();
                    return false;
                }
                
            break;
            case "BSecuencial":
                PanelBSecuencial.panelGrafico.add(PDAB);
                PanelBSecuencial.panelGrafico.repaint();
                PanelBSecuencial.panelGrafico.revalidate();
                PDAB.arreglo = PDAB.LlenarArreglo();
                dato = PDAB.ObtenerDato(PDAB.arreglo);
                PDAB.tipo = "BSecuencial";
                try {
                    PDAB.BusquedaSecuencial(PDAB.arreglo, dato);
                    PDAB.repaint();
                }catch (InterruptedException ex){
                    ex.getStackTrace();
                    return false;
                }

            break;
            case "BHashing":
                PanelBHashing.panelGrafico.add(PanelBHashing.labelNodisponible,BorderLayout.CENTER);
                PanelBHashing.panelGrafico.repaint();
                PanelBHashing.panelGrafico.revalidate();
            break;
        }
        
        return true;
    }
    
    Thread CrearHilo(String tipo){
        Thread heapSort = null;
        switch(tipo){
            case "Bubble-Sort":
               Thread bubbleSort = new Thread(() -> {
                    boolean valor = hiloGraficoOrdenamientos("Bubble-Sort");
                });
               
            bubbleSort.interrupt();
            return bubbleSort;
            
            case "Heap-Sort":
                heapSort = new Thread(() -> {
                    boolean valor = hiloGraficoOrdenamientos("Heap-Sort");
                });
                
                heapSort.interrupt();
                return heapSort; 
            case "Merge-Sort":
                Thread mergeSort = new Thread(() -> {
                   boolean valor = hiloGraficoOrdenamientos("Merge-Sort");
                });
                
                mergeSort.interrupt();
                return mergeSort;
            case "Quick-Sort":
                Thread quickSort = new Thread(() -> {
                    boolean valor = hiloGraficoOrdenamientos("Quick-Sort");   
                });
                
                quickSort.interrupt();
                return quickSort;
                
            case "BBinaria":
                Thread BBinaria = new Thread(() -> {
                   boolean valor = hiloGraficoBusquedas("BBinaria");
                    
                    
                });
            BBinaria.interrupt();
            return BBinaria;
            case "BSecuencial":
                Thread BSecuencial = new Thread(() -> {
                    boolean valor = hiloGraficoBusquedas("BSecuencial");
                    
                    
                });   
            BSecuencial.interrupt();
            return BSecuencial;
            case "BRandom":
                Thread BRandom = new Thread(() -> {
                    boolean valor = hiloGraficoBusquedas("BRandom");
                    
                    
                }); 
                
            BRandom.interrupt();
            return BRandom;
            case "BHashing":
                Thread BHashing = new Thread(() -> {
                    boolean valor = hiloGraficoBusquedas("BHashing");
                    
                    
                });
                
            BHashing.interrupt();
            return BHashing;
        }
        
        return null;
    }
    
    void ComenzarHilo(Thread hilo){
        hilo.start();
    }
}


