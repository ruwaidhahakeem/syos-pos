package strategy;

//this serves as a common interface for ALL payment methods
public interface PaymentStrategy {
    void payment(double amount);
}
