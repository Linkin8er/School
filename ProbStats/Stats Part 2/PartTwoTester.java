import java.util.ArrayList;
import java.util.List;
public class PartTwoTester {
    public static void main(String[] args) {

        CSVReaderWriter CSVTester = new CSVReaderWriter();
        String filePath = "C:/SourceCode/GAW.L.csv";

        // Reading and printing CSV
        List<List<String>> data = CSVTester.readCSV(filePath);
        CSVTester.printCSV(data);

        // Write the same data to a diffrent CSV to test writing 
        CSVTester.writeCSV("WritingTest.csv", data);

        ForceCalculator calculator = new ForceCalculator();
        // Setting up the calculator with predefined values (could also call calculatorSetup to input values)
        List<List<String>> results = calculator.calculateForce(calculator.constant, calculator.constantValue, calculator.start, calculator.end, calculator.incrament);

        // Print the results
        System.out.println("Variable (either mass 'm' or acceleration 'a'), Force:");
        for (List<String> result : results) {
            System.out.println(result.get(0) + " " + result.get(1));
        }
        CSVTester.writeCSV("WritingTest2.csv", results);
    }
}
