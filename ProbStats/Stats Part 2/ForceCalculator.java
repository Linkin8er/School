import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This program calculates force and organizes the results in a map.
 * This was the formula I chose for my PSS programs
 */
public class ForceCalculator {

    // Default parameters for the calculator for testing
    String constant = "m";
    double constantValue = 800.7;
    double start = 0;
    double end = 100;
    double increment = 1;

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

        System.out.println("Enter the increment size for the variable range: ");
        increment = scanner.nextDouble();

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
     * @param increment The increment of the changing variable
     * @return A map where the keys are column headers and the values are lists of data
     */
    public HashMap<String, List<String>> calculateForce(String constant, double constantValue, double start, double end, double increment) {

        HashMap<String, List<String>> resultMap = new HashMap<>();
        List<String> values = new ArrayList<>();
        List<String> forces = new ArrayList<>();

        if ("m".equals(constant)) {
            resultMap.put("Acceleration", values);
            resultMap.put("Force", forces);

            for (double a = start; a <= end; a += increment) {
                double force = constantValue * a;
                values.add(String.format("%.2f", a));
                forces.add(String.format("%.2f", force));
            }
        } else if ("a".equals(constant)) {
            resultMap.put("Mass", values);
            resultMap.put("Force", forces);

            for (double m = start; m <= end; m += increment) {
                double force = m * constantValue;
                values.add(String.format("%.2f", m));
                forces.add(String.format("%.2f", force));
            }
        }
        
        return resultMap;
    }
}
