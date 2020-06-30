package Vista;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedHashMap;
import javax.swing.*;

public class PanelDemostracionAlgoritmosBusqueda extends JPanel{
    int iBSecuencial,encontroBSecuencial = 0;//Datos Busqueda Secuencial
    int encontroBRandom = 0,aleatorioBRandom; //Datos Busqueda Random
    int medioBBinaria,encontroBBinaria = 0; //Datos Busqueda Binaria
    int largo,tiempo = 100;
    public int[] arreglo;
    public LinkedHashMap<Integer, Integer> hashMap;
    public String tipo;

    public PanelDemostracionAlgoritmosBusqueda(int largo) {
        this.largo = largo;
        IniciarPanel();
    }

    void IniciarPanel(){
        setVisible(true);
        setBackground(Color.WHITE);
    }
    
    public int ObtenerDato(int[] arreglo){
        int datoLocal,i;
        
        i = (int)(Math.random() * ((arreglo.length - 1) - 0) + 0);
        datoLocal = arreglo[i];
        
        return datoLocal;
    }
    
    public int[] LlenarArreglo(){
        int arregloLocal[] = new int[20];
        
        for(int i = 0; i < arregloLocal.length; i++){
            arregloLocal[i] = (int)(Math.random() * (100 - 0) + 100);
        }
        
        return arregloLocal;
    }
    
    public int[] BubbleSort(int[] arreglo){
        int aux;
        for(int i = 0; i < arreglo.length; i++){
            for(int j = 0; j < arreglo.length - 1; j++){
                if(arreglo[j] > arreglo[j+1]){
                    aux = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = aux;
                }
            }
        }
        
        return arreglo;
    }
    
    public int BusquedaBinaria(int[] arreglo, int num) throws InterruptedException {
        int tamano = arreglo.length;
        int medio;
        
        if(tamano > 2)
            medio = tamano/2;            
        else
            medio = 0;
        if (arreglo[medio] == num){
            for(int j = 0; j < 10; j++){
                medioBBinaria = medio;
                encontroBBinaria = 1;
                this.repaint();
                Thread.sleep(tiempo);
                encontroBBinaria = 0;
                this.repaint();
                Thread.sleep(tiempo);
            }
            return arreglo[medio];
        }
        else if (tamano == 1){
            return -1;
        }
        else if (arreglo[medio] > num){
            this.arreglo = Arrays.copyOfRange(arreglo,0,medio);
            this.repaint();
            Thread.sleep(tiempo);
            return BusquedaBinaria(Arrays.copyOfRange(arreglo,0,medio),num);                
        }
        else{
            this.arreglo = Arrays.copyOfRange(arreglo,medio+1,tamano);
            this.repaint();
            Thread.sleep(tiempo);
           return BusquedaBinaria(Arrays.copyOfRange(arreglo,medio+1,tamano),num);               
        }
        
    }
    
    public int BusquedaHashing(LinkedHashMap<Integer, Integer> tablaHash, int num) throws InterruptedException{
        System.out.println(tablaHash.containsValue(num));
        if(tablaHash.containsValue(num)){
            this.repaint();
            Thread.sleep(tiempo);
            return 1;
        }
        
        return -1;
    }
    
    public int BusquedaRandom(int[] arreglo, int num) throws InterruptedException{
        
        while(true){
            aleatorioBRandom = (int)Math.floor(Math.random()*(arreglo.length)+0);
            this.repaint();
            Thread.sleep(100);
            if(arreglo[aleatorioBRandom] == num){
                for(int j = 0; j < 10; j++){
                    encontroBRandom = 1;
                    this.repaint();
                    Thread.sleep(tiempo);
                    encontroBRandom = 0;
                    this.repaint();
                    Thread.sleep(tiempo);
                }
                
                return aleatorioBRandom;
            }
        }
    }
    
    public int BusquedaSecuencial(int[] arreglo, int num) throws InterruptedException{   
        
        for(iBSecuencial = 0; iBSecuencial < arreglo.length; iBSecuencial++){
            this.repaint();
            Thread.sleep(100);
            if(arreglo[iBSecuencial] == num){
                for(int i = 0; i < 10; i++){
                    encontroBSecuencial = 1;
                    this.repaint();
                    Thread.sleep(tiempo);
                    encontroBSecuencial = 0;
                    this.repaint();
                    Thread.sleep(tiempo);
                }
                return iBSecuencial;
            }
        }
        
        return -1;
    }

    @Override
    public void paint(Graphics g){
        int tam = 30;
        double X = 5;
        double Y = getHeight();
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        switch(tipo){
            case "BBinaria":
                for (int i = 0; i < arreglo.length; i++){
                    if(encontroBBinaria == 1){
                        g2d.drawRect((int)X + (i*tam), (int)Y - arreglo[i] - 15, tam, arreglo[i]);
                        g2d.drawString(""+arreglo[i],((int)X + (i * tam) + 3), (int)Y);    
                    }
                    else if(i == medioBBinaria){
                        g2d.fillRect((int)X + (i*tam), (int)Y - arreglo[i] - 15, tam, arreglo[i]);
                        g2d.drawString(""+arreglo[i],((int)X + (i * tam) + 3), (int)Y);      
                    }
                    else{
                        g2d.drawRect((int)X + (i*tam), (int)Y - arreglo[i] - 15, tam, arreglo[i]);
                        g2d.drawString(""+arreglo[i],((int)X + (i * tam) + 3), (int)Y);                    
                    }
                }   
            break;
            case "BSecuencial":
                for (int i = 0; i < arreglo.length; i++){
                    if(encontroBSecuencial == 1){
                        g2d.drawRect((int)X + (i*tam), (int)Y - arreglo[i] - 15, tam, arreglo[i]);
                        g2d.drawString(""+arreglo[i],((int)X + (i * tam) + 3), (int)Y);    
                    }
                    else if(i == iBSecuencial){
                        g2d.fillRect((int)X + (i*tam), (int)Y - arreglo[i] - 15, tam, arreglo[i]);
                        g2d.drawString(""+arreglo[i],((int)X + (i * tam) + 3), (int)Y);      
                    }
                    else{
                        g2d.drawRect((int)X + (i*tam), (int)Y - arreglo[i] - 15, tam, arreglo[i]);
                        g2d.drawString(""+arreglo[i],((int)X + (i * tam) + 3), (int)Y);                    
                    }
                }   
            break;
            case "BRandom":
                for (int i = 0; i < arreglo.length; i++){
                    if(encontroBRandom == 1){
                        g2d.drawRect((int)X + (i*tam), (int)Y - arreglo[i] - 15, tam, arreglo[i]);
                        g2d.drawString(""+arreglo[i],((int)X + (i * tam) + 3), (int)Y);    
                    }
                    else if(i == aleatorioBRandom){
                        g2d.fillRect((int)X + (i*tam), (int)Y - arreglo[i] - 15, tam, arreglo[i]);
                        g2d.drawString(""+arreglo[i],((int)X + (i * tam) + 3), (int)Y);      
                    }
                    else{
                        g2d.drawRect((int)X + (i*tam), (int)Y - arreglo[i] - 15, tam, arreglo[i]);
                        g2d.drawString(""+arreglo[i],((int)X + (i * tam) + 3), (int)Y);                    
                    }
                }
            break;
            case "BHashing":      
            break;
        }
    }
}
