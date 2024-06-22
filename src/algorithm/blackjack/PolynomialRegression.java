package algorithm.blackjack;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class PolynomialRegression {

    public static void main(String[] args) {
        double[] inputSize = {512000, 1024000, 2000000, 4000000, 8000000, 16000000, 40000000};
        double[] executionTime = {5290271962.0, 11200390066.0, 26125999524.0, 77792243521.0, 250345099770.0, 693178485187.0, 2321694200704.0};

        // Perform polynomial regression for degree 2
        double[] mse2 = polynomialRegression(inputSize, executionTime, 2);
        System.out.println("2nd Degree Polynomial MSE: " + mse2[0]);
        System.out.println("2nd Degree Polynomial Relative MSE (%): " + mse2[1]);

        // Perform polynomial regression for degree 3
        double[] mse3 = polynomialRegression(inputSize, executionTime, 3);
        System.out.println("3rd Degree Polynomial MSE: " + mse3[0]);
        System.out.println("3rd Degree Polynomial Relative MSE (%): " + mse3[1]);
    }

    public static double[] polynomialRegression(double[] x, double[] y, int degree) {
        int n = x.length;
        double[][] X = new double[n][degree + 1];

        // Construct the Vandermonde matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= degree; j++) {
                X[i][j] = Math.pow(x[i], j);
            }
        }

        OLSMultipleLinearRegression regression = new OLSMultipleLinearRegression();
        regression.setNoIntercept(true);
        regression.newSampleData(y, X);

        double[] beta = regression.estimateRegressionParameters();
        double[] residuals = regression.estimateResiduals();
        double mse = 0;
        for (double residual : residuals) {
            mse += residual * residual;
        }
        mse /= n;

        // Calculate the variance of the actual data
        double meanY = 0;
        for (double value : y) {
            meanY += value;
        }
        meanY /= n;

        double variance = 0;
        for (double value : y) {
            variance += Math.pow(value - meanY, 2);
        }
        variance /= n;

        // Calculate relative MSE
        double relativeMSE = mse / variance * 100;

        return new double[] {mse, relativeMSE};
    }
}
