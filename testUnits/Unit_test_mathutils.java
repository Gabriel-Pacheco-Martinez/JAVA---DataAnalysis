package testUnits;

import java.util.List;
import java.util.Arrays;

//the statistics formulas must be implemented 
import statutils.*;
import binmethod.*;
import mathutils.*;

public class Unit_test_mathutils {
    public static void main(String[] args) {
        //Create Array of data
        List<Double> exampleData = Arrays.asList(1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11.);

        //test Min class
        Min MinInstance = new Min(exampleData);
        double min = MinInstance.getMin();

        //test Max class
        Max MaxInstance = new Max(exampleData);
        double max = MaxInstance.getMax();

        //Calculate number of bins
        SturgesFormula SturgesInstance = new SturgesFormula(exampleData);
        int numberOfBins = SturgesInstance.calculateNumberOfBins();

        //Create Histogram Instance
        Histogram HistogramInstance = new Histogram(exampleData);

        //Call the function that Normalises data and use of Gaussian Curve Fitting
        double[][] normalizedDataSet = HistogramInstance.getProbabilityNormalisedHistogram(min,max,numberOfBins);
        double[] parameters = DataFitting.GaussianCurveFitting(normalizedDataSet,numberOfBins);

        //Show the user the PDF parameters
        double normalizationCoefficient = parameters[0];
        double mean = parameters[1];
        double sigma = parameters[2];

        //Display information to User
        System.out.printf("normalizationCoefficient: "+normalizationCoefficient+"\n");
        System.out.printf("mean: "+mean+"\n");
        System.out.printf("sigma: "+sigma+"\n");
    }
}
