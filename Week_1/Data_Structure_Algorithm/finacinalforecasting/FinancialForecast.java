package finacinalforecasting;

public class FinancialForecast {

    // Recursive method to calculate future investment value
    public static double predictValue(double amount,
                                      double growthRate,
                                      int years) {

        // Base Case: years == 0
        if (years == 0)
            return amount;

        // Recursive Case: apply annual growth and reduce remaining years by 1
        return predictValue(amount * (1 + growthRate),
                growthRate,
                years - 1);
    }
}