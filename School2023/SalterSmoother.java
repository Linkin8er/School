package StatsPartTwo;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileWriter;

/*
*   This is a program to take information from a CSV file and salt or smooth it, and write a CSV file as a result. 
*   Created by Nicholas Stephani, 04/22/2023
*/

/*
*   Definitely late to working on this, but hey, stress makes it more fun.
*   
 */
public class SalterSmoother{
    public static void main(String[] args){
        SalterSmoother runner = new SalterSmoother();
        //String filePath = "C:\\Users\\nicks\\Documents\\Programs\\function_csv.csv";
        String filePath = "C:\\SourceCode\\function_csv.csv";
        runner.CSVReader(filePath);
    }

    Random random = new Random();
    ArrayList<Integer> yValues = new ArrayList<Integer>();
    ArrayList<Integer> xValues = new ArrayList<Integer>();
    int salterMin = -5;
    int salterMax = 5;
    int listAverage = 0;

    public SalterSmoother(){

    }

    private void CSVReader(String filename){ 
        String sample = ",";
            String lineString;
            try{
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                //The first line is the titles
                while ((lineString = reader.readLine()) != null) { //Reads line by line until there are none left
                    String[] values = lineString.split(sample);//utilized to split the string
                    yValues.add(Integer.parseInt(values[0]));
                    xValues.add(Integer.parseInt(values[1]));
                    
                }
                arrayPrinter("Y values: ", yValues);
                arrayPrinter("X values: ", xValues);
                Salter(xValues);
                Smoother(xValues);
                CreateCSV(xValues);
                reader.close();
            }
            catch (IOException e){ //catches exception in the try block
                e.printStackTrace();//Prints this throwable and its backtrace
            }
    }

    private ArrayList<Integer> Salter(ArrayList<Integer> base){
        for(int i = 0; i < base.size(); i++){
            base.set(i, (base.get(i) + random.nextInt(salterMax)+salterMin));
        }
        arrayPrinter("X values: ", xValues);
        return base;
    }

    private ArrayList<Integer> Smoother(ArrayList<Integer> base){
        int whereAmI = 0;
        int average = 0;
        for (int x = 0; x < base.size(); x++){
            if(x < base.size() - 3) whereAmI = 3;
            else if(x < base.size() - 2) whereAmI = 2;
            else if(x < base.size() - 1) whereAmI = 1;
            else if(x < base.size()) whereAmI = 0;
            int y = x + whereAmI;
            int divisor = 0;
            average = 0;
            while(y > x-3 && y > 0){
                average += base.get(y);
                divisor++;
                y--;
            }
            base.set(x, average/divisor);
        }
        arrayPrinter("X values: ", xValues);
        return base;
    }

    private void CreateCSV(ArrayList list){
        try {
            File Creator = new File("Data.csv");
            if (Creator.createNewFile()) {
              System.out.println("Created file: " + Creator.getName());
            } else {
              System.out.println("File already exists.");
            }
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        try {
            FileWriter theWriter = new FileWriter("Data.csv");
            for(int i = 0; i < list.size(); i++){
                theWriter.write((i+1) + ", " + list.get(i) + "\n");
            }
            theWriter.close();
        } 
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
    }

    private void arrayPrinter(String label, ArrayList toPrint){
        System.out.println(label);
        for(int i = 0; i < toPrint.size(); i++){
            System.out.print(toPrint.get(i) + "\t");
        }
        System.out.println();
    }
}

