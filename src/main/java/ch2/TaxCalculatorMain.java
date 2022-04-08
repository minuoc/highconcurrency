package ch2;

public class TaxCalculatorMain {
    public static void main(String[] args) {
//        TaxCalaculator taxCalaculator = new TaxCalaculator(10000d,2000d) {
//            public double calcTax(){
//
//                return getSalary() * 0.1 + getBonus() * 0.15;
//            }
//        };
//
//        double tax = taxCalaculator.calculate();
//        System.out.println(tax);

        TaxCalaculator taxCalaculator = new TaxCalaculator(10000d,2000d);
        CalculatorStrategy calculatorStrategy = new SimpleCalculatorStrategy();
        taxCalaculator.setCalculatorStrategy(calculatorStrategy);
        double tax = taxCalaculator.calculate();
        System.out.println(tax);

    }
}
