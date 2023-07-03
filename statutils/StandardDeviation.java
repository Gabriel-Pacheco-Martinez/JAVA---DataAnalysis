package statutils;

import java.util.List;

public class StandardDeviation extends Statistics{

    //Declare the variables to store the standard deviation and the list
    //which contains the data received from the user
    private double standardDeviation; 

    //Constructor that receives the list of data from main
    public StandardDeviation(List<Double> _measurementData)
    {
        super(_measurementData);
    }  

    //Method that will calculate the value of the standard deviation
    public double calculateStandardDeviation()
    {
        //Calculate the mean
        double sum = 0;
        double mean;
        for(int i = 0; i < measurementData.size(); i++)
        {
            sum += measurementData.get(i);
        }
        mean = sum/(measurementData.size());

        //Calculate the variance
        double sum2=0;
        double variance;
        for (int i = 0; i < measurementData.size(); i++) {
            sum2 += Math.pow(measurementData.get(i) - mean, 2);
        }
        variance = sum2/measurementData.size(); //Divided by N as it is "population"

        //Calculate the standard deviation
        standardDeviation = Math.sqrt(variance);
        return standardDeviation;
    }
}