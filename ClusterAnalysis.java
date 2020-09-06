import net.sf.javaml.clustering.Clusterer;
import net.sf.javaml.clustering.KMeans;
import net.sf.javaml.clustering.evaluation.ClusterEvaluation;
import net.sf.javaml.clustering.evaluation.SumOfSquaredErrors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

import java.io.*;

/**
 * Created by Tripptic on 9/5/2020.
 * Project: ACM coding challenge
 * File: ClusterAnalysis
 * Good Luck
 */

public class ClusterAnalysis
{
    public static void main(String[] args) throws IOException
    {
        // This determines the number of K groupings we will test
        int trials = (int)(new BufferedReader(new FileReader("ClusterPlotFixed.csv")).lines().count())/2;

        //Readers and writers to remove the first line of the CSV since the library doesn't like the first line of the provided one
        BufferedReader in = new BufferedReader(new FileReader("ClusterPlot.csv"));
        BufferedWriter out = new BufferedWriter(new FileWriter("ClusterPlotFixed.csv"));
        in.readLine();

        String line;
        while((line = in.readLine()) != null)
            out.write(line + "\n");
        out.close();

        //Used to keep track of the Sum of Squared Errors of all the K that we test
        double[] scores = new double[trials-1];

        //Library loads fixed csv into dataset ready to be clustered
        Dataset data = FileHandler.loadDataset(new File("ClusterPlotFixed.csv"), 0, ",");

        for (int k = 1; k < trials; k++)
        {

            //stores the best SSE score that the current K has gotten
            double bestScore = Double.MAX_VALUE;

            //runs 300 iterations of a given K in order to find best possible score for any given K
            for (int j = 0; j < 3; j++)
            {

                //uses the K-means clustering method to group dataset
                Clusterer km = new KMeans(k);

                Dataset[] clusters = km.cluster(data);

                ClusterEvaluation sse = new SumOfSquaredErrors();

                //Evaluates SSE
                double currentScore = sse.score(clusters);

                //Checks if this is the best score available
                if (sse.compareScore(bestScore, currentScore))
                    bestScore = currentScore;
            }
            scores[k-1] = bestScore;
        }

        /*
            Elbow Technique:
            goes through each K's SSE and finds the
            K at which the percentage difference between the different K's
            is less than 30%
         */
        for (int i = 1; i < scores.length; i++)
        {
            if((Math.abs(scores[i-1]-scores[i])/scores[i-1])<.3)
            {
                System.out.println("Optimal Number of Clusters: " + (i));
                break;
            }
        }

    }
}
