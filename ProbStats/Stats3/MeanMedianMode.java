import java.util.ArrayList;
import java.util.HashMap;

public class MeanMedianMode {
    
    //Easy method, just adds up the values in a list, and divides by the total number of elements
    public double findMean(ArrayList<Double> dataSet){
        
        double dSum = 0;
        for(double iSingleElement : dataSet){
            dSum = dSum + iSingleElement;
        }
        double mean = dSum/dataSet.size();
        return mean;
    }

    //This one is also easy. It takes in a sorted list, and checks to see the size to find the median
    public double findMedian(ArrayList<Double> dataSet){

        double dMedian = 0;

        //Find the celing of size divided by 2 and that is the median
        if(dataSet.size()%2 == 1)dMedian = dataSet.get((int)(Math.ceil(dataSet.size()/2.0))-1);
        //take the average of the size divided by 2 and divided by 2 + 1
        else if(dataSet.size()%2 == 0 && dataSet.size() != 0)dMedian = (dataSet.get(dataSet.size()/2-1)+dataSet.get((dataSet.size()/2)))/2.0;
        else System.out.println("There is no median of nothing!");
        
        return dMedian;
    }

    //The mode is extra fun, it uses a hash map to store each value, and the number of times it is repeated
    public double findMode(ArrayList<Double> dataSet){

        HashMap<Double,Integer> modeTracker = new HashMap<Double,Integer>();

        int max  = 1;
        double currentMode = 0;

        for(int i = 0; i < dataSet.size(); i++) {

            if (modeTracker.get(dataSet.get(i)) != null) {

                int count = modeTracker.get(dataSet.get(i));
                count++;
                modeTracker.put(dataSet.get(i), count);

                if(count > max) {
                    max  = count;
                    currentMode = dataSet.get(i);
                }
            }

            modeTracker.put(dataSet.get(i), 1);
        }
        
        return currentMode;
    }
}
