package mathutils;

import org.apache.commons.math3.fitting.*;

public class DataFitting {

    //Constructor that receives the "x" and "y" values for each bin
    //and its respective probability, along with the number of bins.
    public DataFitting(double[][] dataSet, int _k){}
    
    //Method which is used to fit the data into a gaussian curve
    //with the use of the function "GaussianCurveFitter" and returns
    //the PDF parameters.
    public static double[] GaussianCurveFitting(double[][] dataSet, int _k)
    {
        WeightedObservedPoints obs = new WeightedObservedPoints();

        for (int i=0; i<_k; i++)
        {
            obs.add(dataSet[i][0],dataSet[i][1]);
        }

        double[] parameters = GaussianCurveFitter.create().fit(obs.toList());
        return parameters;
    } 
}
