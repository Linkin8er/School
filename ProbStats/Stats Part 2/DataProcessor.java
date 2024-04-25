import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataProcessor extends ApplicationFrame {

    public DataProcessor(String title) {
        super(title);
    }

    public static List<Double> readData() {
        List<Double> data = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            data.add(10 + rand.nextGaussian() * 2);
        }
        return data;
    }

    public static List<Double> saltData(List<Double> data, double noiseLevel) {
        Random rand = new Random();
        List<Double> saltedData = new ArrayList<>();
        for (Double datum : data) {
            saltedData.add(datum + noiseLevel * rand.nextGaussian());
        }
        return saltedData;
    }

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

    public void plotData(String chartTitle, List<Double> originalData, List<Double> saltedData, List<Double> smoothedData) {
        XYSeries series1 = new XYSeries("Original Data");
        for (int i = 0; i < originalData.size(); i++) {
            series1.add(i, originalData.get(i));
        }

        XYSeries series2 = new XYSeries("Salted Data");
        for (int i = 0; i < saltedData.size(); i++) {
            series2.add(i, saltedData.get(i));
        }

        XYSeries series3 = new XYSeries("Smoothed Data");
        for (int i = 0; i < smoothedData.size(); i++) {
            series3.add(i, smoothedData.get(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);

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

    public static void main(String[] args) {
        DataProcessor demo = new DataProcessor("Data Processing Demo");
        List<Double> originalData = readData();
        List<Double> saltedData = saltData(originalData, 1.0); // Adjust noise level here
        List<Double> smoothedData = smoothData(saltedData, 5); // Adjust window size here
        demo.plotData("Data Comparison", originalData, saltedData, smoothedData);
        demo.pack();
        demo.setVisible(true);
    }
}
