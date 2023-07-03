package binmethod;

import java.util.List;

public class SturgesFormula extends BinFormulae {
    
    //Constructor that receives the list of data from
    //which the number of bins will be calculated
    public SturgesFormula(List<Double> _measurementData)
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
        double value = (Math.log10(n));
        setNumberOfBins((int)Math.ceil(3.3*value+1)); //Calculate bins rounding up
        return getNumberOfBins();
    }

}
