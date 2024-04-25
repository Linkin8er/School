import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SalterSmoother {

    /**
     * Modifies each force value in a specific column by adding a random amount within a specified range.
     *
     * @param data The original data as a HashMap where each key is a column label and the value is a list of strings containing the column data.
     * @param columnName The name of the column to modify.
     * @param min The minimum amount to add.
     * @param max The maximum amount to add.
     * @return A new list with modified force values for the specified column.
     */
    public List<String> saltData(HashMap<String, List<String>> data, String columnName, double min, double max) {
        if (!data.containsKey(columnName)) {
            throw new IllegalArgumentException("Column " + columnName + " does not exist in the data.");
        }

        Random random = new Random();
        List<String> originalData = data.get(columnName);
        List<String> saltedData = new ArrayList<>();

        for (String value : originalData) {
            double number = Double.parseDouble(value);
            double randomValue = min + (max - min) * random.nextDouble();
            saltedData.add(String.format("%.2f", number + randomValue));
        }

        return saltedData;
    }

    /**
     * Smooths each value in a specific column by averaging it with the values of its K nearest neighbors.
     *
     * @param data The original data as a HashMap where each key is a column label and the value is a list of strings containing the column data.
     * @param columnName The name of the column to modify.
     * @param k The number of neighbors to include in the average.
     * @return A new list with smoothed values for the specified column.
     */
    public List<String> smoothData(HashMap<String, List<String>> data, String columnName, int k) {
        if (!data.containsKey(columnName)) {
            throw new IllegalArgumentException("Column " + columnName + " does not exist in the data.");
        }

        List<String> originalData = data.get(columnName);
        List<String> smoothedData = new ArrayList<>();

        for (int i = 0; i < originalData.size(); i++) {
            double sum = 0;
            int count = 0;

            for (int j = Math.max(0, i - k); j <= Math.min(originalData.size() - 1, i + k); j++) {
                double value = Double.parseDouble(originalData.get(j));
                sum += value;
                count++;
            }

            double average = sum / count;
            smoothedData.add(String.format("%.2f", average));
        }

        return smoothedData;
    }
}
