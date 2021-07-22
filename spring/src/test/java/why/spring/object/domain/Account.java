package why.spring.object.domain;

import why.spring.object.domain.rate.CompoundInterestRate;
import why.spring.object.domain.rate.SimpleInterestRate;

public class Account {
    private int accountNumber;//계좌번호
    private int amount;//예금액

    //interestRate interestRate = new CompoundInterestRate();
    //interestRate interestRate = new SimpleInterestRate();

    public Account(int accountNumber, int amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }


    public void calculateInterest(int when) {

    }
}
