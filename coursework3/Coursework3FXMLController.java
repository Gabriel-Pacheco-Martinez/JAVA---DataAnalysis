/**
 * Sample Skeleton for 'Coursework3FXML.fxml' Controller Class
 */

package coursework3;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

//Coursework inputs
import statutils.*;
import binmethod.*;
import mathutils.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Transform;

public class Coursework3FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="browseButton"
    private Button browseButton; // Value injected by FXMLLoader

    @FXML // fx:id="browseText"
    private TextField browseText; // Value injected by FXMLLoader

    @FXML // fx:id="squareBox"
    private CheckBox squareBox; // Value injected by FXMLLoader

    @FXML // fx:id="sturgesBox"
    private CheckBox sturgesBox; // Value injected by FXMLLoader

    @FXML // fx:id="riceBox"
    private CheckBox riceBox; // Value injected by FXMLLoader

    @FXML // fx:id="meanLabel1"
    private Label meanLabel1; // Value injected by FXMLLoader

    @FXML // fx:id="varianceLabel"
    private Label varianceLabel; // Value injected by FXMLLoader

    @FXML // fx:id="medianLabel"
    private Label medianLabel; // Value injected by FXMLLoader

    @FXML // fx:id="standarddeviationLabel"
    private Label standarddeviationLabel; // Value injected by FXMLLoader

    @FXML // fx:id="runButton"
    private Button runButton; // Value injected by FXMLLoader

    @FXML // fx:id="labelRun"
    private Label labelRun; // Value injected by FXMLLoader

    @FXML // fx:id="binsLabel"
    private Label binsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="normalizationLabel"
    private Label normalizationLabel; // Value injected by FXMLLoader

    @FXML // fx:id="meanLabel2"
    private Label meanLabel2; // Value injected by FXMLLoader

    @FXML // fx:id="sigmaLabel"
    private Label sigmaLabel; // Value injected by FXMLLoader

    @FXML // fx:id="saveButton"
    private Button saveButton; // Value injected by FXMLLoader

    @FXML // fx:id="clearButton"
    private Button clearButton; // Value injected by FXMLLoader

    @FXML // fx:id="rootPane"
    private GridPane rootPane; // Value injected by FXMLLoader

    @FXML // fx:id="nhPlot"
    private BarChart<?, ?> nhPlot; // Value injected by FXMLLoader

    @FXML // fx:id="xAxisHistogram"
    private CategoryAxis xAxisHistogram; // Value injected by FXMLLoader

    @FXML // fx:id="yAxisHistogram"
    private NumberAxis yAxisHistogram; // Value injected by FXMLLoader

    @FXML // fx:id="pdfPlot"
    private ScatterChart<?, ?> pdfPlot; // Value injected by FXMLLoader

    @FXML // fx:id="xAxisPDF"
    private CategoryAxis xAxisPDF; // Value injected by FXMLLoader

    @FXML // fx:id="yAxisPDF"
    private NumberAxis yAxisPDF; // Value injected by FXMLLoader

   //Global variable to store data read
    List<Double> incomingData = new ArrayList<>();
    String filePath;
    WritableImage imageHistogram;
    WritableImage imagePdf;

    @FXML
    void browseButtonPressed(ActionEvent event) throws FileNotFoundException {
        //Clear array where data  is stored
        incomingData.clear();

        //Choose file from where to read data
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File f = fc.showOpenDialog(null);
        if (f != null)
        {
            browseText.setText(f.getAbsolutePath());
            //filePath = f.getAbsolutePath();
            Scanner s = new Scanner(new File(f.getAbsolutePath()));

            //Copy data into "incomingData" list
            while (s.hasNextDouble())
            {
                incomingData.add(s.nextDouble());
            }
        } 
    }

    @FXML
    void clearButtonPressed(ActionEvent event) {
        
        //Clear all plots and labels
        nhPlot.getData().clear();
        pdfPlot.getData().clear();
        meanLabel1.setText("Mean:");
        varianceLabel.setText("Variance:");
        medianLabel.setText("Median:");
        standarddeviationLabel.setText("Standard Deviation:");
        binsLabel.setText("Number of Bins: ");
        normalizationLabel.setText("Normalization factor: ");
        meanLabel2.setText("Mean: ");
        sigmaLabel.setText("Sigma: ");
    }

    @FXML
    void riceBoxHandler(ActionEvent event) {
        //Only allow for the current box to be selected
        if (riceBox.isSelected())
        {
            squareBox.setSelected(false);
            sturgesBox.setSelected(false);
        }
    }

    @FXML
    void runButtonPressed(ActionEvent event) throws IOException {
        
        //==================================================================
        //Calculations           
        //==================================================================

        //Show the mean
        Mean MeanInstance = new Mean(incomingData);
        meanLabel1.setText("Mean:   " + MeanInstance.calculateMean());

        //Show the variance
        Variance VarianceInstance = new Variance(incomingData);
        varianceLabel.setText("Variance:   " + VarianceInstance.calculateVariance());

        //Show the median
        Median MedianInstance = new Median(incomingData);
        medianLabel.setText("Median:   " + MedianInstance.calculateMedian());

        //Show the standard deviation
        StandardDeviation StandardDeviationInstance = new StandardDeviation(incomingData);
        standarddeviationLabel.setText("Standard Deviation:   " + StandardDeviationInstance.calculateStandardDeviation());

        //test Min class
        Min MinInstance = new Min(incomingData);
        double min = MinInstance.getMin();

        //test Max class
        Max MaxInstance = new Max(incomingData);
        double max = MaxInstance.getMax();

        //Declaration of variables
        int numberOfBins=0; //Number of bins
        double w; //Width of each bin
        nhPlot.getData().clear(); //Clear the data on the plot
        pdfPlot.getData().clear();//Clear the data on the plot

        //Declaration of the series of data for the Histogram plot
        XYChart.Series normalisedHistogram = new XYChart.Series(); 

        //Calculate the number of Bins dependant on the method selected
        if (squareBox.isSelected())
        {
            //Calculate number of bins, set binsLabel text
            //and set the Histogram's name
            SquareRootChoice SquareRootChoiceInstance = new SquareRootChoice(incomingData);
            numberOfBins = SquareRootChoiceInstance.calculateNumberOfBins();
            binsLabel.setText("Number of Bins: " + numberOfBins);
            normalisedHistogram.setName("Square Root Formula");
        }

        else if (sturgesBox.isSelected())
        {
            //Calculate number of bins, set binsLabel text
            //and set the Histogram's name
            SturgesFormula SturgesInstance = new SturgesFormula(incomingData);
            numberOfBins = SturgesInstance.calculateNumberOfBins();
            binsLabel.setText("Number of Bins: " + numberOfBins);
            normalisedHistogram.setName("Sturge's Formula");

        }

        else if (riceBox.isSelected())
        {
            //Calculate number of bins, set binsLabel text
            //and set the Histogram's name
            RiceRule RiceRuleInstance = new RiceRule(incomingData);
            numberOfBins = RiceRuleInstance.calculateNumberOfBins();
            binsLabel.setText("Number of Bins: " + numberOfBins);
            normalisedHistogram.setName("Rice Rule Formula");
        }

        //Calculate width
        w = ((max)-(min))/numberOfBins; 

        //Create Histogram Instance
        Histogram HistogramInstance = new Histogram(incomingData);

        //Function for the Un-Normalised Histogram
        HistogramInstance.getFrequencyUnNormalisedHistogram(min,max,numberOfBins);

        //Call the function that Normalises data and use of Gaussian Curve Fitting
        double[][] normalizedDataSet = HistogramInstance.getProbabilityNormalisedHistogram(min,max,numberOfBins);
        double[] parameters = DataFitting.GaussianCurveFitting(normalizedDataSet,numberOfBins);

        //Show the user the PDF parameters
        double normalizationCoefficient = parameters[0];
        double mean = parameters[1];
        double sigma = parameters[2];

        //Set the parameter factors to the labels
        normalizationLabel.setText("Normalization factor:   " + normalizationCoefficient);
        meanLabel2.setText("Mean:   " + mean);
        sigmaLabel.setText("Sigma:   " + sigma);

        //==================================================================
        //Normalised Plot            
        //==================================================================
        
        //Loop to allocate the series
        for (int i=0; i<numberOfBins; i++)
        {
            //Assign retreived fitted values into "xN" and "yN". "x" values
            //will be multiplied by 10e6 as to fit the data correctly in 
            //the plot. Therefore the plot will be in scale of micro meters.
            double xN = normalizedDataSet[i][0] * Math.pow(10,6); //x data for Normalised Plot
            double yN = normalizedDataSet[i][1]; //y data for Normalised Plot

            //Create two strings that can represent the width of
            //each bin in the plot
            String xN1 = String.format("%.1f",xN);

            //Add the "x" and "y" data to the plot
            normalisedHistogram.getData().add(new XYChart.Data(xN1 ,yN));
        }

        //Plot the Normalised Histogram and save it as an Image
        nhPlot.getData().addAll(normalisedHistogram);
        SnapshotParameters sp =  new SnapshotParameters();
        sp.setTransform(Transform.scale(8, 8));
        imageHistogram = nhPlot.snapshot(sp ,null);

        
        //==================================================================
        //Probability Density Function
        //==================================================================

        //Declare the class to plot the PDF and define the y axis
        XYChart.Series probabilityDensityFunction = new XYChart.Series<>();
        probabilityDensityFunction.setName("PDF");
        
        //Declare two arrays that can store values of "x" and "y" to be able
        //to plot a scatter graph
        double[] xPDF = new double[201]; //x points for PDF plot
        double[] yPDF = new double [201]; //y points for PDF plot

        //Calculate a range of "x" over which the graph will be plotted
        //based on the standard deviation
        double range = max-min;
        
        //Allocate data for both the "x" and "y" arrays
        for (int i=0; i<201; i++)
        {
            //Allocate the "x" array with values that go from "min" to "max"
            xPDF[i]=(range*i/(200))+min;

            //Allocate the "y" array by using the PDF formula with
            //every single "x" point obtained and the PDF parameters
            //obtained previously
            double exponent = ((-0.5)*((xPDF[i]-mean)/sigma)*((xPDF[i]-mean)/sigma));
            yPDF[i]=(normalizationCoefficient)*(Math.exp(exponent));
        }  

        //Fill the scatter chart information with the data points obtained
        for (int i=0; i<201; i++)
        {
            //Define a string for the "x" point as the function only allows
            //string to enter the "x" parameter
            String xPoint = String.format("%.1f",xPDF[i]*Math.pow(10,6));

            //Then we can assign the data to the chart by entering the "x" 
            //point as a string and the previosly calculated "y" value
            probabilityDensityFunction.getData().add(new XYChart.Data(xPoint,yPDF[i]));
        }

        //Plot and save PDF as an Image
        pdfPlot.getData().add(probabilityDensityFunction);
        imagePdf = pdfPlot.snapshot(sp ,null);
    }
    

    @FXML
    void saveButtonPressed(ActionEvent event) throws IOException {
        
        //Take the Normalisation Histogram into an Image
        File imageBarChart = new File("Normalised Histogram.png");
        ImageIO.write(SwingFXUtils.fromFXImage(imageHistogram, null), 
            "png", imageBarChart);  
        
        //Take the Probability Density Function into an Image
        File imageScatterChart = new File("Probability Density Function.png");
        ImageIO.write(SwingFXUtils.fromFXImage(imagePdf, null), 
            "png", imageScatterChart);   
        
        //Message to user when Images are saved
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Message");
        alert.setHeaderText("Your charts have been saved as PNG files"
                + " in your NetBeans folder.");
        alert.show();
                
    }

    @FXML
    void squareBoxHandler(ActionEvent event) {
        
        //Only allow for the current box to be selected
        if (squareBox.isSelected())
        {
            riceBox.setSelected(false);
            sturgesBox.setSelected(false);
        }
    }

    @FXML
    void sturgesBoxHandler(ActionEvent event) {
        
        //Only allow for the current box to be selected
        if (sturgesBox.isSelected())
        {
            squareBox.setSelected(false);
            riceBox.setSelected(false);
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert browseButton != null : "fx:id=\"browseButton\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert browseText != null : "fx:id=\"browseText\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert squareBox != null : "fx:id=\"squareBox\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert sturgesBox != null : "fx:id=\"sturgesBox\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert riceBox != null : "fx:id=\"riceBox\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert meanLabel1 != null : "fx:id=\"meanLabel1\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert varianceLabel != null : "fx:id=\"varianceLabel\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert medianLabel != null : "fx:id=\"medianLabel\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert standarddeviationLabel != null : "fx:id=\"standarddeviationLabel\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert runButton != null : "fx:id=\"runButton\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert labelRun != null : "fx:id=\"labelRun\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert binsLabel != null : "fx:id=\"binsLabel\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert normalizationLabel != null : "fx:id=\"normalizationLabel\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert meanLabel2 != null : "fx:id=\"meanLabel2\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert sigmaLabel != null : "fx:id=\"sigmaLabel\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert clearButton != null : "fx:id=\"clearButton\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert rootPane != null : "fx:id=\"rootPane\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert nhPlot != null : "fx:id=\"nhPlot\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert xAxisHistogram != null : "fx:id=\"xAxisHistogram\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert yAxisHistogram != null : "fx:id=\"yAxisHistogram\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert pdfPlot != null : "fx:id=\"pdfPlot\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert xAxisPDF != null : "fx:id=\"xAxisPDF\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";
        assert yAxisPDF != null : "fx:id=\"yAxisPDF\" was not injected: check your FXML file 'Coursework3FXML.fxml'.";

    }
}
