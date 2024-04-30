import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides a command-line interface for performing various operations on CSV files.
 * It allows users to read and print CSV files, perform force calculations, salt and smooth data,
 * generate charts, and exit the program.
 */
public class PartTwoTester {
    private static Scanner scanner = new Scanner(System.in);
    private static CSVReaderWriter CSVWorker = new CSVReaderWriter();

    /**
     * Main method that continuously prompts the user for input until they choose to exit.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        boolean keepGoing = true;
        while (keepGoing) {
            keepGoing = userChoice();
        }
    }

    /**
     * Displays the user menu and processes user choices.
     * @return boolean indicating whether to continue running the program.
     */
    private static boolean userChoice() {
        System.out.println("Welcome! Please choose an operation:");
        System.out.println("1. Read and print CSV");
        System.out.println("2. Perform F=MA calculation");
        System.out.println("3. Salt data");
        System.out.println("4. Smooth data");
        System.out.println("5. Print Dataset");
        System.out.println("6. Exit");
        System.out.println("Enter your choice (1-6):");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                readAndPrintCSV();
                return true;
            case 2:
                forceCalculation();
                return true;
            case 3:
                salterSmoother(true); // true for salting
                return true;
            case 4:
                salterSmoother(false); // false for smoothing
                return true;
            case 5:
                makeChart();
                return true;
            case 6:
                System.out.println("Exiting program...");
                scanner.close();
                return false;
            default:
                System.out.println("Invalid choice. Please enter a valid number (1-6).");
                return true;
        }
    }

    /**
     * Prompts the user for a CSV file path, reads the CSV file, and prints the data.
     */
    private static void readAndPrintCSV() {
        System.out.println("Please enter the full path of your CSV file:");
        String filePath = scanner.nextLine();
        HashMap<String, List<String>> data = CSVWorker.readCSV(filePath);
        CSVWorker.printCSV(data);
    }

    /**
     * Performs force calculations based on user-specified parameters and writes the results to a CSV file.
     */
    private static void forceCalculation() {
        System.out.println("Enter constant (m for mass, a for acceleration):");
        String constant = scanner.nextLine();
        System.out.println("Enter constant value:");
        double constantValue = scanner.nextDouble();
        System.out.println("Enter start value:");
        double start = scanner.nextDouble();
        System.out.println("Enter end value:");
        double end = scanner.nextDouble();
        System.out.println("Enter increment:");
        double increment = scanner.nextDouble();
        scanner.nextLine(); // Consume newline after double

        ForceCalculator calculator = new ForceCalculator();
        HashMap<String, List<String>> results = calculator.calculateForce(constant, constantValue, start, end, increment);
        printForceResults(results);

        System.out.println("Enter the full path for the output CSV file:");
        String outputFilePath = scanner.nextLine();
        CSVWorker.writeCSV(outputFilePath, results);
    }

    /**
     * Salts or smooths data based on the user's choice and updates the CSV file accordingly.
     * @param isSalting Boolean indicating whether to salt (true) or smooth (false) the data.
     */
    private static void salterSmoother(boolean isSalting) {
        System.out.println("Please enter the full path of your CSV file:");
        String filePath = scanner.nextLine();
        HashMap<String, List<String>> data = CSVWorker.readCSV(filePath);

        System.out.println("Available columns: " + String.join(", ", data.keySet()));
        System.out.println("Enter the name of the column to modify:");
        String columnName = scanner.nextLine();

        if (!data.containsKey(columnName)) {
            System.out.println("Column not found.");
            return;
        }

        if (isSalting) {
            saltData(data, columnName);
        } else {
            smoothData(data, columnName);
        }
    }

    /**
     * Adds salt (random noise) to the specified column data.
     * @param data The current dataset.
     * @param columnName The column to be salted.
     */
    private static void saltData(HashMap<String, List<String>> data, String columnName) {
        System.out.println("Enter min value for salt:");
        double min = scanner.nextDouble();
        System.out.println("Enter max value for salt:");
        double max = scanner.nextDouble();
        scanner.nextLine(); // Consume newline after double

        SalterSmoother processor = new SalterSmoother();
        List<String> saltedData = processor.saltData(data, columnName, min, max);
        data.put(columnName + " Salted", saltedData);

        System.out.println("\nSalted Data:");
        saltedData.forEach(System.out::println);

        saveDataToFile(data);
    }

    /**
     * Smooths data for the specified column using neighboring values.
     * @param data The current dataset.
     * @param columnName The column to be smoothed.
     */
    private static void smoothData(HashMap<String, List<String>> data, String columnName) {
        System.out.println("Enter number of neighbors for smoothing:");
        int k = scanner.nextInt();
        scanner.nextLine(); // Consume newline after int

        SalterSmoother processor = new SalterSmoother();
        List<String> smoothedData = processor.smoothData(data, columnName, k);
        data.put(columnName + " Smoothed", smoothedData);

        System.out.println("\nSmoothed Data:");
        smoothedData.forEach(System.out::println);

        saveDataToFile(data);
    }

    /**
     * Saves the modified data back to a CSV file.
     * @param data The dataset to be saved.
     */
    private static void saveDataToFile(HashMap<String, List<String>> data) {
        System.out.println("Enter the full path for the output CSV file:");
        String outputFilePath = scanner.nextLine();
        CSVWorker.writeCSV(outputFilePath, data);
    }

    /**
     * Generates a chart based on selected columns from the CSV file.
     */
    private static void makeChart(){
        System.out.println("Please enter the full path for the CSV file you want to make a chart with:");
        JFreeCharter demo = new JFreeCharter("Data Processing Demo");
        String filePath;
        filePath = scanner.nextLine();
        filePath = scanner.nextLine(); // Ensure double read for potential input buffer issues
        HashMap<String, List<String>> data = CSVWorker.readCSV(filePath);

        System.out.println("Available columns: " + String.join(", ", data.keySet()));
        System.out.println("Enter the names of the columns to print, separated by commas (e.g., Column1, Column2):");
        String[] columnNames = scanner.nextLine().split(",");

        demo.plotData("Data Comparison", columnNames, data);

        demo.pack();
        demo.setVisible(true);
    }

    /**
     * Prints the results of force calculations.
     * @param results HashMap containing the calculated force data.
     */
    private static void printForceResults(HashMap<String, List<String>> results) {
        System.out.println("Variable (either mass 'm' or acceleration 'a'), Force:");
        List<String> variableData = results.get("Mass") != null ? results.get("Mass") : results.get("Acceleration");
        List<String> forceData = results.get("Force");
        for (int i = 0; i < variableData.size(); i++) {
            System.out.println(variableData.get(i) + " " + forceData.get(i));
        }
    }
}
