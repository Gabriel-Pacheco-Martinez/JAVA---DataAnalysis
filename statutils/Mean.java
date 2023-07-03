package statutils;

import java.util.List;

public class Mean extends Statistics{
    
    //Declare the variables to store the mean and the list
    //which contains the data received from the user
    private double mean; 

    //Constructor that receives the list of data from main
    public Mean(List<Double> _measurementData)
    {
        super(_measurementData);
    }  

    //Method that will calculate the value of the mean
    public double calculateMean()
    {
        //Sum all elements of the array
        double sum = 0;
        for(int i = 0; i < measurementData.size(); i++)
        {
            sum += measurementData.get(i);
        }
            
        //Calculate the mean by dividing the sum with
        //the length of the list
        mean = sum/(measurementData.size());
    
        return mean;
    }
}
