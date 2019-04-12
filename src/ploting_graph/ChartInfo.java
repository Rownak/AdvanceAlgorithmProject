/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ploting_graph;

/**
 *
 * @author Ahnaf Farhan
 */
public class ChartInfo {
    
    public String chartTitle = "chartTitle";
    public String xAxisLabel = "X";
    public String yAxisLabel = "Y"; 

    public ChartInfo() {
        
        this.chartTitle = null;
        this.xAxisLabel = null;
        this.yAxisLabel = null;
    }
    public ChartInfo(String chartTitle, String xAxisLabel, String yAxisLabel ) {
        
        this.chartTitle = chartTitle;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
    }
    
    
    
}
