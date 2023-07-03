package binmethod;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BinFormulae 
{
    //Variable to store the number of bins
    private int k; 
    
    //Variable to store the list of data received 
    private final List<Double> measurementData; 

    //Constructor that receives the list of data
    public BinFormulae(List<Double> _measurementData)
    {
        measurementData = _measurementData.stream().collect(Collectors.toList());
    }   

    //Get & Sets
    public List<Double> getMeasurementData(){return measurementData;}
    public int getNumberOfBins(){return k;}
    public void setNumberOfBins(int _k){k = _k;}

    //Method that will be overriden by the three classes
    //that calculate the number of bins. This is simply
    //a declaration of the method
    public abstract int calculateNumberOfBins();
}