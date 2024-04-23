import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This handles reading from, writing to, and printing CSV files.
 * Originally this jsut handled two columns, but has since been updated for multiple columns
 */
public class CSVReaderWriter {

    /**
     * Reads a CSV file and returns the data as a List of Lists of Strings.
     * Each inner List represents a row in the CSV file, where each String is a cell value.
     *
     * @param filePath The path of the file to be read.
     * @return A list of lists of strings containing the values of the CSV file.
     */
    public List<List<String>> readCSV(String filePath) {
        List<List<String>> records = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                List<String> values = new ArrayList<>();
                String[] tokens = line.split(",");
                for (String token : tokens) {
                    if (!token.isEmpty()) {
                        values.add(token.trim());
                    }
                }
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    /**
     * Writes a List of Lists of Strings to a CSV file.
     * Each inner List represents a row, and each String is a cell value.
     *
     * @param filePath The file path where the CSV is to be written.
     * @param data The data to be written to the CSV, structured as a List of Lists of Strings.
     */
    public void writeCSV(String filePath, List<List<String>> data) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            for (List<String> row : data) {
                for (int i = 0; i < row.size(); i++) {
                    fileWriter.append(row.get(i));
                    if (i < row.size() - 1) {
                        fileWriter.append(",");  // Add comma after each cell value, except the last one
                    }
                }
                fileWriter.append("\n"); // Move to the next line after writing all cells in a row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the data read from the CSV file.
     * Each row is printed on a new line, with each cell separated by a comma.
     *
     * @param data The CSV data to be printed, structured as a List of Lists of Strings.
     */
    public void printCSV(List<List<String>> data) {
        for (List<String> row : data) {
            for (int i = 0; i < row.size(); i++) {
                System.out.print(row.get(i));
                if (i < row.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(); // Move to the next line after printing all cells in a row
        }
    }
}
