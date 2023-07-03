package statutils;

import java.util.List;
import java.util.Collections;

public class Min extends Statistics{

    //Constructor that receives the list of data from main
    public Min(List<Double> _measurementData)
    {
        super(_measurementData);
    }  

    //Method that will calculate the value of "min"
    public double getMin()
    {
        //Extract max value from list
        return Collections.min(measurementData);
    }
}