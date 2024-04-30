//
// https://www.youtube.com/watch?v=aBONSQ44cnk
//
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * This program uses Jfreechart to make a chart
 */
public class JFreeCharter extends ApplicationFrame {

    /**
     * Constructor for JFreeCharter.
     * @param title The frame title.
     */
    public JFreeCharter(String title) {
        super(title);
    }

    /**
     * This generates a list of random data points.
     * Used in testing
     * @return List of Double representing random data.
     */
    public static List<Double> getData() {
        List<Double> data = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            data.add(10 + rand.nextGaussian() * 2); // Gaussian distribution around 10.
        }
        return data;
    }

    /**
     * Used to populate an XYSeries with data.
     * @param series The series to populate.
     * @param data The data points to add.
     */
    private void populateSeries(XYSeries series, List<Double> data) {
        for (int i = 0; i < data.size(); i++) {
            series.add(i, data.get(i));
        }
    }

    /**
     * Used to salt data
     * @param data The original list of data points.
     * @param noiseLevel ammount of salt to add
     * @return data modified by the noise
     */
    public static List<Double> saltData(List<Double> data, double noiseLevel) {
        Random rand = new Random();
        List<Double> saltedData = new ArrayList<>();
        for (Double datum : data) {
            saltedData.add(datum + noiseLevel * rand.nextGaussian());
        }
        return saltedData;
    }

    /**
     * Used to smooth data
     * @param data The original list of data points.
     * @param windowSize The number of points in the smoothing window.
     * @return List of Double representing smoothed data.
     */
    public static List<Double> smoothData(List<Double> data, int windowSize) {
        List<Double> smoothedData = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            double sum = 0.0;
            int count = 0;
            for (int j = Math.max(0, i - windowSize / 2); j <= Math.min(data.size() - 1, i + windowSize / 2); j++) {
                sum += data.get(j);
                count++;
            }
            smoothedData.add(sum / count);
        }
        return smoothedData;
    }

    /**
     *This is used to help make display a chart
     * @param chartTitle The title of the chart.
     * @param dataset The dataset to plot.
     */
    private void createChart(String chartTitle, XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
            chartTitle,
            "Time",
            "Value",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

    /**
     * Plots multiple series on a single chart.
     * @param chartTitle The title of the chart.
     * @param originalData original data before manipulation
     * @param saltedData data after it has been salted
     * @param smoothedData data after salting after smoothing
     */
    public void plotData(String chartTitle, List<Double> originalData, List<Double> saltedData, List<Double> smoothedData) {
        XYSeries series1 = new XYSeries("Original Data");
        XYSeries series2 = new XYSeries("Salted Data");
        XYSeries series3 = new XYSeries("Smoothed Data");
        populateSeries(series1, originalData);
        populateSeries(series2, saltedData);
        populateSeries(series3, smoothedData);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

        createChart(chartTitle, dataset);
    }

    /**
     * Plots a single series of data.
     * @param chartTitle The title of the chart.
     * @param originalData The list of data points to be plotted.
     */
    public void plotData(String chartTitle, List<Double> originalData) {

        XYSeries series1 = new XYSeries("Original Data");

        for (int i = 0; i < originalData.size(); i++) {
            series1.add(i, originalData.get(i));
        }


        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);

        // Generate the chart using the dataset.
        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle,
                "Time", 
                "Value", 
                dataset,
                PlotOrientation.VERTICAL,
                true,    
                true,    
                false  
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel); 
    }

    /**
     * Plots multiple series from specified columns in a data map.
     * @param chartTitle The title of the chart.
     * @param columnNames Array of column names to be plotted.
     * @param data Map from column names to lists of string data points.
     */
    public void plotData(String chartTitle, String[] columnNames, HashMap<String, List<String>> data) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        for (String columnName : columnNames) {
            columnName = columnName.trim(); 
            if (!data.containsKey(columnName)) { 
                System.out.println("Column '" + columnName + "' not found.");
                continue; // Skip this iteration if the column is not found.
            }

           
            List<String> columnData = data.get(columnName);
            XYSeries series = new XYSeries(columnName);
            for (int i = 0; i < columnData.size(); i++) {
                try {
                    series.add(i, Double.parseDouble(columnData.get(i)));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid data point: " + columnData.get(i));
                }
            }
            dataset.addSeries(series); 
        }

        // Create the chart with the combined dataset.
        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle,
                "Index", 
                "Value", 
                dataset,
                PlotOrientation.VERTICAL,
                true,     
                true,  
                false   
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
    }

}
