import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * This program goes through and plots the 
 */
public class ForceCalculator {

    // default parameters for the calulator for testing
    String constant = "m";
    double constantValue = 100;
    double start = 0;
    double end = 100;
    double incrament = 1;

    /**
     * Sets up the calculation by the user.
     */
    public void calculatorSetup() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the constant variable ('m' for mass or 'a' for acceleration): ");
        constant = scanner.nextLine();

        System.out.println("Enter the constant value: ");
        constantValue = scanner.nextDouble();

        System.out.println("Enter the start value for the variable range: ");
        start = scanner.nextDouble();

        System.out.println("Enter the end value for the variable range: ");
        end = scanner.nextDouble();

        System.out.println("Enter the incrament size for the variable range: ");
        incrament = scanner.nextDouble();

        scanner.close(); // Close the scanner to free resources
    }

    /**
     * Calculates force using the formula F = m * a over a specified range,
     * depending on which variable (mass or acceleration) is constant.
     *
     * @param constant Specifies which variable is constant ('m' or 'a')
     * @param constantValue The value of the constant variable
     * @param start The starting value of the variable that changes
     * @param end The ending value of the variable that changes
     * @param incrament The incrament of the changing variable
     * @return A list of lists of strings, where each inner list represents [variable value, force]
     */
    public List<List<String>> calculateForce(String constant, double constantValue, double start, double end, double incrament) {

        List<List<String>> records = new ArrayList<>();
        
        if ("m".equals(constant)) {
            for (double a = start; a <= end; a += incrament) {
                double force = constantValue * a;
                List<String> record = new ArrayList<>();
                record.add(String.format("%.2f", a));
                record.add(String.format("%.2f", force));
                records.add(record);
            }
        } else if ("a".equals(constant)) {
            for (double m = start; m <= end; m += incrament) {
                double force = m * constantValue;
                List<String> record = new ArrayList<>();
                record.add(String.format("%.2f", m));
                record.add(String.format("%.2f", force));
                records.add(record);
            }
        }
        
        return records;
    }
}
