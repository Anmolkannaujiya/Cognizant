package finacinalforecasting;

public class Main {

    public static void main(String[] args) {

        double presentValue = 10000;

        double growthRate = 0.10;

        int years = 5;

        double futureValue = FinancialForecast.predictValue(
                presentValue,
                growthRate,
                years);

        System.out.printf("Future Value = %.2f", futureValue);
    }
}