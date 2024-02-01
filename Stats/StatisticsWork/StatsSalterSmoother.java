import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class StatsSalterSmoother{
    public static void main(String[] args){
        StatsSalterSmoother runner = new StatsSalterSmoother("");
    }
    
    public StatsSalterSmoother(String file){
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("book.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }
    }
}