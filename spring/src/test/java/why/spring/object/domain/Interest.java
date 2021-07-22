package why.spring.object.domain;

public class Interest {
    private int amount; //금액
    private int paymentDate; //지급일자

    public Interest(int amount, int paymentDate) {
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

}
