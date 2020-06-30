package Modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import javax.swing.JOptionPane;

public class Modelo{
    
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
         
    public int[] HeapSort(int[] arreglo) { 
        int n = arreglo.length; 
  
        for (int i = n / 2 - 1; i >= 0; i--) 
            apilarHeapSort(arreglo, n, i); 
  
        for (int i=n-1; i>=0; i--) 
        { 
            int temp = arreglo[0]; 
            arreglo[0] = arreglo[i]; 
            arreglo[i] = temp; 
  
            apilarHeapSort(arreglo, i, 0); 
        }
        
        return arreglo;
    } 
  
    void apilarHeapSort(int arregloR[], int n, int i) { 
        int mayor = i;
        int izq = 2*i + 1;
        int der = 2*i + 2;
  
        if (izq < n && arregloR[izq] > arregloR[mayor]) 
            mayor = izq; 
  
        if (der < n && arregloR[der] > arregloR[mayor]) 
            mayor = der; 
  
        if (mayor != i) { 
            int cambio = arregloR[i]; 
            arregloR[i] = arregloR[mayor]; 
            arregloR[mayor] = cambio; 
  
            apilarHeapSort(arregloR, n, mayor); 
        } 
    } 
 
    public int[] MergeSort(int arreglo[], int primerNodo, int ultimoNodo){ 
        if (primerNodo < ultimoNodo){ 
            int medio = (primerNodo+ultimoNodo)/2; 
 
            MergeSort(arreglo, primerNodo, medio); 
            MergeSort(arreglo , medio+1, ultimoNodo); 
  
            ordenMergeSort(arreglo, primerNodo, medio, ultimoNodo); 
        }
        
        return arreglo;
    }
    
