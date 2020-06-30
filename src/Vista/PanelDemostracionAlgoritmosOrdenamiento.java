package Vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;


public final class PanelDemostracionAlgoritmosOrdenamiento extends JPanel{
    int largo;
    public int[] arreglo;

    public PanelDemostracionAlgoritmosOrdenamiento(int largo) {
        this.largo = largo;
        IniciarPanel();

    }

    void IniciarPanel(){
        setVisible(true);
        setBackground(Color.WHITE);
    }
    
    public int[] LlenarArreglo(){
        int arregloLocal[] = new int[largo];
        
        for(int i = 0; i < arregloLocal.length; i++){
            arregloLocal[i] = (int)(Math.random() * (100 - 0) + 100);
        }
        
        return arregloLocal;
    }
    
    public int BubbleSort(int arreglo[]) throws InterruptedException{
        int aux;
        
        for(int i = 0; i < arreglo.length; i++){
            for(int j = 0; j < arreglo.length - 1; j++){
                if(arreglo[j] > arreglo[j+1]){
                    aux = arreglo[j];
                    repaint();
                    Thread.sleep(100);
                    arreglo[j] = arreglo[j + 1];
                    repaint();
                    Thread.sleep(100);
                    arreglo[j + 1] = aux;
                    repaint();
                    Thread.sleep(100);
                }
            }
        }
        
      return 1;
    }
    
    public int HeapSort(int arreglo[]) throws InterruptedException { 
        int n = arreglo.length; 
  
        for (int i = n / 2 - 1; i >= 0; i--) 
            apilarHeapSort(arreglo, n, i);
            repaint();
            Thread.sleep(100);
            
  
        for (int i = n-1; i >= 0; i--){ 
            int temp = arreglo[0]; 
            repaint();
            arreglo[0] = arreglo[i];
            repaint();
            arreglo[i] = temp;
            repaint();
            Thread.sleep(100);
  
            apilarHeapSort(arreglo, i, 0); 
        }
        
        return 1;
    } 
  
    void apilarHeapSort(int arregloR[], int n, int i) throws InterruptedException { 
        int mayor = i;
        int izq = 2*i + 1;
        int der = 2*i + 2;
  
        if (izq < n && arregloR[izq] > arregloR[mayor]) 
            mayor = izq; 
  
        if (der < n && arregloR[der] > arregloR[mayor]) 
            mayor = der; 
  
        if (mayor != i) { 
            int cambio = arregloR[i]; 
            repaint();
            arregloR[i] = arregloR[mayor]; 
            repaint();
            arregloR[mayor] = cambio; 
            repaint();
            Thread.sleep(100);
            apilarHeapSort(arregloR, n, mayor); 
        } 
    } 
    
    public int[] MergeSort(int arreglo[], int primerNodo, int ultimoNodo) throws InterruptedException{ 
        if (primerNodo < ultimoNodo){ 
            int medio = (primerNodo+ultimoNodo)/2; 
 
            MergeSort(arreglo, primerNodo, medio); 
            MergeSort(arreglo , medio+1, ultimoNodo); 
  
            ordenMergeSort(arreglo, primerNodo, medio, ultimoNodo); 
        }
        
        return arreglo;
    }
    
    void ordenMergeSort(int arr[], int primerNodo, int nodoMedio, int ultimoNodo) throws InterruptedException{ 
        int n1 = nodoMedio - primerNodo + 1; 
        int n2 = ultimoNodo - nodoMedio; 
  
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        for (int i=0; i < n1; ++i) 
            L[i] = arr[primerNodo + i]; 
            repaint();
            Thread.sleep(100);
        for (int j=0; j < n2; ++j) 
            R[j] = arr[nodoMedio + 1 + j]; 
            repaint();
            Thread.sleep(100);
            
        int i = 0, j = 0; 
  
        int k = primerNodo; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]){ 
                arr[k] = L[i]; 
                repaint();
                Thread.sleep(100);
                i++; 
            } 
            else{ 
                arr[k] = R[j]; 
                repaint();
                Thread.sleep(100);
                j++; 
            } 
            k++; 
        } 
  
        while (i < n1){ 
            arr[k] = L[i];
            repaint();
            Thread.sleep(100);
            i++; 
            k++; 
        } 
  
        while (j < n2){ 
            arr[k] = R[j];
            repaint();
            Thread.sleep(100);
            j++; 
            k++; 
        } 
    } 
    
    public int[] QuickSort(int arreglo[], int primeraPosicion, int ultimaPosicion) throws InterruptedException{ 
        int[] stack = new int[ultimaPosicion - primeraPosicion + 1]; 
  
        int top = -1; 
        
        stack[++top] = primeraPosicion; 
        stack[++top] = ultimaPosicion; 
  
        while (top >= 0) { 
            ultimaPosicion = stack[top--]; 
            primeraPosicion = stack[top--]; 
  
            int p = particionQuickSort(arreglo, primeraPosicion, ultimaPosicion); 
  
            if (p - 1 > primeraPosicion) { 
                stack[++top] = primeraPosicion; 
                this.repaint();
                Thread.sleep(100);
                stack[++top] = p - 1; 
                this.repaint();
                Thread.sleep(100);
            } 
  
            if (p + 1 < ultimaPosicion) { 
                stack[++top] = p + 1; 
                this.repaint();
                Thread.sleep(100);
                stack[++top] = ultimaPosicion; 
                this.repaint();
                Thread.sleep(100);
            } 
        } 
        return arreglo;
    } 
    
   int particionQuickSort(int arreglo[], int primeraPosicion, int ultimaPosicion) throws InterruptedException{ 
        int pivote = arreglo[ultimaPosicion]; 
  
        int i = (primeraPosicion - 1); 
        for (int j = primeraPosicion; j <= ultimaPosicion - 1; j++) { 
            if (arreglo[j] <= pivote) { 
                i++; 
                this.repaint();
                Thread.sleep(100);
                int temp = arreglo[i]; 
                this.repaint();
                Thread.sleep(100);
                arreglo[i] = arreglo[j]; 
                this.repaint();
                Thread.sleep(100);
                arreglo[j] = temp;
                this.repaint();
                Thread.sleep(100);
            } 
        } 
  
        int temp = arreglo[i + 1];
        this.repaint();
        Thread.sleep(100);
        arreglo[i + 1] = arreglo[ultimaPosicion]; 
        this.repaint();
        Thread.sleep(100);
        arreglo[ultimaPosicion] = temp; 
        this.repaint();
        Thread.sleep(100);
  
        return i + 1; 
    }
    
    @Override
    public void paint(Graphics g){
        int tam = 30;
        double X = 5;
        double Y = getHeight();
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (int i = 0; i < arreglo.length; i++){
            g2d.drawRect((int)X + (i*tam), (int)Y - arreglo[i] - 15, tam, arreglo[i]);
            g2d.drawString(""+arreglo[i],((int)X + (i * tam) + 3), (int)Y);
        }   
    }

}

