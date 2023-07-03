package statutils;

import java.util.List;
import java.util.Collections;

public class Max extends Statistics{

    //Constructor that receives the list of data from main
    public Max(List<Double> _measurementData)
    {
        super(_measurementData);
    }  

    //Method that will calculate the value of "max"
    public double getMax()
    {
        //Extract max value from list and return it
        return Collections.max(measurementData);
    }
}