    void ordenMergeSort(int arr[], int primerNodo, int nodoMedio, int ultimoNodo){ 
        int n1 = nodoMedio - primerNodo + 1; 
        int n2 = ultimoNodo - nodoMedio; 
  
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        for (int i=0; i < n1; ++i) 
            L[i] = arr[primerNodo + i]; 
        for (int j=0; j < n2; ++j) 
            R[j] = arr[nodoMedio + 1 + j]; 
  
        int i = 0, j = 0; 
  
        int k = primerNodo; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]){ 
                arr[k] = L[i]; 
                i++; 
            } 
            else{ 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        while (i < n1){ 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        while (j < n2){ 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    public int[] QuickSort(int arreglo[], int primeraPosicion, int ultimaPosicion){ 
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
                stack[++top] = p - 1; 
            } 
  
            if (p + 1 < ultimaPosicion) { 
                stack[++top] = p + 1; 
                stack[++top] = ultimaPosicion; 
            } 
        } 
        return arreglo;
    } 
    
   int particionQuickSort(int arreglo[], int primeraPosicion, int ultimaPosicion){ 
        int pivote = arreglo[ultimaPosicion]; 
  
        int i = (primeraPosicion - 1); 
        for (int j = primeraPosicion; j <= ultimaPosicion - 1; j++) { 
            if (arreglo[j] <= pivote) { 
                i++; 
  
                int temp = arreglo[i]; 
                arreglo[i] = arreglo[j]; 
                arreglo[j] = temp; 
            } 
        } 
  
        int temp = arreglo[i + 1]; 
        arreglo[i + 1] = arreglo[ultimaPosicion]; 
        arreglo[ultimaPosicion] = temp; 
  
        return i + 1; 
    } 
  
    public int BusquedaBinaria(int[] arreglo, int num) {
        int tamano = arreglo.length;
        int medio;
        if(tamano > 2)
            medio = tamano/2;            
        else
            medio = 0;


        if (arreglo[medio] == num){
          return arreglo[medio];
        }
        else if (tamano == 1){
            return -1;
        }
        else if (arreglo[medio] > num){
            try{
                return BusquedaBinaria(Arrays.copyOfRange(arreglo,0,medio),num);                
            }
            catch(ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null,"Error en la busqueda binaria, se recomienda ordernar los datos antes de efectuar la comparacion", "ERROR", 1);
            }
        }
        else{
            try{
                return BusquedaBinaria(Arrays.copyOfRange(arreglo,medio+1,tamano),num);               
            }
            catch(ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(null,"Error en la busqueda binaria, se recomienda ordernar los datos antes de efectuar la comparacion", "ERROR", 1);
                return -1;
            }
        }
        
        return -1;
    }
    
    public int BusquedaSecuencial(int[] arreglo, int num){   
        for(int i = 0; i < arreglo.length; i++){
            if(arreglo[i] == num){
                return arreglo[i];
            }
        }
        
        return -1;
    }
        
    public int BusquedaRandom(int[] arreglo, int num){
        
        for(int i = 0; i < arreglo.length * 10000; i++){
            int valorEntero = (int)Math.floor(Math.random()*(arreglo.length)+0);
            if(arreglo[valorEntero] == num){
                System.out.println(arreglo[valorEntero]);
                return arreglo[valorEntero];
            }
        }
        
        return -1;
    }
    
    public int BusquedaHashing(LinkedHashMap<Integer, Integer> tablaHash, int dato){
        
        if(tablaHash.get(dato) == dato){
            return 1;
        }
        
        return -1;
    }
    
    public LinkedHashMap<Integer, Integer> CrearLinkedHashMap(int[] arreglo){
        LinkedHashMap<Integer,Integer> tablaHash = new LinkedHashMap();
        
        for(int i = 0; i < arreglo.length; i++){
            tablaHash.put(arreglo[i], arreglo[i]);
        }
        
        return tablaHash;
    }
    
    public int[] LecturaArchivo(String path){
        File f = new File(path);
        String lectura = "";
        ArrayList<Integer> arreglo = new ArrayList<>();
        
        try {
            FileReader lector = new FileReader(f);BufferedReader br = new BufferedReader(lector);
            String aux = "";
            
            while((lectura = br.readLine())!=null){    
               for(int i = 0; i < lectura.length(); i++){
                    if(lectura.charAt(i) == ',' || i == (lectura.length()-1)){
                        try{
                            arreglo.add(Integer.parseInt(aux));
                            aux = "";
                        }catch(NumberFormatException e){
                            JOptionPane.showMessageDialog(null, "Formato CSV no aceptado", "Advertencia", 2);
                            arreglo.clear();
                            return null;
                        }
                    }
                    else{
                        aux+= lectura.charAt(i);
                    }   
                }
            }
        } 
        catch (IOException e) {
            e.getStackTrace();
        }

        int[] arreglo1 = new int[arreglo.size()];
        
        for(int i = 0; i < arreglo.size(); i++){
            arreglo1[i] = arreglo.get(i);
        }
        
        
        return arreglo1;
    }
    
    public int[] LecturaCuadroTexto(String texto){
        String aux = "";
        ArrayList<Integer> arreglo = new ArrayList();
        
        for(int i = 0; i < texto.length(); i++){
            if(texto.charAt(i) == ',' ){
                    try{
                        arreglo.add(Integer.parseInt(aux));
                        aux = "";
                    }catch(NumberFormatException a){return null;}
            }
            else{
                aux+= texto.charAt(i);
                if(i + 1 == texto.length()){
                    try{
                        arreglo.add(Integer.parseInt(aux));
                    }catch(NumberFormatException a){return null;}
                    
                }
            }
        }
        
        int[] arreglo1 = new int[arreglo.size()];
        
        for(int i = 0; i < arreglo.size(); i++){
            arreglo1[i] = arreglo.get(i);
        }
        
        
        return arreglo1;
    }

    public long TiempoEjecucionOrdenamientos(String metodo, int[] arreglo){
        long tiempoInicio,tiempoFinal = 0;
        
        switch(metodo){
            case "BubbleSort":
                tiempoInicio = System.currentTimeMillis();
                BubbleSort(arreglo);
                tiempoFinal = System.currentTimeMillis() - tiempoInicio;
            break;
            case "HeapSort":
                tiempoInicio = System.currentTimeMillis();
                HeapSort(arreglo);
                tiempoFinal = System.currentTimeMillis() - tiempoInicio;
            break;
            case "MergeSort":
                tiempoInicio = System.currentTimeMillis();
                MergeSort(arreglo, 0, arreglo.length - 1);
                tiempoFinal = System.currentTimeMillis() - tiempoInicio;
            break;
            case "QuickSort":
                tiempoInicio = System.currentTimeMillis();
                QuickSort(arreglo, 0, arreglo.length - 1);
                tiempoFinal = System.currentTimeMillis() - tiempoInicio;
            break;
            
        }
        
        return tiempoFinal;
    }
    
    public long TiempoEjecucionBusquedas(String metodo, int[] arreglo, int dato){
        int retorno = 0;
        long tiempoInicio,tiempoFinal = 0;
        
        switch(metodo){
            case "BBinaria":
                tiempoInicio = System.currentTimeMillis();
                retorno = BusquedaBinaria(arreglo, dato);
                tiempoFinal = System.currentTimeMillis() - tiempoInicio;
            break;
            case "BRandom":
                tiempoInicio = System.currentTimeMillis();
                BusquedaRandom(arreglo, dato);
                tiempoFinal = System.currentTimeMillis() - tiempoInicio;
            break;
            case "BSecuencial":
                tiempoInicio = System.currentTimeMillis();
                BusquedaSecuencial(arreglo, dato);
                tiempoFinal = System.currentTimeMillis() - tiempoInicio;
            break;
            case "BHashing":
                LinkedHashMap<Integer, Integer> tablaHash = CrearLinkedHashMap(arreglo);
                tiempoInicio = System.currentTimeMillis();
                BusquedaHashing(tablaHash, dato);
                tiempoFinal = System.currentTimeMillis() - tiempoInicio;
            break;     
        }
        
        if(retorno == -1){
            tiempoFinal = -1;
        }
        
        return tiempoFinal;
    }
}