/* 
 * Client code to test the statulis package
 */
package testUnits;

import java.util.List;
import java.util.Arrays;

//the statistics formulas must be implemented in the statulis package 
import statutils.*;
import binmethod.*;

public class Unit_test_statulis {

    public static void main(String[] args) {
        //Create Array of data
        List<Double> exampleData = Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11.);

        //Format the output
        System.out.printf("==============================================================\n");
        
        //test Mean class
        Mean MeanInstance = new Mean(exampleData);
        System.out.printf("The mean is: " + MeanInstance.calculateMean() + "\n");

        //test Median class
        Median MedianInstance = new Median(exampleData);
        System.out.printf("The median is: " + MedianInstance.calculateMedian() + "\n");

        //test Variance class
        Variance VarianceInstance = new Variance(exampleData);
        System.out.printf("The variance is: " + VarianceInstance.calculateVariance() + "\n");

        //test Standard Deviation class
        StandardDeviation StandardDeviationInstance = new StandardDeviation(exampleData);
        System.out.printf("The standard deviation is: " + StandardDeviationInstance.calculateStandardDeviation() + "\n");

        //test Min class
        Min MinInstance = new Min(exampleData);
        double min = MinInstance.getMin();
        System.out.printf("The min is: " + min + "\n");

        //test Max class
        Max MaxInstance = new Max(exampleData);
        double max = MaxInstance.getMax();
        System.out.printf("The max is: " + max + "\n");

        //Count the number of samples in each bin
        SquareRootChoice SquareRootChoiceInstance = new SquareRootChoice(exampleData);
        int k = SquareRootChoiceInstance.calculateNumberOfBins();
        System.out.printf("Bin size by Root Choice Formula: %d \n", SquareRootChoiceInstance.getNumberOfBins());

        //test the functionality to obtain the frequency of data in each bin
        Histogram HistogramInstance = new Histogram(exampleData);
        int[] unnormalizedDataSet = HistogramInstance.getFrequencyUnNormalisedHistogram(min, max, k);
        System.out.printf("==============================================================\n");
        for (int i = 0; i < k; i++) {
            System.out.printf("For bin " + (i + 1) + " the sample count is: ");
            System.out.printf(unnormalizedDataSet[i] + "\n");
        }
        System.out.printf("==============================================================\n");

        //test the functionality to perform histogram normalisation
        double[][] normalizedDataSet = HistogramInstance.getProbabilityNormalisedHistogram(min,max,k);
        for (int i = 0; i < k; i++) {
            System.out.printf("For bin " + (i + 1) + " starting at " + normalizedDataSet[i][0]);
            System.out.printf(" the probability is: " + normalizedDataSet[i][1] + "\n");
        }
        System.out.printf("==============================================================\n");
    }

}
