package Vista;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class VentanaGraficosTiempo extends JFrame{
    ChartPanel panel;
    JFreeChart chart;
    CategoryDataset dataset;
    long tiempoBublesort,tiempoHeapSort,tiempoMergeSort,tiempoQuickSort;
    long tiempoBBinaria,tiempoBRandom,tiempoBSecuencial,tiempoBHashing;
    String tipo;
    
    public VentanaGraficosTiempo(long tiempo1, long tiempo2, long tiempo3, long tiempo4, String tipo){
        this.tipo = tipo;
        if(tipo.equals("Ordenamientos")){
            tiempoBublesort = tiempo1;
            tiempoHeapSort = tiempo2;
            tiempoMergeSort = tiempo3;
            tiempoQuickSort = tiempo4;
        }
        else{
            tiempoBBinaria = tiempo1;
            tiempoBSecuencial = tiempo2;
            tiempoBRandom = tiempo3;
            tiempoBHashing =  tiempo4;
        }
        IniciarVentana();
        IniciarComponentes();
    }

    private void IniciarVentana(){
        setTitle("Graficos Post-Proceso");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    void IniciarComponentes(){
        dataset = CrearSetdeDatos();
        
        if(tipo.equals("Ordenamientos")){
          chart = ChartFactory.createBarChart("Grafica de Ordenamientos", "", "Tiempo en ms", dataset, PlotOrientation.VERTICAL,true,true,false);
        }
        else{
            chart = ChartFactory.createBarChart("Grafica de Busquedas", "", "Tiempo en ms", dataset, PlotOrientation.VERTICAL,true,true,false);
        }
        
        panel = new ChartPanel(chart);
        setContentPane(panel);
    }
    
    CategoryDataset CrearSetdeDatos(){
        DefaultCategoryDataset datasetLocal = new DefaultCategoryDataset();
        
        if(tipo.equals("Ordenamientos")){
            datasetLocal.addValue(tiempoBublesort, "Bubble-Sort", "Bubble-Sort");
            datasetLocal.addValue(tiempoHeapSort, "Heap-Sort", "Heap-Sort");
            datasetLocal.addValue(tiempoMergeSort, "Merge-Sort", "Merge-Sort");
            datasetLocal.addValue(tiempoQuickSort, "Quick-Sort", "Quick-Sort");
        }
        else{
            datasetLocal.addValue(tiempoBBinaria, "Busqueda Binaria", "Busqueda Binaria");
            datasetLocal.addValue(tiempoBSecuencial, "Busqueda Secuencial", "Busqueda Secuencial");
            datasetLocal.addValue(tiempoBRandom, "Busqueda Random", "Busqueda Random");
            datasetLocal.addValue(tiempoBHashing, "Busqueda Hashing", "Busqueda Hashing");
        }
        
        return datasetLocal;
    }
    
    
}
