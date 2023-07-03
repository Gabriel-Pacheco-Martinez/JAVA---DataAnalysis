package statutils;

import java.util.List;

public class Histogram extends Statistics {

    //Constructor that receives the list of data from main
    public Histogram(List<Double> _measurementData)
    {
        super(_measurementData);
    }  

    //Method to obtain the frenquency of data for each bin. With the data 
    //obtained in this method one could plot the Un-Normalised Histogram.
    //This method will return an array with the frequency of each bin.
    public int[] getFrequencyUnNormalisedHistogram(double min, double max, int k)
    {
        //Declare and calculate the range for the bins. Declare an array
        //in which the frequency for each bin can be stored.
        double range = (max-min)/k;
        int[] frequency = new int[k];

        //Algorithm to determine the frequency for each bin with a nested
        //for loop and a counter.
        for (int i=0; i<k; i++)
        {
            int numberOfSamples = 0;
            
            for (int j=0; j<measurementData.size(); j++)
            {
                if ((measurementData.get(j)>=(min+range*i))&&(measurementData.get(j)<(min+range*(i+1))))
                {
                    numberOfSamples++;
                }
                
                else if ((i==(k-1))&&(measurementData.get(j)==(min+range*(i+1))))
                {
                    numberOfSamples++;
                }
            }
            
            frequency[i] = numberOfSamples;
        }
        return frequency;
    }

    //Method to obtain the probability of data for each bin. With the data 
    //obtained in this method one could plot the Normalised Histogram.
    //This method will return a 2d array with the starting point of each
    //bin in the column [0] and the probability for that respective bin
    //in the column [1].
    public double[][] getProbabilityNormalisedHistogram(double min, double max, int k)
    {
        //Calculate the width of each bin "w". Retreive the frequency for each
        //bin by using the previous method defined in the class. These two 
        //variables are needed in order to determine the normalised data.
        double w = ((max)-(min))/k; 
        int[] frequency = this.getFrequencyUnNormalisedHistogram(min, max, k);

        //Declare an array to store the probability for each bin, and a separate
        //2d array which stores the probability of each bin and the starting point
        //of that bin.
        double[] probability = new double[k];
        double [][] dataSet = new double[k][2];

        //Calculation of the sumation that must go in the denominator
        double sum=0;
        for (int i=0; i<k; i++)
        {
            sum += frequency[i]*w;
        }

        //Allocation of the probability for each bin. For this we divide
        //each individual frequency with the sum of all the frequencies
        //multiplied by the range calculated previosly
        for (int i=0; i<k; i++)
        {
            probability[i]=frequency[i]/sum;
        }

        //Allocate a 2d array. The first column will correspond to the "x"
        //values, which correspond to the beginning of each bin. The second 
        //column will correspond to the "y" values, which is the probability 
        //of each bin.
        for (int i=0; i<k; i++)
        {
            dataSet[i][0] = min+w*i; //x values
            dataSet[i][1] = probability[i]; //y values
        }

        return dataSet;
    }
}
