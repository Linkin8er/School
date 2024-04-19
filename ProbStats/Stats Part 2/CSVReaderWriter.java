import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
//https://www.baeldung.com/opencsv
public class CSVReaderWriter {
    public ArrayList<String> reader(String fileName){
        ArrayList<String> csvList = new ArrayList<String>();
        try { 
  
            // Create an object of filereader 
            // class with CSV file as a parameter. 
            FileReader filereader = new FileReader(fileName); 
  
            // create csvReader object passing 
            // file reader as a parameter 
            CSVReader csvReader = new CSVReader(filereader); 
            String[] nextRecord; 
  
            // we are going to read data line by line 
            
            while ((nextRecord = csvReader.readNext()) != null) { 
                for (String cell : nextRecord) { 
                    csvList.add(cell); 
                } 
            } 
            csvReader.close();
        }
        catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return csvList;
    }
    
    public String writer(ArrayList<String[]> lines, String path) throws Exception {
        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            for (String[] line : lines) {
                writer.writeNext(line);
            }
        return path;
        }
    }  
}
