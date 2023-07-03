package statutils;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Statistics 
{
    //Variable to store the list of data received 
    protected final List<Double> measurementData; 

    //Constructor that receives the list of data
    public Statistics(List<Double> _measurementData)
    {
        measurementData = _measurementData.stream().collect(Collectors.toList());
    }   

    //Get & Sets
    public List<Double> getMeasurementData(){return measurementData;}
}