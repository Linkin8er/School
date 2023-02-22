//////////////////////////////////////////////////////////////////////////////
//                                                                          //
//                          Author: Nicholas Stephani                       //
//                          Project: Stats Lab                              //
//                          Date: 04/11/2021                                //
//                                                                          //
//////////////////////////////////////////////////////////////////////////////
import java.io.*;
import java.util.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class Statistics {

    
    public static void main( String[ ] args ) throws IOException{
        Random random = new Random();
        ArrayList<Integer> favaBeans = new ArrayList();

        DefaultCategoryDataset LineChartNice = new DefaultCategoryDataset();
        DefaultCategoryDataset LineChartSalty = new DefaultCategoryDataset();
        DefaultCategoryDataset LineChartSmooth = new DefaultCategoryDataset();
        for(int x = 0; x <= 10; x++){
            
            LineChartNice.addValue( (x*x), "Squared" , ""+x );
            int saltedValue = (x*x) + random.nextInt(20) - 10;
            favaBeans.add(saltedValue);
            LineChartSalty.addValue( (saltedValue), "Squared" , ""+x );
        }

        int whereAmI = 0;
        int average = 0;
        for(int x = 0; x <= 10; x++){

            if(x<7) whereAmI = 3;
            else if(x<8) whereAmI = 2;
            else if(x<9) whereAmI = 1;
            else if(x==10) whereAmI = 0;
            int y = x + whereAmI;
            int divisor = 0;
            while(y> x - 3 && y > 0){
                average += favaBeans.get(y);
                divisor++;
                y--;
            }
            LineChartSmooth.addValue( (int)(average = average/divisor), "Squared" , ""+x );
        }

        JFreeChart lineChartBaseline = ChartFactory.createLineChart("GoodChart","X","Y", LineChartNice,PlotOrientation.VERTICAL,true,true,false);
        JFreeChart lineChartSalted = ChartFactory.createLineChart("SaltedChart","X","Y", LineChartSalty,PlotOrientation.VERTICAL,true,true,false);
        JFreeChart lineChartSmoothed = ChartFactory.createLineChart("SmoothChart","X","Y", LineChartSmooth,PlotOrientation.VERTICAL,true,true,false);
        int width = 640;
        int height = 480;
        File lineChartGood = new File( "GoodChart.jpeg" );
        File lineChartSalty = new File( "SaltedChart.jpeg" );
        File lineChartSmooth = new File( "SmoothChart.jpeg" );
        ChartUtils.saveChartAsJPEG(lineChartGood ,lineChartBaseline, width ,height);
        ChartUtils.saveChartAsJPEG(lineChartSalty ,lineChartSalted, width ,height);
        ChartUtils.saveChartAsJPEG(lineChartSmooth ,lineChartSmoothed, width ,height);
    }
}