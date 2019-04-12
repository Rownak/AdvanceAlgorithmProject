package ploting_graph;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import selection.*;

/**
 *  * This program demonstrates how to draw XY line chart with XYDataset  *
 * using JFreechart library.
 *
 * @author Ahnaf Farhan
 */
public class XYLineChart extends JFrame {

    public PlotData[] merge_data = new PlotData[20];
    public PlotData[] linear_data = new PlotData[20];
    public PlotData[] threshold_data = new PlotData[20];
    public int numberSZ, numberThr;
    public XYLineChart() {
        super("XY Line Chart Example with JFreechart");
        JFrame frame = new JFrame();
        Selection selection = new Selection();
        PlotData[] temp = new PlotData[2];
        int fixedThreshold = 5;
        int fixedTime = 10000;
         numberSZ = Integer.parseInt(JOptionPane.showInputDialog(frame,"How many sizes you want to input?: (between 5 - 10)") );
        for(int i=0; i<numberSZ; i++){
            int size = Integer.parseInt(JOptionPane.showInputDialog(frame,"Input "+i+" size: ") );
            temp = selection.timeAnalysis(size, fixedThreshold);
            merge_data[i] = temp[0];
            linear_data[i] = temp[1];
        }
         numberThr = Integer.parseInt(JOptionPane.showInputDialog(frame,"How many Thresholds you want to input? : (between 5 - 10)") );
        for(int i=0; i<numberThr; i++){
            int threshold = Integer.parseInt(JOptionPane.showInputDialog(frame,"Input "+i+" threshold: ") );
            temp = selection.timeAnalysis(fixedTime, threshold);
            threshold_data[i] = temp[1];
        }
        
        
        
        JPanel mergeSort_cp = createChartPanel(1);
        JPanel linearTime_cp = createChartPanel(2);
        JPanel mergeLinear_cp = createChartPanel(3);
        JPanel thresoldVstime_cp = createChartPanel(4);

        setLayout(new GridLayout(2, 2));
        add(mergeSort_cp);
        add(linearTime_cp);
        add(mergeLinear_cp);
        add(thresoldVstime_cp);

        //setSize(640, 480);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private JPanel createChartPanel(int chartNo) {
        ChartInfo ci = new ChartInfo();
        
        
        XYDataset dataset = createDataset(chartNo);
        switch (chartNo) {
            case 1:
                ci = new ChartInfo("Merge Sort time Analysis", "Size", "Time (MicroSec)");
                break;
            case 2:
                ci = new ChartInfo("Linear Time Program time Analysis", "Size", "Time (MicroSec");
                break;
            case 3:
                ci = new ChartInfo("Merge Sort Vs Linear time Analysis", "Size", "Time (MicroSec");
                break;
            case 4:
                ci = new ChartInfo("Linear Time fixed size time Analysis", "Threshold", "Time (MicroSec");    
                break;
                
        }
        JFreeChart chart = ChartFactory.createXYLineChart(ci.chartTitle,
                ci.xAxisLabel, ci.yAxisLabel, dataset);

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        if(chartNo == 2){
            renderer.setSeriesPaint(0, Color.BLUE);
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        }
        else{
            renderer.setSeriesPaint(0, Color.RED);
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        }
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesStroke(1, new BasicStroke(2.0f));
        

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(false);
        plot.setDomainGridlinesVisible(false);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(ci.chartTitle,
                new Font("Serif", Font.BOLD, 18)
        )
        );

        return new ChartPanel(chart);
    }

    private XYDataset createDataset(int chartNo) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        String title1 = "Object1";
        String title2 = "Object2";
        XYSeries series1;
        XYSeries series2;
        if(chartNo == 1){
            title1 = "Merge Sort";
            series1 = new XYSeries(title1);
            for(int i=0; i<numberSZ; i++){
                series1.add(merge_data[i].size,merge_data[i].time);
            }
            dataset.addSeries(series1);
            
        }
        else if(chartNo == 2){
            title1 = "Linear Time";
            
            series1 = new XYSeries(title1);
            for(int i=0; i<numberSZ; i++){
                series1.add(linear_data[i].size,linear_data[i].time);
            }
            dataset.addSeries(series1);
        }
        
        else if(chartNo == 3){
            title1 = "Merge Sort";
            title2 = "Linear Time";
            series1 = new XYSeries(title1);
            series2 = new XYSeries(title2);
            for(int i=0; i<numberSZ; i++){
                series1.add(merge_data[i].size,merge_data[i].time);
                series2.add(linear_data[i].size,linear_data[i].time);
                
            }
            dataset.addSeries(series1);
            dataset.addSeries(series2);
        }
        
        else if (chartNo == 4){
             title1 = "Linear Time - threshold";
            
            series1 = new XYSeries(title1);
            for(int i=0; i<numberThr; i++){
                series1.add(threshold_data[i].threshold,threshold_data[i].time);
            }
            dataset.addSeries(series1);
        }
        

        

        

      

        
      

        return dataset;
    }

    public static void main(String[] args) {
        
        
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new XYLineChart().setVisible(true);
            }
        });
    }
}
