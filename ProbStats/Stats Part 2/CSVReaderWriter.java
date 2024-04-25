import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReaderWriter {

    /**
     * Reads a CSV file and returns the data as a HashMap where each key is a column label from the first row
     * and the value is a List of Strings containing the column data.
     *
     * @param filePath The path of the file to be read.
     * @return A HashMap where keys are column labels and values are Lists of column data.
     */
    public HashMap<String, List<String>> readCSV(String filePath) {
        HashMap<String, List<String>> records = new HashMap<>();
        List<String> columnHeaders = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = bufferedReader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (isFirstLine) {
                    for (String header : tokens) {
                        header = header.trim();
                        columnHeaders.add(header);
                        records.put(header, new ArrayList<>());
                    }
                    isFirstLine = false;
                } else {
                    for (int i = 0; i < tokens.length; i++) {
                        records.get(columnHeaders.get(i)).add(tokens[i].trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    /**
     * Writes data from a HashMap to a CSV file.
     *
     * @param filePath The file path where the CSV is to be written.
     * @param data The data to be written to the CSV, structured as a HashMap.
     */
    public void writeCSV(String filePath, HashMap<String, List<String>> data) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            // Write headers
            List<String> headers = new ArrayList<>(data.keySet());
            for (int i = 0; i < headers.size(); i++) {
                fileWriter.append(headers.get(i));
                if (i < headers.size() - 1) {
                    fileWriter.append(",");
                }
            }
            fileWriter.append("\n");

            // Determine the number of rows by finding the size of the first list
            int numRows = data.get(headers.get(0)).size();
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < headers.size(); col++) {
                    List<String> columnData = data.get(headers.get(col));
                    fileWriter.append(columnData.get(row));
                    if (col < headers.size() - 1) {
                        fileWriter.append(",");
                    }
                }
                fileWriter.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints the data read from the CSV file.
     * Each row is printed on a new line, with each cell separated by a comma.
     *
     * @param data The CSV data to be printed, structured as a HashMap.
     */
    public void printCSV(Map<String, List<String>> data) {
    if (data.isEmpty()) {
        System.out.println("No data to display.");
        return;
    }

    List<String> headers = new ArrayList<>(data.keySet());
    if (!headers.isEmpty()) {
        headers.forEach(header -> System.out.print(header + ", "));
        System.out.println();

        int numRows = data.get(headers.get(0)).size();
        for (int row = 0; row < numRows; row++) {
            for (String header : headers) {
                List<String> columnData = data.get(header);
                if (columnData.size() > row) {
                    System.out.print(columnData.get(row) + ", ");
                } else {
                    System.out.print(" , ");
                }
            }
            System.out.println();
        }
    } else {
        System.out.println("Headers are missing.");
    }
}

}
