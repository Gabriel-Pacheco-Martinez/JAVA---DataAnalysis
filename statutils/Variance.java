package statutils;

import java.util.List;

public class Variance extends Statistics{

    //Declare the variables to store the variance and the list
    //which contains the data received from the user
    private double variance; 

    //Constructor that receives the list of data from main
    public Variance(List<Double> _measurementData)
    {
        super(_measurementData);
    }  

    //Method that will calculate the value of the variance
    public double calculateVariance()
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
        for (int i = 0; i < measurementData.size(); i++) {
            sum2 += Math.pow(measurementData.get(i) - mean, 2);
        }
        variance = sum2/measurementData.size(); //Divide by N as it is "population"
        return variance;
    }
}
