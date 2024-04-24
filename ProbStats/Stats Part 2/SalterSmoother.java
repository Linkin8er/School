import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SalterSmoother {

    /**
     * Modifies each force value by adding a random amount within a specified range.
     *
     * @param data The original data as a list of lists of strings.
     * @param min The minimum amount to add.
     * @param max The maximum amount to add.
     * @return A new list with modified force values.
     */
    public List<String> saltData(List<String> data, double min, double max) {
        Random random = new Random();
        List<String> saltedData = new ArrayList<>();

        for (String entry : data) {
            double force = Double.parseDouble(entry);
            double randomValue = min + (max - min) * random.nextDouble();
            List<String> saltedEntry = new ArrayList<>();
            saltedData.add(String.format("%.2f", force + randomValue));
        }

        return saltedData;
    }

    /**
     * Smooths each force value by averaging it with the force values of its K nearest neighbors.
     *
     * @param data The original data as a list of lists of strings.
     * @param k The number of neighbors to include in the average.
     * @return A new list with smoothed force values.
     */
    public List<String> smoothData(List<String> data, int k) {
        List<String> smoothedData = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            double sum = 0.0;
            int count = 0;

            for (int j = Math.max(0, i - k); j <= Math.min(data.size() - 1, i + k); j++) {
                double force = Double.parseDouble(data.get(j));
                sum += force;
                count++;
            }

            double average = sum / count;
            List<String> smoothedEntry = new ArrayList<>();
            smoothedEntry.add(data.get(i));  // Keep the variable value unchanged
            smoothedEntry.add(String.format("%.2f", average));
        }

        return smoothedData;
    }
}

