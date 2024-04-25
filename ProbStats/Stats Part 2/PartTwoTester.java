import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PartTwoTester {
    private static Scanner scanner = new Scanner(System.in);
    private static CSVReaderWriter CSVTester = new CSVReaderWriter();

    public static void main(String[] args) {
        while (true) {
            userChoice();
        }
    }

    private static void userChoice() {
        System.out.println("Welcome! Please choose an operation:");
        System.out.println("1. Read and print CSV");
        System.out.println("2. Perform force calculation");
        System.out.println("3. Salt data");
        System.out.println("4. Smooth data");
        System.out.println("5. Exit");
        System.out.println("Enter your choice (1-5):");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume leftover newline

        if (choice == 1) {
            readAndPrintCSV();
        } else if (choice == 2) {
            performForceCalculation();
        } else if (choice == 3) {
            modifyData(true); // true for salting
        } else if (choice == 4) {
            modifyData(false); // false for smoothing
        } else if (choice == 5) {
            System.out.println("Exiting program...");
            scanner.close();
            System.exit(0);
        } else {
            System.out.println("Invalid choice. Please enter a valid number (1-5).");
        }
    }

    private static void readAndPrintCSV() {
        System.out.println("Please enter the full path of your CSV file:");
        String filePath = scanner.nextLine();
        HashMap<String, List<String>> data = CSVTester.readCSV(filePath);
        CSVTester.printCSV(data);
    }

    private static void performForceCalculation() {
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
        CSVTester.writeCSV(outputFilePath, results);
    }

    private static void modifyData(boolean isSalting) {
        System.out.println("Please enter the full path of your CSV file:");
        String filePath = scanner.nextLine();
        HashMap<String, List<String>> data = CSVTester.readCSV(filePath);

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

    private static void saveDataToFile(HashMap<String, List<String>> data) {
        System.out.println("Enter the full path for the output CSV file:");
        String outputFilePath = scanner.nextLine();
        CSVTester.writeCSV(outputFilePath, data);
    }

    private static void printForceResults(HashMap<String, List<String>> results) {
        System.out.println("Variable (either mass 'm' or acceleration 'a'), Force:");
        List<String> variableData = results.get("Mass") != null ? results.get("Mass") : results.get("Acceleration");
        List<String> forceData = results.get("Force");
        for (int i = 0; i < variableData.size(); i++) {
            System.out.println(variableData.get(i) + " " + forceData.get(i));
        }
    }
}
