package statutils;

import java.util.Collections;
import java.util.List;

public class Median extends Statistics{

    //Declare the variables to store the median and the list
    //which contains the data received from the user
    private double median; 

    //Constructor that receives the list of data from main
    public Median(List<Double> _measurementData)
    {
        super(_measurementData);
    }  

    //Method that will calculate the value of the median
    public double calculateMedian()
    {
        //Obtian the size of the list
        int sizeList = measurementData.size();
        
        //Sort the data of the list. Which is necessary to obtain the median
        Collections.sort(measurementData);
        
        //Algorithm to obtain the median
        if (sizeList % 2 == 0)
        {
            median = ((double)measurementData.get(sizeList/2)+(double)measurementData.get(sizeList/2 - 1))/2;
        }     
        else
        {
            median = (double) measurementData.get(sizeList/2);
        }    
        return median;
    }
}
