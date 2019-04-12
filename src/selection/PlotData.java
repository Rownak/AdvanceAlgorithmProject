/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package selection;

/**
 *
 * @author Rownak
 */
public class PlotData {
    
    public double time;
    public int size;
    public int threshold;

    public PlotData(int size, int threshold) {
        this.size = size;
        this.threshold = threshold;
    }

    public PlotData(double time, int size, int threshold) {
        this.time = time;
        this.size = size;
        this.threshold = threshold;
    }
    
    
}
