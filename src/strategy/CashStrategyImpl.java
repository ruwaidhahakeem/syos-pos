package strategy;

//this is the method for cash - SYOS's main payment method
public class CashStrategyImpl implements PaymentStrategy {
    @Override
    public void payment(double amount) {
        System.out.println("Amount Payable: " + amount);

    }
}
