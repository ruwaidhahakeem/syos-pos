package strategy;
import java.util.*;


public class CardStrategyImpl implements PaymentStrategy {

    @Override
    public void payment(double amount) {
        //implement card payment code here
        //input cardNo..etc

    }

    public void collectPaymentDetail() {
            System.out.println("Card No: ");
            //rest of the impl
            //have a creditCard.java class that initializes variables w getters and setters
    }
}
