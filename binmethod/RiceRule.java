package binmethod;

import java.util.List;

public class RiceRule extends BinFormulae{

    //Constructor that receives the list of data from
    //which the number of bins will be calculated
    public RiceRule(List<Double> _measurementData)
    {
        super(_measurementData);
    } 

    //Method that calculates the number of Bins.
    //Whichever number calculated, the result will
    //be rounded up, given that the number of bins
    //must be a whole number
    @Override
    public int calculateNumberOfBins()
    {
        int n = getMeasurementData().size();
        setNumberOfBins((int)Math.ceil(2*Math.cbrt(n))); 
        return getNumberOfBins();
    }
}