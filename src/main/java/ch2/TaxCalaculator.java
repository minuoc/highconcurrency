package ch2;

public class TaxCalaculator {
    private final double salary;
    private final double bonus;

    private CalculatorStrategy calculatorStrategy;


    public TaxCalaculator(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    public double calcTax(){
        return 0.0d;
    }

    public double calculate(){
        return calculatorStrategy.calculate(salary,bonus);
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public CalculatorStrategy getCalculatorStrategy() {
        return calculatorStrategy;
    }

    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }
}
